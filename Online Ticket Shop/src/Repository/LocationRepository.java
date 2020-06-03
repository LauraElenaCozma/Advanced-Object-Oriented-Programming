package Repository;

import Model.Location;
import java.sql.*;
import java.util.ArrayList;

public class LocationRepository {

    Connection con;

    public LocationRepository(Connection con) {

        this.con = con;
    }


    public void addLocation(Location location) throws SQLException {
        //add a new location in locations
        String sql = "INSERT INTO locations VALUES(NULL, ?, ?, ?, ?)";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1,location.getVenue());
        statement.setString(2, location.getCountry());
        statement.setString(3, location.getCity());
        statement.setString(4, location.getLocationName());
        statement.executeUpdate();
        statement.close();
    }

    //remove
    public void removeLocationById(int id) throws SQLException {
        //remove location with a given id
        String sql = "DELETE FROM locations WHERE id_location = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
        statement.close();
    }


    //find
    public ArrayList<Location> findLocationByLocationName(String name) throws SQLException {
        //find location that has name = name
        ArrayList<Location> loc = new ArrayList<>();
        String sql = "SELECT * FROM locations WHERE name = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, name);
        ResultSet rs = statement.executeQuery();
        while(rs.next())
            loc.add(new Location(rs.getInt("id_location"), rs.getString("venue"), rs.getString("country"), rs.getString("city"),rs.getString("name")));
        statement.close();
        return loc;
    }

    public ArrayList<Location> findLocationByCity(String city) throws SQLException {

        ArrayList<Location> loc = new ArrayList<>();
        String sql = "SELECT * FROM locations WHERE city = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, city);
        ResultSet rs = statement.executeQuery();
        while(rs.next())
            loc.add(new Location(rs.getInt("id_location"), rs.getString("venue"), rs.getString("country"), rs.getString("city"),rs.getString("name")));
        statement.close();
        return loc;
    }

    //update
    public void updateLocationName(int id, String newName) throws SQLException {
        //update the name of a location. The id location is given
        String sql = "UPDATE locations SET name = ? WHERE id_location = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, newName);
        statement.setInt(2, id);
        statement.executeUpdate();
        statement.close();
    }

    public void updateLocationVenue(int id, String newVenue) throws SQLException {
        //update the venue of a location. The id location is given
        String sql = "UPDATE locations SET venue = ? WHERE id_location = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, newVenue);
        statement.setInt(2, id);
        statement.executeUpdate();
        statement.close();
    }

    public void updateLocationCity(int id, String newCity) throws SQLException {
        //update the city of a location
        String sql = "UPDATE locations SET city = ? WHERE id_location = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, newCity);
        statement.setInt(2, id);
        statement.executeUpdate();
        statement.close();
    }

    public void updateLocationCountry(int id, String newCountry) throws SQLException {
        //update the country of a location
        String sql = "UPDATE locations SET country = ? WHERE id_location = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, newCountry);
        statement.setInt(2, id);
        statement.executeUpdate();
        statement.close();
    }

    public ArrayList<Location> getLocations() throws SQLException {

        //return locations;
        ArrayList<Location> loc = new ArrayList<>();
        String sql = "SELECT * FROM locations";
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while(rs.next())
            loc.add(new Location(rs.getInt("id_location"), rs.getString("venue"), rs.getString("country"), rs.getString("city"), rs.getString("name")));
        statement.close();
        return loc;
    }

    public Location getLocationById(int id) throws SQLException {

        String sql = "SELECT * FROM locations WHERE id_location = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();

        Location loc;
        if(rs.next() == false)
            loc = null;
        else loc = new Location(rs.getInt("id_location"), rs.getString("venue"), rs.getString("country"), rs.getString("city"), rs.getString("name"));
        statement.close();
        return loc;

    }

}
