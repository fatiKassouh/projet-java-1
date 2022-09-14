package net.task.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.http.*;


import net.task.dao.TaskDAO;
import net.task.dao.CategoryDAO;
import net.task.model.Task;
import net.task.model.Category;

/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 * @email Ramesh Fadatare
 */

@WebServlet("/")
public class TaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TaskDAO taskDao;
	private CategoryDAO  categoryDao = new CategoryDAO();

	public void init() {
		taskDao = new TaskDAO();
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/": 
                     showLoginForm(request, response);break;
			case "/Auth": System.out.println("switchh");
				SeConnceter(request, response);break;
			case "/registerForm": 
				   sinscrireForm(request, response); break;
			case "/register": 
				   sinscrire(request, response); break;
			case "/new": 
				showNewForm(request, response);
				break;
			case "/insert":
				insertUser(request, response);
				break;
			case "/delete":
				deleteUser(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateUser(request, response);
				break;
			case "/new-category":
				addCategories(request, response);
				break;
			case "/delete-category":
				deleteCategory(request, response);
				break;
			case "/category-form":
				AddForm(request, response);
				break;
			default:
				listUser(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Task> listUser = taskDao.selectAllTasks();
		request.setAttribute("listTask", listUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("task-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Category> categories = taskDao.getCategories();
		request.setAttribute("categories", categories);
		RequestDispatcher dispatcher = request.getRequestDispatcher("task-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		List<Category> categories = taskDao.getCategories();
		request.setAttribute("categories", categories);
		int id = Integer.parseInt(request.getParameter("id"));
		Task existingTask = taskDao.selectTask(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("task-form.jsp");
		request.setAttribute("user", existingTask);
		dispatcher.forward(request, response);

	}

	private void insertUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String status = request.getParameter("status");
		String date_limite = request.getParameter("date_limite");
		int categorie_id = Integer.parseInt(request.getParameter("categorie_id"));
		Task newTask = new Task(name, description, status,date_limite,categorie_id);
		taskDao.insertTask(newTask);
		response.sendRedirect("list");
	}

	private void updateUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String status = request.getParameter("status");
		String date_limite = request.getParameter("date_limite");
		int categorie_id =Integer.parseInt(request.getParameter("categorie_id"));
		Task book = new Task(name, description, status, date_limite,categorie_id);
		taskDao.updateTask(book);
		response.sendRedirect("list");
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		taskDao.deleteTask(id);
		response.sendRedirect("list");

	}
	
	private void showLoginForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("LoginForm.jsp");
		dispatcher.forward(request, response);
	}
	
	private void sinscrireForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("RegisterForm.jsp");
		dispatcher.forward(request, response);
	}
	
	private void SeConnceter(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		String role = request.getParameter("role");
		PrintWriter out = response.getWriter();
		response.setContentType("txt/html");
		try {
			Class.forName("com.mysql.jdbc.Driver") ;
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3308/todolist", "root", "") ;
			PreparedStatement ps = con.prepareStatement("select * from user where email = ? AND pwd = ? ") ;
			ps.setString(1,email);
			ps.setString(2,pwd);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				if(rs.getString(5).equals("user")) {
					RequestDispatcher rd = request.getRequestDispatcher("task-list.jsp");
					rd.forward(request, response);
					HttpSession session = request.getSession();
					session.setAttribute("login", rs.getString("email"));
					session.setAttribute("id", rs.getInt("id_user"));
				}else if(rs.getString(5).equals("admin")) {
			 		List<Category> listCategories = categoryDao.selectAllCategories();
			 		request.setAttribute("listCategories", listCategories);
					RequestDispatcher rd = request.getRequestDispatcher("category-list.jsp");
					rd.forward(request, response);
					System.out.println("adminnnnnnnnnnnnnnnn");

				}
			}else {
				out.println("Email ou mot de pass incorect!");
				RequestDispatcher rd = request.getRequestDispatcher("LoginForm.jsp");
				rd.include(request, response);
				response.sendRedirect("LoginForm.jsp");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
	
	protected void sinscrire(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("nom");
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		PrintWriter out = response.getWriter();
		out.print(name);
		out.print(email);
		response.setContentType("txt/html");
			try {
				Class.forName("com.mysql.jdbc.Driver") ;
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3308/todolist", "root", "") ;
			      // the mysql insert statement
			      String query = " insert into user (name, email, pwd,role)"
			        + " values (?, ?, ?, ?)";
			      // create the mysql insert preparedstatement
			      PreparedStatement preparedStmt = con.prepareStatement(query);
			      preparedStmt.setString (1, name);
			      preparedStmt.setString (2, email);
			      preparedStmt.setString (3, pwd);
			      preparedStmt.setString (4, "user");
			      // execute the preparedstatement
			      System.out.println(preparedStmt);
			      preparedStmt.executeUpdate();
			      out.println("Registered successfully !");
			      response.sendRedirect("LoginForm.jsp");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	public void AddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("Category-form.jsp");
		dispatcher.forward(request, response);
	}
     public void addCategories(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException, ServletException  {

 		String name = request.getParameter("name");
 		Category newCategory = new Category(name);
 		categoryDao.insertCategory(newCategory);
 		List<Category> listCategories = categoryDao.selectAllCategories();
 		request.setAttribute("listCategories", listCategories);
		RequestDispatcher rd = request.getRequestDispatcher("category-list.jsp");
		rd.forward(request, response);

     }

 	private void deleteCategory(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("id"));
		categoryDao.deleteCategory(id);
		System.out.println("heeeeeeeeeeeeeeere");
 		List<Category> listCategories = categoryDao.selectAllCategories();
 		request.setAttribute("listCategories", listCategories);
		RequestDispatcher rd = request.getRequestDispatcher("category-list.jsp");
		rd.forward(request, response);

	}
}
