package muscaa.clichat.shared.command;

import java.util.Arrays;

import fluff.commander.Commander;
import fluff.commander.CommanderConfig;
import fluff.commander.argument.IArgumentInput;
import fluff.commander.argument.StringArgumentInput;
import fluff.commander.command.ICommand;
import fluff.commander.command.OutputBuilder;
import muscaa.clichat.shared.command.commands.CommandHelp;

public abstract class AbstractCommander<C extends AbstractCommander<C, S>, S extends ISharedCommandSource> extends Commander<C, S> {
	
	public AbstractCommander() {
		super(false, "cli");
	}
	
	@Override
	public void init() {
		command(new CommandHelp());
	}
	
	public CommandResult execute(S source, String input) {
		int exitCode;
		String error;
		
		try {
			IArgumentInput in = new StringArgumentInput(input);
			
			exitCode = execute(source, in);
			error = null;
		} catch (Exception e) {
			exitCode = FAIL;
			error = e.getMessage();
			
			source.error(error);
		}
		
		return new CommandResult(exitCode, error);
	}
	
	@Override
	protected boolean shouldGenerateHelp() {
		return false;
	}
	
	@Override
	public void generateHelp(OutputBuilder ob) {
		if (commands.isEmpty()) {
			ob.append("No commands available.")
	        		.newLine();
			return;
		}
		
		ob.append("Help:")
				.newLine();
		
		ob.tab();
		{
			for (ICommand cmd : commands.getNotIgnored()) {
				String[] cmdNames = cmd.getNames();
				String cmdDescription = cmd.getDescription();
				
				ob.append(cmdNames[0])
						.append(":")
						.newLine();
				
				ob.tab();
				{
					if (cmdDescription != null) {
						ob.append("Description: ")
								.append(cmdDescription)
								.newLine();
					}
					if (cmdNames.length > 1) {
						ob.append("Alias: ")
								.append(String.join(CommanderConfig.SEPARATOR_OR, Arrays.copyOfRange(cmdNames, 1, cmdNames.length)))
								.newLine();
					}
					ob.append("Usage: ")
							.append(cmd.getUsage())
							.newLine();
				}
				ob.untab();
			}
		}
		ob.untab();
	}
	
	@Override
	public int help(ICommand command, S source) {
		OutputBuilder ob = new OutputBuilder();
		command.generateHelp(ob);
		source.info(ob.getOutput());
		return HELP;
	}
}
