package net.task.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import net.task.model.Task;
import net.task.model.Category;
import jakarta.servlet.http.HttpSession;

public class TaskDAO {
	
	private String jdbcURL = "jdbc:mysql://localhost:3308/todolist";
	private String jdbcUsername = "root";
	private String jdbcPassword = "";

	private static final String INSERT_TASK_SQL = "INSERT INTO task" + "  (name, description, status, date_limite, categorie_id) VALUES "
			+ " (?, ?, ?, ?, ?);";
	private static final String SELECT_TASK_BY_ID = "select * from task where id =?";
	private static final String SELECT_ALL_TASKS = "select * from task t, categorie c where t.categorie_id=c.id";
	private static final String SELECT_ALL_CATEGORIES = "select * from categorie";
	private static final String DELETE_TASK_SQL = "delete from task where id = ?;";
	private static final String UPDATE_TASK_SQL = "update task set name = ?,description= ?, status =?,date_limite= ?,  categorie_id=? where id = ?;";
	
	public TaskDAO() {
	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	
	public void insertTask(Task task) throws SQLException {
		System.out.println(INSERT_TASK_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TASK_SQL)) {
			preparedStatement.setString(1, task.getName());
			preparedStatement.setString(2, task.getDescription());
			preparedStatement.setString(3, task.getStatus());
			preparedStatement.setString(4, task.getDate_limite());
			preparedStatement.setInt(5, task.getCategorie_id());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
	
	public Task selectTask(int id) {
		Task task = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TASK_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int idi = rs.getInt("id");
				String name = rs.getString("name");
				String description = rs.getString("description");
				String status = rs.getString("status");
				String date_limite = rs.getString("date_limite");
				int categorie_id = rs.getInt("categorie_id");
				task = new Task(idi,name, description, status,date_limite,categorie_id);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return task;
	}
	
	public List<Task> selectAllTasks() {
		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Task> tasks = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TASKS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String description = rs.getString("description");
				String status = rs.getString("status");
				String date_limite = rs.getString("date_limite");
				int categorie_id = rs.getInt("categorie_id");
				tasks.add(new Task(id,name, description, status, date_limite,categorie_id));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return tasks;
	}
	
	public boolean deleteTask(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_TASK_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateTask(Task task) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_TASK_SQL);) {
			statement.setString(1, task.getName());
			statement.setString(2, task.getDescription());
			statement.setString(3, task.getStatus());
			statement.setString(4, task.getDate_limite());
			statement.setInt(5, task.getCategorie_id());
			statement.setInt(6, task.getId());
			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
	
	public List<Category> getCategories() {
		// using try-with-resources to avoid closing resources (boiler plate code)
				List<Category> categories = new ArrayList<>();
				// Step 1: Establishing a Connection
				try (Connection connection = getConnection();

						// Step 2:Create a statement using connection object
					PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CATEGORIES);) {
					System.out.println(preparedStatement);
					// Step 3: Execute the query or update query
					ResultSet rs = preparedStatement.executeQuery();

					// Step 4: Process the ResultSet object.
					while (rs.next()) {
						int id = rs.getInt("id");
						String name = rs.getString("name");
						categories.add(new Category(id,name));
					}
				} catch (SQLException e) {
					printSQLException(e);
				}
				return categories;
		
	}
}
