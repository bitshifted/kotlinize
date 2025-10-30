package co.bitshifted.kotlinize;

import co.bitshifted.kotlinize.data.Address;
import co.bitshifted.kotlinize.data.City;
import co.bitshifted.kotlinize.data.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static co.bitshifted.kotlinize.NullAware.*;
import static org.junit.jupiter.api.Assertions.*;

public class NullAwareTest {
    private Person person;

    @BeforeEach
    void setup() {
        person = person = new Person("John", "Doe", new Address("Main street 1", new City("Springfield", null), "USA"), null);
    }

    @Test
    void shouldReturnNullAwareWhenFieldIsNull() {
        var result = nullAware(() -> person.workAddress().city().zipCode());
        assertNotNull(result);
        assertNull(result.value());

        result = nullAware(() -> person.homeAddress().city().name());
        assertEquals(person.homeAddress().city().name(), result.value());

        result = nullAware(() -> person.homeAddress().city().zipCode());
        assertNull(result.value());
    }

    @Test
    void shouldReturnNullSafeValue() {
        String zipCode = nullSafe(() -> person.workAddress().city().zipCode());
        assertNull(zipCode);

        var street = nullSafe(() -> person.homeAddress().street());
        assertEquals("Main street 1", street);
    }

    @Test
    void shouldReturnDefaultValueWhenNull() {
        String cityName = nullSafe(() -> person.workAddress().city().name(), "Unknown City");
        assertEquals("Unknown City", cityName);
        String street = nullSafe(() -> person.homeAddress().street(), "Unknown Street");
        assertEquals("Main street 1", street);
    }

    @Test
    void shouldReplicateElvisOperator() {
        String cityName = nullAware(() -> person.workAddress().city().name()).ifNull(() -> {
            var now = LocalDateTime.now();
            if (now.getDayOfMonth() < 15) {
                return "First Half City";
            } else {
                return "Second Half City";
            }
        });
        assertTrue(List.of("First Half City", "Second Half City").contains(cityName));
        String street = nullAware(() -> person.homeAddress().street()).ifNull(() -> "Another Street");
        assertEquals("Main street 1", street);
    }

    @Test
    void shouldReplicateLetFunctionalityWithSameReturnType() {
        String upperCaseCity = nullAware(() -> person.homeAddress().city().name()).let(name -> name.toUpperCase());
        assertEquals("SPRINGFIELD", upperCaseCity);

        String upperCaseZip = nullAware(() -> person.homeAddress().city().zipCode()).let(name -> name.toUpperCase());
        assertNull(upperCaseZip);
    }

    @Test
    void shouldReplicateLetDifferentReturnType() {
        Integer cityNameLength = nullAware(() -> person.homeAddress().city().name()).let(name -> name.length());
        assertEquals(11, cityNameLength);

        Integer zipCodeLength = nullAware(() -> person.homeAddress().city().zipCode()).let(name -> name.length());
        assertNull(zipCodeLength);
    }

    @Test
    void shouldReplicateApplyFunctionality() {
        StringBuilder result = nullAware(StringBuilder::new).apply(b -> {
            b.append("Hello, ");
            b.append("World!");
        });
        assertEquals("Hello, World!", result.toString());
    }

}
