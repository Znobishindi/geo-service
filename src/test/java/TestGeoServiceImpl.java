import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestGeoServiceImpl {

    GeoServiceImpl sut;

    @BeforeAll
    public static void started() {
        System.out.println("Начат процесс тестирования");
    }

    @AfterAll
    public static void ended() {
        System.out.println("Процесс тестирования завершен");
    }

    @BeforeEach
    public void init() {
        System.out.println("Тест запущен");
        sut = new GeoServiceImpl();
    }

    @AfterEach
    public void finished() {
        System.out.println("Тест завершен");
        sut = null;
    }
    @ParameterizedTest
    @MethodSource("source")
    public void byIpTest(String ip,Location loc) {
        Location location = sut.byIp(ip);
        List<String> result = Arrays.asList(location.getCity(),location.getStreet(),(String.valueOf(location.getBuiling())));
        List<String> expected = Arrays.asList(loc.getCity(),loc.getStreet(),String.valueOf(loc.getBuiling()));
        assertThat(expected,is(result));
    }

    private static Stream<Arguments> source() {
        return Stream.of(Arguments.of("127.0.0.1",new Location(null,null,null,0)),
                Arguments.of("172.0.32.11",new Location("Moscow", Country.RUSSIA, "Lenina", 15)),
                Arguments.of("96.44.183.149", new Location("New York", Country.USA, " 10th Avenue", 32)),
                Arguments.of("172.12.40.555", new Location("Moscow", Country.RUSSIA, null, 0)),
                Arguments.of("96.111.40.223", new Location("New York", Country.USA, null, 0)));
    }
    @Test
    public void byCoordinatesTest() {
        double latitude = 1.1;
        double longitude = 8.4;
        var expected = RuntimeException.class;
        Assertions.assertThrows(expected,
                () -> sut.byCoordinates(latitude,longitude));
    }
}
