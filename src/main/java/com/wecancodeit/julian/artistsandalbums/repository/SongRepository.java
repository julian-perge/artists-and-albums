package com.wecancodeit.julian.artistsandalbums.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wecancodeit.julian.artistsandalbums.entity.Song;

@Repository
public interface SongRepository extends CrudRepository<Song, Long>{
	Song findBySongName(String songName);
}
