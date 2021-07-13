import loggerlib.enumerations.ReportLevel;
import loggerlib.loggers.interfaces.Logger;

import java.util.Scanner;

public class LoggerParser {

    public Logger parse (Logger logger, String line, Scanner scanner) {

        while (!line.equals("END")) {

            String[] tokens = line.split("\\|");

            switch (ReportLevel.valueOf(tokens[0])) {
                case INFO:
                    logger.logInfo(tokens[1], tokens[2]);
                    break;
                case ERROR:
                    logger.logError(tokens[1], tokens[2]);
                    break;
                case WARNING:
                    logger.logWarning(tokens[1], tokens[2]);
                    break;
                case CRITICAL:
                    logger.logCritical(tokens[1], tokens[2]);
                    break;
                case FATAL:
                    logger.logFatal(tokens[1], tokens[2]);
                    break;
            }

            line = scanner.nextLine();
        }

        return logger;
    }
}
