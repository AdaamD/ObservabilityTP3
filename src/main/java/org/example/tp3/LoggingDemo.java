package org.example.tp3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class LoggingDemo {
    private static final Logger logger = LoggerFactory.getLogger(LoggingDemo.class);

    public static void main(String[] args) {
        // Niveaux de log
        logger.trace("C'est un message TRACE");
        logger.debug("C'est un message DEBUG");
        logger.info("C'est un message INFO");
        logger.warn("C'est un message WARN");
        logger.error("C'est un message ERROR");

        // Logging paramétré
        String name = "John";
        int age = 30;
        logger.info("L'utilisateur {} a {} ans", name, age);

        // Logging d'exceptions
        try {
            throw new RuntimeException("Une erreur s'est produite");
        } catch (Exception e) {
            logger.error("Une exception a été attrapée", e);
        }

        // Utilisation de MDC (Mapped Diagnostic Context)
        MDC.put("userId", "12345");
        logger.info("Action effectuée par l'utilisateur");
        MDC.clear();

        // Performance logging
        long startTime = System.currentTimeMillis();
        // Simuler une opération longue
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        logger.info("L'opération a pris {} ms", endTime - startTime);
    }
}
