package sun.woong.practice.interfaces;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.any;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.head;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import sun.woong.practice.application.RestaurantService;
import sun.woong.practice.domain.MenuItem;
import sun.woong.practice.domain.Restaurant;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@WebMvcTest(RestaurantController.class)
public class RestauruntControllerTests {
	@Autowired
	private MockMvc mvc;

	@MockBean
	private RestaurantService restaurantService;

	@Test
	public void list() throws Exception {
		List<Restaurant> restaurants = new ArrayList<>();
		restaurants.add(new Restaurant(1004L, "Bob zip", "Seoul"));

		given(restaurantService.getRestaurants()).willReturn(restaurants);
		
		mvc.perform(get("/restaurants"))
			.andExpect(status().isOk())
			.andExpect(content().string(
					containsString("\"id\":1004")
			))
			.andExpect(content().string(
					containsString("\"name\":\"Bob zip\"")
			));
	}
	
	@Test
	public void detail() throws Exception {
		Restaurant restaurant1 = new Restaurant(1004L, "ZOKER HOUSE", "Seoul");
		restaurant1.addMenuItem(new MenuItem("Kimchi"));
		
		Restaurant restaurant2 = new Restaurant(2020L, "Cyber food", "Seoul");

		given(restaurantService.getRestaurant(1004L)).willReturn(restaurant1);
		given(restaurantService.getRestaurant(2020L)).willReturn(restaurant2);
		
		mvc.perform(get("/restaurants/1004"))
			.andExpect(content().string(
					containsString("\"id\":1004")
			))
			.andExpect(content().string(
					containsString("\"name\":\"ZOKER HOUSE\"")
			))
			.andExpect(content().string(
					containsString("Kimchi")
			));
		
		mvc.perform(get("/restaurants/2020"))
			.andExpect(content().string(
					containsString("\"id\":2020")
			))
			.andExpect(content().string(
					containsString("\"name\":\"Cyber food\"")
			));
	}

	@Test
	public void create() throws Exception {
		mvc.perform(post("/restaurants")
			.contentType(MediaType.APPLICATION_JSON)
			.content("{\"name\":\"BeRyong\", \"Address\":\"Busan\"}"))
			.andExpect(status().isCreated())
			.andExpect(header().string("location", "/restaurants/1234"))
			.andExpect(content().string("{}"));
		
		verify(restaurantService).addRestaurant(any());
	}
	
	@Test
	public void update() throws Exception {
		mvc.perform(patch("/restaurants/1004")
			.contentType(MediaType.APPLICATION_JSON)
			.content("{\"name\":\"JOCKER Bar\", \"Address\":\"Busan\"}"))
			.andExpect(status().isOk());
		
		verify(restaurantService).updateRestaurant(1004L, "JOKER Bar", "Busan");
	}
}
