package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;

public class WineModelDM extends Model implements ProductModel<WineBean, WinePrimaryKey> {
	private static final String TABLE = "wine";

	private static String wineQuery = "SELECT * FROM " + TABLE
			+ " INNER JOIN winery ON winery.wineryId = wine.wineryId "
			+ "INNER JOIN winefamily ON winefamily.winefamilyId = wine.winefamilyId "
			+ "INNER JOIN winecolor ON winecolor.winecolorId = winefamily.winecolorId "
			+ "INNER JOIN winetype ON winetype.winetypeId = winefamily.winetypeId "
			+ "INNER JOIN winedenom ON winedenom.winedenomId = winefamily.winedenomId "
			+ "INNER JOIN region ON region.regionId = winefamily.regionId "
			+ "INNER JOIN country ON region.countryId = country.countryId "
			+ "LEFT JOIN wine_winegrape ON wine_winegrape.wine = wine.wine AND wine_winegrape.vintage = wine.vintage "
			+ "LEFT JOIN winegrape ON wine_winegrape.winegrapeId = winegrape.winegrapeId ";

	@Override
	public Collection<WineBean> findAll(int limit, int offset) {
		Collection<WineBean> wines = new ArrayList<WineBean>();

		try {
			String sql = wineQuery + "LIMIT ?, ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, limit);
			stmt.setInt(2, offset);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				WineBean wineBean = Helpers.createWineBean(rs);
				wines.add(wineBean);
			}
		} catch (SQLException ex) {
			// handle any errors
			Helpers.handleSQLException(ex);
		}

		return wines;
	}

	@Override
	public WineBean findByPk(WinePrimaryKey pk) {
		WineBean wine = null;

		try {
			// Faccio un'intersezione con tutte le tabell tranne con winegrape con cui
			// faccio un left join al fine di avere comunque tutti i dati del vino anche se
			// non ha l'uvaggio registrato
			String sql = wineQuery + "WHERE wine.wine = ? AND wine.vintage = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, pk.getWine().toLowerCase());
			stmt.setInt(2, pk.getVintage());

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				wine = Helpers.createWineBean(rs);
			}
		} catch (SQLException ex) {
			// handle any errors
			Helpers.handleSQLException(ex);
		}

		return wine;
	}

	@Override
	public void create(WineBean wine) {
		Integer insertedRows = null;

		try {
			String sql = "INSERT INTO " + TABLE + " (wine, vintage, availability, price, wineryId, winefamilyId) "
					+ "VALUES (?, ?, ?, ?, ?, ?)";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, wine.getPk().getWine());
			stmt.setInt(2, wine.getPk().getVintage());
			stmt.setInt(3, wine.getAvailability());
			stmt.setInt(4, wine.getPrice());
			stmt.setInt(5, wine.getWineryId());
			stmt.setInt(6, wine.getWinefamilyId());

			insertedRows = stmt.executeUpdate();
		} catch (SQLException ex) {
			// handle any errors
			Helpers.handleSQLException(ex);
		}

		System.out.println(insertedRows);
	}

	@Override
	public int destroy(WinePrimaryKey pk) {
		Integer updatedRows = null;

		try {
			String sql = "DELETE FROM " + TABLE + " WHERE wine = ? AND vintage = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, pk.getWine());
			stmt.setInt(2, pk.getVintage());

			updatedRows = stmt.executeUpdate();
		} catch (SQLException ex) {
			// handle any errors
			return Helpers.handleSQLException(ex);
		}

		return updatedRows;
	}

	@Override
	public int update(WineBean wine) {
		Integer updatedRows = null;

		try {
			String sql = "UPDATE " + TABLE + " SET " + "price = ?, " + "availability = ?, " + "wineryId = ?, "
					+ "winefamilyId = ?, " + "WHERE wine = ? AND vintage = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setFloat(1, wine.getPrice());
			stmt.setInt(2, wine.getAvailability());
			stmt.setInt(3, wine.getWineryId());
			stmt.setInt(4, wine.getWinefamilyId());
			stmt.setString(5, wine.getPk().getWine());
			stmt.setInt(6, wine.getPk().getVintage());

			updatedRows = stmt.executeUpdate();
		} catch (SQLException ex) {
			// handle any errors
			return Helpers.handleSQLException(ex);
		}

		return updatedRows;
	}
}
