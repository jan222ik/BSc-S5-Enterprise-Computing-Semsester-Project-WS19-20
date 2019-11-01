package at.fhv.itb17.s5.teamb.persistence;


import at.fhv.itb17.s5.teamb.persistence.entities.Address;
import at.fhv.itb17.s5.teamb.persistence.entities.Client;
import at.fhv.itb17.s5.teamb.persistence.entities.ClientRoles;
import at.fhv.itb17.s5.teamb.persistence.entities.Event;
import at.fhv.itb17.s5.teamb.persistence.entities.EventCategory;
import at.fhv.itb17.s5.teamb.persistence.entities.EventOccurrence;
import at.fhv.itb17.s5.teamb.persistence.entities.Organizer;
import at.fhv.itb17.s5.teamb.persistence.entities.Ticket;
import at.fhv.itb17.s5.teamb.persistence.entities.TicketStates;
import at.fhv.itb17.s5.teamb.persistence.repository.EntityRepository;
import at.fhv.itb17.s5.teamb.persistence.util.WhereClause;
import at.fhv.itb17.s5.teamb.persistence.util.WhereClauseBuilder;
import at.fhv.itb17.s5.teamb.persistence.util.WhereCondition;
import org.hibernate.query.criteria.internal.CriteriaBuilderImpl;
import org.hibernate.resource.jdbc.LogicalConnection;

import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("squid:S106")
public class DatabaseMain {

    private static EntityRepository repository = new EntityRepository();

    private static void test() {
        Address address = new Address("AT", "685ftui0", "Do", "ABCStr.", "4711");
        Client client = new Client("test_client", "Hugo Hugo", "pw", "salt", ClientRoles.EXTERNAL, address);
        EventCategory g21 = new EventCategory("G21", 9001, 500, 69);
        EventCategory g16 = new EventCategory("G16", 12312312, 19, 3);
        EventOccurrence occurrence0 = new EventOccurrence(LocalDate.now(), LocalTime.now(), new LinkedList<>(Arrays.asList(g21, g16)));
        Organizer organizer = new Organizer("Std. Do", "e@mail.com", address);
        Event event = new Event("Weihnachtsmarkt", "Weihnachtsmarkt vom 22.11 bis 23.11.2019", "Death Metal", "45+", new LinkedList<>(Arrays.asList(occurrence0)), organizer, address);
        Ticket ticket = new Ticket(client, TicketStates.PAID, event, occurrence0, g21, null, null);

//        repository.save(address);
//        repository.saveOrUpdate(client);
//        repository.saveOrUpdate(g21);
//        repository.saveOrUpdate(g16);
//        repository.saveOrUpdate(occurrence0);
//        repository.saveOrUpdate(organizer);
//        repository.saveOrUpdate(event);
//        repository.saveOrUpdate(ticket);

//        repository.save(new Address("austria", "6850", "dornbirn", "knie", "60"));
//        repository.save(new Address("austria", "6850", "dornbirn", "rohrbach", "121"));
//        repository.save(new Address("austria", "6840", "bregenz", "langestrasse", "12"));
//        repository.save(new Address("austria", "6850", "hörbranz", "nemetzstraße", "420"));
//        repository.save(new Address("deutschland", "61250", "landsberg", "jannikstreet", "0815"));


        WhereClauseBuilder builder = new WhereClauseBuilder();
        builder.equals("country", "austria").equals("city", "dornbirn").equals("house", "60");
        List<Address> all = repository.getAll(Address.class, builder.build());
        all.forEach(System.out::println);
    }

    public static void main(String... test) {
        test();
    }
}
