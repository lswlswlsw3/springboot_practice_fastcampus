package sun.woong.practice.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Builder
@Setter
@NoArgsConstructor
public class Restaurant {
	@Id
	@GeneratedValue
	private Long id;

	private String name;
	
	private String address;
	
	private String regionName;
	
	private String categoryName;
	
	private String tagNames;
	
	@Transient
	private List<MenuItem> menuItems = new ArrayList<MenuItem>();
	
	public Restaurant(String name, String address) {
		this.name = name;
		this.address = address;		
	}
	
	public Restaurant(Long id, String name, String address) {
		this.id = id;
		this.name = name;
		this.address = address;
	}
	
	public Object getInformation() {
		return name+" in "+address;
	}

	public List<MenuItem> getMenuItmes() {
		return menuItems;
	}
	
	public void addMenuItem(MenuItem menuItem) {
		menuItems.add(menuItem);
	}
	
	public void setMenuItems(List<MenuItem> menuItems) {
		for(MenuItem menuItem : menuItems) {
			addMenuItem(menuItem);
		}
	}

	public void updateInformation(String name, String address) {
		this.name = name;
		this.address = address;
	}
}
