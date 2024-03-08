package model;

import lombok.*;
import lombok.experimental.Accessors;

import java.util.Scanner;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Person {
    private int id;
    private String fullName;
    private String gender;
    private String email;
    private String address;

    public Person addPerson(Scanner input){
        System.out.print("Enter fullname: ");
        fullName = input.nextLine();
        System.out.print("Enter The Gender : ");
        gender = input.nextLine();
        System.out.print("Enter The Address : ");
        address = input.nextLine();
        System.out.print("Enter The Email Address : ");
        email = input.nextLine();

        return this;
    }

}
