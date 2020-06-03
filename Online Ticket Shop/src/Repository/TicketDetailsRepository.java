package Repository;

import Model.Event;
import Model.TicketDetails;

import java.sql.*;
import java.util.ArrayList;

public class TicketDetailsRepository {
    private ArrayList<TicketDetails> tickets;
    String url = "jdbc:mysql://localhost:3306/ticketshop";
    String username = "root";
    String password = "1234";
    Connection con;

    public TicketDetailsRepository() {

        tickets = new ArrayList<>();
    }



    public void addTicket(TicketDetails ticket) throws SQLException {
        con = DriverManager.getConnection(url, username, password);
        String sql = "INSERT INTO tickets VALUES(NULL, ?, ?, ?, ?)";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, ticket.getIdEvent());
        statement.setInt(2, ticket.getIdLocation());
        statement.setDate(3, ticket.getDate());
        statement.setString(4, ticket.getHour());
        statement.executeUpdate();
        statement.close();
        con.close();
    }

    //remove
    public void removeTicketById(int id) throws SQLException {
        //remove Ticket with a given id
        con = DriverManager.getConnection(url, username, password);
        String sql = "DELETE FROM tickets WHERE id_ticket = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
        statement.close();
        con.close();
    }

    public void removeTicketDetailsByEventId(int id) throws SQLException {
        //search for the ticket having id event = id and removes it
        con = DriverManager.getConnection(url, username, password);
        String sql = "DELETE FROM tickets WHERE id_event = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
        statement.close();
        con.close();

    }

    public void removeTicketByLocationId(int id) throws SQLException {
        //remove ticket with a given location
        con = DriverManager.getConnection(url, username, password);
        String sql = "DELETE FROM tickets WHERE id_location = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
        statement.close();
        con.close();
    }

    //find
    public ArrayList<TicketDetails> findTicketByDate(Date d) throws SQLException {

        ArrayList<TicketDetails> t = new ArrayList<>();
        con = DriverManager.getConnection(url, username, password);
        String sql = "SELECT * FROM tickets WHERE date = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setDate(1, d);
        ResultSet rs = statement.executeQuery();
        while(rs.next())
            t.add(new TicketDetails(rs.getInt("id_ticket"), rs.getInt("id_event"), rs.getInt("id_location"), rs.getDate("date"), rs.getString("hour")));
        statement.close();
        con.close();
        return t;
    }

    public ArrayList<TicketDetails> findTicketByIdEvent(int id) throws SQLException {
        ArrayList<TicketDetails> t = new ArrayList<>();
        con = DriverManager.getConnection(url, username, password);
        String sql = "SELECT * FROM tickets WHERE id_event = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();
        while(rs.next())
            t.add(new TicketDetails(rs.getInt("id_ticket"), rs.getInt("id_event"), rs.getInt("id_location"), rs.getDate("date"), rs.getString("hour")));
        statement.close();
        con.close();
        return t;
    }


    public ArrayList<TicketDetails> findTicketByIdLocation(int id) throws SQLException {


        ArrayList<TicketDetails> t = new ArrayList<>();
        con = DriverManager.getConnection(url, username, password);
        String sql = "SELECT * FROM tickets WHERE id_location = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();
        while(rs.next())
            t.add(new TicketDetails(rs.getInt("id_ticket"), rs.getInt("id_event"), rs.getInt("id_location"), rs.getDate("date"), rs.getString("hour")));
        statement.close();
        con.close();
        return t;
    }

    //update

    public void updateTicketDetailsEvent(int id, int newId) throws SQLException {
        //update the events of some tickets
        con = DriverManager.getConnection(url, username, password);
        String sql = "UPDATE tickets SET id_event = ? WHERE id_ticket = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, newId);
        statement.setInt(2, id);
        statement.executeUpdate();
        statement.close();
        con.close();
    }


    public void updateTicketDetailsLocation(int id, int newIdLocation) throws SQLException {
        //updates the ticket with id = id with a new location
        con = DriverManager.getConnection(url, username, password);
        String sql = "UPDATE tickets SET id_location = ? WHERE id_ticket = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, newIdLocation);
        statement.setInt(2, id);
        statement.executeUpdate();
        statement.close();
        con.close();
    }

    public void updateTicketDetailsDate(Date d, Date newDate) throws SQLException {
        con = DriverManager.getConnection(url, username, password);
        String sql = "UPDATE tickets SET date = ? WHERE date = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setDate(1, newDate);
        statement.setDate(2, d);
        statement.executeUpdate();
        statement.close();
        con.close();
    }

    public void updateTicketDetailsDate(int id, Date newDate) throws SQLException {
        con = DriverManager.getConnection(url, username, password);
        String sql = "UPDATE tickets SET date = ? WHERE id_ticket = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setDate(1, newDate);
        statement.setInt(2, id);
        statement.executeUpdate();
        statement.close();
        con.close();
    }

    public void updateTicketDetailsHour(int id, String newHour) throws SQLException {
        con = DriverManager.getConnection(url, username, password);
        String sql = "UPDATE tickets SET hour = ? WHERE id_ticket = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, newHour);
        statement.setInt(2, id);
        statement.executeUpdate();
        statement.close();
        con.close();
    }


    public ArrayList<TicketDetails> getTickets() throws SQLException {

        ArrayList<TicketDetails> t = new ArrayList<>();
        con = DriverManager.getConnection(url, username, password);
        String sql = "SELECT * FROM tickets";
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while(rs.next())
            t.add(new TicketDetails(rs.getInt("id_ticket"), rs.getInt("id_event"), rs.getInt("id_location"), rs.getDate("date"), rs.getString("hour")));
        statement.close();
        con.close();
        return t;
    }

    public TicketDetails getTicketById(int id) throws SQLException {

        con = DriverManager.getConnection(url, username, password);
        String sql = "SELECT * FROM tickets WHERE id_ticket = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();

        TicketDetails t;
        if(rs.next() == false)
            t = null;
        else t = new TicketDetails(rs.getInt("id_ticket"), rs.getInt("id_event"), rs.getInt("id_location"), rs.getDate("date"), rs.getString("hour"));
        statement.close();
        con.close();

        return t;
    }

    public ArrayList<TicketDetails> getTicketDetailsByIdLocation(int id) throws SQLException {
        ArrayList<TicketDetails> t = new ArrayList<>();
        con = DriverManager.getConnection(url, username, password);
        String sql = "SELECT * FROM tickets WHERE id_location = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();

        while(rs.next())
            t.add(new TicketDetails(rs.getInt("id_ticket"), rs.getInt("id_event"), rs.getInt("id_location"), rs.getDate("date"), rs.getString("hour")));
        statement.close();
        con.close();
        return t;
    }

}
