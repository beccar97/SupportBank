package training.supportbank;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class ProcessFile {
    private static final Logger LOGGER = LogManager.getLogger(ProcessFile.class);
    static void processFile(String fileName) throws IOException, ParseException {
        LOGGER.info("Begin processing " + fileName);

        String fileType = getFileType(fileName);
        LOGGER.info(String.format("File type: %s", fileType));

        switch (fileType) {
            case "csv":
                try {
                    ProcessCSV.processCSV(fileName);
                } catch (FileNotFoundException e) {
                    LOGGER.warn(String.format("File not found: %s", fileName));
                    System.out.println(String.format("File does not exist %s", fileName));
                }
                break;
            case "json":
                try {
                    ProcessJSON.processJSON(fileName);
                } catch (FileNotFoundException e) {
                    LOGGER.warn(String.format("File not found: %s", fileName));
                    System.out.println(String.format("File does not exist %s", fileName));
                }
                break;
            default:
                LOGGER.error("Invalid file type.");
                System.out.println("Invalid file type.");
        }
    }

    private static String getFileType(String fileName) {
        Pattern rFileType = Pattern.compile("(?:\\w+).(csv|json|xml)", Pattern.CASE_INSENSITIVE);
        Matcher fileMatcher = rFileType.matcher(fileName);

        if (fileMatcher.find()) {
            return fileMatcher.group(1);
        } else {
            return "INVALID FILE TYPE";
        }
    }
}