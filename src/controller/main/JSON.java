package controller.main;

import java.util.Iterator;

import model.WineBean;

public class JSON {
	public static String stringifyWines(Iterator<WineBean> wines) {
		String output = "[";

		while (wines.hasNext()) {
			WineBean next = wines.next();

			String wine = next.getPk().getWine().replace("\"", "\\\"");
			Integer vintage = next.getPk().getVintage();
			String winery = next.getWinery().getWinery();
			String winefamily = next.getWinefamily().getWinefamily();
			String winecolor = next.getWinefamily().getWinecolor().name();
			String winetype = next.getWinefamily().getWinetype().name();

			output += "{";
			output += "\"wine\": \"" + wine + "\",";
			output += "\"vintage\": " + vintage + ",";
			output += "\"winery\": \"" + winery + "\",";
			output += "\"winefamily\": \"" + winefamily + "\",";
			output += "\"winecolor\": \"" + winecolor + "\",";
			output += "\"winetype\": \"" + winetype + "\"";
			output += "},";
		}

		output = output.substring(0, output.length() - 1);
		output += "]";

		return output;
	}
}
