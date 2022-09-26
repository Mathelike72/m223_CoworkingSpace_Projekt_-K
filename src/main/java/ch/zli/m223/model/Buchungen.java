package ch.zli.m223.model;
import javax.persistence.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

//Bestimmung der unterschiedlichen Entitys
@Entity
public class Buchungen implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(readOnly = true)
    private Long id;

    @Column(nullable = true)
    private boolean status;
  
    @Column(nullable = false)
    private LocalDateTime startDate;
  
    @Column(nullable = false)
    private LocalDateTime endDate;

    @OneToOne(mappedBy="Buchungen")
    @JsonIgnore
    private Set<Benutzer> benutzer;

    //Getter und Setter der einzlenen Entitys Buchungen

    //Getter und Setter für Buchungen_ID

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //Getter und Setter für Status
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    //Getter und Setter für StartDate
    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    //Getter und Setter für EndDate
    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    //Getter und Setter für Benutzer_ID
    public Set<Benutzer> getBenutzer() {
        return benutzer;
    }

    public void setBenutzer(Set<Benutzer> benutzer) {
        this.benutzer = benutzer;
    }
}
