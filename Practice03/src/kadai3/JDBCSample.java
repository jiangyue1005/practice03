package kadai3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.security.Timestamp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class JDBCSample {
	public static void main(String[] args) throws Exception {

		///////////////////// TB読み込む//////////////////////////////
		final String URL = "jdbc:postgresql://127.0.0.1:5432/postgres";
		final String USER = null;
		final String PASS = null;
		final String SQL1 = "select * from unseimaster order by unseicd asc;";

		final String SQL2 = "select * from omikuji where omikujicd = ? And unseicd = ? And negaigoto = ? And akinai = ? And gakumon = ? And modifiedby = ? And updatedate = ? And createdby = ? And  createddate = ?;";
//	        final String SQL = "select * from unseimaster where unseiname = ?;";        
		Connection con = null;

		///////////////////// TB読み込む//////////////////////////////
		HashMap<String, String> map = new HashMap<String, String>();

		List<Omikuji> list1 = new ArrayList<Omikuji>();
		Omikuji omikuji = null;

		File file = new File("/Users/y_jiang/Documents/unsei.csv");
		FileReader fr = null;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date d = new Date();
		java.util.Date  t=new java.util.Date();
		java.sql.Date  t1=new java.sql.Date(t.getTime());

		
		//Timestamp t= new Timestamp(d.getTime());

			con = DriverManager.getConnection(URL, USER, PASS);
			PreparedStatement ps1 = con.prepareStatement(SQL1);
			PreparedStatement ps2 = con.prepareStatement(SQL2);

//	            ps.setString(1, "1");
//	            ps.setString(1,"大吉");

			ResultSet rs1 = null;
			rs1 = ps1.executeQuery();

//			ResultSet rs2 = null;
//			rs2 = ps2.executeQuery();

			while (rs1.next()) {
//	                System.out.println(
//	            		
//	                   rs.getInt("unseicd") + " " +
//	                   rs.getString("unseiname"));
				int i = rs1.getInt("unseicd");
				String unseicd = Integer.toString(i);
				String unseiname = rs1.getString("unseiname");

				// 定义好key和value，由于数据较多，后面还需筛选
				map.put(unseiname, unseicd);

			}
			// System.out.println(map.get("末吉"));

			fr = new FileReader(file);
			BufferedReader in = new BufferedReader(fr);

			String date;
			while ((date = in.readLine()) != null) {

				String[] arr = date.split(",");

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

				

				omikuji.setUnsei();
				// 前面筛选了吉凶，直接把结果套在map上就可以定义运势code
				omikuji.setUnseicd(map.get(arr[0]));
				omikuji.setNegaigoto(arr[1]);
				omikuji.setAkinai(arr[2]);
				omikuji.setGakumon(arr[3]);

				list1.add(omikuji);

//				 System.out.println(omikuji.getUnsei() + " " + omikuji.getUnseicd() +
//				  omikuji.getNegaigoto() + omikuji.getAkinai() +
//				 omikuji.getGakumon());

//		         
//		            		
//		                   rs.getInt("unseicd") + " " +
//		                   rs.getString("unseiname"))

//					int a = rs2.getInt("omikujicd");
//					String omikujicd = Integer.toString(a);
//					int b = rs2.getInt("unseicd");
//					String unseicd = Integer.toString(b);
//					String unseiname = rs2.getString("negaigoto");
//					String unseiname = rs2.getString("akinai");
//					String unseiname = rs2.getString("gakumon");
//					
			

				

				// 设置好omikujicodo
				for (int i = 0; i < 50; i++) {
					// 依次填入9个空
					ps2.setInt(1, i + 1);
					ps2.setString(2, omikuji.getUnseicd());
					ps2.setString(3, omikuji.getNegaigoto());
					ps2.setString(4, omikuji.getAkinai());
					ps2.setString(5, omikuji.getGakumon());
					ps2.setString(6, "姜悦");
					ps2.setDate(7, t1);
					ps2.setString(8, "姜悦");
					ps2.setString(9, "20220704");
				}

					
					
					
					
					
					
			
//					void	setString(int a, String b)	a番目の「?」にbをString型でセットする
//					void	setInt(int a, int b)	a番目の「?」にbをint型でセットする
//					void	setLong(int a, long b)	a番目の「?」にbをlong型でセットする
//					void	setFloat(int a, float b)	a番目の「?」にbをfloat型でセットする
//					void	setDouble(int a, double b)	a番目の「?」にbをdouble型でセットする
//					void	setDate(int a, Date b)	a番目の「?」にbをjava.sql.Date型でセットする
//					void	setBoolean(int a, boolean b)	a番目の「?」にbをboolean型でセットする
//					void	clearParameters()	全てのパラメータをクリアする
//					ResultSet	executeQuery()	SELECT文を実行する
//					int	executeUpdate()	UPDATE, INSERT, DELETE,ストアドを実行する

					 int a = ps2.executeUpdate();
					 System.out.println("結果：" + a);

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
