package GUI;

import Model.SoldTicket;
import Model.TicketDetails;
import Service.SoldTicketService;
import Service.TicketDetailsService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import Thread.ThreadAddSoldTicket;


public class ManageSoldTicketFrame  {
    private Connection con;
    static private SoldTicketService soldTicketService= SoldTicketService.getInstance();
    JPanel f = new JPanel();
    static String columns[] = {"Id Sold Ticket", "Id Client", "Id Ticket", "Price after discount"};
    static DefaultTableModel tableModelST = new DefaultTableModel(columns, 0);
    Color background = new Color(167, 201, 209);
    Color fontC = new Color(0, 0, 0);
    Font font = new Font("Tahoma", Font.PLAIN, 18);
    Font font2 = new Font("Tahoma", Font.PLAIN, 14);
    Color c1 = new Color(241, 241, 240);
    Color c2 = new Color(0,0,0);
    public JPanel getPanel() {
        return f;
    }

    public ManageSoldTicketFrame(String title) throws SQLException {

        JButton add = new JButton("Add Sold Ticket");
        add.setBounds(20, 55, 200, 50);
        styleForButton(add, c2, c1);
        JButton remove = new JButton("Remove Sold Ticket");
        remove.setBounds(20, 185, 200, 50);
        styleForButton(remove, c1,c2);
        JButton update = new JButton("Update Sold Ticket");
        update.setBounds(20, 315, 200, 50);
        styleForButton(update, c2,c1);
        JButton get = new JButton("Get Sold Ticket");
        get.setBounds(20, 445, 200, 50);
        styleForButton(get, c1, c2);
        JPanel ptable = new JPanel();
        ptable.setBounds(300, 20, 700, 200);
        ptable.setLayout(null);
        ptable.setBackground(background);
        JLabel titleTable = new JLabel("All the sold tickets are here:");
        titleTable.setFont(font);
        titleTable.setForeground(fontC);
        titleTable.setBounds(50, 0, 300, 20);
        ptable.add(titleTable);
        getAllTickets(ptable);

        JPanel paction = new JPanel();
        paction.setLayout(null);
        paction.setBounds(300, 230, 700, 500);
        paction.setBackground(background);

        add.addActionListener(event -> putPanel(paction, addSoldTicket()));
        remove.addActionListener(event -> putPanel(paction, removeSoldTicketById()));
        update.addActionListener(event -> putPanel(paction, updateTicketDetailsOfTicket()));
        get.addActionListener(event -> putPanel(paction, getSoldTicketById()));
        putPanel(paction, addSoldTicket()); //implicit avem interfata de la add
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

    public JPanel addSoldTicket() {

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 400, 400);
        panel.setLayout(null);
        panel.setBackground(background);

        JLabel title = new JLabel("Enter the data to add a new sold ticket");
        title.setFont(font);
        title.setForeground(fontC);
        title.setBounds(35, 10, 400, 20);

        JLabel idClientLabel = new JLabel("Id Client: ");
        JTextField idClient = new JTextField();
        idClientLabel.setBounds(5, 60, 200, 30);
        idClientLabel.setFont(font2);
        idClientLabel.setForeground(fontC);
        idClient.setBounds(80, 60, 200, 30);

        JLabel idTicketLabel = new JLabel("Id Ticket: ");
        JTextField idTicket = new JTextField();
        idTicketLabel.setBounds(5, 110, 200, 30);
        idTicketLabel.setFont(font2);
        idTicketLabel.setForeground(fontC);
        idTicket.setBounds(80, 110, 200, 30);

        JButton button = new JButton("Add Sold Ticket");
        button.setBounds(80, 175, 200, 40);
        styleForButton(button, c1, c2);
        button.addActionListener(event -> addSoldTicketDB(idClient, idTicket));

        panel.add(title);
        panel.add(idClient);
        panel.add(idClientLabel);
        panel.add(idTicket);
        panel.add(idTicketLabel);
        panel.add(button);

        return panel;
    }
    public void addSoldTicketDB(JTextField idClient, JTextField idTicket) {
        try {
            int idC = Integer.parseInt(idClient.getText());
            int idT = Integer.parseInt(idTicket.getText());

            ThreadAddSoldTicket thread = new ThreadAddSoldTicket(new SoldTicket(idC, idT));
            thread.start();
            try
            {
                thread.join();
            }
            catch (InterruptedException ex)
            {
                System.out.println("Thread error!");
            }

            JOptionPane.showMessageDialog(null, "Sold ticket was succesfully added!");
            ArrayList<SoldTicket> soldTickets = soldTicketService.getSoldTickets();
            SoldTicket st = soldTickets.get(soldTickets.size() - 1);
            Object[] obj = {st.getIdTicket(), st.getIdClient(), st.getIdTicketDetails(), st.getPriceAfterDiscount()};
            tableModelST.addRow(obj);
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No ticket or client having this id!");
        }
        catch (Exception e) {
        JOptionPane.showMessageDialog(null, "All fields are required!");
    }
    }

    JPanel removeSoldTicketById() {
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 400, 400);
        panel.setLayout(null);
        panel.setBackground(background);

