package com.wecancodeit.julian.artistsandalbums;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import com.wecancodeit.julian.artistsandalbums.entity.Album;
import com.wecancodeit.julian.artistsandalbums.entity.Artist;
import com.wecancodeit.julian.artistsandalbums.entity.Song;
import com.wecancodeit.julian.artistsandalbums.repository.AlbumRepository;
import com.wecancodeit.julian.artistsandalbums.repository.ArtistRepository;
import com.wecancodeit.julian.artistsandalbums.repository.SongRepository;

@Service
public class SitePopulator implements CommandLineRunner {

	@Autowired private ArtistRepository artistRepo;
	@Autowired private AlbumRepository albumRepo;
	@Autowired private SongRepository songRepo;
	
  @Override
  public void run(String... args) throws Exception {
	  Song sngCrystalMountain = songRepo.save(new Song("5 minutes", "5", "Crystal Mountain", "DeathLyrics", "a youtube link"));
	  Album albSymbolic = albumRepo.save(new Album("Symbolic", "1995", "Death Metal", "SymbolicCoverImage", sngCrystalMountain));
	  Artist artDeath = artistRepo.save(new Artist("Death", "Roadrunner", albSymbolic));

  }
}
