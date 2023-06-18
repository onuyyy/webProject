package com.sist.dao;

import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.sist.vo.GenieMusicVO;

import java.io.*;

public class GenieDAO {

	public static void main(String[] args) {
	
	
	List<GenieMusicVO> list = new ArrayList<GenieMusicVO>();
	
	try {
		
		String[] urls = {"https://www.genie.co.kr/chart/genre?ditc=D&ymd=20230502&genrecode=M0100",
		               "https://www.genie.co.kr/chart/genre?ditc=D&ymd=20230502&genrecode=M0200",
		               "https://www.genie.co.kr/chart/genre?ditc=D&ymd=20230502&genrecode=M0300",
		               "https://www.genie.co.kr/chart/genre?ditc=D&ymd=20230502&genrecode=M0107"};
		
		

		

		
		for(int i=0; i<urls.length; i++) {
			Document doc = Jsoup.connect(urls[i]).get();
			
			Elements title = doc.select("td.info a.title"); 
			Elements singer = doc.select("td.info a.artist");
			Elements album = doc.select("td.info a.albumtitle");
			Elements poster = doc.select("a.cover img");
			
			for(int j=0; j<title.size(); j++) {
			
			GenieMusicVO vo = new GenieMusicVO();
			
			vo.setTitle(title.get(j).text());
			vo.setSinger(singer.get(j).text());
			vo.setAlbum(album.get(j).text());
			vo.setPoster(poster.get(j).attr("src"));
			list.add(vo);
			
			System.out.println("===========================");
			System.out.println("카테고리 번호 : "+(i+1));
			System.out.println(title.get(j).text());
			System.out.println(singer.get(j).text());		
			System.out.println(album.get(j).text());		
			System.out.println(poster.get(j).attr("src"));	
			
			
			}
			
		
		}
		
		
	} catch(Exception ex) {
		
		
		
	}

	}

}