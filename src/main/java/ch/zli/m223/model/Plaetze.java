package ch.zli.m223.model;

import javax.persistence.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import java.io.Serializable;
import java.util.Set;

//Bestimmung der unterschiedlichen Entitys
@Entity
public class Plaetze implements Serializable{
    @Id
    @Column(name = "plaetze_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(readOnly = true)
    private Long id;

    @Column(nullable = true)
    private int raumNr;
  
    @Column(nullable = true)
    private int platzNr;

    
    @ManyToMany(mappedBy="plaetze")
    private Set<Benutzer> benutzer;

    //Getter und Setter der einzlenen Entitys Buchungen

    //Getter und Setter für Plaetze_ID
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //Getter und Setter für RaumNr
    public int getRaumNr() {
        return raumNr;
    }

    public void setRaumNr(int raumNr) {
        this.raumNr = raumNr;
    }

    //Getter und Setter für platzNr
    public int getPlatzNr() {
        return platzNr;
    }

    public void setPlatzNr(int platzNr) {
        this.platzNr = platzNr;
    }

    //Getter und Setter für Benutzer_ID
    public Set<Benutzer> getBenutzer() {
        return benutzer;
    }

    public void setBenutzer(Set<Benutzer> benutzer) {
        this.benutzer = benutzer;
    }
}
