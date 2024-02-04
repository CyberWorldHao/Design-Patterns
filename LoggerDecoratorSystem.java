/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab5.b;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;

/**
 *
 * @author CWH
 */
abstract class LoggerFactory {

    String msg = "Unknown Message";
    Boolean isFileLogger;

    public abstract String log();

    public void setIsFileLogger(Boolean isFileLogger) {
        this.isFileLogger = isFileLogger;
    }

    public Boolean getIsFileLogger() {
        return isFileLogger;
    }

}

abstract class LoggerFeatureDecorator extends LoggerFactory {
    
    LoggerFactory loggerFactory;

    @Override
    public abstract String log();

    @Override
    public abstract void setIsFileLogger(Boolean isFileLogger);

    @Override
    public abstract Boolean getIsFileLogger();

}

class ConsoleLogger extends LoggerFactory {

    public ConsoleLogger(String msg) {
        this.msg = msg;
        setIsFileLogger(false);
    }

    @Override
    public String log() {
        return msg;
    }
}

class FileLogger extends LoggerFactory {

    public FileLogger(String msg) {
        this.msg = msg;
        setIsFileLogger(true);
    }

    @Override
    public String log() {
        return msg;
    }
}

class Html extends LoggerFeatureDecorator {

    public Html(LoggerFactory loggerFactory) {
        this.loggerFactory = loggerFactory;
    }

    @Override
    public String log() {
        loggerFactory.msg = "<HTML><BODY><b>" + loggerFactory.log() + "</b></BODY></HTML>";
        if (loggerFactory.getIsFileLogger()) {
            try {
                File myObj = new File("log.txt");
                try (FileWriter myWriter = new FileWriter(myObj)) {
                    myWriter.write(loggerFactory.msg);
                }
            } catch (IOException e) {
                System.out.println("File Logging Error.");
            }
        }
        return loggerFactory.msg;
    }

    @Override
    public void setIsFileLogger(Boolean isFileLogger) {
        this.loggerFactory.isFileLogger = isFileLogger;
    }

    @Override
    public Boolean getIsFileLogger() {
        return loggerFactory.getIsFileLogger();
    }

}

class Enc extends LoggerFeatureDecorator {

    public Enc(LoggerFactory loggerFactory) {
        this.loggerFactory = loggerFactory;
    }

    @Override
    public String log() {
        String temp = loggerFactory.log();
        loggerFactory.msg = temp.charAt(temp.length() - 1) + temp.substring(0, temp.length() - 1);
        if (loggerFactory.getIsFileLogger()) {
            try {
                File myObj = new File("log.txt");
                try (FileWriter myWriter = new FileWriter(myObj)) {
                    myWriter.write(loggerFactory.msg);
                }
            } catch (IOException e) {
                System.out.println("File Logging Error.");
            }
        }
        return loggerFactory.msg;
    }

    @Override
    public void setIsFileLogger(Boolean isFileLogger) {
        this.loggerFactory.isFileLogger = isFileLogger;
    }

    @Override
    public Boolean getIsFileLogger() {
        return loggerFactory.getIsFileLogger();
    }

}

public class LoggerDecoratorSystem {

    public static void main(String[] args) {
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        String msg = currentTime + ": hello, how are you?";

        // Question 3 - statement 1
        System.out.println("Question 3 - statement 1");
        LoggerFactory logger = new ConsoleLogger(msg);
        logger = new Html(logger);
        System.out.println(logger.log());
        System.out.println();

        // Question 3 - statement 2
        System.out.println("Question 3 - statement 2");
        LoggerFactory logger2 = new ConsoleLogger(msg);
        logger2 = new Enc(logger2);
        System.out.println(logger2.log());
        System.out.println();

        // Question 4
        System.out.println("Question 4");
        msg = "Good Bye";
        LoggerFactory logger3 = new ConsoleLogger(msg);
        logger3 = new Enc(logger3);
        logger3 = new Html(logger3);
        System.out.println(logger3.log());
        System.out.println();

        // Question 4 - test file logger
        System.out.println("Question 4  - test file logger");
        msg = "Good Bye";
        LoggerFactory logger4 = new FileLogger(msg);
        logger4 = new Enc(logger4);
        logger4 = new Html(logger4);
        System.out.println(logger4.getIsFileLogger() ? "File Logging Success." : logger4.log());
        System.out.println();
    }
}
