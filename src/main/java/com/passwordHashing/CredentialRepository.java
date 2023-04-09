package com.passwordHashing;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CredentialRepository {

    public String doHashing(String emailPassword, byte[] salt) {
        final String sqlQuery = "SELECT SHA2(CONCAT(?, ?, 'PogiSiDan'), 256)";
        String hashedPassword = null;
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement query = connection.prepareStatement( sqlQuery )){

            query.setString(1, emailPassword);
            query.setBytes(2, salt);

            ResultSet result = query.executeQuery();
            while (result.next()) {
                hashedPassword = result.getString(1);
            }
            System.out.println("Hashing password success");
            return hashedPassword;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error Occurred! Hashing the password failed!");
        }
        return hashedPassword;
    }

    public void insertCredential(Credential credential) {
        final String sqlQuery = "INSERT INTO tbl_farmer_credential VALUES (?, SHA2(CONCAT(?, ?, 'PogiSiDan'), 256), ?)";
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement query = connection.prepareStatement( sqlQuery )) {

            query.setString(1, credential.getEmail());
            query.setString(2, credential.getPassword());
            query.setBytes(3, credential.getSalt());
            query.setBytes(4, credential.getSalt());
            query.executeUpdate();

            System.out.println("Inserting credential success");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error Occurred! Inserting credential failed!");
        }
    }

    public byte[] getSalt(String emailUsername) {
        final String sqlQuery = "SELECT salt FROM tbl_farmer_credential WHERE email_username = ?";
        byte[] salt = null;
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement query = connection.prepareStatement( sqlQuery )) {

            query.setString(1, emailUsername);

            ResultSet result = query.executeQuery();
            while (result.next()) {
                salt = result.getBytes("salt");
            }
            System.out.println("Retrieving the salt success");
            return salt;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error Occurred! Retrieving the salt failed!");
        }
        return salt;
    }

    public String getHashedPassword(String emailUsername) {
        final String sqlQuery = "SELECT email_password FROM tbl_farmer_credential WHERE email_username = ?";
        String hashedPassword = null;
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement query = connection.prepareStatement( sqlQuery )) {

            query.setString(1, emailUsername);

            ResultSet result = query.executeQuery();
            while (result.next()) {
                hashedPassword = result.getString("email_password");
            }
            System.out.println("Retrieving the password success");
            return hashedPassword;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error Occurred! Retrieving the password failed!");
        }
        return hashedPassword;
    }
}
