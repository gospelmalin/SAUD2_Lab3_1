package ui;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import model.User;
import repository.UserRepository;

public class UI {
	
	
	private void runUI() {
		Scanner input = new Scanner(System.in);
		// print welcome message
		System.out.println("Welcome to SAUD2! \nAn app to Show, Add, Update or Delete Users in userdblab2 made by Malin\n");
	while (true) {

		int action; 
		System.out.println("What do you want to do? \nPlease enter \n1 to Show all users, \n2 to Show selected user, "
				+ "\n3 to Add user, \n4 to Update user, \n5 to Delete user, or \n99 to Cancel");
		// Input must be integer
		if(!input.hasNextInt()) {
			System.out.println("Only numbers 1-5 and 99 are allowed.\n");
		}
		else {
			action = input.nextInt();
			System.out.println(action); //TODO TEMP
			
			if (action != 1 && action != 2 && action != 3 && action != 4 && action != 5 && action != 99) { 
				System.out.println("Invalid value entered. You have to enter a value between 1 and 5, or, if you want to cancel, 99.");
			}
			else if (action == 99) {
				System.out.println("You want to cancel");
				System.out.println("Exiting SAUD2. Welcome back!");
				input.close();
				break;
			}
			else if (action == 1) {
				System.out.println("You want to show all users.");
				showAllUsers(); 
			}
			else if (action == 2) {
				System.out.println("You want to show selected user");
				UserRepository ur1 = new UserRepository();
				System.out.print("Enter id of user to show: ");
				if(!input.hasNextInt()) {
					System.out.println("User id must be a number.\n");
				}
				else {
					int userId = input.nextInt();
					showSelectedUser(userId); 
				}
			}
			else if (action == 3) {
				System.out.println("You want to add a user");
				UserRepository ur2 = new UserRepository();
				System.out.print("Enter id for new user: ");
				if(!input.hasNextInt()) {
					System.out.println("User id must be a number.\n");
				}
				else {
					int userId = input.nextInt();
					System.out.print("Enter name of new user: ");
					String name = input.next() + input.nextLine();
				//	System.out.println("Name is: " + name + "\n");
					System.out.print("Enter profession of new user. \nIf no profession, enter None: ");
					String profession = input.next() + input.nextLine();
				//	System.out.println("Profession is: " + profession + "\n");
					User user = new User(userId, name, profession);
					addUser(user); 
				}
			}
			else if (action == 4) {
				System.out.println("You want to update a user");
				System.out.print("Enter id for the user to update: ");
				if(!input.hasNextInt()) {
					System.out.println("User id must be a number.\n");
				}
				else {
					int userId = input.nextInt();
					showSelectedUser(userId);
					System.out.print("Please confirm that you want to change this user (Y/N):");
					String confirmation= input.next() + input.nextLine();
					if(confirmation.equals("Y")) {
						System.out.print("Enter name of the user: ");
						String name = input.next() + input.nextLine();
						System.out.print("Enter profession of user. \nIf no profession, enter None: ");
						String profession = input.next() + input.nextLine();
						User updatedUserToBe = new User(userId, name, profession);
						updateUser(updatedUserToBe);
					}
					else {
						System.out.println("Update cancelled.\n");
					}
				}
			}
			else if (action == 5) {
				System.out.println("You want to delete a user");
				UserRepository ur4 = new UserRepository();
				System.out.print("Enter id for the user to delete: ");
				if(!input.hasNextInt()) {
					System.out.println("User id must be a number.\n");
				}
				else {
					int userId = input.nextInt();
					//Nedan konstruktion ger SAX exception: Filen har avslutats för tidigt när user saknas
					User userToDelete = ur4.getSelectedUser(userId);
					
					System.out.println("You selected to delete user " + userToDelete.getId() + ".\nName: " + userToDelete.getName() + "\nProfession: " + userToDelete.getProfession());
					System.out.print("Please confirm deletion of user " + userToDelete.getId() + " (Y/N):");
					String confirmation= input.next() + input.nextLine();
					System.out.println("Confirmation: " + confirmation);
					if(confirmation.equals("Y")) {
						String response = ur4.delete(userId);
					//	System.out.println(response + "\n");
						if (response.equals("Delete user request returned: \n<result>success</result>")) {
							System.out.println("User with id " + userToDelete.getId() + ", " + userToDelete.getName() 
							+ ", " + userToDelete.getProfession() + ", was successfully deleted.\n");
						}
						else {
							System.out.println("User with id " + userToDelete.getId() + "could not be deleted. Please check your data.\n");
						}

					}
					else {
						System.out.println("Deletion of " + userToDelete.getId() + " cancelled.\n");
						
					}
				}
			}

			}
		}
	// closing scanner
	input.close();
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

	private void showAllUsers() {
		UserRepository ur = new UserRepository();
		ArrayList<User> userList = ur.getAllUsers();
		for(int i = 0; i<userList.size(); i++) {
			User user = new User();
			user = userList.get(i);
			System.out.println(user); //TODO snygga till - men funkar OBS inputvalidering
		}
	}
	
	private void showSelectedUser(int id) {
		UserRepository ur = new UserRepository();
		User user = ur.getSelectedUser(id);
		System.out.println("Showing selected user with id " + user.getId() + "\nName: " + user.getName() 
		+ " Profession: " + user.getProfession() + "\n");
	}
	
	private void addUser(User user) {
		UserRepository ur = new UserRepository();
		String response = ur.add(user);
	//	System.out.println(response + "\n");
		if (response.equals("Add user request returned: \n<result>success</result>")) {
			System.out.println("User with id " + user.getId() + ", " + user.getName() + ", " + user.getProfession() + ", was successfully added.\n");
		}
		else {
			System.out.println("The user with userId " + user.getId() + " could not be added. Please check your data.");
		}
	}
	
	private void updateUser(User updatedUserToBe) {
		UserRepository ur = new UserRepository();
		String response = ur.update(updatedUserToBe);
		//	System.out.println(response + "\n");
			User updatedUser = ur.getSelectedUser(updatedUserToBe.getId());
			System.out.println("Updated user details: \nId: " + updatedUser.getId() + "\nName: " + updatedUser.getName() + "\nProfession: " + updatedUser.getProfession() + "\n");
	}
}
