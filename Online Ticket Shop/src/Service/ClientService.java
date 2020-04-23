package Service;

import Model.Client;
import Model.Event;
import Model.Location;
import Repository.ClientRepository;
import Service.Audit.*;

import java.util.ArrayList;
import java.util.Set;
import java.util.ArrayList;

public class ClientService {
    private ClientRepository clientRepository = new ClientRepository();
    private static ClientService instance = new ClientService();
    private static AuditService auditService = AuditService.getInstance();
    public static IOFileService<FileClient, Client> ioFileService = IOFileService.getInstance();
    public static FileClient fileClient = FileClient.getInstance();

    private ClientService() {
    }

    public static ClientService getInstance() {

        return instance;
    }

    public void addClient(Client c) {
        auditService.writeInAudit("Add client");
        clientRepository.addClient(c);
        ioFileService.appendInFile(fileClient, c, "clients.csv");
    }

    //remove
    public void removeClientByName(String name) {
        auditService.writeInAudit("Remove clients by name");
        SoldTicketService soldTicketService = SoldTicketService.getInstance();
        //remove clients with name = name
        //the sold tickets of the client are also removed
        ArrayList<Client> array = clientRepository.findClientByName(name);
        for(Client c : array) {
            soldTicketService.removeTicketByClientId(c.getIdClient());
        }
        clientRepository.removeClientByName(name);
        ioFileService.updateFile(fileClient, clientRepository.getClients(), "clients.csv");
    }

    public void removeClientById(int id) {
        auditService.writeInAudit("Remove client by id");
        SoldTicketService soldTicketService = SoldTicketService.getInstance();
        //remove clients with name = name
        //the sold tickets of the client are also removed
        clientRepository.removeClientById(id);
        soldTicketService.removeTicketByClientId(id);
        ioFileService.updateFile(fileClient, clientRepository.getClients(), "clients.csv");
    }

    //find
    public ArrayList<Client> findClientByName(String name) {
        auditService.writeInAudit("Find client by name");
        return clientRepository.findClientByName(name);
    }

    //update
    public void updateClientName(String name, String newName) {
        auditService.writeInAudit("Update client name");
        ArrayList<Client> c = clientRepository.findClientByName(name);
        if(c.size() == 0)
            throw new IllegalArgumentException("No client having this id!");
        clientRepository.updateClientName(name, newName);
        ioFileService.updateFile(fileClient, clientRepository.getClients(), "clients.csv");
    }

    public void updateClientName(int id, String newName) {
        auditService.writeInAudit("Update client name");
        Client c = clientRepository.getClientById(id);
        if(c == null)
            throw new IllegalArgumentException("No client having this id!");
        clientRepository.updateClientName(id, newName);
        ioFileService.updateFile(fileClient, clientRepository.getClients(), "clients.csv");
    }

    public void updateClientEmail(int id, String newEmail) {
        auditService.writeInAudit("Update client email");
        Client c = clientRepository.getClientById(id);
        if(c == null)
            throw new IllegalArgumentException("No client having this id!");
        clientRepository.updateClientEmail(id, newEmail);
        ioFileService.updateFile(fileClient, clientRepository.getClients(), "clients.csv");
    }

    public void updateClientPhone(int id, String newPhone) {
        auditService.writeInAudit("Update client phone");
        Client c = clientRepository.getClientById(id);
        if(c == null)
            throw new IllegalArgumentException("No client having this id!");
        clientRepository.updateClientPhone(id, newPhone);
        ioFileService.updateFile(fileClient, clientRepository.getClients(), "clients.csv");
    }

    //get
    public Set<Client> getClients() {
        auditService.writeInAudit("Get all clients");
        return clientRepository.getClients();
    }

    public Client getClientById(int id) {
        auditService.writeInAudit("Get client by id");
        return clientRepository.getClientById(id);
    }

    public ClientRepository getClientRepository() {
        return clientRepository;
    }
}
