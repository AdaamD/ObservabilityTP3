package lps;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LogParser {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");


    public static List<LPS> parseLogs(String logFilePath) throws IOException {
        List<LPS> logEntries = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(logFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("User") && line.contains("is")) {
                    LPS lps = parseLine(line);
                    if (lps != null) {
                        logEntries.add(lps);
                    }
                }
            }
        }
        return logEntries;
    }

    private static LPS parseLine(String line) {
        String[] parts = line.split(" ", 4); // Sépare en 4 parties max
        if (parts.length >= 4) {
            return new LPSBuilder()
                    .withTimestamp(ZonedDateTime.parse(parts[0], DATE_TIME_FORMATTER).toLocalDateTime())
                    .withEvent("USER_ACTION")
                    .withUser(parts[1])
                    .withAction(parts[3])
                    .build();
        }
        return null;
    }

}

