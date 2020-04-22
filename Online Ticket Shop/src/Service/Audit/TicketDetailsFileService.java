package Service.Audit;

import Model.Date;
import Model.Event;
import Model.Location;
import Model.TicketDetails;
import Repository.EventRepository;
import Repository.TicketDetailsRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class TicketDetailsFileService implements FileService {
    public static TicketDetailsFileService instance = new TicketDetailsFileService();
    private static TicketDetailsRepository ticketDetailsRepository;

    private TicketDetailsFileService() {
    }

    public static TicketDetailsFileService getInstance(TicketDetailsRepository tdRepo) {
        ticketDetailsRepository = tdRepo;
        return instance;
    }

    public void loadFromFile() {
        //update the repos with the file content
        try{
            //create a file
            File f = new File("detailsOfTickets.csv");
            //if the file doesn't exist, we do not have info to load
            if(!f.exists())
                return;
            BufferedReader readCsv = new BufferedReader(new FileReader(f));

            int maxId = TicketDetails.getUniqueKey();

            String line = readCsv.readLine(); //read the header

            while((line = readCsv.readLine()) != null) {
                String[] data = line.split(",");

                if(Integer.parseInt(data[0]) > maxId) {
                    maxId = Integer.parseInt(data[0]);
                }
                String[] date = data[3].split("/");
                ticketDetailsRepository.addTicket(new TicketDetails(Integer.parseInt(data[0]), //idTicket
                        Integer.parseInt(data[1]), //idEvent
                        Integer.parseInt(data[2]), //idLocation
                        new Date(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2])),
                        data[4])); //idEvent, idLocation, date, hour
            }

            TicketDetails.setUniqueKey(maxId + 1);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void appendInFile(TicketDetails t) {
        //append a new event into events.csv
        try{
            boolean writeHeader = true;
            if(Files.exists(Paths.get("detailsOfTickets.csv"))) {
                writeHeader = false;
            }

            File f = new File("detailsOfTickets.csv");

            BufferedWriter writeCsv = new BufferedWriter(new FileWriter(f, true));

            if(writeHeader) {
                writeCsv.write("Ticket id,Event id,Location id,Date,Hour");
                writeCsv.newLine();
            }

            writeCsv.append(Integer.toString(t.getIdTicket()));
            writeCsv.append(",");
            writeCsv.append(Integer.toString(t.getIdEvent()));
            writeCsv.append(",");
            writeCsv.append(Integer.toString(t.getIdLocation()));
            writeCsv.append(",");
            writeCsv.append(t.getDate().getDay() + "/" + t.getDate().getMonth() + "/" + t.getDate().getYear());
            writeCsv.append(",");
            writeCsv.append(t.getHour());
            writeCsv.append(",");
            writeCsv.newLine();
            writeCsv.flush();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateFile() {
        //upload data from eventRepository into file
        try{

            File f = new File("detailsOfTickets.csv");

            BufferedWriter writeCsv = new BufferedWriter(new FileWriter(f));
            writeCsv.write("Ticket id,Event id,Location id,Date,Hour");
            writeCsv.newLine();
            ArrayList<TicketDetails> arr = ticketDetailsRepository.getTickets();
            for(TicketDetails event : arr)
            {
                writeCsv.append(Integer.toString(event.getIdTicket()) + ",");
                writeCsv.append(Integer.toString(event.getIdEvent()) + ",");
                writeCsv.append(Integer.toString(event.getIdLocation()) + ",");
                writeCsv.append(event.getDate().getDay() + "/" + event.getDate().getMonth() + "/" + event.getDate().getYear() + ",");
                writeCsv.append(event.getHour() + ",");
                writeCsv.newLine();
                writeCsv.flush();
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
