package easyStockUtilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InventoryManager {
    
    public static class ItemDetails {
        private String name;
        private String status;
        private Double difference;
        private Double enteredStock;
        private Double tallyStock;

        // Getters and setters
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
        
        public Double getDifference() { return difference; }
        public void setDifference(Double difference) { this.difference = difference; }
        
        public Double getEnteredStock() { return enteredStock; }
        public void setEnteredStock(Double enteredStock) { this.enteredStock = enteredStock; }
        
        public Double getTallyStock() { return tallyStock; }
        public void setTallyStock(Double tallyStock) { this.tallyStock = tallyStock; }

        @Override
        public String toString() {
            return String.format(
                "ItemDetails{name='%s', status='%s', difference=%.2f, enteredStock=%.2f, tallyStock=%.2f}",
                name, status, difference, enteredStock, tallyStock
            );
        }
    }

    private List<ItemDetails> itemsList;

    public InventoryManager() {
        this.itemsList = new ArrayList<>();
    }

    /**
     * Parses and stores item details from XPath content description.
     * 
     * @param xpathContent The content-desc attribute value from XPath
     * @return ItemDetails object containing parsed information
     * @throws IllegalArgumentException if parsing fails
     */
    public ItemDetails parseAndStoreItemDetails(String xpathContent) {
        try {
            ItemDetails details = new ItemDetails();
            String[] parts = xpathContent.trim().split("\n");

            // Extract item name (first line)
            details.setName(parts[0].trim());

            // Parse status line (second line)
            String statusLine = parts[1].trim();
            if (statusLine.contains("Short:")) {
                details.setStatus("Short");
                details.setDifference(Double.parseDouble(statusLine.split(":")[1].trim()));
            } else if (statusLine.contains("Excess:")) {
                details.setStatus("Excess");
                details.setDifference(Double.parseDouble(statusLine.split(":")[1].trim()));
            }

            // Parse stock values (third line)
            String[] stockParts = parts[2].trim().split("/");
            details.setEnteredStock(Double.parseDouble(stockParts[0].trim()));
            details.setTallyStock(Double.parseDouble(stockParts[1].trim()));

            // Store the details in the list
            itemsList.add(details);
            
            return details;
        } catch (Exception e) {
            throw new IllegalArgumentException("Error parsing XPath content: " + e.getMessage());
        }
    }

    /**
     * Find item details by item name
     * 
     * @param itemName The name of the item to search for
     * @return Optional<ItemDetails> containing the item details if found
     */
    public Optional<ItemDetails> findItemByName(String itemName) {
        return itemsList.stream()
                .filter(item -> item.getName().equalsIgnoreCase(itemName))
                .findFirst();
    }

    /**
     * Get all stored item details
     * 
     * @return List of all stored ItemDetails
     */
    public List<ItemDetails> getAllItems() {
        return new ArrayList<>(itemsList);
    }
    
    /**
     * Clear all stored items
     */
    public void clearAllItems() {
        itemsList.clear();
    }

    // Example usage in Selenium/Appium test
    public static void main(String[] args) {
        InventoryManager manager = new InventoryManager();
        
        // Example data
        String[] xpathContents = {
            "Pulses Godown\nShort: 38.00\n-33.0/5.0",
            "Rice Storage\nExcess: 12.50\n45.0/57.5",
            "Wheat Storage\nShort: 15.75\n84.25/100.0"
        };
        
        try {
            // Parse and store multiple items
            for (String content : xpathContents) {
                ItemDetails details = manager.parseAndStoreItemDetails(content);
                System.out.println("Stored item: " + details);
            }
            
            // Find a specific item
            String searchItem = "Pulses Godown";
            Optional<ItemDetails> foundItem = manager.findItemByName(searchItem);
            
            foundItem.ifPresentOrElse(
                item -> System.out.println("Found item: " + item),
                () -> System.out.println("Item not found: " + searchItem)
            );
            
            // Print all stored items
            System.out.println("\nAll stored items:");
            manager.getAllItems().forEach(System.out::println);
            
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}