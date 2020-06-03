package Repository;

import Model.SoldTicket;

import java.sql.*;
import java.util.ArrayList;



public class SoldTicketRepository {
    //repository for sold tickets
    Connection con;

    public SoldTicketRepository(Connection con) {

        this.con = con;
    }


    //add
    public int addTicket(SoldTicket ticket) throws SQLException {
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
        return id;
    }

    //remove
    public void removeSoldTicketById(int id) throws SQLException {
        //remove Ticket with a given id
        String sql = "DELETE FROM soldtickets WHERE id_soldticket = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
        statement.close();
    }


    public void removeSoldTicketByTicketDetailsId(int id) throws SQLException {
        //remove ticket with a given event
        String sql = "DELETE FROM soldtickets WHERE id_ticket = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
        statement.close();
    }


    public void removeSoldTicketByClientId(int id) throws SQLException {
        String sql = "DELETE FROM soldtickets WHERE id_client = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
        statement.close();
    }

    //find
    public ArrayList<SoldTicket> findSoldTicketByTicketDetailsId(int id) throws SQLException {
        ArrayList<SoldTicket> array = new ArrayList<>();
        String sql = "SELECT * FROM soldtickets WHERE id_ticket = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();
        while(rs.next())
            array.add(new SoldTicket(rs.getInt("id_soldticket"), rs.getInt("id_client"), rs.getInt("id_ticket"), rs.getDouble("price_after_discount")));
        statement.close();
        return array;
    }

    public ArrayList<SoldTicket> findSoldTicketByClientId(int id) throws SQLException {
        ArrayList<SoldTicket> array = new ArrayList<>();
        String sql = "SELECT * FROM soldtickets WHERE id_client = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();
        while(rs.next())
            array.add(new SoldTicket(rs.getInt("id_soldticket"), rs.getInt("id_client"), rs.getInt("id_ticket"), rs.getDouble("price_after_discount")));
        statement.close();
        return array;
    }

    //update

    public void updateSoldTicketDetails(int id, int newId) throws SQLException {
        String sql = "UPDATE soldtickets SET id_ticket = ? WHERE id_soldticket = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, newId);
        statement.setInt(2, id);
        statement.executeUpdate();
        statement.close();

    }

    public void updateSoldTicketClient(int id, int newId) throws SQLException {
        //update the clients of some tickets
        String sql = "UPDATE soldtickets SET id_client = ? WHERE id_soldticket = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, newId);
        statement.setInt(2, id);
        statement.executeUpdate();
        statement.close();
    }

    //get
    public ArrayList<SoldTicket> getTickets() throws SQLException {

        //return all tickets
        ArrayList<SoldTicket> t = new ArrayList<>();
        String sql = "SELECT * FROM soldtickets";
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while(rs.next())
            t.add(new SoldTicket(rs.getInt("id_soldticket"), rs.getInt("id_client"), rs.getInt("id_ticket"), rs.getDouble("price_after_discount")));
        statement.close();
        return t;
    }

    public SoldTicket getTicketById(int id) throws SQLException {
        String sql = "SELECT * FROM soldtickets WHERE id_soldticket = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();

        SoldTicket t;
        if(rs.next() == false)
            t = null;
        else t = new SoldTicket(rs.getInt("id_soldticket"), rs.getInt("id_client"), rs.getInt("id_ticket"), rs.getDouble("price_after_discount"));
        statement.close();

        return t;
    }

}
