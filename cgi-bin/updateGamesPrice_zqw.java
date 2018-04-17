// Import the following packages to use JDBC.
import  java.sql.*;
import  java.io.*;
import  oracle.jdbc.*;
import  oracle.jdbc.pool.OracleDataSource;

class  updateGamesPrice_zqw {
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

			
			
			String[] asinArr = args[0].toString().split("-");
			String[] priceArr = args[1].toString().split("-");
			
			//System.out.println("1");//test
			
			
			for(int i=0;i<asinArr.length;i++){
				StringBuilder query = new StringBuilder();
				query.append("UPDATE game_zqw SET price = '");
				query.append(priceArr[i]);
				query.append("' ");
				query.append(" WHERE asin = '");
				query.append(asinArr[i]);
				query.append("'");
				stmt.executeUpdate(query.toString());
			}
			
			stmt.close();
			


		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
		conn.close();
	}
}

