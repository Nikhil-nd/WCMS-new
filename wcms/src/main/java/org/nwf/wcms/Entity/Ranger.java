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
public class Ranger {
 @Id
 @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    public Ranger() {
}
    @Column(name="name",nullable=false)
    private String name;
    @Column(name="email" ,nullable=false)
    private String email;
    public Ranger(int id, String name, String email, List<Observation> observations) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.observations = observations;
    }
    @OneToMany(mappedBy="ranger")
 @JsonBackReference
    private List<Observation> observations;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public List<Observation> getObservations() {
        return observations;
    }
    public void setObservations(List<Observation> observations) {
        this.observations = observations;
    }

}
