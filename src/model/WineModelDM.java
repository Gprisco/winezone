package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;

public class WineModelDM implements ProductModel<WineBean, WinePrimaryKey> {
	private static final String TABLE = "wine";
	private Connection conn;

	public WineModelDM(Connection conn) {
		this.conn = conn;
	}

	private static String wineQuery(String select) {
		return "SELECT " + select + " FROM " + TABLE + " INNER JOIN winery ON winery.wineryId = wine.wineryId "
				+ "INNER JOIN winefamily ON winefamily.winefamilyId = wine.winefamilyId "
				+ "INNER JOIN winecolor ON winecolor.winecolorId = winefamily.winecolorId "
				+ "INNER JOIN winetype ON winetype.winetypeId = winefamily.winetypeId "
				+ "INNER JOIN winedenom ON winedenom.winedenomId = winefamily.winedenomId "
				+ "INNER JOIN region ON region.regionId = winefamily.regionId "
				+ "INNER JOIN country ON region.countryId = country.countryId "
				+ "LEFT JOIN wine_winegrape ON wine_winegrape.wine = wine.wine AND wine_winegrape.vintage = wine.vintage "
				+ "LEFT JOIN winegrape ON wine_winegrape.winegrapeId = winegrape.winegrapeId ";
	}

	private static String searchWineWhere = "WHERE wine.wine LIKE ? OR winefamily LIKE ? OR winery LIKE ? OR winecolor LIKE ? OR winetype = ? OR winegrape = ?";

	public int count() throws SQLException {
		PreparedStatement stmt = null;

		int wines = 0;

		try {
			String sql = "SELECT COUNT(*) FROM " + TABLE;

			stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next())
				wines = rs.getInt("COUNT(*)");
		} finally {
			if (stmt != null)
				stmt.close();
		}

		return wines;
	}

	public int countWineSearch(String text) throws SQLException {
		String count = "COUNT(CONCAT(wine.wine, wine.vintage))";
		
		PreparedStatement stmt = null;

		int wines = 0;

		try {
			String sql = wineQuery(count) + searchWineWhere;

			stmt = conn.prepareStatement(sql);

			for (int i = 1; i <= 6; i++)
				stmt.setString(i, "%" + text + "%");

			ResultSet rs = stmt.executeQuery();

			while (rs.next())
				wines = rs.getInt(count);
		} finally {
			if (stmt != null)
				stmt.close();
		}

		return wines;
	}

	public Collection<WineBean> searchForWines(String text, int limit, int offset) throws SQLException {
		PreparedStatement stmt = null;

		ArrayList<WineBean> wines = new ArrayList<WineBean>();

		try {
			String sql = wineQuery("*") + searchWineWhere + " LIMIT ?, ?";

			stmt = conn.prepareStatement(sql);

			for (int i = 1; i <= 6; i++)
				stmt.setString(i, "%" + text + "%");
			stmt.setInt(7, limit);
			stmt.setInt(8, offset);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				WineBean wineBean = Helpers.createWineBean(rs);

				if (wines.contains(wineBean)) {
					int indexOfExisting = wines.indexOf(wineBean);
					WineBean existing = wines.get(indexOfExisting);
					existing.addWinegrape(Helpers.createWineWinegrapeBean(rs));

					wines.set(indexOfExisting, existing);
				} else
					wines.add(wineBean);
			}
		} finally {
			if (stmt != null)
				stmt.close();
		}

		return wines;
	}

	@Override
	public Collection<WineBean> findAll(int limit, int offset) throws SQLException {
		PreparedStatement stmt = null;

		ArrayList<WineBean> wines = new ArrayList<WineBean>();

		try {
			String sql = wineQuery("*") + "LIMIT ?, ?";

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, limit);
			stmt.setInt(2, offset);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				WineBean wineBean = Helpers.createWineBean(rs);
				if (wines.contains(wineBean)) {
					int indexOfExisting = wines.indexOf(wineBean);
					WineBean existing = wines.get(indexOfExisting);
					existing.addWinegrape(Helpers.createWineWinegrapeBean(rs));

					wines.set(indexOfExisting, existing);
				} else
					wines.add(wineBean);
			}
		} finally {
			if (stmt != null)
				stmt.close();
		}

		return wines;
	}

	@Override
	public WineBean findByPk(WinePrimaryKey pk) throws SQLException {
		PreparedStatement stmt = null;

		WineBean wine = null;

		try {
			// Faccio un'intersezione con tutte le tabelle tranne con winegrape con cui
			// faccio un left join al fine di avere comunque tutti i dati del vino anche se
			// non ha l'uvaggio registrato
			String sql = wineQuery("*") + "WHERE wine.wine = ? AND wine.vintage = ?";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, pk.getWine().toLowerCase());
			stmt.setInt(2, pk.getVintage());

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				wine = wine == null ? Helpers.createWineBean(rs) : wine;
				wine.addWinegrape(Helpers.createWineWinegrapeBean(rs));
			}
		} finally {
			if (stmt != null)
				stmt.close();
		}

		return wine;
	}

	@Override
	public void create(WineBean wine) throws SQLException {
		PreparedStatement stmt = null;

		Integer insertedRows = null;

		try {
			String sql = "INSERT INTO " + TABLE + " (wine, vintage, availability, price, wineryId, winefamilyId) "
					+ "VALUES (?, ?, ?, ?, ?, ?)";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, wine.getPk().getWine());
			stmt.setInt(2, wine.getPk().getVintage());
			stmt.setInt(3, wine.getAvailability());
			stmt.setInt(4, wine.getPrice());
			stmt.setInt(5, wine.getWineryId());
			stmt.setInt(6, wine.getWinefamilyId());

			insertedRows = stmt.executeUpdate();
		} finally {
			if (stmt != null)
				stmt.close();
		}

		System.out.println(insertedRows);
	}

	@Override
	public int destroy(WinePrimaryKey pk) throws SQLException {
		PreparedStatement stmt = null;

		Integer updatedRows = null;

		try {
			String sql = "DELETE FROM " + TABLE + " WHERE wine = ? AND vintage = ?";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, pk.getWine());
			stmt.setInt(2, pk.getVintage());

			updatedRows = stmt.executeUpdate();
		} finally {
			if (stmt != null)
				stmt.close();
		}

		return updatedRows;
	}

	@Override
	public int update(WineBean wine) throws SQLException {
		PreparedStatement stmt = null;

		Integer updatedRows = null;

		try {
			String sql = "UPDATE " + TABLE + " SET " + "price = ?, " + "availability = ?, " + "wineryId = ?, "
					+ "winefamilyId = ?, " + "WHERE wine = ? AND vintage = ?";

			stmt = conn.prepareStatement(sql);
			stmt.setFloat(1, wine.getPrice());
			stmt.setInt(2, wine.getAvailability());
			stmt.setInt(3, wine.getWineryId());
			stmt.setInt(4, wine.getWinefamilyId());
			stmt.setString(5, wine.getPk().getWine());
			stmt.setInt(6, wine.getPk().getVintage());

			updatedRows = stmt.executeUpdate();
		} finally {
			if (stmt != null)
				stmt.close();
		}

		return updatedRows;
	}
}
