package test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.mario.persistence.impl.RiddleDaoImpl;
import com.mario.utils.HSQLDBConnection;

public class RiddleTest {
	static HSQLDBConnection db;
	@BeforeClass
	public static void setupDB(){
		db = new HSQLDBConnection();

	}

	@Test
	public void test() {
		RiddleDaoImpl riddleDao = new RiddleDaoImpl(db);
		assertNotNull(riddleDao.getRiddle(1));
	}
	
	@Test
	public void testHintrandomness() {
		RiddleDaoImpl riddleDao = new RiddleDaoImpl(db);
		for(int level=1;level<10;level++){
		String hinted=riddleDao.getRiddleHint(level);
		System.out.println(hinted);
		int count = hinted.length() - hinted.replace("_", "").length();
		if(count<2)
			fail();
		}
	}

}
