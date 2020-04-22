package Service;

import Model.Location;
import Model.TicketDetails;
import Repository.LocationRepository;
import Service.Audit.AuditService;
import Service.Audit.EventFileService;
import Service.Audit.LocationFileService;

import java.util.ArrayList;

public class LocationService {
    private LocationRepository locationRepository = new LocationRepository();
    private static LocationService instance = new LocationService();
    private static AuditService auditService = AuditService.getInstance();
    private LocationFileService locationFileService = LocationFileService.getInstance(locationRepository);

    private LocationService() {
    }

    public static LocationService getInstance() {

        return instance;
    }

    //add
    public void addLocationInService(Location loc) {
        auditService.writeInAudit("Add location");
        locationFileService.appendInFile(loc);
        locationRepository.addLocation(loc);
    }

    //remove
    public void removeLocationById(int id) {
        auditService.writeInAudit("Remove location by id");
        TicketDetailsService ticketDetailsService = TicketDetailsService.getInstance();
        SoldTicketService soldTicketService = SoldTicketService.getInstance();
        //remove the location with id = id, the tickets having that location and the sold tickets having that location
        ArrayList<TicketDetails> array = ticketDetailsService.getTicketDetailsByIdLocation(id);

        for(TicketDetails t : array) {
            soldTicketService.removeTicketByTicketDetailsId(t.getIdTicket());
        }
        ticketDetailsService.removeTicketByLocationId(id);
        locationRepository.removeLocationById(id);
        locationFileService.updateFile();
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
        locationFileService.updateFile();
    }

    public void updateLocationVenue(int id, String newVenue) {
        auditService.writeInAudit("Update location venue");
        Location l = locationRepository.getLocationById(id);
        if(l == null)
            throw new IllegalArgumentException("No location having this id!");
        locationRepository.updateLocationVenue(id, newVenue);
        locationFileService.updateFile();
    }

    public void updateLocationCity(int id, String newCity) {
        auditService.writeInAudit("Update location city");
        locationRepository.updateLocationCity(id, newCity);
        locationFileService.updateFile();
    }

    public void updateLocationCountry(int id, String newCountry) {
        auditService.writeInAudit("Update location contry");
        Location l = locationRepository.getLocationById(id);
        if(l == null)
            throw new IllegalArgumentException("No location having this id!");
        locationRepository.updateLocationCountry(id, newCountry);
        locationFileService.updateFile();
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
