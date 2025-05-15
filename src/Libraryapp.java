package com.mycompany.libraryapp;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class Libraryapp 
{
    // Connect to the MySQL database
    private static Connection connectToDatabase() 
    {
        Connection connection = null;
        try 
        {
            String url = "jdbc:mysql://localhost:3306/library_db";
            String user = "root";
            String password = "Sruthi"; 
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the database successfully.");
        } 
        catch (SQLException e) 
        {
            System.out.println("Database connection failed: " + e.getMessage());
        }
        return connection;
    }

    // Add new book to the database
    private static void addBook(JFrame frame, String title, String author, int year) 
    {
        try (Connection connection = connectToDatabase();
             PreparedStatement preparedStatement = connection.prepareStatement(
                 "INSERT INTO books (title, author, year) VALUES (?, ?, ?)")) {
             
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, author);
            preparedStatement.setInt(3, year);
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(frame, "Book added successfully!");
        } 
        catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(frame, "Error adding book: " + e.getMessage());
        }
    }

    // View all books in the database
    private static void viewBooks(JFrame frame) 
    {
        StringBuilder booksList = new StringBuilder();
        try (Connection connection = connectToDatabase();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM books")) {
             
            
            while (resultSet.next()) 
            {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                int year = resultSet.getInt("year");
                booksList.append("ID: ").append(id)
                          .append(", Title: ").append(title)
                          .append(", Author: ").append(author)
                          .append(", Year: ").append(year)
                          .append("\n");
            }
            JOptionPane.showMessageDialog(frame, booksList.toString());
        } 
        catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(frame, "Error retrieving books: " + e.getMessage());
        }
    }

    // Update book details in the database
    private static void updateBook(JFrame frame, int id, String title, String author, int year) 
    {
        try (Connection connection = connectToDatabase();
             PreparedStatement preparedStatement = connection.prepareStatement(
                 "UPDATE books SET title = ?, author = ?, year = ? WHERE id = ?")) {
             
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, author);
            preparedStatement.setInt(3, year);
            preparedStatement.setInt(4, id);
            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) 
            {
                JOptionPane.showMessageDialog(frame, "Book updated successfully!");
            } 
            else 
            {
                JOptionPane.showMessageDialog(frame, "Book not found.");
            }
        } 
        catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(frame, "Error updating book: " + e.getMessage());
        }
    }

    // Delete book from the database
    private static void deleteBook(JFrame frame, int id) 
    {
        try (Connection connection = connectToDatabase();
             PreparedStatement preparedStatement = connection.prepareStatement(
                 "DELETE FROM books WHERE id = ?")) 
                 {
             
            preparedStatement.setInt(1, id);
            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) 
            {
                JOptionPane.showMessageDialog(frame, "Book deleted successfully!");
            } 
            else 
            {
                JOptionPane.showMessageDialog(frame, "Book not found.");
            }
        } 
        catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(frame, "Error deleting book: " + e.getMessage());
        }
    }

    public static void main(String[] args) 
    {
        // Create the main frame
        JFrame frame = new JFrame("Library Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        // Create the panel
        JPanel panel = new JPanel(new GridLayout(6, 2));
        JLabel titleLabel = new JLabel("Title:");
        JTextField titleField = new JTextField(20);
        JLabel authorLabel = new JLabel("Author:");
        JTextField authorField = new JTextField(20);
        JLabel yearLabel = new JLabel("Year:");
        JTextField yearField = new JTextField(4);

        JButton addButton = new JButton("Add Book");
        JButton viewButton = new JButton("View Books");
        JButton updateButton = new JButton("Update Book");
        JButton deleteButton = new JButton("Delete Book");

        panel.add(titleLabel);
        panel.add(titleField);
        panel.add(authorLabel);
        panel.add(authorField);
        panel.add(yearLabel);
        panel.add(yearField);
        panel.add(addButton);
        panel.add(viewButton);
        panel.add(updateButton);
        panel.add(deleteButton);

        frame.add(panel);
        frame.setVisible(true);

        // Button actions
        addButton.addActionListener((ActionEvent e) -> {
            String title = titleField.getText();
            String author = authorField.getText();
            int year = Integer.parseInt(yearField.getText());
            addBook(frame, title, author, year);
        });

        viewButton.addActionListener((ActionEvent e) -> {
            viewBooks(frame);
        });

        updateButton.addActionListener((ActionEvent e) -> {
    try {
        String idStr = JOptionPane.showInputDialog(frame, "Enter Book ID to Update:");
        if (idStr == null || idStr.trim().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Book ID is required.");
            return;
        }

        int id = Integer.parseInt(idStr.trim());

        String title = titleField.getText().trim();
        String author = authorField.getText().trim();
        String yearStr = yearField.getText().trim();

        if (title.isEmpty() || author.isEmpty() || yearStr.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please fill in all the fields (Title, Author, Year).");
            return;
        }

        int year = Integer.parseInt(yearStr);
        updateBook(frame, id, title, author, year);
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(frame, "Please enter valid numbers for Book ID and Year.");
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(frame, "An error occurred: " + ex.getMessage());
    }
});

        
        deleteButton.addActionListener((ActionEvent e) -> {
            String idStr = JOptionPane.showInputDialog(frame, "Enter Book ID to Delete:");
            int id = Integer.parseInt(idStr);
            deleteBook(frame, id);
        });
    }
}
