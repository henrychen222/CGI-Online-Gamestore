// Import the following packages to use JDBC.
import  java.sql.*;
import  java.io.*;
import  oracle.jdbc.*;
import  oracle.jdbc.pool.OracleDataSource;

class  InsertGame {
    public static void  main( String args[ ] ) throws SQLException {
		String asin = null;
		String title = null;
		String price = null;
		boolean devs = false;

		if(args.length >= 1)
			asin = args[0];
		else
			return; //must have asin
		if(args.length >= 2)
			title = args[1];
		if(args.length >= 3)
			price = args[2];
		if(args.length >= 4)
			devs = true;

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
			Statement exists_state = conn.createStatement();
			String exists_query = "SELECT asin FROM games WHERE asin = '" + asin + "'";
			Statement state = conn.createStatement();
			StringBuilder query = new StringBuilder();

			ResultSet rset = exists_state.executeQuery(exists_query);
			if(rset.next()) //update
			{
				query.append("UPDATE games SET title = '");
				query.append(title);
				query.append("', price = ");
				query.append(price);
				query.append(", developers = dev_table(");
				for(int i = 3; i < args.length; i++)
				{
					if(i != 3)
						query.append(",");
					query.append("dev_id(");
					query.append(args[i]);
					query.append(")");
				}
				query.append(")"); //end dev_table(
				query.append(" WHERE asin = '");
				query.append(asin);
				query.append("'");
			}
			else
			{
				query.append("INSERT INTO games (asin, title, price, developers) VALUES ('");
				query.append(asin);
				if(title != null)
					query.append("','");
				query.append(title);
				if(title != null)
					query.append("',");
				query.append(price);
				query.append(",");
				if(!devs)
					query.append("NULL");
				else
				{
					query.append("dev_table(");
					for(int i = 3; i < args.length; i++)
					{
						if(i != 3)
							query.append(",");
						query.append("dev_id(");
						query.append(args[i]);
						query.append(")");
					}
					query.append(")");
				}

				query.append(")");
			}
			//System.out.println(query.toString());
			state.executeUpdate(query.toString());
			exists_state.close();
			state.close();
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
		conn.close();
	}
}

