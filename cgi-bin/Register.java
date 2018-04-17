// Import the following packages to use JDBC.
import  java.sql.*;
import  java.io.*;
import  oracle.jdbc.*;
import  oracle.jdbc.pool.OracleDataSource;

class  Register {
    public static void  main( String args[ ] ) throws SQLException {

                
		String user     = "wchen";
		String password = "w1149396";
		String database = "oracle1.aero.und.edu:1521/cs513.aero.und.edu";

		OracleDataSource ods = new OracleDataSource( );
		ods.setURL     ( "jdbc:oracle:thin:@" + database );
		ods.setUser    ( user );
		ods.setPassword( password );
		Connection conn = ods.getConnection( );
		        
        //System.out.println(" we are in JDBC.java");
		try
		{
			Statement stmt = conn.createStatement();

			StringBuilder query = new StringBuilder();

			int no = args.length;
			System.out.println(no);

			query.append("INSERT INTO customer_begin (c_id,c_name, password) VALUES (customer_begion_seq.nextval,'"+args[0]+"','"+args[1]+"')");

            stmt.executeUpdate(query.toString());
			stmt.close();
			
			System.out.println( "<h3><em>" + query + "</em><br /><br />" );
			System.out.print( "<font>success</font>" );
			System.out.println( "</h3>" );
			//System.out.print( "<form><input type='button' name='administor' value='administor' onclick='location.href='Login.html''></form>" );
			

		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
		conn.close();
	}
}

