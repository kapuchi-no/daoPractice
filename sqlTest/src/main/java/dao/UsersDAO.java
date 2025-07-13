package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.Users;

public class UsersDAO {
	private String url = "jdbc:postgresql://localhost:5432/postgres?currentSchema=daopractice";
	private String user = "postgres";
	private String password = "*********************";
	
	/**
	 * ユーザーIDからレコードを取得
	 * ユーザーIDは主キーなので返り値はUsers（配列ではない）
	 * 
	 * @param userId
	 * @return
	 */
	public Users selectById(int userId) {
		// ドライバ確認
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println("ドライバが見つかりません");
		}
		
		// SELECT実行
		String sql = "SELECT * FROM users WHERE id = ?";
		try (
				Connection connection = DriverManager.getConnection(this.url, this.user, this.password);
				PreparedStatement statement = connection.prepareStatement(sql)
			) {
			
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
