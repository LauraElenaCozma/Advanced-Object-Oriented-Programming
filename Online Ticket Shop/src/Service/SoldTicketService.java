package Service;

import Model.*;
import Repository.SoldTicketRepository;
import Service.Audit.*;

import java.util.ArrayList;

public class SoldTicketService {
    private SoldTicketRepository soldTicketRepository = new SoldTicketRepository();
    private static SoldTicketService instance = new SoldTicketService();
    private static AuditService auditService = AuditService.getInstance();
    public static IOFileService<FileSoldTicket, SoldTicket> ioFileService = IOFileService.getInstance();
    public static FileSoldTicket fileSoldTicket = FileSoldTicket.getInstance();
    private SoldTicketService() {
    }

    public static SoldTicketService getInstance() {
        return instance;
    }

    public SoldTicket addTicket(SoldTicket t) {
        auditService.writeInAudit("Add ticket");
        TicketDetails td = TicketDetailsService.getInstance().getTicketDetailsById(t.getIdTicketDetails());
        if(td == null)
            throw new IllegalArgumentException("No ticket details having this id!");

        Client c = ClientService.getInstance().getClientById(t.getIdClient());
        if(c == null)
            throw new IllegalArgumentException("No client having this id!");
        soldTicketRepository.addTicket(t);
        t.setPriceAfterDiscount(updateFinalPriceOfTicket(t.getIdTicket()));
        ioFileService.appendInFile(fileSoldTicket, t, "soldTickets.csv");
        return t;
    }

    //remove
    public void removeTicketById(int id ) {
        //remove sold ticket with id = id
        auditService.writeInAudit("Remove ticket by id");
        soldTicketRepository.removeSoldTicketById(id);
        ioFileService.updateFile(fileSoldTicket, soldTicketRepository.getTickets(), "soldTickets.csv");
    }

    public void removeTicketByTicketDetailsId(int id) {
        //remove sold ticket with ticket id = id
        auditService.writeInAudit("Remove ticket by ticket details id");
        soldTicketRepository.removeSoldTicketByTicketDetailsId(id);
        ioFileService.updateFile(fileSoldTicket, soldTicketRepository.getTickets(), "soldTickets.csv");
    }


    public void removeTicketByClientId(int id) {
        auditService.writeInAudit("Remove ticket by client id");
        soldTicketRepository.removeSoldTicketByClientId(id);
        ioFileService.updateFile(fileSoldTicket, soldTicketRepository.getTickets(), "soldTickets.csv");
    }


    //find
    public ArrayList<SoldTicket> findSoldTicketByTicketDetailsId(int id) {
        auditService.writeInAudit("Find ticket by ticket details id");
        return soldTicketRepository.findSoldTicketByTicketDetailsId(id);
    }

    public ArrayList<SoldTicket> findSoldTicketByClientId(int id) {
        auditService.writeInAudit("Find ticket by client id");
        return soldTicketRepository.findSoldTicketByClientId(id);
    }

    //update
    public void updateTicketDetailsOfTicket(int id, int newId) {
        auditService.writeInAudit("Update ticket details id");
        SoldTicket t = soldTicketRepository.getTicketById(id);
        if(t == null)
            throw new IllegalArgumentException("No ticket having this id!");
        soldTicketRepository.updateSoldTicketDetails(id, newId);
        ioFileService.updateFile(fileSoldTicket, soldTicketRepository.getTickets(), "soldTickets.csv");
    }


    public void updateClientOfTicket(int id, int newId) {
        auditService.writeInAudit("Update client id");
        SoldTicket t = soldTicketRepository.getTicketById(id);
        if(t == null)
            throw new IllegalArgumentException("No ticket having this id!");
        soldTicketRepository.updateSoldTicketClient(id, newId);
        ioFileService.updateFile(fileSoldTicket, soldTicketRepository.getTickets(), "soldTickets.csv");
    }

    public double updateFinalPriceOfTicket(int id) {
        auditService.writeInAudit("Update final price of ticket");
        TicketDetailsService ticketDetailsService = TicketDetailsService.getInstance();
        EventService eventService = EventService.getInstance();
        ClientService clientService = ClientService.getInstance();
        int ticketDetailsId = soldTicketRepository.getTicketById(id).getIdTicketDetails();
        int eventId = ticketDetailsService.getTicketDetailsById(ticketDetailsId).getIdEvent();
        Event e = eventService.getEventById(eventId);
        double discount = clientService.getClientById(soldTicketRepository.getTicketById(id).getIdClient()).computeDiscount();
        double newPrice = e.getPrice() * (1 - discount);
        return newPrice;
    }

    //get
    public ArrayList<SoldTicket> getSoldTickets() {
        auditService.writeInAudit("Get sold tickets");
        return soldTicketRepository.getTickets();
    }

    public SoldTicket getSoldTicketById(int id) {
        auditService.writeInAudit("Get ticket by id");
        return soldTicketRepository.getTicketById(id);
    }

    public SoldTicketRepository getSoldTicketRepository() {
        return soldTicketRepository;
    }
}
