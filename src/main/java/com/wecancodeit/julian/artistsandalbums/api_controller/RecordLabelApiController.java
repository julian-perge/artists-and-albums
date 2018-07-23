package com.wecancodeit.julian.artistsandalbums.api_controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wecancodeit.julian.artistsandalbums.entity.Band;
import com.wecancodeit.julian.artistsandalbums.entity.RecordLabel;
import com.wecancodeit.julian.artistsandalbums.repository.RecordLabelRepository;

@RestController
@RequestMapping(value = "/api")
public class RecordLabelApiController {
	
	@Autowired private RecordLabelRepository recordLabelRepo;
	
	  @RequestMapping(value = "/recordLabels", method = RequestMethod.GET)
	  public Collection<RecordLabel> getRecordLabels() {
	    return (Collection<RecordLabel>) recordLabelRepo.findAll();
	  }
	  
	  @RequestMapping(value = "/recordLabel/{labelName}", method = RequestMethod.GET)
	  public Collection<Band> getRecordLabelBands(@PathVariable(name = "labelName") String labelName) {
	    return recordLabelRepo.findByLabelName(labelName).getBands();
	  }
}
