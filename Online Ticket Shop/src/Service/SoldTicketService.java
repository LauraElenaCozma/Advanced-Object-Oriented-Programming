package Service;

import Model.*;
import Repository.SoldTicketRepository;
import Service.Audit.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class SoldTicketService {
    private SoldTicketRepository soldTicketRepository = new SoldTicketRepository();
    private static SoldTicketService instance = new SoldTicketService();
    private static AuditService auditService = AuditService.getInstance();

    private SoldTicketService() {
    }

    public static SoldTicketService getInstance() {
        return instance;
    }

    synchronized public SoldTicket addTicket(SoldTicket t) throws SQLException {
        auditService.writeInAudit(Thread.currentThread().getName() + ",Add ticket");

        int id = soldTicketRepository.addTicket(t);
        if (id != -1) {
            updateFinalPriceOfTicket(id);
        }
        return t;
    }

    //remove
    public void removeTicketById(int id ) throws SQLException {
        //remove sold ticket with id = id
        auditService.writeInAudit(Thread.currentThread().getName() + ",Remove ticket by id");
        soldTicketRepository.removeSoldTicketById(id);

    }

    public void removeTicketByTicketDetailsId(int id) throws SQLException {
        //remove sold ticket with ticket id = id
        auditService.writeInAudit(Thread.currentThread().getName() + ",Remove ticket by ticket details id");
        soldTicketRepository.removeSoldTicketByTicketDetailsId(id);
    }


    public void removeTicketByClientId(int id) throws SQLException {
        auditService.writeInAudit(Thread.currentThread().getName() + ",Remove ticket by client id");
        soldTicketRepository.removeSoldTicketByClientId(id);
    }


    //find
    public ArrayList<SoldTicket> findSoldTicketByTicketDetailsId(int id) throws SQLException {
        auditService.writeInAudit(Thread.currentThread().getName() + ",Find ticket by ticket details id");
        return soldTicketRepository.findSoldTicketByTicketDetailsId(id);
    }

    public ArrayList<SoldTicket> findSoldTicketByClientId(int id) throws SQLException {
        auditService.writeInAudit(Thread.currentThread().getName() + ",Find ticket by client id");
        return soldTicketRepository.findSoldTicketByClientId(id);
    }

    //update
    public void updateTicketDetailsOfTicket(int id, int newId) throws SQLException {
        auditService.writeInAudit(Thread.currentThread().getName() + ",Update ticket details id");
        soldTicketRepository.updateSoldTicketDetails(id, newId);
    }


    public void updateClientOfTicket(int id, int newId) throws SQLException {
        auditService.writeInAudit(Thread.currentThread().getName() + ",Update client id");
        soldTicketRepository.updateSoldTicketClient(id, newId);
    }

    public void updateFinalPriceOfTicket(int id) throws SQLException {
        auditService.writeInAudit(Thread.currentThread().getName() + ",Update final price of ticket");
        TicketDetailsService ticketDetailsService = TicketDetailsService.getInstance();
        EventService eventService = EventService.getInstance();
        ClientService clientService = ClientService.getInstance();
        System.out.println(id);
        int ticketDetailsId = soldTicketRepository.getTicketById(id).getIdTicketDetails();
        System.out.println(ticketDetailsId);
        int eventId = ticketDetailsService.getTicketDetailsById(ticketDetailsId).getIdEvent();
        System.out.println(eventId);
        Event e = eventService.getEventById(eventId);
        double discount = clientService.getClientById(soldTicketRepository.getTicketById(id).getIdClient()).computeDiscount();
        double newPrice = e.getPrice() * (1 - discount);
        String url = "jdbc:mysql://localhost:3306/ticketshop";
        String username = "root";
        String password = "1234";
        Connection con;
        try {
            con = DriverManager.getConnection(url, username, password);
            String sql = "UPDATE soldtickets SET price_after_discount = ? WHERE id_soldticket = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setDouble(1, newPrice);
            statement.setInt(2, id);
            statement.executeUpdate();
            statement.close();
            con.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    //get
    public ArrayList<SoldTicket> getSoldTickets() throws SQLException {
        auditService.writeInAudit(Thread.currentThread().getName() + ",Get sold tickets");
        return soldTicketRepository.getTickets();
    }

    public SoldTicket getSoldTicketById(int id) throws SQLException {
        auditService.writeInAudit(Thread.currentThread().getName() + ",Get ticket by id");
        return soldTicketRepository.getTicketById(id);
    }

}
