package Models.Company;

import Models.Tariffs.BasicTariff;
import Models.Tariffs.PremiumTariff;
import Models.Tariffs.Tariff;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class CompanyTest {
    private Company company;

    @BeforeEach
    public void setup() {
        company = new Company();
    }

    @Test
    public void testCalculateCountOfClientsWithNoTariffs() {
        int countOfClients = company.calculateCountOfClients();

        assertEquals(0, countOfClients);
    }

    @Test
    public void testSortTariffsBySubscriptionFee() {
        Tariff basicTariff1 = new BasicTariff("Basic1", 200.0, 5);
        Tariff basicTariff2 = new BasicTariff("Basic2", 100.0, 3);

        company.addTariff(basicTariff1);
        company.addTariff(basicTariff2);

        company.sortTariffsBySubscriptionFee();

        List<Tariff> sortedTariffs = company.getTariffs();

        // Check if the tariffs are sorted in ascending order of subscription fee
        assertEquals(100.0, sortedTariffs.get(0).getSubscriptionFee());
        assertEquals(200.0, sortedTariffs.get(1).getSubscriptionFee());
    }

    @Test
    public void testFilterByName() {
        Predicate<Tariff> filter = tariff -> tariff.getName().equals("Plan A");

        company.addTariff(new BasicTariff("Plan A", 10.0, 100));
        company.addTariff(new PremiumTariff("Plan B", 20.0, 200, 120));
        company.addTariff(new BasicTariff("Plan C", 30.0, 300));

        List<Tariff> filteredTariffs = company.filterTariffs(filter);


        assertEquals(1, filteredTariffs.size());
        assertTrue(filteredTariffs.get(0).getName().equalsIgnoreCase("Plan A"));
    }

    @Test
    public void testFilterBySubscriptionRange() {
        Predicate<Tariff> filter = tariff ->
                tariff.getSubscriptionFee() >= 10.0 &&
                        tariff.getSubscriptionFee() <= 20.0;

        company.addTariff(new BasicTariff("Plan A", 10.0, 100));
        company.addTariff(new PremiumTariff("Plan B", 20.0, 200, 120));
        company.addTariff(new BasicTariff("Plan C", 30.0, 300));

        List<Tariff> filteredTariffs = company.filterTariffs(filter);

        assertEquals(2, filteredTariffs.size());
    }

    @Test
    public void testFilterByUserRange() {
        Predicate<Tariff> filter = tariff ->
                tariff.getCountOfUsers() >= 100 &&
                        tariff.getCountOfUsers() < 200;

        company.addTariff(new BasicTariff("Plan A", 10.0, 100));
        company.addTariff(new PremiumTariff("Plan B", 20.0, 200, 120));
        company.addTariff(new BasicTariff("Plan C", 30.0, 300));

        List<Tariff> filteredTariffs = company.filterTariffs(filter);

        assertEquals(1, filteredTariffs.size());
    }

    @ParameterizedTest
    @ValueSource(strings = {"1\nPlan A\n4\n", "2\n10\n20\n4\n","3\n150\n250\n4\n"})
    public void testGetFiltrationPredicateWithName(String input) {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        company = new Company(new Scanner(in));

        Predicate<Tariff> filter = company.getFiltrationPredicate();

        company.addTariff(new BasicTariff("Plan A", 10.0, 100));
        company.addTariff(new PremiumTariff("Plan B", 21.0, 200, 120));
        company.addTariff(new BasicTariff("Plan C", 30.0, 300));


        List<Tariff> filteredTariffs = company.getTariffs().stream()
                .filter(filter)
                .collect(Collectors.toList());

        assertEquals(1, filteredTariffs.size());
    }
}