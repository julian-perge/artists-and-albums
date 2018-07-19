package com.wecancodeit.julian.artistsandalbums.entity;

import java.util.Arrays;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ARTISTS")
public class Artist {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;

  @OneToMany(cascade = CascadeType.ALL,
          fetch = FetchType.LAZY,
          mappedBy = "artist")
  private Collection<Album> albums;

  private String artistName;
  private String recordLabel;

  /**
   * @param artistName
   * @param albums
   * @param recordLabel
   */
  public Artist(String artistName, String recordLabel, Album... albums) {
    this.artistName = artistName;
    this.recordLabel = recordLabel;
    this.albums = Arrays.asList(albums);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((albums == null) ? 0 : albums.hashCode());
    result = prime * result + ((artistName == null) ? 0 : artistName.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((recordLabel == null) ? 0 : recordLabel.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    Artist other = (Artist) obj;
    if (albums == null) {
      if (other.albums != null) return false;
    } else if (!albums.equals(other.albums)) return false;
    if (artistName == null) {
      if (other.artistName != null) return false;
    } else if (!artistName.equals(other.artistName)) return false;
    if (id == null) {
      if (other.id != null) return false;
    } else if (!id.equals(other.id)) return false;
    if (recordLabel == null) {
      if (other.recordLabel != null) return false;
    } else if (!recordLabel.equals(other.recordLabel)) return false;
    return true;
  }

  public Long getId() {
    return id;
  }

  public String getArtistName() {
    return artistName;
  }

  public Collection<Album> getAlbums() {
    return albums;
  }

  public String getRecordLabel() {
    return recordLabel;
  }

  protected Artist() {}
}
