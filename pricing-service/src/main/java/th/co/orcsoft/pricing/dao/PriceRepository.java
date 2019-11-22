package th.co.orcsoft.pricing.dao;

import java.math.BigDecimal;
import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Repository;

@Repository
public class PriceRepository {
	
	
	private Map<String,BigDecimal> pricingMap = Stream.of(
			  new AbstractMap.SimpleEntry<>("Hawaiian Pizza", new BigDecimal("300")), 
			  new AbstractMap.SimpleEntry<>("Xiaolongbao", new BigDecimal("200")),
			  new AbstractMap.SimpleEntry<>("Kimchi",new BigDecimal("50")),
			  new AbstractMap.SimpleEntry<>("Oolong tea",new BigDecimal("30"))
			  ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	
	
	public BigDecimal findByMenuName(String name) {
		return pricingMap.get(name);
	}

}
