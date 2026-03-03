package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerConfig {

    private static final Logger log = LogManager.getLogger("FlipkartFramework");

    public static void info(String msg) {
        log.info(msg);
    }

    public static void warn(String msg) {
        log.warn(msg);
    }

    public static void error(String msg) {
        log.error(msg);
    }

    public static void error(String msg, Throwable t) {
        log.error(msg, t);
    }

    public static void debug(String msg) {
        log.debug(msg);
    }
}
