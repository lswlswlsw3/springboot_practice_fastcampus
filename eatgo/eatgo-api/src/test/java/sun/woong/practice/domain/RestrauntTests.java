package sun.woong.practice.domain;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class RestrauntTests {
	@Test
	public void creation() {
		Restaurant restaurant = new Restaurant(1004L, "Bob zip", "Seoul");
		assertThat(restaurant.getId(), is(1004L));
		assertThat(restaurant.getName(), is("Bob zip"));
		assertThat(restaurant.getAddress(), is("Seoul"));
	}
	
	@Test
	public void information() {
		Restaurant restaurant = new Restaurant(1004L, "Bob zip", "Seoul");
		assertThat(restaurant.getInformation(), is("Bob zip in Seoul"));
	}
}
