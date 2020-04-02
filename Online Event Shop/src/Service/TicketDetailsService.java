package Service;

import Model.*;
import Repository.EventRepository;
import Repository.SoldTicketRepository;
import Repository.TicketDetailsRepository;

import java.util.ArrayList;

public class TicketDetailsService {
    private TicketDetailsRepository ticketDetailsRepository = new TicketDetailsRepository();
    private static TicketDetailsService instance = new TicketDetailsService();
    private TicketDetailsService() {
    }

    public static TicketDetailsService getInstance() {
        return instance;
    }

    //add
    public TicketDetails addTicket(TicketDetails t) {
        ticketDetailsRepository.addTicket(t);
        return t;
    }

    //remove
    public void removeTicketById(int id, SoldTicketService soldTicketService) {
        //remove a ticket detail by ticket detail id, and also sold tickets having this ticket detail
        ticketDetailsRepository.removeTicketById(id);
        soldTicketService.removeTicketByTicketDetailsId(id);
    }
    public void removeTicketByEvent(Event e, SoldTicketService soldTicketService) {
        //remove ticket details having the event e, and also sold tickets having this ticket detail
        ticketDetailsRepository.removeTicketByEvent(e);
        soldTicketService.removeTicketByEvent(e);
    }

    public void removeTicketByEventId(int id, SoldTicketService soldTicketService) {
        //remove ticket details having the event id = id, and also sold tickets having this ticket detail
        ticketDetailsRepository.removeTicketDetailsByEventId(id);
        soldTicketService.removeTicketByEventId(id);
    }

    public void removeTicketByLocationId(int id, SoldTicketService soldTicketService) {
        //remove ticket details having the location id = id, and also sold tickets having this ticket detail
        ticketDetailsRepository.removeTicketByLocationId(id);
        soldTicketService.removeTicketByLocationId(id);
    }


    //find
    public ArrayList<TicketDetails> findTicketByEvent(Event e) {
        return ticketDetailsRepository.findTicketByEvent(e);
    }

    public ArrayList<TicketDetails> findTicketByLocation(Location loc) {
        return ticketDetailsRepository.findTicketByLocation(loc);
    }

    public ArrayList<TicketDetails> findTicketByDate(Date d) {
        return ticketDetailsRepository.findTicketByDate(d);
    }

    //update
    public void updateTicketDetailsEventOfEvent(Event e, Event newE) {
        ticketDetailsRepository.updateTicketDetailsEvent(e, newE);
    }

    public void updateTicketDetailsEventOfEvent(int id, Event newE) {
        ticketDetailsRepository.updateTicketDetailsEvent(id, newE);
    }

    public void updateTicketDetailsLocationOfEvent(Location location, Location newLocation) {
        ticketDetailsRepository.updateTicketDetailsLocation(location, newLocation);
    }

    public void updateTicketDetailsLocationOfEvent(int id, Location newLocation) {
        ticketDetailsRepository.updateTicketDetailsLocation(id, newLocation);
    }

    public void updateTicketDetailsDateOfEvent(Date date, Date newDate) {
        ticketDetailsRepository.updateTicketDetailsDate(date, newDate);
    }

    public void updateTicketDetailsDateOfEvent(int id, Date newDate) {
        ticketDetailsRepository.updateTicketDetailsDate(id, newDate);
    }

    public void updateTicketDetailsHourOfEvent(int id, String hour) {
        ticketDetailsRepository.updateTicketDetailsHour(id, hour);
    }

    //get
    public ArrayList<TicketDetails> getTicketDetails() {
        return ticketDetailsRepository.getTickets();
    }

    public TicketDetails getTicketDetailsById(int id) {
        return ticketDetailsRepository.getTicketById(id);
    }
}
