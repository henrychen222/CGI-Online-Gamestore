/***************************************************

  This program shows how to list customers
    from the CUSTOMERS table.

  To use this program, you need to create a
    table by using the following command:

    create table customers (
      name   varchar(32),
      SSN    char(10) primary key,
      no     integer check (no >= 0) );

  An example of calling this program is as follows:

    shell> java ListCustomer '123456789'

***************************************************/

// Import the following packages to use JDBC.
import  java.sql.*;
import  java.io.*;
import  oracle.jdbc.*;
import  oracle.jdbc.pool.OracleDataSource;

class  ListCustomers {
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
      // Create and compose a Statement.
      Statement stmt = conn.createStatement( );

      // List customers from the CUSTOMERS table.
      String query = "select name, SSN, no from customers";

      // If the customer is with a specific SSN,
      if ( args.length != 0 )
        query = query + " where SSN = " + args[0];
      ResultSet rset = stmt.executeQuery( query );
      
      // Iterate through the result and print the customers.
      System.out.println ( );
      System.out.println ( "             Name                  SSN     Number" );
      System.out.println ( "==============================  =========  ======" );
      while ( rset.next( ) ) {
        String  str1 = rset.getString( 1 );
        String  str2 = "                                ";
        System.out.print   ( str1 );
        int     endIndex = 32 - str1.length( );
        System.out.print   ( str2.substring( 0, endIndex ) );
        System.out.print   ( rset.getString( 2 ) );
        System.out.print   ( "   " );
        System.out.print   ( rset.getString( 3 ) );
        System.out.println ( );
      }
      System.out.println( );
      stmt.close( );
      rset.close( );
    }
    catch( SQLException ex ) {
      System.out.println( ex );
    }

    // Close the connection.
    conn.commit( );
    conn.close ( );
  }
}
