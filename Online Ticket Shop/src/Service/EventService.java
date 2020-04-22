package Service;

import Model.Event;
import Repository.EventRepository;
import Service.Audit.AuditService;
import Service.Audit.EventFileService;

import java.util.ArrayList;

public class EventService {

    private EventRepository eventRepository = new EventRepository();
    private static EventService instance = new EventService();
    private EventFileService eventFileService = EventFileService.getInstance(eventRepository);
    private static AuditService auditService = AuditService.getInstance();
    private EventService() {
    }

    public static EventService getInstance() {
        return instance;
    }

    public Event addEvent(Event e) {
        auditService.writeInAudit("Add event");
        eventFileService.appendInFile(e);
        eventRepository.addEvent(e);
        return e;
    }

    public void removeEventByIdEvent(int id) {
        TicketDetailsService ticketDetailsService =  TicketDetailsService.getInstance();
        SoldTicketService soldTicketService = SoldTicketService.getInstance();

        auditService.writeInAudit("Remove event by id");
        eventRepository.removeEventById(id);
        ticketDetailsService.removeTicketByEventId(id); //remove sell tickets with this event id also
        eventFileService.updateFile();
    }

    //find
    public ArrayList<Event> findEventByName(String name) {
        auditService.writeInAudit("Get event by name");
        return eventRepository.findEventByName(name);
    }

    //update
    public void updateNameEvent(int id,  String newName) {
        auditService.writeInAudit("Update name of an event given by id");
        Event e = eventRepository.getEventById(id);
        if(e == null)
            throw new IllegalArgumentException("No event having this id!");
        eventRepository.updateNameEvent(id, newName);
        eventFileService.updateFile();

    }

    public void updatePriceEventById(int id,  double newPrice) {
        auditService.writeInAudit("Update price of an event given by id");
        Event e = eventRepository.getEventById(id);
        if(e == null)
            throw new IllegalArgumentException("No event having this id!");
        eventRepository.updatePriceEventById(id, newPrice);
        eventFileService.updateFile();

    }

    public void updateDurationEventById(int id,  int newDuration) {
        auditService.writeInAudit("Update duration of an event given by id");
        Event e = eventRepository.getEventById(id);
        if(e == null)
            throw new IllegalArgumentException("No event having this id!");
        eventRepository.updateDurationEventById(id, newDuration);
        eventFileService.updateFile();
    }

    public ArrayList<Event> getEvents() {
        auditService.writeInAudit("Get all the events");
        return eventRepository.getEvents();
    }

    public Event getEventById(int id) {
        auditService.writeInAudit("Get event by id");
        return eventRepository.getEventById(id);
    }

    public EventRepository getEventRepository() {
        auditService.writeInAudit("Get eventRepository");
        return eventRepository;
    }
}
