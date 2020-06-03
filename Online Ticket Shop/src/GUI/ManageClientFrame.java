package GUI;

import Model.*;
import Model.Event;
import Service.ClientService;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;
import Thread.ThreadUpdateClientName;

public class ManageClientFrame {
    private Connection con;
    private ClientService clientService= ClientService.getInstance();
    JPanel f = new JPanel();
    String columns[] = {"Id Client", "Type", "Name", "Email", "Phone number", "Age", "No. Legitimation"};
    DefaultTableModel tableModelClient = new DefaultTableModel(columns, 0);
    Color background = new Color(164, 189, 186);
    Color fontC = new Color(0, 0, 0);
    Font font = new Font("Tahoma", Font.PLAIN, 18);
    Font font2 = new Font("Tahoma", Font.PLAIN, 14);
    Color c1 = new Color(241, 241, 240);
    Color c2 = new Color(0,0,0);

    public JPanel getPanel() {
        return f;
    }


    public ManageClientFrame(String title) throws SQLException {

        JButton add = new JButton("Add Client");
        add.setBounds(20, 55, 200, 50);
        styleForButton(add, c2, c1);
        JButton remove = new JButton("Remove Client");
        remove.setBounds(20, 185, 200, 50);
        styleForButton(remove, c1,c2);
        JButton update = new JButton("Update Client");
        update.setBounds(20, 315, 200, 50);
        styleForButton(update, c2,c1);
        JButton get = new JButton("Get Client");
        get.setBounds(20, 445, 200, 50);
        styleForButton(get, c1, c2);
        JPanel ptable = new JPanel();
        ptable.setBounds(300, 20, 700, 200);
        ptable.setLayout(null);
        ptable.setBackground(background);
        JLabel titleTable = new JLabel("All the clients are here:");
        titleTable.setFont(font);
        titleTable.setForeground(fontC);
        titleTable.setBounds(50, 0, 300, 20);
        ptable.add(titleTable);
        getClients(ptable);

        JPanel paction = new JPanel();
        paction.setLayout(null);
        paction.setBounds(300, 230, 700, 500);
        paction.setBackground(background);

        add.addActionListener(event -> putPanel(paction, addClient()));
        remove.addActionListener(event -> putPanel(paction, removeClientById()));
        update.addActionListener(event -> putPanel(paction, updateClientName()));
        get.addActionListener(event -> putPanel(paction, getClientById()));
        putPanel(paction, addClient()); //implicit avem interfata de la add
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
        root.removeAll();
        root.repaint();
        root.revalidate();

        root.add(p);
        root.repaint();
        root.revalidate();
    }

