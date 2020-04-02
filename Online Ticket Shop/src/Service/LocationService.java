package Service;

import Model.Location;
import Repository.LocationRepository;

import java.util.ArrayList;

public class LocationService {
    private LocationRepository locationRepository = new LocationRepository();
    private static LocationService instance = new LocationService();
    private LocationService() {
    }

    public static LocationService getInstance() {

        return instance;
    }

    //add
    public void addLocationInService(Location loc) {

        locationRepository.addLocation(loc);
    }

    //remove
    public void removeLocationById(int id, TicketDetailsService ticketDetailsService, SoldTicketService soldTicketService) {
        //remove the location with id = id, the tickets having that location and the sold tickets having that location
        locationRepository.removeLocationById(id);
        ticketDetailsService.removeTicketByLocationId(id, soldTicketService);
    }

    //find
    public ArrayList<Location> findLocationByName(String name) {
        return locationRepository.findLocationByLocationName(name);
    }

    public ArrayList<Location> findLocationByCity(String city) {

        return locationRepository.findLocationByCity(city);
    }

    //update
    public void updateLocationName(int id, String newName) {

        locationRepository.updateLocationName(id, newName);
    }

    public void updateLocationVenue(int id, String newVenue) {

        locationRepository.updateLocationVenue(id, newVenue);
    }

    public void updateLocationCity(int id, String newCity) {

        locationRepository.updateLocationCity(id, newCity);
    }

    public void updateLocationCountry(int id, String newCountry) {
        locationRepository.updateLocationCountry(id, newCountry);
    }


    //get
    public ArrayList<Location> getLocations() {

        return locationRepository.getLocations();
    }

    public Location getLocationById(int id) {

        return locationRepository.getLocationById(id);
    }
}
