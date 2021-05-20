package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WinefamilyModelDM implements ProductModel<WinefamilyBean, Integer> {
	private static final String TABLE = "winefamily";
	private Connection conn;

	public WinefamilyModelDM(Connection conn) {
		this.conn = conn;
	}

	@Override
	public WinefamilyBean findByPk(Integer pk) throws SQLException {
		PreparedStatement stmt = null;

		WinefamilyBean winefamily = null;

		try {
			String sql = "SELECT * FROM " + TABLE + " WHERE winefamilyId = ?";

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, pk);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				winefamily = Helpers.createWineFamilyBean(rs);
			}
		} finally {
			if (stmt != null)
				stmt.close();
		}

		return winefamily;
	}

	@Override
	public Collection<WinefamilyBean> findAll(int limit, int offset) throws SQLException {
		PreparedStatement stmt = null;

		List<WinefamilyBean> winefamilies = new ArrayList<WinefamilyBean>();

		try {
			String sql = "SELECT * FROM " + TABLE + " ORDER BY winefamilyId DESC LIMIT ?, ?";

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, limit);
			stmt.setInt(2, offset);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				winefamilies.add(Helpers.createWineFamilyBean(rs));
			}
		} finally {
			if (stmt != null)
				stmt.close();
		}

		return winefamilies;
	}

	@Override
	public void create(WinefamilyBean winefamily) throws SQLException {
		PreparedStatement stmt = null;

		Integer insertedRows = null;

		try {
			String sql = "INSERT INTO " + TABLE + " (winefamily, winecolorId, winetypeId, winedenomId, regionId) "
					+ "VALUES (?, ?, ?, ?, ?)";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, winefamily.getWinefamily());
			stmt.setInt(2, winefamily.getWinecolor().getValue());
			stmt.setInt(3, winefamily.getWinetype().getValue());
			stmt.setInt(4, winefamily.getWinedenom().getValue());
			stmt.setInt(5, winefamily.getRegionId());

			insertedRows = stmt.executeUpdate();
		} finally {
			if (stmt != null)
				stmt.close();
		}

		System.out.println(insertedRows);
	}

	@Override
	public int update(WinefamilyBean winefamily) throws SQLException {
		PreparedStatement stmt = null;

		Integer updatedRows = null;

		try {
			String sql = "UPDATE " + TABLE + " SET "
					+ "winefamily = ?, winecolorId = ?, winetypeId = ?, winedenomId = ?, regionId = ? WHERE winefamilyId = ?";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, winefamily.getWinefamily());
			stmt.setInt(2, winefamily.getWinecolor().getValue());
			stmt.setInt(3, winefamily.getWinetype().getValue());
			stmt.setInt(4, winefamily.getWinedenom().getValue());
			stmt.setInt(5, winefamily.getRegionId());
			stmt.setInt(6, winefamily.getWinefamilyId());

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
			String sql = "DELETE FROM " + TABLE + " WHERE winefamilyId = ?";

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
