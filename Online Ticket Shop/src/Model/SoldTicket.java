package Model;

public class SoldTicket {
    //Sold tickets are here. It has the ticket with details and the client
    private int idTicket;
    private int idClient;
    private int idTicketDetails;
    private double priceAfterDiscount;

    public SoldTicket() {
    }

    public SoldTicket(int idClient, int idTicketDetails) {
        this.idClient = idClient;
        this.idTicketDetails = idTicketDetails;
    }

    public SoldTicket(int idTicket, int idClient, int idTicketDetails, double priceAfterDiscount) {
        this.idTicket = idTicket;
        this.idClient = idClient;
        this.idTicketDetails = idTicketDetails;
        this.priceAfterDiscount = priceAfterDiscount;
    }


    public int getIdTicket() {

        return idTicket;
    }

    public void setIdTicket(int idTicket) {

        this.idTicket = idTicket;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdTicketDetails() {
        return idTicketDetails;
    }

    public void setIdTicketDetails(int idTicketDetails) {
        this.idTicketDetails = idTicketDetails;
    }

    public double getPriceAfterDiscount() {
        return priceAfterDiscount;
    }

    public void setPriceAfterDiscount(double priceAfterDiscount) {
        this.priceAfterDiscount = priceAfterDiscount;
    }

    @Override
    public String toString() {
        return "SoldTicket{" +
                "idTicket=" + idTicket +
                ", idClient=" + idClient +
                ", idTicketDetails=" + idTicketDetails +
                ", Price after discount=" + priceAfterDiscount +
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
        return idTicket == t.getIdTicket() && idClient == t.getIdClient() && idTicketDetails == t.getIdTicketDetails() && priceAfterDiscount == t.getPriceAfterDiscount();
    }
}
