package Repository;

import Model.SoldTicket;

import java.sql.*;
import java.util.ArrayList;



public class SoldTicketRepository {
    //repository for sold tickets
    private ArrayList<SoldTicket> tickets;
    String url = "jdbc:mysql://localhost:3306/ticketshop";
    String username = "root";
    String password = "1234";
    Connection con;

    public SoldTicketRepository() {

        tickets = new ArrayList<>();
    }


    //add
    public int addTicket(SoldTicket ticket) throws SQLException {
        con = DriverManager.getConnection(url, username, password);
        String sql = "INSERT INTO soldtickets (id_soldticket, id_client, id_ticket) VALUES(NULL, ?, ?)";
        PreparedStatement statement = con.prepareStatement(sql);

        statement.setInt(1, ticket.getIdClient());
        statement.setInt(2, ticket.getIdTicketDetails());
        statement.executeUpdate();
        sql = "SELECT LAST_INSERT_ID()";
        Statement statement1 = con.createStatement();
        ResultSet rs = statement1.executeQuery(sql);
        rs.next();
        int id = rs.getInt(1);

        statement.close();
        statement1.close();
        con.close();
        return id;
    }

    //remove
    public void removeSoldTicketById(int id) throws SQLException {
        //remove Ticket with a given id
        con = DriverManager.getConnection(url, username, password);
        String sql = "DELETE FROM soldtickets WHERE id_soldticket = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
        statement.close();
        con.close();
    }


    public void removeSoldTicketByTicketDetailsId(int id) throws SQLException {
        //remove ticket with a given event
        con = DriverManager.getConnection(url, username, password);
        String sql = "DELETE FROM soldtickets WHERE id_ticket = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
        statement.close();
        con.close();
    }


    public void removeSoldTicketByClientId(int id) throws SQLException {
        con = DriverManager.getConnection(url, username, password);
        String sql = "DELETE FROM soldtickets WHERE id_client = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
        statement.close();
        con.close();
    }

    //find
    public ArrayList<SoldTicket> findSoldTicketByTicketDetailsId(int id) throws SQLException {
        ArrayList<SoldTicket> array = new ArrayList<>();
        con = DriverManager.getConnection(url, username, password);
        String sql = "SELECT * FROM soldtickets WHERE id_ticket = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();
        while(rs.next())
            array.add(new SoldTicket(rs.getInt("id_soldticket"), rs.getInt("id_client"), rs.getInt("id_ticket"), rs.getDouble("price_after_discount")));
        statement.close();
        con.close();
        return array;
    }

    public ArrayList<SoldTicket> findSoldTicketByClientId(int id) throws SQLException {
        ArrayList<SoldTicket> array = new ArrayList<>();
        con = DriverManager.getConnection(url, username, password);
        String sql = "SELECT * FROM soldtickets WHERE id_client = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();
        while(rs.next())
            array.add(new SoldTicket(rs.getInt("id_soldticket"), rs.getInt("id_client"), rs.getInt("id_ticket"), rs.getDouble("price_after_discount")));
        statement.close();
        con.close();
        return array;
    }

    //update

    public void updateSoldTicketDetails(int id, int newId) throws SQLException {
        con = DriverManager.getConnection(url, username, password);
        String sql = "UPDATE soldtickets SET id_ticket = ? WHERE id_soldticket = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, newId);
        statement.setInt(2, id);
        statement.executeUpdate();
        statement.close();
        con.close();
    }

    public void updateSoldTicketClient(int id, int newId) throws SQLException {
        //update the clients of some tickets
        con = DriverManager.getConnection(url, username, password);
        String sql = "UPDATE soldtickets SET id_client = ? WHERE id_soldticket = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, newId);
        statement.setInt(2, id);
        statement.executeUpdate();
        statement.close();
        con.close();
    }

    //get
    public ArrayList<SoldTicket> getTickets() throws SQLException {

        //return tickets;
        ArrayList<SoldTicket> t = new ArrayList<>();
        con = DriverManager.getConnection(url, username, password);
        String sql = "SELECT * FROM soldtickets";
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while(rs.next())
            t.add(new SoldTicket(rs.getInt("id_soldticket"), rs.getInt("id_client"), rs.getInt("id_ticket"), rs.getDouble("price_after_discount")));
        statement.close();
        con.close();
        return t;
    }

    public SoldTicket getTicketById(int id) throws SQLException {
        con = DriverManager.getConnection(url, username, password);
        String sql = "SELECT * FROM soldtickets WHERE id_soldticket = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();

        SoldTicket t;
        if(rs.next() == false)
            t = null;
        else t = new SoldTicket(rs.getInt("id_soldticket"), rs.getInt("id_client"), rs.getInt("id_ticket"), rs.getDouble("price_after_discount"));
        statement.close();
        con.close();

        return t;
    }

}
