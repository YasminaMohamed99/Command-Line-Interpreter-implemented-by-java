
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {

		Scanner in = new Scanner(System.in);
		String input;

		do {
			input = in.nextLine();
			if (input.equals(""))
				continue;

			if (input.equals("exit") || input.equals("Exit")) {
				break;
			} else {
				String[] commands = input.split("\\|");
				for (int i = 0; i < commands.length; i++) {
					executeCommand(commands[i]);
				}
			}
		} while (true);

		in.close();
	}

	private static void writeResultOnFile(Parser parser, String result) {
		String filePath = parser.getRedirectPath();
		String currentDir = Terminal.getCurrentDir();
		String fullPath = currentDir + "\\" + filePath;
		
		boolean isAppend = false;
		if (parser.getRedirectCommand().equals(">>")){
			isAppend = true;
		}
		
		File outputFile = new File(fullPath);
		if (outputFile.exists() == false) {
			try {
				outputFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
		    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fullPath, isAppend)));
		    out.println(result);
		    out.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	public static void executeCommand(String command) throws IOException {
		Parser parser = new Parser();
		boolean isParsed = parser.parse(command);

		if (isParsed == false) {
			System.out.println("Command is not valid :(");
			return;
		}

		String cmd = parser.getCmd();
		String[] args = parser.getArgs();
		String result = "";
		
		if (cmd.equals("pwd")) {
			result = Terminal.pwd();
				
		} else if (cmd.equals("help") && args.length == 0)
			result = Terminal.help();
		else if (cmd.equals("help") && args.length == 1)
			result = Terminal.help(args[0]);
		else if (cmd.equals("more"))
			Terminal.more(args[0]);
		else if (cmd.equals("args"))
			result = Terminal.args(args[0]);
		else if (cmd.equals("ls"))
			result = Terminal.ls();
		else if (cmd.equals("clear"))
			Terminal.clear();
		else if (cmd.equals("rmdir"))
			Terminal.deletedDirectory(args[0]);
		else if (cmd.equals("mkdir"))
			Terminal.mkdir(args[0]);
		else if (cmd.equals("date"))
			result = Terminal.date();
		else if (cmd.equals("rm"))
			Terminal.deleteFile(args[0]);
		else if (cmd.equals("mk"))
			Terminal.createFile(args[0]);
		else if (cmd.equals("cd"))
			Terminal.changeDir(args[0]);
		else if (cmd.equals("cd.."))
			Terminal.changeDir_();
		else if (cmd.equals("cp"))
			Terminal.copy(args[0], args[1], args[2]);
		else if (cmd.equals("mv"))
			Terminal.move(args[0], args[1], args[2]);
		else if (cmd.equals("cat"))
			Terminal.cat(args);
		else 
			return;
		
		if (parser.isRedirect()) {
			writeResultOnFile(parser, result);
		} else {
			if(!result.equals("")) {
			System.out.println(result);
			}
		}
	}

}
