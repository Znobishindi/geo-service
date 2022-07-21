import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.stream.Stream;

public class TestLocalizationServiceImpl {
    LocalizationServiceImpl sut;

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
        sut = new LocalizationServiceImpl();
    }

    @AfterEach
    public void finished() {
        System.out.println("Тест завершен");
        sut = null;
    }

    @ParameterizedTest
    @MethodSource("source")
    public void locateTest(Country country, String expected) {
        String result = sut.locale(country);
        Assertions.assertEquals(expected, result);

    }

    private static Stream<Arguments> source() {
        return Stream.of(Arguments.of(Country.RUSSIA, "Добро пожаловать"),
                Arguments.of(Country.USA, "Welcome"),
                Arguments.of(Country.BRAZIL, "Welcome"));
    }
}
