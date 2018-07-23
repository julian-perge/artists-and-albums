package com.wecancodeit.julian.artistsandalbums.api_controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wecancodeit.julian.artistsandalbums.entity.Artist;
import com.wecancodeit.julian.artistsandalbums.entity.Band;
import com.wecancodeit.julian.artistsandalbums.repository.ArtistRepository;
import com.wecancodeit.julian.artistsandalbums.repository.BandRepository;

@RestController
@RequestMapping("/api")
public class ArtistApiController {
	@Autowired private ArtistRepository artistRepo;
	@Autowired private BandRepository bandRepo;
	
	  @RequestMapping(value = "/artists", method = RequestMethod.GET)
	  public Collection<Artist> getArtists() {
	    return (Collection<Artist>) artistRepo.findAll();
	  }

	  @RequestMapping(value = "/artists/{artistName}", method = RequestMethod.GET)
	  public Artist getArtist(@PathVariable(name = "artistName") String artistName) {
	    return artistRepo.findByArtistName(artistName);
	  }

	  @RequestMapping(value = "/artists/add-artist", method = RequestMethod.POST)
	  public Collection<Artist> addArtist(
	      @RequestParam(value = "artistName") String artistName,
	      @RequestParam(value = "bandName") String bandName) {
		  Band bandToAddToArtist = bandRepo.findByBandName(bandName);
	    if (bandName == "") {
	      artistRepo.save(new Artist(artistName.trim(), null));
	    } else if (artistRepo.findByArtistName(artistName.trim()) == null) {
	        if (bandToAddToArtist != null) {
	          artistRepo.save(new Artist(artistName.trim(), bandToAddToArtist));
	      }
	    }
	    return (Collection<Artist>) artistRepo.findAll();
	  }
	
}
