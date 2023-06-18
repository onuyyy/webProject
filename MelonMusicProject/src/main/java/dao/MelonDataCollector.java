
package dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MelonDataCollector {

	public static void main(String[] args) {

		List<MelonVO> list = new ArrayList<MelonVO>();
		Set<MelonVO> set = new HashSet<MelonVO>();

		
		try {
		
			Document doc = (Document) Jsoup.connect("https://www.melon.com/chart/index.htm").get();
	
			Elements title = doc.select("div.ellipsis.rank01 a");
			Elements singer = doc.select("div.ellipsis.rank02 a[title]");

			for(int i=0; i<title.size(); i++) {
//			System.out.println(title.get(i).text());
//			System.out.println(singer.get(i).text());
			
			MelonVO vo = new MelonVO();
			vo.setTitle(title.get(i).text());
			vo.setSinger(singer.get(i).text());
			
			set.add(vo);
			
			}
			int i=1;
			for(MelonVO setOut : set) {
				System.out.println(i+" : "+"제목 : "+setOut.getTitle());
				System.out.println("가수 : "+setOut.getSinger());
				i++;
				System.out.println("======================================");
			}
			
		} catch(Exception ex) {}
		
	}
	
}
