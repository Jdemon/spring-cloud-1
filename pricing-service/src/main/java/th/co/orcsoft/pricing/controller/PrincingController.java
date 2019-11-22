package th.co.orcsoft.pricing.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import th.co.orcsoft.pricing.dao.PriceRepository;

@RestController
public class PrincingController {
	
	@Autowired
	private PriceRepository repository;
	
	@GetMapping()
	public BigDecimal getPrice(@RequestParam("name") String name) {
		return repository.findByMenuName(name);
	}

}
