package test.users;

import java.sql.Connection;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mario.model.User;
import com.mario.persistence.impl.HSQLDBConnection;
import com.mario.persistence.impl.UserDaoHsql;

public class UserManagementTest {
	static Connection db;
	@BeforeClass
	public static void setupDB(){
		db = HSQLDBConnection.getConnection();

	}
	
	@AfterClass
	public static void destroy(){
		HSQLDBConnection.closeConnection();
	}

	@Test
	public void testUserAdd() {
		UserDaoHsql userDao = new UserDaoHsql(db);
		userDao.persistUser(new User("test"));
	}
	
	@Test
	public void testUserDelete() {
		UserDaoHsql userDao = new UserDaoHsql(db);
		userDao.deleteUser("test");
	}

}
