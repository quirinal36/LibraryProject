package www.lmj.com.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import www.lmj.com.db.DBConn;
import www.lmj.com.vo.Book;

public class BookController {

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
			
			System.out.println(stmt.toString());
			
			result = stmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
