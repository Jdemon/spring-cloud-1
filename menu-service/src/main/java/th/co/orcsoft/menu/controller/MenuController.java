package th.co.orcsoft.menu.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import th.co.orcsoft.menu.api.MenuAPI;
import th.co.orcsoft.menu.dao.MenuRepository;
import th.co.orcsoft.menu.dto.Menu;

@RestController
public class MenuController {
	
	@Autowired
	private MenuRepository repository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	@GetMapping("/{name}")
	public MenuAPI findName(@PathVariable("name") String name){
		Menu menu = repository.findByName(name);
		
		MenuAPI menuAPI = new MenuAPI();
		BeanUtils.copyProperties(menu, menuAPI);
		
		menuAPI.setPrice(getPrice(menu.getName()));
		
		return menuAPI;
	}
	
	
	@GetMapping()
	@HystrixCommand
//	@HystrixCommand(
//            fallbackMethod = "fallbackGetAll",
//            commandProperties = {
//                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
//            }
//    )
	public List<MenuAPI> findAllMenu(){
		
		randomlyRunLong();
		
		List<Menu> menuList = repository.findAll();
		List<MenuAPI> menuAPIs = new ArrayList<>();
		menuList.forEach(menu -> {
			MenuAPI menuAPI = new MenuAPI();
			BeanUtils.copyProperties(menu, menuAPI);
			
			menuAPI.setPrice(getPrice(menu.getName()));
			
			menuAPIs.add(menuAPI);
			
		});
		
		return menuAPIs;
	}
	
	public List<MenuAPI> fallbackGetAll(){
		List<MenuAPI> menuAPIs = new ArrayList<>();
		MenuAPI menuAPI = new MenuAPI();
		menuAPI.setMenuDesc("fallback");
		menuAPI.setName("fallback");
		menuAPI.setPrice(new BigDecimal(0));
		menuAPIs.add(menuAPI);
		return menuAPIs;
	}
	
	
	private void randomlyRunLong() {
        Random rand = new Random();
        int randomNum = rand.nextInt(3) + 1;
        if (randomNum == 3) sleep();
    }

    private void sleep() {
        try {
            Thread.sleep(11000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
	
	private BigDecimal getPrice(String name) {
		
		String pricing_service_url = "http://PRICING-SERVICE/price?name=";
		
		BigDecimal price = restTemplate.getForObject(pricing_service_url+name, BigDecimal.class);
		return price;
	}
	
	

}
