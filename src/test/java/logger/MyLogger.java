package logger;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class MyLogger {

    private static MyLogger myLogger;
    private final String STRING_LOG_INFO = "[%s]";
    protected final static Logger log = Logger.getLogger(MyLogger.class);

    private MyLogger() {
    }

    public static MyLogger getMyLogger() {
        if (myLogger == null) {
            myLogger = new MyLogger();
            PropertyConfigurator.configure("src\\test\\resources\\log4j.properties");
        }
        return myLogger;
    }

    public void info(String myClass, String msg) {
        log.info(String.format(STRING_LOG_INFO, myClass) + msg);
    }

    public void info(String s) {
        log.info(s);
    }
}
