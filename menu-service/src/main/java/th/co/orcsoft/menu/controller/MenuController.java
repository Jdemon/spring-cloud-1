package th.co.orcsoft.menu.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
	public List<MenuAPI> findAllMenu(){
		
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
	
	private BigDecimal getPrice(String name) {
		
		String pricing_service_url = "http://PRICING-SERVICE/price?name=";
		
		BigDecimal price = restTemplate.getForObject(pricing_service_url+name, BigDecimal.class);
		return price;
	}
	
	

}
