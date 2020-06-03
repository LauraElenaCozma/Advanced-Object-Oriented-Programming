package Thread;

import Main.Main;


import java.sql.SQLException;

public class ThreadUpdateClientName extends Thread{
    private int id;
    private String name;

    public ThreadUpdateClientName(int id, String name) {

        this.id = id;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            Main.clientService.updateClientName(id, name);
        } catch (SQLException throwables) {
            throw new RuntimeException("Error");
        }
    }
}
