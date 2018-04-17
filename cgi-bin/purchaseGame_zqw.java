// Import the following packages to use JDBC.
import  java.sql.*;
import  java.io.*;
import  oracle.jdbc.*;
import  oracle.jdbc.pool.OracleDataSource;

class  purchaseGame_zqw {
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

			
			
			String[] titles = args[0].split("-");
			String[] quantity = args[1].split("-");
			String name = args[2];
			
			
			
			//System.out.println("1");//test
			
			//String queryIsNew = "select name,title from  Customers_ZQW a ,table(a.games) b where a.name = '" + name + "'";
			
			
			int i = 0;
			int isInserted = 0;
			for(String info : titles){
				
				
				StringBuilder query = new StringBuilder();
				String queryIsNew = "select name,title,quantity from Customers_ZQW a ,table(a.games) b where a.name = '"+ name +"' and title='"+ info +"'";
				ResultSet rset = stmt.executeQuery(queryIsNew);
				
				if(rset.next()) //for update
				{
					query.append("update table(select games from customers_zqw where name = '"+ name +"') set quantity="+(Integer.parseInt(quantity[i])+rset.getInt(3))+" where  title='"+titles[i]+"'");
				}
				else
				{
					query.append("insert into table(select games from customers_zqw where name = '"+name+"') values('"+ info +"',"+ quantity[i] +")");
					
				}
				i++;
				//System.out.println("4");//test
				stmt.executeUpdate(query.toString());
				
				
				
				
				
			}
			stmt.close();
			StringBuilder outp = new StringBuilder();
			outp.append("[");
			outp.append("{\"status\":\"true\"");
			outp.append("}");
			outp.append("]");
			System.out.println(outp.toString());
					


		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
		conn.close();
	}
}

