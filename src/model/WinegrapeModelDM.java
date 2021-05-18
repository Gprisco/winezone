package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WinegrapeModelDM implements ProductModel<WinegrapeBean, Integer> {
	private static final String TABLE = "winegrape";
	private Connection conn;

	public WinegrapeModelDM(Connection conn) {
		this.conn = conn;
	}

	@Override
	public WinegrapeBean findByPk(Integer pk) throws SQLException {
		PreparedStatement stmt = null;
		WinegrapeBean winegrape = null;

		try {
			String sql = "SELECT * FROM " + TABLE + " WHERE winegrapeId = ?";

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, pk);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				winegrape = Helpers.createWinegrapeBean(rs);
			}
		} finally {
			if (stmt != null)
				stmt.close();
		}

		return winegrape;
	}

	@Override
	public Collection<WinegrapeBean> findAll(int limit, int offset) throws SQLException {
		PreparedStatement stmt = null;
		List<WinegrapeBean> winegrapes = new ArrayList<WinegrapeBean>();

		try {
			String sql = "SELECT * FROM " + TABLE + " ORDER BY winegrapeId DESC LIMIT ?";

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, limit);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				winegrapes.add(Helpers.createWinegrapeBean(rs));
			}
		} finally {
			if (stmt != null)
				stmt.close();
		}

		return winegrapes;
	}

	@Override
	public void create(WinegrapeBean winegrape) throws SQLException {
		PreparedStatement stmt = null;

		Integer insertedRows = null;

		try {
			String sql = "INSERT INTO " + TABLE + "(winegrape) VALUES (?)";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, winegrape.getWinegrape());

			insertedRows = stmt.executeUpdate();
		} finally {
			if (stmt != null)
				stmt.close();
		}

		System.out.println(insertedRows);
	}

	@Override
	public int update(WinegrapeBean winegrape) throws SQLException {
		PreparedStatement stmt = null;

		Integer updatedRows = null;

		try {
			String sql = "UPDATE " + TABLE + " SET winegrape = ? WHERE winegrapeId = ?";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, winegrape.getWinegrape());
			stmt.setInt(2, winegrape.getWinegrapeId());

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
			String sql = "DELETE FROM " + TABLE + " WHERE winegrapeId = ?";

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
