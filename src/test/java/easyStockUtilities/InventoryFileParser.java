package easyStockUtilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class InventoryFileParser {
    
    private final List<InventoryRecord> inventoryList;
    
    // Inner class to hold each inventory record
    public static class InventoryRecord {
        private final String itemName;
        private final double quantity;
        private final String unit;
        private final String warehouse;
        
        public InventoryRecord(String itemName, double quantity, String unit, String warehouse) {
            this.itemName = itemName;
            this.quantity = quantity;
            this.unit = unit;
            this.warehouse = warehouse;
        }
        
        public String getItemName() { return itemName; }
        public double getQuantity() { return quantity; }
        public String getUnit() { return unit; }
        public String getWarehouse() { return warehouse; }
    }
    
    public InventoryFileParser(String filename) throws IOException {
        this.inventoryList = parseFile(filename);
    }
    
    private List<InventoryRecord> parseFile(String filename) throws IOException {
        List<InventoryRecord> records = new ArrayList<>();
        String currentItem = null;
        String currentUnit = null;
        
        String filePath = System.getProperty("user.dir") + "/src/test/java/easyStockTestData/" + filename;
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                
                // Split line keeping quotes intact
                String[] parts = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                
                // Remove quotes and trim
                String firstField = parts[0].replaceAll("\"", "").trim();
                
                if (!firstField.isEmpty()) {
                    // This is a main item line
                    currentItem = firstField;
                    String[] quantityParts = parts[1].trim().split("\\s+");
                    if (quantityParts.length >= 2) {
                        currentUnit = quantityParts[1];
                    }
                } else if (parts.length >= 3 && currentItem != null) {
                    // This is a warehouse line
                    String warehouse = parts[1].replaceAll("\"", "").trim();
                    String[] quantityParts = parts[2].trim().split("\\s+");
                    
                    if (quantityParts.length >= 2) {
                        try {
                            double quantity = Double.parseDouble(quantityParts[0]);
                            records.add(new InventoryRecord(currentItem, quantity, currentUnit, warehouse));
                        } catch (NumberFormatException e) {
                            System.out.println("Warning: Invalid quantity format in line: " + line);
                        }
                    }
                }
            }
        }
        return records;
    }
    
    
    
    // Get all unique item names
    public List<String> getAllItemNames() {
        Set<String> items = new LinkedHashSet<>();
        for (InventoryRecord record : inventoryList) {
            items.add(record.getItemName());
        }
        return new ArrayList<>(items);
    }
    
    // Get all unique warehouse names
    public List<String> getAllWarehouses() {
        Set<String> warehouses = new LinkedHashSet<>();
        for (InventoryRecord record : inventoryList) {
            warehouses.add(record.getWarehouse());
        }
        return new ArrayList<>(warehouses);
    }
    
    // Get quantity details for a specific item
    public ItemQuantityDetails getQuantityDetails(String itemName) {
        double totalQuantity = 0;
        String unit = null;
        Map<String, Double> warehouseQuantities = new LinkedHashMap<>();
        
        for (InventoryRecord record : inventoryList) {
            if (record.getItemName().equals(itemName)) {
                totalQuantity += record.getQuantity();
                unit = record.getUnit();
                warehouseQuantities.put(record.getWarehouse(), record.getQuantity());
            }
        }
        
        return new ItemQuantityDetails(totalQuantity, unit, warehouseQuantities);
    }
    
    // Get quantity for a specific item and warehouse
    public double getQuantityForItemAndWarehouse(String itemName, String warehouseName) {
        for (InventoryRecord record : inventoryList) {
            if (record.getItemName().equals(itemName) && record.getWarehouse().equals(warehouseName)) {
                return record.getQuantity();
            }
        }
        return 0.0; // Return 0 if no matching record is found
    }
    
    
 // Get all unique items present in a specific warehouse
    public List<String> getItemsInWarehouse(String warehouseName) {
        Set<String> itemsInWarehouse = new LinkedHashSet<>();
        for (InventoryRecord record : inventoryList) {
            if (record.getWarehouse().equals(warehouseName)) {
                itemsInWarehouse.add(record.getItemName());
            }
        }
        return new ArrayList<>(itemsInWarehouse);
    }

    
    // Class to hold quantity details for an item
    public static class ItemQuantityDetails {
        private final double totalQuantity;
        private final String unit;
        private final Map<String, Double> warehouseQuantities;
        
        public ItemQuantityDetails(double totalQuantity, String unit, Map<String, Double> warehouseQuantities) {
            this.totalQuantity = totalQuantity;
            this.unit = unit;
            this.warehouseQuantities = warehouseQuantities;
        }
        
        public double getTotalQuantity() { return totalQuantity; }
        public String getUnit() { return unit; }
        public Map<String, Double> getWarehouseQuantities() { return warehouseQuantities; }
    }
    
    
    
    // Example usage and testing method
    public static void main(String[] args) {
        try {
            InventoryFileParser parser = new InventoryFileParser("Dm Closing Stk.txt");
            
            // Print all items
            System.out.println("All Items:");
            for (String item : parser.getAllItemNames()) {
                System.out.println("- " + item);
                
                // Print quantity details for each item
                ItemQuantityDetails details = parser.getQuantityDetails(item);
                System.out.printf("  Total Quantity: %.2f %s%n", 
                    details.getTotalQuantity(), details.getUnit());
                System.out.println("  Warehouse Distribution:");
                for (Map.Entry<String, Double> entry : details.getWarehouseQuantities().entrySet()) {
                    System.out.printf("    %s: %.2f %s%n", 
                        entry.getKey(), entry.getValue(), details.getUnit());
                }
                System.out.println();
            }
            
         // Example item and warehouse
            String itemName = "Gramdal Gold 30kg";
            String warehouseName = "Pulses Godown";
            
            // Fetch quantity for item and warehouse
            double quantity = parser.getQuantityForItemAndWarehouse(itemName, warehouseName);
            System.out.printf("Quantity of '%s' in '%s': %.2f%n", itemName, warehouseName, quantity);
            
            // Print all warehouses
            System.out.println("\nAll Warehouses:");
            for (String warehouse : parser.getAllWarehouses()) {
                System.out.println("- " + warehouse);
            }
            
        } catch (IOException e) {
            System.err.println("Error reading inventory file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}