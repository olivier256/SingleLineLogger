package logger;

import java.util.logging.Handler;
import java.util.logging.Logger;

public class SystemOutLogger extends Logger {

	public SystemOutLogger(String loggerName) {
		super(loggerName, null);
		setUseParentHandlers(false);
		Handler handler = new SystemOutHandler();
		handler.setFormatter(new SingleLineFormatter());
		addHandler(handler);
	}

}
