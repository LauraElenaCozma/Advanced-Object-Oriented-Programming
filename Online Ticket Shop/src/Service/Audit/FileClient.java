package Service.Audit;



import Model.*;
import Service.ClientService;

import java.io.BufferedWriter;
import java.io.IOException;

public class FileClient implements FileInterface<Client> {
    public static FileClient instance = new FileClient();

    private FileClient() {
    }

    public static FileClient getInstance() {
        return instance;
    }
    public void print(BufferedWriter out, Client ob) throws IOException {
        String className = ob.getClass().getSimpleName();
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
        out.append(type);
        out.append(",");
        out.append(Integer.toString(ob.getIdClient()));
        out.append(",");
        out.append(ob.getName());
        out.append(",");
        out.append(ob.getEmail());
        out.append(",");
        out.append(ob.getPhoneNumber());
        out.append(",");

        if(className.equals("Child")) {
            Child ch = (Child)ob;
            out.append(Integer.toString(ch.getAge()));
            out.append(",");
        }
        else if(className.equals("Student")) {
            out.append(",");
            Student s = (Student)ob;
            out.append(s.getNoLegit());
        }
        else {
            out.append(",");
        }
    }

    public void load(String line) {
        String[] data = line.split(",");

        int maxId = Client.getUniqueId();
        if(Integer.parseInt(data[1]) > maxId) {  //compute max id of clients
            maxId = Integer.parseInt(data[1]);
        }
        Client.setUniqueId(maxId + 1);

        switch (Integer.parseInt(data[0])) {
            case 1:ClientService.getInstance().getClientRepository().addClient(new Adult(Integer.parseInt(data[1]), data[2], data[3], data[4]));     //adult
                    break;//idclient, nume, email, nr telefon
            case 2:ClientService.getInstance().getClientRepository().addClient(new Child(Integer.parseInt(data[1]), data[2], data[3], data[4], Integer.parseInt(data[5])));     //child
                    break;//idclient, nume, email, nr telefon, age
            case 3: ClientService.getInstance().getClientRepository().addClient(new Student(Integer.parseInt(data[1]), data[2], data[3], data[4], data[5]));     //student
                    break;//idclient, nume, email, nr telefon, noLegit
            case 4:ClientService.getInstance().getClientRepository().addClient(new Pensioner(Integer.parseInt(data[1]), data[2], data[3], data[4]));     //pensioner
                    break;//idclient, nume, email, nr telefon
            }
    }


    public void writeHeader(BufferedWriter out) throws IOException {
        out.write("Tip, Id client, Nume, Email, Telefon,Varsta,Numar legitimatie");
    }
}
