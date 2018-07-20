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
	  Artist artDeath = artistRepo.save(new Artist("Death", "Roadrunner"));
	  
	  Album albSymbolic = albumRepo.save(new Album("Symbolic", "1995", "Death Metal", "symbolicAlbumCover.jpg", artDeath));
	  
	  Song sngSymbolic= songRepo.save(new Song("6m32s", "5", "Symbolic", "SymbolicLyrics", 
			  "<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/zbp60IX_jFQ\" frameborder=\"0\" allow=\"autoplay; encrypted-media\" allowfullscreen></iframe>", albSymbolic));
	  Song sngZeroTolerance = songRepo.save(new Song("4m48s", "5", "Zero Tolerance", "ZToleranceLyrics", 
			  "<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/gINrK9RJ6-o\" frameborder=\"0\" allow=\"autoplay; encrypted-media\" allowfullscreen></iframe>", albSymbolic));
	  Song sngEmptyWords = songRepo.save(new Song("6m22s", "5", "Empty Words", "EmptyWordsLyrics", 
			  "<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/Ha5VAfg_7CU\" frameborder=\"0\" allow=\"autoplay; encrypted-media\" allowfullscreen></iframe>", albSymbolic));
	  Song sngSacredSerenity = songRepo.save(new Song("4m26s", "5", "Sacred Serenity", "SacredLyrics", 
			  "<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/-as4LausEok\" frameborder=\"0\" allow=\"autoplay; encrypted-media\" allowfullscreen></iframe>", albSymbolic));
	  Song sng1000Eyes = songRepo.save(new Song("4m28s", "5", "1,000 Eyes", "1000EyesLyrics", 
			  "<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/JApkcEF0hws\" frameborder=\"0\" allow=\"autoplay; encrypted-media\" allowfullscreen></iframe>", albSymbolic));
	  Song sngWithoutJudgement = songRepo.save(new Song("5m28s", "5", "Without Judgement", "WithoutJudgementLyrics", 
			  "<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/Pt1WkhvxxMg\" frameborder=\"0\" allow=\"autoplay; encrypted-media\" allowfullscreen></iframe>", albSymbolic));
	  Song sngCrystalMountain = songRepo.save(new Song("5m07s", "5", "Crystal Mountain", "CrystalMountainLyrics", 
			  "<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/h8g5QsT-RSw\" frameborder=\"0\" allow=\"autoplay; encrypted-media\" allowfullscreen></iframe>", albSymbolic));
	  Song sngMisanthrope = songRepo.save(new Song("5m04s", "5", "Misanthrope", "MisanthropeLyrics", 
			  "<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/lfpWDOzE5nM\" frameborder=\"0\" allow=\"autoplay; encrypted-media\" allowfullscreen></iframe>", albSymbolic));
	  Song sngPerennialQuest = songRepo.save(new Song("8m22s", "5", "Perennial Quest", "Perennial Quest Lyrics", 
			  "<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/LQwfYuIrQF8\" frameborder=\"0\" allow=\"autoplay; encrypted-media\" allowfullscreen></iframe>", albSymbolic));
  }
}
