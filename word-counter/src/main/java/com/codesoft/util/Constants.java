package com.codesoft.util;

import java.time.chrono.IsoChronology;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.ResolverStyle;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;
import static java.time.format.DateTimeFormatter.ISO_LOCAL_TIME;

public class Constants {

    // Since IntelliJ Asks to do it
    private Constants(){}


    public static final String NEWLINE = "\n";
    public static final String TEXT = "text";
    public static final String FILE_ICON = "word-counter/src/main/resources/icons/file.png";
    public static final String TEXT_ICON = "word-counter/src/main/resources/icons/text.png";
}
