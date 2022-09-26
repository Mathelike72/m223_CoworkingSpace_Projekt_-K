package ch.zli.m223.model;
import java.util.Set;
import javax.persistence.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Entity
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(readOnly = true)
    private Long id;
    
    @Column(nullable = false)
    private String title;
    
    @ManyToMany(mappedBy = "tags")
    private Set<Entry> entries;
    
    public Long getId(){
        return id;
    }
    
    public void setId(Long id){
        this.id = id;
    }
    
    public String getTitle(){
        return title;
    }

    public Set<Entry> getEntries() {
        return entries;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setEntries(Set<Entry> entries) {
        this.entries = entries;
    }
}