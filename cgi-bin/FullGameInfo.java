// Import the following packages to use JDBC.
import  java.sql.*;
import  java.io.*;
import  oracle.jdbc.*;
import  oracle.jdbc.pool.OracleDataSource;

class  FullGameInfo {
    public static void  main( String args[ ] ) throws SQLException {
		if(args.length == 0)
		{
			System.out.println("{\"Asin\":\"0000000000\",\"Title\":\"No ASIN Given\"}");
			return;
		}
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
			Statement state_info = conn.createStatement(), state_devs = conn.createStatement();
			String query_info = "SELECT asin, title, price FROM games WHERE asin = '" + args[0] + "'";
			String query_devs = "SELECT d.id, d.name FROM games g, developers d WHERE g.asin = '" + args[0] + "' AND d.id IN (SELECT * FROM table(g.developers))";
			ResultSet rset_info = state_info.executeQuery(query_info);
			ResultSet rset_devs = state_devs.executeQuery(query_devs);

			StringBuilder builder = new StringBuilder();
			builder.append("{");
			boolean firstTime = true;
			if(rset_info.next())
			{
				builder.append("\"Asin\":\"");
				builder.append(rset_info.getString(1));
				builder.append("\",\"Title\":\"");
				builder.append(rset_info.getString(2));
				builder.append("\",\"Price\":\"");
				builder.append(String.format("%.2f",Float.parseFloat(rset_info.getString(3))));
				builder.append("\",");
			}
			builder.append("\"Devs\":[");
			while(rset_devs.next())
			{
				if(firstTime)
					firstTime = false;
				else
					builder.append(",");
				builder.append("{\"Id\":\"");
				builder.append(rset_devs.getString(1));
				builder.append("\",\"Name\":\"");
				builder.append(rset_devs.getString(2));
				builder.append("\"}");
			}
			builder.append("]}");

			System.out.println(builder.toString());

			rset_info.close();
			rset_devs.close();
			state_info.close();
			state_devs.close();
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
		conn.close();
	}
}
