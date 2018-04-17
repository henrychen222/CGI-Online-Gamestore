// Import the following packages to use JDBC.
import  java.sql.*;
import  java.io.*;
import  oracle.jdbc.*;
import  oracle.jdbc.pool.OracleDataSource;

class  addDeveloperToGame_zqw {
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
			String[] names = new String[]{};
			if(args.length==2){
				String asin = args[0];
				names = args[1].split("-");
				if(names != null && names.length>=1){
					for(int i=0;i<names.length;i++){
						StringBuilder query = new StringBuilder();
						query.append("insert into table(select g.developers from game_zqw g where asin='"+args[0]+"') (name) values('"+names[i]+"')");
						stmt.executeUpdate(query.toString());
					}
					
				}
				
				
				
				
			}
			stmt.close();
			
			System.out.println(names[0]+" "+ names[1]);

		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
		conn.close();
	}
}

