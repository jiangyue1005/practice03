package kadai3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class  Uranai {
	public static void main(String[] args) throws Exception {

		///////////////////// TB読み込む//////////////////////////////
		final String URL = "jdbc:postgresql://127.0.0.1:5432/postgres";
		final String USER = null;
		final String PASS = null;

		final String SQL1 = "select COUNT( * ) from omikuji;";
		final String SQL2 = "insert into omikuji (omikujicd,unseicd,negaigoto,akinai,gakumon,modifiedby,updatedate,createdby,createddate)values(nextval('omikujicd_seq'),?,?,?,?,?,CURRENT_TIMESTAMP,?,CURRENT_TIMESTAMP);";
		final String SQL3 = "insert into result (uranaidate,birthday,omikujicd,modifiedby,updatedate,createdby,createddate)values(?,?,?,?,CURRENT_TIMESTAMP,?,CURRENT_TIMESTAMP);";
		final String SQL4 = "select * from omikuji where omikujicd = ?;";
		final String SQL5 = "select * from result where uranaidate = ? and birthday = ?;";

		Connection con = null;

		// CSVファイル
		File file = new File("/Users/y_jiang/Documents/unseiNew.csv");
		FileReader fr = null;
		con = DriverManager.getConnection(URL, USER, PASS);

		Omikuji omikuji = null;
		try {
			// omikujiテーブルにデータがすでに存在した場合、データの書き込みをスキップ。
			PreparedStatement getOmikujiCount = con.prepareStatement(SQL1);
			ResultSet getCount = getOmikujiCount.executeQuery();

			int count = 0;
			// 件数取得
			while (getCount.next()) {
				count = getCount.getInt(1);
			}

			if (count == 0) {// nullの場合、insert実行
				PreparedStatement insertOmkjInfo = con.prepareStatement(SQL2);
				fr = new FileReader(file);
				BufferedReader in = new BufferedReader(fr);
				String data;
				int i = 0;
				// CSVファイルを読み込み、DBに書き込み
				while ((data = in.readLine()) != null) {
					String[] arr = data.split(",");
					// 運勢コード
					insertOmkjInfo.setString(1, arr[0]);
					// 願い事
					insertOmkjInfo.setString(2, arr[1]);
					// 商い
					insertOmkjInfo.setString(3, arr[2]);
					// 学問
					insertOmkjInfo.setString(4, arr[3]);
					// 更新者
					insertOmkjInfo.setString(5, "姜悦");
					// 作成者
					insertOmkjInfo.setString(6, "姜悦");
					// SQL実行
					int insertCount = insertOmkjInfo.executeUpdate();
					count = insertCount + i;
					i++;
				}
				fr.close();
			}

			// ****************ここは日付入力****************
			System.out.println("誕生日を入力してください。");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String key = br.readLine();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			// フォーマット設定
			sdf.setLenient(false);
			sdf.parse(key);

			// データ有無判定
			boolean resultFlg = false;

			// ランダムomikujicd生成
			Random random = new Random();
			String omikujicd = Integer.toString(random.nextInt(count));

			// 誕生日をDate型に
			SimpleDateFormat sdFormat1 = new SimpleDateFormat("yyyyMMdd");
			Date birth = sdFormat1.parse(key);
			long newBirth = birth.getTime();

			// 日付型変換
			Date today = new Date();
			long day = today.getTime();
			java.sql.Date uranaidate = new java.sql.Date(day);
			java.sql.Date birthday = new java.sql.Date(newBirth);

			// resultテーブルに同じデータが存在するかどうかを判定
			PreparedStatement findResultInfo = con.prepareStatement(SQL5);
			findResultInfo.setDate(1, uranaidate);
			findResultInfo.setDate(2, birthday);
			ResultSet resultRs = findResultInfo.executeQuery();

			while (resultRs.next()) {
				resultFlg = true;
				// おみくじコード取得
				omikujicd = resultRs.getString("omikujicd");
			}

			// omikujicdで結果を検索
			PreparedStatement resultInfo = con.prepareStatement(SQL4);
			resultInfo.setString(1, omikujicd);
			ResultSet getResultRs = resultInfo.executeQuery();
			while (getResultRs.next()) {
				switch (getResultRs.getString("unseicd")) {
				case "1":
					omikuji = new Daikichi();
					break;
				case "2":
					omikuji = new Chukichi();
					break;
				case "3":
					omikuji = new Shokichi();
					break;
				case "4":
					omikuji = new Suekichi();
					break;
				case "5":
					omikuji = new Kichi();
					break;
				case "6":
					omikuji = new Kyo();
					break;

				}
				omikuji.setUnsei();
				omikuji.setOmikujicd(getResultRs.getString("omikujicd"));
				omikuji.setUnseicd(getResultRs.getString("unseicd"));
				omikuji.setNegaigoto(getResultRs.getString("negaigoto"));
				omikuji.setAkinai(getResultRs.getString("akinai"));
				omikuji.setGakumon(getResultRs.getString("gakumon"));

			}
			// 結果出力
			String result = omikuji.disp() + "," + omikuji.getNegaigoto() + "," + omikuji.getAkinai() + ","
					+ omikuji.getGakumon();
			System.out.println(result.replace(",", "\n"));

			// 同じデータ存在しない場合insert
			if (!resultFlg) {
				PreparedStatement insertResultInfo = con.prepareStatement(SQL3);
				insertResultInfo.setDate(1, uranaidate);
				insertResultInfo.setDate(2, birthday);
				insertResultInfo.setString(3, omikujicd);
				insertResultInfo.setString(4, "姜悦");
				insertResultInfo.setString(5, "姜悦");
				insertResultInfo.executeUpdate();
			}

		} catch (ParseException e) {
			System.out.println("入力された日付は存在しません。");
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}

}
