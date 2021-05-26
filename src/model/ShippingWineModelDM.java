package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class ShippingWineModelDM implements ProductModel<ShippingWineBean, Integer> {
	private static final String TABLE = "shipping_wine";
	private Connection conn;

	public ShippingWineModelDM(Connection conn) {
		this.conn = conn;
	}

	@Override
	public ShippingWineBean findByPk(Integer pk) throws SQLException {
		ShippingWineBean shippingBean = null;

		PreparedStatement stmt = null;

		try {
			String sql = "SELECT * FROM " + TABLE + " WHERE id = ?";

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, pk);

			ResultSet rs = stmt.executeQuery();

			while (rs.next())
				shippingBean = Helpers.createShippingWineBean(rs);
		} finally {
			if (stmt != null)
				stmt.close();
		}

		return shippingBean;
	}

	@Override
	public Collection<ShippingWineBean> findAll(int limit, int offset) throws SQLException {
		Collection<ShippingWineBean> shippingBeans = new ArrayList<ShippingWineBean>();

		PreparedStatement stmt = null;

		try {
			String sql = "SELECT * FROM " + TABLE + " LIMIT ?, ? ORDER BY idShipping DESC";

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, limit);
			stmt.setInt(2, offset);

			ResultSet rs = stmt.executeQuery();

			while (rs.next())
				shippingBeans.add(Helpers.createShippingWineBean(rs));
		} finally {
			if (stmt != null)
				stmt.close();
		}

		return shippingBeans;
	}

	@Override
	public void create(ShippingWineBean product) throws SQLException {
		Integer insertedRows = null;

		PreparedStatement stmt = null;

		try {
			String sql = "INSERT INTO " + TABLE + " (wine, vintage, idShipping) VALUES (?, ?, ?)";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, product.getWine());
			stmt.setInt(2, product.getVintage());
			stmt.setInt(3, product.getIdShipping());

			insertedRows = stmt.executeUpdate();
		} finally {
			if (stmt != null)
				stmt.close();
		}

		System.out.println(insertedRows);
	}

	@Override
	public int update(ShippingWineBean product) throws SQLException {
		PreparedStatement stmt = null;

		Integer updatedRows = null;

		try {
			String sql = "UPDATE " + TABLE + " SET " + "wine = ?, vintage = ?, idShipping = ? WHERE id = ?";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, product.getWine());
			stmt.setInt(2, product.getVintage());
			stmt.setInt(3, product.getVintage());
			stmt.setInt(4, product.getId());

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
