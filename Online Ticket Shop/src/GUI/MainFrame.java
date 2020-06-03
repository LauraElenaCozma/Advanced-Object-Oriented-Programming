package GUI;

import Database.DBConnectionUtils;
import Main.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.SQLException;

public class MainFrame extends JFrame{
    static Color background = new Color(164, 189, 186);
    static Color fontC = new Color(0, 0, 0);
    static Font font = new Font("Tahoma", Font.PLAIN, 18);
    static Font font2 = new Font("Tahoma", Font.PLAIN, 14);
    static Color c1 = new Color(241, 241, 240);
    static Color c2 = new Color(0,0,0);

    public MainFrame() throws SQLException {
        JPanel p1 = new ManageSoldTicketFrame().getPanel();
        JPanel p2 = new ManageClientFrame().getPanel();
        JPanel p3 = new ManageTicketFrame().getPanel();
        JPanel p4 = new ManageEventFrame().getPanel();
        JPanel p5 = new ManageLocationFrame().getPanel();

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                DBConnectionUtils.closeDBConnection(Main.connection);
                System.exit(0);
            }
        });

        JTabbedPane tp = new JTabbedPane();
        tp.setBounds(10, 10, 1500, 800);
        tp.add("Sold Tickets", p1);
        tp.add("Clients", p2);
        tp.add("Tickets", p3);
        tp.add("Events", p4);
        tp.add("Locations", p5);
        tp.setFont(MainFrame.font2);
        add(tp);
        setSize(1050, 830);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);
    }



}
