/***************************************************************

  This program shows how to enter customer information
    into the customers table.

  To use this program, you need to create a table by
    using the following command:

    SQL> create table  customers (
      2    name  varchar(32),
      3    SSN   char(10) primary key,
      4    no    integer check (no >= 0) );

  An example of calling this program is as follows:

    shell> java EnterCustomer 'Super Mario' '368061766' 45

***************************************************************/

// Import the following packages to use JDBC.
import  java.sql.*;
import  java.io.*;
import  oracle.jdbc.*;
import  oracle.jdbc.pool.OracleDataSource;

class  EnterCustomer {
  public static void  main( String args[ ] ) 
    throws SQLException {
      
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
      // Create and compose a statement.
      Statement stmt = conn.createStatement( );
      String  query  = "insert into customers values ('";
      query += args[0] + "', '" + args[1] + "', " + args[2] + ")";
    
      // Insert the data into the customers table.
      stmt.executeUpdate( query );
      stmt.close( );
    }
    catch( SQLException ex ) {
      System.out.println( ex );
    }

    // Commit and close the connection.
    conn.commit( );
    conn.close ( );
  }
}
