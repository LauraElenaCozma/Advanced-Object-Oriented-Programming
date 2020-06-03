package GUI;

import Main.Main;
import Model.Event;
import Service.EventService;
import Thread.ThreadAddEvent;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;


public class ManageEventFrame{
    private JPanel f = new JPanel();
    private EventService eventService = Main.eventService;
    String columns[] = {"Id Event", "Name", "Duration", "Price"};
    DefaultTableModel tableModelEvents = new DefaultTableModel(columns, 0);

    public JPanel getPanel() {
        return f;
    }
    public ManageEventFrame() throws SQLException {

        JButton add = new JButton("Add Event");
        add.setBounds(20, 55, 200, 50);
        styleForButton(add, MainFrame.c2, MainFrame.c1);
        JButton remove = new JButton("Remove Event");
        remove.setBounds(20, 185, 200, 50);
        styleForButton(remove, MainFrame.c1,MainFrame.c2);
        JButton update = new JButton("Update Event");
        update.setBounds(20, 315, 200, 50);
        styleForButton(update, MainFrame.c2,MainFrame.c1);
        JButton get = new JButton("Get Event");
        get.setBounds(20, 445, 200, 50);
        styleForButton(get, MainFrame.c1, MainFrame.c2);
        JPanel ptable = new JPanel();
        ptable.setBounds(300, 20, 700, 200);
        ptable.setLayout(null);
        ptable.setBackground(MainFrame.background);
        JLabel titleTable = new JLabel("All the events are here:");
        titleTable.setFont(MainFrame.font);
        titleTable.setForeground(MainFrame.fontC);
        titleTable.setBounds(50, 0, 300, 20);
        ptable.add(titleTable);
        getEvents(ptable);

        JPanel paction = new JPanel();
        paction.setLayout(null);
        paction.setBounds(300, 230, 700, 500);
        paction.setBackground(MainFrame.background);

        add.addActionListener(event -> putPanel(paction, addEvent()));
        remove.addActionListener(event -> putPanel(paction, removeEventById()));
        update.addActionListener(event -> putPanel(paction, updatePriceOfEventById()));
        get.addActionListener(event -> putPanel(paction, getEventById()));
        putPanel(paction, addEvent()); //implicit avem interfata de la add
        f.add(add);
        f.add(remove);
        f.add(update);
        f.add(get);
        f.add(ptable);
        f.add(paction);
        f.setSize(1300, 1000);
        f.setBackground(MainFrame.background);
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
        root.removeAll();
        root.repaint();
        root.revalidate();

        root.add(p);
        root.repaint();
        root.revalidate();
    }
    public JPanel addEvent() {
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 400, 400);
        panel.setBackground(MainFrame.background);
        panel.setLayout(null);
        JLabel title = new JLabel("Enter the data to add a new event");
        title.setFont(MainFrame.font);
        title.setForeground(MainFrame.fontC);
        title.setBounds(50, 10, 300, 20);
        JLabel nameLabel = new JLabel("Name: " );
        JTextField name = new JTextField();
        nameLabel.setBounds(5, 60, 200, 30);
        nameLabel.setFont(MainFrame.font2);
        nameLabel.setForeground(MainFrame.fontC);
        name.setBounds(80, 60, 200, 30);

        JLabel durationLabel = new JLabel("Duration: " );
        JTextField duration = new JTextField();
        durationLabel.setBounds(5, 110, 200, 30);
        durationLabel.setFont(MainFrame.font2);
        durationLabel.setForeground(MainFrame.fontC);
        duration.setBounds(80, 110, 200, 30);

        JLabel priceLabel = new JLabel("Price: " );
        JTextField price = new JTextField();
        priceLabel.setBounds(5, 160, 200, 30);
        priceLabel.setFont(MainFrame.font2);
        priceLabel.setForeground(MainFrame.fontC);
        price.setBounds(80, 160, 200, 30);

