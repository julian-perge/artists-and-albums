package com.wecancodeit.julian.artistsandalbums;

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
	  Artist artDeath = artistRepo.save(new Artist("Death", "Relapse"));
	  
	  Album albHuman = albumRepo.save(new Album("Human", "1991", "Death Metal", "humanAlbumCover.jpg", artDeath));
	  
	  
	  Album albIndividualThoughtPatterns = albumRepo.save(new Album("Individual Thought Patterns", "1993", "Death Metal", "individualThoughtPatternsAlbumCover.jpg", artDeath));
	  
	  
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
	  
	  Album albSoundOfPerseverance = albumRepo.save(new Album("Sound Of Perseverance", "1998", "Death Metal", "soundOfPerseveranceAlbumCover.jpg", artDeath));
	  Song sngScavengerOfHumanSorrow = songRepo.save(new Song("6m56s", "5", "Scavenger of Human Sorrow", "humanSorrowLyrics", 
			  "<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/fa2-ylENJtE\" frameborder=\"0\" allow=\"autoplay; encrypted-media\" allowfullscreen></iframe>", albSoundOfPerseverance));
	  Song sngBiteThePain = songRepo.save(new Song("4m29s", "5", "Bite the Pain", "Bite the Pain lyrics", 
			  "<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/eYRC14UicI0\" frameborder=\"0\" allow=\"autoplay; encrypted-media\" allowfullscreen></iframe>", albSoundOfPerseverance));
	  Song sngSpiritCrusher = songRepo.save(new Song("6m47s", "5", "Spirit Crusher", "spiritCrusherLyrics", 
			  "<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/4_rYk_aJbcQ\" frameborder=\"0\" allow=\"autoplay; encrypted-media\" allowfullscreen></iframe>", albSoundOfPerseverance));
	  Song sngStoryToTell = songRepo.save(new Song("6m34s", "5", "Story to Tell", "storyToTellLyrics", 
			  "<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/TZfnhXQJvJo\" frameborder=\"0\" allow=\"autoplay; encrypted-media\" allowfullscreen></iframe>", albSoundOfPerseverance));
	  Song sngFleshAndThePowerItHolds = songRepo.save(new Song("8m26s", "5", "Flesh and the Power It Holds", "FleshAndThePowerLyrics", 
			  "<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/gXwBV_Ur53w\" frameborder=\"0\" allow=\"autoplay; encrypted-media\" allowfullscreen></iframe>", albSoundOfPerseverance));
	  Song sngVoiceOfTheSoul = songRepo.save(new Song("3m43s", "5", "Voice of the Soul", "voiceOfTheSoulLyrics", 
			  "<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/j3OPOYG6XIQ\" frameborder=\"0\" allow=\"autoplay; encrypted-media\" allowfullscreen></iframe>", albSoundOfPerseverance));
	  Song sngToForgiveIsToSuffer = songRepo.save(new Song("5m55s", "5", "To Forgive Is to Suffer", "forgiveIsToSufferLyrics", 
			  "<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/uWHShZmYkk0\" frameborder=\"0\" allow=\"autoplay; encrypted-media\" allowfullscreen></iframe>", albSoundOfPerseverance));
	  Song sngMomentOfClarity = songRepo.save(new Song("7m25s", "5", "A Moment of Clarity", "momentOfClarityLyrics", 
			  "<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/tSEE9U5UYJ4\" frameborder=\"0\" allow=\"autoplay; encrypted-media\" allowfullscreen></iframe>", albSoundOfPerseverance));
	  Song sngPainkiller = songRepo.save(new Song("6m02s", "5", "Painkiller", "painkillerLyrics", 
			  "<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/8By9DnqCHac\" frameborder=\"0\" allow=\"autoplay; encrypted-media\" allowfullscreen></iframe>", albSoundOfPerseverance));
  }
}
