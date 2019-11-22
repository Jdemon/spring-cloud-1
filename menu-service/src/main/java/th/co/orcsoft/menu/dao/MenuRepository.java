package th.co.orcsoft.menu.dao;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import th.co.orcsoft.menu.dto.Menu;

@Component
public class MenuRepository {
	
	
	private Map<String,Menu> menuMap = Stream.of(
			  new AbstractMap.SimpleEntry<>("Hawaiian Pizza", new Menu("Hawaiian Pizza","All-time favourite toppings, Hawaiian pizza in Tropical Hawaii style.")), 
			  new AbstractMap.SimpleEntry<>("Xiaolongbao",new Menu("Xiaolongbao","Chinese steamed bun")),
			  new AbstractMap.SimpleEntry<>("Kimchi",new Menu("Kimchi","Traditional side dish made from salted and fermented vegetables")),
			  new AbstractMap.SimpleEntry<>("Oolong tea",new Menu("Oolong tea","Partially fermented tea grown in the Alishan area"))
			  ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	
	
	
	public List<Menu> findAll(){
		return menuMap.values().stream().collect(Collectors.toList());
	}
	
	public Menu findByName(String name) {
		return menuMap.get(name);
	}
	

}
