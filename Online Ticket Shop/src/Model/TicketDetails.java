package Model;

public class TicketDetails {
    //keeps some events and their specific details, such as date, location etc. These are the tickets that can be sold
    private static int uniqueKey = 1;
    private int idTicket;
    private Event event;
    private Location loc;
    private Date date;
    private String hour;

    public TicketDetails() {
        this.idTicket = uniqueKey;
        uniqueKey++;
    }

    public TicketDetails(Event event, Location loc, Date date, String hour) {
        this.idTicket = uniqueKey;
        uniqueKey++;
        this.event = event;
        this.loc = loc;
        this.date = date;
        this.hour = hour;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Location getLocation() {

        return loc;
    }

    public void setLocation(Location loc) {

        this.loc = loc;
    }

    public Date getDate() {

        return date;
    }

    public void setDate(Date date) {

        this.date = date;
    }

    public String getHour() {

        return hour;
    }

    public void setHour(String hour) {

        this.hour = hour;
    }

    public int getIdTicket() {

        return idTicket;
    }

    @Override
    public String toString() {
        return "TicketDetails{" +
                "idTicket=" + idTicket +
                ", " + event +
                ", " + loc +
                ", " + date +
                ", hour='" + hour + '\'' +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof TicketDetails)) {
            return false;
        }
        TicketDetails t = (TicketDetails) o;

        // Compare the data members and return accordingly
        return idTicket == t.getIdTicket() && event.equals(t.getEvent()) && loc.equals(t.getLocation())
                && date.equals(t.getDate()) && hour.equals(t.getHour());
    }
}
