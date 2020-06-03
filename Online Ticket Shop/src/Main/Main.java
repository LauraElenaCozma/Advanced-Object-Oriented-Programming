package Main;

import Database.DBConnectionUtils;
import GUI.*;
import Service.*;
import java.sql.*;


public class Main {
    public static Connection connection = DBConnectionUtils.getDBConnection();
    public static EventService eventService = EventService.getInstance(connection);
    public static LocationService locationService = LocationService.getInstance(connection);
    public static ClientService clientService = ClientService.getInstance(connection);
    public static TicketDetailsService ticketDetailsService = TicketDetailsService.getInstance(connection);
    public static SoldTicketService soldTicketService = SoldTicketService.getInstance(connection);

    public static void main(String[] args) {

        try {
            new MainFrame();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            DBConnectionUtils.closeDBConnection(connection);
        }

    }

}
