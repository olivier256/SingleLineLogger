package logger;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;

/** @see SimpleFormatter */
public class SingleLineFormatter extends Formatter {

	private static final String FORMAT = "%1$tb %1$td, %1$tY %1$tk:%1$tM:%1$tS %2$s %3$s: %4$s";

	@Override
	public String format(LogRecord logRecord) {
		long millis = logRecord.getMillis();
		LocalDateTime ldt = LocalDateTime.ofEpochSecond(millis / 1000, 0, ZoneOffset.ofHours(+1));
		String source;
		if (logRecord.getSourceClassName() != null) {
			source = logRecord.getSourceClassName();
			if (logRecord.getSourceMethodName() != null) {
				source += " " + logRecord.getSourceMethodName();
			}
		} else {
			source = logRecord.getLoggerName();
		}
		String message = formatMessage(logRecord);
		String throwable = "";
		if (logRecord.getThrown() != null) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			pw.println();
			logRecord.getThrown().printStackTrace(pw);
			pw.close();
			throwable = sw.toString();
		}
		return String.format(FORMAT, ldt, source, logRecord.getLoggerName(), message, throwable);
	}

}
