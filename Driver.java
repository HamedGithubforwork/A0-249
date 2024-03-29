import java.util.Scanner;
// Assignment (1)
// Question: (Part 2)
// Written by: Hamed Vasheghani Farahani 40246686
// -----------------------------------------------------
public class Driver {
    public static double maxPrice; // the maximum price the user would like to spend on a book
    public static String authorName=""; // the name of the author the user wants
    public static boolean loopProgram=true; // a flag to see if the program should loop or not
    public static int realIndexOfBook;
    public static int numberOfBooksToAdd; // records the number of books to add to the inventory
    static boolean enoughSpace; // a flag to see if there is enough space or not to add into the inventory
    static Scanner scan = new Scanner(System.in); // a scanner object created to record the user's input
    static int response; // holds the user's response
    static String password = "249"; // the password for the system
    static int passwordFailsInArow = 0; // records the number of failed attempts in a row for entering the correct password
    static int totalPasswordFails = 0; // records the total number of failed attempts to enter the correct password
    static Book newBook; // a book object to record the new book to be added into the inventory
    static boolean systemAccess = false; // a flag to see if the user has the correct password or not inputed
    static Book[] inventory = new Book[10]; // an array of books to store the books the user adds, the maximum it can hold is 10
    public static void displayChoices() { // a method to display the menu to the user
        System.out.println("What do you want to do? ");
        System.out.println("1. Enter new books (password required) ");
        System.out.println("2. Change information of a book (password required) ");
        System.out.println("3. Display all books by a specific author ");
        System.out.println("4. Display all books under a certain a price. ");
        System.out.println("5. Quit ");
        System.out.println("Please enter your choice > ");
    }
    public static void recordValidResponse() { // a method that records the user's response and prompts the user to enter a valid response if the response is not valid

        response = scan.nextInt();
        if (response <= 0 || response > 5) {
            System.out.println("please enter a valid response");
            recordValidResponse();
        }
    }
    public static void startChoice() { // a method that will select the choice the user has made and call that method
        if (response == 1) {
            enterNewBooks();
        }
        if (response ==2){
            changeBookInfo();
        }
        if (response==3){
            displayAllBooksBySpecificAuthor();
        }
        if (response==4){
            displayAllBooksUnderSelectedPrice();
        }
        if (response==5){
            System.out.println("Thank you for using the program! Goodbyed!");
            System.exit(0);
        }
    }

    // a method that checks if there is enough space or not in the program to add the books, flagging  it true if so and false otherwise
    // if the number is less than or equal to 0 it will prompt the user to enter a number greater than 0
    public static void checkIfEnoughSpace() {
        numberOfBooksToAdd = scan.nextInt();
        while (numberOfBooksToAdd <= 0) {
            System.out.println("please enter a number greater than 0!");
            numberOfBooksToAdd = scan.nextInt(); // records the number of books to be added
        }
        if (numberOfBooksToAdd > inventory.length - Book.numberOfCreatedBooks) {
            System.out.println("There is not enough space in our inventory to add that many books!");
            System.out.println("Returning to menu screen!");
            loopProgram=true; // loops the whole program

        } else {
            enoughSpace = true; // sets the enough space flag to be true allowing for the books to be added
        }
    }

    public static void createNewBook() { /// a  method that will create a new book object and asks the user to input the book object's values
        long isbn; // declares the isbn of the book
        String title; // declares the title of the book
        String author; // declares the author of the book
        double price; // declares  the price of the book
        System.out.println("Please enter the title of the book"); /// ask for the title of the book
        title = scan.next(); // records title in string format
        System.out.println("Please enter the author's name"); // ask for the author of the book
        author = scan.next(); // records author in string format
        System.out.println("Please enter the price of the book"); // ask for the price of the book
        price = scan.nextDouble(); // records price in double format
        System.out.println("Please enter the isbn of the book"); // ask for the isbn of the book
        isbn = scan.nextLong(); // records isbn in long format
        newBook = new Book(title, author, isbn, price); //creates a new book object with the recorded values
    }
    // a method that will add the book to the inventory. its placement is based on when the book object was created
    public static void addBookToInventory() {  // example first book will be in index 0, third book will be in index 2 5th book in index 4  etc
        inventory[Book.numberOfCreatedBooks - 1] = newBook; // the book is added to the inventory to the index of the number of books created - 1
    }

    public static void updatePasswordFails() { // a method that updates the password fails in a row and the total password fails
        passwordFailsInArow++; // increments passwordfails in a row by one
        totalPasswordFails++; //increments total password fails by one
    }

    public static void passWordCheck() { // a method that checks if the password is correct or not
        System.out.print("please enter the correct password"); // prompts the user to enter the correct password
        if (password.equals(scan.next())) { // checks if the correct password has been inputted
            systemAccess = true; // sets the system acess to be true. used to terminate loops when method is called
        } else {
            updatePasswordFails(); // if the password is incorrect the password fails counter increases for more information check the method
        }
    }
    public static void resetPasswordFailsInARow() { // a method that resets password fails in a row if the user enters the correct password and returns making a mistake
        passwordFailsInArow = 0;
    }
    public static void checkForSuspiciousActivity() { // if the user enters the wrong password a total of 12 times the system will terminate
        if (totalPasswordFails >= 12) { // if the total password is greater than or equal to 12 the system will terminate
            System.out.println("Suspicious Activity Detected!"); //tells the user that suspicious activity has been detected
            System.exit(0); // terminates the system
        }
    }






