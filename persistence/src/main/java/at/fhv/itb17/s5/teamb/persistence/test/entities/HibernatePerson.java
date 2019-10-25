package at.fhv.itb17.s5.teamb.persistence.test.entities;


import javax.persistence.*;

@Entity
@Table(name = "person")
public class HibernatePerson {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    //@Column(name = "hobby")
    @OneToOne
    private Hobby hobby;

    public HibernatePerson(String name, Hobby hobby) {
        this.name = name;
        this.hobby = hobby;
    }

    public Hobby getHobby() {
        return hobby;
    }

    public HibernatePerson() {
    }

    public HibernatePerson(int id, String name, Hobby hobby) {
        this.id = id;
        this.name = name;
        this.hobby = hobby;
    }

    public void setHobby(Hobby hobby) {
        this.hobby = hobby;
    }

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
}
