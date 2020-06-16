package service;

import bean.*;
import dao.*;

public class Administrator {
	
	public String addBook(BookBean bean) throws Exception{
		if(bean==null||bean.getBookName().equals("")||bean.getIsbn().equals("")||bean.getBookType()!='T'&&bean.getBookType()!='G'||bean.getCost()<=0||bean.getAuthor().getAuthorName()==null||bean.getAuthor().getAuthorName().equals("")){
			return "INVALID";
		}
		else {
		int res = new BookDao().createBook(bean);
		if(res == 1)
			return "SUCCESS";
		else
			return "FAILURE";
		}
	}
	
	
	public BookBean viewBook(String isbn) throws Exception{
		if(isbn.equals(""))
			return null;
		return new BookDao().fetchBook(isbn);
	}

}
