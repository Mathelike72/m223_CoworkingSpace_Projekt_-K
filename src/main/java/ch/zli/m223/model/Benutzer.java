package ch.zli.m223.model;
import javax.persistence.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import java.util.List;

//Bestimmung der unterschiedlichen Entitys
@Entity
public class Benutzer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(readOnly = true)
  private Long id;
  
  @Column(nullable = false)
  private String firstName;
  
  @Column(nullable = false)
  private String secoundName;

  @Column(nullable = false)
  private String eMail;

  @Column(nullable = false)
  private String password;

  @OneToMany
  private List<Buchungen> buchungen;
 
  @ManyToMany
  @JoinTable(
    name = "benutzer_abos",
    joinColumns = @JoinColumn(name="benutzer_id"),
    inverseJoinColumns = @JoinColumn(name="abos_id")
  )
  private List<Abos> abos;

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
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  //Getter und Setter für Buchungen
  public List<Buchungen> getBuchungen() {
    return buchungen;
  }

  public void setBuchungen(List<Buchungen> buchungen) {
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
}