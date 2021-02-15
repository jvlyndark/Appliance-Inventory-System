// -----------------------------------------------------
// Assignment 1
// Question: Part II
// Written by: Ursula Newfield
// -----------------------------------------------------

import java.util.Scanner;

/** Appliance Driver Class
 * 
 * @author Ursula Newfield
 *
 */
public class Driver {

	/** Main Method
	 * 
	 * @param args represents main Driver implementation
	 */
	public static void main(String[] args) {
		
		// Variable Declarations
		int maxAppliances, mainMenuEntry;
		final String password = "c249";
		String passwordEntry = "";
		int numberOfAppliances = Appliance.findNumberOfCreatedAppliances();
		
		// Create Scanner object
		Scanner keyboard = new Scanner(System.in);
		
		// Welcome Message
		System.out.println("Welcome, Major Appliance Dealer to Ursula's Appliance Program!");
		
		// Prompt user for max number of appliances
		System.out.println("\nWhat is the maximum number of appliances that your store can contain?");
		
		// Creation of array to hold all appliance information
		maxAppliances = keyboard.nextInt();
		Appliance inventory[] = new Appliance[maxAppliances];
		
		// Incorrect password loop - System locks down after 4 failed attempts at entry to Enter New Appliance Section
		int suspiciousAttemptCount = 0;
		
		// Menu Variables
		int userSelectionInvalidSerialMenu = 0;
		int userSelectionChangeAttributeMenu;
		while(suspiciousAttemptCount < 4) {
			
			// Return to Main Menu Loop
			while(suspiciousAttemptCount < 4) {
				// Display of Main Menu and verification loop
				do {
					System.out.println("\nWhat do you want to do?"
							+ "\n\t1. Enter new appliances (password required)"
							+ "\n\t2. Change information of an appliance (password required)"
							+ "\n\t3. Display all appliances by a specific brand"
							+ "\n\t4. Display all appliances under a certain price"
							+ "\n\t5. Quit"
							+ "\nPlease enter your choice>");
					mainMenuEntry = keyboard.nextInt();
				} while((mainMenuEntry > 5) || (mainMenuEntry < 1));
				
				///////
				// Option 1 entered from Main Menu
				// Create new appliances
				///////
				
				if(mainMenuEntry == 1) {
					
					// Prompt user for password
					System.out.println("Please enter password: ");
					passwordEntry = keyboard.next();
					
					// Password verification
					int passwordAttemptCount = 1;
					
					// Password verification loop
					while((!passwordEntry.contentEquals(password)) && (passwordAttemptCount < 3)) {
						passwordAttemptCount++;
						System.out.println("Incorrect Password. Please try again. You have "
								+ (4 - passwordAttemptCount) + " more attempts.");
						
						// Re-prompt user for password
						System.out.println("Please enter password: ");
						passwordEntry = keyboard.next();
						
						// if password is incorrectly entered 3 times
						if(passwordAttemptCount > 2) {
							suspiciousAttemptCount++;
							
							// if 4 sets of 3 incorrect password attempts have occurred, exit program
							if(suspiciousAttemptCount > 3 ) {
								System.out.println("Sorry, that was your last attempt.");
								break;
							}
							System.out.println("You have entered your password incorrectly 3 times."
									+ "\nReturning you to the main menu...");
						}
					}
					// if password is correctly entered
					if(passwordEntry.contentEquals(password)) {
						
						// Reset incorrect password attempts
						passwordAttemptCount = 1;
						suspiciousAttemptCount = 0;
						
						// New Appliance Variables
						String newApplianceType, newApplianceBrand;
						double newAppliancePrice;
						
						// User prompt for desired number of appliances to enter
						System.out.println("How many appliances do you want to enter? ");
						int newAppliances = keyboard.nextInt();
						
						// Verify enough room in inventory to add desired appliances
						while((newAppliances + Appliance.findNumberOfCreatedAppliances()) > inventory.length) {
							System.out.println("You only have space for "
									+ (inventory.length - Appliance.findNumberOfCreatedAppliances()) + " more appliances"
											+ " in your inventory.");
							System.out.println("How many appliances do you want to enter? ");
							newAppliances = keyboard.nextInt();
						}
						
						// Reset scanner
						newApplianceType = keyboard.nextLine();
						
						// Prompt user to enter new appliance info
						for(int applianceInfoLoop = 0; applianceInfoLoop < newAppliances; applianceInfoLoop++) {
							System.out.println("Please enter new appliance type: ");
							newApplianceType = keyboard.nextLine();
							
							// Type verification
							while (Appliance.typeVerify(newApplianceType) == false) {
								// please enter appliance type
								System.out.println("That is not a valid appliance type."
										+ "\nPlease enter new appliance type: ");
								newApplianceType = keyboard.nextLine();
							}
							System.out.println("Please enter new appliance brand: ");
							newApplianceBrand = keyboard.next();
							System.out.println("Please enter appliance price: ");
							newAppliancePrice = keyboard.nextInt();
							
							// Price verification
							while (Appliance.priceVerify(newAppliancePrice) == false) {
								// please enter appliance type
								System.out.println("That is not a valid appliance price."
										+ "\nPlease enter new appliance price: ");
								newAppliancePrice = keyboard.nextDouble();
							}
							
							// Execute addition of new Appliance
							inventory[numberOfAppliances] = new Appliance(newApplianceType, newApplianceBrand, newAppliancePrice);
							numberOfAppliances = Appliance.findNumberOfCreatedAppliances();
							System.out.println("New appliance added.");
							newApplianceType = keyboard.nextLine();
						}
						System.out.println("Returning to Main Menu...");
					}
				}
				
				///////
				// Option 2 entered from Main Menu
				// Make changes to appliances
				///////
				
				if(mainMenuEntry == 2) {
					
					// Prompt user for password
					System.out.println("Please enter password: ");
					passwordEntry = keyboard.next();
					
					// Password verification
					int passwordAttemptCount = 1;
					
					// Loop if password is not entered correctly
					while((!passwordEntry.contentEquals(password)) && (passwordAttemptCount < 3)) {
						suspiciousAttemptCount = 0;
						passwordAttemptCount++;
						System.out.println("Incorrect Password. Please try again. You have "
								+ (4 - passwordAttemptCount) + " more attempts.");
						System.out.println("Please enter password: ");
						passwordEntry = keyboard.next();
						
						// if password is entered incorrectly too many times
						if(passwordAttemptCount > 2) {
							System.out.println("You have entered your password incorrectly 3 times."
									+ "\nReturning you to the main menu...");
						}
					}
					// if password is correctly entered
					if(passwordEntry.contentEquals(password)) {
						
						// Reset incorrect password attempts
						passwordAttemptCount = 1;
						suspiciousAttemptCount = 0;
						
						// Loop until correct serial number is entered
						while(userSelectionInvalidSerialMenu == 0) {
							
							// Prompt user for desired serial number to update
							System.out.println("What is the serial number of the appliance you wish to update?");
							long serialNumberUpdate = keyboard.nextLong();
							
							// Verify if desired serial number exists to update
							for(int i = 0; i < Appliance.findNumberOfCreatedAppliances(); i++) {
								
								// if serial number does not exist in inventory, open menu
								if(serialNumberUpdate != inventory[i].getSerialNumber()) {
									System.out.println("That is not a valid serial number."
											+ "\nDo you wish to re-enter the serial number, or return to the main menu?"
											+ "\nPress 1 for re-enter serial number"
											+ "\nPress 2 for Main Menu");
									userSelectionInvalidSerialMenu = keyboard.nextInt();
									
									// Option 1 selected: re-enter serial number
									if(userSelectionInvalidSerialMenu == 1) {
										userSelectionInvalidSerialMenu = 0;
										break;
									}
									// Option 2 selected: quite to main menu
									if(userSelectionInvalidSerialMenu == 2) {
										break;
									}
								}
								
								// if serial number exists in inventory
								if(serialNumberUpdate == inventory[i].getSerialNumber()) {
									userSelectionChangeAttributeMenu = 0;
									while(userSelectionChangeAttributeMenu != 4) {
										// Display appliance info
										System.out.println(inventory[i].toString());
										
										// loop to make changes until user quits
										do {
											
										// Prompt user for which attribute to change
										System.out.println("What information would you like to change?"
												+ "\n\t1. Brand"
												+ "\n\t2. Type"
												+ "\n\t3. Price"
												+ "\n\t4. Quit");
										userSelectionChangeAttributeMenu = keyboard.nextInt();
											
											// Information Change switch
											switch(userSelectionChangeAttributeMenu) {
											
												// Option 1: Change Brand selected
												case 1: {
													//Prompt user for changes and complete them
													System.out.println("What is the new brand?");
													String newBrand = keyboard.next();
													inventory[i].setBrand(newBrand);
													break;
												}
												
												// Option 2: Change Type selected
												case 2: {
													// Prompt user for changes
													System.out.println("What is the new type?");
													String newType = keyboard.next();
													
													// type verification
													while (Appliance.typeVerify(newType) == false) {
														// please enter appliance type
														System.out.println("That is not a valid appliance type."
																+ "\nPlease enter new appliance type: ");
														newType = keyboard.next();
													}
													// execute changes
													inventory[i].setType(newType);
													break;
												}
												
												// Option 3: Change Price selected
												case 3: {
													// Prompt user for changes and complete them
													System.out.println("What is the new brand?");
													double newPrice = keyboard.nextDouble();
													
													// Price verification
													while (Appliance.priceVerify(newPrice) == false) {
														// please enter appliance type
														System.out.println("That is not a valid appliance price."
																+ "\nPlease enter new appliance price: ");
														newPrice = keyboard.nextDouble();
													}
													// execute changes
													inventory[i].setPrice(newPrice);
													break;
												}
												
												// Option 4: Quit
												case 4: {
													userSelectionInvalidSerialMenu = 2;
													break;
												}
												
												// Default choice: loop until valid choice is selected
												default: {
													System.out.println("That is not a valid choice."
															+ "\nPlease enter a valid choice.");
												}
											}
										}while(userSelectionInvalidSerialMenu == 0);
										
										// Inform user changes complete + Display new info
										System.out.println("Changes complete.");
									}
								}
							}
						}
						// Reset invalid serial menu variable
						userSelectionInvalidSerialMenu = 0;
					}
				}
				
				///////
				// Option 3 entered from Main Menu
				// Search for appliances with matching brand
				///////
				
				if(mainMenuEntry == 3) {
					int applianceMatchedByBrandFound = 0;
					
					// Prompt user for brand to display
					System.out.println("Please enter the brand you wish to display: ");
					String brandToDisplay = keyboard.next();
					
					// Find all appliances that match selected brand
					for(int i = 0; i < Appliance.findNumberOfCreatedAppliances(); i++) {
						if(Appliance.findAppliancesByBrand(inventory[i], brandToDisplay) == true) {
							System.out.println(inventory[i].toString());
							applianceMatchedByBrandFound++;
						}
					}
					
					// if no appliances match selected brand
					if(applianceMatchedByBrandFound == 0) {
						System.out.println("Sorry, no appliances matched that brand."
								+ "\nReturning you to the main menu now...");
					}
				}
				
				///////
				// Option 4 entered from Main Menu
				// Search for all appliances with lower price
				///////
				
				if(mainMenuEntry == 4) {
					int applianceCheaperThanFound = 0;
					
					// Prompt user for price to be compared against
					System.out.println("Please enter the price to compare to: ");
					double comparedPrice = keyboard.nextDouble();
					
					// Loop to search for all appliances that have a lower price
					for (int i = 0; i < Appliance.findNumberOfCreatedAppliances(); i++) {
						if(Appliance.findCheaperThan(inventory[i], comparedPrice) == true) {
							System.out.println(inventory[i].toString());
							applianceCheaperThanFound++;
						}
					}
					
					// if no appliances are found with a lower price
					if(applianceCheaperThanFound == 0) {
						System.out.println("Sorry, no appliances were found to be cheaper than that price."
								+ "\nReturning you to the main menu now...");
					}
				}
				
				///////
				// Option 5 entered from Main Menu
				// Quit program
				///////
				
				if(mainMenuEntry == 5) {
					
					// Closing message to user
					System.out.println("Thank you for using Ursula's Appliance Program."
							+ "\n\nGoodbye!!");
					keyboard.close();
					System.exit(0);
				}
			}
			// Inform user of suspicious activities and lock system
			if(suspiciousAttemptCount > 3) {
				System.out.println("Program detected suspicious activities and will terminate immediately!"
						+ "\nGoodbye!");
				keyboard.close();
				System.exit(0);
			}
		}
	}
}
