package Repository;

import Model.Client;

import java.util.Comparator;

public class ComparatorClients implements Comparator<Client> {
    @Override
    public int compare(Client c1, Client c2) {
        return c1.getName().compareToIgnoreCase(c2.getName());
    }
}
