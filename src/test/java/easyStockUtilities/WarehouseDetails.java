package easyStockUtilities;

public class WarehouseDetails {
    private String warehouseName;
    private double shortQuantity;
    private double excessQuantity;
    private double enteredQuantity;
    private double tallyStock;

    public WarehouseDetails(String warehouseName, double shortQuantity, double excessQuantity, 
                           double enteredQuantity, double tallyStock) {
        this.warehouseName = warehouseName;
        this.shortQuantity = shortQuantity;
        this.excessQuantity = excessQuantity;
        this.enteredQuantity = enteredQuantity;
        this.tallyStock = tallyStock;
    }

    // Getters
    public String getWarehouseName() { return warehouseName; }
    public double getShortQuantity() { return shortQuantity; }
    public double getExcessQuantity() { return excessQuantity; }
    public double getEnteredQuantity() { return enteredQuantity; }
    public double getTallyStock() { return tallyStock; }

	
}