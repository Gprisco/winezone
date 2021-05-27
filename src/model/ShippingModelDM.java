package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.ArrayList;

public class ShippingModelDM implements ProductModel<ShippingBean, Integer> {
	private static final String TABLE = "shipping";
	private static final String leftJoin = " LEFT JOIN shipping_wine ON shipping_wine.idShipping = " + TABLE + ".id";

	private int lastCreatedId;

	private Connection conn;

	public ShippingModelDM(Connection conn) {
		this.conn = conn;
	}

	public int getLastCreatedId() {
		return lastCreatedId;
	}

	public int count() throws SQLException {
		int count = 0;
		PreparedStatement stmt = null;

		try {
			String sql = "SELECT COUNT(*) FROM " + TABLE;

			stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				count = rs.getInt("COUNT(*)");
			}
		} finally {
			if (stmt != null)
				stmt.close();
		}

		return count;

	}

	@Override
	public ShippingBean findByPk(Integer pk) throws SQLException {
		ShippingBean shippingBean = null;

		PreparedStatement stmt = null;

		try {
			String sql = "SELECT * FROM " + TABLE + leftJoin + " WHERE shipping.id = ? ";

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, pk);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				shippingBean = shippingBean == null ? Helpers.createShippingBean(rs) : shippingBean;
				shippingBean.addWine(Helpers.createShippingWineBean(rs));
			}
		} finally {
			if (stmt != null)
				stmt.close();
		}

		return shippingBean;
	}

	@Override
	public Collection<ShippingBean> findAll(int limit, int offset) throws SQLException {
		ArrayList<ShippingBean> shippingBeans = new ArrayList<ShippingBean>();

		PreparedStatement stmt = null;

		try {
			String sql = "SELECT * FROM " + TABLE + leftJoin + " LIMIT ?, ? ";

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, limit);
			stmt.setInt(2, offset);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ShippingBean newShippingBean = Helpers.createShippingBean(rs);

				if (shippingBeans.contains(newShippingBean)) {
					int indexOfExisting = shippingBeans.indexOf(newShippingBean);

					ShippingBean existing = shippingBeans.get(indexOfExisting);
					existing.addWine(Helpers.createShippingWineBean(rs));

					shippingBeans.set(indexOfExisting, existing);
				} else {
					shippingBeans.add(newShippingBean);
				}
			}
		} finally {
			if (stmt != null)
				stmt.close();
		}

		return shippingBeans;
	}

	@Override
	public void create(ShippingBean product) throws SQLException {
		Integer insertedRows = null;

		PreparedStatement stmt = null;

		try {
			String sql = "INSERT INTO " + TABLE + " (idUser, address, createdAt) VALUES (?, ?, ?)";

			stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, product.getIdUser());
			stmt.setString(2, product.getAddress());
			stmt.setDate(3, new java.sql.Date((new Date()).getTime()));

			insertedRows = stmt.executeUpdate();

			try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					lastCreatedId = generatedKeys.getInt(1);
				} else {
					throw new SQLException("Creating user failed, no ID obtained.");
				}
			}
		} finally {
			if (stmt != null)
				stmt.close();
		}

		System.out.println(insertedRows);
	}

	@Override
	public int update(ShippingBean product) throws SQLException {
		PreparedStatement stmt = null;

		Integer updatedRows = null;

		try {
			String sql = "UPDATE " + TABLE + " SET " + "address = ? WHERE id = ?";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, product.getAddress());

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
