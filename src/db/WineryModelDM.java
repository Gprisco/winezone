package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WineryModelDM extends Model implements ProductModel<WineryBean, Integer> {
	private static final String TABLE = "winery";

	@Override
	public WineryBean findByPk(Integer pk) {
		WineryBean winery = null;

		try {
			String sql = "SELECT * FROM " + TABLE + " WHERE wineryId = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, pk);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				winery = Helpers.createWineryBean(rs);
			}
		} catch (SQLException ex) {
			// handle any errors
			Helpers.handleSQLException(ex);
		}

		return winery;
	}

	@Override
	public Collection<WineryBean> findAll(int limit, int offset) {
		List<WineryBean> wineries = new ArrayList<WineryBean>();

		try {
			String sql = "SELECT * FROM " + TABLE + " ORDER BY wineryId DESC LIMIT ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, limit);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				wineries.add(Helpers.createWineryBean(rs));
			}
		} catch (SQLException ex) {
			// handle any errors
			Helpers.handleSQLException(ex);
		}

		return wineries;
	}

	@Override
	public void create(WineryBean winery) {
		Integer insertedRows = null;

		try {
			String sql = "INSERT INTO " + TABLE + " (winery, address, telephone) " + "VALUES (?, ?, ?)";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, winery.getWinery());
			stmt.setString(2, winery.getAddress());
			stmt.setString(3, winery.getTelephone());

			insertedRows = stmt.executeUpdate();
		} catch (SQLException ex) {
			// handle any errors
			Helpers.handleSQLException(ex);
		}

		System.out.println(insertedRows);
	}

	@Override
	public int update(WineryBean winery) {
		Integer updatedRows = null;

		try {
			String sql = "UPDATE " + TABLE + " SET " + "winery = ?, address = ?, telephone = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, winery.getWinery());
			stmt.setString(2, winery.getAddress());
			stmt.setString(3, winery.getTelephone());

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
			String sql = "DELETE FROM winery WHERE wineryId = ?";

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