        JLabel title = new JLabel("Enter an id to remove that sold ticket");
        title.setFont(font);
        title.setForeground(fontC);
        title.setBounds(50, 10, 400, 20);

        JLabel idLabel = new JLabel("Id of sold ticket:");
        idLabel.setBounds(5, 60, 200, 30);
        idLabel.setFont(font2);
        idLabel.setForeground(fontC);
        JTextField id = new JTextField();
        id.setBounds(120, 60, 200, 30);
        JButton button = new JButton("Remove ticket");
        button.setBounds(120, 125, 200, 40);
        styleForButton(button, c1, c2);
        button.addActionListener(event -> removeTicketByIdDB(id));
        panel.add(title);
        panel.add(id);
        panel.add(idLabel);
        panel.add(button);
        return panel;
    }

    void removeTicketByIdDB(JTextField id) {
        try {
            int i = Integer.parseInt(id.getText());
            SoldTicket st = soldTicketService.getSoldTicketById(i);
            if (st == null) {
                JOptionPane.showMessageDialog(null, "There is no ticket having this id!");
            }
            else {
                soldTicketService.removeTicketById(i);
                JOptionPane.showMessageDialog(null, "Ticket was succesfully removed!");
                replaceDataFromTable();
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "All fields are required!");
        }
    }

    static void replaceDataFromTable() throws SQLException {
        tableModelST.setRowCount(0);
        ArrayList<SoldTicket> soldTickets = soldTicketService.getSoldTickets();
        for(SoldTicket st : soldTickets) {
            Object[] obj = {st.getIdTicket(), st.getIdClient(), st.getIdTicketDetails(), st.getPriceAfterDiscount()};
            tableModelST.addRow(obj);
        }
    }

    public void getAllTickets(JPanel p) {
        JPanel panel = new JPanel();
        panel.setBounds(0, 35, 600, 150);
        panel.setLayout(null);

        JTable table = new JTable(tableModelST);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 0, 600, 150);
        try {
            replaceDataFromTable();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        panel.add(scrollPane);
        p.add(panel);
    }

    public JPanel updateTicketDetailsOfTicket() {
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 500, 400);
        panel.setLayout(null);
        panel.setBackground(background);

        JLabel title = new JLabel("Enter an id to update the ticket id of that sold ticket");
        title.setFont(font);
        title.setForeground(fontC);
        title.setBounds(25, 10, 500, 20);

        JLabel idLabel = new JLabel("Id of sold ticket:");
        idLabel.setBounds(5, 60, 200, 30);
        idLabel.setFont(font2);
        idLabel.setForeground(fontC);
        JTextField id = new JTextField();
        id.setBounds(120, 60, 200, 30);

        JLabel newIdTLabel = new JLabel("New ticket id:");
        newIdTLabel.setBounds(5, 110, 200, 30);
        newIdTLabel.setFont(font2);
        newIdTLabel.setForeground(fontC);
        JTextField newIdT = new JTextField();
        newIdT.setBounds(120, 110, 200, 30);
        JButton button = new JButton("Update ticket");
        button.setBounds(120, 175, 200, 40);
        button.addActionListener(event -> updateTicketDetailsOfTicketDB(id, newIdT));
        panel.add(title);
        panel.add(id);
        panel.add(idLabel);
        panel.add(newIdT);
        panel.add(newIdTLabel);
        panel.add(button);
        return panel;
    }

    void updateTicketDetailsOfTicketDB(JTextField id, JTextField newIdTicket) {
        try {
            int i = Integer.parseInt(id.getText());
            SoldTicket st = soldTicketService.getSoldTicketById(i);
            if (st == null) {
                JOptionPane.showMessageDialog(null, "There is no sold ticket having this id!");
            }
            else {
                int newI = Integer.parseInt(newIdTicket.getText());
                soldTicketService.updateTicketDetailsOfTicket(i, newI);
                JOptionPane.showMessageDialog(null, "Ticket was succesfully updated!");
                replaceDataFromTable();
            }
        }
        catch (SQLException s) {
            JOptionPane.showMessageDialog(null, "There is no ticket having this id!");
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "All fields are required!");
        }
    }

    public JPanel getSoldTicketById() {
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 600, 400);
        panel.setBackground(background);
        panel.setLayout(null);

        JLabel title = new JLabel("Enter an id to see that sold ticket");
        title.setFont(font);
        title.setForeground(fontC);
        title.setBounds(45, 10, 500, 20);

        JLabel idLabel = new JLabel("Id of sold ticket:");
        JTextField id = new JTextField();
        idLabel.setFont(font2);
        idLabel.setForeground(fontC);
        idLabel.setBounds(5, 60, 200, 30);
        id.setBounds(120, 60, 200, 30);

        JButton button = new JButton("Get ticket");
        button.setBounds(120, 125, 200, 40);
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
            SoldTicket t = soldTicketService.getSoldTicketById(i);
            if (t == null) {
                JOptionPane.showMessageDialog(null, "There is no sold ticket having this id!");
            }
            else {
                Object[] obj = {t.getIdTicket(), t.getIdClient(), t.getIdTicketDetails(), t.getPriceAfterDiscount()};

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
