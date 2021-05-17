package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Winefamily extends Model implements ProductModel<WinefamilyBean, Integer> {
	private static final String TABLE = "winefamily";

	@Override
	public WinefamilyBean findByPk(Integer pk) {
		WinefamilyBean winefamily = null;

		try {
			String sql = "SELECT * FROM " + TABLE + " WHERE winefamilyId = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, pk);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				winefamily = Helpers.createWineFamilyBean(rs);
			}
		} catch (SQLException ex) {
			// handle any errors
			Helpers.handleSQLException(ex);
		}

		return winefamily;
	}

	@Override
	public Collection<WinefamilyBean> findAll(int limit, int offset) {
		List<WinefamilyBean> winefamilies = new ArrayList<WinefamilyBean>();

		try {
			String sql = "SELECT * FROM " + TABLE + " ORDER BY winefamilyId DESC LIMIT ?, ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, limit);
			stmt.setInt(2, offset);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				winefamilies.add(Helpers.createWineFamilyBean(rs));
			}
		} catch (SQLException ex) {
			// handle any errors
			Helpers.handleSQLException(ex);
		}

		return winefamilies;
	}

	@Override
	public void create(WinefamilyBean winefamily) {
		Integer insertedRows = null;

		try {
			String sql = "INSERT INTO " + TABLE + " (winefamily, winecolorId, winetypeId, winedenomId, regionId) "
					+ "VALUES (?, ?, ?, ?, ?)";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, winefamily.getWinefamily());
			stmt.setInt(2, winefamily.getWinecolor().getValue());
			stmt.setInt(3, winefamily.getWinetype().getValue());
			stmt.setInt(4, winefamily.getWinedenom().getValue());
			stmt.setInt(5, winefamily.getRegionId());

			insertedRows = stmt.executeUpdate();
		} catch (SQLException ex) {
			// handle any errors
			Helpers.handleSQLException(ex);
		}

		System.out.println(insertedRows);
	}

	@Override
	public int update(WinefamilyBean winefamily) {
		Integer updatedRows = null;

		try {
			String sql = "UPDATE " + TABLE + " SET "
					+ "winefamily = ?, winecolorId = ?, winetypeId = ?, winedenomId = ?, regionId = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, winefamily.getWinefamily());
			stmt.setInt(2, winefamily.getWinecolor().getValue());
			stmt.setInt(3, winefamily.getWinetype().getValue());
			stmt.setInt(4, winefamily.getWinedenom().getValue());
			stmt.setInt(5, winefamily.getRegionId());

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
			String sql = "DELETE FROM " + TABLE + " WHERE winefamilyId = ?";

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
