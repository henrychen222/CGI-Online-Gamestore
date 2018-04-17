
// Import the following packages to use JDBC.
import  java.sql.*;
import  java.io.*;
import  oracle.jdbc.*;
import  oracle.jdbc.pool.OracleDataSource;

class  DeleteDevelopers {
    public static void  main( String args[ ] ) throws SQLException {
		if(args.length == 0)
			return; //only do stuff if there is people to delete

		String user     = "wchen";
		String password = "w1149396";
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
			query.append("DELETE FROM developers WHERE");
			for(int i = 0; i < args.length; i++)
			{
				if(i != 0)
					query.append(" OR");
				query.append(" id = ");
				query.append(args[i]);
			}
			//System.out.println(query.toString());
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
