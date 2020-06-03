package Service;

import Main.Main;
import Model.*;
import Repository.SoldTicketRepository;
import Audit.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class SoldTicketService {
    private SoldTicketRepository soldTicketRepository = null;
    private static SoldTicketService instance;
    private static AuditService auditService = AuditService.getInstance();

    private SoldTicketService(Connection con) {
        soldTicketRepository = new SoldTicketRepository(con);
    }

    public static SoldTicketService getInstance(Connection con) {
        if(instance == null) {
            instance = new SoldTicketService(con);
        }
        return instance;
    }

    public SoldTicket addTicket(SoldTicket t) throws SQLException {
        auditService.writeInAudit("Add ticket");

        int id = soldTicketRepository.addTicket(t);
        if (id != -1) {
            updateFinalPriceOfTicket(id);
        }
        return t;
    }

    //remove
    public void removeTicketById(int id ) throws SQLException {
        //remove sold ticket with id = id
        auditService.writeInAudit("Remove ticket by id");
        soldTicketRepository.removeSoldTicketById(id);

    }

    public void removeTicketByTicketDetailsId(int id) throws SQLException {
        //remove sold ticket with ticket id = id
        auditService.writeInAudit("Remove ticket by ticket details id");
        soldTicketRepository.removeSoldTicketByTicketDetailsId(id);
    }


    public void removeTicketByClientId(int id) throws SQLException {
        auditService.writeInAudit("Remove ticket by client id");
        soldTicketRepository.removeSoldTicketByClientId(id);
    }


    //find
    public ArrayList<SoldTicket> findSoldTicketByTicketDetailsId(int id) throws SQLException {
        auditService.writeInAudit("Find ticket by ticket details id");
        return soldTicketRepository.findSoldTicketByTicketDetailsId(id);
    }

    public ArrayList<SoldTicket> findSoldTicketByClientId(int id) throws SQLException {
        auditService.writeInAudit("Find ticket by client id");
        return soldTicketRepository.findSoldTicketByClientId(id);
    }

    //update
    public void updateTicketDetailsOfTicket(int id, int newId) throws SQLException {
        auditService.writeInAudit("Update ticket details id");
        updateFinalPriceOfTicket(id);
        soldTicketRepository.updateSoldTicketDetails(id, newId);
    }


    public void updateClientOfTicket(int id, int newId) throws SQLException {
        auditService.writeInAudit("Update client id");
        updateFinalPriceOfTicket(id);
        soldTicketRepository.updateSoldTicketClient(id, newId);
    }

    public void updateFinalPriceOfTicket(int id) throws SQLException {
        //used to update the price in sold ticket, applying the discount to the initial price
        auditService.writeInAudit("Update final price of ticket");
        TicketDetailsService ticketDetailsService = Main.ticketDetailsService;
        EventService eventService = Main.eventService;
        ClientService clientService = Main.clientService;
        int ticketDetailsId = soldTicketRepository.getTicketById(id).getIdTicketDetails();
        int eventId = ticketDetailsService.getTicketDetailsById(ticketDetailsId).getIdEvent();
        Event e = eventService.getEventById(eventId);
        double discount = clientService.getClientById(soldTicketRepository.getTicketById(id).getIdClient()).computeDiscount();
        double newPrice = e.getPrice() * (1 - discount);

        Connection con = Main.connection;
        String sql = "UPDATE soldtickets SET price_after_discount = ? WHERE id_soldticket = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setDouble(1, newPrice);
        statement.setInt(2, id);
        statement.executeUpdate();
        statement.close();
    }


    //get
    public ArrayList<SoldTicket> getSoldTickets() throws SQLException {
        auditService.writeInAudit("Get sold tickets");
        return soldTicketRepository.getTickets();
    }

    public SoldTicket getSoldTicketById(int id) throws SQLException {
        auditService.writeInAudit("Get ticket by id");
        return soldTicketRepository.getTicketById(id);
    }

}
