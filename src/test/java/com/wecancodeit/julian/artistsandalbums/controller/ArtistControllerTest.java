package com.wecancodeit.julian.artistsandalbums.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.wecancodeit.julian.artistsandalbums.entity.Artist;
import com.wecancodeit.julian.artistsandalbums.repository.ArtistRepository;
import com.wecancodeit.julian.artistsandalbums.repository.ArtistRepositoryTest;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(ArtistController.class)
public class ArtistControllerTest {
	@Autowired private MockMvc mvc;
	
	@MockBean private ArtistRepository artistRepo;
	
	@Mock private Artist testArtist;
	
	@Test
	public void shouldReturnModelAndViewOfArtistsAndBe2xxSuccessful() throws Exception {
		mvc.perform(get("/artists")).andExpect(status().is2xxSuccessful())
		.andExpect(model().attribute("artists", is(equalTo(artistRepo.findAll()))))
		.andExpect(view().name(is(equalTo("artists"))));
	}
	
	@Test
	public void shouldReturnModelAndViewOfArtistAndBe2xxSuccessful() throws Exception {
		given(artistRepo.findByArtistName("Death")).willReturn(testArtist);
		
		mvc.perform(get("/artists/Death")).andExpect(status().is2xxSuccessful())
		.andExpect(model().attribute("artist", is(equalTo(artistRepo.findByArtistName("Death")))))
		.andExpect(view().name(is(equalTo("artist"))));
	}
}
