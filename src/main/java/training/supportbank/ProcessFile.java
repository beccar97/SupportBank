package training.supportbank;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class ProcessFile {
    private static final Logger LOGGER = LogManager.getLogger(ProcessFile.class);
    static void processFile(String fileName) throws IOException, ParseException {
        LOGGER.info("Begin processing " + fileName);

        String fileType = getFileType(fileName);

        switch (fileType) {
            case "csv":
                ProcessCSV.processCSV(fileName);
                break;
            case "json":
                ProcessJSON.processJSON(fileName);
                break;
            default:
                LOGGER.error(String.format("Invalid file type %s", fileType));
                System.out.println(String.format("Invalid file type %s", fileType));
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