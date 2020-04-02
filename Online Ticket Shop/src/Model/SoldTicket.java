package Model;

public class SoldTicket {
    //Sold tickets are here. It has the ticket with details and the client
    private static int uniqueKey = 1;
    private int idTicket;
    private Client client;
    private TicketDetails event;

    public SoldTicket() {
    }

    public SoldTicket(Client client, TicketDetails event) {
        this.idTicket = uniqueKey;
        uniqueKey++;
        this.client = client;
        this.event = event;
    }

    public int getIdTicket() {

        return idTicket;
    }

    public void setIdTicket(int idTicket) {

        this.idTicket = idTicket;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {

        this.client = client;
    }

    public TicketDetails getEvent() {

        return event;
    }

    public void setEvent(TicketDetails event) {
        this.event = event;
    }

    public double finalPriceTicket() {
        double price = event.getEvent().getPrice();
        return price * (1 - client.computeDiscount());
    }

    @Override
    public String toString() {
        return "SoldTicket{" +
                "idTicket=" + idTicket +
                ", " + client +
                ", " + event +
                ", Price with discount=" + finalPriceTicket() +
                "}\n";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof SoldTicket)) {
            return false;
        }
        SoldTicket t = (SoldTicket) o;

        // Compare the data members and return accordingly
        return idTicket == t.getIdTicket() && client.equals(t.getClient()) && event.equals(t.getEvent());
    }
}
