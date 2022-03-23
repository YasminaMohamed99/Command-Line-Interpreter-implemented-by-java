
import java.util.*;

public class Parser {
	
//	private  ArrayList<Pair<String, Integer>> mainCommands = new  ArrayList<Pair<String, Integer>>(){{
//		add(new Pair<String, Integer>( "cd", 1 ));
//		add(new Pair<String, Integer>( "cp", 2 ));
//		add(new Pair<String, Integer>( "cd", 1 ));
//		add(new Pair<String, Integer>( "cd", 1 ));
//		add(new Pair<String, Integer>( "cd", 1 ));
//		add(new Pair<String, Integer>( "cd", 1 ));
//		add(new Pair<String, Integer>( "cd", 1 ));
//	}};
	
	private String[] args;
	private String cmd;
	private boolean isRedirect = false;
	private String redirectCommand;
	private String redirectPath;
	
	private String checkRedirectCommand(String input) {
		String[] inputFeilds = input.split(">>");
		if (inputFeilds.length == 2){
			isRedirect = true;
			redirectCommand = ">>";
			redirectPath = inputFeilds[1].trim();
			return inputFeilds[0].trim();
		}
		
		inputFeilds = input.split(">");
		if (inputFeilds.length == 2){
			isRedirect = true;
			redirectCommand = ">";
			redirectPath = inputFeilds[1].trim();
			return inputFeilds[0].trim();
		}
		
		return input;
	}
	
	public boolean parse (String input) {
		
		input = checkRedirectCommand(input);
		
		String[] inputFeilds = input.split(" ");
		if (inputFeilds.length == 0) return false;
		
		cmd = inputFeilds[0].trim();
		
		int numOfArgs = inputFeilds.length - 1;
		args = new String[numOfArgs];
		for (int i = 0; i < numOfArgs; i++) {
			args[i] = inputFeilds[i + 1].trim();
		}
		
		return isValidCommand();
	}
	
	private boolean isValidCommand() {
		
		if (cmd.equals("pwd") && args.length == 0) return true;
		else if (cmd.equals("help") && (args.length == 0 || args.length == 1)) return true;
		else if (cmd.equals("args") && args.length == 1) return true;
		else if (cmd.equals("more") && args.length == 1) return true;
		else if (cmd.equals("ls") && args.length == 0) return true;
		else if (cmd.equals("clear") && args.length == 0) return true;
		else if (cmd.equals("mkdir") && args.length == 1) return true;
		else if (cmd.equals("rmdir") && args.length == 1) return true;
		else if (cmd.equals("date") && args.length == 0) return true;
		else if (cmd.equals("rm") && args.length == 1) return true;
		else if (cmd.equals("mk") && args.length == 1) return true;
		else if (cmd.equals("cd") && args.length == 1) return true;
		else if (cmd.equals("cd..") && args.length == 0) return true;
		else if (cmd.equals("cp") && args.length == 3) return true;
		else if (cmd.equals("mv") && args.length == 3) return true;
		else if (cmd.equals("cat") && args.length > 0) return true;
		
		return false;
	}

	public String[] getArgs() {
		return args;
	}

	public String getCmd() {
		return cmd;
	}

	public boolean isRedirect() {
		return isRedirect;
	}

	public String getRedirectCommand() {
		return redirectCommand;
	}

	public String getRedirectPath() {
		return redirectPath;
	}

	

}
