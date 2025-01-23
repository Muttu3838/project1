package easyStockUtilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ItemParser {
    public static class Item {
        private String itemName;
        private double totalQuantity;
        private String unit;

        public Item(String itemName, double totalQuantity, String unit) {
            this.itemName = itemName;
            this.totalQuantity = totalQuantity;
            this.unit = unit;
        }

        // Getters
        public String getItemName() { return itemName; }
        public double getTotalQuantity() { return totalQuantity; }
        public String getUnit() { return unit; }

        @Override
        public String toString() {
            return String.format("%s: %.2f %s", itemName, totalQuantity, unit);
        }
    }

    public static List<Item> parseUniqueItems(String filename) throws IOException {
        Map<String, Item> itemMap = new LinkedHashMap<>(); // Using LinkedHashMap to maintain order
        
        String filePath = System.getProperty("user.dir") + "/src/test/java/easyStockTestData/"+filename;
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            String currentItem = null;
            
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                
                if (parts.length >= 2) {
                    String itemName = parts[0].trim().replaceAll("\"", "");
                    
                    // If it's a main item line (not empty)
                    if (!itemName.isEmpty()) {
                        currentItem = itemName;
                        // Parse quantity and unit
                        String[] quantityParts = parts[1].trim().split("\\s+");
                        double quantity = Double.parseDouble(quantityParts[0]);
                        String unit = quantityParts[1];
                        
                        itemMap.put(currentItem, new Item(currentItem, quantity, unit));
                    } 
                    // If it's a warehouse line (empty item name)
                    else if (currentItem != null) {
                        String[] quantityParts = parts[1].trim().split("\\s+");
                        double quantity = Double.parseDouble(quantityParts[0]);
                        
                        // Add to existing item's total quantity
                        Item existingItem = itemMap.get(currentItem);
                        existingItem.totalQuantity += quantity;
                    }
                }
            }
        }
        
        return new ArrayList<>(itemMap.values());
    }

    // Utility method to get item by name
    public static Item getItemByName(List<Item> items, String itemName) {
        for (Item item : items) {
            if (item.getItemName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }

    // Utility method to print all items
    public static void printAllItems(List<Item> items) {
        System.out.println("All Items with Total Quantities:");
        for (Item item : items) {
            System.out.println(item);
        }
    }
    
    public static List<String> getAllItems(List<Item> inventory) {
        Set<String> items = new LinkedHashSet<>();  // Using Set to ensure uniqueness
        for (Item item : inventory) {
            if (item.getItemName() != null && !item.getItemName().isEmpty()) {
                items.add(item.getItemName());
            }
        }
        return new ArrayList<>(items);
    }
}
