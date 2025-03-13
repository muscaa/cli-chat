package muscaa.clichat.shared.utils;

import java.util.Objects;

import org.jline.jansi.Ansi;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.InfoCmp;

public class Utils {
	
    public static final Terminal TERMINAL;
    public static final LineReader READER;
    
    static {
        try {
			TERMINAL = TerminalBuilder.terminal();
			READER = LineReaderBuilder.builder().terminal(TERMINAL).build();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
    
	public static void print(Object o) {
		READER.printAbove(Objects.toString(o));
	}
	
	public static String read(Object prompt) {
		String line = null;
		try {
			line = READER.readLine(Objects.toString(prompt));
		} catch (Exception e) {}
		eraseLine();
		return line;
	}
	
	public static void eraseLine() {
		TERMINAL.puts(InfoCmp.Capability.cursor_up);
		TERMINAL.puts(InfoCmp.Capability.carriage_return);
		TERMINAL.puts(InfoCmp.Capability.delete_line);
		TERMINAL.flush();
	}
	
	public static String info(Object o) {
		return Ansi.ansi()
				.fgBrightGreen().a(o)
				.reset()
				.toString();
	}
	
	public static String warn(Object o) {
		return Ansi.ansi()
				.fgBrightYellow().a(o)
				.reset()
				.toString();
	}
	
	public static String error(Object o) {
		return Ansi.ansi()
				.fgBrightRed().a(o)
				.reset()
				.toString()
				;
	}
}
