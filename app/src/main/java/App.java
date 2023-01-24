import java.io.File;
import java.io.IOException;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;
import java.nio.file.Files;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {
	public String getGreeting() {
		return "Hello World!";
	}

	public static void addDate(String path) throws IOException {
		Path filePath = Paths.get(path);
		if (!Files.exists(filePath)) {
			Files.createFile(filePath);
		}
		Files.write(filePath, (new Date().toString()+"\n").getBytes(), StandardOpenOption.APPEND);
	}

	public static String join(String[] fileNames){
		String joinedText = "";
		//Path currentRelativePath = Paths.get("");
		//String s = currentRelativePath.toAbsolutePath().toString();
		//System.out.println("Current absolute path is: " + s);
		for (int i = 0; i < fileNames.length; i++) {
			String fileName = fileNames[i];
			try (Stream<String> stream =
				Files.lines(Paths.get(fileName))) {
				joinedText += stream.collect(Collectors.joining("\n")) + "\n";
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return joinedText;
	}
	public static String find(String filename, boolean exact) throws IOException {
		String foundPaths =
			Files
				.find(Paths.get("src/main"), 100,
					(path, basicFileAttributes) -> {
						File file = path.toFile();
						if(file.isDirectory()) return false;
						if(exact){
							return file.getName().equals(filename);
						}
						return file.getName().contains(filename);
					})
				.map(Object::toString)
				.collect(Collectors.joining("\n"));
		return foundPaths;
    }
	public static String findAll(String filename) throws IOException {
		return find(filename, false);
    }
	public static void main(String[] args) throws IOException {
		if(args[0].equals("addDate")){
			addDate(args[1]);
			return;
		}

		if(args[0].equals("find")){
			System.out.println(find("file1", true));
			return;
		}
		if(args[0].equals("findAll")){
			System.out.println(findAll(args[1]));
			return;
		}
		else {
			System.out.print(join(args));
		}
	}
}