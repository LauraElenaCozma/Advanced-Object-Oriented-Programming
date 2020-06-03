package Service;

import Model.Client;
import Model.Event;
import Model.Location;
import Repository.ClientRepository;
import Service.Audit.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;
import java.util.ArrayList;

public class ClientService {
    private ClientRepository clientRepository = new ClientRepository();
    private static ClientService instance = new ClientService();
    private static AuditService auditService = AuditService.getInstance();

    private ClientService() {
    }

    public static ClientService getInstance() {

        return instance;
    }

    public void addClient(Client c) throws SQLException {
        auditService.writeInAudit(Thread.currentThread().getName() + ",Add client");
        clientRepository.addClient(c);
    }

    //remove
    public void removeClientByName(String name) throws SQLException {
        auditService.writeInAudit(Thread.currentThread().getName() + ",Remove clients by name");
        SoldTicketService soldTicketService = SoldTicketService.getInstance();
        //remove clients with name = name
        //the sold tickets of the client are also removed
        ArrayList<Client> array = clientRepository.findClientByName(name);
        for(Client c : array) {
            soldTicketService.removeTicketByClientId(c.getIdClient());
        }
        clientRepository.removeClientByName(name);
    }

    public void removeClientById(int id) throws SQLException {
        auditService.writeInAudit(Thread.currentThread().getName() + ",Remove client by id");
        //!!SoldTicketService soldTicketService = SoldTicketService.getInstance();
        //remove clients with name = name
        //the sold tickets of the client are also removed
        clientRepository.removeClientById(id);
        //!!soldTicketService.removeTicketByClientId(id);
    }

    //find
    public ArrayList<Client> findClientByName(String name) throws SQLException {
        auditService.writeInAudit(Thread.currentThread().getName() + ",Find client by name");
        return clientRepository.findClientByName(name);
    }

    //update
    public void updateClientName(String name, String newName) throws SQLException {
        auditService.writeInAudit(Thread.currentThread().getName() + ",Update client name");
        /*ArrayList<Client> c = clientRepository.findClientByName(name);
        if(c.size() == 0)
            throw new IllegalArgumentException("No client having this id!");*/
        clientRepository.updateClientName(name, newName);
    }

    public void updateClientName(int id, String newName) throws SQLException {
        auditService.writeInAudit(Thread.currentThread().getName() + ",Update client name");
        clientRepository.updateClientName(id, newName);
    }

    public void updateClientEmail(int id, String newEmail) throws SQLException {
        auditService.writeInAudit(Thread.currentThread().getName() + ",Update client email");
        clientRepository.updateClientEmail(id, newEmail);
    }

    public void updateClientPhone(int id, String newPhone) throws SQLException {
        auditService.writeInAudit(Thread.currentThread().getName() + ",Update client phone");
        clientRepository.updateClientPhone(id, newPhone);

    }

    //get
    public Set<Client> getClients() throws SQLException {
        auditService.writeInAudit(Thread.currentThread().getName() + ",Get all clients");
        return clientRepository.getClients();
    }

    public Client getClientById(int id) throws SQLException {
        auditService.writeInAudit(Thread.currentThread().getName() + ",Get client by id");
        return clientRepository.getClientById(id);
    }

    public ClientRepository getClientRepository() {
        return clientRepository;
    }
}
