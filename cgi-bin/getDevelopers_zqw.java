// Import the following packages to use JDBC.
import  java.sql.*;
import  java.io.*;
import  oracle.jdbc.*;
import  oracle.jdbc.pool.OracleDataSource;

class  getDevelopers_zqw {
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
				query.append("select * from developers order by ID");
			
				ResultSet rset_info = stmt.executeQuery(query.toString());
				
				
				StringBuilder builder = new StringBuilder();
				boolean firstFlag = true;
				builder.append("[");
				while(rset_info.next())
				{
					if(firstFlag){
						firstFlag = false;
						builder.append("{\"Id\":\"");
						builder.append(rset_info.getString(1));
						builder.append("\",\"Name\":\"");
						builder.append(rset_info.getString(2));
						builder.append("\"}");
					}else{
						builder.append(",");
						builder.append("{\"Id\":\"");
						builder.append(rset_info.getString(1));
						builder.append("\",\"Name\":\"");
						builder.append(rset_info.getString(2));
						builder.append("\"}");
					}
				
				}
				
				builder.append("]");
				stmt.close();
				System.out.println( builder );
				
			}else{
				query.append("select * from ("
					+" select dev.id,dev.developer_name,g.title,g.name,g.asin from developers dev "
					+" left join (select a.asin ,a.title ,b.name from game_zqw a,table(a.developers) b) g "
					+" on  dev.developer_name = g.name "
					+" )where developer_name = '"+args[0]+"'");
			
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
						builder.append("{\"Id\":\"");
						builder.append(rset_info.getString(1));
						builder.append("\",\"Name\":\"");
						builder.append(rset_info.getString(2));
						builder.append("\",\"Games\":[");
					}else{
						if(secondFlag){
							secondFlag = false;
							builder.append("{\"Asin\":\"");
							builder.append(rset_info.getString(5));
							builder.append("\",\"Title\":\"");
							builder.append(rset_info.getString(3));
							builder.append("\"}");
						}else{
							builder.append(",");
							builder.append("{\"Asin\":\"");
							builder.append(rset_info.getString(5));
							builder.append("\",\"Title\":\"");
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

