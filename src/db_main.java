package kanoon_ke_haath;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class db_main {

  static Connection con;
  static String connectionString = "jdbc:hsqldb:file:db_data/database";

  public static void main(String[] args) throws Exception {

    String createTables = readToString("sql/tables.sql");
    String addValues = readToString("sql/insertVal.sql");

    System.out.println("Attempting to create contacts DB ... ");

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
      con.createStatement().executeUpdate(createTables);

      // add random values in tables
      con.createStatement().executeUpdate(addValues);

      ResultSet rs = con.createStatement().executeQuery("select * from administrator");
      System.out.println("In Administrator: ");
      while (rs.next()) {
        System.out.println(rs.getString(1));
      }

      rs = con.createStatement().executeQuery("select * from police_dept");
      System.out.println("In Police_DEPT: ");
      while (rs.next()) {
        System.out.print(rs.getString(1) + " ");
        System.out.print(rs.getString(2) + " ");
        System.out.print(rs.getString(3) + " ");
        System.out.print(rs.getString(4) + " ");
        System.out.print(rs.getString(5) + " ");
        System.out.print(rs.getString(6) + " ");
        System.out.println(rs.getString(7));
      }

      rs = con.createStatement().executeQuery("select * from fir");
      System.out.println("In FIR: ");
      while (rs.next()) {
        System.out.print(rs.getString(1) + " ");
        System.out.print(rs.getString(2) + " ");
        System.out.print(rs.getString(3) + " ");
        System.out.println(rs.getString(4));
      }

      rs = con.createStatement().executeQuery("select * from police_officer");
      System.out.println("In Police_OFFICER: ");
      while (rs.next()) {
        System.out.print(rs.getString(1) + " ");
        System.out.print(rs.getString(2) + " ");
        System.out.println(rs.getString(3));
      }

      rs = con.createStatement().executeQuery("select * from criminal");
      System.out.println("In CRIMINAL: ");
      while (rs.next()) {
        System.out.print(rs.getString(1) + " ");
        System.out.print(rs.getString(2) + " ");
        System.out.print(rs.getString(3) + " ");
        System.out.print(rs.getString(4) + " ");
        System.out.print(rs.getString(5) + " ");
        System.out.println(rs.getString(6));
      }

      rs = con.createStatement().executeQuery("select * from applic");
      System.out.println("In APPLIC: ");
      while (rs.next()) {
        System.out.print(rs.getString(1) + " ");
        System.out.print(rs.getString(2) + " ");
        System.out.print(rs.getString(3) + " ");
        System.out.print(rs.getString(4) + " ");
        System.out.println(rs.getString(5));
      }

    } catch (SQLException e) {
      throw e;
    } finally {
      con.close();
    }
  }

  public static String readToString(String fname) throws Exception {
    File file = new File(fname);
    String string = FileUtils.readFileToString(file, "utf-8");
    return string;
  }
}