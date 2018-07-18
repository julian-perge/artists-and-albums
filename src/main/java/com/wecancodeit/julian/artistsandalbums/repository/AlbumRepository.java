package com.wecancodeit.julian.artistsandalbums.repository;

import org.springframework.data.repository.CrudRepository;

import com.wecancodeit.julian.artistsandalbums.entity.Album;

public interface AlbumRepository extends CrudRepository<Album, Long> {}
