package model;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WineryModelDM implements ProductModel<WineryBean, Integer> {
	private static final String TABLE = "winery";
	private Connection conn;

	public WineryModelDM(Connection conn) {
		this.conn = conn;
	}

	@Override
	public WineryBean findByPk(Integer pk) throws SQLException {
		PreparedStatement stmt = null;

		WineryBean winery = null;

		try {
			String sql = "SELECT * FROM " + TABLE + " WHERE wineryId = ?";

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, pk);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				winery = Helpers.createWineryBean(rs);
			}
		} finally {
			if (stmt != null)
				stmt.close();
		}

		return winery;
	}

	@Override
	public Collection<WineryBean> findAll(int limit, int offset) throws SQLException {
		PreparedStatement stmt = null;

		List<WineryBean> wineries = new ArrayList<WineryBean>();

		try {
			String sql = "SELECT * FROM " + TABLE + " ORDER BY wineryId DESC LIMIT ?";

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, limit);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				wineries.add(Helpers.createWineryBean(rs));
			}
		} finally {
			if (stmt != null)
				stmt.close();
		}

		return wineries;
	}

	@Override
	public void create(WineryBean winery) throws SQLException {
		PreparedStatement stmt = null;

		Integer insertedRows = null;

		try {
			String sql = "INSERT INTO " + TABLE + " (winery, address, telephone) " + "VALUES (?, ?, ?)";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, winery.getWinery());
			stmt.setString(2, winery.getAddress());
			stmt.setString(3, winery.getTelephone());

			insertedRows = stmt.executeUpdate();
		} finally {
			if (stmt != null)
				stmt.close();
		}

		System.out.println(insertedRows);
	}

	@Override
	public int update(WineryBean winery) throws SQLException {
		PreparedStatement stmt = null;

		Integer updatedRows = null;

		try {
			String sql = "UPDATE " + TABLE + " SET " + "winery = ?, address = ?, telephone = ?";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, winery.getWinery());
			stmt.setString(2, winery.getAddress());
			stmt.setString(3, winery.getTelephone());

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
			String sql = "DELETE FROM winery WHERE wineryId = ?";

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
