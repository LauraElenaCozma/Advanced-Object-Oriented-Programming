package Service;

import Model.Event;
import Model.Location;
import Model.TicketDetails;
import Repository.LocationRepository;
import Service.Audit.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class LocationService {
    private LocationRepository locationRepository = new LocationRepository();
    private static LocationService instance = new LocationService();
    private static AuditService auditService = AuditService.getInstance();
    private LocationService() {
    }

    public static LocationService getInstance() {

        return instance;
    }

    //add
    public void addLocationInService(Location loc) throws SQLException {
        auditService.writeInAudit(Thread.currentThread().getName() + ",Add location");
        locationRepository.addLocation(loc);
    }

    //remove
    public void removeLocationById(int id) throws SQLException {
        auditService.writeInAudit(Thread.currentThread().getName() + ",Remove location by id");
        TicketDetailsService ticketDetailsService = TicketDetailsService.getInstance();
        SoldTicketService soldTicketService = SoldTicketService.getInstance();
        ArrayList<TicketDetails> array = ticketDetailsService.getTicketDetailsByIdLocation(id);

        for(TicketDetails t : array) {
            soldTicketService.removeTicketByTicketDetailsId(t.getIdTicket());
        }
        ticketDetailsService.removeTicketByLocationId(id);
        locationRepository.removeLocationById(id);

    }

    //find
    public ArrayList<Location> findLocationByName(String name) throws SQLException {
        auditService.writeInAudit(Thread.currentThread().getName() + ",Find location by name");
        return locationRepository.findLocationByLocationName(name);
    }

    public ArrayList<Location> findLocationByCity(String city) throws SQLException {
        auditService.writeInAudit(Thread.currentThread().getName() + ",Find location by city");
        return locationRepository.findLocationByCity(city);
    }

    //update
    public void updateLocationName(int id, String newName) throws SQLException {
        auditService.writeInAudit(Thread.currentThread().getName() + ",Update location name");
        Location l = locationRepository.getLocationById(id);
        if(l == null)
            throw new IllegalArgumentException("No location having this id!");
        locationRepository.updateLocationName(id, newName);
    }

    public void updateLocationVenue(int id, String newVenue) throws SQLException {
        auditService.writeInAudit(Thread.currentThread().getName() + ",Update location venue");
        Location l = locationRepository.getLocationById(id);
        if(l == null)
            throw new IllegalArgumentException("No location having this id!");
        locationRepository.updateLocationVenue(id, newVenue);
    }

    public void updateLocationCity(int id, String newCity) throws SQLException {
        auditService.writeInAudit(Thread.currentThread().getName() + ",Update location city");
        locationRepository.updateLocationCity(id, newCity);
    }

    public void updateLocationCountry(int id, String newCountry) throws SQLException {
        auditService.writeInAudit(Thread.currentThread().getName() + ",Update location contry");
        Location l = locationRepository.getLocationById(id);
        if(l == null)
            throw new IllegalArgumentException("No location having this id!");
        locationRepository.updateLocationCountry(id, newCountry);
    }


    //get
    public ArrayList<Location> getLocations() throws SQLException {
        auditService.writeInAudit(Thread.currentThread().getName() + ",Get locations");
        return locationRepository.getLocations();
    }

    public Location getLocationById(int id) throws SQLException {
        auditService.writeInAudit("Get location by id");
        return locationRepository.getLocationById(id);
    }

    public LocationRepository getLocationRepository() {
        return locationRepository;
    }
}
