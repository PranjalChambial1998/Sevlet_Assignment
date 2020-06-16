package util;

import java.sql.*;

public class DBUtil {

	private static Connection con = null;

	public static Connection getDBConnection() throws Exception {
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		String user="system";
		String password="412589";
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection(url, user, password);
			
			Statement st=con.createStatement();
			//st.executeQuery("drop table  Book_Tbl");
			//st.executeQuery("drop table  Author_Tbl");
			//st.executeQuery("create table Author_Tbl (Author_code number(5) primary key,Author_name varchar2(20) not null,Contact_no number(10))");
			//st.executeQuery("create table Book_Tbl (ISBN varchar2(10) primary key,Book_title varchar2(20) not null,Book_type char(1),Author_code number(5) not null,Book_cost number(15,2) not null,constraint bk_ck check(Book_type='T' or Book_type='G'),constraint au_fk foreign key(Author_code) references Author_Tbl (Author_code))");
			//st.executeQuery("insert into Author_Tbl values (1,'RobinSharma',8800799224)");
			//st.executeQuery("insert into Author_Tbl values (2,'R.K.Narayan',8800799255)");
			//st.executeQuery("insert into Author_Tbl values (3,'Paulo',8800799775)");
			ResultSet rs=st.executeQuery("select Author_name from Author_Tbl");
			while(rs!=null&&rs.next())
				System.out.println(rs.getString(1));
		}
		catch(ClassNotFoundException e){System.out.println("Driver Not Found");}
		catch(SQLException e) {System.out.println("Connection Failed");e.printStackTrace();}
		return con;
		
	}
}
