package com.srs.develop;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletInsert
 */
@WebServlet("/ServletInsert")
public class ServletInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ServletInsert() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
//		 doGet(request, response);
		Connection con=null;
		PreparedStatement pstmt=null;
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		//System.out.println("done");
		String insertEmpQuery="insert into Emp_Details values(?,?,?,?,?)";
		
		int id=Integer.parseInt(request.getParameter("id"));
		String name=request.getParameter("name");
		String password=request.getParameter("pass");
		String email=request.getParameter("email");
		String country=request.getParameter("country");
		
		try{
			//System.out.println("entered try");
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost/mary","root","root");
			pstmt = con.prepareStatement(insertEmpQuery);
			pstmt.setInt(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, password);
			pstmt.setString(4, email);
			pstmt.setString(5, country);
			int res=pstmt.executeUpdate();
			if(res>0){
				//System.out.println("Entered if");
				response.sendRedirect("Main.html");
				
			} else{
				out.println("<html><body bgcolor='yellow'>"+"<h2>Welcome"+name+" your record not inserted</h2>"+"</body></html>");
			}
		out.flush();
		out.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
				try{
					if(pstmt!=null && con!=null){
						pstmt.close();
						con.close();
				}

			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

}
