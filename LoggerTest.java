/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DP_Lab3.Part1;

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

public class LoggerTest {

    public static boolean isFileLoggingEnabled() {
        Properties p = new Properties();
        try {
            p.load(ClassLoader.getSystemResourceAsStream("Logger.properties"));
            String fileLoggingValue = p.getProperty("FileLogging");
            if (fileLoggingValue.equalsIgnoreCase("ON") == true) {
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        Logger logger;
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());

        if (isFileLoggingEnabled()) {
            logger = new FileLogger();
        } else {
            logger = new ConsoleLogger();
        }

        logger.log((currentTime + ": hello, how are you?"));

    }

}
