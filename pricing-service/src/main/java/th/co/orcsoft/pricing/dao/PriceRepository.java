package th.co.orcsoft.pricing.dao;

import java.math.BigDecimal;
import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PriceRepository {
	
	@Value("${hawaiian_pizza.price}")
	private String hawaiian_pizza;
	@Value("${xiaolongbao.price}")
	private String xiaolongbao;
	@Value("${kimchi.price}")
	private String kimchi;
	@Value("${oolong.price}")
	private String oolong;
	
	private Map<String,BigDecimal> pricingMap = null;
	
	public BigDecimal findByMenuName(String name) {
		pricingMap = Stream.of(
					  new AbstractMap.SimpleEntry<>("Hawaiian Pizza", new BigDecimal(hawaiian_pizza)), 
					  new AbstractMap.SimpleEntry<>("Xiaolongbao", new BigDecimal(xiaolongbao)),
					  new AbstractMap.SimpleEntry<>("Kimchi",new BigDecimal(kimchi)),
					  new AbstractMap.SimpleEntry<>("Oolong tea",new BigDecimal(oolong))
					  ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
		
		return pricingMap.get(name);
	}

}
