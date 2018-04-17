// Import the following packages to use JDBC.
import  java.sql.*;
import  java.io.*;
import  oracle.jdbc.*;
import  oracle.jdbc.pool.OracleDataSource;

class  DeveloperInfo {
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
		
		try
		{
			Statement state = conn.createStatement();
			String query = "SELECT id, name FROM developers";
			ResultSet rset = state.executeQuery(query);

			StringBuilder builder = new StringBuilder();
			builder.append("[");
			boolean firstTime = true;
			while(rset.next())
			{
				if(firstTime)
					firstTime = false;
				else
					builder.append(",");
				builder.append("{\"Id\":\"");
				builder.append(rset.getString(1));
				builder.append("\",\"Name\":\"");
				builder.append(rset.getString(2));
				builder.append("\"}");
			}
			builder.append("]");

			System.out.println(builder.toString());

			rset.close();
			state.close();
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
		conn.close();
	}
}
