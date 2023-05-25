package framework.util.logger;

import java.io.FileInputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class LoggerManager {
    private static Logger logger;

    static {
        try (FileInputStream ins = new FileInputStream("src/main/resources/log.config")) {
            LogManager.getLogManager().readConfiguration(ins);
            logger = Logger.getLogger(LoggerManager.class.getName());
        } catch (Exception e) {
            LoggerManager.logError(e.toString());
        }
    }

    public static void logInfo(String message) {
        logger.info(message);
    }
    public static void logError(String message){logger.warning(message);}
}
