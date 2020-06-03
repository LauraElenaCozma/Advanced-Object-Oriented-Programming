package Service;

import Model.*;
import Repository.TicketDetailsRepository;
import Audit.*;

import java.sql.Connection;
import java.sql.Date;

import java.sql.SQLException;
import java.util.ArrayList;

public class TicketDetailsService {
    private TicketDetailsRepository ticketDetailsRepository = null;
    private static TicketDetailsService instance;
    private static AuditService auditService = AuditService.getInstance();

    private TicketDetailsService(Connection connection) {
        ticketDetailsRepository = new TicketDetailsRepository(connection);
    }

    public static TicketDetailsService getInstance(Connection connection) {
        if(instance == null) {
            instance = new TicketDetailsService(connection);
        }
        return instance;
    }

    //add
    synchronized public TicketDetails addTicket(TicketDetails t) throws SQLException {
        auditService.writeInAudit("Add ticket details");
        ticketDetailsRepository.addTicket(t);
        return t;
    }

    //remove
    public void removeTicketById(int id) throws SQLException {
        //remove a ticket detail by ticket detail id, and also sold tickets having this ticket detail
        auditService.writeInAudit("Remove ticket details by id");
        ticketDetailsRepository.removeTicketById(id);
    }


    public void removeTicketByEventId(int id) throws SQLException {
        //remove ticket details having the event id = id, and also sold tickets having this ticket detail
        auditService.writeInAudit("Remove ticket details by id event");
        ticketDetailsRepository.removeTicketDetailsByEventId(id);
    }

    public void removeTicketByLocationId(int id) throws SQLException {
        auditService.writeInAudit("Remove ticket details by location id");
        ticketDetailsRepository.removeTicketByLocationId(id);
    }


    //find

    public ArrayList<TicketDetails> findTicketByDate(Date d) throws SQLException {
        auditService.writeInAudit("Find ticket details by date");
        return ticketDetailsRepository.findTicketByDate(d);
    }

    //update
    public void updateTicketDetailsEventOfEvent(int id, int newId) throws SQLException {
        auditService.writeInAudit("Update ticket details event");
        ticketDetailsRepository.updateTicketDetailsEvent(id, newId);
    }

    public void updateTicketDetailsLocationOfEvent(int id, int newIdLocation) throws SQLException {
        auditService.writeInAudit("Update ticket details location");
        ticketDetailsRepository.updateTicketDetailsLocation(id, newIdLocation);
    }

    public void updateTicketDetailsDateOfEvent(Date date, Date newDate) throws SQLException {
        auditService.writeInAudit("Update ticket details date");
        ticketDetailsRepository.updateTicketDetailsDate(date, newDate);
    }

    public void updateTicketDetailsDateOfEvent(int id, Date newDate) throws SQLException {
        auditService.writeInAudit("Update ticket details date");
        ticketDetailsRepository.updateTicketDetailsDate(id, newDate);
    }

    public void updateTicketDetailsHourOfEvent(int id, String hour) throws SQLException {
        auditService.writeInAudit("Update ticket details hour");
        ticketDetailsRepository.updateTicketDetailsHour(id, hour);
    }

    //get
    public ArrayList<TicketDetails> getTicketDetails() throws SQLException {
        auditService.writeInAudit("Get ticket details");
        return ticketDetailsRepository.getTickets();
    }

    public TicketDetails getTicketDetailsById(int id) throws SQLException {
        auditService.writeInAudit("Get ticket details by id");
        return ticketDetailsRepository.getTicketById(id);
    }

    public ArrayList<TicketDetails> getTicketDetailsByIdEvent(int id) throws SQLException {
        auditService.writeInAudit("Get ticket details by id of event");
        return ticketDetailsRepository.findTicketByIdEvent(id);
    }

    public ArrayList<TicketDetails> getTicketDetailsByIdLocation(int id) throws SQLException {
        auditService.writeInAudit("Get ticket details by id of location");
        return ticketDetailsRepository.getTicketDetailsByIdLocation(id);
    }

    public TicketDetailsRepository getTicketDetailsRepository() {
        auditService.writeInAudit("Get ticketDetailsRepository");
        return ticketDetailsRepository;
    }
}
