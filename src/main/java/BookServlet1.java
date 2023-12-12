
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class BookServlet1
 */
@WebServlet("/BookServlet1")

public class BookServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public BookServlet1() {
        // TODO Auto-generated constructor stub
    }

    
    // Database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3307/bookstore";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "";
    
/**
 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
 */
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        // Establish a database connection
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            // Prepare a SQL query
            String sql = "SELECT * FROM books";
            PreparedStatement statement = conn.prepareStatement(sql);
            
            // Execute the query
            ResultSet resultSet = statement.executeQuery();
            
            // Process the query results
            while (resultSet.next()) {
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                // ... process other book fields
                
                // Output the book information
                response.getWriter().println("Title: " + title + ", Author: " + author);
            }
            
            // Clean up resources
            resultSet.close();
            statement.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error retrieving books from the database.");
        }
   }
    
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

//}




