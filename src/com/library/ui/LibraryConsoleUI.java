package ui;

import java.util.Scanner;

public class LibraryConsoleUI{
    private final Scanner scanner = new Scanner(System.in);

    public void start(){
        int choice;

        do{
            showMainMenu();
            choice = readInt("enter your choice :");

            switch(choice){
                case 1 -> addBook();
                case 2 -> viewAllBooks();
                case 3 -> searchBooks();
                case 4 -> issueBook();
                case 5 -> returnBook();
                case 0 -> System.out.println("Existing Library Management System");
                default -> System.out.println("Invalid choice.please try again");
            }
        }while(choice!=0);
    }

    private void showMainMenu(){
        System.out.println("==========================================");
        System.out.println("        LIBRARY MANAGEMENT SYSTEM");
        System.out.println("==========================================");
        System.out.println("1. Add Books");
        System.out.println("2. view All Books");
        System.out.println("3. search Book");
        System.out.println("4. Issue Book");
        System.out.println("5. Return Book");
        System.out.println("0. Exit");
        System.out.println("==========================================");
    }

    private void addBook(){
        System.out.println("add book selected");
    }

    private void viewAllBooks(){
        System.out.println("view all books selected");
    }

    private void searchBooks(){
        System.out.println("issue book selected");
    }

    private void issueBook(){
        System.out.println("issue book selected");
    }

    private void returnBook(){
        System.out.println("return book selected");
    }

    private int readInt(String message){
        System.out.println(message);

        while (!scanner.hasNextInt()){
            System.out.println("please enter a valid number: ");
            scanner.next();
        }

        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
        
    }
}