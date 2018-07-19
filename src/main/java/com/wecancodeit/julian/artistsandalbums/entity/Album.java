package com.wecancodeit.julian.artistsandalbums.entity;

import java.util.Arrays;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ALBUMS")
public class Album {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;

  private String albumName;
  private String coverImage;
  private String genre;
  private String releaseDate;

  @OneToMany(cascade = CascadeType.ALL,
          fetch = FetchType.LAZY,
          mappedBy = "album")
  private Collection<Song> songs;

  @ManyToOne(fetch = FetchType.LAZY, optional=true)
  @JoinColumn(name = "ARTIST_ID", nullable = false)
  @JsonIgnore
  private Artist artist;

  /**
   * @param albumName
   * @param releaseDate
   * @param genre
   * @param coverImage
   * @param songs
   */
  public Album(
      String albumName, String releaseDate, String genre, String coverImage, Song... songs) {
    this.albumName = albumName;
    this.releaseDate = releaseDate;
    this.genre = genre;
    this.coverImage = coverImage;
    this.songs = Arrays.asList(songs);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((albumName == null) ? 0 : albumName.hashCode());
    result = prime * result + ((artist == null) ? 0 : artist.hashCode());
    result = prime * result + ((coverImage == null) ? 0 : coverImage.hashCode());
    result = prime * result + ((genre == null) ? 0 : genre.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((releaseDate == null) ? 0 : releaseDate.hashCode());
    result = prime * result + ((songs == null) ? 0 : songs.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    Album other = (Album) obj;
    if (albumName == null) {
      if (other.albumName != null) return false;
    } else if (!albumName.equals(other.albumName)) return false;
    if (artist == null) {
      if (other.artist != null) return false;
    } else if (!artist.equals(other.artist)) return false;
    if (coverImage == null) {
      if (other.coverImage != null) return false;
    } else if (!coverImage.equals(other.coverImage)) return false;
    if (genre == null) {
      if (other.genre != null) return false;
    } else if (!genre.equals(other.genre)) return false;
    if (id == null) {
      if (other.id != null) return false;
    } else if (!id.equals(other.id)) return false;
    if (releaseDate == null) {
      if (other.releaseDate != null) return false;
    } else if (!releaseDate.equals(other.releaseDate)) return false;
    if (songs == null) {
      if (other.songs != null) return false;
    } else if (!songs.equals(other.songs)) return false;
    return true;
  }

  public Artist getArtist() {
    return artist;
  }

  public Long getId() {
    return id;
  }

  public String getAlbumName() {
    return albumName;
  }

  public String getCoverImage() {
    return coverImage;
  }

  public String getGenre() {
    return genre;
  }

  public String getReleaseDate() {
    return releaseDate;
  }

  public Collection<Song> getSongs() {
    return songs;
  }

  protected Album() {}
}
