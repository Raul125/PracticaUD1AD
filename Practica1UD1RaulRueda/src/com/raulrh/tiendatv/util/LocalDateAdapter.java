package com.raulrh.tiendatv.util;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Adapter class for converting between {@link LocalDate} and {@link String} in XML.
 * Uses ISO-8601 date format (e.g., "YYYY-MM-DD").
 */
public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

    /**
     * Converts a {@link String} in ISO-8601 format to a {@link LocalDate}.
     *
     * @param dateString the date string to convert.
     * @return the corresponding {@link LocalDate} object.
     */
    @Override
    public LocalDate unmarshal(String dateString) {
        return LocalDate.parse(dateString, formatter);
    }

    /**
     * Converts a {@link LocalDate} to a {@link String} in ISO-8601 format.
     *
     * @param date the {@link LocalDate} to convert.
     * @return the date formatted as a {@link String} in ISO-8601 format.
     */
    @Override
    public String marshal(LocalDate date) {
        return date.format(formatter);
    }
}