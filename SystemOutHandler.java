package logger;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

/** @see https://sematext.com/blog/java-logging/ */
public class SystemOutHandler extends Handler {

	@Override
	public void publish(LogRecord logRecord) {
		String msg = getFormatter().format(logRecord);
		Level level = logRecord.getLevel();
		if (level == Level.SEVERE || level == Level.WARNING) {
			System.err.println(msg);
		} else {
			/**
			 * By default, we don't log Level < INFO
			 * 
			 * @see java.util.logging.Logger.ConfigurationData
			 */
			System.out.println(msg);
		}
	}

	@Override
	public void flush() {
		// Auto-generated method stub
	}

	@Override
	public void close() throws SecurityException {
		// Auto-generated method stub
	}

}
