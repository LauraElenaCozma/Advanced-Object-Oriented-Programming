package GUI;

import Model.Event;
import Model.Location;
import Model.SoldTicket;
import Service.LocationService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class ManageLocationFrame{
    private JPanel f = new JPanel();
    private Connection con;
    private LocationService locationService= LocationService.getInstance();
    String columns[] = {"Id Location", "Venue", "Country", "City", "Location name"};
    DefaultTableModel tableModelLocations = new DefaultTableModel(columns, 0);
    Color background = new Color(164, 189, 186);
    Color fontC = new Color(0, 0, 0);
    Font font = new Font("Tahoma", Font.PLAIN, 18);
    Font font2 = new Font("Tahoma", Font.PLAIN, 14);
    Color c1 = new Color(241, 241, 240);
    Color c2 = new Color(0,0,0);

    public JPanel getPanel() {
        return f;
    }
    public ManageLocationFrame(String title) throws SQLException {
        JButton add = new JButton("Add Location");
        add.setBounds(20, 55, 200, 50);
        styleForButton(add, c2, c1);
        JButton remove = new JButton("Remove Location");
        remove.setBounds(20, 185, 200, 50);
        styleForButton(remove, c1,c2);
        JButton update = new JButton("Update Location");
        update.setBounds(20, 315, 200, 50);
        styleForButton(update, c2,c1);
        JButton get = new JButton("Get Location");
        get.setBounds(20, 445, 200, 50);
        styleForButton(get, c1, c2);
        JPanel ptable = new JPanel();
        ptable.setBounds(300, 20, 700, 170);
        ptable.setLayout(null);
        ptable.setBackground(background);
        JLabel titleTable = new JLabel("All the locations are here:");
        titleTable.setFont(font);
        titleTable.setForeground(fontC);
        titleTable.setBounds(50, 0, 300, 20);
        ptable.add(titleTable);
        getLocations(ptable);

        JPanel paction = new JPanel();
        paction.setLayout(null);
        paction.setBounds(300, 200, 700, 500);
        paction.setBackground(background);

        add.addActionListener(event -> putPanel(paction, addLocation()));
        remove.addActionListener(event -> putPanel(paction, removeLocationById()));
        update.addActionListener(event -> putPanel(paction, updateLocationName()));
        get.addActionListener(event -> putPanel(paction, getLocationById()));
        putPanel(paction, addLocation()); //implicit avem interfata de la add
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
    public void getLocations(JPanel root) {
        JPanel panel = new JPanel();
        panel.setBounds(0, 35, 700, 100);
        panel.setLayout(null);

        JTable table = new JTable(tableModelLocations);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(0, 0, 700, 100);

        panel.add(scrollPane);
        try {
            replaceDataFromTable();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        root.add(panel);
    }
    public JPanel addLocation() {
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 600, 400);
        panel.setLayout(null);
        panel.setBackground(background);
        JLabel title = new JLabel("Fill the fields to add a new location");
        title.setFont(font);
        title.setForeground(fontC);
        title.setBounds(35, 10, 400, 20);
        JLabel venueLabel = new JLabel("Venue: ");
        JTextField venue = new JTextField();
        venueLabel.setBounds(10, 60, 200, 30);
        venueLabel.setFont(font2);
        venueLabel.setForeground(fontC);
        venue.setBounds(80, 60, 200, 30);

        JLabel countryLabel = new JLabel("Country: ");
        JTextField country = new JTextField();
        countryLabel.setBounds(10, 110, 200, 30);
        countryLabel.setFont(font2);
        countryLabel.setForeground(fontC);
        country.setBounds(80, 110, 200, 30);

        JLabel cityLabel = new JLabel("City: ");
        JTextField city = new JTextField();
        cityLabel.setBounds(10, 160, 200, 30);
        cityLabel.setFont(font2);
        cityLabel.setForeground(fontC);
        city.setBounds(80, 160, 200, 30);

        JLabel nameLabel = new JLabel("Name: ");
        JTextField name = new JTextField();
        nameLabel.setBounds(10, 210, 200, 30);
        nameLabel.setFont(font2);
        nameLabel.setForeground(fontC);
        name.setBounds(80, 210, 200, 30);

        JButton button = new JButton("Add Location");
        button.setBounds(80, 275, 200, 40);
        styleForButton(button,c1 , c2);
        button.addActionListener(event -> addLocationDB(venue, country, city, name));
        panel.add(title);
        panel.add(venue);
        panel.add(venueLabel);
        panel.add(country);
        panel.add(countryLabel);
        panel.add(city);
        panel.add(cityLabel);
        panel.add(name);
        panel.add(nameLabel);
        panel.add(button);
        return panel;
    }
    public void addLocationDB(JTextField venue, JTextField country, JTextField city, JTextField name){
        try {
            String v = venue.getText();
            String cou = country.getText();
            String cit = city.getText();
            String n = name.getText();
            if(v.length() == 0 || cou.length() == 0 || cit.length() == 0 || n.length() == 0) {
                JOptionPane.showMessageDialog(null, "All fields are required!");
            }
            else {
                this.locationService.addLocationInService(new Location(v, cou, cit, n));
                JOptionPane.showMessageDialog(null, "Location was succesfully added!");
                ArrayList<Location> locations = locationService.getLocations();
                Location loc = locations.get(locations.size() - 1);
                Object []obj = {loc.getIdLocation(), loc.getVenue(), loc.getCountry(), loc.getCity(), loc.getLocationName()};
                tableModelLocations.addRow(obj);
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in database!");
        }

    }

     void replaceDataFromTable() throws SQLException {
        tableModelLocations.setRowCount(0);
        ArrayList<Location> loc = locationService.getLocations();
        for(Location location : loc) {
            Object[] obj = {location.getIdLocation(), location.getVenue(), location.getCountry(), location.getCity(), location.getLocationName()};
            tableModelLocations.addRow(obj);
        }
    }

    public JPanel removeLocationById() {
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 600, 400);
        panel.setBackground(background);
        panel.setLayout(null);
        JLabel title = new JLabel("Fill the fields to remove a location");
        title.setFont(font);
        title.setForeground(fontC);
        title.setBounds(75, 10, 400, 20);
        JLabel idLabel = new JLabel("Id of location: ");
        idLabel.setBounds(5, 60, 200, 30);
        idLabel.setFont(font2);
        idLabel.setForeground(fontC);
        JTextField id = new JTextField();
        id.setBounds(110, 60, 200, 30);

        JButton button = new JButton("Remove location");
        button.setBounds(110, 125, 200, 40);
        styleForButton(button, c1, c2);
        button.addActionListener(event -> removeLocationByIdDB(id));
        panel.add(title);
        panel.add(id);
        panel.add(idLabel);
        panel.add(button);

        return panel;
    }

    public void removeLocationByIdDB(JTextField id) {
        try {
            int i = Integer.parseInt(id.getText());
            Location loc = locationService.getLocationById(i);
            if (loc == null) {
                JOptionPane.showMessageDialog(null, "There is no location having this id!");
            }
            else {
                locationService.removeLocationById(i);
                JOptionPane.showMessageDialog(null, "Location was succesfully removed!");
                replaceDataFromTable();
                ManageTicketFrame.replaceDataFromTable();
                ManageSoldTicketFrame.replaceDataFromTable();
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "All fields are required!");
        }
    }

    public JPanel updateLocationName() {
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 600, 400);
        panel.setLayout(null);
        panel.setBackground(background);
        panel.setLayout(null);
        JLabel title = new JLabel("Fill the fields update the name of the location");
        title.setFont(font);
        title.setForeground(fontC);
        title.setBounds(35, 10, 400, 20);

        JLabel idLabel = new JLabel("Id of location: ");
        idLabel.setBounds(5, 60, 200, 30);
        idLabel.setFont(font2);
        idLabel.setForeground(fontC);
        JTextField id = new JTextField();
        id.setBounds(110, 60, 200, 30);

        JLabel nameLabel = new JLabel("New name: ");
        nameLabel.setBounds(5, 110, 200, 30);
        nameLabel.setFont(font2);
        nameLabel.setForeground(fontC);
        JTextField name = new JTextField();
        name.setBounds(110, 110, 200, 30);

        JButton button = new JButton("Update location");
        button.setBounds(110, 175, 200, 40);
        styleForButton(button, c1, c2);
        button.addActionListener(event -> updateLocationNameDB(id, name));

        panel.add(title);
        panel.add(id);
        panel.add(idLabel);
        panel.add(name);
        panel.add(nameLabel);
        panel.add(button);

        return panel;
    }

    public void updateLocationNameDB(JTextField id, JTextField name) {
        try {
            int i = Integer.parseInt(id.getText());
            Location loc = locationService.getLocationById(i);
            if (loc == null) {
                JOptionPane.showMessageDialog(null, "There is no location having this id!");
            }
            else {
                String n = name.getText();
                if (n.length() == 0) {
                    JOptionPane.showMessageDialog(null, "All fields are required!");
                }
                else {
                    locationService.updateLocationName(i, n);
                    JOptionPane.showMessageDialog(null, "Location was succesfully updated!");
                    replaceDataFromTable();
                }
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "All fields are required!");
        }
    }

    public JPanel getLocationById() {
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 700, 400);
        panel.setBackground(background);
        panel.setLayout(null);
        JLabel title = new JLabel("Fill the fields to see the location");
        title.setFont(font);
        title.setForeground(fontC);
        title.setBounds(85, 10, 400, 20);

        JLabel idLabel = new JLabel("Id of location:");
        JTextField id = new JTextField();
        idLabel.setBounds(5, 60, 200, 30);
        id.setBounds(110, 60, 200, 30);
        idLabel.setFont(font2);
        idLabel.setForeground(fontC);
        JButton button = new JButton("Get location");
        button.setBounds(110, 125, 200, 40);
        styleForButton(button, c1, c2);
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
            Location loc = locationService.getLocationById(i);
            if (loc == null) {
                JOptionPane.showMessageDialog(null, "There is no location having this id!");
            }
            else {
                Object[] obj = {loc.getIdLocation(), loc.getVenue(), loc.getCountry(), loc.getCity(), loc.getLocationName()};

                DefaultTableModel tGet = new DefaultTableModel(columns, 0);
                JTable tLoc = new JTable(tGet);
                tLoc.setBounds(0,0, 600, 50);
                JScrollPane scrollPane = new JScrollPane(tLoc);
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
