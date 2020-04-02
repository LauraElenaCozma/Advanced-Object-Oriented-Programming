package Repository;

import Model.Client;
import Model.Event;
import Model.SoldTicket;
import Model.TicketDetails;

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
        int index = 0;
        for(int i = 0 ; i < tickets.size() ; i++){
            if(tickets.get(i).getIdTicket() == id) {
                index = i;
                break;
            }
        }

        if(index == 0) {
            System.out.println("There is no ticket with this id");
        }
        else {
            tickets.remove(index);
        }
    }

    public void removeSoldTicketByTicketDetails(TicketDetails t){
        //remove ticket with a given event

        for(int i = 0 ; i < tickets.size() ; i++){
            if(tickets.get(i).getEvent().equals(t) == true) {
                tickets.remove(i);
                i--;
                break;
            }
        }
    }

    public void removeSoldTicketByTicketDetailsId(int id){
        //remove ticket with a given event
        for(int i = 0 ; i < tickets.size() ; i++){
            if(tickets.get(i).getEvent().getIdTicket() == id) {
                tickets.remove(i);
                i--;
                break;
            }
        }
    }


    public void removeSoldTicketByEvent(Event event){
        //remove ticket with a given event

        for(int i = 0 ; i < tickets.size() ; i++){
            if(tickets.get(i).getEvent().getEvent().equals(event) == true) {
                tickets.remove(i);
                i--;
                break;
            }
        }
    }

    public void removeSoldTicketByEventId(int id) {
        //remove the tickets having the id event(from ticket details) = id
        for(int i = 0 ; i < tickets.size() ; i++) {
            if(tickets.get(i).getEvent().getEvent().getIdEvent() == id) {
                tickets.remove(i);
                i--;
            }
        }
    }

    public void removeSoldTicketByClientId(int id) {
        for(int i = 0 ; i < tickets.size() ; i++) {
            if(tickets.get(i).getClient().getIdClient() == id) {
                tickets.remove(i);
                i--;
            }
        }
    }

    public void removeSoldTicketByClientName(String name) {
        for(int i = 0 ; i < tickets.size() ; i++) {
            if(tickets.get(i).getClient().getName().equals(name) == true) {
                tickets.remove(i);
                i--;
            }
        }
    }

    public void removeSoldTicketByLocationId(int id) {
        for(int i = 0 ; i < tickets.size() ; i++){
            if(tickets.get(i).getEvent().getLocation().getIdLocation() == id) {
                tickets.remove(i);
                i--;
                break;
            }
        }
    }

    //find
    public ArrayList<SoldTicket> findSoldTicketByTicketDetails(TicketDetails t) {

        ArrayList<SoldTicket> indexes = new ArrayList<>();
        for(int i = 0 ; i < tickets.size() ; i++) {
            if(tickets.get(i).getEvent().equals(t) == true) {
                indexes.add(tickets.get(i));
            }
        }
        return indexes;
    }

    public ArrayList<SoldTicket> findSoldTicketByClient(Client c) {

        ArrayList<SoldTicket> indexes = new ArrayList<>();
        for(int i = 0 ; i < tickets.size() ; i++) {
            if(tickets.get(i).getClient().equals(c) == true) {
                indexes.add(tickets.get(i));
            }
        }
        return indexes;
    }

    //update
    public void updateSoldTicketDetails(TicketDetails event, TicketDetails newEvent) {
        //update the ticket details of some tickets
        for(int i = 0 ; i < tickets.size() ; i++) {
            if(tickets.get(i).getEvent().equals(event) == true) {
                tickets.get(i).setEvent(newEvent);
            }
        }
    }
    public void updateSoldTicketDetails(int id, TicketDetails newEvent) {
        //update the clients of some tickets. The id of a sold ticket is given
        for(int i = 0 ; i < tickets.size() ; i++) {
            if(tickets.get(i).getIdTicket() == id) {
                tickets.get(i).setEvent(newEvent);
            }
        }
    }


    public void updateSoldTicketClient(Client c, Client newC) {
        //update the clients of some tickets
        for(int i = 0 ; i < tickets.size() ; i++) {
            if(tickets.get(i).getClient().equals(c) == true) {
                tickets.get(i).setClient(newC);
            }
        }
    }

    public void updateSoldTicketClient(int id, Client newC) {
        //update the clients of some tickets
        for(int i = 0 ; i < tickets.size() ; i++) {
            if(tickets.get(i).getIdTicket() == id) {
                tickets.get(i).setClient(newC);
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
