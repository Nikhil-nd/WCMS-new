package org.nwf.wcms.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Species {
 @Id
 @GeneratedValue(strategy=GenerationType.AUTO)
 private int id;
 @Column (name="speciesName",nullable=false)
 private String speciesName;
 @Column(name="endangered",nullable=false)
 private boolean endangered;
 @OneToMany(mappedBy="species")
 @JsonBackReference
 private List<Observation> observation;
 public int getId() {
    return id;
 }
 public void setId(int id) {
    this.id = id;
 }
 public String getSpeciesName() {
    return speciesName;
 }
 public Species() {
}
 public Species(int id, String speciesName, boolean endangered, List<Observation> observation) {
    this.id = id;
    this.speciesName = speciesName;
    this.endangered = endangered;
    this.observation = observation;
}
 public void setSpeciesName(String speciesName) {
    this.speciesName = speciesName;
 }
 public boolean isEndangered() {
    return endangered;
 }
 public void setEndangered(boolean endangered) {
    this.endangered = endangered;
 }
 public List<Observation> getObservation() {
    return observation;
 }
 public void setObservation(List<Observation> observation) {
    this.observation = observation;
 }

}
