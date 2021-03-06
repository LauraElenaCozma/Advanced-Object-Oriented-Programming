package Thread;

import Main.Main;
import Model.Event;

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
            Main.eventService.addEvent(e);
        }
        catch(SQLException e)
        {
            throw new RuntimeException("Error");
        }

    }
}
