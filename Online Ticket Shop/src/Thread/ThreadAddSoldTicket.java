package Thread;

import Model.SoldTicket;
import Service.EventService;
import Service.SoldTicketService;

import java.sql.SQLException;

public class ThreadAddSoldTicket extends Thread {

    private SoldTicket t;
    public ThreadAddSoldTicket(SoldTicket t) {
        this.t = t;
    }

    @Override
    public void run() {
        try {
            SoldTicketService.getInstance().addTicket(t);
        }
        catch(SQLException e)
        {
            throw new RuntimeException("Error");
        }
    }
}
