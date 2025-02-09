package easyStockUtilities;

import java.util.List;
import java.util.ArrayList;
import org.openqa.selenium.WebElement;
import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;


public class InventoryDetailsUtil {
    private static final Logger logger = LogManager.getLogger(InventoryDetailsUtil.class);

    public ItemInventoryDetails processWarehouseElements(String itemName, List<WebElement> warehouseElements) {
        if (itemName == null || warehouseElements == null) {
            logger.error("Null parameters provided to processWarehouseElements");
            return null;
        }

        ItemInventoryDetails itemDetails = new ItemInventoryDetails(itemName);
        
        for (WebElement element : warehouseElements) {
            try {
                String contentDesc = element.getDomAttribute("content-desc");
                if (contentDesc == null || contentDesc.trim().isEmpty()) {
                    logger.warn("Empty content-desc for element in item: "+ itemName);
                    continue;
                }

                WarehouseDetails warehouse = parseWarehouseContent(contentDesc);
                if (warehouse != null) {
                    itemDetails.addWarehouseDetails(warehouse);
                }
            } catch (Exception e) {
                logger.error("Error processing warehouse element for item "+ 
                           itemName);
            }
        }
        
        logItemDetails(itemDetails);
        return itemDetails;
    }

    private WarehouseDetails parseWarehouseContent(String contentDesc) {
        try {
            String[] lines = contentDesc.split("\n");
            if (lines.length < 3) {
                logger.warn("Invalid content description format: "+ contentDesc);
                return null;
            }

            String warehouseName = lines[0].trim();
            double shortQuantity = 0.0;
            double excessQuantity = 0.0;
            
            // Parse second line which may or may not contain Short/Excess
            String[] quantityParts = lines[1].split(":");
            if (quantityParts.length > 1) {
                String quantityType = quantityParts[0].trim().toLowerCase();
                double quantity = parseNumber(quantityParts[1]);
                
                if (quantityType.contains("short")) {
                    shortQuantity = quantity;
                } else if (quantityType.contains("excess")) {
                    excessQuantity = quantity;
                }
            }

            // Parse stock values (e.g., "-15.0/5.0")
            String[] stockParts = lines[2].split("/");
            double enteredQuantity = parseNumber(stockParts[0]);
            double tallyStock = parseNumber(stockParts[1]);

            return new WarehouseDetails(warehouseName, shortQuantity, excessQuantity,enteredQuantity, tallyStock);
        } catch (Exception e) {
            logger.error("Failed to parse warehouse content: "+ contentDesc, e);
            return null;
        }
    }

    private double parseNumber(String value) {
        try {
            return Double.parseDouble(value.trim());
        } catch (NumberFormatException e) {
            logger.error("Failed to parse number: "+ value);
            return 0.0;
        }
    }

    private void logItemDetails(ItemInventoryDetails details) {
        if (details == null) {
            logger.warn("Null ItemInventoryDetails provided to logItemDetails");
            return;
        }

        logger.info("=== Item Details for:  ==="+ details.getItemName());
        logger.info("Number of warehouses: "+ details.getWarehouseDetails().size());
        
        // Log warehouse-wise details
        logger.info("Warehouse-wise breakdown:");
        for (WarehouseDetails warehouse : details.getWarehouseDetails()) {
            logger.info("\nWarehouse: "+ warehouse.getWarehouseName());
            logger.info("  Short Quantity: "+ warehouse.getShortQuantity());
            logger.info("  Excess Quantity: "+ warehouse.getExcessQuantity());
            logger.info("  Entered Quantity: "+ warehouse.getEnteredQuantity());
            logger.info("  Tally Stock: "+ warehouse.getTallyStock());
        }

        // Log totals
        logger.info("\nTotals across all warehouses:");
        logger.info("Total Short Quantity: "+ details.getTotalShortQuantity());
        logger.info("Total Excess Quantity: "+ details.getTotalExcessQuantity());
        logger.info("Total Entered Quantity: "+ details.getTotalEnteredQuantity());
        logger.info("Total Tally Stock: "+ details.getTotalTallyStock());
    }

    // Helper method to get a formatted summary string
    public String getFormattedSummary(ItemInventoryDetails details) {
        StringBuilder summary = new StringBuilder();
        summary.append("Item: ").append(details.getItemName()).append("\n\n");
        
        // Warehouse-wise details
        summary.append("Warehouse Details:\n");
        for (WarehouseDetails warehouse : details.getWarehouseDetails()) {
            summary.append("- ").append(warehouse.getWarehouseName()).append(":\n");
            summary.append("  • Short: ").append(String.format("%.2f", warehouse.getShortQuantity())).append("\n");
            summary.append("  • Excess: ").append(String.format("%.2f", warehouse.getExcessQuantity())).append("\n");
            summary.append("  • Entered: ").append(String.format("%.2f", warehouse.getEnteredQuantity())).append("\n");
            summary.append("  • Tally: ").append(String.format("%.2f", warehouse.getTallyStock())).append("\n");
        }
        
        // Total summary
        summary.append("\nTotal Summary:\n");
        summary.append("• Total Short: ").append(String.format("%.2f", details.getTotalShortQuantity())).append("\n");
        summary.append("• Total Excess: ").append(String.format("%.2f", details.getTotalExcessQuantity())).append("\n");
        summary.append("• Total Entered: ").append(String.format("%.2f", details.getTotalEnteredQuantity())).append("\n");
        summary.append("• Total Tally: ").append(String.format("%.2f", details.getTotalTallyStock())).append("\n");
        
        return summary.toString();
    }
}

