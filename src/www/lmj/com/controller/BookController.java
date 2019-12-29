package www.lmj.com.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import www.lmj.com.db.DBConn;
import www.lmj.com.vo.Book;

public class BookController {
	Logger logger = Logger.getLogger(getClass().getSimpleName());
	
	/**
	 * library 의 테이블 [Book] 안에 있는 모든 정보를 가져온다.
	 *   
	 * @return : 검색결과
	 */
	public List<Book> selectList(){
		List<Book> result = new ArrayList<Book>();
		
		DBConn db = new DBConn();
		
		try (Connection conn = db.getConnection()){
			// 1. 쿼리문 만들기
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM Book");
			
			// 2. 쿼리문 실행하여 ResultSet 얻어오기
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			
			// 3. ResultSet 안에있는 정보를 읽어와 result 에 차례차례 저장하기
			while(rs.next()) {
				Book item = new Book();
				item.setTitle(rs.getString("title"));
				item.setAuthor(rs.getString("author"));
				item.setGenre(rs.getString("genre"));
				item.setId(rs.getInt("id"));
				item.setRegist(rs.getString("regist"));
				item.setWdate(rs.getString("wdate"));
				
				result.add(item);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int insert(Book input) {
		int result = 0;
		DBConn db = new DBConn();
		
		try (Connection conn = db.getConnection()){
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO Book")
				.append("(title, author, regist, genre)")
				.append("VALUES")
				.append("(?,?,?,?)");
			
			System.out.println(sql.toString());
			
			PreparedStatement stmt = conn.prepareStatement(sql.toString());
			stmt.setString(1, input.getTitle());
			stmt.setString(2, input.getAuthor());
			stmt.setString(3, input.getRegist());
			stmt.setString(4, input.getGenre());
			
			logger.info(stmt.toString());
			
			result = stmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
