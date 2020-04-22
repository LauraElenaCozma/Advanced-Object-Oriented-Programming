package Repository;

import Model.Location;

import java.util.ArrayList;

public class LocationRepository {
    private ArrayList<Location> locations;

    public LocationRepository() {

        locations = new ArrayList<>();
    }

    public LocationRepository(ArrayList<Location> locations) {

        this.locations = new ArrayList<>(locations);
    }

    public void addLocation(Location location) {
        //add a new location in locations
        locations.add(location);
    }

    //remove
    public void removeLocationById(int id){
        //remove location with a given id
        int index = -1;
        for(int i = 0 ; i < locations.size() ; i++){
            if(locations.get(i).getIdLocation() == id) {
                index = i;
                break;
            }
        }

        if(index == -1) {
            System.out.println("There is no location with this id");
        }
        else {
            locations.remove(index);

        }

    }


    //find
    public ArrayList<Location> findLocationByLocationName(String name) {
        //find location that has name = name
        ArrayList<Location> indexes = new ArrayList<>();
        for(int i = 0 ; i < locations.size() ; i++) {
            if(locations.get(i).getLocationName().equals(name) == true) {
                indexes.add(locations.get(i));
            }
        }
        return indexes;
    }

    public ArrayList<Location> findLocationByCity(String city) {

        ArrayList<Location> indexes = new ArrayList<>();
        for(int i = 0 ; i < locations.size() ; i++) {
            if(locations.get(i).getCity().equals(city) == true) {
                indexes.add(locations.get(i));
            }
        }
        return indexes;
    }

    //update
    public void updateLocationName(int id, String newName) {
        //update the name of a location. The id location is given
        for(int i = 0 ; i < locations.size() ; i++) {
            if(locations.get(i).getIdLocation() == id) {
                locations.get(i).setLocationName(newName);
            }
        }
    }
    public void updateLocationVenue(int id, String newVenue) {
        //update the venue of a location. The id location is given
        for(int i = 0 ; i < locations.size() ; i++) {
            if(locations.get(i).getIdLocation() == id) {
                locations.get(i).setVenue(newVenue);
            }
        }
    }

    public void updateLocationCity(int id, String newCity) {
        //update the city of a location
        for(int i = 0 ; i < locations.size() ; i++) {
            if(locations.get(i).getIdLocation() == id) {
                locations.get(i).setCity(newCity);
            }
        }
    }

    public void updateLocationCountry(int id, String newCountry) {
        //update the country of a location
        for(int i = 0 ; i < locations.size() ; i++) {
            if(locations.get(i).getIdLocation() == id) {
                locations.get(i).setCountry(newCountry);
            }
        }
    }

    public ArrayList<Location> getLocations() {

        return locations;
    }

    public Location getLocationById(int id) {
        for(int i = 0 ; i < locations.size() ; i++) {
            if (locations.get(i).getIdLocation() == id) {
                return locations.get(i);
            }
        }
        return null;
    }
}
