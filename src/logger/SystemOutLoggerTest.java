package logger;

import java.util.logging.Logger;

import org.junit.Test;

public class SystemOutLoggerTest {

	@Test
	public void test() {
		Logger logger = new SystemOutLogger();
		logger.info("Hello, World!");
	}

}
