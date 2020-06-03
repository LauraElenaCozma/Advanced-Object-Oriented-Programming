package Service;

import Model.Location;
import Repository.LocationRepository;
import Audit.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class LocationService {
    private LocationRepository locationRepository = null;
    private static LocationService instance;
    private static AuditService auditService = AuditService.getInstance();
    private LocationService(Connection connection) {
        locationRepository = new LocationRepository(connection);
    }

    public static LocationService getInstance(Connection connection) {

        if(instance == null) {
            instance = new LocationService(connection);
        }
        return instance;
    }

    //add
    public void addLocationInService(Location loc) throws SQLException {
        auditService.writeInAudit("Add location");
        locationRepository.addLocation(loc);
    }

    //remove
    public void removeLocationById(int id) throws SQLException {
        auditService.writeInAudit("Remove location by id");
        locationRepository.removeLocationById(id);

    }

    //find
    public ArrayList<Location> findLocationByName(String name) throws SQLException {
        auditService.writeInAudit("Find location by name");
        return locationRepository.findLocationByLocationName(name);
    }

    public ArrayList<Location> findLocationByCity(String city) throws SQLException {
        auditService.writeInAudit("Find location by city");
        return locationRepository.findLocationByCity(city);
    }

    //update
    public void updateLocationName(int id, String newName) throws SQLException {
        auditService.writeInAudit("Update location name");
        locationRepository.updateLocationName(id, newName);
    }

    public void updateLocationVenue(int id, String newVenue) throws SQLException {
        auditService.writeInAudit("Update location venue");
        locationRepository.updateLocationVenue(id, newVenue);
    }

    public void updateLocationCity(int id, String newCity) throws SQLException {
        auditService.writeInAudit("Update location city");
        locationRepository.updateLocationCity(id, newCity);
    }

    public void updateLocationCountry(int id, String newCountry) throws SQLException {
        auditService.writeInAudit("Update location contry");
        locationRepository.updateLocationCountry(id, newCountry);
    }


    //get
    public ArrayList<Location> getLocations() throws SQLException {
        auditService.writeInAudit("Get locations");
        return locationRepository.getLocations();
    }

    public Location getLocationById(int id) throws SQLException {
        auditService.writeInAudit("Get location by id");
        return locationRepository.getLocationById(id);
    }
}
