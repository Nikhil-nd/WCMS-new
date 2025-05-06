package org.nwf.wcms.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Observation {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    @Column(name="type")
    private String type;
    @Column(nullable=false)
    private String location;
    @Column(name="time", nullable=false)
    private LocalDateTime time;
    
    @ManyToOne
    private Species species;
    @ManyToOne
    private Ranger ranger;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public LocalDateTime getTime() {
        return time;
    }
    public void setTime(LocalDateTime time) {
        this.time = time;
    }
    public Species getSpecies() {
        return species;
    }
    public void setSpecies(Species species) {
        this.species = species;
    }
    public Ranger getRanger() {
        return ranger;
    }
    public void setRanger(Ranger ranger) {
        this.ranger = ranger;
    }
    public Observation(int id, String type, String location, LocalDateTime time, Species species, Ranger ranger) {
        this.id = id;
        this.type = type;
        this.location = location;
        this.time = time;
        this.species = species;
        this.ranger = ranger;
    }
    public Observation() {
    }
    
}
