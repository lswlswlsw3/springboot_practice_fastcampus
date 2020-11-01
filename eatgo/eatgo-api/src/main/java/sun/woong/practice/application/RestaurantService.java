package sun.woong.practice.application;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sun.woong.practice.domain.MenuItem;
import sun.woong.practice.domain.MenuItemRepository;
import sun.woong.practice.domain.Restaurant;
import sun.woong.practice.domain.RestaurantRepository;

@Service
public class RestaurantService {
	@Autowired
	RestaurantRepository restaurantRepository;

	@Autowired
	MenuItemRepository menuItemRepository;

	public RestaurantService(RestaurantRepository restaurantRepository, MenuItemRepository menuItemRepository) {
		this.restaurantRepository	= restaurantRepository;
		this.menuItemRepository		= menuItemRepository;
	}

	public List<Restaurant> getRestaurants() {
		List<Restaurant> restaurants = restaurantRepository.findAll();
		return restaurants;
	}
	
	public Restaurant getRestaurant(Long id) {
		Restaurant restaurant = restaurantRepository.findById(id).orElse(null);
		
		List<MenuItem> menuItems = menuItemRepository.findAllByRestaurantId(id);
		restaurant.setMenuItems(menuItems);
		
		return restaurant;
	}

	public Restaurant addRestaurant(Restaurant restaurant) {
		return restaurantRepository.save(restaurant);
	}

	@Transactional
	public Restaurant updateRestaurant(Long id, String name, String address) {
		Restaurant restaurant = restaurantRepository.findById(id).orElse(null);
		
		restaurant.updateInformation(name, address);
		
		return restaurant;
	}
}
