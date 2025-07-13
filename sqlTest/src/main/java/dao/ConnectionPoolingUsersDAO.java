package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import dto.Users;

public class ConnectionPoolingUsersDAO {
	/**
	 * ユーザーIDからレコードを取得
	 * ユーザーIDは主キーなので返り値はUsers（配列ではない）
	 * 
	 * @param userId
	 * @return
	 */
	public Users selectById(int userId) {
		
		try {
			// コネクションプーリングからとってくる
			InitialContext context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/sqlTest");
			Connection connection = ds.getConnection();
			
			// sqlの準備（いつもの）
			String sql = "SELECT * FROM users WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			// idを設定して実行
			statement.setInt(1, userId);
			ResultSet result = statement.executeQuery();
			
			// 結果を取得
			result.next();
			Users user = new Users(
					result.getInt("id"),
					result.getString("name"),
					result.getString("email"),
					result.getString("password")
				);
			
			return user;
		} catch(NamingException | SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
