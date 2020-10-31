package sun.woong.practice.interfaces;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import sun.woong.practice.domain.RestaurantRepository;
import sun.woong.practice.domain.RestaurantRepositoryImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(RestaurantController.class)
public class RestauruntControllerTests {
	@Autowired
	private MockMvc mvc;

	@SpyBean(RestaurantRepositoryImpl.class)
	private RestaurantRepository restaurantRepository;

	@Test
	public void list() throws Exception {
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
		mvc.perform(get("/restaurants/1004"))
			.andExpect(content().string(
					containsString("\"id\":1004")
			))
			.andExpect(content().string(
					containsString("\"name\":\"Bob zip\"")
			))
			.andExpect(content().string(
					containsString("Kimchi")));
		
		mvc.perform(get("/restaurants/2020"))
			.andExpect(content().string(
					containsString("\"id\":2020")
			))
			.andExpect(content().string(
					containsString("\"name\":\"Cyber food\"")
			));
	}

}