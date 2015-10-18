package prompto.test.gen;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;


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

	static interface FileGenerator {
		void generate(String dirName, String fileName) throws Exception;
	}
	
	private void generate() throws Exception {
		generate(readResourcesPath(), this::generateRuntimeTests, 
				"resource", "issues", "debug", "comment", "unexpected", "return", "dateTimeTZName");
		generate(readResourcesPath(), this::generateTranslateTests);
		generate(readLibrariesPath(), this::generateLibraryTests, "concat");
	}

	
	private void generate(String path, FileGenerator generator, String ... _excluded) throws Exception {
		List<String> excluded = Arrays.asList(_excluded);
		File rootDir = new File(path);
		String[] dirNames = rootDir.list();
		for(String dirName : dirNames) {
			if(excluded.contains(dirName))
				continue;
			File subDir = new File(rootDir, dirName);
			if(!subDir.isDirectory())
				continue;
			loadDependencies(subDir);
			enterSubdir(subDir);
			String[] fileNames = subDir.list(new FilenameFilter() {
				@Override public boolean accept(File dir, String name) {
					return name.endsWith(".pec")
							|| name.endsWith(".poc")
							|| name.endsWith(".psc");
				}
			});
			for(String fileName : fileNames) {
				String plainName = fileName.substring(0, fileName.indexOf('.'));
				if(excluded.contains(plainName))
					continue;
				generator.generate(dirName, fileName);
			}
			exitSubdir(subDir);
		}
	}

	private void loadDependencies(File subDir) throws Exception {
		dependencies = null;
		File file = new File(subDir, ".project");
		if(!file.exists())
			return;
		XPathExpression xpath = XPathFactory.newInstance().newXPath().compile("/projectDescription/projects/project");
		NodeList nodes = (NodeList)xpath.evaluate(new InputSource(new FileInputStream(file)), XPathConstants.NODESET);
		dependencies = new ArrayList<>();
		dependencies.add(subDir.getName());
		for(int i=0;i<nodes.getLength();i++)
			dependencies.add(nodes.item(i).getTextContent());
	}

	private void generateLibraryTests(String dirName, String fileName) throws Exception {
		if(fileName.endsWith(".pec")) {
			addToLibraryE(dirName, fileName);
		} else if(fileName.endsWith(".poc")) {
			addToLibraryO(dirName, fileName);
		} else if(fileName.endsWith(".psc")) {
			addToLibraryS(dirName, fileName);
		}
	}

	private void generateRuntimeTests(String dirName, String fileName) throws Exception {
		if(fileName.endsWith(".pec")) {
			addToRuntimeE(dirName, fileName);
		} else if(fileName.endsWith(".poc")) {
			addToRuntimeO(dirName, fileName);
		} else if(fileName.endsWith(".psc")) {
			addToRuntimeS(dirName, fileName);
		}
	}
	
	private void generateTranslateTests(String dirName, String fileName) throws Exception {
		if(fileName.endsWith(".pec")) {
			addToTranslateEOE(dirName, fileName);
			addToTranslateESE(dirName, fileName);
		} else if(fileName.endsWith(".poc")) {
			addToTranslateOEO(dirName, fileName);
			addToTranslateOSO(dirName, fileName);
		} else if(fileName.endsWith(".psc")) {
			addToTranslateSES(dirName, fileName);
			addToTranslateSOS(dirName, fileName);
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
	protected abstract void addToLibraryE(String dirName, String fileName) throws Exception;
	protected abstract void addToLibraryO(String dirName, String fileName) throws Exception;
	protected abstract void addToLibraryS(String dirName, String fileName) throws Exception;

	OutputStreamWriter translateEOE;
	OutputStreamWriter translateESE;
	OutputStreamWriter translateOEO;
	OutputStreamWriter translateOSO;
	OutputStreamWriter translateSES;
	OutputStreamWriter translateSOS;
	OutputStreamWriter runtimeE;
	OutputStreamWriter runtimeO;
	OutputStreamWriter runtimeS;
	OutputStreamWriter libraryE;
	OutputStreamWriter libraryO;
	OutputStreamWriter libraryS;
	List<String> dependencies; // of library projects
	
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
		if(libraryE!=null) {
			libraryE.close();
			libraryE = null;
		}
		if(libraryO!=null) {
			libraryO.close();
			libraryO = null;
		}
		if(libraryS!=null) {
			libraryS.close();
			libraryS = null;
		}
	}


	protected String readResourcesPath() {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		String thisPath = loader.getResource("").getFile();
		return thisPath.substring(0, thisPath.lastIndexOf("/target/")) + "/resources/";
	}
	
	protected String readPromptoPath() {
		String testsPath = readResourcesPath();
		return testsPath.substring(0, testsPath.lastIndexOf("/prompto-tests/")) + "/";
	}

	protected String readLibrariesPath() {
		String promptoPath = readPromptoPath();
		return promptoPath + "prompto-libraries/";
	}

	protected String capitalize(String s) {
		return s.substring(0,1).toUpperCase() + s.substring(1);
	}

	protected OutputStreamWriter mkfile(String filePath) throws IOException {
		// filePath is relative to Presto
		String fullPath = readPromptoPath() + filePath;
		String parentPath = fullPath.substring(0, fullPath.lastIndexOf("/"));
		File file = new File(parentPath);
		file.mkdirs();
		return new OutputStreamWriter(new FileOutputStream(fullPath));
	}


}
