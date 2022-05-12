package kadai3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Text {
	public static void main(String[] args) {

		File file = new File("/Users/y_jiang/Documents/unsei.csv");

		FileReader fr = null;
		



		List<Omikuji> list = new ArrayList<Omikuji>();
		Omikuji omikuji = null;

		try {

			fr = new FileReader(file);
			BufferedReader in = new BufferedReader(fr);

			String date;
			while ((date = in.readLine()) != null) {
				String[] arr = date.split(",");
				// System.out.println(arr[0]);

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
				for(int i = 1;i <= list.size();i++) {  
					//list.size()

				omikuji.setOmikujicd(Integer.toString(i));
				omikuji.setUnsei(arr[0]);
				omikuji.setNegaigoto(arr[1]);
				omikuji.setAkinai(arr[2]);
				omikuji.setGakumon(arr[3]);

				list.add(omikuji);

				String result = omikuji.getOmikujicd() + omikuji.getUnsei() + omikuji.getNegaigoto() + omikuji.getAkinai() + omikuji.getGakumon();
				System.out.println(result);
			}
			}
			

		} catch (IOException e) {
			System.out.println("ファイルが見つかりません。");

		} finally {
			try {
				fr.close();
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}

	}

}


