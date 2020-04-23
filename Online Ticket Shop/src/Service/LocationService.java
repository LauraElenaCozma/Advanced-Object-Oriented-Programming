package Service;

import Model.Event;
import Model.Location;
import Model.TicketDetails;
import Repository.LocationRepository;
import Service.Audit.*;

import java.util.ArrayList;

public class LocationService {
    private LocationRepository locationRepository = new LocationRepository();
    private static LocationService instance = new LocationService();
    private static AuditService auditService = AuditService.getInstance();
    public static IOFileService<FileLocation, Location> ioFileService = IOFileService.getInstance();
    public static FileLocation fileLocation = FileLocation.getInstance();
    private LocationService() {
    }

    public static LocationService getInstance() {

        return instance;
    }

    //add
    public void addLocationInService(Location loc) {
        auditService.writeInAudit("Add location");
        ioFileService.appendInFile(fileLocation, loc, "locations.csv");
        locationRepository.addLocation(loc);
    }

    //remove
    public void removeLocationById(int id) {
        auditService.writeInAudit("Remove location by id");
        TicketDetailsService ticketDetailsService = TicketDetailsService.getInstance();
        SoldTicketService soldTicketService = SoldTicketService.getInstance();
        ArrayList<TicketDetails> array = ticketDetailsService.getTicketDetailsByIdLocation(id);

        for(TicketDetails t : array) {
            soldTicketService.removeTicketByTicketDetailsId(t.getIdTicket());
        }
        ticketDetailsService.removeTicketByLocationId(id);
        locationRepository.removeLocationById(id);
        ioFileService.updateFile(fileLocation, locationRepository.getLocations(), "locations.csv");

    }

    //find
    public ArrayList<Location> findLocationByName(String name) {
        auditService.writeInAudit("Find location by name");
        return locationRepository.findLocationByLocationName(name);
    }

    public ArrayList<Location> findLocationByCity(String city) {
        auditService.writeInAudit("Find location by city");
        return locationRepository.findLocationByCity(city);
    }

    //update
    public void updateLocationName(int id, String newName) {
        auditService.writeInAudit("Update location name");
        Location l = locationRepository.getLocationById(id);
        if(l == null)
            throw new IllegalArgumentException("No location having this id!");
        locationRepository.updateLocationName(id, newName);
        ioFileService.updateFile(fileLocation, locationRepository.getLocations(), "locations.csv");
    }

    public void updateLocationVenue(int id, String newVenue) {
        auditService.writeInAudit("Update location venue");
        Location l = locationRepository.getLocationById(id);
        if(l == null)
            throw new IllegalArgumentException("No location having this id!");
        locationRepository.updateLocationVenue(id, newVenue);
        ioFileService.updateFile(fileLocation, locationRepository.getLocations(), "locations.csv");
    }

    public void updateLocationCity(int id, String newCity) {
        auditService.writeInAudit("Update location city");
        locationRepository.updateLocationCity(id, newCity);
        ioFileService.updateFile(fileLocation, locationRepository.getLocations(), "locations.csv");
    }

    public void updateLocationCountry(int id, String newCountry) {
        auditService.writeInAudit("Update location contry");
        Location l = locationRepository.getLocationById(id);
        if(l == null)
            throw new IllegalArgumentException("No location having this id!");
        locationRepository.updateLocationCountry(id, newCountry);
        ioFileService.updateFile(fileLocation, locationRepository.getLocations(), "locations.csv");
    }


    //get
    public ArrayList<Location> getLocations() {
        auditService.writeInAudit("Get locations");
        return locationRepository.getLocations();
    }

    public Location getLocationById(int id) {
        auditService.writeInAudit("Get location by id");
        return locationRepository.getLocationById(id);
    }

    public LocationRepository getLocationRepository() {
        return locationRepository;
    }
}
