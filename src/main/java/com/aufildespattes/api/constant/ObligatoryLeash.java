package com.aufildespattes.api.constant;

public enum ObligatoryLeash {
    YES("Oui"),
    NO("Non"),
    RECOMMENDED("Recommandé");

    private String label;

    private ObligatoryLeash(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
