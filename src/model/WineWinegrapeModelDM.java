package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WineWinegrapeModelDM implements ProductModel<WineWinegrapeBean, WineWinegrapePrimaryKey> {
	private static final String TABLE = "wine_winegrape";
	private Connection conn;

	public WineWinegrapeModelDM(Connection conn) {
		this.conn = conn;
	}

	@Override
	public WineWinegrapeBean findByPk(WineWinegrapePrimaryKey pk) throws SQLException {
		PreparedStatement stmt = null;

		WineWinegrapeBean winegrape = null;

		try {
			String sql = "SELECT * FROM " + TABLE
					+ " INNER JOIN winegrape ON winegrape.winegrapeId = wine_winegrape.winegrapeId "
					+ "INNER JOIN wine ON wine.wine = wine_winegrape.wine AND wine.vintage = wine_winegrape.vintage "
					+ "WHERE winegrape.winegrapeId = ? AND winegrape.wine = ? AND winegrape.vintage = ?";

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, pk.getWinegrapeId());
			stmt.setString(2, pk.getWinePk().getWine());
			stmt.setInt(3, pk.getWinePk().getVintage());

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				winegrape = Helpers.createWineWinegrapeBean(rs);
			}
		} finally {
			if (stmt != null)
				stmt.close();
		}

		return winegrape;
	}

	@Override
	public Collection<WineWinegrapeBean> findAll(int limit, int offset) throws SQLException {
		PreparedStatement stmt = null;

		List<WineWinegrapeBean> winegrapes = new ArrayList<WineWinegrapeBean>();

		try {
			String sql = "SELECT * FROM " + TABLE
					+ " INNER JOIN winegrape ON winegrape.winegrapeId = wine_winegrape.winegrapeId "
					+ "INNER JOIN wine ON wine.wine = wine_winegrape.wine AND wine.vintage = wine_winegrape.vintage "
					+ "LIMIT ?, ?";

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, limit);
			stmt.setInt(2, offset);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				winegrapes.add(Helpers.createWineWinegrapeBean(rs));
			}
		} finally {
			if (stmt != null)
				stmt.close();
		}

		return winegrapes;
	}

	@Override
	public void create(WineWinegrapeBean wineWinegrape) throws SQLException {
		PreparedStatement stmt = null;

		Integer insertedRows = null;

		try {
			String sql = "INSERT INTO " + TABLE + " (wine, vintage, winegrapeId, percentage) " + "VALUES (?, ?, ?, ?)";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, wineWinegrape.getWine().getPk().getWine());
			stmt.setInt(2, wineWinegrape.getWine().getPk().getVintage());
			stmt.setInt(3, wineWinegrape.getWinegrape().getWinegrapeId());
			stmt.setInt(4, wineWinegrape.getPercentage());

			insertedRows = stmt.executeUpdate();
		} finally {
			if (stmt != null)
				stmt.close();
		}

		System.out.println(insertedRows);
	}

	@Override
	public int update(WineWinegrapeBean wineWinegrape) throws SQLException {
		PreparedStatement stmt = null;

		Integer updatedRows = null;

		try {
			String sql = "UPDATE " + TABLE + " SET percentage = ? WHERE winegrapeId = ? AND wine = ? AND vintage = ?";

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, wineWinegrape.getPercentage());
			stmt.setInt(2, wineWinegrape.getWinegrape().getWinegrapeId());
			stmt.setString(3, wineWinegrape.getWine().getPk().getWine());
			stmt.setInt(4, wineWinegrape.getWine().getPk().getVintage());

			updatedRows = stmt.executeUpdate();
		} finally {
			if (stmt != null)
				stmt.close();
		}

		return updatedRows;
	}

	@Override
	public int destroy(WineWinegrapePrimaryKey pk) throws SQLException {
		PreparedStatement stmt = null;

		Integer deletedRows = null;

		try {
			String sql = "DELETE FROM " + TABLE + " WHERE winegrapeId = ? AND wine = ? AND vintage = ?";

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, pk.getWinegrapeId());
			stmt.setString(2, pk.getWinePk().getWine());
			stmt.setInt(3, pk.getWinePk().getVintage());

			deletedRows = stmt.executeUpdate();
		} finally {
			if (stmt != null)
				stmt.close();
		}

		return deletedRows;
	}
}
