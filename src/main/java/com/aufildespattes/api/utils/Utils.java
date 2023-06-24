package com.aufildespattes.api.utils;

public class Utils {
    /**
     * Formats a string into a slug format.
     * @param name the string to format
     * @return the formatted slug string
     */
    public final static String formatSlug(String name) {
        String slug = name.toLowerCase().replaceAll("[^a-z0-9\\s-]", "").replace(" ", "-");
        return slug;
    }

    /**
     * Formats a string into a slug format.
     * @param name the string to format
     * @return the formatted slug string
     */
    public final static String formatAddress(String name) {
        String slug = name.toLowerCase().replaceAll("[^a-z0-9\\s-]", "").replace(" ", "+");
        return slug;
    }
}
