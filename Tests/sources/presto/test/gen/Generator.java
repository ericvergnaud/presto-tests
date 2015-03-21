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
			if(fileName.endsWith(".e")) {
				addToTranslateEOE(subDirName, fileName);
				addToTranslateEPE(subDirName, fileName);
			} else if(fileName.endsWith(".o")) {
				addToTranslateOEO(subDirName, fileName);
				addToTranslateOPO(subDirName, fileName);
			} else if(fileName.endsWith(".p")) {
				addToTranslatePEP(subDirName, fileName);
				addToTranslatePOP(subDirName, fileName);
			}
			// test only standard test cases
			if(isSpecialDir(subDirName) || isSpecialFile(fileName))
				continue;
			if(fileName.endsWith(".e")) {
				addToRuntimeE(subDirName, fileName);
			} else if(fileName.endsWith(".o")) {
				addToRuntimeO(subDirName, fileName);
			} else if(fileName.endsWith(".p")) {
				addToRuntimeP(subDirName, fileName);
			}
		}
		exitSubdir(subDir);
	}
	
	private boolean isSpecialFile(String fileName) {
		fileName = fileName.substring(0, fileName.lastIndexOf('.')).toLowerCase();
		switch(fileName) {
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
	protected abstract void addToTranslateEPE(String dirName, String fileName) throws Exception;
	protected abstract void addToTranslateOEO(String dirName, String fileName) throws Exception;
	protected abstract void addToTranslateOPO(String dirName, String fileName) throws Exception;
	protected abstract void addToTranslatePEP(String dirName, String fileName) throws Exception;
	protected abstract void addToTranslatePOP(String dirName, String fileName) throws Exception;
	protected abstract void addToRuntimeE(String dirName, String fileName) throws Exception;
	protected abstract void addToRuntimeO(String dirName, String fileName) throws Exception;
	protected abstract void addToRuntimeP(String dirName, String fileName) throws Exception;

	OutputStreamWriter translateEOE;
	OutputStreamWriter translateEPE;
	OutputStreamWriter translateOEO;
	OutputStreamWriter translateOPO;
	OutputStreamWriter translatePEP;
	OutputStreamWriter translatePOP;
	OutputStreamWriter runtimeE;
	OutputStreamWriter runtimeO;
	OutputStreamWriter runtimeP;
	
	protected void closeAll() throws IOException {
		if(translateEOE!=null) {
			translateEOE.close();
			translateEOE = null;
		}
		if(translateEPE!=null) {
			translateEPE.close();
			translateEPE = null;
		}
		if(translateOEO!=null) {
			translateOEO.close();
			translateOEO = null;
		}
		if(translateOPO!=null) {
			translateOPO.close();
			translateOPO = null;
		}
		if(translatePEP!=null) {
			translatePEP.close();
			translatePEP = null;
		}
		if(translatePOP!=null) {
			translatePOP.close();
			translatePOP = null;
		}
		if(runtimeE!=null) {
			runtimeE.close();
			runtimeE = null;
		}
		if(runtimeO!=null) {
			runtimeO.close();
			runtimeO = null;
		}
		if(runtimeP!=null) {
			runtimeP.close();
			runtimeP = null;
		}
	}


	protected String readResourcesPath() {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		String thisPath = loader.getResource("").getFile();
		return thisPath.substring(0, thisPath.lastIndexOf("/target/")) + "/resources/";
	}
	
	protected String readPrestoPath() {
		String testsPath = readResourcesPath();
		return testsPath.substring(0, testsPath.lastIndexOf("/Test/")) + "/";
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
