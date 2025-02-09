package easyStockUtilities;

import java.util.ArrayList;
import java.util.List;

public class ItemInventoryDetails {
    private String itemName;
    private List<WarehouseDetails> warehouseDetails;
    private double totalEnteredQuantity;
    private double totalTallyStock;
    private double totalShortQuantity;
    private double totalExcessQuantity;

    public ItemInventoryDetails(String itemName) {
        this.itemName = itemName;
        this.warehouseDetails = new ArrayList<>();
    }

    public void addWarehouseDetails(WarehouseDetails details) {
        warehouseDetails.add(details);
        calculateTotals();
    }

    private void calculateTotals() {
        totalEnteredQuantity = 0;
        totalTallyStock = 0;
        totalShortQuantity = 0;
        totalExcessQuantity = 0;

        for (WarehouseDetails detail : warehouseDetails) {
            totalEnteredQuantity += detail.getEnteredQuantity();
            totalTallyStock += detail.getTallyStock();
            totalShortQuantity += detail.getShortQuantity();
            totalExcessQuantity += detail.getExcessQuantity();
        }
    }

    // Getters
    public String getItemName() { return itemName; }
    public List<WarehouseDetails> getWarehouseDetails() { return warehouseDetails; }
    public double getTotalEnteredQuantity() { return totalEnteredQuantity; }
    public double getTotalTallyStock() { return totalTallyStock; }
    public double getTotalShortQuantity() { return totalShortQuantity; }
    public double getTotalExcessQuantity() { return totalExcessQuantity; }

    // Get warehouse details by name
    public WarehouseDetails getWarehouseByName(String warehouseName) {
        return warehouseDetails.stream()
            .filter(w -> w.getWarehouseName().equals(warehouseName))
            .findFirst()
            .orElse(null);
    }
}
