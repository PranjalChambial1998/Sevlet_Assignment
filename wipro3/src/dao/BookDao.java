package dao;

import java.sql.*;
import bean.*;
import util.*;

public class BookDao {
	public BookBean fetchBook(String ISBN) throws Exception{
		Connection con = DBUtil.getDBConnection();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("select * from Book_Tbl where ISBN=?");
			ps.setString(1, ISBN);
			ResultSet rs = ps.executeQuery();
			BookBean bean =null;
			while(rs.next()){
				bean = new BookBean();
				bean.setIsbn(ISBN);
				bean.setBookName(rs.getString(2));
				bean.setBookType(rs.getString(3).charAt(0));
				bean.setAuthor( new AuthorDao().getAuthor(rs.getInt(4)));
				bean.setCost(rs.getFloat(5));
			}
			return bean;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(NullPointerException e){
			System.out.println("error"+ISBN);
			
		}
		
		return null;
	}
	
	public int createBook(BookBean book) throws Exception{
		Connection con = DBUtil.getDBConnection();
		try {
			PreparedStatement ps = con.prepareStatement("insert into Book_Tbl values(?,?,?,?,?)");
			ps.setString(1, book.getIsbn());
			ps.setString(2, book.getBookName());
			ps.setString(3,((Character)book.getBookType()).toString());
			ps.setInt(4, book.getAuthor().getAuthorCode());
			ps.setFloat(5, book.getCost());
			ps.execute();
			return 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		
		
	}

}
