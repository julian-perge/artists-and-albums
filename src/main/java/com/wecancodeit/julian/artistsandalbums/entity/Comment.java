package com.wecancodeit.julian.artistsandalbums.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Comment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String description;

  @ManyToOne @JsonIgnore private Album album;

  @ManyToOne @JsonIgnore private Song song;

  /**
   * @param description
   * @param album
   */
  public Comment(String description, Album album) {
    this.description = description;
    this.album = album;
  }

  /**
   * @param description
   * @param song
   */
  public Comment(String description, Song song) {
    this.description = description;
    this.song = song;
  }

  public Album getAlbum() {
    return album;
  }

  public Song getSong() {
    return song;
  }

  public Long getId() {
    return id;
  }

  public String getDescription() {
    return description;
  }

  protected Comment() {}
}
