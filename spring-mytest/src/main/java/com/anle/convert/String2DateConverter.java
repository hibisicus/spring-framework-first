package com.anle.convert;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.util.Date;

/**
 * @author AnnLee
 * @version 1.0
 * @date 2021-10-12 17:21
 */
public class String2DateConverter implements Converter<String, Date> {
    @Override
    public Date convert(String source) {
        try {
            return DateUtils.parseDate(source, new String[] { "yyyy-MM-dd HH:mm:ss" });
        } catch (ParseException e) {
            return null;
        }
    }
}
