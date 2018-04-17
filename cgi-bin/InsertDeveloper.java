// Import the following packages to use JDBC.
import  java.sql.*;
import  java.io.*;
import  oracle.jdbc.*;
import  oracle.jdbc.pool.OracleDataSource;

class  InsertDeveloper {
    public static void  main( String args[ ] ) throws SQLException {
		if(args.length > 1)
			return;

		String user     = "cbowley";
		String password = "secrets";
		String database = "oracle1.aero.und.edu:1521/cs513.aero.und.edu";

		// Open an OracleDataSource and get a connection.
		OracleDataSource ods = new OracleDataSource( );
		ods.setURL     ( "jdbc:oracle:thin:@" + database );
		ods.setUser    ( user );
		ods.setPassword( password );
		Connection conn = ods.getConnection( );
		
		try
		{
			Statement state = conn.createStatement();
			StringBuilder query = new StringBuilder();
			query.append("INSERT INTO developers (name) VALUES ('");
			query.append(args[0]);
			query.append("')");
			state.executeUpdate(query.toString());
			state.close();
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
		conn.close();
	}
}

