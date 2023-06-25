package com.aufildespattes.api.utils;

import java.text.Normalizer;

public class Utils {
    /**
     * Formats a string into a slug format.
     * 
     * @param name the string to format
     * @return the formatted slug string
     */
    public final static String formatSlug(String text) {
        String slug = text.replaceAll(" ", "-");
        slug = Normalizer.normalize(slug, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
                .replaceAll("[^\\p{ASCII}]", "");
        slug = slug.replaceAll("[^a-zA-Z0-9-]", "-");
        slug = slug.replaceAll("-{2,}", "-");
        slug = slug.replaceAll("^-|-$", "");
        return slug.toLowerCase();
    }

    /**
     * Formats a string into a slug format.
     * 
     * @param name the string to format
     * @return the formatted slug string
     */
    public final static String formatAddress(String name) {
        String slug = name.toLowerCase().replaceAll("[^a-z0-9\\s-]", "").replace(" ", "+");
        return slug;
    }
}
