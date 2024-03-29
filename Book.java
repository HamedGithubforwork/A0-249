//-----------------------------------------------------
// Assignment (1)
// Question: (Part 1)
// Written by: Hamed Vasheghani Farahani 40246686
// -----------------------------------------------------
// this class is used to create book objects. it contains the isbn, the title, the author and price.
// it also contains a counter that records the number of books created with this class.
// it also has the getters and setters for all the attributes of the class.
// it can be constructed only with the isbn, title, author and price all present.
// it has a method that returns the number of books created with this class.
// it also has a method that checks the price and isbn between two books, if theyre both  the same it returns true and false otherwise
// it also has a method that returns the information of the book in String from
public class Book {
    //the following are attributes of the class
    String title; // the title of the book
    String author; // the author of the book
    long isbn; // the isbn of the book
    static int numberOfCreatedBooks = 0; // the number of books that are created
    // ^^^^^^^ shared with all the objects and used to track the number of books made from this class
    double price; // the price of the book

    //the following are the getters of the class
    public String getTitle() { // returns the title of the book
        return title;
    }

    public String getAuthor() { // returns the author of the book
        return author;
    }

    public long getIsbn() { // returns the isbn of the book
        return isbn;
    }

    public Double getPrice() { // returns the price of the book
        return price;
    }

    public int getNumberOfCreatedBooks() { // gets the number of created books
        return numberOfCreatedBooks;
    }

    // the following are setters of the class
    public void setTitle(String title) { // sets the title of the book
        this.title = title;
    }

    public void setAuthor(String Author) { //   sets the author of the book
        this.author = Author;
    }

    public void setIsbn(long isbn) { // sets the isbn of the book
        this.isbn = isbn;
    }

    public void setPrice(double price) { // sets the price of the book
        this.price = price;
    }

    public void setNumberOfCreatedBooks(int numberOfCreatedBooksNow) { // sets the number of created books
        numberOfCreatedBooks = numberOfCreatedBooksNow;
    }

    // the following is the constructor of the class
    public Book(String title, String author, long isbn, double price) { // initializes all the attributes of the class
        this.title = title;
        this.author = author;
        this.price = price;
        this.isbn = isbn;
        numberOfCreatedBooks++; // the counter increases by one every time a book is created
    }

    public int findNumberOfCreatedBooks() {
        return numberOfCreatedBooks; // returns the number of books that are created
    }

    public boolean equals(Book a) { // checks if the two books are equal or not and returns the truth value as a result
        return this.price == a.getPrice() && this.isbn == a.getIsbn();
    }

    public String toString() { // returns the information of the book in text form
        return "the title of the book is " + title + " the author of the book is " + author + " the price of the book is " + price + " the isbn of the book is " + isbn;
    }



}

