package Thread;

import Model.Client;
import Service.ClientService;

import java.sql.SQLException;

public class ThreadUpdateClientName extends Thread{
    private int id;
    private String name;

    public ThreadUpdateClientName(int id, String name) {
        this.id = id;
    }

    @Override
    public void run() {
        try {
            ClientService.getInstance().updateClientName(id, name);
        } catch (SQLException throwables) {
            throw new RuntimeException("Error");
        }
    }
}
