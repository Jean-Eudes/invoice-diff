package service.audit;

import com.google.common.base.Joiner;
import de.danielbechler.diff.node.Node;
import de.danielbechler.diff.path.Element;
import de.danielbechler.diff.path.NamedPropertyElement;
import de.danielbechler.diff.path.PropertyPath;
import de.danielbechler.diff.visitor.Visit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AuditVisitor implements Node.Visitor {

    private Object base;

    private Object working;

    private List<Delta> deltas;

    public AuditVisitor(Object base, Object working) {
        this.base = base;
        this.working = working;
        deltas = new ArrayList<>();
    }

    @Override
    public void accept(Node node, Visit visit) {
        if(node.getCategories().contains("address") && node.hasChanges()) {
            String oldValue = buildValueFromObject(base, node);
            String newValue = buildValueFromObject(working, node);
            deltas.add(new Delta(buildFieldName(node.getPropertyPath()), convertState(node.getState()), oldValue, newValue));
            visit.dontGoDeeper();

        }
        else if (!node.hasChildren() && node.hasChanges()) {
            State state = convertState(node.getState());
            String oldValue = buildValueFromObject(base, node);
            String newValue = buildValueFromObject(working, node);
            deltas.add(new Delta(buildFieldName(node.getPropertyPath()), state, oldValue, newValue));
        }
    }

    private String buildValueFromObject(Object object, Node node) {
        return String.valueOf(node.canonicalGet(object));
    }

    public List<Delta> getDeltas() {
        return Collections.unmodifiableList(deltas);
    }

    private String buildFieldName(PropertyPath propertyPath) {
        String fieldName = null;
        Joiner joiner = Joiner.on(".").skipNulls();
        for (Element element : propertyPath.getElements()) {
            if (element instanceof NamedPropertyElement) {
                NamedPropertyElement namedPropertyElement = (NamedPropertyElement) element;
                fieldName = joiner.join(fieldName, namedPropertyElement.getPropertyName());
            }
        }
        return fieldName;
    }


    private State convertState(Node.State state) {
        switch (state) {

            case ADDED:
                return State.ADD;
            case CHANGED:
                return State.MODIFIED;
            case REMOVED:
                return State.REMOVED;

            default:
                return null;
        }
    }

}
