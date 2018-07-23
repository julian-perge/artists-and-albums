package com.wecancodeit.julian.artistsandalbums.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Band {
  @Id @GeneratedValue private Long id;

  private String bandName;

  @ManyToOne private RecordLabel recordLabel;

  @OneToMany(mappedBy = "band")
//  @JsonIgnore
  private Collection<Artist> artists;

  @OneToMany(mappedBy = "band")
  @JsonIgnore
  private Collection<Album> albums;

  private String merchandise;

  public Band(String bandName, String merchandise, RecordLabel recordLabel) {
    this.bandName = bandName;
    this.merchandise = merchandise;
    this.recordLabel = recordLabel;
  }

  public Long getId() {
    return id;
  }

  public String getBandName() {
    return bandName;
  }

  public RecordLabel getRecordLabel() {
    return recordLabel;
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
