package lps;

import java.time.LocalDateTime;

public class LPS {
    private LocalDateTime timestamp;
    private String event;
    private String user;
    private String action;

    public LPS() {}

    public LPS(LocalDateTime timestamp, String event, String user, String action) {
        this.timestamp = timestamp;
        this.event = event;
        this.user = user;
        this.action = action;
    }

    // Getters
    public LocalDateTime getTimestamp() { return timestamp; }
    public String getEvent() { return event; }
    public String getUser() { return user; }
    public String getAction() { return action; }

    // Setters
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
    public void setEvent(String event) { this.event = event; }
    public void setUser(String user) { this.user = user; }
    public void setAction(String action) { this.action = action; }

    @Override
    public String toString() {
        return "LPS{" +
                "timestamp=" + timestamp +
                ", event='" + event + '\'' +
                ", user='" + user + '\'' +
                ", action='" + action + '\'' +
                '}';
    }
}
