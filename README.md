# Library-Management-System

A simple Library Management System built using **Core Java** and **PostgreSQL**.  
It helps manage books, members, book issue/return records, and overdue fines.

---

## Features

- Add, view, search, update, and delete books
- Add and manage library members
- Issue books to members
- Return books and update availability
- Calculate overdue fines
- View issued and overdue books
- Store data permanently in PostgreSQL

---

## Technologies Used

| Component | Technology |
|---|---|
| Language | Java |
| Database | PostgreSQL |
| Database Connection | JDBC |
| User Interface | Console-based |

---

## Project Structure

```text
library-management-system/
│
├── src/
│   └── com/library/
│       ├── ui/          # Console menus and user interaction
│       ├── service/     # Business logic and validation
│       ├── dao/         # Database operations using JDBC
│       ├── model/       # Book, Member, Loan classes
│       ├── exception/   # Custom exceptions
│       └── util/        # Database connection helper
│
├── resources/
│   ├── db.properties
│   └── schema.sql
│
└── README.md
```

---

## Architecture

The project follows a simple layered architecture:

```text
Console UI
    ↓
Service Layer
    ↓
DAO Layer
    ↓
PostgreSQL Database
```

- **UI Layer:** Displays menus and takes user input.
- **Service Layer:** Contains business rules and validations.
- **DAO Layer:** Performs database operations using JDBC.
- **Model Layer:** Represents books, members, and loans.
- **Database:** Stores all application data.

---

## Database Tables

### books

| Column | Description |
|---|---|
| book_id | Primary key |
| title | Book title |
| author | Book author |
| isbn | Unique book ISBN |
| category | Book category |
| total_copies | Total copies available in library |
| available_copies | Copies currently available |

### members

| Column | Description |
|---|---|
| member_id | Primary key |
| full_name | Member name |
| email | Member email |
| phone | Member phone number |
| membership_date | Date of registration |
| status | ACTIVE or INACTIVE |

### loans

| Column | Description |
|---|---|
| loan_id | Primary key |
| book_id | Foreign key to books |
| member_id | Foreign key to members |
| issue_date | Date the book was issued |
| due_date | Expected return date |
| return_date | Actual return date |
| fine_amount | Fine for late return |

---

## Setup Instructions

### 1. Install Requirements

- Java JDK 17 or above
- PostgreSQL
- PostgreSQL JDBC Driver
- IntelliJ IDEA, Eclipse, or VS Code

### 2. Create Database

Open PostgreSQL and run:

```sql
CREATE DATABASE library_db;
```

### 3. Create Tables

Run the SQL commands from `schema.sql`, or use the following basic schema:

```sql
CREATE TABLE books (
    book_id BIGSERIAL PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    author VARCHAR(150) NOT NULL,
    isbn VARCHAR(20) UNIQUE,
    category VARCHAR(100),
    total_copies INT NOT NULL,
    available_copies INT NOT NULL
);

CREATE TABLE members (
    member_id BIGSERIAL PRIMARY KEY,
    full_name VARCHAR(150) NOT NULL,
    email VARCHAR(150) UNIQUE,
    phone VARCHAR(20),
    membership_date DATE DEFAULT CURRENT_DATE,
    status VARCHAR(20) DEFAULT 'ACTIVE'
);

CREATE TABLE loans (
    loan_id BIGSERIAL PRIMARY KEY,
    book_id BIGINT REFERENCES books(book_id),
    member_id BIGINT REFERENCES members(member_id),
    issue_date DATE DEFAULT CURRENT_DATE,
    due_date DATE NOT NULL,
    return_date DATE,
    fine_amount NUMERIC(10,2) DEFAULT 0
);
```

### 4. Configure Database

Create `db.properties` inside the `resources` folder:

```properties
db.url=jdbc:postgresql://localhost:5432/library_db
db.username=postgres
db.password=your_password
```

---

## How to Run

### Using Maven

```bash
mvn clean compile
mvn exec:java -Dexec.mainClass="com.library.ui.LibraryConsoleUI"
```

### Using Manual Compilation

```bash
javac -cp "lib/postgresql.jar" -d out $(find src -name "*.java")
java -cp "out:lib/postgresql.jar" com.library.ui.LibraryConsoleUI
```

For Windows:

```bash
java -cp "out;lib/postgresql.jar" com.library.ui.LibraryConsoleUI
```

---

## Console Menu

```text
=========================================
      LIBRARY MANAGEMENT SYSTEM
=========================================
1. Add Book
2. View All Books
3. Search Book
4. Add Member
5. View Members
6. Issue Book
7. Return Book
8. View Issued Books
9. View Overdue Books
0. Exit
=========================================
Enter your choice:
```

---

## Example

### Issue a Book

```text
Enter Book ID: 1
Enter Member ID: 5

Book issued successfully.
Due date: 17-Nov-2026
```

### Return a Book

```text
Enter Loan ID: 10

Book returned successfully.
Fine: ₹0.00
```

---

## Business Rules

- A book cannot be issued if it is unavailable.
- A member must exist to issue a book.
- Returning a book increases its available copies.
- A fine is applied only if the book is returned after the due date.
- ISBN and member email must be unique.

---

## Future Improvements

- Admin login system
- Book reservation
- JavaFX graphical interface
- Email reminders for due dates
- Reports and analytics
- Unit testing with JUnit

---

## License

This project is licensed under the MIT License.
