/*******************************************************************

  This program shows how to list the stores in the
    stores table.

  To use this program, you need to create a table
    stores by using the following commands:

  SQL> create table  stores (
    2    store_key  INTEGER PRIMARY KEY,
    3    city       VARCHAR(32) NOT NULL,
    4    region     VARCHAR(16) NOT NULL );
  Table created.

*******************************************************************/

// Import the following packages to use JDBC.
import  java.sql.*;
import  java.io.*;
import  oracle.jdbc.*;
import  oracle.jdbc.pool.OracleDataSource;

class  List {
  public static void  main( String args[ ] ) throws SQLException {
    String user     = "wchen";
    String password = "w1149396";
    String database = "oracle1.aero.und.edu:1521/cs513.aero.und.edu";

    // Open an OracleDataSource and get a connection.
    OracleDataSource ods = new OracleDataSource( );
    ods.setURL     ( "jdbc:oracle:thin:@" + database );
    ods.setUser    ( user );
    ods.setPassword( password );
    Connection conn = ods.getConnection( );

    try {
      // Create, compose, and execute a statement.
      Statement stmt = conn.createStatement( );
      String  query  = "select store_key, city, region from stores ";
              query += "where region like '%" + args[0].trim( ) + "%'";
      System.out.println( query + "<b>" );
      ResultSet rset = stmt.executeQuery( query );

      // Iterate through the result and print the data.
      while ( rset.next( ) ) {
        System.out.print( rset.getString(1) + ", " + rset.getString(2) );
        System.out.print( ", " + rset.getString(3) );
      }
      // Close the ResultSet and Statement.
      rset.close( );
      stmt.close( );
    }
    catch ( SQLException ex ) {
      System.out.println( ex );
    }
    // Close the Connection.
    conn.close( );
  }
}
