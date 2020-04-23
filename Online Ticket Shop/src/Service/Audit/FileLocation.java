package Service.Audit;

import Model.Location;
import Service.LocationService;

import java.io.BufferedWriter;
import java.io.IOException;

public class FileLocation implements FileInterface<Location> {
    public static FileLocation instance = new FileLocation();

    private FileLocation() {
    }

    public static FileLocation getInstance() {
        return instance;
    }
    public void print(BufferedWriter out, Location ob) throws IOException {
        out.write(Integer.toString(ob.getIdLocation()));
        out.write(",");
        out.write(ob.getVenue());
        out.write(",");
        out.write(ob.getCountry());
        out.write(",");
        out.write(ob.getCity());
        out.write(",");
        out.write(ob.getLocationName());
    }

    public void load(String line) {
        String[] data = line.split(",");
        int maxId = Location.getUniqueId();
        if(Integer.parseInt(data[0]) > maxId) {
            maxId = Integer.parseInt(data[0]);
        }
        Location.setUniqueId(maxId + 1); //the next available id
        LocationService.getInstance().getLocationRepository().addLocation(new Location(Integer.parseInt(data[0]), data[1], data[2], data[3], data[4]));
    }

    public void writeHeader(BufferedWriter out) throws IOException {
        out.write("Location id,Venue,Country,City,Location Name");
    }
}
