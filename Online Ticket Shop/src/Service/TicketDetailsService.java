package Service;

import Model.Date;
import Model.Event;
import Model.Location;
import Model.TicketDetails;
import Repository.TicketDetailsRepository;
import Service.Audit.AuditService;
import Service.Audit.EventFileService;
import Service.Audit.TicketDetailsFileService;

import java.util.ArrayList;

public class TicketDetailsService {
    private TicketDetailsRepository ticketDetailsRepository = new TicketDetailsRepository();
    private static TicketDetailsService instance = new TicketDetailsService();
    private static AuditService auditService = AuditService.getInstance();
    private TicketDetailsFileService ticketDetailsFileService = TicketDetailsFileService.getInstance(ticketDetailsRepository);

    private TicketDetailsService() {
    }

    public static TicketDetailsService getInstance() {
        return instance;
    }

    //add
    public TicketDetails addTicket(TicketDetails t) {
        auditService.writeInAudit("Add ticket details");
        Event e = EventService.getInstance().getEventById(t.getIdEvent()); //search if the event exist
        if(e == null)
            throw new IllegalArgumentException("No event having this id!");

        Location loc = LocationService.getInstance().getLocationById(t.getIdLocation());
        if(loc == null)
            throw new IllegalArgumentException("No location having this id!");

        ticketDetailsFileService.appendInFile(t);
        ticketDetailsRepository.addTicket(t);
        return t;
    }

    //remove
    public void removeTicketById(int id) {
        auditService.writeInAudit("Remove ticket details by id");
        SoldTicketService soldTicketService = SoldTicketService.getInstance();
        //remove a ticket detail by ticket detail id, and also sold tickets having this ticket detail
        ticketDetailsRepository.removeTicketById(id);
        soldTicketService.removeTicketByTicketDetailsId(id);
        ticketDetailsFileService.updateFile();
    }


    public void removeTicketByEventId(int id) {
        auditService.writeInAudit("Remove ticket details by id event");
        SoldTicketService soldTicketService = SoldTicketService.getInstance();
        //remove ticket details having the event id = id, and also sold tickets having this ticket detail
        ArrayList<TicketDetails> array = ticketDetailsRepository.findTicketByIdEvent(id);
        for(TicketDetails t : array) {
            soldTicketService.removeTicketByTicketDetailsId(t.getIdTicket());
        }
        ticketDetailsRepository.removeTicketDetailsByEventId(id);
        ticketDetailsFileService.updateFile();
    }

    public void removeTicketByLocationId(int id) {
        auditService.writeInAudit("Remove ticket details by location id");
        SoldTicketService soldTicketService = SoldTicketService.getInstance();
        //remove ticket details having the location id = id, and also sold tickets having this ticket detail
        ArrayList<TicketDetails> array = ticketDetailsRepository.findTicketByIdLocation(id);

        for(TicketDetails ticket : array) {
            soldTicketService.removeTicketByTicketDetailsId(ticket.getIdTicket());
        }
        ticketDetailsRepository.removeTicketByLocationId(id);
        ticketDetailsFileService.updateFile();
    }


    //find

    public ArrayList<TicketDetails> findTicketByDate(Date d) {
        auditService.writeInAudit("Find ticket details by date");
        return ticketDetailsRepository.findTicketByDate(d);
    }

    //update
    public void updateTicketDetailsEventOfEvent(int id, int newId) {
        auditService.writeInAudit("Update ticket details event");
        TicketDetails t = ticketDetailsRepository.getTicketById(id);
        if(t == null)
            throw new IllegalArgumentException("No ticket having this id!");
        ticketDetailsRepository.updateTicketDetailsEvent(id, newId);
        ticketDetailsFileService.updateFile();
    }

    public void updateTicketDetailsLocationOfEvent(int id, int newIdLocation) {
        auditService.writeInAudit("Update ticket details location");
        TicketDetails t = ticketDetailsRepository.getTicketById(id);
        if(t == null)
            throw new IllegalArgumentException("No ticket having this id!");
        ticketDetailsRepository.updateTicketDetailsLocation(id, newIdLocation);
        ticketDetailsFileService.updateFile();
    }

    public void updateTicketDetailsDateOfEvent(Date date, Date newDate) {
        auditService.writeInAudit("Update ticket details date");
        ArrayList<TicketDetails> t = ticketDetailsRepository.findTicketByDate(date);
        if(t.size() == 0)
            throw new IllegalArgumentException("No tickets on this date!");
        ticketDetailsRepository.updateTicketDetailsDate(date, newDate);
        ticketDetailsFileService.updateFile();
    }

    public void updateTicketDetailsDateOfEvent(int id, Date newDate) {
        auditService.writeInAudit("Update ticket details date");
        TicketDetails t = ticketDetailsRepository.getTicketById(id);
        if(t == null)
            throw new IllegalArgumentException("No ticket having this id!");
        ticketDetailsRepository.updateTicketDetailsDate(id, newDate);
        ticketDetailsFileService.updateFile();
    }

    public void updateTicketDetailsHourOfEvent(int id, String hour) {
        auditService.writeInAudit("Update ticket details hour");
        TicketDetails t = ticketDetailsRepository.getTicketById(id);
        if(t == null)
            throw new IllegalArgumentException("No ticket having this id!");
        ticketDetailsRepository.updateTicketDetailsHour(id, hour);
        ticketDetailsFileService.updateFile();
    }

    //get
    public ArrayList<TicketDetails> getTicketDetails() {
        auditService.writeInAudit("Get ticket details");
        return ticketDetailsRepository.getTickets();
    }

    public TicketDetails getTicketDetailsById(int id) {
        auditService.writeInAudit("Get ticket details by id");
        return ticketDetailsRepository.getTicketById(id);
    }

    public ArrayList<TicketDetails> getTicketDetailsByIdEvent(int id) {
        auditService.writeInAudit("Get ticket details by id of event");
        return ticketDetailsRepository.findTicketByIdEvent(id);
    }

    public ArrayList<TicketDetails> getTicketDetailsByIdLocation(int id) {
        auditService.writeInAudit("Get ticket details by id of location");
        return ticketDetailsRepository.getTicketDetailsByIdLocation(id);
    }

    public TicketDetailsRepository getTicketDetailsRepository() {
        auditService.writeInAudit("Get ticketDetailsRepository");
        return ticketDetailsRepository;
    }
}
