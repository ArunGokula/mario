package test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.mario.persistence.impl.RiddleDaoImpl;
import com.mario.utils.HSQLDBConnection;

public class RiddleTest {

	@Test
	public void test() {
		HSQLDBConnection db = new HSQLDBConnection();
		RiddleDaoImpl riddleDao = new RiddleDaoImpl(db);
		assertNotNull(riddleDao.getRiddle(1));
	}

}
