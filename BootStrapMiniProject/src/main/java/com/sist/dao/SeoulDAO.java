package com.sist.dao;
import java.util.*;
import java.sql.*;
import com.sist.dbconn.*;



public class SeoulDAO {

	private String[] tables= {
			"",
			"seoul_location",
			"seoul_nature",
			"seoul_shop"
	};
	
	private Connection conn;
	private PreparedStatement ps;
	private CreateDataBase db = new CreateDataBase();
	private static SeoulDAO dao;
	
	public static SeoulDAO newInstance() {
		if(dao==null)
			dao = new SeoulDAO();
		return dao;
	}
	// 1. 기능
	public List<SeoulVO> seoulListData(int type) {
		
		List<SeoulVO> list = new ArrayList<SeoulVO>();
		
		/*
		 *  private int no,hit;
		 *	private String title, poster, msg, address;
		 */
		
		try {
			
			conn=db.getConnection();
			String sql = "SELECT no,title,poster,rownum "
					+"FROM "+tables[type]
							+"WHERE rownum<=20";
			
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				SeoulVO vo = new SeoulVO();
				vo.setNo(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setPoster(rs.getString(3));
				list.add(vo);
				
			}
			
			rs.close();
			
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			db.disConnection(conn, ps);
		}
		
		return list;

	}
	
	
	// 2. 총페이지
	public int seoulTotalpage(int type) {
		
		int total = 0;
		
		try {
			
			db.getConnection();
			String sql = "SELECT CEIL(COUNT(*)/12.0) "
					+ "FROM "+tables[type];
			// ?를 안 쓰는 이유 => ps.setString(1,tables[type]) => FROM 뒤엔 테이블명이 나와야 하는데 테이블명은 '' 안 붙임
								// setString이 문자로 인식하여 ''를 붙인다
			
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			
			
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			db.disConnection(conn, ps);
		}
				
		return total;
		
	}
	
	
	// 3. 상세 보기
	public SeoulVO seoulDetailData(int no, int type) {
		
		SeoulVO vo = new SeoulVO();
		
		try {
			
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			db.disConnection(conn, ps);
		}
		
		return vo;
	}
	
	
	
	
	
}
