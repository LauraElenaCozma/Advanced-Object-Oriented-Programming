package Service.Audit;

import Model.Event;
import Model.Location;
import Repository.EventRepository;
import Repository.LocationRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class LocationFileService implements FileService {
    public static LocationFileService instance = new LocationFileService();
    private static LocationRepository locationRepository;

    private LocationFileService() {
    }

    public static LocationFileService getInstance(LocationRepository locRepo) {
        locationRepository = locRepo;
        return instance;
    }

    public void loadFromFile() {
        try{
            //create a file
            File f = new File("locations.csv");
            //if the file doesn't exist, we do not have info to load
            if(!f.exists())
                return;
            BufferedReader readCsv = new BufferedReader(new FileReader(f));
            int maxId = Location.getUniqueId();

            String line = readCsv.readLine(); //read the header
            while((line = readCsv.readLine()) != null) {
                String[] data = line.split(",");

                if(Integer.parseInt(data[0]) > maxId) {
                    maxId = Integer.parseInt(data[0]);
                }
                locationRepository.addLocation(new Location(Integer.parseInt(data[0]), data[1], data[2], data[3], data[4]));
            }
            Location.setUniqueId(maxId + 1);

        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void appendInFile(Location loc) {
        //append a new event into events.csv
        try{
            boolean writeHeader = true;
            if(Files.exists(Paths.get("locations.csv"))) {
                writeHeader = false;
            }

            File f = new File("locations.csv");

            BufferedWriter writeCsv = new BufferedWriter(new FileWriter(f, true));

            if(writeHeader) {
                writeCsv.write("Location id,Venue,Country,City,Location Name");
                writeCsv.newLine();
            }
            writeCsv.append(Integer.toString(loc.getIdLocation()));
            writeCsv.append(",");
            writeCsv.append(loc.getVenue());
            writeCsv.append(",");
            writeCsv.append(loc.getCountry());
            writeCsv.append(",");
            writeCsv.append(loc.getCity());
            writeCsv.append(",");
            writeCsv.append(loc.getLocationName());
            writeCsv.append(",");
            writeCsv.newLine();
            writeCsv.flush();

        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void updateFile() {
        //upload data from locationRepository into file
        try{
            File f = new File("locations.csv");
            BufferedWriter writeCsv = new BufferedWriter(new FileWriter(f));

            writeCsv.write("Location id,Venue,Country,City,Location Name");
            writeCsv.newLine();

            ArrayList<Location> arr = locationRepository.getLocations();
            for(Location loc : arr)
            {
                writeCsv.write(Integer.toString(loc.getIdLocation()));
                writeCsv.write(",");
                writeCsv.append(loc.getVenue());
                writeCsv.append(",");
                writeCsv.append(loc.getCountry());
                writeCsv.append(",");
                writeCsv.append(loc.getCity());
                writeCsv.append(",");
                writeCsv.append(loc.getLocationName());
                writeCsv.append(",");
                writeCsv.newLine();
                writeCsv.flush();
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
