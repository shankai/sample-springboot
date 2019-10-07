package io.github.shankai.springboot.i18n;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import lombok.extern.log4j.Log4j2;

/**
 * MyLocaleResolver
 */
@Log4j2
public class MyLocaleResolver implements LocaleResolver {

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String custom = request.getParameter("locale");

        log.info(">>> custom locale: {}", custom);

        Locale locale = request.getLocale();

        if (StringUtils.isEmpty(custom)) {

            log.info(">>> default locale: {}", locale.getLanguage());

            return locale;
        }

        String[] split = custom.split("_");
        if (split.length > 1) {
            locale = new Locale(split[0], split[1]);
        } else {
            locale = new Locale(split[0]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
        // TODO Auto-generated method stub

    }

}