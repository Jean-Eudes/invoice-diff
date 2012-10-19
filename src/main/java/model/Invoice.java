package model;


import java.util.ArrayList;
import java.util.List;

public class Invoice {

    private Supplier supplier;
    private Customer customer;
    private List<Line> lines;

    public Invoice() {
        supplier = new Supplier();
        customer = new Customer();
        lines = new ArrayList<>();
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void addLine(Line line) {
        lines.add(line);
    }
}
