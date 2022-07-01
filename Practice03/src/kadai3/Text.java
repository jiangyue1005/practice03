package kadai3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
			int i = 0;
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
				i++;
//				for(i = 1;i < list.size();i++) {  
					
			    
				omikuji.setOmikujicd(Integer.toString(i));
				omikuji.setUnsei();
				omikuji.setNegaigoto(arr[1]);
				omikuji.setAkinai(arr[2]);
				omikuji.setGakumon(arr[3]);

				list.add(omikuji);
				System.out.println(omikuji.getOmikujicd()+ omikuji.getUnsei() + omikuji.getNegaigoto() + omikuji.getAkinai() + omikuji.getGakumon());
				//System.out.println(omikuji.getOmikujicd());
				//omikuji.unseicode();
			
				}	
				System.out.println("誕生日を入力してください。");
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

				String key = br.readLine();

				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

				sdf.setLenient(false);
				// setLenient(false)を呼び出すことによって、このフォーマットを厳密に要求できます。
				sdf.parse(key);

				

//				Random random = new Random();
//				Date d = new Date();
//				// fw1.write(sdf.format(d) + "," + key + ",");
//
//				String date2;
//				boolean resultFlg = false;
//				while ((date2 = on.readLine()) != null) {
//					String[] abb = date2.split(",");
//					String td = abb[0];
//					String bd = abb[1];
//
//					if (td.equals(sdf.format(d))) {
//						if (bd.equals(key)) {
//							String us = abb[2] + "," + abb[3] + "," + abb[4] + "," + abb[5];
//							System.out.println(us.replace(",", "\n"));
//							resultFlg = true;
//						}
//					}
//				}
//				if (!resultFlg) {
//					Omikuji re = list.get(random.nextInt(list.size()));
//					String result = re.disp() + "," + re.getNegaigoto() + "," + re.getAkinai() + "," + re.getGakumon();
//					fw.write(sdf.format(d) + "," + key + "," + result + "\n");
//					System.out.println(result.replace(",", "\n"));
//				}

		    } catch (IOException e) {
				System.out.println("ファイルが見つかりません。");

			} catch (ParseException e) {
				System.out.println("入力された日付は存在しません。");
			} finally {
				try {
					fr.close();
					
				} catch (IOException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}

//				String result = omikuji.getOmikujicd() + omikuji.getUnseicd() + omikuji.getNegaigoto() + omikuji.getAkinai() + omikuji.getGakumon();
//				System.out.println(result);
			}
			
			

//		} catch (IOException e) {
//			System.out.println("ファイルが見つかりません。");
//
//		} finally {
//			try {
//				fr.close();
//			} catch (IOException e) {
//				// TODO 自動生成された catch ブロック
//				e.printStackTrace();
//			}
//		}

	}

}


