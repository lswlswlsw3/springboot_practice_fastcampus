package sun.woong.practice.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import sun.woong.practice.domain.MenuItem;
import sun.woong.practice.domain.MenuItemRepository;
import sun.woong.practice.domain.Restaurant;
import sun.woong.practice.domain.RestaurantRepository;

public class RestaurantServiceTests {
	private RestaurantService restaurantService;

	@Mock
	private RestaurantRepository restaurantRepository;
	
	@Mock
	private MenuItemRepository menuItemRepository;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		mockRestaurantRepository();
		mockMenuItemRepository();
		
		restaurantService =	new RestaurantService(restaurantRepository, menuItemRepository);
	}

	public void mockRestaurantRepository() {
		List<Restaurant> restaurants = new ArrayList<>();
		Restaurant restaurant = Restaurant.builder()
				.id(1004L)
				.name("Bob zip")
				.address("Seoul")
				.menuItems(new ArrayList<MenuItem>())
				.build();
		restaurants.add(restaurant);

		given(restaurantRepository.findAll()).willReturn(restaurants);
		given(restaurantRepository.findById(1004L)).willReturn(Optional.of(restaurant));
	}
	
	public void mockMenuItemRepository() {
		List<MenuItem> menuItems = new ArrayList<>();
		menuItems.add(new MenuItem("Kimchi"));
		given(menuItemRepository.findAllByRestaurantId(1004L)).willReturn(menuItems);
	}
	
	@Test
	public void getRestaurants() {
		List<Restaurant> restaurants = restaurantService.getRestaurants();
		Restaurant restaurant = restaurants.get(0);
		assertThat(restaurant.getId(), is(1004L));
	}
	
	@Test
	public void getRestaurant() {
		Restaurant restaurant = restaurantService.getRestaurant(1004L);
		assertThat(restaurant.getId(), is(1004L));
		
		MenuItem menuItem = restaurant.getMenuItmes().get(0);
		assertThat(menuItem.getName(), is("Kimchi"));
	}
	
	@Test
	public void addRestaurant() {
		Restaurant restaurant	= new Restaurant("BeRyong", "Busan");
		Restaurant saved		= new Restaurant(1234L, "BeRyong", "Busan");
		
		given(restaurantRepository.save(any())).willReturn(saved);
		Restaurant created		= restaurantService.addRestaurant(restaurant);
		assertThat(created.getId(), is(1234L));
	}
	
	@Test
	public void updateRestaurant() {
		Restaurant restaurant = new Restaurant(1004L, "Bob zip", "Seoul");
		
		given(restaurantRepository.findById(1004L)).willReturn(Optional.of(restaurant));
		
		Restaurant updated = restaurantService.updateRestaurant(1004L, "Sool zip", "Busan");
		
		assertThat(updated.getName(), is("Sool zip"));
		assertThat(updated.getAddress(), is("Busan"));
	}
}
