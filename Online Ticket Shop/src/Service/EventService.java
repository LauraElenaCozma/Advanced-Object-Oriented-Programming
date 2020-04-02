package Service;

import Model.Event;
import Repository.EventRepository;

import java.util.ArrayList;

public class EventService {

    private EventRepository eventRepository = new EventRepository();
    private static EventService instance = new EventService();
    private EventService() {
    }

    public static EventService getInstance() {

        return instance;
    }

    public Event addEvent(Event e) {
        eventRepository.addEvent(e);
        return e;
    }

    public void removeEvent(Event e, TicketDetailsService ticketDetailsService, SoldTicketService soldTicketService) {
        eventRepository.removeEvent(e);
        ticketDetailsService.removeTicketByEvent(e, soldTicketService); //remove sell tickets with this event also
    }

    public void removeEventByIdEvent(int id, TicketDetailsService ticketDetailsService, SoldTicketService soldTicketService) {
        eventRepository.removeEventById(id);
        ticketDetailsService.removeTicketByEventId(id, soldTicketService); //remove sell tickets with this event id also

    }

    //find
    public ArrayList<Event> findEventByName(String name) {

        return eventRepository.findEventByName(name);
    }

    //update

    public void updateNameEvent(String name, String newName) {

        eventRepository.updateNameEvent(name, newName);
    }

    public void updateNameEvent(int id,  String newName) {

        eventRepository.updateNameEvent(id, newName);
    }

    public void updatePriceEventById(int id,  double newPrice) {

        eventRepository.updatePriceEventById(id, newPrice);
    }

    public void updateDurationEventById(int id,  int newDuration) {
        eventRepository.updateDurationEventById(id, newDuration);
    }

    public ArrayList<Event> getEvents() {
        return eventRepository.getEvents();
    }

    public Event getEventById(int id) {

        return eventRepository.getEventById(id);
    }

}
