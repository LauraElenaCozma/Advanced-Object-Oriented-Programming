package Service.Audit;

import Model.SoldTicket;
import Service.SoldTicketService;

import java.io.BufferedWriter;
import java.io.IOException;

public class FileSoldTicket implements FileInterface<SoldTicket> {
    public static FileSoldTicket instance = new FileSoldTicket();

    private FileSoldTicket() {
    }

    public static FileSoldTicket getInstance() {
        return instance;
    }
    public void print(BufferedWriter out, SoldTicket ob) throws IOException {
        out.write(Integer.toString(ob.getIdTicket()));
        out.write(",");
        out.write(Integer.toString(ob.getIdClient()));
        out.write(",");
        out.write(Integer.toString(ob.getIdTicketDetails()));
        out.write(",");
        out.write(Double.toString(ob.getPriceAfterDiscount()));
    }

    public void load(String line) {
        String[] data = line.split(",");
        int maxId = SoldTicket.getUniqueKey();
        if(Integer.parseInt(data[0]) > maxId) {
            maxId = Integer.parseInt(data[0]);
        }
        SoldTicket.setUniqueKey(maxId + 1); //the next available id
        SoldTicketService.getInstance().getSoldTicketRepository().addTicket(new SoldTicket(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2]), Double.parseDouble(data[3])));
    }

    public void writeHeader(BufferedWriter out) throws IOException {
        out.write("Ticket id,Client id,TicketDetails id,Price");
    }
}
