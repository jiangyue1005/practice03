package kadai3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JDBCSample {
	public static void main(String[] args) throws Exception {

		///////////////////// TB読み込む//////////////////////////////
		final String URL = "jdbc:postgresql://127.0.0.1:5432/postgres";
		final String USER = null;
		final String PASS = null;
		final String SQL = "select * from unseimaster order by unseicd asc;";
//	        final String SQL = "select * from unseimaster where unseicd = ?;";  
//	        final String SQL = "select * from unseimaster where unseiname = ?;";        
		Connection con = null;

		///////////////////// TB読み込む//////////////////////////////
		HashMap<String, String> map = new HashMap<String, String>();

		List<Omikuji> list1 = new ArrayList<Omikuji>();
		Omikuji omikuji = null;

		File file = new File("/Users/y_jiang/Documents/unsei.csv");
		FileReader fr = null;
		// int a = 0;
		//List<Integer> list2 = new ArrayList<Integer>();

//
		FileInputStream fis = new FileInputStream(file);

		byte[] byteArray = new byte[(int) file.length()];

		fis.read(byteArray);

		String data = new String(byteArray);

		String[] stringArray = data.split("\r\n");

//
//		List<Omikuji> list = new ArrayList<Omikuji>();
//		Omikuji omikuji = null;

		try {

			con = DriverManager.getConnection(URL, USER, PASS);
			PreparedStatement ps = con.prepareStatement(SQL);

//	            ps.setString(1, "1");
//	            ps.setString(1,"大吉");

			ResultSet rs = null;
			rs = ps.executeQuery();

			while (rs.next()) {
//	                System.out.println(
//	            		
//	                   rs.getInt("unseicd") + " " +
//	                   rs.getString("unseiname"));
				int i = rs.getInt("unseicd");
				String unseicd = Integer.toString(i);
				String unseiname = rs.getString("unseiname");

				map.put(unseiname, unseicd);

			}
			// System.out.println(map.get("小吉"));

			fr = new FileReader(file);
			BufferedReader in = new BufferedReader(fr);
			for (int a = 1; a <= stringArray.length; a++) {
				int oc = +a;
				//list2.add(oc);

				// System.out.println(oc);

				String date;
				while ((date = in.readLine()) != null) {

					String[] arr = date.split(",");

//					
//	

					switch (arr[0]) {
					case "大吉":
						omikuji = new Daikichi();
						break;
					case "中吉":
						omikuji = new Chukichi();
						break;
					case "小吉":
						omikuji = new Shokichi();
						break;
					case "末吉":
						omikuji = new Suekichi();
						break;
					case "吉":
						omikuji = new Kichi();
						break;
					case "凶":
						omikuji = new Kyo();
						break;

					}

					// omikuji.setOmikujicd(Integer.toString(a));
					// omikuji.setOmikujicd(a);

					// System.out.println(Integer.toString(oc));

					// omikuji.setOmikujicd();
					System.out.println(omikuji.getOmikujicd());

					omikuji.setUnsei();
					omikuji.setUnseicd(map.get(arr[0]));
					omikuji.setNegaigoto(arr[1]);
					omikuji.setAkinai(arr[2]);
					omikuji.setGakumon(arr[3]);

					list1.add(omikuji);

//				 System.out.println(omikuji.getUnsei() + " " + omikuji.getUnseicd() +
//				  omikuji.getNegaigoto() + omikuji.getAkinai() +
//				 omikuji.getGakumon());
				//

				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
