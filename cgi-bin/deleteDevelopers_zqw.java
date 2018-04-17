// Import the following packages to use JDBC.
import  java.sql.*;
import  java.io.*;
import  oracle.jdbc.*;
import  oracle.jdbc.pool.OracleDataSource;

class  deleteDevelopers_zqw {
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
			Statement stmtdel = conn.createStatement();
			
			
			String[] idsArr = args[0].toString().split("-");
			String[] namesArr = args[1].toString().split("-");
			//System.out.println("1");//test
			
			StringBuilder query = new StringBuilder();
			query.append("delete from developers where id in( ");
			
			for(int i=0;i<idsArr.length;i++){
				if(i!=0){
					query.append(", ");
				}
				query.append(idsArr[i]);
				
			}
			
			query.append(")");
			
			
			stmt.executeUpdate(query.toString());
			
			//删除game_zqw表里的developers
			for(String item : namesArr){
				
				StringBuilder querySelect = new StringBuilder();
				querySelect.append("select g.title,d.* from game_zqw g ,table(g.developers) d where name = '"+item+"'");
				
				ResultSet rset_info = stmt.executeQuery(querySelect.toString());
			
				System.out.println(querySelect.toString()+";;;");
				
				while(rset_info.next())
				{
					StringBuilder queryDelete = new StringBuilder();
					queryDelete.append("delete from table(select developers from game_zqw where title = '"+rset_info.getString(1)+"' and rownum=1) where name='"+item+"' ");
					
					//queryDelete.append("delete from developers where id in(1) ");
					
					System.out.println(queryDelete.toString()+";;;");
					
					stmtdel.executeUpdate(queryDelete.toString());
				}
				
			
			}
			
			System.out.println(query.toString()+";;;");
			
			stmt.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		conn.close();
	}
}

