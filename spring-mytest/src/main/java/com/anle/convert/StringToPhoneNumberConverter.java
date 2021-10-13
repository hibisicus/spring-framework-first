package com.anle.convert;

import org.springframework.core.convert.converter.Converter;

/**
 * @author AnnLee
 * @version 1.0
 * @date 2021-10-13 10:07
 */
public class StringToPhoneNumberConverter implements Converter<String, String> {
    public StringToPhoneNumberConverter() {
        super();
    }

    @Override
    public String convert(String source) {
        return null;
    }
}
