package Service;

import Model.Client;
import Model.Location;
import Repository.ClientRepository;
import Service.Audit.AuditService;
import Service.Audit.ClientFileService;
import Service.Audit.EventFileService;

import java.util.ArrayList;
import java.util.Set;
import java.util.Vector;

public class ClientService {
    private ClientRepository clientRepository = new ClientRepository();
    private static ClientService instance = new ClientService();
    private static AuditService auditService = AuditService.getInstance();
    private ClientFileService clientFileService = ClientFileService.getInstance(clientRepository);

    private ClientService() {
    }

    public static ClientService getInstance() {

        return instance;
    }

    public void addClient(Client c) {
        auditService.writeInAudit("Add client");
        clientRepository.addClient(c);
        clientFileService.appendInFile(c);
    }

    //remove
    public void removeClientByName(String name) {
        auditService.writeInAudit("Remove clients by name");
        SoldTicketService soldTicketService = SoldTicketService.getInstance();
        //remove clients with name = name
        //the sold tickets of the client are also removed
        Vector<Client> array = clientRepository.findClientByName(name);
        for(Client c : array) {
            soldTicketService.removeTicketByClientId(c.getIdClient());
        }
        clientRepository.removeClientByName(name);
        clientFileService.updateFile();
    }

    public void removeClientById(int id) {
        auditService.writeInAudit("Remove client by id");
        SoldTicketService soldTicketService = SoldTicketService.getInstance();
        //remove clients with name = name
        //the sold tickets of the client are also removed
        clientRepository.removeClientById(id);
        soldTicketService.removeTicketByClientId(id);
        clientFileService.updateFile();
    }

    //find
    public Vector<Client> findClientByName(String name) {
        auditService.writeInAudit("Find client by name");
        return clientRepository.findClientByName(name);
    }

    //update
    public void updateClientName(String name, String newName) {
        auditService.writeInAudit("Update client name");
        Vector<Client> c = clientRepository.findClientByName(name);
        if(c.size() == 0)
            throw new IllegalArgumentException("No client having this id!");
        clientRepository.updateClientName(name, newName);
        clientFileService.updateFile();
    }

    public void updateClientName(int id, String newName) {
        auditService.writeInAudit("Update client name");
        Client c = clientRepository.getClientById(id);
        if(c == null)
            throw new IllegalArgumentException("No client having this id!");
        clientRepository.updateClientName(id, newName);
        clientFileService.updateFile();
    }

    public void updateClientEmail(int id, String newEmail) {
        auditService.writeInAudit("Update client email");
        Client c = clientRepository.getClientById(id);
        if(c == null)
            throw new IllegalArgumentException("No client having this id!");
        clientRepository.updateClientEmail(id, newEmail);
        clientFileService.updateFile();
    }

    public void updateClientPhone(int id, String newPhone) {
        auditService.writeInAudit("Update client phone");
        Client c = clientRepository.getClientById(id);
        if(c == null)
            throw new IllegalArgumentException("No client having this id!");
        clientRepository.updateClientPhone(id, newPhone);
        clientFileService.updateFile();
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
