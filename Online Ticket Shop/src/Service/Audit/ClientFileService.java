package Service.Audit;

import Model.*;
import Repository.ClientRepository;
import Repository.EventRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Set;

public class ClientFileService implements FileService {
    public static ClientFileService instance = new ClientFileService();
    private static ClientRepository clientRepository;

    private ClientFileService() {
    }

    public static ClientFileService getInstance(ClientRepository cRepo) {
        clientRepository = cRepo;
        return instance;
    }

    public void loadFromFile() {
        try{
            //create a file
            File f = new File("clients.csv");
            //if the file doesn't exist, we do not have info to load
            if(!f.exists())
                return;
            BufferedReader readCsv = new BufferedReader(new FileReader(f));
            int maxId = Client.getUniqueId();

            String line = readCsv.readLine(); //read the header
            while((line = readCsv.readLine()) != null) {
                String[] data = line.split(",");

                if(Integer.parseInt(data[1]) > maxId) {  //compute max id of clients
                    maxId = Integer.parseInt(data[1]);
                }
                switch (Integer.parseInt(data[0])) {
                    case 1:clientRepository.addClient(new Adult(Integer.parseInt(data[1]), data[2], data[3], data[4]));     //adult
                           break;//idclient, nume, email, nr telefon
                    case 2:clientRepository.addClient(new Child(Integer.parseInt(data[1]), data[2], data[3], data[4], Integer.parseInt(data[5])));     //child
                        break;//idclient, nume, email, nr telefon, age
                    case 3:clientRepository.addClient(new Student(Integer.parseInt(data[1]), data[2], data[3], data[4], data[5]));     //student
                        break;//idclient, nume, email, nr telefon, noLegit
                    case 4:clientRepository.addClient(new Pensioner(Integer.parseInt(data[1]), data[2], data[3], data[4]));     //pensioner
                        break;//idclient, nume, email, nr telefon
                }

            }
            Client.setUniqueId(maxId + 1);

        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void appendInFile(Client c) {
        //append a new event into events.csv
        try{
            boolean writeHeader = true;
            if(Files.exists(Paths.get("clients.csv"))) {
                writeHeader = false;
            }

            File f = new File("clients.csv");

            BufferedWriter writeCsv = new BufferedWriter(new FileWriter(f, true));

            if(writeHeader) {
                writeCsv.write("Tip, Id client, Nume, Email, Telefon,Varsta,Numar legitimatie");
                writeCsv.newLine();
            }
            String className = c.getClass().getSimpleName();
            String type = "0";
            switch (className) {
                case "Adult":type = "1";
                             break;
                case "Child":type = "2";
                             break;
                case "Student":type = "3";
                             break;
                case "Pensioner":type = "4";
                             break;
            }
            writeCsv.append(type);
            writeCsv.append(",");
            writeCsv.append(Integer.toString(c.getIdClient()));
            writeCsv.append(",");
            writeCsv.append(c.getName());
            writeCsv.append(",");
            writeCsv.append(c.getEmail());
            writeCsv.append(",");
            writeCsv.append(c.getPhoneNumber());
            writeCsv.append(",");

            if(className.equals("Child")) {
                Child ch = (Child)c;
                writeCsv.append(Integer.toString(ch.getAge()));
                writeCsv.append(",");
            }
            else if(className.equals("Student")) {
                writeCsv.append(",");
                Student s = (Student)c;
                writeCsv.append(s.getNoLegit());
                writeCsv.append(",");
            }
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
            File f = new File("clients.csv");
            BufferedWriter writeCsv = new BufferedWriter(new FileWriter(f));

            writeCsv.write("Tip, Id client, Nume, Email, Telefon,Varsta,Numar legitimatie");
            writeCsv.newLine();

            Set<Client> arr = clientRepository.getClients();
            for(Client c : arr)
            {
                String className = c.getClass().getSimpleName();
                String type = "0";
                if(className.equals("Adult")) {
                    type = "1";
                }
                else if(className.equals("Child")) {
                    type = "2";
                }
                else if(className.equals("Student")) {
                    type = "3";
                }
                else if(className.equals("Pensioner")) {
                    type = "4";
                }
                writeCsv.write(type);
                writeCsv.write(",");
                writeCsv.write(Integer.toString(c.getIdClient()));
                writeCsv.write(",");
                writeCsv.write(c.getName());
                writeCsv.write(",");
                writeCsv.write(c.getEmail());
                writeCsv.write(",");
                writeCsv.write(c.getPhoneNumber());
                writeCsv.write(",");

                if(className.equals("Child")) {
                    Child ch = (Child)c;
                    writeCsv.write(Integer.toString(ch.getAge()));
                    writeCsv.write(",");
                }
                else if(className.equals("Student")) {
                    writeCsv.write(",");
                    Student s = (Student)c;
                    writeCsv.write(s.getNoLegit());
                    writeCsv.write(",");
                }
                writeCsv.newLine();
                writeCsv.flush();
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
