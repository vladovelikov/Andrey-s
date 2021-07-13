import loggerlib.appenders.interfaces.Appender;
import loggerlib.loggers.MessageLogger;
import loggerlib.loggers.interfaces.Logger;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int appendersCount = Integer.parseInt(scanner.nextLine());

        Appender[] appenders = new Appender[appendersCount];

        AppenderParser appenderParser = new AppenderParser();
        appenderParser.parse(scanner, appendersCount, appenders);

        Logger logger = new MessageLogger(appenders);

        String line = scanner.nextLine();

        LoggerParser loggerParser = new LoggerParser();
        loggerParser.parse(logger, line, scanner);

        System.out.println(logger.toString());
        
    }
}

