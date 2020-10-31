package sun.woong.practice.interfaces;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import sun.woong.practice.domain.MenuItem;
import sun.woong.practice.domain.MenuItemRepository;
import sun.woong.practice.domain.Restaurant;
import sun.woong.practice.domain.RestaurantRepository;

@RestController
public class RestaurantController {
	@Autowired
	private RestaurantRepository repository;

	@Autowired
	private MenuItemRepository menuItemRepository;
	
	@GetMapping("/restaurants")
	public List<Restaurant> list() {
		List<Restaurant> restaurants = repository.findAll();
		return restaurants;
	}
	
	@GetMapping("/restaurants/{id}")
	public Restaurant detail(@PathVariable Long id) {
		Restaurant restaurant = repository.findById(id);
		
		List<MenuItem> menuItmes = menuItemRepository.findAllByRestaurantId(id);
		restaurant.addMenuItem(new MenuItem("Kimchi"));
		
		return restaurant;
	}
}
