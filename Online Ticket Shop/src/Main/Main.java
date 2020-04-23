package Main;

import Model.*;
import Service.*;
import Service.Audit.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.ArrayList;


public class Main {
    static IOFileService ioFileService = IOFileService.getInstance();
    static EventService eventService = EventService.getInstance();
    static LocationService locationService = LocationService.getInstance();
    static ClientService clientService = ClientService.getInstance();
    static TicketDetailsService ticketDetailsService = TicketDetailsService.getInstance();
    static SoldTicketService soldTicketService = SoldTicketService.getInstance();

    public static void eventMenu(Scanner in) {
        int eMenu = 0;

        do {
            System.out.println("Choose an option:\n");
            System.out.println( "1. Add an event\n" +
                                "2. Print all the events\n" +
                                "3. Find event by name\n" +
                                "4. Find a particular event given by id\n" +
                                "5. Update name of an event\n" +
                                "6. Remove an event by id\n" +
                                "0. Go back\n");

            System.out.println("Your option is:");
            eMenu = in.nextInt();
            in.nextLine();

            String name;
            int duration;
            double price;
            int id;

            switch(eMenu) {
                case 0: break;
                case 1: System.out.println("\nChoose a name: ");
                        name = in.nextLine();
                        System.out.println("\nChoose a price: ");
                        price = in.nextDouble();
                        System.out.println("\nChoose a duration: ");
                        duration = in.nextInt();
                        eventService.addEvent(new Event(name, duration, price));
                        break;
                case 2: System.out.println(eventService.getEvents());
                        break;
                case 3: System.out.println("\nName = ");
                        name = in.nextLine();
                        ArrayList<Event> e = eventService.findEventByName(name);
                        if(e.size() == 0)
                            System.out.println("No events with this name");
                        else System.out.println(e);
                        break;
                case 4: System.out.println("\nId = ");
                        id = in.nextInt();
                        in.nextLine();
                        Event ev = eventService.getEventById(id);
                        if(ev == null)
                            System.out.println("No event with this id");
                        else System.out.println(ev);
                        break;
                case 5: System.out.println("\nId = ");
                        id = in.nextInt();
                        in.nextLine();
                        System.out.println("\nName = ");
                        name = in.nextLine();
                        try {
                            eventService.updateNameEvent(id, name);
                        } catch (IllegalArgumentException ex)  {
                            System.out.println(ex.getMessage());
                        }
                        break;
                case 6: //remove event
                        System.out.println("\nId =");
                        id = in.nextInt();
                        in.nextLine();
                        eventService.removeEventByIdEvent(id);
                        break;
            }
        } while(eMenu != 0);
    }

    public static void ticketDetailsMenu(Scanner in) {
        int tdMenu = 0;

        do {
            System.out.println("Choose an option:\n");
            System.out.println( "1. Add details to a ticket\n" +
                    "2. Print all tickets\n" +
                    "3. Find ticket by date\n" +
                    "4. Find a particular ticket given by id\n" +
                    "5. Update hour of a ticket given by id\n" +
                    "6. Remove a ticket by id\n" +
                    "0. Go back\n");

            System.out.println("Your option is:");
            tdMenu = in.nextInt();
            in.nextLine();

            int idEvent, idLocation, id;
            String hour;
            int day, month, year;

            switch(tdMenu) {
                case 0: break;
                case 1: System.out.println("\nChoose an id for the event: ");
                        idEvent = in.nextInt();
                        System.out.println("\nChoose an id for the location: ");
                        idLocation = in.nextInt();
                        in.nextLine();
                        System.out.println("\nChoose an hour: ");
                        hour = in.nextLine();
                        System.out.println("\nChoose a date: ");
                        System.out.println("Day: ");
                        day = in.nextInt();
                        System.out.println("Month: ");
                        month = in.nextInt();
                        System.out.println("Year: ");
                        year = in.nextInt();
                        Date date = new Date(day, month, year);
                        try {
                               ticketDetailsService.addTicket(new TicketDetails(idEvent, idLocation, date, hour));
                        } catch (IllegalArgumentException e)  {
                                //the object was created, but not appended to repository and file
                                //decrement the current number of object
                                TicketDetails.setUniqueKey(TicketDetails.getUniqueKey() - 1);
                                System.out.println(e.getMessage());
                        }
                        break;
                case 2: System.out.println(ticketDetailsService.getTicketDetails());
                        break;
                case 3: System.out.println("\nChoose a date: ");
                        System.out.println("Day: ");
                        day = in.nextInt();
                        System.out.println("Month: ");
                        month = in.nextInt();
                        System.out.println("Year: ");
                        year = in.nextInt();
                        date = new Date(day, month, year);
                        ArrayList<TicketDetails> t = ticketDetailsService.findTicketByDate(date);
                        if(t.size() == 0)
                            System.out.println("No tickets on this day");
                        else System.out.println(t);
                        break;
                case 4: System.out.println("\nId = ");
                        id = in.nextInt();
                        in.nextLine();
                        TicketDetails td = ticketDetailsService.getTicketDetailsById(id);
                        if(td == null)
                            System.out.println("No ticket with this id");
                        else System.out.println(td);
                        break;
                case 5: System.out.println("\nId = ");        //try catch daca nu e ticket cu id
                        id = in.nextInt();
                        in.nextLine();
                        System.out.println("\nHour = ");
                        hour = in.nextLine();
                        try {
                            ticketDetailsService.updateTicketDetailsHourOfEvent(id, hour);
                        } catch(IllegalArgumentException e)  {
                            System.out.println(e.getMessage());
                        }
                        break;
                case 6: //remove ticket
                        System.out.println("\nId =");
                        id = in.nextInt();
                        in.nextLine();
                        ticketDetailsService.removeTicketById(id);
                        break;
            }
        } while(tdMenu != 0);
    }

