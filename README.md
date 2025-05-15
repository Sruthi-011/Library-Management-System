# 📚 Library Management System

A simple desktop-based Library Management System built using Java Swing and MySQL.

---

## 🧩 Problem Statement

Managing books manually in small libraries can lead to inefficiencies such as:

- ❌ Misplaced records  
- ❌ Data redundancy  
- ❌ Lack of quick access to information  

This results in poor user experience and difficulty maintaining accurate and up-to-date book inventories.

---

## 💡 Proposed Solution

- A desktop application built using **Java Swing** (UI) and **MySQL** (backend).  
- The system supports **adding, viewing, updating, and deleting** books via a user-friendly GUI.
- It uses **JDBC** to connect Java with the MySQL database.
- Each book has an auto-generated ID, title, author, and year.
- The system is scalable and can be extended with:
  - User login
  - Book search
  - Issue tracking
  - Reporting tools

---

## ✅ Key Benefits

- 📚 Efficient Book Management  
- 😀 User-Friendly Interface  
- ⚡ Fast and Accurate Data Handling  
- 🗃️ Centralized Data Storage  
- 🔧 Easy Maintenance and Updates  
- 📈 Improved Productivity

---

## ✨ Features

### 1. Add Book
- Accepts title, author, and year
- Inserts data into the MySQL `books` table
- Displays confirmation

### 2. View Books
- Fetches all book records
- Displays them using `JOptionPane`

### 3. Update Book
- Prompts user for Book ID
- Updates title, author, and year
- Shows success or error message

### 4. Delete Book
- Prompts for Book ID
- Deletes the matching entry
- Shows confirmation or error

---

## ⚙️ Technical Specifications

### Technologies Used

- **Frontend (UI)**: Java Swing (AWT & Swing)
- **Backend**: Java
- **Database**: MySQL
- **Connectivity**: JDBC

---

### 📊 Database Schema

**Table:** `books`

| Column | Type      | Description                 |
|--------|-----------|-----------------------------|
| id     | INT       | Primary Key, Auto Increment |
| title  | VARCHAR   | Book title                  |
| author | VARCHAR   | Book author                 |
| year   | INT       | Year of publication         |

---

## 🧠 Implementation Details

### Core Class: `Libraryapp`

- `main(String[] args)` — Launches the GUI
- `connectToDatabase()` — Connects to MySQL

### CRUD Methods

- `addBook()` — Add new book to DB
- `viewBooks()` — Show all books
- `updateBook()` — Update book details
- `deleteBook()` — Delete book by ID

### GUI Components

- `JFrame`, `JPanel`, `JLabel`, `JTextField`, `JButton`, `JOptionPane`

### Event Handling

- `addButton` → `addBook()`
- `viewButton` → `viewBooks()`
- `updateButton` → prompt for ID → `updateBook()`
- `deleteButton` → prompt for ID → `deleteBook()`

---

## 📄 Conclusion

The Library Management System project effectively demonstrates how to use **Java Swing**, **JDBC**, and **MySQL** together to build a complete CRUD-based desktop application.

It provides hands-on experience in both frontend and backend development and can serve as a strong foundation for larger systems with:

- Login authentication
- Book issue/return modules
- Advanced search
- Reporting

---

