package Thread;

import Model.Event;
import Service.EventService;

import java.sql.SQLException;

public class ThreadAddEvent extends Thread
{
    private Event e;
    public ThreadAddEvent(Event e) {
        this.e = e;
    }

    @Override
    public void run() {
        try {
            EventService.getInstance().addEvent(e);
        }
        catch(SQLException e)
        {
            throw new RuntimeException("Error");
        }

    }
}