    // this method is one of the menu options in the starting screen. It will allow the user to  enter new books into the inventory
    // it will first ask for a password and if the user fails 3 times in a row they will return to the menu screen. If they fail 12 times in a row
    // the system will terminate. once they enter the correct password they will be prompted to enter the number of books they want to add
    // if that number is greater than the space avaiable in the array it will tell the user and return to the menu screen.
    // if there is enough space for the books, it will promp the user to enter the books' information and then add them to the inventory
    // it will also return the book's information to the user in a string format to make sure the user has entered the correct information
    // once all the books information has been inputed and added to the inventory it will return to the main menu and prompt the user to enter a choice
    public static void enterNewBooks() {
        systemAccess = false;
        resetPasswordFailsInARow();
        while (!systemAccess && passwordFailsInArow < 3) {
            passWordCheck();
            checkForSuspiciousActivity();
        }
        if (systemAccess) {
            System.out.println("System Access granted!");
            System.out.println("how many books do you want to enter?");
            checkIfEnoughSpace(); //checks if there is enough space to add the books, if not it returns to menu screen
            if (enoughSpace) {
                for (int i = 0; i < numberOfBooksToAdd; i++) {
                    createNewBook();
                    addBookToInventory();
                    System.out.println(inventory[Book.numberOfCreatedBooks - 1].toString());
                }
                System.out.println("all the books have been added to the inventory!");
                System.out.println("Returning to main menu!");
                loopProgram=true;
            }
        } else {
            System.out.println("Too many failed attempts in a row! returning to menu screen!");
            loopProgram=true;
        }
    }
    // program that checks if the book number is valid  or not and states if its not valid it asks the user to input another book number or quit back to main menu
    public static void checkIfValidBookNumber(int index){
        if(inventory[index]==null){
            System.out.println("there is no book at this specific index, input another book number or enter 0 to quit");
            index=scan.nextInt();
            if(index==0){
            loopProgram=true;
            }
            else {
                checkIfValidBookNumber(scan.nextInt());
            }
        }
        realIndexOfBook=index;// if a valid index is inputted, it will become the real index of the book that will be displayed
    }
    public static void displayBookInfo(){
        System.out.println("Book # "+realIndexOfBook);
        System.out.println("Author: "+inventory[realIndexOfBook].getAuthor());
        System.out.println("Title: "+inventory[realIndexOfBook].getTitle());
        System.out.println("ISBN: "+inventory[realIndexOfBook].getIsbn());
        System.out.println("Price: $"+inventory[realIndexOfBook].getPrice());
    }
    public static void menuScreenChangInfo(){// method to show what the user can change
        System.out.println("what information would you like to change?");
        System.out.println("1.author");
        System.out.println("2. title");
        System.out.println("3. ISBN");
        System.out.println("4. Price");
        System.out.println("5. Quit");
        System.out.println("Enter your choice>");
    }
    public static void changeBookAction(int response){ // records the users response and prompts the user to enter a valid response if its invalid
        if (response==1){
            System.out.println("please enter the new author's name");
            inventory[realIndexOfBook].setAuthor(scan.next());
            displayBookInfo();
            menuScreenChangInfo();
            recordValidResponse();
            changeBookAction(response);// if the program is not terminated initially it will loop back the method and display book info and also check a valid response
        }
        else if (response==2){
            System.out.println("Please enter the new title's name");
            inventory[realIndexOfBook].setTitle(scan.next());
            displayBookInfo();
            menuScreenChangInfo();
            recordValidResponse();
            changeBookAction(response);// if the program is not terminated initially it will loop back the method and display book info
        }
       else if (response==3){
            System.out.println("Please enter the new ISBN");
            inventory[realIndexOfBook].setIsbn(scan.nextLong());
            displayBookInfo();
            menuScreenChangInfo();
            recordValidResponse();
            changeBookAction(response);// if the program is not terminated initially it will loop back the method and display book info
        }
       else if (response==4){
            System.out.println("Please enter the new price");
            inventory[realIndexOfBook].setPrice(scan.nextDouble());
            displayBookInfo();
            menuScreenChangInfo();
            recordValidResponse();
            changeBookAction(response);// if the program is not terminated initially it will loop back the method and display book info
        }
        else if (response==5){
            System.out.println("Return to the main menu!");
            loopProgram=true;
        }

    }
    // this method allows the user to change the info of the book. It will first ask for a password and if the user fails 3 times in a row they will return to the menu screen
    // if they fail 12 times in a row the system will terminate. once they enter the correct password they will be prompted to enter the book number of the book
    // they want to change which will be the index of the book in the inventory. if the book at that index is empty, it will prompt the user to renter another book or quit
    // back into the main menu
public static void changeBookInfo(){
    systemAccess = false;
    resetPasswordFailsInARow();
    while (!systemAccess && passwordFailsInArow < 3) {
        passWordCheck();
        checkForSuspiciousActivity();
    }
    if (systemAccess) {
        System.out.println("which book number do you wish to update?");
checkIfValidBookNumber(scan.nextInt());
displayBookInfo();
menuScreenChangInfo();
recordValidResponse();
changeBookAction(response); // uses the valid response to change book select the users input

    }
}
public static void displayAllBooksBySpecificAuthor(){
        int numberOfBooksByAuthor=0;
        authorName=scan.next();
        for (int i=0;i<inventory.length;i++){
            if(inventory[i]!=null&&authorName.equalsIgnoreCase(inventory[i].getAuthor())){
                realIndexOfBook=i;
                displayBookInfo();
            }
        }
}
public static void displayAllBooksUnderSelectedPrice(){
System.out.println(" Please enter the maximum price you are willing to spend on a book");
maxPrice=scan.nextDouble();
for(int i=0;i<inventory.length;i++){
    if(inventory[i]!=null&&inventory[i].getPrice()<maxPrice){
        realIndexOfBook=i;
        displayBookInfo();
    }
    }
}
    public static void main(String[] args) {
        while(loopProgram) {
            displayChoices();
            recordValidResponse();
            startChoice();
        }

    }
}