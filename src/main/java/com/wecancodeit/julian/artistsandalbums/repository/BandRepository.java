package com.wecancodeit.julian.artistsandalbums.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wecancodeit.julian.artistsandalbums.entity.Band;

@Repository
public interface BandRepository extends CrudRepository<Band, Long> {

  Band findByBandName(String bandName);}
