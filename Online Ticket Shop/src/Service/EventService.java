package Service;

import Model.Event;
import Repository.EventRepository;
import Audit.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class EventService {


    private EventRepository eventRepository = null;
    private static EventService instance;
    private static AuditService auditService = AuditService.getInstance();


    private EventService(Connection connection) {

        eventRepository = new EventRepository(connection);
    }

    public static EventService getInstance(Connection connection) {

        if(instance == null) {
            instance = new EventService(connection);
        }
        return instance;
    }

    public Event addEvent(Event e) throws SQLException {
        auditService.writeInAudit( "Add event");
        eventRepository.addEvent(e);
        return e;
    }

    public void removeEventByIdEvent(int id) throws SQLException {
        auditService.writeInAudit("Remove event by id");
        eventRepository.removeEventById(id);
    }

    //find
    public ArrayList<Event> findEventByName(String name) throws SQLException {
        auditService.writeInAudit("Get event by name");
        return eventRepository.findEventByName(name);
    }

    //update
    public void updateNameEvent(int id,  String newName) throws SQLException {
        auditService.writeInAudit("Update name of an event given by id");
        eventRepository.updateNameEvent(id, newName);


    }

    public void updatePriceEventById(int id,  double newPrice) throws SQLException {
        auditService.writeInAudit("Update price of an event given by id");
        eventRepository.updatePriceEventById(id, newPrice);

    }

    public void updateDurationEventById(int id,  int newDuration) throws SQLException {
        auditService.writeInAudit("Update duration of an event given by id");
        eventRepository.updateDurationEventById(id, newDuration);

    }

    public ArrayList<Event> getEvents() throws SQLException {
        auditService.writeInAudit("Get all the events");
        return eventRepository.getEvents();
    }

    public Event getEventById(int id) throws SQLException {
        auditService.writeInAudit("Get event by id");
        return eventRepository.getEventById(id);
    }

}
