package com.wecancodeit.julian.artistsandalbums.api_controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wecancodeit.julian.artistsandalbums.entity.Album;
import com.wecancodeit.julian.artistsandalbums.entity.Artist;
import com.wecancodeit.julian.artistsandalbums.entity.Band;
import com.wecancodeit.julian.artistsandalbums.entity.RecordLabel;
import com.wecancodeit.julian.artistsandalbums.repository.BandRepository;
import com.wecancodeit.julian.artistsandalbums.repository.RecordLabelRepository;

@RestController
@RequestMapping(value="/api")
public class BandApiController {

  @Autowired private BandRepository bandRepo;
  @Autowired private RecordLabelRepository recordLabelRepo;

  @RequestMapping(value = "/bands", method = RequestMethod.GET)
  public Collection<Band> getBands() {
    return (Collection<Band>) bandRepo.findAll();
  }

  @RequestMapping(value = "/bands", method = RequestMethod.POST)
  public Collection<Band> addBand(
      @RequestParam(value = "bandName") String bandName,
      @RequestParam(value = "recordLabel") String recordLabel) {
    RecordLabel recordLabelToAdd = recordLabelRepo.findByLabelName(recordLabel);
    if (recordLabelToAdd == null) {
      // create new record label and save that to the artist
      recordLabelToAdd = recordLabelRepo.save(new RecordLabel(recordLabel));
    }
    bandRepo.save(new Band(bandName, null, recordLabelToAdd));
    return (Collection<Band>) bandRepo.findAll();
  }

  @RequestMapping(value = "/band/delete", method = RequestMethod.DELETE)
  public Collection<Band> deleteBand(@RequestParam(value = "bandName") String bandName) {
    Band bandToDelete = bandRepo.findByBandName(bandName);
    bandToDelete.getAlbums().removeAll(bandToDelete.getAlbums());
    bandToDelete.getArtists().removeAll(bandToDelete.getArtists());
    bandRepo.delete(bandToDelete);
    return (Collection<Band>) bandRepo.findAll();
  }

  @RequestMapping(value = "/band/{bandName}", method = RequestMethod.GET)
  public Band getBand(@PathVariable(name = "bandName") String bandName) {
    return bandRepo.findByBandName(bandName);
  }

  @RequestMapping(value = "/band/{bandName}/albums", method = RequestMethod.GET)
  public Collection<Album> getBandAlbums(@PathVariable(name = "bandName") String bandName) {
    return bandRepo.findByBandName(bandName).getAlbums();
  }

  @RequestMapping(value = "/band/{bandName}/artists", method = RequestMethod.GET)
  public Collection<Artist> getBandArtists(@PathVariable(name = "bandName") String bandName) {
    System.out.println(bandRepo.findByBandName(bandName).getBandName());
    return bandRepo.findByBandName(bandName).getArtists();
  }
}
