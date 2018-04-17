// Import the following packages to use JDBC.
import  java.sql.*;
import  java.io.*;
import  oracle.jdbc.*;
import  oracle.jdbc.pool.OracleDataSource;

class  registerCustomer_zqw {
    public static void  main( String args[ ] ) throws SQLException {

                
		String user     = "wchen";
		String password = "w1149396";
		String database = "oracle1.aero.und.edu:1521/cs513.aero.und.edu";

		OracleDataSource ods = new OracleDataSource( );
		ods.setURL     ( "jdbc:oracle:thin:@" + database );
		ods.setUser    ( user );
		ods.setPassword( password );
		Connection conn = ods.getConnection( );
		        
        //System.out.println(" we are in JDBC.java");//test
		try
		{
			Statement stmt = conn.createStatement();

			//StringBuilder query = new StringBuilder();
			
			
			//System.out.println(args[0]);//test
			
			String queryIsNew = "insert into Customer_ZQW values('"+args[0]+"',null);";
			stmt.executeUpdate(queryIsNew);
			stmt.close();
			

		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
		conn.close();
	}
}

