package Service;

import Model.Client;
import Repository.ClientRepository;
import Audit.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;

public class ClientService {
    private ClientRepository clientRepository = null;
    private static ClientService instance;
    private static AuditService auditService = AuditService.getInstance();

    private ClientService(Connection connection) {
        clientRepository = new ClientRepository(connection);
    }

    public static ClientService getInstance(Connection connection) {

        if(instance == null) {
            instance = new ClientService(connection);
        }
        return instance;
    }

    public void addClient(Client c) throws SQLException {
        auditService.writeInAudit(",Add client");
        clientRepository.addClient(c);
    }

    //remove
    public void removeClientByName(String name) throws SQLException {
        //remove clients with name = name
        //the sold tickets of the client are also removed
        auditService.writeInAudit("Remove clients by name");
        clientRepository.removeClientByName(name);
    }

    public void removeClientById(int id) throws SQLException {
        //remove clients with name = name
        //the sold tickets of the client are also removed
        auditService.writeInAudit("Remove client by id");
        clientRepository.removeClientById(id);
    }

    //find
    public ArrayList<Client> findClientByName(String name) throws SQLException {
        auditService.writeInAudit("Find client by name");
        return clientRepository.findClientByName(name);
    }

    //update
    public void updateClientName(String name, String newName) throws SQLException {
        auditService.writeInAudit("Update client name");
        clientRepository.updateClientName(name, newName);
    }

    synchronized public void updateClientName(int id, String newName) throws SQLException {
        auditService.writeInAudit("Update client name");
        clientRepository.updateClientName(id, newName);
    }

    public void updateClientEmail(int id, String newEmail) throws SQLException {
        auditService.writeInAudit("Update client email");
        clientRepository.updateClientEmail(id, newEmail);
    }

    public void updateClientPhone(int id, String newPhone) throws SQLException {
        auditService.writeInAudit("Update client phone");
        clientRepository.updateClientPhone(id, newPhone);

    }

    //get
    public Set<Client> getClients() throws SQLException {
        auditService.writeInAudit("Get all clients");
        return clientRepository.getClients();
    }

    public Client getClientById(int id) throws SQLException {
        auditService.writeInAudit("Get client by id");
        return clientRepository.getClientById(id);
    }
}
