package sun.woong.practice.domain;

import java.util.List;

public interface RestaurantRepository {
	List<Restaurant> findAll();

	Restaurant findById(Long id);
}