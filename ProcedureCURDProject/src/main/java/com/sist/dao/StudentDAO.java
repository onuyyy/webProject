package com.sist.dao;

import com.sist.dao.*;

import oracle.jdbc.internal.OracleTypes;

import java.util.*;
import java.sql.*;

public class StudentDAO {

	private Connection conn;
	private static StudentDAO dao;
	// 함수 (프로시저( 호출
	private CallableStatement cs;
	
	private final String URL ="jdbc:oracle:thin:@211.238.142.111:1521:XE";
	
	public StudentDAO() {
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		} catch(Exception ex) {}
		
	}
	
	
	// 싱글턴 사용
	public static StudentDAO newInstance() {
		
		if(dao==null)
			dao = new StudentDAO();
		return dao;
		
	}
	
	
	
	/*
	 * CREATE OR REPLACE PROCEDURE studentInsert (
    pName student.name%TYPE,
    pKor student.kor%TYPE,
    pEng student.eng%TYPE,
    pMath student.math%TYPE
)
IS
BEGIN
    INSERT INTO student (hakbun, name, kor, eng, math)
    VALUES (
        (SELECT NVL(MAX(hakbun)+1, 1) FROM student),
        pName,
        pKor,
        pEng,
        pMath
    );
    COMMIT;
END;
    
	 */
	
	
	public void studentInsert(StudentVO vo) {
		
		try {
			
			conn = DriverManager.getConnection(URL,"hr","happy");
			String sql ="{CALL studentInsert(?,?,?,?)}";
			cs = conn.prepareCall(sql);
			// ?에 값을 채운다
			cs.setString(1, vo.getName());
			cs.setInt(2, vo.getKor());
			cs.setInt(3, vo.getEng());
			cs.setInt(4, vo.getMath());	
			
			// 실행 요청
			cs.executeQuery();
			
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				
				if(cs!=null) cs.close();
				if(conn!=null) conn.close();
				
			} catch(Exception ex) {}
		}

		
	}
	
	public void studentUpdate(StudentVO vo) {
		
		/*
		 *     CREATE OR REPLACE PROCEDURE studentUpadate (
        pHakbun student.hakbun%TYPE,
        pName student.name%TYPE,
        pEng student.eng%TYPE,
        pMath student.math%TYPE
    )
    IS
    BEGIN
        UPDATE student SET
        name = pName,kor=pKor,eng=pEng
        WHERE hakbun=pHakbun;
        COMMIT;
    END;
		 */
		
		try {
			
			conn = DriverManager.getConnection(URL,"hr","happy");
			String sql = "{CALL studentUpdate(?,?,?,?,?)}";
			cs = conn.prepareCall(sql);
			
			cs.setInt(1, vo.getHakbun());
			cs.setString(2, vo.getName());
			cs.setInt(3, vo.getKor());
			cs.setInt(4, vo.getEng());
			cs.setInt(5, vo.getMath());	
			
			cs.executeQuery();
			
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				
				if(cs!=null) cs.close();
				if(conn!=null) conn.close();
				
			} catch(Exception ex) {}
		}

		
	}
	
	public void studentDelete(int hakbun) {
		
		try {
			/*   
			 *  CREATE OR REPLACE PROCEDURE studentDelete (
        pHakbun student.hakbun%TYPE
    )
    IS
    BEGIN
        DELETE FROM student
        WHERE hakbun = pHakbun;
        COMMIT;
    END;
			 * 
			 */
			conn = DriverManager.getConnection(URL,"hr","happy");
			String sql ="{CALL studentDelete(?)}";
			cs=conn.prepareCall(sql);
			
			cs.setInt(1, hakbun);
			
			cs.executeQuery();
			
			
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				
				if(cs!=null) cs.close();
				if(conn!=null) conn.close();
				
			} catch(Exception ex) {}
		}

		
	}
	
	   public StudentVO studentDetail(int hakbun)
	   {
		   StudentVO vo=new StudentVO();
		   try
		   {
			   conn=DriverManager.getConnection(URL,"hr","happy");
			   String sql="{CALL studentDetailData(?,?,?,?,?)}";
			   cs=conn.prepareCall(sql);
			   //?에 값을 채운다 
			   cs.setInt(1, hakbun);
			   // OUT 변수일 경우에 => 메모리에 저장한다
			   cs.registerOutParameter(2, OracleTypes.VARCHAR);
			   cs.registerOutParameter(3, OracleTypes.INTEGER);
			   cs.registerOutParameter(4, OracleTypes.INTEGER);
			   cs.registerOutParameter(5, OracleTypes.INTEGER);
			   // 실행 요청 
			   cs.executeQuery();
			   vo.setName(cs.getString(2));
			   vo.setKor(cs.getInt(3));
			   vo.setEng(cs.getInt(4));
			   vo.setMath(cs.getInt(5));
			   vo.setHakbun(hakbun);
		   }catch(Exception ex)
		   {
			   ex.printStackTrace();
		   }
		   finally
		   {
			   try
			   {
				   if(cs!=null) cs.close();
				   if(conn!=null) conn.close();
			   }catch(Exception ex) {}
		   }
		   return vo;
	   }
	
	
	
	// 데이터 전체
	public List<StudentVO> studentListData() {
		/*
		 *     CREATE OR REPLACE PROCEDURE studentListData(
			        pResult OUT SYS_REFCURSOR
			    )
			    IS
			    BEGIN
			        OPEN Result FOR
			        SELECT * FROM student;
			    END;
		 */
		List<StudentVO> list = new ArrayList<StudentVO>();
		
		try {
			
			conn = DriverManager.getConnection(URL,"hr","happy");
			String sql ="{CALL studentListData(?)}";
			cs = conn.prepareCall(sql);
			
			// registerOupParameter => 저장해주는 공간
			cs.registerOutParameter(1, OracleTypes.CURSOR);
			/*
			 * 	NUMBER => INTEGER or DOUBLE
			 *  VARCHAR2,CHAR => VARCHAR
			 *  CURSOR => CURSOR
			 */
			cs.executeQuery();
			
			// 결과 값 받기
			
			ResultSet rs = (ResultSet) cs.getObject(1);	// CURSOR는 무조건 ResultSet으로 변환
			while(rs.next()) {
				
				StudentVO vo = new StudentVO();
				vo.setHakbun(rs.getInt(1));
				vo.setName(rs.getString(2));
				vo.setKor(rs.getInt(3));
				vo.setEng(rs.getInt(4));
				vo.setMath(rs.getInt(5));
				vo.setTotal(rs.getInt(6));
				vo.setAvg(rs.getDouble(7));
				list.add(vo);
				
			}
			rs.close();
			
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				
				if(cs!=null) cs.close();
				if(conn!=null) conn.close();
				
			} catch(Exception ex) {}
		}

		return list;
	}
	
	
	
	
	
}
