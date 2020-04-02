package Model;

import java.util.Arrays;

public class Event {
    static private int uniqueId = 1;
    private int idEvent;
    private String name;
    private int duration; //duration of play
    private double price;


    public Event() {

        this.idEvent = uniqueId;
        uniqueId++;
    }

    public Event(String name, int duration, double price) {
        this.idEvent = uniqueId;
        uniqueId ++;
        this.name = name;
        this.duration = duration;
        this.price = price;
    }

    public double getPrice() {

        return price;
    }

    public void setPrice(double price) {

        this.price = price;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public int getDuration() {

        return duration;
    }

    public void setDuration(int duration) {

        this.duration = duration;
    }

    public int getIdEvent() {

        return idEvent;
    }

    public void setIdEvent(int idEvent) {

        this.idEvent = idEvent;
    }


    @Override
    public String toString() {
        return "Event{" +
                "idEvent=" + idEvent +
                ", name='" + name + '\'' +
                ", duration=" + duration +
                ", price=" + price +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Event)) {
            return false;
        }
        Event e = (Event) o;

        // Compare the data members and return accordingly
        return idEvent == e.getIdEvent() && name.equals(e.getName()) && duration == e.getDuration() && price == e.getPrice();
    }

}
