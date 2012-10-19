package service.audit;

public class Delta {

    protected final String fieldName;

    protected final State state;

    private final String oldValue;

    private final String newValue;

    public Delta(String fieldName, State state, String oldValue, String newValue) {
        this.fieldName = fieldName;
        this.state = state;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public String getFieldName() {
        return fieldName;
    }

    public State getState() {
        return state;
    }

    public String getOldValue() {
        return oldValue;
    }

    public String getNewValue() {
        return newValue;
    }
}
