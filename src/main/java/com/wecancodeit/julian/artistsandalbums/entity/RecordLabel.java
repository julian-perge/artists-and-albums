package com.wecancodeit.julian.artistsandalbums.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class RecordLabel {
  @Id @GeneratedValue private Long id;

  private String labelName;

  @OneToMany(mappedBy = "recordLabel") @JsonIgnore private Collection<Band> bands;

  public RecordLabel(String labelName) {
    this.labelName = labelName;
  }

  public Long getId() {
    return id;
  }

  public String getLabelName() {
    return labelName;
  }

  public Collection<Band> getBands() {
    return bands;
  }

  protected RecordLabel() {}
}
