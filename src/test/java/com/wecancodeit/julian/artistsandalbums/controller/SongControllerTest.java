package com.wecancodeit.julian.artistsandalbums.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.wecancodeit.julian.artistsandalbums.entity.Song;
import com.wecancodeit.julian.artistsandalbums.repository.AlbumRepository;
import com.wecancodeit.julian.artistsandalbums.repository.ArtistRepository;
import com.wecancodeit.julian.artistsandalbums.repository.SongRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(SongController.class)
public class SongControllerTest {
	@Autowired MockMvc mvc;
	
//	@MockBean ArtistRepository artistRepo;
//	@MockBean AlbumRepository albumRepo;
	@MockBean SongRepository songRepo;
	@Mock Song song;
	
	@Test
	public void modelAndViewShouldReturnSingleSongFromAlbum() throws Exception {
		
//		given(artistRepo.findOne(1L).getArtistName()).willReturn("Death");
//		given(albumRepo.findOne(1L).getAlbumName()).willReturn("Symbolic");
		given(songRepo.findBySongName("Death")).willReturn(song);
		
		mvc.perform(get("/artists/Death/Symbolic/Zero Tolerance")).andExpect(status().is2xxSuccessful());
	}
}