        JButton button = new JButton("Add Event");
        button.setBounds(80, 225, 200, 40);
        styleForButton(button, MainFrame.c1, MainFrame.c2);
        button.addActionListener(event -> addEventDB(name, duration, price));


        panel.add(title);
        panel.add(name);
        panel.add(nameLabel);
        panel.add(duration);
        panel.add(durationLabel);
        panel.add(price);
        panel.add(priceLabel);
        panel.add(button);

        return panel;
    }

    public void addEventDB(JTextField name, JTextField duration, JTextField price)   {
        try {
            String n = name.getText();
            if(n.length() == 0) {
                JOptionPane.showMessageDialog(null, "All fields required!");
            }
            else {
                int d = Integer.parseInt(duration.getText());
                double p = Double.parseDouble(price.getText());

                ThreadAddEvent thread = new ThreadAddEvent(new Event(n, d, p));
                thread.start();

                try
                {
                    thread.join();
                }
                catch (InterruptedException ex)
                {
                    System.out.println("Thread error!");
                }



                JOptionPane.showMessageDialog(null, "The event was succesfully added!");
                ArrayList<Event> events = eventService.getEvents();
                Event e = events.get(events.size() - 1);
                Object ev[] = {e.getIdEvent(), e.getName(), e.getDuration(), e.getPrice()};
                tableModelEvents.addRow(ev);
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "All fields required!");
        }

    }

    public JPanel updatePriceOfEventById() {
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 600, 250);
        panel.setBackground(MainFrame.background);
        panel.setLayout(null);

        JLabel title = new JLabel("Enter an id to update the price of that event");
        title.setFont(MainFrame.font);
        title.setForeground(MainFrame.fontC);
        title.setBounds(20, 10, 500, 20);

        JLabel idLabel = new JLabel("Id of event:");
        JTextField id = new JTextField();
        idLabel.setBounds(10, 70, 200, 30);
        idLabel.setFont(MainFrame.font2);
        idLabel.setForeground(MainFrame.fontC);
        id.setBounds(100, 70, 200, 30);

        JLabel priceLabel = new JLabel("New Price:");
        JTextField price = new JTextField();
        priceLabel.setBounds(10, 120, 200, 30);
        priceLabel.setFont(MainFrame.font2);
        priceLabel.setForeground(MainFrame.fontC);
        price.setBounds(100, 120, 200, 30);

        JButton button = new JButton("Update event");
        button.setBounds(100, 185, 200, 40);
        styleForButton(button, MainFrame.c1, MainFrame.c2);
        button.addActionListener(event -> updatePriceOfEventByIdDB(id, price));
        panel.add(title);
        panel.add(id);
        panel.add(idLabel);
        panel.add(price);
        panel.add(priceLabel);
        panel.add(button);

        return panel;
    }


    public void updatePriceOfEventByIdDB(JTextField id, JTextField price) {
        try {
            int i = Integer.parseInt(id.getText());
            double p = Double.parseDouble(price.getText());
            Event o = eventService.getEventById(i);
            if(o == null) {
                JOptionPane.showMessageDialog(null, "No event having this id!");
            }
            else {
                eventService.updatePriceEventById(i, p);
                JOptionPane.showMessageDialog(null, "The event was succesfully updated!");

                tableModelEvents.setRowCount(0);
                ArrayList<Event> events = eventService.getEvents();

                for(Event e : events) {
                    Object ev[] = {e.getIdEvent(), e.getName(), e.getDuration(), e.getPrice()};
                    tableModelEvents.addRow(ev);
                }
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "All fields are required!");
        }


    }
    public JPanel removeEventById() {
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 400, 250);
        panel.setBackground(MainFrame.background);
        panel.setLayout(null);
        JLabel title = new JLabel("Enter an id to remove that event");
        title.setFont(MainFrame.font);
        title.setForeground(MainFrame.fontC);
        title.setBounds(70, 10, 300, 20);

        JLabel idLabel = new JLabel("Id of event:");
        JTextField id = new JTextField();
        idLabel.setBounds(10, 70, 200, 30);
        idLabel.setFont(MainFrame.font2);
        idLabel.setForeground(MainFrame.fontC);
        id.setBounds(100, 70, 200, 30);

        JButton button = new JButton("Delete event");
        button.setBounds(100, 135, 200, 40);
        styleForButton(button, MainFrame.c1, MainFrame.c2);
        button.addActionListener(event -> removeEventByIdDB(id));
        panel.add(title);
        panel.add(id);
        panel.add(idLabel);
        panel.add(button);

        return panel;
    }

    public void removeEventByIdDB(JTextField id) {
        try {
            int i = Integer.parseInt(id.getText());
            Event o = eventService.getEventById(i);
            if(o == null) {
                JOptionPane.showMessageDialog(null, "No event having this id!");
            }
            else {
                eventService.removeEventByIdEvent(i);
                JOptionPane.showMessageDialog(null, "The event was succesfully removed!");

                tableModelEvents.setRowCount(0);
                ArrayList<Event> events = eventService.getEvents();

                for(Event e : events) {
                    Object ev[] = {e.getIdEvent(), e.getName(), e.getDuration(), e.getPrice()};
                    tableModelEvents.addRow(ev);
                }
                ManageTicketFrame.replaceDataFromTable();
                ManageSoldTicketFrame.replaceDataFromTable();
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "All fields are required!");
        }

    }

    public void getEvents(JPanel root) {
        try {
            ArrayList<Event> events = eventService.getEvents();

            JTable tableEvents = new JTable(tableModelEvents);

            for(Event e : events) {
                Object ev[] = {e.getIdEvent(), e.getName(), e.getDuration(), e.getPrice()};
                tableModelEvents.addRow(ev);
            }

            JScrollPane scrollEvent = new JScrollPane(tableEvents);
            scrollEvent.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            scrollEvent.setBounds(0, 35, 600, 150);

            root.add(scrollEvent);
        }
        catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Error");
        }
    }

    public JPanel getEventById() {
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 600, 250);
        panel.setBackground(MainFrame.background);
        panel.setLayout(null);

        JLabel title = new JLabel("Enter an id to see that event");
        title.setFont(MainFrame.font);
        title.setForeground(MainFrame.fontC);
        title.setBounds(80, 10, 500, 20);

        JLabel idLabel = new JLabel("Id of event:");
        JTextField id = new JTextField();
        idLabel.setBounds(10, 70, 200, 30);
        idLabel.setFont(MainFrame.font2);
        idLabel.setForeground(MainFrame.fontC);
        id.setBounds(100, 70, 200, 30);

        JButton button = new JButton("Get event");
        button.setBounds(100, 120, 200, 40);
        styleForButton(button, MainFrame.c1, MainFrame.c2);
        button.addActionListener(event -> getEventByIdDB(id, panel));

        panel.add(title);
        panel.add(id);
        panel.add(idLabel);
        panel.add(button);

        return panel;

    }

    public void getEventByIdDB(JTextField id, JPanel panel) {
        try {
            int i = Integer.parseInt(id.getText());
            Event e = eventService.getEventById(i);
            if(e == null) {
                JOptionPane.showMessageDialog(null, "No event having this id!");
            }
            else {
                Object[] obj = {e.getIdEvent(), e.getName(), e.getDuration(), e.getPrice()};

                DefaultTableModel tGet = new DefaultTableModel(columns, 0);
                JTable tEv = new JTable(tGet);
                JScrollPane scrollEvent = new JScrollPane(tEv);
                scrollEvent.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                scrollEvent.setBounds(0, 185, 600, 45);
                panel.add(scrollEvent);
                tGet.addRow(obj);
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "All fields are required!");
        }


    }


}
