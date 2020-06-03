package Repository;

import Model.Event;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventRepository {
    //repository for events
    private ArrayList<Event> events;
    String url = "jdbc:mysql://localhost:3306/ticketshop";
    String username = "root";
    String password = "1234";
    Connection con;


    public EventRepository() throws SQLException {

        events = new ArrayList<>();
    }

    synchronized  public void addEvent(Event ev) throws SQLException {
        //add an Event
        con = DriverManager.getConnection(url, username, password);
        String sql = "INSERT INTO events VALUES(NULL, ?, ?, ?)";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, ev.getName());
        statement.setInt(2, ev.getDuration());
        statement.setDouble(3, ev.getPrice());
        statement.executeUpdate();
        statement.close();
        con.close();
    }

    public void removeEventById(int id){
        //remove Event with a given id

        try {
            con = DriverManager.getConnection(url, username, password);
            String sql = "DELETE FROM events WHERE id_event = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
            statement.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //find
    public ArrayList<Event> findEventByName(String name) {
        //returns the events with the name name(can be different in price, duration)
        ArrayList<Event> ev = new ArrayList<>();

        try {
            con = DriverManager.getConnection(url, username, password);
            String sql = "SELECT * FROM events WHERE name = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            while(rs.next())
                ev.add(new Event(rs.getInt("id_event"), rs.getString("name"), rs.getInt("duration"), rs.getDouble("price")));
            statement.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ev;
    }

    //update
    public void updateNameEvent(int id, String newName) {
        //update the name of the event that has id = id

        try {

            con = DriverManager.getConnection(url, username, password);
            String sql = "UPDATE events SET name = ? WHERE id_event = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, newName);
            statement.setInt(2, id);
            statement.executeUpdate();
            statement.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updatePriceEventById(int id, double newPrice) {
        //update the price of the event that has id = id
        try {
            con = DriverManager.getConnection(url, username, password);
            String sql = "UPDATE events SET price = ? WHERE id_event = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setDouble(1, newPrice);
            statement.setInt(2, id);
            statement.executeUpdate();
            statement.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateDurationEventById(int id, int newDuration) {
        //update the duration of the event that has id = id

        try {
            con = DriverManager.getConnection(url, username, password);
            String sql = "UPDATE events SET duration = ? WHERE id_event = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, newDuration);
            statement.setInt(2, id);
            statement.executeUpdate();
            statement.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //get
    public ArrayList<Event> getEvents(){
        //return all the events
       ArrayList<Event> ev = new ArrayList<>();
        try {
            con = DriverManager.getConnection(url, username, password);
            String sql = "SELECT * FROM events";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next())
                ev.add(new Event(rs.getInt("id_event"), rs.getString("name"), rs.getInt("duration"), rs.getDouble("price")));
            statement.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ev;
    }

    public Event getEventById(int id) {
        //return the event with id = id
        try {
            con = DriverManager.getConnection(url, username, password);
            String sql = "SELECT * FROM events WHERE id_event = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            Event e;
            if(rs.next() == false)
                e = null;
            else e = new Event(rs.getInt("id_event"), rs.getString("name"), rs.getInt("duration"), rs.getDouble("price"));
            statement.close();
            con.close();

            return e;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
