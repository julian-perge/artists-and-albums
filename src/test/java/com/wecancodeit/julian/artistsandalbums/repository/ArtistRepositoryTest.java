package com.wecancodeit.julian.artistsandalbums.repository;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
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

import java.util.Optional;

import javax.persistence.EntityManager;

import org.junit.After;
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
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.wecancodeit.julian.artistsandalbums.entity.Album;
import com.wecancodeit.julian.artistsandalbums.entity.Artist;
import com.wecancodeit.julian.artistsandalbums.entity.Song;
import com.wecancodeit.julian.artistsandalbums.repository.ArtistRepositoryTest;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@DirtiesContext()
public class ArtistRepositoryTest {
	
	@Autowired  private ArtistRepository artistRepo;
	@Autowired private AlbumRepository albumRepo;
	@Autowired private SongRepository songRepo;
	
	@Autowired private EntityManager em;
	
	@Before
	public void setUp() throws Exception {

//		Song testSong = songRepo.save(new Song("testLength", "testRating", "testSongName", "testLyrics", "testVideoUrl", testAlbum));
	}
	
	@Test
	public void artistRepoShouldSaveArtistAndNotBeNull() throws Exception {
		Artist testArtist = artistRepo.save(new Artist("TestName", "TestLabel"));
		
		em.flush();
		em.clear();
		
		assertNotNull(artistRepo.findOne(1L));
		assertTrue(artistRepo.exists(1L));
	}
	
	@Test
	public void artistShouldEstablishRelationshipToAlbum() throws Exception {
		Artist testArtist = artistRepo.save(new Artist("TestName", "TestLabel"));
		Album testAlbum = albumRepo.save(new Album("testAlbumName", "testReleaseDate", "testGenre", "testCoverImage", testArtist));
		long artistId = testArtist.getId();
		long albumId = testAlbum.getId();
		
		em.flush();
		em.clear();
		
		assertThat(artistRepo.findOne(artistId).getArtistName(), is(equalTo("TestName")));
		assertTrue(artistRepo.findOne(artistId).getAlbums().contains(albumRepo.findOne(albumId)));
	}
}
