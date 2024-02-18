package com.dreamcase.app.enums;

public enum CaseExceptionMessage {
    CASE_NOT_FOUND_EXCEPTION("No.case.found.with.ID");

    private final String value;
    CaseExceptionMessage(String s) {
        this.value=s;
    }

    public String getValue() {
        return value;
    }
}
