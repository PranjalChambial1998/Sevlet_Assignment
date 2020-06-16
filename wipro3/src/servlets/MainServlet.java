package servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import bean.*;
import dao.*;
import service.*;

public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		res.getWriter().append("Served at: ").append(req.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String operation = req.getParameter("operation");
		if(operation.equals("AddBook")){
			String result = null;
			try {
				result = addBook(req);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(result.equals("SUCCESS")){
				RequestDispatcher rd = req.getRequestDispatcher("Menu.html");
				rd.include(req, res);  
			}
			else if(result.equals("FAILURE")){
				RequestDispatcher rd = req.getRequestDispatcher("Failure.html");
				rd.include(req, res); 
			}
			else{
				RequestDispatcher rd = req.getRequestDispatcher("Invalid.html");
				rd.include(req, res); 
			}
		}
		if(operation.equals("Search")){
			BookBean bean = null;
			try {
				bean = viewBook(req.getParameter("isbn"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(bean==null){
				RequestDispatcher rd = req.getRequestDispatcher("Invalid.html");
				rd.include(req, res);
				return;
			}
		    res.setContentType("text/html");
		    PrintWriter out = res.getWriter();
		    out.println("<!DOCTYPE html><html><head><title>View Book</title></head><body><table>");
		    String type="";
		    if(bean.getBookType()=='T')
		    	type = "Technical";
		    else if(bean.getBookType()=='G')
		    	type = "General";
		    else{
				RequestDispatcher rd = req.getRequestDispatcher("Failure.html");
				rd.forward(req, res);
				return;
		    }
		    
		    out.println("<tr><td>ISBN<td>"+bean.getIsbn()+"</tr>");
		    out.println("<tr><td>Book Name<td>"+bean.getBookName()+"</tr>");
		    out.println("<tr><td>Book Type<td>"+type+"</tr>");
		    out.println("<tr><td>Author Name<td>"+bean.getAuthor().getAuthorName()+"</tr>");
		    out.println("<tr><td>Contact<td>"+bean.getAuthor().getContactNumber()+"</tr>");
		    out.println("<tr><td>Cost<td>"+bean.getCost()+"</tr></table>");
		    out.println("</body></html>");
		    out.close();
		    
		}
	}
	
	public String addBook(HttpServletRequest request) throws NumberFormatException, Exception{
		Administrator admin = new Administrator();
		BookBean bean = new BookBean();
		bean.setIsbn(request.getParameter("isbn"));
		bean.setBookName(request.getParameter("bookname"));
		bean.setBookType(request.getParameter("booktype").charAt(0));
		bean.setAuthor(( new AuthorDao()).getAuthor(Integer.parseInt(request.getParameter("author")) ));
		bean.setCost(Float.parseFloat(request.getParameter("cost")));
		return admin.addBook(bean);
	}
	
	
	public BookBean viewBook(String isbn) throws Exception{
		return new Administrator().viewBook(isbn);
	}
	
}
