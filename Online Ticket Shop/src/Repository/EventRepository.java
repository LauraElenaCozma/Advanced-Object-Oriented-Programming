package Repository;

import Model.Event;

import java.sql.*;
import java.util.ArrayList;

public class EventRepository {
    //repository for events
    Connection con;


    public EventRepository(Connection connection) {

        con = connection;
    }

    synchronized  public void addEvent(Event ev) throws SQLException {
        //add an Event
        String sql = "INSERT INTO events VALUES(NULL, ?, ?, ?)";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, ev.getName());
        statement.setInt(2, ev.getDuration());
        statement.setDouble(3, ev.getPrice());
        statement.executeUpdate();
        statement.close();
    }

    public void removeEventById(int id) throws SQLException {
        //remove Event with a given id
        String sql = "DELETE FROM events WHERE id_event = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
        statement.close();
    }


    //find
    public ArrayList<Event> findEventByName(String name) throws SQLException {
        //returns the events with the name name(can be different in price, duration)
        ArrayList<Event> ev = new ArrayList<>();
        String sql = "SELECT * FROM events WHERE name = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, name);
        ResultSet rs = statement.executeQuery();
        while(rs.next())
            ev.add(new Event(rs.getInt("id_event"), rs.getString("name"), rs.getInt("duration"), rs.getDouble("price")));
        statement.close();
        return ev;
    }

    //update
    public void updateNameEvent(int id, String newName) throws SQLException {
        //update the name of the event that has id = id
        String sql = "UPDATE events SET name = ? WHERE id_event = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, newName);
        statement.setInt(2, id);
        statement.executeUpdate();
        statement.close();
    }

    public void updatePriceEventById(int id, double newPrice) throws SQLException {
        //update the price of the event that has id = id
        String sql = "UPDATE events SET price = ? WHERE id_event = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setDouble(1, newPrice);
        statement.setInt(2, id);
        statement.executeUpdate();
        statement.close();
    }

    public void updateDurationEventById(int id, int newDuration) throws SQLException {
        //update the duration of the event that has id = id
        String sql = "UPDATE events SET duration = ? WHERE id_event = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, newDuration);
        statement.setInt(2, id);
        statement.executeUpdate();
        statement.close();
    }

    //get
    public ArrayList<Event> getEvents() throws SQLException {
        //return all the events
       ArrayList<Event> ev = new ArrayList<>();
        String sql = "SELECT * FROM events";
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while(rs.next())
            ev.add(new Event(rs.getInt("id_event"), rs.getString("name"), rs.getInt("duration"), rs.getDouble("price")));
        statement.close();
        return ev;
    }

    public Event getEventById(int id) throws SQLException {
        //return the event with id = id
        String sql = "SELECT * FROM events WHERE id_event = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();

        Event e;
        if(rs.next() == false)
            e = null;
        else e = new Event(rs.getInt("id_event"), rs.getString("name"), rs.getInt("duration"), rs.getDouble("price"));
        statement.close();
        return e;
    }
}
