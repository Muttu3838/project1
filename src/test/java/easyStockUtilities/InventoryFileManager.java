package easyStockUtilities;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;
import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class InventoryFileManager {
    private static final Logger logger = LogManager.getLogger(InventoryFileManager.class);
    private static final String BASE_DIRECTORY = "inventory_records";
    private static final String FILE_EXTENSION = ".json";
    private final Gson gson;

    public InventoryFileManager() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        createBaseDirectory();
    }

    private void createBaseDirectory() {
        File directory = new File(BASE_DIRECTORY);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    public void saveInventoryDetails(String date, List<ItemInventoryDetails> inventoryDetails) {
        try {
            String fileName = getFileNameForDate(date);
            File file = new File(BASE_DIRECTORY, fileName);

            // If file exists, load existing data and merge
            Map<String, ItemInventoryDetails> existingData = new HashMap<>();
            if (file.exists()) {
                existingData = loadExistingData(file);
            }

            // Update with new data
            for (ItemInventoryDetails detail : inventoryDetails) {
                existingData.put(detail.getItemName(), detail);
            }

            // Write updated data back to file
            try (Writer writer = new FileWriter(file)) {
                gson.toJson(existingData, writer);
            }

            logger.info("Successfully saved inventory details for date: " + date);
        } catch (IOException e) {
            logger.error("Error saving inventory details for date: " + date, e);
            throw new RuntimeException("Failed to save inventory details", e);
        }
    }

    private String getFileNameForDate(String date) {
        return "inventory_" + date.replace("/", "_") + FILE_EXTENSION;
    }

    private Map<String, ItemInventoryDetails> loadExistingData(File file) throws IOException {
        try (Reader reader = new FileReader(file)) {
            Type type = new TypeToken<Map<String, ItemInventoryDetails>>(){}.getType();
            Map<String, ItemInventoryDetails> data = gson.fromJson(reader, type);
            return data != null ? data : new HashMap<>();
        }
    }

    public List<ItemInventoryDetails> getInventoryDetailsForDate(String date) {
        try {
            File file = new File(BASE_DIRECTORY, getFileNameForDate(date));
            if (!file.exists()) {
                return new ArrayList<>();
            }

            Map<String, ItemInventoryDetails> data = loadExistingData(file);
            return new ArrayList<>(data.values());
        } catch (IOException e) {
            logger.error("Error loading inventory details for date: " + date, e);
            throw new RuntimeException("Failed to load inventory details", e);
        }
    }

    public void generateInventoryReport(String date) {
    	 String reportPath = System.getProperty("user.dir")+ "/InventoryReport/"+ date.replace("/", "_") + ".txt";
        List<ItemInventoryDetails> details = getInventoryDetailsForDate(date);
        try (PrintWriter writer = new PrintWriter(new FileWriter(reportPath))) {
            writer.println("Inventory Report for " + date);
            writer.println("=====================================");
            
            for (ItemInventoryDetails item : details) {
                writer.println("\nItem: " + item.getItemName());
                writer.println("Total Entered Quantity: " + item.getTotalEnteredQuantity());
                writer.println("Total Tally Stock: " + item.getTotalTallyStock());
                writer.println("Total Excess: " + item.getTotalExcessQuantity());
                writer.println("Total Short: " + item.getTotalShortQuantity());
                
                writer.println("\nWarehouse Details:");
                for (WarehouseDetails warehouse : item.getWarehouseDetails()) {
                    writer.println("  " + warehouse.getWarehouseName());
                    writer.println("    Entered: " + warehouse.getEnteredQuantity());
                    writer.println("    Tally: " + warehouse.getTallyStock());
                    writer.println("    Excess: " + warehouse.getExcessQuantity());
                    writer.println("    Short: " + warehouse.getShortQuantity());
                }
                writer.println("-------------------------------------");
            }
        } catch (IOException e) {
            logger.error("Error generating inventory report for date: " + date, e);
            throw new RuntimeException("Failed to generate inventory report", e);
        }
    }
}