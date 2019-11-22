package th.co.orcsoft.menu.dto;

public class Menu {
	
	private String name;
	private String menuDesc;
	
	public Menu(String name, String menuDesc) {
		super();
		this.name = name;
		this.menuDesc = menuDesc;
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
	
	
	
}
