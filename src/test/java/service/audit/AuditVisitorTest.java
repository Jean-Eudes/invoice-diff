package service.audit;


import de.danielbechler.diff.ObjectDiffer;
import de.danielbechler.diff.ObjectDifferFactory;
import de.danielbechler.diff.node.Node;
import model.Supplier;
import org.junit.Test;

import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.extractProperty;

public class AuditVisitorTest {

    @Test
    public void should_compare_two_supplier_without_address() {
        Supplier supplier1 = new Supplier();
        supplier1.setName("fournisseur1");

        Supplier supplier2 = new Supplier();
        supplier2.setName("fournisseur2");

        ObjectDiffer objectDiffer = ObjectDifferFactory.getInstance();
        Node node = objectDiffer.compare(supplier1, supplier2);
        AuditVisitor auditVisitor = new AuditVisitor(supplier1, supplier2);
        node.visit(auditVisitor);

        List<Delta> deltas = auditVisitor.getDeltas();

        assertThat(deltas).hasSize(1);
        assertThat(extractProperty("fieldName").from(deltas)).contains("name");
        assertThat(extractProperty("state").from(deltas)).contains(State.MODIFIED);
        assertThat(extractProperty("oldValue").from(deltas)).contains("fournisseur1");
        assertThat(extractProperty("newValue").from(deltas)).contains("fournisseur2");

    }

    @Test
    public void should_compare_two_supplier_with_address() {
        Supplier supplier1 = new Supplier();
        supplier1.setName("name");
        supplier1.getAddress().setRoad1("12 rue de la paie");
        supplier1.getAddress().setRoad2("Immeuble 21");
        supplier1.getAddress().setPostalCode("75012");
        supplier1.getAddress().setCity("Parix");

        Supplier supplier2 = new Supplier();
        supplier2.setName("name");
        supplier2.getAddress().setRoad1("15 rue de la paie");
        supplier2.getAddress().setRoad2("Immeuble 21");
        supplier2.getAddress().setPostalCode("75013");
        supplier2.getAddress().setCity("Paris");

        ObjectDiffer objectDiffer = ObjectDifferFactory.getInstance();
        Node node = objectDiffer.compare(supplier1, supplier2);
        AuditVisitor auditVisitor = new AuditVisitor(supplier1, supplier2);
        node.visit(auditVisitor);

        List<Delta> deltas = auditVisitor.getDeltas();

        assertThat(deltas).hasSize(1);
        assertThat(extractProperty("fieldName").from(deltas)).contains("address");
        assertThat(extractProperty("state").from(deltas)).contains(State.MODIFIED);
        assertThat(extractProperty("oldValue").from(deltas)).contains("12 rue de la paie Immeuble 21 75012 Parix");
        assertThat(extractProperty("newValue").from(deltas)).contains("15 rue de la paie Immeuble 21 75013 Paris");

    }

    @Test
    public void should_compare_two_supplier_with_same_address() {
        Supplier supplier1 = new Supplier();
        supplier1.setName("name");
        supplier1.getAddress().setRoad1("15 rue de la paie");
        supplier1.getAddress().setRoad2("Immeuble 21");
        supplier1.getAddress().setPostalCode("75013");
        supplier1.getAddress().setCity("Paris");

        Supplier supplier2 = new Supplier();
        supplier2.setName("name");
        supplier2.getAddress().setRoad1("15 rue de la paie");
        supplier2.getAddress().setRoad2("Immeuble 21");
        supplier2.getAddress().setPostalCode("75013");
        supplier2.getAddress().setCity("Paris");

        ObjectDiffer objectDiffer = ObjectDifferFactory.getInstance();
        Node node = objectDiffer.compare(supplier1, supplier2);
        AuditVisitor auditVisitor = new AuditVisitor(supplier1, supplier2);
        node.visit(auditVisitor);

        List<Delta> deltas = auditVisitor.getDeltas();

        assertThat(deltas).hasSize(0);
    }

}
