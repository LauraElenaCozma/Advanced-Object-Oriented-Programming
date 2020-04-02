package Service;

import Model.Client;
import Model.SoldTicket;
import Repository.ClientRepository;
import Repository.EventRepository;

import java.util.ArrayList;
import java.util.Vector;

public class ClientService {
    private ClientRepository clientRepository = new ClientRepository();
    private static ClientService instance = new ClientService();
    private ClientService() {
    }

    public static ClientService getInstance() {

        return instance;
    }

    public void addClient(Client c) {

        clientRepository.addClient(c);
    }

    //remove
    public void removeClientByName(String name, SoldTicketService soldTicketService) {
        //remove clients with name = name
        //the sold tickets of the client are also removed
        clientRepository.removeClientByName(name);
        soldTicketService.removeTicketByClientName(name);
    }

    public void removeClientById(int id, SoldTicketService soldTicketService) {
        //remove clients with name = name
        //the sold tickets of the client are also removed
        clientRepository.removeClientById(id);
        soldTicketService.removeTicketByClientId(id);
    }

    //find
    public Vector<Client> findClientByName(String name) {

        return clientRepository.findClientByName(name);
    }

    //update
    public void updateClientName(String name, String newName) {

        clientRepository.updateClientName(name, newName);
    }

    public void updateClientName(int id, String newName) {

        clientRepository.updateClientName(id, newName);
    }

    public void updateClientEmail(int id, String newEmail) {

        clientRepository.updateClientEmail(id, newEmail);
    }

    public void updateClientPhone(int id, String newPhone) {

        clientRepository.updateClientPhone(id, newPhone);
    }

    //get
    public ArrayList<Client> getClients() {

        return clientRepository.getClients();
    }

    public Client getClientById(int id) {

        return clientRepository.getClientById(id);
    }
}
