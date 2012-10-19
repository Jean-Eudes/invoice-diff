package model;

import java.math.BigDecimal;

public class Line {

    private String lineNumber;
    private String description;
    private BigDecimal unitPrice;
    private BigDecimal quantity;

    public Line() {
        unitPrice = BigDecimal.ZERO;
        quantity = BigDecimal.ZERO;
    }

    public String getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(String lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal totalLine() {
        return unitPrice.multiply(quantity);
    }
}
