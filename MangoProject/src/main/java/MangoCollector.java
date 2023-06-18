import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

public class MangoCollector {

	
	public static void main(String[] args) {
		
		List<MangoVO> list = new ArrayList<MangoVO>();
		

		try {
			
			
			Document doc = Jsoup.connect("https://www.mangoplate.com/").get();
			
			Elements title = doc.select("div.top_list_slide div.info_inner_wrap span.title");
			Elements subject = doc.select("div.top_list_slide div.info_inner_wrap p.desc");
			Elements poster = doc.select("div.top_list_slide img.center-croping");
			Elements link = doc.select("div.top_list_slide a");
			
			
			for(int i=0; i<title.size(); i++) {
				MangoVO vo = new MangoVO();
				System.out.println("number : "+(i+1));
				System.out.println("title : "+title.get(i).text());
				System.out.println("subject : "+subject.get(i).text());
				System.out.println("poster : "+poster.get(i).attr("data-lazy"));
				String p = poster.get(i).attr("data-lazy");
				p = p.replace("&", "#");
				System.out.println("link : "+link.get(i).attr("href"));
				
				vo.setPoster(p);
				vo.setSubject(subject.get(i).text());
				vo.setTitle(title.get(i).text());
				vo.setLink("https://www.mangoplate.com/"+link.get(i).attr("href"));
				list.add(vo);
				
			}
			
			System.out.println("완료");
			
		} catch(Exception ex) {
			// TODO Auto-generated catch block
		
		}
		



		
	}
}
