package com.wecancodeit.julian.artistsandalbums.entity;

import java.util.Arrays;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ALBUMS")
public class Album {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String albumName;

  @ManyToMany private Collection<Artist> artists;

  @ManyToOne private Band band;

  @OneToMany(mappedBy = "album")
  @Column(name = "ALBUM_COMMENTS")
  private Collection<Comment> comments;

  private String coverImage;
  private String genre;
  @ManyToOne private RecordLabel recordLabel;
  private String releaseDate;

  @OneToMany(mappedBy = "album")
  @JsonIgnore
  private Collection<Song> songs;

  /**
   * @param albumName
   * @param coverImage
   * @param genre
   * @param releaseDate
   * @param songs
   */
  public Album(
      String albumName,
      Band band,
      String coverImage,
      String genre,
      RecordLabel recordLabel,
      String releaseDate,
      Artist... artists) {
    this.albumName = albumName;
    this.artists = Arrays.asList(artists);
    this.band = band;
    this.coverImage = coverImage;
    this.genre = genre;
    this.recordLabel = recordLabel;
    this.releaseDate = releaseDate;
  }

  public Long getId() {
    return id;
  }

  public String getAlbumName() {
    return albumName;
  }

  public Collection<Artist> getArtists() {
    return artists;
  }

  public Band getBand() {
    return band;
  }

  public Collection<Comment> getComments() {
    return comments;
  }

  public String getCoverImage() {
    return coverImage;
  }

  public String getGenre() {
    return genre;
  }

  public RecordLabel getRecordLabel() {
    return recordLabel;
  }

  public String getReleaseDate() {
    return releaseDate;
  }

  public Collection<Song> getSongs() {
    return songs;
  }

  protected Album() {}
}
