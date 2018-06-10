//name: Erika Vargas
//email: ertika.vargas@bellevuecollege.edu
//SID: 950638445
//Date;06/10/2018
//Program Description: this program is a rudimentary database for the storage of only the user generated identities.

import java.io.*;
import java.io.FileNotFoundException;
import java.util.*;

public class userName {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("users.txt"));

        String[] users;
        users = getUserList(input);
        String newUser;
        newUser = validation(users);
        updateList(users, newUser);
    }

    // this method reads and loads the list of usernames to an array.
    public static String[] getUserList(Scanner input) {
        int letter = 0;
        String userName = "";
        //reads the file
        while (input.hasNextLine()) {
            String name = input.nextLine().toLowerCase();
            System.out.println(name);
            userName += name + "\n";
            letter++;
        }
        String[] arrayName = new String[letter];
        Scanner readUser = new Scanner(userName);
        int i = 0;
        while (readUser.hasNextLine()) {
            arrayName[i] = readUser.nextLine();
            i++;
        }
        return arrayName;
    }

    //This method checks for duplicate usernames
    public static boolean isDuplicate(String[] users, String userName) {
        boolean isDuplicate = false;
        for (int i = 0; i < users.length; i++) {
            String newName = users[i];
            if (newName.equals(userName)) {
                isDuplicate = true;
            }
        }
        return isDuplicate;
    }

    //This method checks that all the rules are valid for any attempt to create a new username.
    public static boolean isvalid(String[] arrayName, String userName) {
        boolean isDuplicate = isDuplicate(arrayName, userName);
        boolean isAbc = abc(userName);
        boolean isNameShort = nameShort(userName);
        boolean isNameLong = nameLong(userName);
        boolean validName = !isDuplicate && isAbc && !isNameShort && !isNameLong;

        if (!validName) {
            System.out.println("Invalid input.");
            if (isDuplicate) {
                System.out.println("Name duplicate.");
            }
            if (!isAbc) {
                System.out.print("Name can be made of letter only. ");
            }
            if (isNameShort) {
                System.out.print("Name too short. ");
            }
            if (isNameLong) {
                System.out.print("Name is too large. ");
            }
        }
        return validName;
    }


    //This method informs the user of the rules before creating a new username and also uses isvalid method to corroborate
    // if a new user name is valid, anf if it is then it returns the username.
    public static String validation(String[] users) {
        System.out.println("\nUser name must be between 8 and 20 characters. ");
        System.out.println("User name can only contain letters from the ABC. ");
        String userName = promptusername();
        while (!isvalid(users, userName)) {
            userName = promptusername();
        }
        return userName;
    }

    // this method prompts the user for creating a new user name.
    public static String promptusername() {
        System.out.print("\nCreate a new user: ");
        Scanner console = new Scanner(System.in);
        String newUser = console.nextLine().toLowerCase();
        return newUser;
    }

    // This method checks if the user name is too short, that is the number of characters in the names is less than 8.
    public static boolean nameShort(String newUser) {
        if (newUser.length() < 8) {
            return true;
        } else {
            return false;
        }
    }

    // This method checks if the user name is too long, that is the number of characters in the names is greater than 20.
    public static boolean nameLong(String newUser) {
        if (newUser.length() > 20) {
            return true;
        }
        return false;
    }

    //This method checks if the user name is made out of only letters between Aa and Zz
    public static boolean abc(String newUser) {
        boolean valid = true;
        for (int k = 0; k < newUser.length(); k++) {
            int asciiVal = (int) newUser.charAt(k);
            if (asciiVal < 65)
                valid = false;
            if (asciiVal > 90) {
                if (asciiVal < 97)
                    valid = false;
                if (asciiVal > 122)
                    valid = false;
            }
            if (!valid) {
                break;
            }
        }
        return valid;
    }

    //This method updates the users list with the new username
    public static void updateList(String[] arrayName, String newUser) throws FileNotFoundException {
        PrintStream output = new PrintStream("users.txt");
        arrayName = Arrays.copyOf(arrayName, arrayName.length + 1);
        arrayName[arrayName.length - 1] = newUser;
        System.out.println("New user '" + newUser + "' added to" + "\"UsersArray\"");
        System.out.println("\nContents of  \"UsersArray\"");
        for (int m = 0; m < arrayName.length; m++) {
            System.out.println(arrayName[m]);
            output.println(arrayName[m]);
        }
        System.out.println("\n\"UsersArray\" written to \"users.txt\" successfully");
    }
}

