package Model;

public class TicketDetails {
    //keeps some events and their specific details, such as date, location etc. These are the tickets that can be sold
    private static int uniqueKey = 1;
    private int idTicket;
    private int idEvent;
    private int idLocation;
    private Date date;
    private String hour;

    public TicketDetails() {
        this.idTicket = uniqueKey;
        uniqueKey++;
    }

    public TicketDetails(int idEvent, int idLocation, Date date, String hour) {
        this.idTicket = uniqueKey;
        uniqueKey++;
        this.idEvent = idEvent;
        this.idLocation = idLocation;
        this.date = date;
        this.hour = hour;
    }

    public TicketDetails(int idTicket, int idEvent, int idLocation, Date date, String hour) {
        this.idTicket = idTicket;
        this.idEvent = idEvent;
        this.idLocation = idLocation;
        this.date = date;
        this.hour = hour;
    }

    public static int getUniqueKey() {
        return uniqueKey;
    }

    public static void setUniqueKey(int uniqueKey) {
        TicketDetails.uniqueKey = uniqueKey;
    }

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public int getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(int idLocation) {
        this.idLocation = idLocation;
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
                ", idEvent=" + idEvent +
                ", idLocation=" + idLocation +
                ", " + date +
                ", hour='" + hour + '\'' +
                "}\n";
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
        return idTicket == t.getIdTicket() && idEvent == t.getIdEvent() && idLocation == t.getIdLocation()
                && date.equals(t.getDate()) && hour.equals(t.getHour());
    }
}
