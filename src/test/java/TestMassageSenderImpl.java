import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class TestMassageSenderImpl {

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
        //    sut = new LocalizationServiceImpl();
    }

    @AfterEach
    public void finished() {
        System.out.println("Тест завершен");
        //    sut = null;
    }

    @ParameterizedTest
    @MethodSource("source")
    public void sendTest(String ip,Location location, String expected){
        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp(ip))
                .thenReturn(location);

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(location.getCountry()))
                .thenReturn(expected);

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService,localizationService);

        Map<String, String> headers = new HashMap<>();
        headers.put("x-real-ip",ip);

        String preferences = messageSender.send(headers);

        Assertions.assertEquals(expected,preferences);

    }
    private static Stream<Arguments> source() {
        return Stream.of(Arguments.of("172.0.32.11",new Location("Moscow", Country.RUSSIA, "Lenina", 15), "Добро пожаловать"),
                Arguments.of("96.44.183.149", new Location("New York", Country.USA, " 10th Avenue", 32), "Welcome"));

    }
}
