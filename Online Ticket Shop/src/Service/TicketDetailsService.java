package Service;

import Model.*;
import Repository.TicketDetailsRepository;
import Service.Audit.*;
import java.sql.Date;

import java.sql.SQLException;
import java.util.ArrayList;

public class TicketDetailsService {
    private TicketDetailsRepository ticketDetailsRepository = new TicketDetailsRepository();
    private static TicketDetailsService instance = new TicketDetailsService();
    private static AuditService auditService = AuditService.getInstance();

    private TicketDetailsService() {
    }

    public static TicketDetailsService getInstance() {
        return instance;
    }

    //add
    public TicketDetails addTicket(TicketDetails t) throws SQLException {
        auditService.writeInAudit(Thread.currentThread().getName() + ",Add ticket details");
        ticketDetailsRepository.addTicket(t);
        return t;
    }

    //remove
    public void removeTicketById(int id) throws SQLException {
        auditService.writeInAudit(Thread.currentThread().getName() + ",Remove ticket details by id");
        SoldTicketService soldTicketService = SoldTicketService.getInstance();
        //remove a ticket detail by ticket detail id, and also sold tickets having this ticket detail
        ticketDetailsRepository.removeTicketById(id);
        soldTicketService.removeTicketByTicketDetailsId(id);
    }


    public void removeTicketByEventId(int id) throws SQLException {
        auditService.writeInAudit(Thread.currentThread().getName() + ",Remove ticket details by id event");
        SoldTicketService soldTicketService = SoldTicketService.getInstance();
        //remove ticket details having the event id = id, and also sold tickets having this ticket detail
        ArrayList<TicketDetails> array = ticketDetailsRepository.findTicketByIdEvent(id);
        for(TicketDetails t : array) {
            soldTicketService.removeTicketByTicketDetailsId(t.getIdTicket());
        }
        ticketDetailsRepository.removeTicketDetailsByEventId(id);
    }

    public void removeTicketByLocationId(int id) throws SQLException {
        auditService.writeInAudit(Thread.currentThread().getName() + ",Remove ticket details by location id");
        SoldTicketService soldTicketService = SoldTicketService.getInstance();
        //remove ticket details having the location id = id, and also sold tickets having this ticket detail
        ArrayList<TicketDetails> array = ticketDetailsRepository.findTicketByIdLocation(id);

        for(TicketDetails ticket : array) {
            soldTicketService.removeTicketByTicketDetailsId(ticket.getIdTicket());
        }
        ticketDetailsRepository.removeTicketByLocationId(id);
    }


    //find

    public ArrayList<TicketDetails> findTicketByDate(Date d) throws SQLException {
        auditService.writeInAudit(Thread.currentThread().getName() + ",Find ticket details by date");
        return ticketDetailsRepository.findTicketByDate(d);
    }

    //update
    public void updateTicketDetailsEventOfEvent(int id, int newId) throws SQLException {
        auditService.writeInAudit(Thread.currentThread().getName() + ",Update ticket details event");
        TicketDetails t = ticketDetailsRepository.getTicketById(id);
        if(t == null)
            throw new IllegalArgumentException("No ticket having this id!");
        ticketDetailsRepository.updateTicketDetailsEvent(id, newId);
    }

    public void updateTicketDetailsLocationOfEvent(int id, int newIdLocation) throws SQLException {
        auditService.writeInAudit(Thread.currentThread().getName() + ",Update ticket details location");
        TicketDetails t = ticketDetailsRepository.getTicketById(id);
        if(t == null)
            throw new IllegalArgumentException("No ticket having this id!");
        ticketDetailsRepository.updateTicketDetailsLocation(id, newIdLocation);
    }

    public void updateTicketDetailsDateOfEvent(Date date, Date newDate) throws SQLException {
        auditService.writeInAudit(Thread.currentThread().getName() + ",Update ticket details date");
        ArrayList<TicketDetails> t = ticketDetailsRepository.findTicketByDate(date);
        if(t.size() == 0)
            throw new IllegalArgumentException("No tickets on this date!");
        ticketDetailsRepository.updateTicketDetailsDate(date, newDate);
    }

    public void updateTicketDetailsDateOfEvent(int id, Date newDate) throws SQLException {
        auditService.writeInAudit(Thread.currentThread().getName() + ",Update ticket details date");
        TicketDetails t = ticketDetailsRepository.getTicketById(id);
        if(t == null)
            throw new IllegalArgumentException("No ticket having this id!");
        ticketDetailsRepository.updateTicketDetailsDate(id, newDate);
    }

    public void updateTicketDetailsHourOfEvent(int id, String hour) throws SQLException {
        auditService.writeInAudit(Thread.currentThread().getName() + ",Update ticket details hour");
        TicketDetails t = ticketDetailsRepository.getTicketById(id);
        if(t == null)
            throw new IllegalArgumentException("No ticket having this id!");
        ticketDetailsRepository.updateTicketDetailsHour(id, hour);
    }

    //get
    public ArrayList<TicketDetails> getTicketDetails() throws SQLException {
        auditService.writeInAudit(Thread.currentThread().getName() + ",Get ticket details");
        return ticketDetailsRepository.getTickets();
    }

    public TicketDetails getTicketDetailsById(int id) throws SQLException {
        auditService.writeInAudit(Thread.currentThread().getName() + ",Get ticket details by id");
        return ticketDetailsRepository.getTicketById(id);
    }

    public ArrayList<TicketDetails> getTicketDetailsByIdEvent(int id) throws SQLException {
        auditService.writeInAudit(Thread.currentThread().getName() + ",Get ticket details by id of event");
        return ticketDetailsRepository.findTicketByIdEvent(id);
    }

    public ArrayList<TicketDetails> getTicketDetailsByIdLocation(int id) throws SQLException {
        auditService.writeInAudit(Thread.currentThread().getName() + ",Get ticket details by id of location");
        return ticketDetailsRepository.getTicketDetailsByIdLocation(id);
    }

    public TicketDetailsRepository getTicketDetailsRepository() {
        auditService.writeInAudit(Thread.currentThread().getName() + ",Get ticketDetailsRepository");
        return ticketDetailsRepository;
    }
}
