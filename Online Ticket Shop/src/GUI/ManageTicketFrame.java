package GUI;


import Model.Location;
import Model.TicketDetails;
import Service.LocationService;
import Service.TicketDetailsService;

import org.jdesktop.swingx.JXDatePicker;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ManageTicketFrame {
    private Connection con;
    static private TicketDetailsService ticketDetailsService = TicketDetailsService.getInstance();
    JPanel f = new JPanel();
    static String columns[] = {"Id Ticket", "Id Event", "Id Location", "Date", "Hour"};
    static DefaultTableModel tableModelTickets = new DefaultTableModel(columns, 0);
    Color background = new Color(164, 189, 186);
    Color fontC = new Color(0, 0, 0);
    Font font = new Font("Tahoma", Font.PLAIN, 18);
    Font font2 = new Font("Tahoma", Font.PLAIN, 14);
    Color c1 = new Color(241, 241, 240);
    Color c2 = new Color(0,0,0);

    public JPanel getPanel() {
        return f;
    }

    static void replaceDataFromTable() throws SQLException {
        tableModelTickets.setRowCount(0);
        ArrayList<TicketDetails> tickets = ticketDetailsService.getTicketDetails();

        for(TicketDetails td : tickets) {
            Object[] obj = {td.getIdTicket(), td.getIdEvent(), td.getIdLocation(), td.getDate(), td.getHour()};
            tableModelTickets.addRow(obj);
        }
    }

    public ManageTicketFrame(String title) throws SQLException {

        JButton add = new JButton("Add Ticket");
        add.setBounds(20, 55, 200, 50);
        styleForButton(add, c2, c1);
        JButton remove = new JButton("Remove Ticket");
        remove.setBounds(20, 185, 200, 50);
        styleForButton(remove, c1,c2);
        JButton update = new JButton("Update Ticket");
        update.setBounds(20, 315, 200, 50);
        styleForButton(update, c2,c1);
        JButton get = new JButton("Get Ticket");
        get.setBounds(20, 445, 200, 50);
        styleForButton(get, c1, c2);
        JPanel ptable = new JPanel();
        ptable.setBounds(300, 20, 700, 200);
        ptable.setLayout(null);
        ptable.setBackground(background);
        JLabel titleTable = new JLabel("All the tickets are here:");
        titleTable.setFont(font);
        titleTable.setForeground(fontC);
        titleTable.setBounds(50, 0, 300, 20);
        ptable.add(titleTable);
        getTickets(ptable);

        JPanel paction = new JPanel();
        paction.setLayout(null);
        paction.setBounds(300, 230, 700, 500);
        paction.setBackground(background);

        add.addActionListener(event -> putPanel(paction, addTicket()));
        remove.addActionListener(event -> putPanel(paction, removeTicketById()));
        update.addActionListener(event -> putPanel(paction, updateTicketLocation()));
        get.addActionListener(event -> putPanel(paction, getTicketById()));
        putPanel(paction, addTicket()); //implicit avem interfata de la add
        f.add(add);
        f.add(remove);
        f.add(update);
        f.add(get);
        f.add(ptable);
        f.add(paction);
        f.setSize(1300, 1000);
        f.setBackground(background);
        f.setLayout(null);
    }

    void styleForButton(JButton b, Color c, Color font) {
        b.setBackground(c);
        b.setForeground(font);
        b.setFocusPainted(false);
        b.setBorder(null);
        b.setFont(new Font("Tahoma", Font.PLAIN, 16));
    }

    void putPanel(JPanel root, JPanel p) {
        //puts into the panel root the panel p
        root.removeAll();
        root.repaint();
        root.revalidate();

        root.add(p);
        root.repaint();
        root.revalidate();
    }

    public JPanel addTicket() {
        JPanel p = new JPanel();
        p.setBounds(0, 0, 600, 400);
        p.setLayout(null);
        p.setBackground(background);

        JLabel title = new JLabel("Fill the fields to add a new ticket");
        title.setFont(font);
        title.setForeground(fontC);
        title.setBounds(35, 10, 400, 20);

        JLabel idEventLabel = new JLabel("Id Event: ");
        JTextField idEvent = new JTextField();
        idEventLabel.setBounds(10, 60, 200, 30);
        idEventLabel.setFont(font2);
        idEventLabel.setForeground(fontC);
        idEvent.setBounds(80, 60, 200, 30);

        JLabel idLocationLabel = new JLabel("Location: ");
        JTextField idLocation = new JTextField();
        idLocationLabel.setBounds(10, 110, 200, 30);
        idLocationLabel.setFont(font2);
        idLocationLabel.setForeground(fontC);
        idLocation.setBounds(80, 110, 200, 30);

        JPanel panel = new JPanel();
        panel.setBackground(background);

        JLabel hourLabel = new JLabel("Hour: ");
        JTextField hour = new JTextField();
        hourLabel.setBounds(10, 160, 200, 30);
        hourLabel.setFont(font2);
        hourLabel.setForeground(fontC);
        hour.setBounds(80, 160, 200, 30);

        JLabel dateLabel = new JLabel("Date: ");
        dateLabel.setBounds(10, 210, 200, 30);
        dateLabel.setFont(font2);
        dateLabel.setForeground(fontC);
        p.add(dateLabel);
        panel.setBounds(170, 203, 110, 30);
        JXDatePicker picker = new JXDatePicker();
        picker.setDate(Calendar.getInstance().getTime());
        picker.setFormats(new SimpleDateFormat("dd.MM.yyyy"));

        panel.add(picker);
        p.add(title);
        p.add(panel);

        JButton button = new JButton("Add Ticket");
        button.setBounds(80, 270, 200, 40);
        styleForButton(button, c1, c2);
        button.addActionListener(event -> addTicketDB(idEvent, idLocation, picker, hour));

        p.add(idEvent);
        p.add(idEventLabel);
        p.add(idLocation);
        p.add(idLocationLabel);
        p.add(hour);
        p.add(hourLabel);
        p.add(button);

        return p;
    }
    public void addTicketDB(JTextField idEvent, JTextField idLocation, JXDatePicker picker, JTextField hour) {
        try {
            int idE = Integer.parseInt(idEvent.getText());
            int idL = Integer.parseInt(idLocation.getText());
            SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
            String d = formater.format(picker.getDate());
            java.sql.Date d2 = Date.valueOf(d);
            String h = hour.getText();
            if(h.length() == 0) {
                JOptionPane.showMessageDialog(null, "All the fields are required");
            }
            else {
                this.ticketDetailsService.addTicket(new TicketDetails(idE, idL, d2, h));
                JOptionPane.showMessageDialog(null, "Ticket was succesfully added!");
                ArrayList<TicketDetails> tickets = ticketDetailsService.getTicketDetails();
                TicketDetails td = tickets.get(tickets.size() - 1);
                Object[] obj = {td.getIdTicket(), td.getIdEvent(), td.getIdLocation(), td.getDate(), td.getHour()};
                tableModelTickets.addRow(obj);
            }

        }
        catch (SQLException s) {
            JOptionPane.showMessageDialog(null, "No event or location with this id!");
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "All the fields are required");
        }
    }

    public void getTickets(JPanel root) {
        JPanel panel = new JPanel();
        panel.setBounds(0, 35, 600, 150);
        panel.setLayout(null);
        JTable table = new JTable(tableModelTickets);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 0, 600, 150);
        try {
            replaceDataFromTable();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        panel.add(scrollPane);
        root.add(panel);
    }

    public JPanel removeTicketById() {
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 600, 400);
        panel.setLayout(null);
        panel.setBackground(background);

        JLabel title = new JLabel("Fill the fields to remove a ticket");
        title.setFont(font);
        title.setForeground(fontC);
        title.setBounds(60, 10, 400, 20);

        JLabel idLabel = new JLabel("Id of ticket:");
        idLabel.setBounds(5, 60, 200, 30);
        idLabel.setFont(font2);
        idLabel.setForeground(fontC);
        JTextField id = new JTextField();
        id.setBounds(90, 60, 200, 30);
        JButton button = new JButton("Remove ticket");
        button.setBounds(90, 125, 200, 40);
        button.addActionListener(event -> removeTicketByIdDB(id));
        styleForButton(button, c1, c2);
        panel.add(title);
        panel.add(id);
        panel.add(idLabel);
        panel.add(button);
        return panel;
    }

    void removeTicketByIdDB(JTextField id) {
        try {
            int i = Integer.parseInt(id.getText());
            TicketDetails t = ticketDetailsService.getTicketDetailsById(i);
            if (t == null) {
                JOptionPane.showMessageDialog(null, "There is no ticket having this id!");
            }
            else {
                ticketDetailsService.removeTicketById(i);
                JOptionPane.showMessageDialog(null, "Ticket was succesfully removed!");
                replaceDataFromTable();
                ManageSoldTicketFrame.replaceDataFromTable();
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "All fields are required!");
        }
    }

    public JPanel updateTicketLocation() {
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 600, 400);
        panel.setLayout(null);
        panel.setBackground(background);

        JLabel title = new JLabel("Fill the fields to update a ticket");
        title.setFont(font);
        title.setForeground(fontC);
        title.setBounds(60, 10, 400, 20);

        JLabel idLabel = new JLabel("Id of ticket:");
        idLabel.setBounds(5, 60, 200, 30);
        idLabel.setFont(font2);
        idLabel.setForeground(fontC);
        JTextField id = new JTextField();
        id.setBounds(90, 60, 200, 30);

        JLabel idLocationLabel = new JLabel("New location id:");
        idLocationLabel.setBounds(5, 110, 200, 30);
        idLocationLabel.setFont(font2);
        idLocationLabel.setForeground(fontC);
        JTextField idLocation = new JTextField();
        idLocation.setBounds(90, 110, 200, 30);
        JButton button = new JButton("Update ticket");
        button.setBounds(90, 175, 200, 40);
        styleForButton(button, c1, c2);
        button.addActionListener(event -> updateTicketLocationDB(id, idLocation));
        panel.add(title);
        panel.add(id);
        panel.add(idLabel);
        panel.add(idLocation);
        panel.add(idLocationLabel);
        panel.add(button);
        return panel;
    }

    void updateTicketLocationDB(JTextField id, JTextField idLoc) {
        try {
            int i = Integer.parseInt(id.getText());
            int iL = Integer.parseInt(idLoc.getText());
            TicketDetails t = ticketDetailsService.getTicketDetailsById(i);
            if (t == null) {
                JOptionPane.showMessageDialog(null, "There is no ticket having this id!");
            }
            else {
                ticketDetailsService.updateTicketDetailsLocationOfEvent(i, iL);
                JOptionPane.showMessageDialog(null, "Ticket was succesfully updated!");
                replaceDataFromTable();
            }

        }
        catch (SQLException s) {
            JOptionPane.showMessageDialog(null, "There is no location having this id!");
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "All fields are required!");
        }
    }

    public JPanel getTicketById() {
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 600, 400);
        panel.setLayout(null);
        panel.setBackground(background);
        JLabel title = new JLabel("Insert an id to see the ticket");
        title.setFont(font);
        title.setForeground(fontC);
        title.setBounds(70, 10, 400, 20);

        JLabel idLabel = new JLabel("Id of ticket:");
        JTextField id = new JTextField();
        idLabel.setFont(font2);
        idLabel.setForeground(fontC);
        idLabel.setBounds(5, 60, 200, 30);
        id.setBounds(90, 60, 200, 30);

        JButton button = new JButton("Get ticket");
        button.setBounds(90, 125, 200, 40);
        styleForButton(button, c1, c2);
        button.addActionListener(event -> getTicketByIdDB(id, panel));

        panel.add(title);
        panel.add(id);
        panel.add(idLabel);
        panel.add(button);

        return panel;

    }

    void getTicketByIdDB(JTextField id, JPanel panel) {
        try {
            int i = Integer.parseInt(id.getText());
            TicketDetails t = ticketDetailsService.getTicketDetailsById(i);
            if (t == null) {
                JOptionPane.showMessageDialog(null, "There is no ticket having this id!");
            }
            else {
                Object[] obj = {t.getIdTicket(), t.getIdEvent(), t.getIdLocation(), t.getDate(), t.getHour()};

                DefaultTableModel tGet = new DefaultTableModel(columns, 0);
                JTable tTick = new JTable(tGet);
                tTick.setBounds(0,0, 600, 50);
                JScrollPane scrollPane = new JScrollPane(tTick);
                scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                scrollPane.setBounds(0, 190, 600, 50);
                panel.add(scrollPane);
                tGet.addRow(obj);
            }

        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "All fields are required!");
        }
    }
}

