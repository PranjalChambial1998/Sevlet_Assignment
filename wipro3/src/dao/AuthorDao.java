package dao;

import java.sql.*;
import bean.*;
import util.*;


public class AuthorDao {
	public AuthorBean getAuthor(int authorCode) throws Exception{
		Connection con = DBUtil.getDBConnection();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("select * from Author_Tbl where Author_code=?");
			ps.setInt(1, authorCode);
			ResultSet rs = ps.executeQuery();
			AuthorBean bean = null;
			while(rs.next()){
				bean = new AuthorBean();
				bean.setAuthorCode(authorCode);
				bean.setAuthorName(rs.getString(2));
				bean.setContactNumber(rs.getLong(3));
			}
			return bean;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(NullPointerException e){
			System.out.println("error"+authorCode);
		}
		
		return null;
	}
	
	public AuthorBean getAuthor(String authorName) throws Exception{
		Connection con = DBUtil.getDBConnection();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("select * from Author_Tbl where Author_name=?");
			ps.setString(1, authorName);
			ResultSet rs = ps.executeQuery();
			AuthorBean bean = new AuthorBean();
			while(rs.next()){
				bean.setAuthorCode(rs.getInt(1));
				bean.setAuthorName(rs.getString(2));
				bean.setContactNumber(rs.getLong(3));
			}
			return bean;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
