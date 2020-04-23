package Service.Audit;

import Model.Event;
import Repository.EventRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Set;

public class IOFileService <T extends FileInterface, P>{
    public static IOFileService instance = new IOFileService();
    private IOFileService() {
    }

    public static IOFileService getInstance() {
        return instance;
    }

    public void loadFromFile(T ob, String fileName) {
        //load from fileName to the repo of T objects
        try{
            //create a file
            File f = new File(fileName);
            //if the file doesn't exist, we do not have info to load
            if(!f.exists())
                return;
            BufferedReader readCsv = new BufferedReader(new FileReader(f));
            String line = readCsv.readLine(); //read the header
            while((line = readCsv.readLine()) != null) {
                ob.load(line);
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void appendInFile(T ob, P newOb, String fileName) {
        //append a new event into fileName
        try{
            boolean writeHeader = true;
            if(Files.exists(Paths.get(fileName))) {
                writeHeader = false;
            }

            File f = new File(fileName);

            BufferedWriter writeCsv = new BufferedWriter(new FileWriter(f, true));

            if(writeHeader) {
                ob.writeHeader(writeCsv);
                writeCsv.newLine();
            }
            ob.print(writeCsv, newOb);
            writeCsv.newLine();
            writeCsv.flush();

        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void updateFile(T ob, ArrayList<P> repo, String fileName) {
        //copy the content from repo to fileName
        try{
            File f = new File(fileName);
            BufferedWriter writeCsv = new BufferedWriter(new FileWriter(f));

            ob.writeHeader(writeCsv);
            writeCsv.newLine();


            for(P p : repo)
            {
                ob.print(writeCsv, p); //write every object from repo
                writeCsv.newLine();
                writeCsv.flush();
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateFile(T ob, Set<P> repo, String fileName) {
        //copy the content from repo to fileName
        try{
            File f = new File(fileName);
            BufferedWriter writeCsv = new BufferedWriter(new FileWriter(f));

            ob.writeHeader(writeCsv);
            writeCsv.newLine();


            for(P p : repo)
            {
                ob.print(writeCsv, p); //write every object from repo
                writeCsv.newLine();
                writeCsv.flush();
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