    public static void soldTicketMenu(Scanner in) {
        int stMenu = 0;

        do {
            System.out.println("Choose an option:\n");
             System.out.println( "1. Add a sold ticket\n" +
                    "2. Print all tickets\n" +
                    "3. Find a particular ticket given by the client id\n" +
                    "4. Find ticket by id\n" +
                    "5. Update ticket details of ticket\n" +
                    "6. Remove a ticket by id\n" +
                    "0. Go back\n");

            System.out.println("Your option is:");
            stMenu = in.nextInt();
            in.nextLine();

            int id, idTicket, idClient;

            switch(stMenu) {
                case 0: break;
                case 1: System.out.println("\nChoose an id for the event: ");         //adauga try catch
                        idTicket = in.nextInt();
                        System.out.println("\nChoose an id for the client: ");
                        idClient = in.nextInt();
                        try {
                            soldTicketService.addTicket(new SoldTicket(idClient, idTicket));
                        } catch (IllegalArgumentException ex)  {
                            SoldTicket.setUniqueKey(SoldTicket.getUniqueKey() - 1);
                            System.out.println(ex.getMessage());
                        }
                        break;
                case 2: System.out.println(soldTicketService.getSoldTickets());
                        break;
                case 3: System.out.println("\nId = ");
                        idClient = in.nextInt();
                        in.nextLine();
                        ArrayList<SoldTicket> st = soldTicketService.findSoldTicketByClientId(idClient);
                        if(st.size() == 0)
                            System.out.println("No tickets of this client");
                        else System.out.println(st);
                        break;
                case 4: System.out.println("\nId = ");
                    id = in.nextInt();
                    in.nextLine();
                    SoldTicket sticket = soldTicketService.getSoldTicketById(id);
                    if(sticket == null)
                        System.out.println("No ticket with this id");
                    else System.out.println(sticket);
                    break;
                case 5: System.out.println("\nId = ");
                    id = in.nextInt();
                    in.nextLine();
                    System.out.println("\nId for ticket details = ");
                    idTicket = in.nextInt();
                    try {
                        soldTicketService.updateTicketDetailsOfTicket(id, idTicket);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 6: //remove ticket
                    System.out.println("\nId =");
                    id = in.nextInt();
                    in.nextLine();
                    soldTicketService.removeTicketById(id);
                    break;
            }
        } while(stMenu != 0);
    }

    public static void clientsMenu(Scanner in) {
        int cMenu = 0;

        do {
            System.out.println("Choose an option:\n");
            System.out.println( "1. Add a client\n" +
                    "2. Print all clients\n" +
                    "3. Find client by name\n" +
                    "4. Find client by id\n" +
                    "5. Update name of client\n" +
                    "6. Remove a client by id\n" +
                    "0. Go back\n");

            System.out.println("Your option is:");
            cMenu = in.nextInt();
            in.nextLine();

            String name, email, phone, noLegit;
            int type = 0, age, id;
            switch(cMenu) {
                case 0: break;
                case 1:
                    System.out.println("\nChoose a type:\n1. Child\n2. Student\n3. Adult\n4. Pensioner\n");
                    type = in.nextInt();
                    in.nextLine();
                    System.out.println("\nChoose a name: ");         //adauga try catch
                    name = in.nextLine();
                    System.out.println("\nChoose an email: ");
                    email = in.nextLine();
                    System.out.println("\nChoose a phone number: ");
                    phone = in.nextLine();
                    switch (type) {
                        case 1:
                            System.out.println("\nInsert age of child: ");
                            age = in.nextInt();
                            in.nextLine();
                            clientService.addClient(new Child(name, email, phone, age));
                            break;
                        case 2:
                            System.out.println("\nInsert no of Legitimation: ");
                            noLegit = in.nextLine();
                            clientService.addClient(new Student(name, email, phone, noLegit));
                            break;
                        case 3: clientService.addClient(new Adult(name, email, phone));
                            break;
                        case 4: clientService.addClient(new Pensioner(name, email, phone));
                            break;
                        default:break;
                    }
                    break;
                case 2: System.out.println(clientService.getClients());
                    break;
                case 3: System.out.println("\nName = ");
                    name = in.nextLine();
                    ArrayList<Client> cl = clientService.findClientByName(name);
                    if(cl.size() == 0)
                        System.out.println("No clients with this name");
                    else System.out.println(cl);
                    break;
                case 4: System.out.println("\nId = ");
                    id = in.nextInt();
                    in.nextLine();
                    Client c = clientService.getClientById(id);
                    if(c == null)
                        System.out.println("No client with this id");
                    else System.out.println(c);
                    break;
                case 5: System.out.println("\nId = ");
                    id = in.nextInt();
                    in.nextLine();
                    System.out.println("\nNew name = ");
                    name = in.nextLine();
                    try {
                        clientService.updateClientName(id, name);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 6: //remove client
                    System.out.println("\nId =");
                    id = in.nextInt();
                    in.nextLine();
                    clientService.removeClientById(id);
                    break;
            }
        } while(cMenu != 0);
    }

    public static void locationsMenu(Scanner in) {
        int locMenu = 0;

        do {
            System.out.println("Choose an option:\n");
            System.out.println( "1. Add a location\n" +
                    "2. Print all locations\n" +
                    "3. Find a particular location by city\n" +
                    "4. Find a location by id\n" +
                    "5. Update location venue\n" +
                    "6. Remove a location by id\n" +
                    "0. Go back\n");

            System.out.println("Your option is:");
            locMenu = in.nextInt();
            in.nextLine();

            int id;
            String name, city, country, venue;

            switch(locMenu) {
                case 0: break;
                case 1: System.out.println("\nInsert the name of the location: ");
                    name = in.nextLine();
                    System.out.println("\n: Insert a country: ");
                    country = in.nextLine();
                    System.out.println("\n: Insert a city: ");
                    city = in.nextLine();
                    System.out.println("\n: Insert the name of the venue:");
                    venue = in.nextLine();
                    locationService.addLocationInService(new Location(venue, country, city, name));
                    break;
                case 2: System.out.println(locationService.getLocations());
                        break;
                case 3:
                        System.out.println("\nCity = ");
                        city = in.nextLine();
                        ArrayList<Location> loc = locationService.findLocationByCity(city);
                        if(loc.size() == 0)
                            System.out.println("No location in this city");
                        else System.out.println(loc);
                        break;
                case 4: System.out.println("\nId = ");
                        id = in.nextInt();
                        in.nextLine();
                        Location l = locationService.getLocationById(id);
                        if(l == null)
                            System.out.println("No location with this id");
                        else System.out.println(l);
                        break;
                case 5: System.out.println("\nId = ");
                        id = in.nextInt();
                        in.nextLine();
                        System.out.println("\nNew Venue = ");
                        venue = in.nextLine();
                        try {
                            locationService.updateLocationVenue(id, venue);
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                case 6: //remove location
                        System.out.println("\nId =");
                        id = in.nextInt();
                        in.nextLine();
                        locationService.removeLocationById(id);
                        break;
            }
        } while(locMenu != 0);
    }

    public static void main(String[] args) {

        FileEvent fileEvent = FileEvent.getInstance();
        FileClient fileClient = FileClient.getInstance();
        FileLocation fileLocation = FileLocation.getInstance();
        FileTicketDetails fileTicketDetails = FileTicketDetails.getInstance();
        FileSoldTicket fileSoldTicket = FileSoldTicket.getInstance();

        ioFileService.loadFromFile(fileEvent, "events.csv");
        ioFileService.loadFromFile(fileClient, "clients.csv");
        ioFileService.loadFromFile(fileLocation, "locations.csv");
        ioFileService.loadFromFile(fileTicketDetails, "detailsOfTickets.csv");
        ioFileService.loadFromFile(fileSoldTicket, "soldTickets.csv");


        int menu1 = 0;
        Scanner in = new Scanner(System.in);

        do {
            System.out.println("Choose an option:\n\n" +
                               "1. Events\n2. Ticket Details\n3. Sold Tickets\n4. Clients\n5. Locations\n0. Exit");
            System.out.println("\nYour option is: ");
            menu1 = in.nextInt();
            in.nextLine();


            switch (menu1) {
                case 0: break;
                case 1: eventMenu(in);
                        break;
                case 2: ticketDetailsMenu(in);
                        break;
                case 3: soldTicketMenu(in);
                        break;
                case 4: clientsMenu(in);
                        break;
                case 5: locationsMenu(in);
                        break;

            }
           } while(menu1 != 0);

    }

}
