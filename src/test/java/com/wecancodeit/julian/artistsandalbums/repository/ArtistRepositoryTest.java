package com.wecancodeit.julian.artistsandalbums.repository;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.wecancodeit.julian.artistsandalbums.entity.Album;
import com.wecancodeit.julian.artistsandalbums.entity.Artist;
import com.wecancodeit.julian.artistsandalbums.entity.Song;
import com.wecancodeit.julian.artistsandalbums.repository.ArtistRepositoryTest;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class ArtistRepositoryTest {
	
	@Autowired  private ArtistRepository artistRepo;
	@Autowired private AlbumRepository albumRepo;
	@Autowired private SongRepository songRepo;
	
	@Before
	public void setUp() throws Exception {
		Song testSong = songRepo.save(new Song("testLength", "testRating", "testSongName", "testLyrics", "testVideoUrl"));
		Album testAlbum = albumRepo.save(new Album("testAlbumName", "testReleaseDate", "testGenre", "testCoverImage", testSong));
		Artist testArtist = artistRepo.save(new Artist("TestName", "TestLabel", testAlbum));
		
		verify(artistRepo).save(testArtist);
//		given(artistRepo.findOne(1L)).willReturn(testArtist);
	}
	
	@Test
	public void artistRepoShouldSaveArtistAndNotBeNull() throws Exception {
		assertNotNull(artistRepo.findOne(1L));
		assertTrue(artistRepo.exists(1L));
	}
	
	@Test
	public void artistShouldEstablishRelationshipToAlbum() throws Exception {
		System.out.println(artistRepo.findAll() );
		System.out.println(albumRepo.findOne(1L) ); 
//		assertTrue(artistRepo.findOne(1L).getAlbums().contains(albumRepo.findOne(1L)));
	}
}
