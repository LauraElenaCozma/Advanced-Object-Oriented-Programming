package com.company;

import Model.*;
import Service.*;

import java.sql.SQLOutput;

public class Main {

    public static void main(String[] args) {

        EventService eventService = EventService.getInstance();
        LocationService locationService = LocationService.getInstance();
        ClientService clientService = ClientService.getInstance();
        TicketDetailsService ticketDetailsService = TicketDetailsService.getInstance();
        SoldTicketService soldTicketService = SoldTicketService.getInstance();

        //add 5 events
        eventService.addEvent(new Event("Visul unei nopti de vara", 120 , 60));
        eventService.addEvent(new Event("Toti fiii mei", 200 , 70));
        eventService.addEvent(new Event("Visul de vara", 300 , 80));
        eventService.addEvent(new Event("(D)efectul Placebo" ,60 , 80));
        eventService.addEvent(new Event("Toti fiii mei", 100 , 150));

        //add 4 locations
        locationService.addLocationInService(new Location("Studio" , "Romania" , "Bucuresti" , "TNB"));
        locationService.addLocationInService(new Location("Artist" , "Romania" , "Bucuresti" , "TNB"));
        locationService.addLocationInService(new Location("C1" , "Romania" , "Iasi" , "Teatrul National Iasi"));
        locationService.addLocationInService(new Location("Sala mare" , "Romania" , "Bucuresti" , "TNB"));

        //add 6 clients
        clientService.addClient(new Child("Ionescu Andrei" , "andrei.ionescu@gmail.com" , "0723430129" , 10));
        clientService.addClient(new Student("Irimia Miruna" , "mirunairimia99@gmail.com", "0233751231", "249802"));
        clientService.addClient(new Pensioner("Mihalache Ion" , "mihaio@gmail.com", "0728739401"));
        clientService.addClient(new Adult("Popa Monica" , "mo29niq@yahoo.com", "0724487762"));
        clientService.addClient(new Adult("Ion Constantin" , "ionConstantin@yahoo.com", "0724487762"));
        clientService.addClient(new Pensioner("Popa Monica" , "monlog@yahoo.com", "0743251242"));

        //add 7 available shows
        ticketDetailsService.addTicket(new TicketDetails(eventService.getEventById(1) , locationService.getLocationById(2) ,new Date(30 , 6 , 2020) , "19:30"));
        ticketDetailsService.addTicket(new TicketDetails(eventService.getEventById(1) , locationService.getLocationById(1) ,new Date(2 , 7 , 2020) , "19:00"));
        ticketDetailsService.addTicket(new TicketDetails(eventService.getEventById(2) , locationService.getLocationById(2) ,new Date(12 , 2 , 2020) , "20:30"));
        ticketDetailsService.addTicket(new TicketDetails(eventService.getEventById(3) , locationService.getLocationById(4) ,new Date(30 , 6 , 2020) , "19:00"));
        ticketDetailsService.addTicket(new TicketDetails(eventService.getEventById(4) , locationService.getLocationById(1) ,new Date(30 , 6 , 2020) , "19:00"));
        ticketDetailsService.addTicket(new TicketDetails(eventService.getEventById(5) , locationService.getLocationById(3) ,new Date(30 , 6 , 2020) , "19:00"));
        ticketDetailsService.addTicket(new TicketDetails(eventService.getEventById(4) , locationService.getLocationById(2) ,new Date(30 , 6 , 2020) , "19:00"));

        //add 6 sold tickets
        soldTicketService.addTicket(new SoldTicket(clientService.getClientById(1) , ticketDetailsService.getTicketDetailsById(2)));
        soldTicketService.addTicket(new SoldTicket(clientService.getClientById(1) , ticketDetailsService.getTicketDetailsById(1)));
        soldTicketService.addTicket(new SoldTicket(clientService.getClientById(1) , ticketDetailsService.getTicketDetailsById(3)));
        soldTicketService.addTicket(new SoldTicket(clientService.getClientById(2) , ticketDetailsService.getTicketDetailsById(2)));
        soldTicketService.addTicket(new SoldTicket(clientService.getClientById(3) , ticketDetailsService.getTicketDetailsById(5)));
        soldTicketService.addTicket(new SoldTicket(clientService.getClientById(4) , ticketDetailsService.getTicketDetailsById(6)));
        soldTicketService.addTicket(new SoldTicket(clientService.getClientById(5) , ticketDetailsService.getTicketDetailsById(5)));

        //print the Clients
        System.out.println("The clients are:");
        System.out.println(clientService.getClients());

        //find operations on ClientService
        System.out.println("Find client by name: Popa Monica");
        System.out.println(clientService.findClientByName("Popa Monica"));

        //update operation on ClientService
        System.out.println();
        System.out.println("Updating clients...");
        clientService.updateClientName("Mihalache Ion" , "Mihalache Ionel");
        clientService.updateClientName(2 , "Irimia Miruna Ina");
        clientService.updateClientEmail(3 , "mihaio@yahoo.ro");
        clientService.updateClientPhone(4, "0727579080");
        System.out.println("Updated clients are:");
        System.out.println(clientService.getClients());
        System.out.println();


        //print Events
        System.out.println("The events are:");
        System.out.println(eventService.getEvents());

        //find operations on EventService
        System.out.println("Find event by name: Toti fiii mei");
        System.out.println(eventService.findEventByName("Toti fiii mei"));

        //update operations on EventService
        System.out.println("Updating the events...");
        eventService.updateNameEvent("Visul de vara" , "Lut");
        eventService.updateNameEvent(2 , "Lut");
        eventService.updateDurationEventById(2, 65);
        eventService.updatePriceEventById(2, 50);

        System.out.println("Events after update:");
        System.out.println(eventService.getEvents());
        System.out.println();
        System.out.println();

        //print ticketDetails
        System.out.println("The available tickets are:");
        System.out.println(ticketDetailsService.getTicketDetails());

        //find operations on tickets
        System.out.println("Find tickets by event:");
        System.out.println(ticketDetailsService.findTicketByEvent(eventService.getEventById(4)));
        System.out.println("Find tickets by location:");
        System.out.println(ticketDetailsService.findTicketByLocation(locationService.getLocationById(2)));
        System.out.println("Find tickets by date:");
        System.out.println(ticketDetailsService.findTicketByDate(new Date(30 , 6 , 2021)));

        //update operations on tickets
        System.out.println("Update the tickets...");
        ticketDetailsService.updateTicketDetailsEventOfEvent(eventService.getEventById(1) , eventService.getEventById(5));
        ticketDetailsService.updateTicketDetailsEventOfEvent(6 , eventService.getEventById(4));
        ticketDetailsService.updateTicketDetailsLocationOfEvent(locationService.getLocationById(1) , locationService.getLocationById(3));
        ticketDetailsService.updateTicketDetailsLocationOfEvent(2 , locationService.getLocationById(4));
        ticketDetailsService.updateTicketDetailsDateOfEvent(new Date(12 , 2 , 2020) , new Date(12 , 8 , 2020));
        ticketDetailsService.updateTicketDetailsDateOfEvent(1 , new Date(12 , 8 , 2020));
        ticketDetailsService.updateTicketDetailsHourOfEvent(5 , "20:20");


        System.out.println("The tickets after update");
        System.out.println(ticketDetailsService.getTicketDetails());
        System.out.println();
        System.out.println();

        //print sold tickets
        System.out.println("Sold Tickets are:");
        System.out.println(soldTicketService.getSoldTickets());

        //find operations in sold tickets
        System.out.println("Find by a particular ticket(id 2)");
        System.out.println(soldTicketService.findSoldTicketByTicketDetails(ticketDetailsService.getTicketDetailsById(2)));
        System.out.println("Find by a particular ticket(id 9)");
        System.out.println(soldTicketService.findSoldTicketByTicketDetails(ticketDetailsService.getTicketDetailsById(9)));
        System.out.println("Find by a particular Client(id 1)");
        System.out.println(soldTicketService.findSoldTicketByClient(clientService.getClientById(1)));

        //update operations in sold tickets
        System.out.println("Sold tickets are updating");
        soldTicketService.updateTicketDetailsOfTicket(ticketDetailsService.getTicketDetailsById(1) , ticketDetailsService.getTicketDetailsById(2));
        soldTicketService.updateTicketDetailsOfTicket(1 , ticketDetailsService.getTicketDetailsById(4));
        soldTicketService.updateClientOfTicket(clientService.getClientById(1) , clientService.getClientById(3));
        soldTicketService.updateClientOfTicket(4 , clientService.getClientById(2));

        System.out.println("Sold tickets after update");
        System.out.println(soldTicketService.getSoldTickets());
        System.out.println();
        System.out.println();

        //print locations
        System.out.println(locationService.getLocations());

        //find operations on locationService
        System.out.println("Locations in TNB:");
        System.out.println(locationService.findLocationByName("TNB"));
        System.out.println("Locations in Cluj");
        System.out.println(locationService.findLocationByCity("Cluj"));

        //update operations on locationService
        System.out.println("Updating locations...");
        locationService.updateLocationName(1, "Teatrul Avangardia");
        locationService.updateLocationCity(3, "Roma");
        locationService.updateLocationCountry(3, "Italia");
        locationService.updateLocationVenue(3 , "C7");

        System.out.println("Locations after update");
        System.out.println(locationService.getLocations());
        System.out.println();
        System.out.println();


        //remove operation
        //remove an event. The available tickets and sold tickets with that event will be deleted also
        System.out.println("Remove event with id 3");
        eventService.removeEventByIdEvent(3, ticketDetailsService, soldTicketService);
        //from ticketDetails and SoldTicket
        System.out.println("Remove ticket details with id 3");
        ticketDetailsService.removeTicketById(3, soldTicketService);
        //only from SoldTicket
        System.out.println("Remove sold ticket with id 6");
        soldTicketService.removeTicketById(6);
        System.out.println("Remove sold ticket by Client name");
        soldTicketService.removeTicketByClientName("Ion Constantin");
        System.out.println(eventService.getEvents() + "\n");
        System.out.println(ticketDetailsService.getTicketDetails() + "\n");
        System.out.println(soldTicketService.getSoldTickets() + "\n");

        //remove from clients
        clientService.removeClientById(3, soldTicketService);
        clientService.removeClientByName("Irimia Miruna Ina", soldTicketService);
        System.out.println(clientService.getClients());
        System.out.println(soldTicketService.getSoldTickets());

        clientService.addClient(new Adult("Ion Constantin" , "ionConstantin@yahoo.com", "0724487762"));
        clientService.addClient(new Pensioner("Popa Monica" , "monlog@yahoo.com", "0743251242"));
        soldTicketService.addTicket(new SoldTicket(clientService.getClientById(1) , ticketDetailsService.getTicketDetailsById(6)));
        soldTicketService.addTicket(new SoldTicket(clientService.getClientById(7) , ticketDetailsService.getTicketDetailsById(2)));
        soldTicketService.addTicket(new SoldTicket(clientService.getClientById(8) , ticketDetailsService.getTicketDetailsById(7)));

        System.out.println("Print locations and sold tickets before removing them");
        System.out.println(locationService.getLocations() + "\n");
        System.out.println(soldTicketService.getSoldTickets());

        locationService.removeLocationById(2, ticketDetailsService, soldTicketService);
        System.out.println("Print locations, ticket details and sold tickets after removing them");
        System.out.println(locationService.getLocations() + "\n");
        System.out.println(ticketDetailsService.getTicketDetails() + "\n");
        System.out.println(soldTicketService.getSoldTickets());
    }
}
