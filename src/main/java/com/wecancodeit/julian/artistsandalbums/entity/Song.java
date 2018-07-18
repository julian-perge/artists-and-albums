package com.wecancodeit.julian.artistsandalbums.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class Song {
  @Id @GeneratedValue private Long id;
  private String length;
  private String rating;
  private String songName;
  @Lob private String lyrics;
  private String videoUrl;
  
  @ManyToOne private Album album;

  /**
   * @param length
   * @param rating
   * @param songName
   * @param lyrics
   * @param videoUrl
   */
  public Song(String length, String rating, String songName, String lyrics, String videoUrl) {
    this.length = length;
    this.rating = rating;
    this.songName = songName;
    this.lyrics = lyrics;
    this.videoUrl = videoUrl;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((length == null) ? 0 : length.hashCode());
    result = prime * result + ((lyrics == null) ? 0 : lyrics.hashCode());
    result = prime * result + ((rating == null) ? 0 : rating.hashCode());
    result = prime * result + ((songName == null) ? 0 : songName.hashCode());
    result = prime * result + ((videoUrl == null) ? 0 : videoUrl.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    Song other = (Song) obj;
    if (id == null) {
      if (other.id != null) return false;
    } else if (!id.equals(other.id)) return false;
    if (length == null) {
      if (other.length != null) return false;
    } else if (!length.equals(other.length)) return false;
    if (lyrics == null) {
      if (other.lyrics != null) return false;
    } else if (!lyrics.equals(other.lyrics)) return false;
    if (rating == null) {
      if (other.rating != null) return false;
    } else if (!rating.equals(other.rating)) return false;
    if (songName == null) {
      if (other.songName != null) return false;
    } else if (!songName.equals(other.songName)) return false;
    if (videoUrl == null) {
      if (other.videoUrl != null) return false;
    } else if (!videoUrl.equals(other.videoUrl)) return false;
    return true;
  }

  public Long getId() {
    return id;
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
