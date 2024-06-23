package com.hydottech;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Database {

    private static final String URL = "jdbc:sqlite:/Users/glydetek/Desktop/dcit308grp4/src/main/java/com/hydottech/pharmacy.db";

    static {
        try {
            Class.forName("org.sqlite.JDBC");
            initializeDatabase();
        } catch (ClassNotFoundException e) {
            System.err.println("Failed to load SQLite JDBC driver.");
            e.printStackTrace();
        }
    }

    public static void initializeDatabase() {
        try (Connection conn = DriverManager.getConnection(URL)) {
            if (conn != null) {
                String createDrugsTable = "CREATE TABLE IF NOT EXISTS Drugs (" +
                        "drugCode TEXT PRIMARY KEY, " +
                        "name TEXT, " +
                        "description TEXT, " +
                        "stock INTEGER, " +
                        "price REAL)";
                conn.createStatement().execute(createDrugsTable);
                System.out.println("Drugs table created or already exists.");
            }
        } catch (SQLException e) {
            System.err.println("Failed to initialize the database.");
            e.printStackTrace();
        }
    }

    public static void addDrug(Drug drug) {
        String sql = "INSERT INTO Drugs(drugCode, name, description, stock, price) VALUES(?,?,?,?,?)";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, drug.getCode());
            pstmt.setString(2, drug.getName());
            pstmt.setString(3, drug.getDescription());
            pstmt.setInt(4, drug.getStock());
            pstmt.setDouble(5, drug.getPrice());
            pstmt.executeUpdate();
            System.out.println("Added into DB");
        } catch (SQLException e) {
            System.err.println("Failed to add drug to DB");
            e.printStackTrace();
        }
    }

    public static ObservableList<Drug> getAllDrugs() {
        ObservableList<Drug> drugs = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Drugs";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                drugs.add(new Drug(
                        rs.getString("drugCode"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("stock"),
                        rs.getDouble("price")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Failed to retrieve drugs from DB");
            e.printStackTrace();
        }
        return drugs;
    }
}
