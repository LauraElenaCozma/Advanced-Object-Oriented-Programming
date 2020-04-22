package Service.Audit;

import Model.Event;
import Repository.EventRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class EventFileService implements FileService{
    public static EventFileService instance = new EventFileService();
    private static EventRepository eventRepository;

    private EventFileService() {
    }

    public static EventFileService getInstance(EventRepository evRepo) {
        eventRepository = evRepo;
        return instance;
    }

    public void loadFromFile() {
        try{
            //create a file
            File f = new File("events.csv");
            //if the file doesn't exist, we do not have info to load
            if(!f.exists())
                return;
            BufferedReader readCsv = new BufferedReader(new FileReader(f));
            int maxId = Event.getUniqueId();
            String line = readCsv.readLine(); //read the header
            while((line = readCsv.readLine()) != null) {
                String[] data = line.split(",");
                if(Integer.parseInt(data[0]) > maxId) {
                    maxId = Integer.parseInt(data[0]);
                }
                eventRepository.addEvent(new Event(Integer.parseInt(data[0]), data[1], Integer.parseInt(data[2]), Double.parseDouble(data[3]))); //name, duration, price
            }
            Event.setUniqueId(maxId + 1);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void appendInFile(Event e) {
        //append a new event into events.csv
        try{
            boolean writeHeader = true;
            if(Files.exists(Paths.get("events.csv"))) {
                writeHeader = false;
            }

            File f = new File("events.csv");

            BufferedWriter writeCsv = new BufferedWriter(new FileWriter(f, true));

            if(writeHeader) {
                writeCsv.write("Id of event,Name of event,Duration of event, Price of event");
                writeCsv.newLine();
            }
            writeCsv.append(Integer.toString(e.getIdEvent()));
            writeCsv.append(",");
            writeCsv.append(e.getName());
            writeCsv.append(",");
            writeCsv.append(Integer.toString(e.getDuration()));
            writeCsv.append(",");
            writeCsv.append(Double.toString(e.getPrice()));
            writeCsv.append(",");
            writeCsv.newLine();
            writeCsv.flush();

        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void updateFile() {
        //upload data from eventRepository into file
        try{
            File f = new File("events.csv");
            BufferedWriter writeCsv = new BufferedWriter(new FileWriter(f));

            writeCsv.write("Id of event,Name of event,Duration of event, Price of event");
            writeCsv.newLine();

            ArrayList<Event> arr = eventRepository.getEvents();
            for(Event event : arr)
            {
                writeCsv.write(Integer.toString(event.getIdEvent()));
                writeCsv.write(",");
                writeCsv.write(event.getName());
                writeCsv.write(",");
                writeCsv.write(Integer.toString(event.getDuration()));
                writeCsv.write(",");
                writeCsv.write(Double.toString(event.getPrice()));
                writeCsv.write(",");
                writeCsv.newLine();
                writeCsv.flush();
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
