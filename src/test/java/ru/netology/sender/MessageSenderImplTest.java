package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import java.util.HashMap;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class MessageSenderImplTest {

    @Mock
    private GeoService geoService;

    @Mock
    private LocalizationService localizationService;

    @InjectMocks
    private MessageSenderImpl messageSender;

    private static final String IP_ADDRESS_HEADER = "x-real-ip";

    @Test
    public void testRussianMessage() {
        String russianIp = "172.123.12.19";
        Location russiaLocation = new Location("Moscow", Country.RUSSIA, null, 0);
        Mockito.when(geoService.byIp(russianIp)).thenReturn(russiaLocation);
        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");

        Map<String, String> headers = new HashMap<>();
        headers.put(IP_ADDRESS_HEADER, russianIp);

        String result = messageSender.send(headers);

        Assertions.assertEquals("Добро пожаловать", result);
    }

    @Test
    public void testEnglishMessageForAmerican() {
        String americanIp = "96.44.183.149";
        Location usaLocation = new Location("New York", Country.USA, null, 0);
        Mockito.when(geoService.byIp(americanIp)).thenReturn(usaLocation);
        Mockito.when(localizationService.locale(Country.USA)).thenReturn("Welcome");

        Map<String, String> headers = new HashMap<>();
        headers.put(IP_ADDRESS_HEADER, americanIp);

        String result = messageSender.send(headers);

        Assertions.assertEquals("Welcome", result);
    }

    @Test
    public void testEnglishMessageForOther() {
        String otherIp = "127.0.0.1";
        Location russiaLocation = new Location(null, Country.RUSSIA, null, 0);
        Mockito.when(geoService.byIp(otherIp)).thenReturn(russiaLocation);
        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");

        Map<String, String> headers = new HashMap<>();
        headers.put(IP_ADDRESS_HEADER, otherIp);

        String result = messageSender.send(headers);

        Assertions.assertEquals("Добро пожаловать", result);
    }

}