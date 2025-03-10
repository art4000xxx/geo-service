package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

public class GeoServiceImplTest {

    @Test
    public void testByIp_Localhost() {
        GeoServiceImpl geoService = new GeoServiceImpl();
        Location location = geoService.byIp(GeoServiceImpl.LOCALHOST);

        Assertions.assertNull(location.getCountry());
        Assertions.assertNull(location.getCity());
        Assertions.assertNull(location.getStreet());
        Assertions.assertEquals(0, location.getBuiling());
    }

    @Test
    public void testByIp_MoscowIp() {
        GeoServiceImpl geoService = new GeoServiceImpl();
        Location location = geoService.byIp(GeoServiceImpl.MOSCOW_IP);

        Assertions.assertEquals(Country.RUSSIA, location.getCountry());
        Assertions.assertEquals("Moscow", location.getCity());
        Assertions.assertEquals("Lenina", location.getStreet());
        Assertions.assertEquals(15, location.getBuiling());
    }

    @Test
    public void testByIp_NewYorkIp() {
        GeoServiceImpl geoService = new GeoServiceImpl();
        Location location = geoService.byIp(GeoServiceImpl.NEW_YORK_IP);

        Assertions.assertEquals(Country.USA, location.getCountry());
        Assertions.assertEquals("New York", location.getCity());
        Assertions.assertEquals(" 10th Avenue", location.getStreet());
        Assertions.assertEquals(32, location.getBuiling());
    }

    @Test
    public void testByIp_RussianPrefix() {
        GeoServiceImpl geoService = new GeoServiceImpl();
        Location location = geoService.byIp("172.123.45.67");

        Assertions.assertEquals(Country.RUSSIA, location.getCountry());
        Assertions.assertEquals("Moscow", location.getCity());
        Assertions.assertNull(location.getStreet());
        Assertions.assertEquals(0, location.getBuiling());
    }

    @Test
    public void testByIp_AmericanPrefix() {
        GeoServiceImpl geoService = new GeoServiceImpl();
        Location location = geoService.byIp("96.123.45.67");

        Assertions.assertEquals(Country.USA, location.getCountry());
        Assertions.assertEquals("New York", location.getCity());
        Assertions.assertNull(location.getStreet());
        Assertions.assertEquals(0, location.getBuiling());
    }

    @Test
    public void testByIp_OtherIp() {
        GeoServiceImpl geoService = new GeoServiceImpl();
        Location location = geoService.byIp("192.168.1.1");

        Assertions.assertNull(location);
    }

    @Test
    public void testByIp_NullIp() {
        GeoServiceImpl geoService = new GeoServiceImpl();
        Location location = geoService.byIp(null);

        Assertions.assertNull(location);
    }
}