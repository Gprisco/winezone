package model;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserModelDM implements ProductModel<UserBean, Integer> {
	private static final String TABLE = "users";
	Connection conn;

	public UserModelDM(Connection conn) {
		this.conn = conn;
	}

	public UserBean findOne(String email) throws SQLException {
		PreparedStatement stmt = null;

		UserBean user = null;

		try {
			String sql = "SELECT * FROM " + TABLE + " WHERE email = ?";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, email);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				user = Helpers.createUserBean(rs);
			}
		} finally {
			if (stmt != null)
				stmt.close();
		}

		return user;
	}

	@Override
	public UserBean findByPk(Integer pk) throws SQLException {
		UserBean user = null;

		PreparedStatement stmt = null;

		try {
			String sql = "SELECT * FROM " + TABLE + " WHERE id = ?";

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, pk);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				user = Helpers.createUserBean(rs);
			}
		} finally {
			if (stmt != null)
				stmt.close();
		}

		return user;
	}

	@Override
	public Collection<UserBean> findAll(int limit, int offset) throws SQLException {
		PreparedStatement stmt = null;

		List<UserBean> users = new ArrayList<UserBean>();

		try {
			String sql = "SELECT * FROM " + TABLE + " LIMIT ?, ?";

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, limit);
			stmt.setInt(2, offset);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				users.add(Helpers.createUserBean(rs));
			}
		} finally {
			if (stmt != null)
				stmt.close();
		}

		return users;
	}

	@Override
	public void create(UserBean user) throws SQLException {
		PreparedStatement stmt = null;

		Integer insertedRows = null;

		try {
			String sql = "INSERT INTO " + TABLE + " (email, password) " + "VALUES (?, ?)";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user.getEmail());
			stmt.setString(2, user.getPassword());

			insertedRows = stmt.executeUpdate();
		} finally {
			if (stmt != null)
				stmt.close();
		}

		System.out.println(insertedRows);
	}

	@Override
	public int update(UserBean user) throws SQLException {
		PreparedStatement stmt = null;

		Integer updatedRows = null;

		try {
			String sql = "UPDATE " + TABLE + " SET " + "email = ?, password = ? WHERE id = ?";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user.getEmail());
			stmt.setString(2, user.getPassword());
			stmt.setInt(3, user.getId());

			updatedRows = stmt.executeUpdate();
		} finally {
			if (stmt != null)
				stmt.close();
		}

		return updatedRows;
	}

	@Override
	public int destroy(Integer pk) throws SQLException {
		PreparedStatement stmt = null;

		Integer deletedRows = null;

		try {
			String sql = "DELETE FROM " + TABLE + " WHERE id = ?";

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, pk);

			deletedRows = stmt.executeUpdate();
		} finally {
			if (stmt != null)
				stmt.close();
		}

		return deletedRows;
	}
}
