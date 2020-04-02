package Repository;

import Model.Event;

import java.util.ArrayList;

public class EventRepository {
    //repository for events
    private ArrayList<Event> events;

    public EventRepository() {

        events = new ArrayList<>();
    }

    public EventRepository(ArrayList<Event> events) {

        this.events = new ArrayList<>(events);
    }

    public void addEvent(Event ev){
        //add an Event
        events.add(ev);
    }

    public void removeEventById(int id){
        //remove Event with a given id
        int index = 0;
        for(int i = 0 ; i < events.size() ; i++){
            if(events.get(i).getIdEvent() == id) {
                index = i;
                break;
            }
        }

        if(index == 0) {
            System.out.println("There is no event with this id");
        }
        else {
            events.remove(index);
        }
    }

    public void removeEvent(Event ev){
        //remove a given Event
        //remove removes only the first occurrence and it is ok because the ids of the events are always different
        events.remove(ev);
    }

    //find
    public ArrayList<Event> findEventByName(String name) {
        //returns the events with the name name(can be different in price, duration)
        ArrayList<Event> ev = new ArrayList<>();
        for(int i = 0 ; i < events.size() ; i++) {
            if(events.get(i).getName().equals(name) == true) {
                ev.add(events.get(i));
            }
        }
        return ev;
    }

    //update
    public void updateNameEvent(String name, String newName) {
        //update the name of some events
        for(int i = 0 ; i < events.size() ; i++) {
            if(events.get(i).getName().equals(name) == true) {
                events.get(i).setName(newName);
            }
        }
    }

    public void updateNameEvent(int id, String newName) {
        //update the name of the event that has id = id
        for(int i = 0 ; i < events.size() ; i++) {
            if(events.get(i).getIdEvent() == id) {
                events.get(i).setName(newName);
            }
        }
    }

    public void updatePriceEventById(int id, double newPrice) {
        //update the price of the event that has id = id
        for(int i = 0 ; i < events.size() ; i++) {
            if(events.get(i).getIdEvent() == id) {
                events.get(i).setPrice(newPrice);
            }
        }
    }

    public void updateDurationEventById(int id, int newDuration) {
        //update the duration of the event that has id = id
        for(int i = 0 ; i < events.size() ; i++) {
            if(events.get(i).getIdEvent() == id) {
                events.get(i).setDuration(newDuration);
            }
        }
    }

    //get
    public ArrayList<Event> getEvents(){
        //return all the events
        return events;
    }

    public Event getEventById(int id) {
        //return the event with id = id
        for(Event e : events) {
            if(e.getIdEvent() == id)
                return e;
        }
        return null;
    }
}
