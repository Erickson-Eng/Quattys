package br.com.quattys.sportive.business.enums;

public enum SportEnum {
    SOCCER("SOCCER"),
    BASKETBALL("BASKETBALL"),
    VOLLEY_BALL("VOLLEY_BALL");

    private final String code;

    SportEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
