package Service.Audit;

import Model.Event;
import Repository.EventRepository;
import Service.EventService;

import java.io.BufferedWriter;
import java.io.IOException;

public class FileEvent implements FileInterface<Event> {
    public static FileEvent instance = new FileEvent();

    private FileEvent() {
    }

    public static FileEvent getInstance() {
        return instance;
    }
    public void print(BufferedWriter out, Event ob) throws IOException{
        out.write(ob.getIdEvent() + "," + ob.getName() + "," + ob.getDuration() + "," + ob.getPrice());
    }

    public void load(String line) {
        String[] data = line.split(",");
        int maxId = Event.getUniqueId();
        if(Integer.parseInt(data[0]) > maxId) {
            maxId = Integer.parseInt(data[0]);
        }
        Event.setUniqueId(maxId + 1); //the next available id

        EventService.getInstance().getEventRepository().addEvent(new Event(Integer.parseInt(data[0]), data[1], Integer.parseInt(data[2]), Double.parseDouble(data[3])));
    }

    public void writeHeader(BufferedWriter out) throws IOException{
        out.write("Id of event,Name of event,Duration of event, Price of event");
    }
}
