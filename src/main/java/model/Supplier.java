package model;

import de.danielbechler.diff.annotation.ObjectDiffProperty;

public class Supplier {

    private String name;
    private Address address;

    public Supplier() {
        this.address = new Address();
    }

    @ObjectDiffProperty(categories = {"address"})
    public Address getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
