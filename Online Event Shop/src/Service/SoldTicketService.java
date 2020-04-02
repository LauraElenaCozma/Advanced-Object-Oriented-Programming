package Service;

import Model.*;
import Repository.SoldTicketRepository;
import Repository.TicketDetailsRepository;

import java.util.ArrayList;

public class SoldTicketService {
    private SoldTicketRepository soldTicketRepository = new SoldTicketRepository();
    private static SoldTicketService instance = new SoldTicketService();
    private SoldTicketService() {
    }

    public static SoldTicketService getInstance() {
        return instance;
    }

    public SoldTicket addTicket(SoldTicket t) {
        soldTicketRepository.addTicket(t);
        return t;
    }

    //remove
    public void removeTicketById(int id ) {
        //remove sold ticket with id = id
        soldTicketRepository.removeSoldTicketById(id);
    }

    public void removeTicketByTicketDetailsId(int id) {
        //remove sold ticket with ticket id = id
        soldTicketRepository.removeSoldTicketByTicketDetailsId(id);
    }

    public void removeTicketByEvent(Event e) {
        //remove sold ticket with the event e
        soldTicketRepository.removeSoldTicketByEvent(e);
    }

    public void removeTicketByEventId(int id) {
        //remove sold ticket with the event id in ticket details = id
        soldTicketRepository.removeSoldTicketByEventId(id);
    }

    public void removeTicketByClientId(int id) {

        soldTicketRepository.removeSoldTicketByClientId(id);
    }

    public void removeTicketByClientName(String name) {

        soldTicketRepository.removeSoldTicketByClientName(name);
    }

    public void removeTicketByLocationId(int id) {

        soldTicketRepository.removeSoldTicketByLocationId(id);
    }
    public void removeTicketByTicketDetails(TicketDetails t) {
        soldTicketRepository.removeSoldTicketByTicketDetails(t);
    }

    //find
    public ArrayList<SoldTicket> findSoldTicketByTicketDetails(TicketDetails t) {

        return soldTicketRepository.findSoldTicketByTicketDetails(t);
    }

    public ArrayList<SoldTicket> findSoldTicketByClient(Client c) {

        return soldTicketRepository.findSoldTicketByClient(c);
    }

    //update
    public void updateTicketDetailsOfTicket(TicketDetails e, TicketDetails newE) {

        soldTicketRepository.updateSoldTicketDetails(e, newE);
    }

    public void updateTicketDetailsOfTicket(int id, TicketDetails newE) {

        soldTicketRepository.updateSoldTicketDetails(id, newE);
    }

    public void updateClientOfTicket(Client c, Client newC) {

        soldTicketRepository.updateSoldTicketClient(c, newC);
    }

    public void updateClientOfTicket(int id, Client newC) {

        soldTicketRepository.updateSoldTicketClient(id, newC);
    }

    //get
    public ArrayList<SoldTicket> getSoldTickets() {

        return soldTicketRepository.getTickets();
    }

    public SoldTicket getSoldTicketById(int id) {

        return soldTicketRepository.getTicketById(id);
    }

}
