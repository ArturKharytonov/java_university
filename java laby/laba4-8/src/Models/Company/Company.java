package Models.Company;

import Models.Tariffs.BasicTariff;
import Models.Tariffs.PremiumTariff;
import Models.Tariffs.Tariff;

import java.util.function.Predicate;
import java.util.*;
import java.util.stream.Collectors;

public class Company {
    private final List<Tariff> _tariffs = new ArrayList<Tariff>();
    //private final Scanner _scanner = new Scanner(System.in);
    private Scanner _scanner;

    public Company(Scanner scanner){
        _scanner = scanner;
    }
    public Company(){}
    public void createListOfTariffs(){
        while (true) {
            System.out.println("Menu:\n" +
                    "1 - Create Basic Tariff\n" +
                    "2 - Create Premium Tariff.\n" +
                    "3 - EXIT.\n");
            System.out.print("Enter your choice: ");

            int choice = _scanner.nextInt();
            _scanner.nextLine(); // Consume newline character
            if (choice == 3)
                return;

            System.out.print("Enter name: ");
            String name = _scanner.nextLine();

            System.out.print("Enter subscription fee: ");
            double subscriptionFee = _scanner.nextDouble();

            System.out.print("Enter count of users: ");
            int countOfUsers = _scanner.nextInt();

            Tariff tariff;
            if (choice == 1) {
                tariff = new BasicTariff(name, subscriptionFee, countOfUsers);
                System.out.println("Basic tariff created successfully!");
            }
            else if (choice == 2) {
                System.out.println("Minutes of tv sybscription: ");
                int minutesOfTvSubscription = _scanner.nextInt();
                tariff = new PremiumTariff(name, subscriptionFee, countOfUsers, minutesOfTvSubscription);
                System.out.println("Premium tariff created successfully!");
            }
            else {
                System.out.println("Invalid choice. Please try again.");
                continue;
            }
            _tariffs.add(tariff);
        }
    }

    public int calculateCountOfClients() {
        int clients = 0;
        for (Tariff tariff : _tariffs)
            clients += tariff.getCountOfUsers();
        return clients;
    }

    public void sortTariffsBySubscriptionFee(){
        Collections.sort(_tariffs, new Comparator<Tariff>() {
            @Override
            public int compare(Tariff t1, Tariff t2) {
                return Double.compare(t1.getSubscriptionFee(), t2.getSubscriptionFee());
            }
        });
    }

    public List<Tariff> filterTariffs(Predicate<Tariff> filter){
        return _tariffs.stream().filter(filter).collect(Collectors.toList());
    }

    public List<Tariff> getTariffs(){
        return _tariffs;
    }

    public void addTariff(Tariff tariff){
        _tariffs.add(tariff);
    }

    public Predicate<Tariff> getFiltrationPredicate(){
        Predicate<Tariff> filter =  tariff -> true;

        while (true){
            System.out.println("Menu:\n" +
                    "1 - Set name\n" +
                    "2 - Set subscription range\n" +
                    "3 - Set user range.\n" +
                    "4 - Search");
            int choice = _scanner.nextInt();
            _scanner.nextLine(); // Consume newline character

            switch (choice){
                case 1: {
                    System.out.print("Enter name: ");
                    final String name = _scanner.nextLine();
                    filter = filter.and(new Predicate<Tariff>() {
                        @Override
                        public boolean test(Tariff tariff) {
                            return tariff.getName().equalsIgnoreCase(name);
                        }
                    });
                    break;
                }
                case 2: {
                    System.out.print("Enter minimum subscription fee: ");
                    final double minSubscriptionFee = _scanner.nextDouble();
                    _scanner.nextLine(); // Consume newline character
                    System.out.print("Enter maximum subscription fee: ");
                    final double maxSubscriptionFee = _scanner.nextDouble();
                    _scanner.nextLine(); // Consume newline character
                    
                    filter = filter.and(new Predicate<Tariff>() {
                        @Override
                        public boolean test(Tariff tariff) {
                            return tariff.getSubscriptionFee() >= minSubscriptionFee &&
                                    tariff.getSubscriptionFee() <= maxSubscriptionFee;
                        }
                    });
                    break;
                }
                case 3:{
                    System.out.print("Enter minimum user count: ");
                    final int minUserCount = _scanner.nextInt();
                    _scanner.nextLine(); // Consume newline character
                    System.out.print("Enter maximum user count: ");
                    final int maxUserCount = _scanner.nextInt();
                    _scanner.nextLine(); // Consume newline character
                    filter = filter.and(new Predicate<Tariff>() {
                        @Override
                        public boolean test(Tariff tariff) {
                            return tariff.getCountOfUsers() >= minUserCount &&
                                    tariff.getCountOfUsers() <= maxUserCount;
                        }
                    });
                    break;
                }
                case 4:
                    return filter;
                default:
                    System.out.println("Wrong choice");
            }
        }
    }
}
