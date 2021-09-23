package logger;

import java.util.logging.Handler;
import java.util.logging.Logger;

public class LoggerUtil {

	private LoggerUtil() {
		super();
	}

	public static Logger getLogger(String loggerName) {
		Logger log = Logger.getLogger(loggerName);
		log.setUseParentHandlers(false);
		Handler handler = new SystemOutHandler();
		handler.setFormatter(new SingleLineFormatter());
		log.addHandler(handler);
		return log;
	}

}
