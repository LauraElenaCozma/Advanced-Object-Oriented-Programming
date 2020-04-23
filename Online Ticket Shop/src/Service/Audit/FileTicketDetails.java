package Service.Audit;

import Model.Date;
import Model.TicketDetails;
import Service.TicketDetailsService;

import java.io.BufferedWriter;
import java.io.IOException;

public class FileTicketDetails implements FileInterface<TicketDetails> {
    public static FileTicketDetails instance = new FileTicketDetails();

    private FileTicketDetails () {
    }

    public static FileTicketDetails getInstance() {
        return instance;
    }
    public void print(BufferedWriter out, TicketDetails ob) throws IOException{
        out.write(Integer.toString(ob.getIdTicket()) + ",");
        out.write(Integer.toString(ob.getIdEvent()) + ",");
        out.write(Integer.toString(ob.getIdLocation()) + ",");
        out.write(ob.getDate().getDay() + "/" + ob.getDate().getMonth() + "/" + ob.getDate().getYear() + ",");
        out.write(ob.getHour());
    }

    public void load(String line) {
        String[] data = line.split(",");
        int maxId = TicketDetails.getUniqueKey();
        if(Integer.parseInt(data[0]) > maxId) {
            maxId = Integer.parseInt(data[0]);
        }
        String[] date = data[3].split("/");

        TicketDetails.setUniqueKey(maxId + 1); //the next available id
        TicketDetailsService.getInstance().getTicketDetailsRepository().addTicket(new TicketDetails(Integer.parseInt(data[0]), //idTicket
                                                    Integer.parseInt(data[1]), //idEvent
                                                    Integer.parseInt(data[2]), //idLocation
                                                    new Date(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2])),
                                                    data[4])); //date, hour
    }


    public void writeHeader(BufferedWriter out) throws IOException {
        out.write("Ticket id,Event id,Location id,Date,Hour");
    }
}
