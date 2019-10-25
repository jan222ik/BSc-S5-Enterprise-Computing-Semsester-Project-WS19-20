package at.fhv.itb17.s5.teamb.persistence.test;


import at.fhv.itb17.s5.teamb.persistence.test.entities.HibernatePerson;
import at.fhv.itb17.s5.teamb.persistence.test.entities.Hobby;

import java.util.List;

@SuppressWarnings("squid:S106")
public class Main {

    private static EntityRepository repository = new EntityRepository();

    private static void testData() {
        Hobby hobby = new Hobby("kuchen");
        Hobby hobby1 = new Hobby( "schinken");
        Hobby hobby2 = new Hobby( "tauchen");
        Hobby hobby3 = new Hobby( "putenschinken");

        repository.saveOrUpdate(hobby);
        repository.saveOrUpdate(hobby1);
        repository.saveOrUpdate(hobby2);
        repository.saveOrUpdate(hobby3);

        repository.saveOrUpdate(new HibernatePerson("matthias", hobby));
        repository.saveOrUpdate(new HibernatePerson( "janik", hobby2));
        repository.saveOrUpdate(new HibernatePerson( "iris", hobby1));
        repository.saveOrUpdate(new HibernatePerson( "mathias", hobby1));
        repository.saveOrUpdate(new HibernatePerson( "alexandra", hobby3));

    }

    public static void main(String... test) {
        testData();

        List<HibernatePerson> persons = repository.getAll(HibernatePerson.class);
        for (HibernatePerson person : persons) {
            System.out.println(person.getName() + person.getHobby().getDescription());
        }
        System.out.println("getting done");


        HibernatePerson person = new HibernatePerson();
        person.setName("foo");
        Hobby hobby = new Hobby();
        hobby.setDescription("KUCHEN");
        person.setHobby(hobby);
        repository.saveOrUpdate(hobby);
        repository.saveOrUpdate(person);
        System.out.println("updating done");

        persons = repository.getAll(HibernatePerson.class);
        persons.forEach(s -> System.out.println(s.getName() + s.getHobby().getDescription()));
        System.out.println("getting done");
    }
}
