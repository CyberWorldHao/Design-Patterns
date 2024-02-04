/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DP_Lab3.Part2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Properties;

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

    public Logger getLogger() {
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

public class MessageLoggingUtilitySystem {

    LoggerFactory factory; // attribute member

    MessageLoggingUtilitySystem() {
        factory = new LoggerFactory();
    }

    public void LoggerTestFactory() {
        Logger logger = factory.getLogger();
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        logger.log((currentTime + ": hello, how are you?"));
    }

    public static void main(String[] args) {
        MessageLoggingUtilitySystem logger = new MessageLoggingUtilitySystem();
        logger.LoggerTestFactory();
    }
}

class LoggerTestFactory {
//    LoggerFactory factory;

//    LoggerTestFactory() {
//        factory = new LoggerFactory();    // local variable
//        Logger logger = factory.getLogger();
//        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
//        logger.log((currentTime + ": hello, how are you?"));
//    }

    public static void main(String[] args) {
        LoggerFactory factory = new LoggerFactory();  // local variable
        Logger logger = factory.getLogger();
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        logger.log((currentTime + ": hello, how are you?"));
//        LoggerTestFactory logger = new LoggerTestFactory();
    }
}
