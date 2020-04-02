package Repository;

import Model.Client;
import Model.Event;
import Model.SoldTicket;
import Service.SoldTicketService;

import java.util.ArrayList;
import java.util.Vector;

public class ClientRepository{
    //repo for client
    ArrayList<Client> clients;

    public ClientRepository() {

        clients = new ArrayList<>();
    }

    public ClientRepository(ArrayList<Client> clients) {

        this.clients = new ArrayList<>(clients);
    }

    public void addClient(Client c){
        //add an new Client
        clients.add(c);
    }

    //remove
    public void removeClientById(int id) {
        //remove a client by id
        for(int i = 0 ; i < clients.size() ; i++) {
            if(clients.get(i).getIdClient() == id) {
                clients.remove(i);
                i--;
            }
        }
    }

    public void removeClientByName(String name) {
        //remove a client by name
        for(int i = 0 ; i < clients.size() ; i++) {
            if(clients.get(i).getName().equals(name) == true) {
                clients.remove(i);
                i--;
            }
        }
    }

    //find
    public Vector<Client> findClientByName(String name) {
        //VECTOR USED
        //can be 2 different clients with the same name
        Vector<Client> c= new Vector<>();
        for(Client cl : clients) {
            if(cl.getName().equals(name) == true) {
                c.add(cl);
            }
        }
        return c;
    }

    //update
    public void updateClientName(String name, String newName) {
        //update a client name with a new name
        for(Client c : clients) {
            if(c.getName().equals(name) == true) {
                c.setName(newName);
            }
        }
    }

    public void updateClientName(int id, String newName) {
        //update a client name with a new name. Client id is given here
        for(Client c : clients) {
            if(c.getIdClient() == id) {
                c.setName(newName);
            }
        }
    }

    public void updateClientEmail(int id, String newEmail) {
        //update client email of a client(specified by id)
        for(Client c : clients) {
            if(c.getIdClient() == id) {
                c.setEmail(newEmail);
            }
        }
    }

    public void updateClientPhone(int id, String newPhone) {
        //update client phone number
        for(Client c : clients) {
            if(c.getIdClient() == id) {
                c.setPhoneNumber(newPhone);
            }
        }
    }

    public ArrayList<Client> getClients() {
        //return all the elements in client
        return clients;
    }

    public Client getClientById(int id) {
        //return client having id = id
        for(Client c : clients) {
            if(c.getIdClient() == id) {
                return c;
            }
        }
        return null;
    }

}
