package com.wecancodeit.julian.artistsandalbums.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Band {
  @Id @GeneratedValue private Long id;

  private String bandName;

  @OneToMany(mappedBy = "band")
  private Collection<Artist> artists;

  @OneToMany(mappedBy = "band")
  private Collection<Album> albums;

  private String merchandise;

  public Band(String bandName, String merchandise) {
    this.bandName = bandName;
    this.merchandise = merchandise;
  }

  public Long getId() {
    return id;
  }

  public String getBandName() {
    return bandName;
  }

  public Collection<Artist> getArtists() {
    return artists;
  }

  public Collection<Album> getAlbums() {
    return albums;
  }

  public String getMerchandise() {
    return merchandise;
  }

  protected Band() {}
}
