/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab5.a;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.sql.Timestamp;

/**
 *
 * @author CWH
 */
interface Logger {

    abstract void log(String msg);
}

class ConsoleLogger implements Logger {

    @Override
    public void log(String msg) {
        System.out.println(msg);
    }
}

class FileLogger implements Logger {

    @Override
    public void log(String msg) {
        try {
            File myObj = new File("log.txt");
            try (FileWriter myWriter = new FileWriter(myObj)) {
                myWriter.write(msg);
            }
            System.out.println("File Logging Success.");
        } catch (IOException e) {
            System.out.println("File Logging Error.");
        }
    }
}

class LoggerFactory {

    private static LoggerFactory uniqueLoggerInstance;

    private LoggerFactory() {
    }

    public static synchronized Logger getLogger() {
        if (uniqueLoggerInstance == null) {
            uniqueLoggerInstance = new LoggerFactory();
        }
        Logger logger = null;
        Properties p = new Properties();
        try {
            p.load(ClassLoader.getSystemResourceAsStream("Logger.properties"));
            String fileLoggingValue = p.getProperty("FileLogging");
            if (fileLoggingValue.equalsIgnoreCase("ON") == true) {
                logger = new FileLogger();
            } else {
                logger = new ConsoleLogger();
            }
        } catch (IOException e) {
            System.out.println("File Logging Error");
        }
        return logger;
    }
}

public class FileLoggerSingleton {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger();
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        logger.log((currentTime + ": hello, how are you?"));
    }
}
