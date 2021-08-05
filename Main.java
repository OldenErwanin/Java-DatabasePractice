package com.company;

import java.sql.*;

public class Main {
    public static final String DB_NAME = "testjava.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\Users\\Anonymous\\Documents\\SQLite\\databases\\" + DB_NAME;
    public static final String TABLE_CONTACTS = "contacts";

    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_EMAIL = "email";

    public static void main(String[] args) {

	    /* In this case, the connection will be closed automatically as soon as the try goes through

	    try (Connection conn = DriverManager.getConnection(CONNECTION_STRING);
             Statement statement = conn.createStatement()) {
	        statement.execute("CREATE TABLE contacts (name TEXT, phone INTEGER, email TEXT)");
        }*/

        // Here we close the connection manually
        try {
	        Connection conn = DriverManager.getConnection(CONNECTION_STRING);
            // conn.setAutoCommit(false);
	        Statement statement = conn.createStatement();

	        // DROP
	        statement.execute("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
	        // CREATE
            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_CONTACTS + " (" + COLUMN_NAME + " TEXT, " + COLUMN_PHONE + " INTEGER, " + COLUMN_EMAIL + " TEXT)");

            // INSERT
            /*statement.execute("INSERT INTO " + TABLE_CONTACTS + " (" + COLUMN_NAME + ", " + COLUMN_PHONE + ", " + COLUMN_EMAIL + ") VALUES ('Jane', 258741, 'jane@gmail.com')");
            statement.execute("INSERT INTO " + TABLE_CONTACTS + " (" + COLUMN_NAME + ", " + COLUMN_PHONE + ", " + COLUMN_EMAIL + ") VALUES ('Joe', 346124, 'joe@gmail.com')");
            statement.execute("INSERT INTO " + TABLE_CONTACTS + " (" + COLUMN_NAME + ", " + COLUMN_PHONE + ", " + COLUMN_EMAIL + ") VALUES ('Tim', 252637, 'tim@gmail.com')");
            statement.execute("INSERT INTO " + TABLE_CONTACTS + " (" + COLUMN_NAME + ", " + COLUMN_PHONE + ", " + COLUMN_EMAIL + ") VALUES ('Fido', 744142, 'fido@gmail.com')");*/
            insertContact(statement, "Jane", 258741, "jane@gmail.com");
            insertContact(statement, "Joe", 346124, "joe@gmail.com");
            insertContact(statement, "Tim", 252637, "tim@gmail.com");
            insertContact(statement, "Fido", 744142, "fido@gmail.com");


            // UPDATE
            statement.execute("UPDATE " + TABLE_CONTACTS + " SET " + COLUMN_PHONE + " = 1234567 WHERE " + COLUMN_NAME + " = 'Jane'");

            // DELETE
            statement.execute("DELETE FROM " + TABLE_CONTACTS + " WHERE " + COLUMN_NAME + " = 'Joe'");

            //statement.execute("SELECT * FROM contacts");
            //ResultSet results = statement.getResultSet();

            // SELECT
            ResultSet results = statement.executeQuery("SELECT * FROM " + TABLE_CONTACTS);
            while(results.next()) {
                System.out.println(results.getString(COLUMN_NAME) + " " + results.getInt(COLUMN_PHONE) + " " + results.getString(COLUMN_EMAIL));
            }

            // CLOSE
            results.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void insertContact(Statement statement, String name, int phone, String email) throws SQLException {
        statement.execute("INSERT INTO " + TABLE_CONTACTS + " (" + COLUMN_NAME + ", " + COLUMN_PHONE + ", " + COLUMN_EMAIL + ") VALUES ('" + name + "', " + phone + ", '" + email + "')");
    }
}
