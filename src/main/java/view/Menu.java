package view;

import model.Person;
import model.User;
import repository.PersonRepository;
import repository.UserRepository;
import service.PersonService;
import service.UserService;
import utils.TableUtils;

import java.util.*;

public class Menu {
    private static UserService userService = new UserService(new UserRepository());
    private static PersonService personService =
            new PersonService(new PersonRepository());
    public void showMenuOption(){
        User user = new User();
        Scanner scanner = new Scanner(System.in);
        // Check if the user is already logged in
        boolean isLoggedIn = false;
        User loggedInUser = null;

        while (true) {
            if (isLoggedIn) {
                System.out.println("1. Show Menu");
                System.out.println("2. Logout");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        showMenu(loggedInUser);
                        break;
                    case 2:
                        isLoggedIn = false;
                        loggedInUser = null;
                        System.out.println("Logged out successfully.");
                        break;
                    case 3:
                        System.out.println("Exiting the program.");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } else {
                System.out.println("1. Login");
                System.out.println("2. Register");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        scanner.nextLine();
                        loggedInUser = userService.login(scanner);
                        if (loggedInUser != null) {
                            isLoggedIn = true;
                            System.out.println("Login successful!");
                        } else {
                            System.out.println("Invalid username or password");
                        }
                        break;
                    case 2:
                        scanner.nextLine();
                        userService.createUser(scanner);
                        System.out.println("User registered successfully!");
                        break;
                    case 3:
                        System.out.println("Exiting the program.");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    }
    private static void showMenu(User loggedInUser) {
        Scanner input = new Scanner(System.in);
        int option;
        do {
            option = Menu.renderMain(input);
            switch (option) {
                case 1: {
                    input.nextLine(); // clear buffer
                    System.out.println(
                            personService.createPerson(input) > 0 ?
                                    "Successfully Created a New Person"
                                    : ""
                    );

                }
                break;
                case 2: {
                    System.out.println(
                            personService
                                    .updatePerson(input) > 0 ?
                                    "Successfully Update Person Info"
                                    : ""
                    );
                }
                break;
                case 3: {
                    System.out.println(
                            personService
                                    .deletePersonByID(input) > 0 ?
                                    "Successfully Remove the Person"
                                    : "");
                    ;
                }
                break;
                case 4: {
                    int showOption;
                    List<String> showMenu = new ArrayList<>(List.of(
                            "Show Original Order",
                            "Show Descending Order (ID)",
                            "Show Descending Order (name) ",
                            "Exit"));
                    do {
                        TableUtils.renderMenu(showMenu, "Show Person Information");
                        System.out.print("Choose your option: ");
                        showOption = input.nextInt();


                        switch (showOption) {
                            case 1:

                                TableUtils.renderObjectToTable(personService.getAllPerson());
                                break;
                            case 2:
                                // descending id
                                TableUtils.renderObjectToTable(
                                        personService.getAllPersonDescendingByID()
                                );
                                break;
                            case 3:
                                // descending name
                                TableUtils.renderObjectToTable(
                                        personService.getAllPersonDescendingByName()
                                );
                                break;
                            default:
                                System.out.println("Invalid option ...!!!!");
                                break;
                        }
                    } while (showOption != showMenu.size());
                }
                break;
                case 5: {
                    int searchOption;
                    List<String> searchMenu = new ArrayList<>(Arrays.asList(
                            "Search By ID",
                            "Search By Gender",
                            "Search By Country",
                            "Exit"));
                    do {
                        TableUtils.renderMenu(searchMenu, "Search for Person");
                        System.out.print("Choose your option:");
                        searchOption = input.nextInt();
                        switch (searchOption) {
                            case 1:
                                int searchID = 0;
                                System.out.println("Enter Person ID to search:");
                                searchID = input.nextInt();
                                int finalSearchID = searchID;
                                try {
                                    Person optionalPerson =
                                            personService.getAllPerson()
                                                    .stream()
                                                    .filter(person -> person.getId() == finalSearchID)
                                                    .findFirst()
                                                    .orElseThrow(() -> new ArithmeticException("Whatever exception!! "));
                                    TableUtils.renderObjectToTable(
                                            Collections.singletonList(optionalPerson));
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                    System.out.println("There is no element with ID=" + searchID);
                                }

                                break;
                            case 2:
                                break;
                            case 3:
                                break;
                        }

                    } while (searchOption != searchMenu.size());

                }
                break;
                case 6:
                    System.out.println("Exit from the program!!! ");
                    break;
                default:
                    System.out.println("Invalid Option!!!!!! ");
                    break;
            }
        } while (option != 6);
    }
    private final static List<String> mainMenu = new ArrayList<>(List.of(
            "Add New Person ",
            "Update Person ",
            "Delete Person",
            "Show Person Information",
            "Search Person Information",
            "Exit"));
    public static int renderMain(Scanner input){
        TableUtils.renderMenu(mainMenu, "Person Management System");
        System.out.print("Enter your option : ");
        return input.nextInt();
    }
}
