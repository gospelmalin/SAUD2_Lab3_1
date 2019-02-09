package ui;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import model.User;
import repository.UserRepository;

public class UI {
	
	
	private void runUI() {
		// print welcome message
		System.out.println("Welcome to SAUD2! \nAn app to Show, Add, Update or Delete Users in userdblab2 made by Malin\n");
	while (true) {
		UserRepository ur = new UserRepository();
		Scanner input = new Scanner(System.in);
		
		int action; 
		System.out.println("What do you want to do? \nPlease enter \n1 to Show all users, \n2 to Show selected user, "
				+ "\n3 to Add user, \n4 to Update user, \n5 to Delete user, or \n99 to Cancel");
		action = input.nextInt();
		System.out.println(action); //TODO TEMP
		
		if (action != 1 && action != 2 && action != 3 && action != 4 && action != 5 && action != 99) { 
			System.out.println(
					"Invalid value entered. You have to enter a value between 1 and 5, or, if you want to cancel, 99.");
		}
		else if (action == 99) {
			System.out.println("You want to cancel");
			
			//TODO
		}
		else if (action == 1) {
			System.out.println("You want to show all users.");
			ArrayList<User> userList = ur.getAllUsers();
			for(int i = 0; i<userList.size(); i++) {
				User user = new User();
				user = userList.get(i);
				System.out.println(user); //TODO snygga till - men funkar OBS inputvalidering
			}
			//TODO
		}
		else if (action == 2) {
			System.out.println("You want to show selected user");
			System.out.println("Enter id of user to show");
			int userId = input.nextInt();
			User user = ur.getSelectedUser(userId);
			System.out.println("You selected to show user with id " + user.getId() + "\nName: " + user.getName() + " Profession: " + user.getProfession() + "\n");
			//TODO
		}
		else if (action == 3) {
			System.out.println("You want to add a user");
			System.out.println("Enter id for new user");
			int userId = input.nextInt();
			System.out.println("Enter name of new user");
			String name = input.next();
			System.out.println("Enter profession of new user. If no profession, enter None.");
			String profession = input.next();
			User user = new User(userId, name, profession);
			String response = ur.add(user);
			System.out.println(response);
			//TODO
		}
		else if (action == 4) {
			System.out.println("You want to update a user");
			//TODO
		}
		else if (action == 5) {
			System.out.println("You want to delete a user");
			//TODO
		}
	}
	}
		
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		UI ui = new UI();
		ui.runUI();
	}

}
