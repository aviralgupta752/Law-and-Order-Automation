package kanoon_ke_haath;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;

public class db_connect {

  static Connection con;
  static String connectionString = "jdbc:hsqldb:file:lawOrder/db_data/database";

  public static void main(String[] args) throws Exception {

    try {
      Class.forName("org.hsqldb.jdbc.JDBCDriver");
    } catch (ClassNotFoundException e) {
      throw e;
    }

    try {
      // will create DB if does not exist
      // "SA" is default user with hypersql
      con = DriverManager.getConnection(connectionString, "SA", "");

      // create tables
      con.createStatement().executeQuery("");

      ResultSet rs = con.createStatement().executeQuery("select * from administrator");
      while (rs.next()) {
        System.out.println(rs.getString(1));
      }

    } catch (SQLException e) {
      throw e;
    } finally {
      con.close();
    }
  }
}