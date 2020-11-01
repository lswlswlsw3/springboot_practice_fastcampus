package sun.woong.practice.interfaces;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import sun.woong.practice.application.RestaurantService;
import sun.woong.practice.domain.MenuItem;
import sun.woong.practice.domain.MenuItemRepository;
import sun.woong.practice.domain.Restaurant;
import sun.woong.practice.domain.RestaurantRepository;

@CrossOrigin
@RestController
public class RestaurantController {
	@Autowired
	private RestaurantService restaurantService;
	
	@GetMapping("/restaurants")
	public List<Restaurant> list() {
		List<Restaurant> restaurants = restaurantService.getRestaurants();
		return restaurants;
	}
	
	@GetMapping("/restaurants/{id}")
	public Restaurant detail(@PathVariable Long id) {
		Restaurant restaurant = restaurantService.getRestaurant(id);
		return restaurant;
	}
	
	//http POST localhost:8080/restaurants name=BeRyong address=Busan
	
	@PostMapping("/restaurants")
	public ResponseEntity<?> create(@RequestBody Restaurant resource)
				throws URISyntaxException {
		String name		= resource.getName();
		String address	= resource.getAddress();
		
		Restaurant restaurant = new Restaurant(name, address);
		restaurantService.addRestaurant(restaurant);
		
		URI location = new URI("/restaurants/" + restaurant.getId());
		return ResponseEntity.created(location).body("{}");
	}
	
	@PatchMapping("/restaurants/{id}")
	public String update(@PathVariable("id") Long id,
						 @RequestBody Restaurant resource) {
		String name		= resource.getName();
		String address	= resource.getAddress();
		
		restaurantService.updateRestaurant(id, name, address);
		
		return "{}";
	}
}
