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

	private static final String FIRST_PARAMETER_AS_DATE_TIME = "%1$tb %1$td, %1$tY %1$tk:%1$tM:%1$tS";
	private static final String FORMAT = FIRST_PARAMETER_AS_DATE_TIME + " %2$s %3$s: %4$s";
	private static final String FORMAT_WITHOUT_LOGGER = FIRST_PARAMETER_AS_DATE_TIME + " %2$s: %3$s";

	@Override
	public String format(LogRecord logRecord) {
		long millis = logRecord.getMillis();
		LocalDateTime ldt = LocalDateTime.ofEpochSecond(millis / 1000, 0, ZoneOffset.ofHours(+1));
		String source = buildSource(logRecord);
		String loggerName = logRecord.getLoggerName();
		String message = formatMessage(logRecord);
		String throwable = buildThrowable(logRecord);
		if (loggerName == null) {
			return String.format(FORMAT_WITHOUT_LOGGER, ldt, source, message, throwable);
		} else {
			return String.format(FORMAT, ldt, source, loggerName, message, throwable);
		}
	}

	private String buildSource(LogRecord logRecord) {
		String source;
		if (logRecord.getSourceClassName() != null) {
			source = logRecord.getSourceClassName();
			if (logRecord.getSourceMethodName() != null) {
				source += "." + logRecord.getSourceMethodName() + "()";
			}
		} else {
			source = logRecord.getLoggerName();
		}
		return source;
	}

	private String buildThrowable(LogRecord logRecord) {
		String throwable = null;
		if (logRecord.getThrown() != null) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			pw.println();
			logRecord.getThrown().printStackTrace(pw);
			pw.close();
			throwable = sw.toString();
		}
		return throwable;
	}

}
