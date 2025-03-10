package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;

public class LocalizationServiceImplTest {

    @Test
    public void testLocale_Russia() {
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        String result = localizationService.locale(Country.RUSSIA);
        Assertions.assertEquals("Добро пожаловать", result);
    }

    @Test
    public void testLocale_USA() {
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        String result = localizationService.locale(Country.USA);
        Assertions.assertEquals("Welcome", result);
    }

    @Test
    public void testLocale_Brazil() {
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        String result = localizationService.locale(Country.BRAZIL);
        Assertions.assertEquals("Welcome", result);
    }

    @Test
    public void testLocale_Germany() {
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        String result = localizationService.locale(Country.GERMANY);
        Assertions.assertEquals("Welcome", result);
    }
}