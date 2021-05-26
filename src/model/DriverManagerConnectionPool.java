package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.TimeZone;

public class DriverManagerConnectionPool {

	private static List<Connection> freeDbConnections;

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("DB driver not found:" + e.getMessage());
		}
	}

	public DriverManagerConnectionPool() {
		freeDbConnections = new LinkedList<Connection>();
	}

	private synchronized Connection createDBConnection() throws SQLException {
		Connection newConnection = null;

		String ip = System.getenv("DB_IP");
		String port = System.getenv("DB_PORT");
		String db = System.getenv("DB_DB");
		String username = System.getenv("DB_USER");
		String password = System.getenv("DB_PASS");

		TimeZone timeZone = TimeZone.getTimeZone("Europe/Rome");
		TimeZone.setDefault(timeZone);

		newConnection = DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port + "/" + db, username, password);

		newConnection.setAutoCommit(true);

		return newConnection;
	}

	public synchronized Connection getConnection() throws SQLException {
		Connection connection;

		if (!freeDbConnections.isEmpty()) {
			connection = (Connection) freeDbConnections.get(0);
			freeDbConnections.remove(0);

			try {
				if (connection.isClosed())
					connection = getConnection();
			} catch (SQLException e) {
				connection.close();
				connection = getConnection();
			}
		} else {
			connection = createDBConnection();
		}

		return connection;
	}

	public synchronized void releaseConnection(Connection connection) throws SQLException {
		if (connection != null)
			freeDbConnections.add(connection);
	}
}
