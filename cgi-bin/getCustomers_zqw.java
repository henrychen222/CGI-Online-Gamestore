// Import the following packages to use JDBC.
import  java.sql.*;
import  java.io.*;
import  oracle.jdbc.*;
import  oracle.jdbc.pool.OracleDataSource;

class  getCustomers_zqw {
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

		
			
			
			if(args.length==0){
				query.append("select * from customers_zqw order by name");
			
				ResultSet rset_info = stmt.executeQuery(query.toString());
				
				
				StringBuilder builder = new StringBuilder();
				boolean firstFlag = true;
				builder.append("[");
				while(rset_info.next())
				{
					if(firstFlag){
						firstFlag = false;
						builder.append("{");
						builder.append("\"Name\":\"");
						builder.append(rset_info.getString(1));
						builder.append("\"}");
					}else{
						builder.append(",");
						builder.append("{");
						builder.append("\"Name\":\"");
						builder.append(rset_info.getString(1));
						builder.append("\"}");
					}
				
				}
				
				builder.append("]");
				stmt.close();
				System.out.println( builder );
				
			}else{
				query.append("select c.name ,t.title,t.quantity,g.asin from customers_zqw c,table(c.games)t , game_zqw g where  t.title = g.title and name = '"+args[0]+"'");
			
				ResultSet rset_info = stmt.executeQuery(query.toString());
				
				
				StringBuilder builder = new StringBuilder();
				boolean firstFlag = true;
				boolean secondFlag = true;
				builder.append("[");
				int checkedNo = 0;
				while(rset_info.next())
				{
					checkedNo++;
					if(firstFlag){
						firstFlag = false;
						builder.append("{\"Name\":\"");
						builder.append(rset_info.getString(1));
						builder.append("\",\"Games\":[");
					}else{
						if(secondFlag){
							secondFlag = false;
							builder.append("{\"Asin\":\"");
							builder.append(rset_info.getString(4));
							builder.append("\",\"Title\":\"");
							builder.append(rset_info.getString(2));
							builder.append("\",\"Quantity\":\"");
							builder.append(rset_info.getString(3));
							builder.append("\"}");
						}else{
							builder.append(",");
							builder.append("{\"Asin\":\"");
							builder.append(rset_info.getString(4));
							builder.append("\",\"Title\":\"");
							builder.append(rset_info.getString(2));
							builder.append("\",\"Quantity\":\"");
							builder.append(rset_info.getString(3));
							builder.append("\"}");
							
						}
						
						
					}
				
				}
				builder.append("]}");
				builder.append("]");
				stmt.close();
				if(checkedNo>0){
					System.out.println( builder );
				}
				else{
					System.out.println( "" );
				}
				
				
				
				
				
			}
			

		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
		conn.close();
	}
}

