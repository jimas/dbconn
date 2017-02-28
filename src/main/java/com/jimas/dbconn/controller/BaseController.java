package com.jimas.dbconn.controller;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

@Controller
public class BaseController {
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(String.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String value) {
                try {
                    if (value != null) {
                        setValue(value.trim());
                    } else {
                        setValue(null);
                    }
                } catch (Exception e) {
                    setValue(null);
                }
            }
        });
        dataBinder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String value) {
                try {
                    if (StringUtils.isNotBlank(value) && value.contains(":")) {
                        setValue(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(value));
                    } else if (StringUtils.isNotBlank(value)) {
                        setValue(new SimpleDateFormat("yyyy-MM-dd").parse(value));
                    } else {
                        setValue(null);
                    }
                } catch (ParseException e) {
                    setValue(null);
                }
            }
        });
    }
}
