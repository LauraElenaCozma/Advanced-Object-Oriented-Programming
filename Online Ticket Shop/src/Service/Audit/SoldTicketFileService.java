package Service.Audit;

import Model.Date;
import Model.SoldTicket;
import Model.TicketDetails;
import Repository.SoldTicketRepository;
import Repository.TicketDetailsRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class SoldTicketFileService implements FileService {
    public static SoldTicketFileService instance = new SoldTicketFileService();
    private static SoldTicketRepository soldTicketRepository;

    private SoldTicketFileService() {
    }

    public static SoldTicketFileService getInstance(SoldTicketRepository stRepo) {
        soldTicketRepository = stRepo;
        return instance;
    }

    public void loadFromFile() {
        //update the repos with the file content
        try{
            //create a file
            File f = new File("soldTickets.csv");
            //if the file doesn't exist, we do not have info to load
            if(!f.exists())
                return;
            BufferedReader readCsv = new BufferedReader(new FileReader(f));

            int maxId = SoldTicket.getUniqueKey();

            String line = readCsv.readLine(); //read the header
            while((line = readCsv.readLine()) != null) {
                String[] data = line.split(",");

                if(Integer.parseInt(data[0]) > maxId) {
                    maxId = Integer.parseInt(data[0]);
                }
                soldTicketRepository.addTicket(new SoldTicket(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2]), Double.parseDouble(data[3])));
            }

            SoldTicket.setUniqueKey(maxId + 1);

        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void appendInFile(SoldTicket t) {
        //append a new event into events.csv
        try{
            boolean writeHeader = true;
            if(Files.exists(Paths.get("soldTickets.csv"))) {
                writeHeader = false;
            }

            File f = new File("soldTickets.csv");

            BufferedWriter writeCsv = new BufferedWriter(new FileWriter(f, true));

            if(writeHeader) {
                writeCsv.write("Ticket id,Client id,TicketDetails id,Price");
                writeCsv.newLine();
            }

            writeCsv.append(Integer.toString(t.getIdTicket()));
            writeCsv.append(",");
            writeCsv.append(Integer.toString(t.getIdClient()));
            writeCsv.append(",");
            writeCsv.append(Integer.toString(t.getIdTicketDetails()));
            writeCsv.append(",");
            writeCsv.append(Double.toString(t.getPriceAfterDiscount()));
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

            File f = new File("soldTickets.csv");

            BufferedWriter writeCsv = new BufferedWriter(new FileWriter(f));
            writeCsv.write("Ticket id,Client id,TicketDetails id,Price");
            writeCsv.newLine();
            ArrayList<SoldTicket> arr = soldTicketRepository.getTickets();
            for(SoldTicket t : arr)
            {
                writeCsv.append(Integer.toString(t.getIdTicket()));
                writeCsv.append(",");
                writeCsv.append(Integer.toString(t.getIdClient()));
                writeCsv.append(",");
                writeCsv.append(Integer.toString(t.getIdTicketDetails()));
                writeCsv.append(",");
                writeCsv.append(Double.toString(t.getPriceAfterDiscount()));
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
