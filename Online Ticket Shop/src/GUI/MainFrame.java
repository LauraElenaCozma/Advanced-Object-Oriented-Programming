package GUI;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class MainFrame extends JFrame{
    Color background = new Color(164, 189, 186);
    Font font2 = new Font("Tahoma", Font.PLAIN, 14);
    public MainFrame() throws SQLException {
        JPanel p1 = new ManageSoldTicketFrame("Sold Tickets").getPanel();
        JPanel p2 = new ManageClientFrame("Clients").getPanel();
        JPanel p3 = new ManageTicketFrame("Tickets").getPanel();
        JPanel p4 = new ManageEventFrame("Events").getPanel();
        JPanel p5 = new ManageLocationFrame("Events").getPanel();


        JTabbedPane tp = new JTabbedPane();
        tp.setBounds(10, 10, 1500, 800);
        tp.add("Sold Tickets", p1);
        tp.add("Clients", p2);
        tp.add("Tickets", p3);
        tp.add("Events", p4);
        tp.add("Locations", p5);
        tp.setFont(font2);
        add(tp);
        setSize(1050, 830);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);
    }
}
