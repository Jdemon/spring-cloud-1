package th.co.orcsoft.menu.api;

import java.math.BigDecimal;

public class MenuAPI {

	private String name;
	private String menuDesc;
	private BigDecimal price;

	public MenuAPI() {

	}

	public MenuAPI(String name, String menuDesc, BigDecimal price) {
		super();
		this.name = name;
		this.menuDesc = menuDesc;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMenuDesc() {
		return menuDesc;
	}

	public void setMenuDesc(String menuDesc) {
		this.menuDesc = menuDesc;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
