package training.supportbank;

import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class ProcessJSON {

    private static final Logger LOGGER = LogManager.getLogger(ProcessJSON.class);

    static void processJSON(String fileName) throws IOException {
        LOGGER.info(String.format("Begin to process %s", fileName));
        String json = ProcessJSON.readJSONFile(fileName);
        processJSONTransactions(json);
    }


    private static void processJSONTransactions(String json) {
        LOGGER.info("Attempting to process transactions using GSON");
        StringBuilder warningMessage = new StringBuilder();

        Gson gson = new Gson();
        JSONTransaction[] transactions = gson.fromJson(json, JSONTransaction[].class);
        for (JSONTransaction transaction : transactions) {
            warningMessage.append(transaction.processTransaction());
        }
        if (warningMessage.length() != 0) System.out.println(warningMessage);
    }

    private static String readJSONFile(String fileName) throws IOException {
        LOGGER.info(String.format("Attempt to read %s", fileName));
        Path filePath = Paths.get(fileName);
        return Files.readString(filePath);
    }
}

