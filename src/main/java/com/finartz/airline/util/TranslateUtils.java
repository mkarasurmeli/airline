package com.finartz.airline.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class TranslateUtils {

    private static MessageSource messageSource;

    @Autowired
    public TranslateUtils(MessageSource messageSource) {
        TranslateUtils.messageSource = messageSource;
    }

    private TranslateUtils() {
    }

    public static MessageSource getMessageSource() {
        return messageSource;
    }

    public static String getMessage(String messageCode) {
        return messageSource.getMessage(messageCode, null, LocaleContextHolder.getLocale());
    }

    public static String getMessage(String messageCode, Object... args) {
        return messageSource.getMessage(messageCode, args, LocaleContextHolder.getLocale());
    }

}
