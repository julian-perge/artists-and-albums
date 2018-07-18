package com.wecancodeit.julian.artistsandalbums.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wecancodeit.julian.artistsandalbums.entity.Artist;

@Repository
public interface ArtistRepository extends CrudRepository<Artist, Long> {
  Artist findByArtistName(String artistName);
}
