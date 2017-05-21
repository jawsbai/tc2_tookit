package com.tc2.database;

public class FieldValue {
    public final FieldDefined defined;
    public final Object value;

    public FieldValue(FieldDefined defined, Object value) {
        this.defined = defined;
        this.value = value;
    }
}
