import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class Terminal {

	public static StringBuilder passed = new StringBuilder();
	public static boolean isPipe = false;

	private static String currentDir = "/home/yasmina/Desktop/root";
	
	public static String getCurrentDir() {
		return currentDir;
	}

	public static String pwd() {
		return currentDir;
	}

	public static String help(String command) {
		if (command.equals("cd"))
			return ("cd: Changes current working directory");
		else if (command.equals("pwd"))
			return ("pwd : Display current directory");
		else if (command.equals("ls"))
			return ("ls: List all contents of current directory");
		else if (command.equals("mv"))
			return ("mv: Moves files from directory to another directory");
		else if (command.equals("cp"))
			return ("cp: Copies files");
		else if (command.equals("mkdir"))
			return ("mkdir: Creates a new directory");
		else if (command.equals("rmdir"))
			return ("rmdir: Deletes a directory");
		else if (command.equals("rm"))
			return ("rm: Deletes a file");
		else if (command.equals("cat"))
			return ("cat: Displays contents of a file and concatenates files and display output");
		else if (command.equals("args"))
			return ("args: List all commands arguments");
		else if (command.equals("date"))
			return ("date: Displays system date and time");
		else
			return ("Command not found!");
	}

	public static String help() {
		return ("cd: [arg] Changes current working directory. The default directory is the value of the HOME\n"
				+ "pwd : [no arg] Display current working directory\n"
				+ "ls:[no arg] List all names of files of current directory sorted alphabetically\n"
				+ "mv: [arg1] [arg2] Moves files from directory to another director\n"
				+ "cp: [arg1] [arg2] Copies files to another directory\n"
				+ "mkdir:[arg]  Creates a new directory\nrmdir: [arg] Deletes a directory\n"
				+ "rm: [arg] Deletes a file\ncat: [arg1] displays contents of arg1(file)\n"
				+ "cat: [arg1] [arg2] concatenates contents of arg1 to contents of arg2 and displays the result\n"
				+ "args: [arg] List all commands arguments\ndate:[no arg] Displays system date and time\n");
	}

	public static String args(String command) {
		if (command.equals("cd"))
			return ("cd: [arg] changes working directory to the given arg");
		else if (command.equals("ls"))
			return ("ls: [no arg] displays contents of a file");
		else if (command.equals("cp"))
			return ("cp: [arg1] [arg2] copies contents of arg1(file) to arg2(file) + directory argN");
		else if (command.equals("mv"))
			return ("mv: [arg1] [arg2] copies contents of arg1(file) to arg2(file) and deletes arg1");
		else if (command.equals("mkdir"))
			return ("mkdir: [arg] creates a directory with whose name is the given argument");
		else if (command.equals("rmdir"))
			return ("rmdir: [arg] deletes a directory whose name is given argument");
		else if (command.equals("rm"))
			return ("rm: [arg] deletes a file whose name is the given argument");
		else if (command.equals("cat"))
			return("cat: [arg1] displays contents of arg1(file)");
		else if (command.equals("cat"))
			return("cat: [arg1] [arg2] concatenates contents of arg1 to contents of arg2 and displays the result");
		else if (command.equals("date"))
			return("date : [noargs] displays system date and time");
		else
			return("Command not found");
	}
	
	public static void changeDir_() {
		String[] newCur = currentDir.split("/");
		String newDir = "";
		int m = 0;
		for(int i = 0 ; i < (newCur.length)-1 ; i++) {
			newDir += newCur[i];
			m++;
			if(m == (newCur.length)-1) {
				break;
			} else {
				newDir += "/";
			}
		}
		currentDir = newDir;
		System.out.println(currentDir);
	}

	public static void changeDir(String path) {
		String fullPath;
		if (new File(path).exists()) {
			fullPath = path;
			currentDir = fullPath;

		} else if (new File(currentDir + "/" + path).exists()) {
			fullPath = currentDir + "/" + path;
			currentDir = fullPath;
			System.out.println(currentDir);

		} else {
			System.out.println("Folder not found!");
		}
	}

	public static void more(String path) {
		String fullPath;
		if (new File(path).exists()) {
			fullPath = path;

		} else if (new File(currentDir + "/" + path).exists()) {
			fullPath = currentDir + "/" + path;

		} else {
			System.out.println("File not found!");
			return;
		}

		try {
			Scanner scanner = new Scanner(System.in);
			Scanner fileScanner = new Scanner(new File(fullPath));

			ArrayList<String> fileContent = new ArrayList<String>();
			while (fileScanner.hasNext()) {
				if (fileContent.size() < 10) {
					fileContent.add(fileScanner.nextLine());
				} else {
					for (int i = 0; i < fileContent.size(); i++) {
						if (i == fileContent.size() - 1)
							System.out.print(fileContent.get(i));
						else
							System.out.println(fileContent.get(i));
					}
					fileContent.clear();
					scanner.nextLine();
				}
			}
			if (fileContent.size() > 0) {
				for (int i = 0; i < fileContent.size(); i++) {
					if (i == fileContent.size() - 1)
						System.out.print(fileContent.get(i));
					else
						System.out.println(fileContent.get(i));
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("No such file or directory");
		}
	}

	public static String date() {
		Date date = new Date();
		return (date.toString());
	}

	public static String ls() {
		File file = new File(currentDir);
		File[] sortedFiles = file.listFiles();
		Arrays.sort(sortedFiles);
		String sorted = "";

		int m = 0;
		for (File f : sortedFiles) {
			sorted += (f.getName());
			m++;
			if(m == (sortedFiles.length)) {
				break;
			} else {
				sorted += "\n";
			}
		}
		return sorted;
	}

	public static void clear() {

		for (int i = 0; i < 60; i++) {
			System.out.println('\f');
		}
	}

	public static void mkdir(String path) {

		File file = new File(currentDir + "/" + path);
		// check if the pathname already exists
		// if not create it
		if (!file.exists()) {
			// create the folder
			boolean result = file.mkdir();
			if (result) {
				System.out.println("Successfully created " + file.getAbsolutePath());
			} else {
				System.out.println("Failed creating " + file.getAbsolutePath());
			}
		} else {
			System.out.println("Pathname already exists");
		}
	}

	private static boolean deleteDirectory(File file) {
		File[] allContents = file.listFiles();
		if (allContents != null) {
			for (File _file : allContents) {
				deleteDirectory(_file);
			}
		}
		return file.delete();
	}

	public static void deletedDirectory(String path) {
		String fullPath;
		if (new File(path).exists()) {
			fullPath = path;

		} else if (new File(currentDir + "/" + path).exists()) {
			fullPath = currentDir + "/" + path;

		} else {
			System.out.println("Folder not found!");
			return;
		}

		System.out.println(fullPath);
		File file = new File(fullPath);

		if (deleteDirectory(file)) {
			System.out.println("File deleted successfully");
		} else {
			System.out.println("Failed to delete the file\nNot Found");
		}
	}

	public static void createFile(String text) throws IOException {
		
		File file = new File(currentDir + "/" + text);
		
		if (!file.exists()) {
			file.createNewFile();
			System.out.println("Successfully created");
			
		} else {
			System.out.println("File name already exists");
			
		}
	}

	public static void deleteFile(String path) {
		String fullPath;
		if (new File(path).exists()) {
			fullPath = path;

		} else if (new File(currentDir + "/" + path).exists()) {
			fullPath = currentDir + "/" + path;

		} else {
			System.out.println("Folder not found!");
			return;
		}

		File file = new File(fullPath);

		if (file.delete()) {
			System.out.println("File deleted successfully");
		} else {
			System.out.println("Failed to delete the file\nNot Found");
		}
	}

	public static void copy(String option, String src, String des) {

		String path;
		if (new File(src).exists()) {
			path = src;

		} else if (new File(currentDir + "/" + src).exists()) {
			path = currentDir + "/" + src;

		} else {
			System.out.println("Folder not found!");
			return;
		}

		File source = new File(path);

		String fileName = source.getName();

		if (new File(currentDir + "/" + des).exists()) {
			des = currentDir + "/" + des;

		} else if (new File(des).exists() == false) {
			System.out.println("Folder not found!");
			return;
		}

		File target = new File(des + "/" + fileName);

		try {
			if (option.equals("-f")) {
				CopyUtil.copySingleFile(source, target);
			} else if (option.equals("-r")) {
				CopyUtil.copyDirectory(source, target);
			} else if (option.equals("-i") || target.exists()) {
				System.out.println("Do you want to overwrite it (Y / N) ?");
				char c;
				Scanner scanner = new Scanner(System.in);
				c = scanner.next().charAt(0);
				if (c == 'Y' || c == 'y') {
					if (target.isFile()) {
						CopyUtil.copySingleFile(source, target);
					} else {
						CopyUtil.copyDirectory(source, target);
					}
				}
				scanner.close();
			}
		} catch (Exception e) {
			System.out.println("No such file or directory");
		}
	}

	public static void move(String option, String src, String des) {

		copy(option, src, des);
		if (option.equals("-f")) {
			deleteFile(src);
		} else if (option.equals("-r")) {
			deletedDirectory("/home/yasmina/Desktop/root/" + src);
		}

	}

	public static void cat(String[] filesPaths) {
		for(String filePath : filesPaths) {
			try {
				String fullPath;
				if (new File(filePath).exists()) {
					fullPath = filePath;

				} else if (new File(currentDir + "/" + filePath).exists()) {
					fullPath = currentDir + "/" + filePath;

				} else {
					System.out.println("file not found!");
					return;
				}
				
				Scanner fileScanner = new Scanner(new File(fullPath));
				while(fileScanner.hasNextLine()) {
					System.out.println(fileScanner.nextLine());
				}
				fileScanner.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
		}
	}
}
