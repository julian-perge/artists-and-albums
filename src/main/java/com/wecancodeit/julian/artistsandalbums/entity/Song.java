package com.wecancodeit.julian.artistsandalbums.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Song {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String length;
  private String rating;
  private String songName;
  @Lob private String lyrics;
  private String videoUrl;

  @OneToMany(mappedBy = "song")
  @Column(name = "SONG_COMMENTS")
  private Collection<Comment> comments;

  @ManyToOne
  @JsonIgnore
  private Album album;

  /**
   * @param length
   * @param rating
   * @param songName
   * @param lyrics
   * @param videoUrl
   */
  public Song(
      String length, String rating, String songName, String lyrics, String videoUrl, Album album) {
    this.length = length;
    this.rating = rating;
    this.songName = songName;
    this.lyrics = lyrics;
    this.videoUrl = videoUrl;
    this.album = album;
  }

  public Long getId() {
    return id;
  }

  public Collection<Comment> getComments() {
    return comments;
  }

  public Album getAlbum() {
    return album;
  }

  public String getLength() {
    return length;
  }

  public String getRating() {
    return rating;
  }

  public String getSongName() {
    return songName;
  }

  public String getLyrics() {
    return lyrics;
  }

  public String getVideoUrl() {
    return videoUrl;
  }

  protected Song() {}
}
