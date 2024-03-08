package service;

import model.User;
import repository.UserRepository;

import java.util.Scanner;

public class UserService {
    private final UserRepository userRepository;
public UserService(UserRepository userRepository) {
this.userRepository = userRepository;

}
public int createUser(Scanner input) {
return userRepository.createNewUser(new User().addNewUser(input));
}
public User login(Scanner input) {
    return userRepository.loginUser(new User().addNewUser(input));
    }
}
