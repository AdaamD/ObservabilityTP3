package lps;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProfileBuilder {
    public static Map<String, UserProfile> buildProfiles(List<LPS> logEntries) {
        Map<String, UserProfile> profiles = new HashMap<>();

        for (LPS entry : logEntries) {
            String userId = entry.getUser();
            UserProfile profile = profiles.computeIfAbsent(userId, UserProfile::new);

            String action = entry.getAction().toLowerCase();
            if (action.contains("creating a new user")) {
                profile.incrementCreateUserCount();
            } else if (action.contains("displaying all products")) {
                profile.incrementDisplayProductsCount();
            } else if (action.contains("fetching product")) {
                profile.incrementFetchProductCount();
            } else if (action.contains("adding a new product")) {
                profile.incrementAddProductCount();
            } else if (action.contains("deleting product")) {
                profile.incrementDeleteProductCount();
            } else if (action.contains("updating product")) {
                profile.incrementUpdateProductCount();
            }
        }

        return profiles;
    }
}

