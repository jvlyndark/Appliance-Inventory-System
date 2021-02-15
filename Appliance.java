// -----------------------------------------------------
// Assignment 1
// Question: Part I
// Written by: Ursula Newfield
// -----------------------------------------------------

/** Represents an appliance
 * 
 * @author Ursula Newfield
 *
 */

public class Appliance {
	
	/** Variables to describe Appliance objects
	 * 
	 */
	private String type;
	private String brand;
	private long serialNumber;
	private static double price;
	public static int numberOfAppliances = 0;
	
	/** Array to store valid types of Appliance objects
	 * 
	 */
	private static String[] validTypes = new String[] {"Fridge", "Air Conditioner", "Washer", "Dryer", "Freezer", "Stove", "Dishwasher", "Water Heater", "Microwave"};
	
	/** Default Constructor
	 * 
	 */
	public Appliance() {
		type = "Fridge";
		brand = "LG";
		serialNumber = serialNumGenerator();
		price = 1;
		
		numberOfAppliances++;
	}
	
	/** Copy Constructor
	 * @param applianceToCopy the Appliance that will be copied
	 */
	public Appliance(Appliance applianceToCopy) {
		type = applianceToCopy.type;
		brand = applianceToCopy.brand;
		serialNumber = serialNumGenerator();
		price = applianceToCopy.getPrice();
		numberOfAppliances++;
	}
	
	/** Parameterized Constructor
	 * @param type1 the type of Appliance to be created
	 * @param brand1 the brand of Appliance to be created
	 * @param price1 the price of Appliance to be created
	 */
	public Appliance(String type1, String brand1, double price1) {
		type = type1;
		brand = brand1;
		serialNumber = serialNumGenerator();
		price = price1;
		numberOfAppliances++;
	}
	
	// Accessors
	
	/** Type Accessor
	 * @return Appliance type
	 */
	public String getType() {
		return type;
	}
	
	/** Brand Accessor
	 * @return Appliance brand
	 */
	public String getBrand() {
		return brand;
	}

	/** Serial Number Accessor
	 * @return Appliance serial number
	 */
	public long getSerialNumber() {
		return serialNumber;
	}
	
	/** Price Accessor
	 * @return Appliance price
	 */
	public double getPrice() {
		return price;
	}
	
	// Mutators
	
	/** Type Mutator
	 * @param newType represents new type of Appliance
	 */
	public void setType(String newType) {
		type = newType;
	}
	
	/** Brand Mutator
	 * @param newBrand represents new brand of Appliance
	 */
	public void setBrand(String newBrand) {
		brand = newBrand;
	}
	
	/** Price Mutator
	 * @param newPrice represents new price of Appliance
	 */
	public void setPrice(double newPrice) {
		price = newPrice;
	}
	
	/** toString Method
	 * @return Returns Appliance info in readable format
	 */
	public String toString() {
		return ("Appliance Serial # " + serialNumber 
				+ "\nBrand: " + brand.toUpperCase()
				+ "\nType: " + type.toUpperCase()
				+ "\nPrice: $" + price + "\n");
	}
	
	/** Type Verification Method to ensure no invalid Appliance types are entered
	 * @param inputType represents a user input type String that needs to be verified as valid
	 * @return true if input type is valid, false otherwise
	 */
	public static boolean typeVerify(String inputType) {
		for(int i = 0; i < 9; i++) {
			if (inputType.equalsIgnoreCase(validTypes[i])) {
				return true;
			}
		}
		return false;
	}
	
	/** Price Verify Method to ensure price is greater than 0
	 * @param inputPrice represents the user input price to be verified
	 * @return true if price is greater than 0, false otherwise
	 */
	public static boolean priceVerify(double inputPrice) {
		if(inputPrice >= 1) {
			return true;
		} else {
			return false;
		}
	}
	
	/** Method to increment serial numbers of Appliance objects
	 * @return a serial number that is associated to Appliance object, while also incrementing for next serial number
	 */
	private long serialNumGenerator() {
		serialNumber = 1000000 + findNumberOfCreatedAppliances();
		return serialNumber++;	
	}
	
	/** Equals Method to verify is two Appliance objects contain same information
	 * @param otherAppliance represents the other Appliance object to be compared
	 * @return true if compared Appliance object is not null
	 * AND if both Appliance objects contain the same information, 
	 * otherwise return false
	 */
	public boolean equals(Appliance otherAppliance) {
		if(otherAppliance == null) {
			return false;
		} else if((type.equals(otherAppliance.type)) 
				&& (brand.equals(otherAppliance.brand)) 
				&& (price == otherAppliance.getPrice())) {
			return true;
		} else {
			return false;
		}
	}
	
	/** Find Number of Created Appliances Method
	 * to inform user how many Appliance objects have been created
	 * @return numberOfAppliances represents the number of Appliance objects already created
	 */
	public static int findNumberOfCreatedAppliances() {
		return numberOfAppliances;
	}
	
	/** Find Appliances by Brand Method
	 * to inform user which Appliance objects in system share the same brand
	 * @param otherAppliance represents Appliance to be compared
	 * @param brandCompare represents brand to compare against
	 * @return true if specified Appliance brand matches compared Appliance brand, false otherwise
	 */
	public static boolean findAppliancesByBrand(Appliance otherAppliance, String brandCompare) {
		if(otherAppliance.getBrand().equalsIgnoreCase(brandCompare)) {
			return true;
		} else {
			return false;
		}
	}
	
	/** Find Cheaper Than Method
	 * checks if specific Appliance object has a lower price than compared Appliance
	 * @param otherAppliance represents Appliance whose price will be compared
	 * @param priceCompare represents price that is compared against
	 * @return true if specified Appliance object has lower price than specified amount,
	 * false otherwise
	 */
	public static boolean findCheaperThan(Appliance otherAppliance, double priceCompare) {
		if(otherAppliance.getPrice() < priceCompare) {
			return true;
		} else {
			return false;
		}
	}

}
