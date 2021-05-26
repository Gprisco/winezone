package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Helpers {

	public static Integer handleSQLException(SQLException ex) {
		System.out.println("SQLException: " + ex.getMessage());
		System.out.println("SQLState: " + ex.getSQLState());
		System.out.println("VendorError: " + ex.getErrorCode());

		return 0;
	}

	public enum Winecolor {
		Rosso(1), Bianco(2), Rosato(3);

		private final Integer winecolorValue;

		private Winecolor(Integer value) {
			this.winecolorValue = value;
		}

		public int getValue() {
			return winecolorValue;
		}
	}

	public enum Winetype {
		Passito(1), Fermo(2), SpumanteClassico(3), SpumanteCharmat(4), Frizzante(5), Liquoroso(6);

		private final Integer winetypeValue;

		private Winetype(Integer value) {
			this.winetypeValue = value;
		}

		public int getValue() {
			return winetypeValue;
		}
	}

	public enum Winedenom {
		DOCG(1), DOC(2), IGT(3), NONE(4);

		private final Integer winedenomValue;

		private Winedenom(Integer value) {
			this.winedenomValue = value;
		}

		public int getValue() {
			return winedenomValue;
		}
	}

	static WineBean createWineBean(ResultSet rs) {
		WineBean wineBean = new WineBean();

		try {
			WinePrimaryKey pk = new WinePrimaryKey(rs.getString("wine"), rs.getInt("vintage"));

			wineBean.setPk(pk);
			wineBean.setAvailability(rs.getInt("availability"));
			wineBean.setPrice(rs.getInt("price"));
			wineBean.setWineryId(rs.getInt("wineryId"));
			wineBean.setWinefamilyId(rs.getInt("winefamilyId"));

			wineBean.setWinery(createWineryBean(rs));
			wineBean.setWinefamily(createWineFamilyBean(rs));

			wineBean.addWinegrape(createWineWinegrapeBean(rs));
		} catch (SQLException e) {
			Helpers.handleSQLException(e);
		}

		return wineBean;
	}

	static WineryBean createWineryBean(ResultSet rs) {
		WineryBean wineryBean = new WineryBean();

		try {
			wineryBean.setWineryId(rs.getInt("wineryId"));
			wineryBean.setWinery(rs.getString("winery"));
			wineryBean.setAddress(rs.getString("address"));
			wineryBean.setTelephone(rs.getString("telephone"));
		} catch (SQLException e) {
			Helpers.handleSQLException(e);
		}

		return wineryBean;
	}

	static WinefamilyBean createWineFamilyBean(ResultSet rs) {
		WinefamilyBean winefamilyBean = new WinefamilyBean();

		try {
			winefamilyBean.setWinefamilyId(rs.getInt("winefamilyId"));
			winefamilyBean.setWinefamily(rs.getString("winefamily"));

			winefamilyBean.setRegionId(rs.getInt("regionid"));

			winefamilyBean.setWinecolor(Winecolor.values()[rs.getInt("winecolorId") - 1]);
			winefamilyBean.setWinetype(Winetype.values()[rs.getInt("winetypeId") - 1]);
			winefamilyBean.setWinedenom(Winedenom.values()[rs.getInt("winedenomId") - 1]);
		} catch (SQLException e) {
			Helpers.handleSQLException(e);
		}

		return winefamilyBean;
	}

	static WinegrapeBean createWinegrapeBean(ResultSet rs) {
		WinegrapeBean winegrapeBean = new WinegrapeBean();

		try {
			winegrapeBean.setWinegrapeId(rs.getInt("winegrapeId"));
			winegrapeBean.setWinegrape(rs.getString("winegrape"));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return winegrapeBean;
	}

	static WineWinegrapeBean createWineWinegrapeBean(ResultSet rs) {
		WineWinegrapeBean wineWinegrapeBean = new WineWinegrapeBean();

		try {
			wineWinegrapeBean.setPercentage(rs.getInt("percentage"));
			wineWinegrapeBean.setWinegrape(createWinegrapeBean(rs));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return wineWinegrapeBean;
	}

	static UserBean createUserBean(ResultSet rs) {
		UserBean userBean = new UserBean();

		try {
			userBean.setId(rs.getInt("id"));
			userBean.setEmail(rs.getString("email"));
			userBean.setPassword(rs.getString("password"));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return userBean;
	}

	static ShippingBean createShippingBean(ResultSet rs) {
		ShippingBean shippingBean = new ShippingBean();

		try {
			shippingBean.setId(rs.getInt("id"));
			shippingBean.setIdUser(rs.getInt("idUser"));
			shippingBean.setAddress(rs.getString("address"));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return shippingBean;
	}

	static ShippingWineBean createShippingWineBean(ResultSet rs) {
		ShippingWineBean shippingWineBean = new ShippingWineBean();

		try {
			shippingWineBean.setIdShipping(rs.getInt("id"));
			shippingWineBean.setVintage(rs.getInt("vintage"));
			shippingWineBean.setWine(rs.getString("wine"));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return shippingWineBean;
	}
}
