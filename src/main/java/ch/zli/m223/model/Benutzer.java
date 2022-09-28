package ch.zli.m223.model;

import javax.persistence.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;

import java.util.List;
import java.util.Set;

//Bestimmung der unterschiedlichen Entitys
@Entity
public class Benutzer implements Serializable{
  @Id
  @Column(name = "benutzer_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(readOnly = true)
  private Long id;
  
  @Column(nullable = false, length = 40)
  private String firstName;
  
  @Column(nullable = false, length = 40)
  private String secoundName;

  @Column(nullable = false, unique = true)
  private String eMail;

  @Column(nullable = false, unique = true)
  private String passwort;

  @Column(nullable = false)
  private boolean isAdmin;

  @JsonIgnore
  @OneToMany(mappedBy="benutzer")
  private Set<Buchungen> buchungen;

  @JsonIgnore
  @ManyToMany
  @JoinTable(
    name = "benutzer_abos",
    joinColumns = @JoinColumn(name="benutzer_id"),
    inverseJoinColumns = @JoinColumn(name="abos_id")
  )
  private List<Abos> abos;

  @JsonIgnore
  @ManyToMany
  @JoinTable(
    name = "benutzer_plaetze",
    joinColumns = @JoinColumn(name="benutzer_id"),
    inverseJoinColumns = @JoinColumn(name="plaetze_id")
  )
  private List<Plaetze> plaetze;
  
  //Getter und Setter der einzlenen Entity

  //Getter und Setter für Benutzer_ID
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  //Getter und Setter für firstName
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  //Getter und Setter für secoundName
  public String getSecoundName() {
    return secoundName;
  }

  public void setSecoundName(String secoundName) {
    this.secoundName = secoundName;
  }

  //Getter und Setter für eMail
  public String getEMail() {
    return eMail;
  }

  public void setEMail(String eMail) {
    this.eMail = eMail;
  }

  //Getter und Setter für password
  public String getPassword() {
    return passwort;
  }

  public void setPassword(String passwort) {
    this.passwort = passwort;
  }

  //Getter und Setter für Buchungen
  public Set<Buchungen> getBuchungen() {
    return buchungen;
  }

  public void setBuchungen(Set<Buchungen> buchungen) {
    this.buchungen = buchungen;
  }

  //Getter und Setter für Abos
  public List<Abos> getAbos() {
    return abos;
  }

  public void setAbos(List<Abos> abos) {
    this.abos = abos;
  }

  //Getter und Setter für Plaetze
  public List<Plaetze> getPlaetze() {
    return plaetze;
  }

  public void setPleatze(List<Plaetze> plaetze) {
    this.plaetze = plaetze;
  }

  //Getter und Setter für isAdmin
  public boolean isAdmin() {
    return isAdmin;
  }

  public void setAdmin(boolean isAdmin) {
    this.isAdmin = isAdmin;
  }
}