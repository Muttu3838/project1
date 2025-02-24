package easyStockUtilities;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;
import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class InventoryDataReader {
    private static final Logger logger = LogManager.getLogger(InventoryDataReader.class);
    private static final String BASE_DIRECTORY = "inventory_records";
    private List<ItemInventoryDetails> storedItemDetails;

    public InventoryDataReader() {
        this.storedItemDetails = new ArrayList<>();
    }

    public List<ItemInventoryDetails> readInventoryData(String date) {
        try {
            String fileName = "inventory_" + date.replace("/", "_") + ".json";
            File file = new File(BASE_DIRECTORY, fileName);

            if (!file.exists()) {
                logger.warn("No inventory data file found for date: " + date);
                return new ArrayList<>();
            }

            try (Reader reader = new FileReader(file)) {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                Type type = new TypeToken<Map<String, ItemInventoryDetails>>(){}.getType();
                Map<String, ItemInventoryDetails> data = gson.fromJson(reader, type);
                
                if (data != null) {
                    storedItemDetails = new ArrayList<>(data.values());
                    logger.info("Successfully loaded " + storedItemDetails.size() + " items for date: " + date);
                }
            }

            return storedItemDetails;
        } catch (IOException e) {
            logger.error("Error reading inventory data for date: " + date, e);
            throw new RuntimeException("Failed to read inventory data", e);
        }
    }

    public ItemInventoryDetails getItemDetails(String itemName) {
        for (ItemInventoryDetails detail : storedItemDetails) {
            if (detail.getItemName().equalsIgnoreCase(itemName)) {
                return detail;
            }
        }
        return null;
    }

    public Map<String, List<WarehouseDetails>> getWarehouseDetailsForItem(String itemName) {
        Map<String, List<WarehouseDetails>> warehouseMap = new HashMap<>();
        ItemInventoryDetails item = getItemDetails(itemName);
        
        if (item != null) {
            warehouseMap.put(itemName, item.getWarehouseDetails());
        }
        
        return warehouseMap;
    }

    public void printItemSummary(String itemName) {
        ItemInventoryDetails item = getItemDetails(itemName);
        if (item != null) {
            logger.info("=== Summary for Item: " + itemName + " ===");
            logger.info("Total Tally Stock: " + item.getTotalTallyStock());
            logger.info("Total Entered Quantity: " + item.getTotalEnteredQuantity());
            logger.info("Total Excess Quantity: " + item.getTotalExcessQuantity());
            logger.info("Total Short Quantity: " + item.getTotalShortQuantity());
            
            logger.info("\nWarehouse Details:");
            for (WarehouseDetails warehouse : item.getWarehouseDetails()) {
                logger.info("\nWarehouse: " + warehouse.getWarehouseName());
                logger.info("  Entered Quantity: " + warehouse.getEnteredQuantity());
                logger.info("  Tally Stock: " + warehouse.getTallyStock());
                logger.info("  Excess Quantity: " + warehouse.getExcessQuantity());
                logger.info("  Short Quantity: " + warehouse.getShortQuantity());
            }
        } else {
            logger.warn("No data found for item: " + itemName);
        }
    }
}
