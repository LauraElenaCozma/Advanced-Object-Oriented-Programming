package Repository;

import Model.Date;
import Model.Event;
import Model.Location;
import Model.TicketDetails;

import java.util.ArrayList;

public class TicketDetailsRepository {
    private ArrayList<TicketDetails> tickets;

    public TicketDetailsRepository() {

        tickets = new ArrayList<>();
    }

    public TicketDetailsRepository(ArrayList<TicketDetails> tickets) {

        this.tickets = new ArrayList<>(tickets);
    }

    public void addTicket(TicketDetails ticket) {

        tickets.add(ticket);
    }

    //remove
    public void removeTicketById(int id){
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

    public void removeTicketDetailsByEventId(int id) {
        //search for the ticket having id event = id and removes it
        for(int i = 0 ; i < tickets.size() ; i++) {
            if(tickets.get(i).getIdEvent() == id) {
                tickets.remove(i);
                i--;
            }
        }
    }

    public void removeTicketByLocationId(int id){
        //remove ticket with a given location
        for(int i = 0 ; i < tickets.size() ; i++){
            if(tickets.get(i).getIdLocation() == id) {
                tickets.remove(i);
                i--;
            }
        }
    }

    //find
    public ArrayList<TicketDetails> findTicketByDate(Date d) {

        ArrayList<TicketDetails> indexes = new ArrayList<>();
        for(int i = 0 ; i < tickets.size() ; i++) {
            if(tickets.get(i).getDate().equals(d) == true) {
                indexes.add(tickets.get(i));
            }
        }
        return indexes;
    }

    public ArrayList<TicketDetails> findTicketByIdEvent(int id) {
        ArrayList<TicketDetails> indexes = new ArrayList<>();
        for(int i = 0 ; i < tickets.size() ; i++) {
            if(tickets.get(i).getIdEvent() == id) {
                indexes.add(tickets.get(i));
            }
        }
        return indexes;
    }

    public ArrayList<TicketDetails> findTicketByIdLocation(int id) {
        ArrayList<TicketDetails> indexes = new ArrayList<>();
        for(int i = 0 ; i < tickets.size() ; i++) {
            if(tickets.get(i).getIdLocation() == id) {
                indexes.add(tickets.get(i));
            }
        }
        return indexes;
    }

    //update

    public void updateTicketDetailsEvent(int id, int newId) {
        //update the events of some tickets
        for(int i = 0 ; i < tickets.size() ; i++) {
            if(tickets.get(i).getIdTicket() == id) {
                tickets.get(i).setIdEvent(newId);
            }
        }
    }


    public void updateTicketDetailsLocation(int id, int newIdLocation) {
        //updates the ticket with id = id with a new location
        for(int i = 0 ; i < tickets.size() ; i++) {
            if(tickets.get(i).getIdTicket() == id) {
                tickets.get(i).setIdLocation(newIdLocation);
            }
        }
    }

    public void updateTicketDetailsDate(Date d, Date newDate) {
        for(int i = 0 ; i < tickets.size() ; i++) {
            if(tickets.get(i).getDate().equals(d) == true) {
                tickets.get(i).setDate(newDate);
            }
        }
    }

    public void updateTicketDetailsDate(int id, Date newDate) {
        for(int i = 0 ; i < tickets.size() ; i++) {
            if(tickets.get(i).getIdTicket() == id) {
                tickets.get(i).setDate(newDate);
            }
        }
    }

    public void updateTicketDetailsHour(int id, String newHour) {
        for(int i = 0 ; i < tickets.size() ; i++) {
            if(tickets.get(i).getIdTicket() == id) {
                tickets.get(i).setHour(newHour);
            }
        }
    }


    public ArrayList<TicketDetails> getTickets() {

        return tickets;
    }

    public TicketDetails getTicketById(int id) {
        for(int i = 0 ; i < tickets.size() ; i++) {
            if(tickets.get(i).getIdTicket() == id) {
                return tickets.get(i);
            }
        }
        return null;
    }

    public ArrayList<TicketDetails> getTicketDetailsByIdLocation(int id) {
        ArrayList<TicketDetails> indexes = new ArrayList<>();
        for(int i = 0 ; i < tickets.size() ; i++) {
            if(tickets.get(i).getIdLocation() == id) {
                indexes.add(tickets.get(i));
            }
        }
        return indexes;
    }

}
