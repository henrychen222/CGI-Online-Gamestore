// Import the following packages to use JDBC.
import  java.sql.*;
import  java.io.*;
import  oracle.jdbc.*;
import  oracle.jdbc.pool.OracleDataSource;

class  saveGame_zqw {
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

			StringBuilder query = new StringBuilder();
			
			String asin = args[0];
			String title = args[1];
			String price = args[2];
			
			//System.out.println("1");//test
			
			String queryIsNew = "SELECT asin FROM game_zqw WHERE asin = '" + asin + "'";
			ResultSet rset = stmt.executeQuery(queryIsNew);
			
			//System.out.println("2");//test
			
			boolean firstFlag = true;
			if(rset.next()) //for update
			{
				//System.out.println("3");//test
				
				query.append("UPDATE game_zqw SET title = '");
				query.append(title);
				query.append("', price = ");
				query.append(price);
				query.append(", developers = developerObjZqw_bas(");//developerObjZqw_bas (developerObjZqw_ty
				for(int i = 3; i < args.length; i++)
				{
					if(firstFlag){
						firstFlag = false;
						query.append("developerObjZqw_ty('");
						query.append(args[i]);
						query.append("')");
					}else{
						query.append(",");
						query.append("developerObjZqw_ty('");
						query.append(args[i]);
						query.append("')");
					}
					
				}
				query.append(")"); 
				query.append(" WHERE asin = '");
				query.append(asin);
				query.append("'");
				
				//System.out.println(query.toString());//test
			}
			else
			{
				query.append("INSERT INTO game_zqw (asin, title, price, developers) VALUES ('");
				query.append(asin);
				query.append("','");
				query.append(title);
				query.append("','");
				query.append(price);
				query.append("',");
				query.append("developerObjZqw_bas(");//developerObjZqw_bas (developerObjZqw_ty
				for(int i = 3; i < args.length; i++)
				{
					if(firstFlag){
						firstFlag = false;
						query.append("developerObjZqw_ty('");
						query.append(args[i]);
						query.append("')");
					}else{
						query.append(",");
						query.append("developerObjZqw_ty('");
						query.append(args[i]);
						query.append("')");
					}
					
				}
				query.append("))");
				
				//System.out.println(query.toString());//test
			}
			//System.out.println("4");//test
			stmt.executeUpdate(query.toString());
			
			stmt.close();
			
			StringBuilder  outp = new StringBuilder();
			outp.append("<!DOCTYPE html><html><head><title>Game have been added !</title></head>");
			outp.append("<body>");
			outp.append("<script language='javascript'>document.location = 'http://people.aero.und.edu/~wchen/513/1/Entergame_zqw.html'</script>");
			outp.append("</body>");
			outp.append("</html>");
			
			System.out.println( outp.toString() );


		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
		conn.close();
	}
}

