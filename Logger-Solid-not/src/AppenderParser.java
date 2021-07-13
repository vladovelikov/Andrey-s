import loggerlib.appenders.ConsoleAppender;
import loggerlib.appenders.FileAppender;
import loggerlib.appenders.interfaces.Appender;
import loggerlib.enumerations.ReportLevel;
import loggerlib.layouts.SimpleLayout;
import loggerlib.layouts.XmlLayout;
import loggerlib.layouts.interfaces.Layout;

import java.util.Scanner;

public class AppenderParser {

    public Appender[] parse(Scanner scanner, int count, Appender[] appenders) {

        for (int i = 0; i < count; i++) {
            String[] tokens = scanner.nextLine().split(" ");
            Appender appender = null;

            Layout layout = null;

            if (tokens[1].equals("SimpleLayout")) {
                layout = new SimpleLayout();
            } else {
                layout = new XmlLayout();
            }

            if (tokens[0].equals("ConsoleAppender")) {
                appender = new ConsoleAppender(layout);
            } else {
                appender = new FileAppender(layout);
            }

            if (tokens.length == 3) {
                appender.setReportLevel(ReportLevel.valueOf(tokens[2]));
            }

            appenders[i] = appender;
        }

        return appenders;
    }
}
