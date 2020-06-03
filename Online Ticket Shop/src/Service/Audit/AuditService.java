package Service.Audit;


import Model.Event;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.Date;

public class AuditService {

    public static AuditService instance = new AuditService();

    private AuditService() {
    }

    public static AuditService getInstance() {
        return instance;
    }

    public String getTimestamp() {
        Date date= new Date();
        //Time in Milliseconds
        long time = date.getTime();
        //Current Timestamp
        Timestamp ts = new Timestamp(time);
        return ts.toString();
    }

    public void writeInAudit(String action) {
        try{

            boolean writeHeader = true;
            if(Files.exists(Paths.get("audit.csv"))) {
                writeHeader = false;
            }
            //create a file
            File f = new File("audit.csv");
            BufferedWriter writeCsv = new BufferedWriter(new FileWriter(f, true)); //if the file exists, append


            if(writeHeader) {
                writeCsv.write("Thread Name,Action,Date");
                writeCsv.newLine();
            }
            writeCsv.append(action + "," + getTimestamp());
            writeCsv.newLine();
            writeCsv.flush();
        }

        catch(IOException e) {
            e.printStackTrace();
        }
    }

}
