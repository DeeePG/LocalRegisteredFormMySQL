package db;

import Constants.CommonConstants;

import java.sql.*;

//this class is the connection between form and database(MySql)
public class MyJDBC {
    //register new user to the DB
    //true - register success
    //false - register failure
    public static boolean register(String username, String password) {
        //First check if the user already exist in the database
        try {
            if(!checkUser(username)) {
                //Connection to the db
                Connection connection = DriverManager.getConnection(CommonConstants.DB_URL,
                        CommonConstants.DB_USERNAME, CommonConstants.DB_PASSWORD);
                PreparedStatement insertUser = connection.prepareStatement(
                        "INSERT INTO " + CommonConstants.DB_USERS_TABLE_NAME + "(username, password)" +
                                "VALUES(?, ?)"
                );
                //Insert parameters in the insert query
                insertUser.setString(1,username);
                insertUser.setString(2,password);
                //update db with the new user
                insertUser.executeUpdate();
                return true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    //Check if user already exist in the database
    //true - user exist
    //false - user doesn't exist
    public static boolean checkUser(String username) {

        try {
            Connection connection = DriverManager.getConnection(CommonConstants.DB_URL,
                    CommonConstants.DB_USERNAME, CommonConstants.DB_PASSWORD);

            PreparedStatement checkUserExist = connection.prepareStatement("SELECT * FROM " +
                    CommonConstants.DB_USERS_TABLE_NAME + " WHERE USERNAME = ?");

            checkUserExist.setString(1, username);
            ResultSet resultset = checkUserExist.executeQuery();

            //check to see if the result set is empty
            //if it is empty it means there was not data with this username(user does not exist)
            if(!resultset.isBeforeFirst()) return false;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    public static boolean validateLogin(String username, String password) {

        try {
            Connection connection = DriverManager.getConnection(CommonConstants.DB_URL,
                    CommonConstants.DB_USERNAME, CommonConstants.DB_PASSWORD);
            //Create SELECT query
            PreparedStatement validateUser = connection.prepareStatement("SELECT * FROM " +
                    CommonConstants.DB_USERS_TABLE_NAME + " WHERE USERNAME = ? AND PASSWORD = ?");

            validateUser.setString(1, username);
            validateUser.setString(2, password);

            ResultSet resultSet = validateUser.executeQuery();

            if(!resultSet.isBeforeFirst()) return false;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }
}
