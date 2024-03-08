

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/fb")
public class Feedback_Ser extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Feedback_Ser() {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username="unknown";
		String phoneno = "unknown";
		String email="unknown";
		String text = "nothing ";
		username = request.getParameter("username");
		email = request.getParameter("email");
		phoneno = request.getParameter("number");
		text = request.getParameter("message");
		String url = "jdbc:sqlserver://vinaykuruva.database.windows.net:1433;" + "database=vinay;" + "user=vinay@vinaykuruva.database.windows.net;" + "password=Root@123;"+ "encrypt=true;"+ "trustServerCertificate=false;"+ "hostNameInCertificate=*.database.windows.net;"+ "loginTimeout=30;";		
		String user="vinay";
		String pwd="Root@123";
		Connection connection=null;
		@SuppressWarnings("unused")
		Statement statement = null;
		try {
			connection=DriverManager.getConnection(url,user,pwd);
			statement=connection.createStatement();
			PreparedStatement preparedStatement1=null;
			String query ="insert into vinay(sname,smail,snumber,smessage) values(?,?,?,?)";
			preparedStatement1=connection.prepareStatement(query);
				preparedStatement1.setString(1, username);
				preparedStatement1.setString(2, email);
				preparedStatement1.setString(3, phoneno);
				preparedStatement1.setString(4, text);
				preparedStatement1.executeUpdate();
				PrintWriter out = response.getWriter();
				out.println("thanks for feedback");
		}catch(SQLException se) {
			se.printStackTrace();
		}
	}
}
