package Thread;

import Main.Main;
import Model.TicketDetails;


import java.sql.SQLException;

public class ThreadAddTicket extends Thread {

    private TicketDetails t;
    public ThreadAddTicket(TicketDetails t) {

        this.t = t;
    }

    @Override
    public void run() {
        try {
            Main.ticketDetailsService.addTicket(t);
        }
        catch(SQLException e)
        {
            throw new RuntimeException("Error");
        }
    }
}
