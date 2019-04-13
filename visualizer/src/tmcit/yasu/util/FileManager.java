package tmcit.yasu.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileManager {
	private File procon30Directory, settingDirectory, mapDirectory, solverDirectory;

	public FileManager() {
		init();
		createFolder();
	}

	private void init() {
		String procon30Path = System.getProperty("user.home") + "\\procon30";
		procon30Directory = new File(procon30Path);
		settingDirectory = new File(procon30Path.toString() + "\\setting");
		mapDirectory = new File(procon30Path.toString() + "\\map");
		solverDirectory = new File(procon30Path.toString() + "\\solver");
	}

	private void createFolder() {
		if(!procon30Directory.isDirectory()) {
			procon30Directory.mkdir();
		}
		if(!settingDirectory.isDirectory()) {
			settingDirectory.mkdir();
		}
		if(!mapDirectory.isDirectory()) {
			mapDirectory.mkdir();
		}
		if(!solverDirectory.isDirectory()) {
			solverDirectory.mkdir();
		}
	}

	private void recursiveDeleteFile(File file) {
		if(!file.exists()) {
			return;
		}
		if(file.isDirectory()) {
			for(File child : file.listFiles()) {
				recursiveDeleteFile(child);
			}
		}
		boolean res = file.delete();
		System.out.println(res);
	}

	public File getMapDirectory() {
		return mapDirectory;
	}

	public String[] getSolverList() {
		String[] solverList = solverDirectory.list();
		return solverList;
	}

	public void makeSolverDir(String solverName) {
		File newFile = new File(solverDirectory.getAbsolutePath() + "\\" + solverName);
		if(!newFile.exists()) {
			newFile.mkdir();
		}
	}

	public void removeSolverDir(String solverName) {
		File removeFile = new File(solverDirectory.getAbsolutePath() + "\\" + solverName);
		System.out.println("[SYS]Delete File:" + removeFile.getAbsolutePath());
		recursiveDeleteFile(removeFile);
	}

	public String getSelectedSolverExePath(String solverName) {
		File readFile = new File(solverDirectory.getAbsolutePath() + "\\" + solverName + "\\default.txt");

		String ret = "";
		if(readFile.exists()) {
			try {
				BufferedReader br = new BufferedReader(new FileReader(readFile));
				ret = br.readLine();
				br.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return ret;
	}

	public ArrayList<String[]> getSelectedSolverParameter(String solverName){
		File readFile = new File(solverDirectory.getAbsolutePath() + "\\" + solverName + "\\default.txt");

		if(!readFile.exists()) {
			return new ArrayList<String[]>();
		}

		ArrayList<String[]> ret = new ArrayList<String[]>();

		try {
			BufferedReader br = new BufferedReader(new FileReader(readFile));

			br.readLine();

			String line;
			while((line = br.readLine()) != null) {
				String[] split = line.split(" ");
				ret.add(split);
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return ret;
	}

	public void saveSolverParameter(String solverName, String presetName, ArrayList<String[]> parameters, String exePath) {
		File writeFile = new File(solverDirectory.getAbsoluteFile() + "\\" + solverName + "\\" + presetName + ".txt");
		if(!writeFile.exists()) {
			try {
				writeFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			FileWriter fw = new FileWriter(writeFile);
			fw.write(exePath + "\n");
			for(String[] nowParam : parameters) {
				String paramName = nowParam[0], paramText = nowParam[1], paramDefaultValue = nowParam[2];
				fw.write(paramName + " " + paramText + " " + paramDefaultValue + "\n");
			}
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}