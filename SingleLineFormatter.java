package logger;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;

/** @see SimpleFormatter */
public class SingleLineFormatter extends Formatter {

	private static final String FORMAT = "%1$tb %1$td, %1$tY %1$tl:%1$tM:%1$tS %1$Tp %2$s %4$s: %5$s";

	@Override
	public String format(LogRecord logRecord) {
		ZonedDateTime zdt = ZonedDateTime.ofInstant(logRecord.getInstant(), ZoneId.systemDefault());
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
		String localizedName = logRecord.getLevel().getLocalizedName();
		return String.format(FORMAT, zdt, source, logRecord.getLoggerName(), localizedName, message, throwable);
	}

}
