package com.wecancodeit.julian.artistsandalbums.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ARTISTS")
public class Artist {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToMany(mappedBy = "artists")
  @JsonIgnore
  private Collection<Album> albums;

  private String artistName;

  @ManyToOne @JsonIgnore private Band band;

  /**
   * @param artistName
   * @param albums
   */
  public Artist(String artistName, Band band) {
    this.artistName = artistName;
    this.band = band;
  }

  public Long getId() {
    return id;
  }

  public Collection<Album> getAlbums() {
    return albums;
  }

  public String getArtistName() {
    return artistName;
  }

  public Band getBand() {
    return band;
  }

  protected Artist() {}
}
