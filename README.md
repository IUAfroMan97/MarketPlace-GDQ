Justin Drinkall
Jacob Good 
Nick Quigley 
Group 4
Read-Me File 

// For Grading Purposes

The grader will be given a .jar file to run. The grader will need to run this .jar file 
and create a new user for each type of user explained down below. 

This .jar file will contain instances of users/items and transaction histories that are 
already in marketplace. 

** Example ** 

In order to search through transaction histories of the marketplace:
	Run .jar file
	Click Create New User button, fill out information and select "Administrator" as 
	account type
	Click Register! then ok
	Click log in as administrator
	Type in Correct username/password and press enter
	Click Transaction Viewer Tab
	See all the marvelous transactions
	

// Main screen 

Four buttons:
	3 different user types (Seller, Customer, Administrator)
	Or Create New user 
	By pressing any of these buttons you will be directed to a new screen
	
	At any time you want to leave the GDQ marketplace, simply press the X in the top right
	corner of the panel

/////////////////////////////////////////////////////////////////////////////////////////

// Seller View 

Seller Login screen contains:
	Text boxes to input username/password
	Enter button to login
	Return button which will take you back to main screen
	
Typing a username that is not in the system will prompt a dialogue box saying that user 
does not exist 

Typing a correct username but a wrong password will result in a wrong password dialogue 
box

Upon entering a correct username/password
	Directed to an account overview where you will find 
		3 buttons to change username/email/password
		5 Labels displaying user information
		Overview and Post New Item Tab
		
	Selecting the post new item tab next to the overview tab will allow you to see 
		Text fields for creating a new item which include 
			Item name
			Price 
			Quantity 
			Category
			Item description
			
		** Selecting Create listing will result in a dialogue box prompting user to input
		fields ** 
		
		*** All postings are final, they can only be changed if the vendor contacts an 
		administrator ***
		
/////////////////////////////////////////////////////////////////////////////////////////
				
// Customer View 

Customer Login Screen Contains:
	Text boxes to input username/password
	Enter button to login
	Return button which will take you back to main screen
	
Typing a username that is not in the system will prompt a dialogue box saying that user 
does not exist 

Typing a correct username but a wrong password will result in a wrong password dialogue 
box

Upon entering a correct username/password
	Directed to an account overview where you will find 
		3 buttons to change username/email/password
		5 Labels displaying user information
		Overview and Inventory tab
		
Selecting the inventory tab next to the overview tab will allow you to see 
		Items available that are in the marketplace for purchase
			Item name
			Price 
			Quantity 
			
		** Administrators have the ability to change an items characteristics at any 
		time **
			
		Search Bar for easier navigation (Can be sorted by category, name, id number)
		Purchase button allows the user to select Quantity desired
		
		Following a purchase a dialogue box will appear notifying the user of the shipping
		status of their item - GDQ is famous for their 5 second shipping times !
				
		** After making a purchase it is imperative that the user presses the refresh 
		button to see the updated marketplace **
		

/////////////////////////////////////////////////////////////////////////////////////////
		
// Administrator View 

Administrator Login Screen Contains:
	Text boxes to input username/password
	Enter button to login
	Return button which will take you back to main screen
			
Typing a username that is not in the system will prompt a dialogue box saying that user 
does not exist 

Typing a correct username but a wrong password will result in a wrong password dialogue 
box		

Upon entering a correct username/password
	Directed to an account overview where you will find 
		3 buttons to change username/email/password
		4 Labels displaying user information 
		Overview, Inventory, User View, Transaction Viewer Tab	
	
Selecting the inventory tab next to the overview tab will allow you to see 
		Items available that are in the marketplace for purchase
			Item name
			Price 
			Quantity 
			
		** Administrators have the ability to change an items characteristics at any 
		time **
			
		Search Bar for easier navigation (Can be sorted by category, name, id number)
		Purchase button allows the user to select Quantity desired
		
		
		** After making a change to the item in the marketplace it is imperative to 
		refresh the page to see the change **	
		
Selecting the user view tab will display
		All the users in the marketplace (Customers, Administrators, Vendors)
		Buttons for changing user information and/or deleting a user
		
		** After making a change to the users in the marketplace it is imperative to 
		refresh the page to see the change **
		
Selecting the transaction view tab will display
	All transactions that have occurred in the marketplace
		Each transaction contains :
			Item ID
			Seller ID
			Buyer ID
			Transaction ID
			Item Quantity
			Shipping Status 
		
/////////////////////////////////////////////////////////////////////////////////////////

// Create User View 

Create New User Login button contains:
	Fields for registration:
		Username
		Password
		Email
		Type (Seller, Buyer, Administrator)
		Starting Balance 
		
	Upon successful registration a dialogue box will appear telling the user their account
	was successfully created
	
	This new user will be able to login to the appropriate account type chosen in the 
	registration
