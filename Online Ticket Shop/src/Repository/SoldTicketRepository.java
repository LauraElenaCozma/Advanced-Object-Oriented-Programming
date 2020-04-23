package Repository;

import Model.Client;
import Model.Event;
import Model.SoldTicket;
import Model.TicketDetails;
import Service.ClientService;
import Service.TicketDetailsService;

import java.util.ArrayList;

public class SoldTicketRepository {
    //repository for sold tickets
    private ArrayList<SoldTicket> tickets;

    public SoldTicketRepository() {

        tickets = new ArrayList<>();
    }

    public SoldTicketRepository(ArrayList<SoldTicket> tickets) {

        this.tickets = new ArrayList<>(tickets);
    }

    //add
    public void addTicket(SoldTicket ticket) {

        tickets.add(ticket);
    }

    //remove
    public void removeSoldTicketById(int id){
        //remove Ticket with a given id
        int index = -1;
        for(int i = 0 ; i < tickets.size() ; i++){
            if(tickets.get(i).getIdTicket() == id) {
                index = i;
                break;
            }
        }

        if(index == -1) {
            System.out.println("There is no ticket with this id");
        }
        else {
            tickets.remove(index);
        }
    }


    public void removeSoldTicketByTicketDetailsId(int id){
        //remove ticket with a given event
        for(int i = 0 ; i < tickets.size() ; i++){
            if(tickets.get(i).getIdTicketDetails() == id) {
                tickets.remove(i);
                i--;
            }
        }
    }


    public void removeSoldTicketByClientId(int id) {
        for(int i = 0 ; i < tickets.size() ; i++) {
            if(tickets.get(i).getIdClient() == id) {
                tickets.remove(i);
                i--;
            }
        }
    }

    //find
    public ArrayList<SoldTicket> findSoldTicketByTicketDetailsId(int id) {
        ArrayList<SoldTicket> array = new ArrayList<>();
        for(int i = 0 ; i < tickets.size() ; i++) {
            if(tickets.get(i).getIdTicketDetails() == id) {
                array.add(tickets.get(i));
            }
        }
        return array;
    }

    public ArrayList<SoldTicket> findSoldTicketByClientId(int id) {
        ArrayList<SoldTicket> array = new ArrayList<>();
        for(int i = 0 ; i < tickets.size() ; i++) {
            if(tickets.get(i).getIdClient() == id) {
                array.add(tickets.get(i));
            }
        }
        return array;
    }

    //update

    public void updateSoldTicketDetails(int id, int newId) {
        TicketDetails t = TicketDetailsService.getInstance().getTicketDetailsById(newId);
        if(t == null)
            throw new IllegalArgumentException("No ticket having new Id");
        //update the clients of some tickets. The id of a sold ticket is given
        for(int i = 0 ; i < tickets.size() ; i++) {
            if(tickets.get(i).getIdTicketDetails() == id) {
                tickets.get(i).setIdTicketDetails(newId);
            }
        }
    }

    public void updateSoldTicketClient(int id, int newId) {
        //update the clients of some tickets
        Client c = ClientService.getInstance().getClientById(newId);
        if(c == null)
            throw new IllegalArgumentException("No client having new Id");
        for(int i = 0 ; i < tickets.size() ; i++) {
            if(tickets.get(i).getIdClient() == id) {
                tickets.get(i).setIdClient(newId);
            }
        }
    }

    //get
    public ArrayList<SoldTicket> getTickets() {

        return tickets;
    }

    public SoldTicket getTicketById(int id) {
        for(int i = 0 ; i < tickets.size() ; i++) {
            if (tickets.get(i).getIdTicket() == id) {
                return tickets.get(i);
            }
        }
        return null;
    }

}
