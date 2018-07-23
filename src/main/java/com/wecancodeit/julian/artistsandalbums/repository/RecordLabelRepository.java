package com.wecancodeit.julian.artistsandalbums.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wecancodeit.julian.artistsandalbums.entity.RecordLabel;

@Repository
public interface RecordLabelRepository extends CrudRepository<RecordLabel, Long> {

  RecordLabel findByLabelName(String recordLabelName);}
