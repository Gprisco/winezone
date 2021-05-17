package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Winegrape extends Model implements ProductModel<WinegrapeBean, Integer> {
	private static final String TABLE = "winegrape";

	@Override
	public WinegrapeBean findByPk(Integer pk) {
		WinegrapeBean winegrape = null;

		try {
			String sql = "SELECT * FROM " + TABLE + " WHERE winegrapeId = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, pk);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				winegrape = Helpers.createWinegrapeBean(rs);
			}
		} catch (SQLException ex) {
			// handle any errors
			Helpers.handleSQLException(ex);
		}

		return winegrape;
	}

	@Override
	public Collection<WinegrapeBean> findAll(int limit, int offset) {
		List<WinegrapeBean> winegrapes = new ArrayList<WinegrapeBean>();

		try {
			String sql = "SELECT * FROM " + TABLE + " ORDER BY winegrapeId DESC LIMIT ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, limit);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				winegrapes.add(Helpers.createWinegrapeBean(rs));
			}
		} catch (SQLException ex) {
			// handle any errors
			Helpers.handleSQLException(ex);
		}

		return winegrapes;
	}

	@Override
	public void create(WinegrapeBean winegrape) {
		Integer insertedRows = null;

		try {
			String sql = "INSERT INTO " + TABLE + "(winegrape) " + "VALUES (?)";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, winegrape.getWinegrape());

			insertedRows = stmt.executeUpdate();
		} catch (SQLException ex) {
			// handle any errors
			Helpers.handleSQLException(ex);
		}

		System.out.println(insertedRows);
	}

	@Override
	public int update(WinegrapeBean winegrape) {
		Integer updatedRows = null;

		try {
			String sql = "UPDATE " + TABLE + " SET " + "winegrape = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, winegrape.getWinegrape());

			updatedRows = stmt.executeUpdate();
		} catch (SQLException ex) {
			// handle any errors
			return Helpers.handleSQLException(ex);
		}

		return updatedRows;
	}

	@Override
	public int destroy(Integer pk) {
		Integer deletedRows = null;

		try {
			String sql = "DELETE FROM " + TABLE + " WHERE winegrapeId = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, pk);

			deletedRows = stmt.executeUpdate();
		} catch (SQLException ex) {
			// handle any errors
			return Helpers.handleSQLException(ex);
		}

		return deletedRows;
	}
}
