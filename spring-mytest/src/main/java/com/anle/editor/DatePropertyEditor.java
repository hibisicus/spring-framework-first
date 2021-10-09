package com.anle.editor;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author AnnLee
 * @version 1.0
 * @date 2021-10-09 15:22
 * @Description 自定义属性编辑器
 */
public class DatePropertyEditor extends PropertyEditorSupport {

    private String format = "yyyy-MM-dd";

    public void setFormat(String format) {
        this.format = format;
    }

    @Override
    public void setAsText(String arg0) throws IllegalArgumentException {
        System.out.println("arg0:" + arg0);
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            Date date = sdf.parse(arg0);
            this.setValue(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