    private void replaceDataFromTable() throws SQLException {
        tableModelClient.setRowCount(0);
        Set<Client> clients = clientService.getClients();
        for(Client c : clients) {
           if(c instanceof Child) {
               Child ch = (Child)c;
               Object[] obj = {ch.getIdClient(), "Child", ch.getName(), ch.getEmail(), ch.getPhoneNumber(), ch.getAge(), null};
               tableModelClient.addRow(obj);
           }
            else if(c instanceof Student) {
                Student s = (Student)c;
                Object[] obj = {s.getIdClient(), "Student", s.getName(), s.getEmail(), s.getPhoneNumber(), null, s.getNoLegit()};
                tableModelClient.addRow(obj);
            }
           else if(c instanceof Adult) {
               Object[] obj = {c.getIdClient(), "Adult", c.getName(), c.getEmail(), c.getPhoneNumber(), null, null};
               tableModelClient.addRow(obj);
           }
           else if(c instanceof Pensioner) {
               Object[] obj = {c.getIdClient(), "Pensioner", c.getName(), c.getEmail(), c.getPhoneNumber(), null, null};
               tableModelClient.addRow(obj);
           }
        }
    }
    public void getClients(JPanel root) {
        JPanel panel = new JPanel();
        panel.setBounds(0, 35, 600, 150);
        panel.setLayout(null);

        JTable table = new JTable(tableModelClient);
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
    public JPanel addClient() {
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 600, 400);
        panel.setLayout(null);
        panel.setBackground(background);

        JPanel panel2 = new JPanel();
        panel2.setBounds(0, 0, 600, 400);
        panel2.setBackground(background);
        panel2.setLayout(null);

        JLabel title = new JLabel("Choose your type");
        title.setFont(font);
        title.setForeground(fontC);
        title.setBounds(145, 10, 500, 20);

        ButtonGroup group = new ButtonGroup();

        JRadioButton radio1 = new JRadioButton("Child");
        radio1.setFont(font2);
        radio1.setForeground(fontC);
        radio1.setBackground(background);
        group.add(radio1);
        radio1.setBounds(0, 70, 100, 30);

        JRadioButton radio2 = new JRadioButton("Student");
        radio2.setFont(font2);
        radio2.setForeground(fontC);
        radio2.setBackground(background);
        group.add(radio2);
        radio2.setBounds(120, 70, 100, 30);

        JRadioButton radio3 = new JRadioButton("Adult");
        radio3.setFont(font2);
        radio3.setForeground(fontC);
        radio3.setBackground(background);
        group.add(radio3);
        radio3.setBounds(240, 70, 100, 30);

        JRadioButton radio4 = new JRadioButton("Pensioner");
        radio4.setFont(font2);
        radio4.setForeground(fontC);
        radio4.setBackground(background);
        group.add(radio4);
        radio4.setBounds(360, 70, 100, 30);

        JButton button = new JButton("Ok");
        button.setBounds(118, 135, 200, 40);
        styleForButton(button, c1, c2);
        JPanel panel1 = new JPanel();
        panel1.setBounds(0, 0, 600, 250);
        panel1.setBackground(background);
        button.addActionListener(event -> typeOfClient(radio1, radio2, radio3, radio4, panel, panel1, panel2));

        panel1.setLayout(null);
        panel1.add(radio1);
        panel1.add(radio2);
        panel1.add(radio3);
        panel1.add(radio4);
        panel1.add(button);
        panel1.add(title);
        panel.add(panel1);

        return panel;
    }
    public void typeOfClient(JRadioButton radio1, JRadioButton radio2, JRadioButton radio3, JRadioButton radio4, JPanel panel, JPanel panel1, JPanel panel2) {
        JLabel nameLabel = new JLabel("Name: ");
        JTextField name = new JTextField();
        nameLabel.setFont(font2);
        nameLabel.setForeground(fontC);
        JLabel emailLabel = new JLabel("Email: ");
        emailLabel.setFont(font2);
        emailLabel.setForeground(fontC);
        JTextField email = new JTextField();
        JLabel phoneLabel = new JLabel("Phone: ");
        phoneLabel.setFont(font2);
        phoneLabel.setForeground(fontC);
        JTextField phone = new JTextField();
        JLabel ageLabel = new JLabel("Age: ");
        ageLabel.setFont(font2);
        ageLabel.setForeground(fontC);
        JTextField age = new JTextField();
        JLabel noLegitLabel = new JLabel("No. legit: ");
        noLegitLabel.setFont(font2);
        noLegitLabel.setForeground(fontC);
        JTextField noLegit = new JTextField();

        panel.remove(panel1);
        panel.repaint();
        panel.revalidate();
        panel2.removeAll();

        if (radio1.isSelected()) {
            JLabel title = new JLabel("You are a child. Fill the fiels to register");
            title.setFont(font);
            title.setForeground(fontC);
            title.setBounds(50, 10, 500, 20);

            nameLabel.setBounds(10, 60, 200, 30);
            name.setBounds(70, 60, 200, 30);

            emailLabel.setBounds(10, 110, 200, 30);
            email.setBounds(70, 110, 200, 30);

            phoneLabel.setBounds(10, 160, 200, 30);
            phone.setBounds(70, 160, 200, 30);

            ageLabel.setBounds(10, 210, 200, 30);
            age.setBounds(70, 210, 200, 30);

            JButton button1 = new JButton("Back");
            button1.setBounds(0, 260, 200, 40);
            button1.addActionListener(event -> back(panel2, panel, panel1));
            styleForButton(button1,c2,c1);
            JButton button2 = new JButton("Add");
            button2.setBounds(230, 260, 200, 40);
            button2.addActionListener(event -> addClient(1, name, email, phone, age, noLegit));
            styleForButton(button2,c1,c2);
            panel2.add(title);
            panel2.add(name);
            panel2.add(nameLabel);
            panel2.add(email);
            panel2.add(emailLabel);
            panel2.add(phone);
            panel2.add(phoneLabel);
            panel2.add(age);
            panel2.add(ageLabel);
            panel2.add(button1);
            panel2.add(button2);
            panel.add(panel2);
            panel.repaint();
            panel.revalidate();

        }

        if (radio2.isSelected()) {
            JLabel title = new JLabel("You are a student. Fill the fiels to register");
            title.setFont(font);
            title.setForeground(fontC);
            title.setBounds(50, 10, 500, 20);
            panel2.add(title);

            nameLabel.setBounds(10, 60, 200, 30);
            name.setBounds(70, 60, 200, 30);


            emailLabel.setBounds(10, 110, 200, 30);
            email.setBounds(70, 110, 200, 30);

            phoneLabel.setBounds(10, 160, 200, 30);
            phone.setBounds(70, 160, 200, 30);

            noLegitLabel.setBounds(10, 210, 200, 30);
            noLegit.setBounds(70, 210, 200, 30);

            JButton button1 = new JButton("Back");
            button1.setBounds(0, 260, 200, 40);
            button1.addActionListener(event -> back(panel2, panel, panel1));
            styleForButton(button1,c2,c1);
            JButton button2 = new JButton("Add");
            button2.setBounds(230, 260, 200, 40);
            button2.addActionListener(event -> addClient(2, name, email, phone, age, noLegit));
            styleForButton(button2,c1,c2);
            panel2.add(name);
            panel2.add(nameLabel);
            panel2.add(email);
            panel2.add(emailLabel);
            panel2.add(phone);
            panel2.add(phoneLabel);
            panel2.add(noLegit);
            panel2.add(noLegitLabel);
            panel2.add(button1);
            panel2.add(button2);
        }

        if (radio3.isSelected()) {
            JLabel title = new JLabel("You are an adult. Fill the fiels to register");
            title.setFont(font);
            title.setForeground(fontC);
            title.setBounds(50, 10, 500, 20);
            panel2.add(title);

            nameLabel.setBounds(10, 60, 200, 30);
            name.setBounds(70, 60, 200, 30);


            emailLabel.setBounds(10, 110, 200, 30);
            email.setBounds(70, 110, 200, 30);

            phoneLabel.setBounds(10, 160, 200, 30);
            phone.setBounds(70, 160, 200, 30);

            JButton button1 = new JButton("Back");
            button1.setBounds(0, 210, 200, 40);
            button1.addActionListener(event -> back(panel2, panel, panel1));
            styleForButton(button1,c2,c1);

            JButton button2 = new JButton("Add");
            button2.setBounds(230, 210, 200, 40);
            button2.addActionListener(event -> addClient(3, name, email, phone, age, noLegit));
            styleForButton(button2, c1, c2);

            panel2.add(name);
            panel2.add(nameLabel);
            panel2.add(email);
            panel2.add(emailLabel);
            panel2.add(phone);
            panel2.add(phoneLabel);
            panel2.add(button1);
            panel2.add(button2);


        }

        if (radio4.isSelected()) {
            JLabel title = new JLabel("You are a pensioner. Fill the fiels to register");
            title.setFont(font);
            title.setForeground(fontC);
            title.setBounds(50, 10, 500, 20);
            panel2.add(title);

            nameLabel.setBounds(10, 60, 200, 30);
            name.setBounds(70, 60, 200, 30);


            emailLabel.setBounds(10, 110, 200, 30);
            email.setBounds(70, 110, 200, 30);

            phoneLabel.setBounds(10, 160, 200, 30);
            phone.setBounds(70, 160, 200, 30);

            JButton button1 = new JButton("Back");
            button1.setBounds(0, 210, 200, 40);
            button1.addActionListener(event -> back(panel2, panel, panel1));
            styleForButton(button1,c2,c1);

            JButton button2 = new JButton("Add");
            button2.setBounds(230, 210, 200, 40);
            button2.addActionListener(event -> addClient(4, name, email, phone, age, noLegit));
            styleForButton(button2, c1, c2);

            panel2.add(name);
            panel2.add(nameLabel);
            panel2.add(email);
            panel2.add(emailLabel);
            panel2.add(phone);
            panel2.add(phoneLabel);
            panel2.add(button1);
            panel2.add(button2);

        }
        panel.add(panel2);
        panel.repaint();
        panel.revalidate();
    }

