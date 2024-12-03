package lps;

import java.time.LocalDateTime;

public class LPSBuilder {
    private LPS lps = new LPS();

    public LPSBuilder withTimestamp(LocalDateTime timestamp) {
        lps.setTimestamp(timestamp);
        return this;
    }

    public LPSBuilder withEvent(String event) {
        lps.setEvent(event);
        return this;
    }

    public LPSBuilder withUser(String user) {
        lps.setUser(user);
        return this;
    }

    public LPSBuilder withAction(String action) {
        lps.setAction(action);
        return this;
    }

    public LPS build() {
        return lps;
    }
}
