package com.raulrh.tiendatv.util;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The type Local date adapter.
 */
public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

    @Override
    public LocalDate unmarshal(String dateString) {
        return LocalDate.parse(dateString, formatter);
    }

    @Override
    public String marshal(LocalDate date) {
        return date.format(formatter);
    }
}