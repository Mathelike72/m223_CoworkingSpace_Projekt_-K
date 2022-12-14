package ch.zli.m223.model;

import javax.persistence.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import java.io.Serializable;
import java.util.Set;

//Bestimmung der unterschiedlichen Entitys
@Entity
public class Abos implements Serializable{
    @Id
    @Column(name = "abos_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(readOnly = true)
    private Long id;

    @Column(nullable = true)
    private String titel;
  
    @Column(nullable = true)
    private boolean status;

    
    @ManyToMany(mappedBy="abos")
    private Set<Benutzer> benutzer;

    //Getter und Setter der einzlenen Entitys Buchungen

    //Getter und Setter für Abos_ID
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //Getter und Setter für titel
    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    //Getter und Setter für status
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    //Getter und Setter für Benutzer_ID
    public Set<Benutzer> getBenutzer() {
        return benutzer;
    }

    public void setBenutzer(Set<Benutzer> benutzer) {
        this.benutzer = benutzer;
    }
}
