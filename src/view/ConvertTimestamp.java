package view;

import java.time.Instant;

public class ConvertTimestamp {
    public static void main(String[] args) {
        long timestamp = 1704816140190L;
        Instant instant = Instant.ofEpochMilli(timestamp);

        System.out.println("Thời điểm: " + instant);
    }
}

