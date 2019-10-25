package at.fhv.itb17.s5.teamb.persistence.test.entities;

import javax.persistence.*;

@Entity
@Table(name = "hobby")
public class Hobby {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "hobby")
    private Integer id;

    @Column(name = "description")
    private String description;

    public Hobby(){

    }

    public Hobby(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public Hobby(String description) {
        this.description =description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
