package presto.test.gen;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;


public abstract class Generator {

	public static void main(String[] args) throws Exception {
		List<Generator> generators = buildGenerators();
		for(Generator g : generators)
			g.generate();
	}

	private static List<Generator> buildGenerators() {
		List<Generator> list = new ArrayList<Generator>();
		list.add(new JavaGenerator());
		list.add(new CSharpGenerator());
		list.add(new Python2Generator());
		list.add(new Python3Generator());
		list.add(new JavaScriptGenerator());
		return list;
	}

	private void generate() throws Exception {
		String path = readResourcesPath();
		File rootDir = new File(path);
		File[] subDirs = rootDir.listFiles();
		for(File subDir : subDirs) {
			if(!subDir.isDirectory())
				continue;
			generate(subDir);
		}
	}

	private boolean isSpecialDir(String dirName) {
		switch(dirName) {
		case "resource":
		case "issues":
		case "debug":
			return true;
		default:
			return false;
		}
	}

	private void generate(File subDir) throws Exception {
		String subDirName = subDir.getName();
		enterSubdir(subDir);
		String[] fileNames = subDir.list();
		for(String fileName : fileNames) {
			// translate all test cases
			if(fileName.endsWith(".pec")) {
				addToTranslateEOE(subDirName, fileName);
				addToTranslateESE(subDirName, fileName);
			} else if(fileName.endsWith(".poc")) {
				addToTranslateOEO(subDirName, fileName);
				addToTranslateOSO(subDirName, fileName);
			} else if(fileName.endsWith(".psc")) {
				addToTranslateSES(subDirName, fileName);
				addToTranslateSOS(subDirName, fileName);
			}
			// test only standard test cases
			if(isSpecialDir(subDirName) || isSpecialFile(fileName))
				continue;
			if(fileName.endsWith(".pec")) {
				addToRuntimeE(subDirName, fileName);
			} else if(fileName.endsWith(".poc")) {
				addToRuntimeO(subDirName, fileName);
			} else if(fileName.endsWith(".psc")) {
				addToRuntimeS(subDirName, fileName);
			}
		}
		exitSubdir(subDir);
	}
	
	private boolean isSpecialFile(String fileName) {
		fileName = fileName.substring(0, fileName.lastIndexOf('.')).toLowerCase();
		switch(fileName) {
		case "unexpected":
		case "return":
		case "datetimetzname":
			return true;
		default:
			return false;
		}
	}

	protected abstract void enterSubdir(File subDir) throws Exception;
	protected abstract void exitSubdir(File subDir) throws Exception;
	protected abstract void addToTranslateEOE(String dirName, String fileName) throws Exception;
	protected abstract void addToTranslateESE(String dirName, String fileName) throws Exception;
	protected abstract void addToTranslateOEO(String dirName, String fileName) throws Exception;
	protected abstract void addToTranslateOSO(String dirName, String fileName) throws Exception;
	protected abstract void addToTranslateSES(String dirName, String fileName) throws Exception;
	protected abstract void addToTranslateSOS(String dirName, String fileName) throws Exception;
	protected abstract void addToRuntimeE(String dirName, String fileName) throws Exception;
	protected abstract void addToRuntimeO(String dirName, String fileName) throws Exception;
	protected abstract void addToRuntimeS(String dirName, String fileName) throws Exception;

	OutputStreamWriter translateEOE;
	OutputStreamWriter translateESE;
	OutputStreamWriter translateOEO;
	OutputStreamWriter translateOSO;
	OutputStreamWriter translateSES;
	OutputStreamWriter translateSOS;
	OutputStreamWriter runtimeE;
	OutputStreamWriter runtimeO;
	OutputStreamWriter runtimeS;
	
	protected void closeAll() throws IOException {
		if(translateEOE!=null) {
			translateEOE.close();
			translateEOE = null;
		}
		if(translateESE!=null) {
			translateESE.close();
			translateESE = null;
		}
		if(translateOEO!=null) {
			translateOEO.close();
			translateOEO = null;
		}
		if(translateOSO!=null) {
			translateOSO.close();
			translateOSO = null;
		}
		if(translateSES!=null) {
			translateSES.close();
			translateSES = null;
		}
		if(translateSOS!=null) {
			translateSOS.close();
			translateSOS = null;
		}
		if(runtimeE!=null) {
			runtimeE.close();
			runtimeE = null;
		}
		if(runtimeO!=null) {
			runtimeO.close();
			runtimeO = null;
		}
		if(runtimeS!=null) {
			runtimeS.close();
			runtimeS = null;
		}
	}


	protected String readResourcesPath() {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		String thisPath = loader.getResource("").getFile();
		return thisPath.substring(0, thisPath.lastIndexOf("/target/")) + "/resources/";
	}
	
	protected String readPrestoPath() {
		String testsPath = readResourcesPath();
		return testsPath.substring(0, testsPath.lastIndexOf("/presto-tests/")) + "/";
	}

	protected String capitalize(String s) {
		return s.substring(0,1).toUpperCase() + s.substring(1);
	}

	protected OutputStreamWriter mkfile(String filePath) throws IOException {
		// filePath is relative to Presto
		String fullPath = readPrestoPath() + filePath;
		String parentPath = fullPath.substring(0, fullPath.lastIndexOf("/"));
		File file = new File(parentPath);
		file.mkdirs();
		return new OutputStreamWriter(new FileOutputStream(fullPath));
	}


}
