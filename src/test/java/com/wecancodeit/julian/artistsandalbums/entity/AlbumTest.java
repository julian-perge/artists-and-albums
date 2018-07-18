package com.wecancodeit.julian.artistsandalbums.entity;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wecancodeit.julian.artistsandalbums.entity.Album;

@RunWith(SpringJUnit4ClassRunner.class)
public class AlbumTest {
	
	@Mock Album testAlbum;
	
	@Test
	public void artistShouldHaveGeneratedId() throws Exception {
		given(testAlbum.getId()).willReturn(1L);
		assertThat(testAlbum.getId(), is(equalTo(1L)));
	}
}
