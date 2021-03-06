package clui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Run clui commands from a text file. 
 * Write the output from the stdout to a resultFile names appropriately.
 * @author animato
 *
 */
public class RunCommandsFromFile {

		
	/**
	 * Reads from file and writes to new file the result. 
	 * @param filename
	 * @param clui
	 */
	public static void run(String filename, CLUIThread clui) {
		String message = "";
		String trimmedFilename = filename.substring(0, filename.length()-4);
		FileReader file = null;
		BufferedReader reader = null;
		FileWriter writer = null;
		File outputFile = new File("src/eval/" + trimmedFilename + "Result.txt");
		
		try {
			file = new FileReader("src/eval/" + filename);
			reader = new BufferedReader(file);
			String line = "";
			
			System.out.println("Reading scenario file " + filename);
			outputFile.createNewFile();
			// creates a FileWriter Object
			writer = new FileWriter(outputFile, true);

			while ((line = reader.readLine()) != null) { // read the file linebyline
				// pass line to CLUIThread.parseUserInput
				// ignores empty lines or lines starting with #
				if (line.length() > 0 && !line.substring(0, 1).equals("#")) {
					message = clui.parseUserInput(line);
					writer.write(message + "\n");
					System.out.println(message + "\n");
				}
			}
			System.out.println("Scenario completed! Results were stored in " + trimmedFilename + "Result.txt");
		} catch (FileNotFoundException e) {
			System.out.print("File not found. If scenario file is in eval folder, the filepath should be src/eval/filename> \n");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (reader != null) {
					reader.close();
					if (file != null) {
						file.close();
					}
				}
				if (writer != null) {
					writer.flush();
					writer.close();  
				}
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
