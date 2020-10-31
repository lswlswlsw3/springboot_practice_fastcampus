package sun.woong.practice.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class RestaurantRepositoryImpl implements RestaurantRepository {
	List<Restaurant> restaurants = new ArrayList<>();
	
	public RestaurantRepositoryImpl() {
		restaurants.add(new Restaurant(1004L, "Bob zip", "Seoul"));
		restaurants.add(new Restaurant(2020L, "Cyber food", "Seoul"));
	}
	
	@Override
	public List<Restaurant> findAll() {
		return restaurants;
	}

	@Override
	public Restaurant findById(Long id) {
		return restaurants.stream()
				.filter(r -> r.getId().equals(id))
				.findFirst()
				.orElse(null);
	}
}