    public void back(JPanel p, JPanel panel, JPanel panel1) {
        panel.remove(p);
        panel.repaint();
        panel.revalidate();

        panel.add(panel1);
        panel.repaint();
        panel.revalidate();
    }

    public void addClient(int type, JTextField name, JTextField email, JTextField phone, JTextField age, JTextField noLegit) {
        try {
            String n = name.getText();
            String e = email.getText();
            String ph = phone.getText();

            switch(type) {
                case 1:
                    int a = Integer.parseInt(age.getText());
                    if(n.length() == 0 || e.length() == 0 || ph.length() == 0) {
                        JOptionPane.showMessageDialog(null, "All fields are required!");
                    }
                    else {
                        clientService.addClient(new Child(n, e, ph, a));
                        JOptionPane.showMessageDialog(null, "Child was succesfully added!");
                        replaceDataFromTable();

                    }

                    break;
                case 2:
                    String nl = noLegit.getText();
                    if(nl.length() == 0 || n.length() == 0 || e.length() == 0 || ph.length() == 0) {
                        JOptionPane.showMessageDialog(null, "All fields are required!");
                    }
                    else {

                        clientService.addClient(new Student(n, e, ph, nl));
                        JOptionPane.showMessageDialog(null, "Student was succesfully added!");
                        replaceDataFromTable();
                    }
                    break;
                case 3:if(n.length() == 0 || e.length() == 0 || ph.length() == 0) {
                            JOptionPane.showMessageDialog(null, "All fields are required!");
                        }
                    else {
                        clientService.addClient(new Adult(n, e, ph));
                        JOptionPane.showMessageDialog(null, "Adult was succesfully added!");
                        replaceDataFromTable();
                    }
                    break;
                case 4:if(n.length() == 0 || e.length() == 0 || ph.length() == 0) {
                        JOptionPane.showMessageDialog(null, "All fields are required!");
                    }
                    else {
                        clientService.addClient(new Pensioner(n, e, ph));
                        JOptionPane.showMessageDialog(null, "Pensioner was succesfully added!");
                        replaceDataFromTable();
                    }
                    break;
                default:
                    break;
            }


        }
        catch (SQLException s) {
            JOptionPane.showMessageDialog(null, "Email, phone must be unique!");
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "All fields are required!");
        }
    }
    JPanel removeClientById() {
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 600, 400);
        panel.setBackground(background);
        panel.setLayout(null);

        JLabel title = new JLabel("Enter an id to remove that client");
        title.setFont(font);
        title.setForeground(fontC);
        title.setBounds(50, 10, 400, 20);

        JLabel idLabel = new JLabel("Id of client:");
        idLabel.setBounds(5, 60, 200, 30);
        idLabel.setFont(font2);
        idLabel.setForeground(fontC);
        JTextField id = new JTextField();
        id.setBounds(90, 60, 200, 30);
        JButton button = new JButton("Remove client");
        button.setBounds(90, 125, 200, 40);
        button.addActionListener(event -> removeClientByIdDB(id));
        styleForButton(button, c1, c2);
        panel.add(title);
        panel.add(id);
        panel.add(idLabel);
        panel.add(button);
        return panel;
    }

    void removeClientByIdDB(JTextField id) {
        try {
            int i = Integer.parseInt(id.getText());
            Client c = clientService.getClientById(i);
            if (c == null) {
                JOptionPane.showMessageDialog(null, "There is no client having this id!");
            }
            else {
                clientService.removeClientById(i);
                JOptionPane.showMessageDialog(null, "Client was succesfully removed!");
                replaceDataFromTable();
                ManageSoldTicketFrame.replaceDataFromTable();
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "All fields are required!");
        }

    }

    JPanel updateClientName() {
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 600, 400);
        panel.setLayout(null);
        panel.setBackground(background);
        JLabel title = new JLabel("Enter the id of a client to update the name");
        title.setFont(font);
        title.setForeground(fontC);
        title.setBounds(35, 10, 500, 20);
        JLabel idLabel = new JLabel("Id of client: ");
        idLabel.setBounds(5, 60, 200, 30);
        idLabel.setFont(font2);
        idLabel.setForeground(fontC);
        JTextField id = new JTextField();
        id.setBounds(90, 60, 200, 30);

        JLabel nameLabel = new JLabel("New name: ");
        nameLabel.setBounds(5, 110, 200, 30);
        nameLabel.setFont(font2);
        nameLabel.setForeground(fontC);
        JTextField name = new JTextField();
        name.setBounds(90, 110, 200, 30);

        JButton button = new JButton("Update client");
        button.setBounds(90, 175, 200, 40);
        styleForButton(button, c1, c2);
        button.addActionListener(event -> updateClientNameDB(id, name));
        panel.add(title);
        panel.add(id);
        panel.add(idLabel);
        panel.add(name);
        panel.add(nameLabel);
        panel.add(button);

        return panel;
    }

    void updateClientNameDB(JTextField id, JTextField name) {
        try {

            int i = Integer.parseInt(id.getText());
            Client c = clientService.getClientById(i);
            if (c == null) {
                JOptionPane.showMessageDialog(null, "There is no client having this id!");
            }
            else {
                String n = name.getText();
                if (n.length() == 0)
                {
                    JOptionPane.showMessageDialog(null, "All fields are required!");
                }
                else {

                    ThreadUpdateClientName thread = new ThreadUpdateClientName(i, n);
                    thread.start();

                    try
                    {
                        thread.join();
                    }
                    catch (InterruptedException ex)
                    {
                        System.out.println("Thread error!");
                    }

                    JOptionPane.showMessageDialog(null, "Client was succesfully updated!");
                    replaceDataFromTable();
                }

            }

        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "All fields are required!");
        }

    }

    public JPanel getClientById() {
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 600, 400);
        panel.setBackground(background);
        panel.setLayout(null);

        JLabel title = new JLabel("Enter an id to see that client");
        title.setFont(font);
        title.setForeground(fontC);
        title.setBounds(50, 10, 500, 20);

        JLabel idLabel = new JLabel("Id of client:");
        JTextField id = new JTextField();
        idLabel.setBounds(5, 60, 200, 30);
        idLabel.setFont(font2);
        idLabel.setForeground(fontC);
        id.setBounds(90, 60, 200, 30);

        JButton button = new JButton("Get client");
        button.setBounds(90, 125, 200, 40);
        styleForButton(button, c1, c2);
        button.addActionListener(event -> getClientByIdDB(id, panel));

        panel.add(title);
        panel.add(id);
        panel.add(idLabel);
        panel.add(button);

        return panel;

    }

    void getClientByIdDB(JTextField id, JPanel panel) {
        try {
            int i = Integer.parseInt(id.getText());
            Client c = clientService.getClientById(i);
            if (c == null) {
                JOptionPane.showMessageDialog(null, "There is no client having this id!");
            }
            else {
                DefaultTableModel tGet = new DefaultTableModel(columns, 0);
                JTable tTick = new JTable(tGet);
                tTick.setBounds(0,0, 600, 50);
                JScrollPane scrollPane = new JScrollPane(tTick);
                scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                scrollPane.setBounds(0, 190, 600, 50);

                if(c instanceof Child) {
                    Child ch = (Child)c;
                    Object[] obj = {ch.getIdClient(), "Child", ch.getName(), ch.getEmail(), ch.getPhoneNumber(), ch.getAge(), null};
                    tGet.addRow(obj);
                }
                else if(c instanceof Student) {
                    Student s = (Student)c;
                    Object[] obj = {s.getIdClient(), "Student", s.getName(), s.getEmail(), s.getPhoneNumber(), null, s.getNoLegit()};
                    tGet.addRow(obj);
                }
                else if(c instanceof Adult) {
                    Object[] obj = {c.getIdClient(), "Adult", c.getName(), c.getEmail(), c.getPhoneNumber(), null, null};
                    tGet.addRow(obj);
                }
                else if(c instanceof Pensioner) {
                    Object[] obj = {c.getIdClient(), "Pensioner", c.getName(), c.getEmail(), c.getPhoneNumber(), null, null};
                    tGet.addRow(obj);
                }

                panel.add(scrollPane);
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "All fields are required!");
        }
    }
}
