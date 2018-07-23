package com.wecancodeit.julian.artistsandalbums.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wecancodeit.julian.artistsandalbums.entity.Album;
import com.wecancodeit.julian.artistsandalbums.entity.Artist;

@Repository
public interface AlbumRepository extends CrudRepository<Album, Long> {

  Album findByAlbumName(String albumName);
}
