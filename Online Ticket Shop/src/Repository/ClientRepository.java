package Repository;

import Model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.util.ArrayList;

import static java.sql.Types.*;

public class ClientRepository{
    //repo for client
    private Set<Client> clients;
    String url = "jdbc:mysql://localhost:3306/ticketshop";
    String username = "root";
    String password = "1234";
    Connection con;

    public ClientRepository() {

        clients = new TreeSet<>(new ComparatorClients());
    }

    public void addClient(Client c) throws SQLException {
        //add an new Client
        con = DriverManager.getConnection(url, username, password);
        String sql = "INSERT INTO clients VALUES(NULL, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = con.prepareStatement(sql);
        String className = c.getClass().getSimpleName();

        statement.setString(1, className);
        statement.setString(2, c.getName());
        statement.setString(3, c.getEmail());
        statement.setString(4, c.getPhoneNumber());

        switch (className) {
            case "Adult":
            case "Pensioner":
                statement.setNull(5, INTEGER);
                statement.setNull(6, VARCHAR);
                break;
            case "Child":
                Child ch = (Child)c;
                statement.setInt(5, ch.getAge());
                statement.setNull(6, VARCHAR);
                break;
            case "Student":
                Student s = (Student)c;
                statement.setNull(5, INTEGER);
                statement.setString(6, s.getNoLegit());
                break;
        }

        statement.executeUpdate();
        statement.close();
        con.close();
    }

    //remove
    public void removeClientById(int id) throws SQLException {
        //remove a client by id
        con = DriverManager.getConnection(url, username, password);
        String sql = "DELETE FROM clients WHERE id_client = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
        statement.close();
        con.close();
    }

    public void removeClientByName(String name) throws SQLException {
        //remove a client by name
        con = DriverManager.getConnection(url, username, password);
        String sql = "DELETE FROM clients WHERE name = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, name);
        statement.executeUpdate();
        statement.close();
        con.close();
    }

    //find
    public ArrayList<Client> findClientByName(String name) throws SQLException {
        //ArrayList USED
        //can be 2 different clients with the same name

        ArrayList<Client> c= new ArrayList<>();
        con = DriverManager.getConnection(url, username, password);
        String sql = "SELECT * FROM clients WHERE UPPER(name) = UPPER(?)";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, name);
        ResultSet rs = statement.executeQuery();
        while(rs.next()) {
            switch(rs.getString("type")) {
                case "Adult":
                    c.add(new Adult(rs.getInt("id_client"), rs.getString("name"), rs.getString("email"), rs.getString("phone_number")));
                    break;
                case "Pensioner":
                    c.add(new Pensioner(rs.getInt("id_client"), rs.getString("name"), rs.getString("email"), rs.getString("phone_number")));
                    break;
                case "Child":
                    c.add(new Child(rs.getInt("id_client"), rs.getString("name"), rs.getString("email"), rs.getString("phone_number"), rs.getInt("age")));
                    break;
                case "Student":
                    c.add(new Student(rs.getInt("id_client"), rs.getString("name"), rs.getString("email"), rs.getString("phone_number"), rs.getString("no_legitimation")));
                    break;
            }
        }
        statement.close();
        con.close();
        return c;
    }

    //update
    public void updateClientName(String name, String newName) throws SQLException {
        //update a client name with a new name
        con = DriverManager.getConnection(url, username, password);
        String sql = "UPDATE clients SET name = ? WHERE name = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, newName);
        statement.setString(2, name);
        statement.executeUpdate();
        statement.close();
        con.close();
    }

    public void updateClientName(int id, String newName) throws SQLException {
        //update a client name with a new name. Client id is given here
        con = DriverManager.getConnection(url, username, password);
        String sql = "UPDATE clients SET name = ? WHERE id_client = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, newName);
        statement.setInt(2, id);
        statement.executeUpdate();
        statement.close();
        con.close();
    }

    public void updateClientEmail(int id, String newEmail) throws SQLException {
        //update client email of a client(specified by id)
        con = DriverManager.getConnection(url, username, password);
        String sql = "UPDATE clients SET email = ? WHERE id_client = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, newEmail);
        statement.setInt(2, id);
        statement.executeUpdate();
        statement.close();
        con.close();
    }

    public void updateClientPhone(int id, String newPhone) throws SQLException {
        //update client phone number
        con = DriverManager.getConnection(url, username, password);
        String sql = "UPDATE clients SET phone_number = ? WHERE id_client = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, newPhone);
        statement.setInt(2, id);
        statement.executeUpdate();
        statement.close();
        con.close();
    }

    public Set<Client> getClients() throws SQLException {
        //return all the elements in client
        //return clients;
        Set<Client> c = new TreeSet<>(new ComparatorClients());
        con = DriverManager.getConnection(url, username, password);
        String sql = "SELECT * FROM clients";
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while(rs.next())
            switch(rs.getString("type")) {
                case "Adult":
                    c.add(new Adult(rs.getInt("id_client"), rs.getString("name"), rs.getString("email"), rs.getString("phone_number")));
                    break;
                case "Pensioner":
                    c.add(new Pensioner(rs.getInt("id_client"), rs.getString("name"), rs.getString("email"), rs.getString("phone_number")));
                    break;
                case "Child":
                    c.add(new Child(rs.getInt("id_client"), rs.getString("name"), rs.getString("email"), rs.getString("phone_number"), rs.getInt("age")));
                    break;
                case "Student":
                    c.add(new Student(rs.getInt("id_client"), rs.getString("name"), rs.getString("email"), rs.getString("phone_number"), rs.getString("no_legitimation")));
                    break;
            }
        statement.close();
        con.close();
        return c;
    }

    public Client getClientById(int id) throws SQLException {
        //return client having id = id
        con = DriverManager.getConnection(url, username, password);
        String sql = "SELECT * FROM clients WHERE id_client = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();

        Client c;
        if(rs.next() == false)
            c = null;
        else {
            switch(rs.getString("type")) {
                case "Pensioner":
                    c = new Pensioner(rs.getInt("id_client"), rs.getString("name"), rs.getString("email"), rs.getString("phone_number"));
                    break;
                case "Child":
                    c = new Child(rs.getInt("id_client"), rs.getString("name"), rs.getString("email"), rs.getString("phone_number"), rs.getInt("age"));
                    break;
                case "Student":
                    c = new Student(rs.getInt("id_client"), rs.getString("name"), rs.getString("email"), rs.getString("phone_number"), rs.getString("no_legitimation"));
                    break;
                default:
                    c = new Adult(rs.getInt("id_client"), rs.getString("name"), rs.getString("email"), rs.getString("phone_number"));
                    break;      //nu am mai pus caz cu adult ca sa fim siguri ca e initializata variabila c
            }
        }
        statement.close();
        con.close();

        return c;
    }

}
