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
		Options options = parseCmdLine(args);
		options = adjustToContext(options);
		List<Generator> generators = buildGenerators(options);
		for(Generator g : generators)
			g.generate(options);
	}

	private static Options adjustToContext(Options options) {
		String path = readPromptoPath();
		if(path.endsWith("/prompto/"))
			return options;
		// if running as git submodule, only generate tests for the parent git project
		options.java = false;
		options.csharp = false;
		options.python2 = false;
		options.python3 = false;
		options.javascript = false;
		if(path.endsWith("/prompto-java/"))
			options.java = true;
		else if(path.endsWith("/prompto-csharp/"))
			options.csharp = true;
		else if(path.endsWith("/prompto-python2/"))
			options.python2 = true;
		else if(path.endsWith("/prompto-python3/"))
			options.python3 = true;
		else if(path.endsWith("/prompto-javascript/"))
			options.javascript = true;
		return options;
	}

	private static Options parseCmdLine(String[] args) {
		Options o = new Options();
		for(String arg : args) {
			if("compiledOnly".equals(arg))
				o.interpreted = false;
			else if("interpretedOnly".equals(arg))
				o.compiled = false;
		}
		return o;
	}

	private static List<Generator> buildGenerators(Options options) {
		List<Generator> list = new ArrayList<Generator>();
		if(options.java)
			list.add(new JavaGenerator());
		if(options.csharp)
			list.add(new CSharpGenerator());
		if(options.python2)
			list.add(new Python2Generator());
		if(options.python3)
			list.add(new Python3Generator());
		if(options.javascript)
			list.add(new JavaScriptGenerator());
		return list;
	}

	protected static String readResourcesPath() {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		String thisPath = loader.getResource("").getFile();
		return thisPath.substring(0, thisPath.lastIndexOf("/target/")) + "/resources/";
	}
	
	protected static String readPromptoPath() {
		String testsPath = readResourcesPath();
		return testsPath.substring(0, testsPath.lastIndexOf("/prompto-tests/")) + "/";
	}

	protected static String readLibrariesPath() {
		String promptoPath = readPromptoPath();
		return promptoPath + "prompto-libraries/";
	}

	protected static String capitalize(String s) {
		return s.substring(0,1).toUpperCase() + s.substring(1);
	}

	protected static OutputStreamWriter mkfile(String filePath) throws IOException {
		// filePath is relative to Prompto
		String promptoPath = readPromptoPath();
		// when running as a git submodule, adjust the path accordingly
		String fullPath = checkPathForSubmodule(promptoPath,filePath);
		String parentPath = fullPath.substring(0, fullPath.lastIndexOf("/"));
		File file = new File(parentPath);
		file.mkdirs();
		return new OutputStreamWriter(new FileOutputStream(fullPath));
	}

	private static String checkPathForSubmodule(String parentPath, String filePath) {
		// if parentPath ends with file parent, then skip file parent
		String[] parentParts = parentPath.split("/");
		String[] fileParts = filePath.split("/");
		if(parentParts[parentParts.length-1].equals(fileParts[0]))
			return parentPath + filePath.substring(fileParts[0].length() + 1);
		else
			return parentPath + filePath;
	}

	static interface FileGenerator {
		void generate(String dirName, String fileName, Options options) throws Exception;
	}
	
	private void generate(Options options) throws Exception {
		generate(readResourcesPath(), this::generateRuntimeTests, options,
				"resourceError", "issues", "debug", "comment", "unexpected", "return", "dateTimeTZOffset", "dateTimeTZName");
		generate(readResourcesPath(), this::generateTranslateTests, options);
		generate(readLibrariesPath(), this::generateLibraryTests, options, "concat");
	}

	
	private void generate(String path, FileGenerator generator, Options options, String ... _excluded) throws Exception {
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
				generator.generate(dirName, fileName, options);
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

	private void generateLibraryTests(String dirName, String fileName, Options options) throws Exception {
		if(fileName.endsWith(".pec")) {
			addToLibraryE(dirName, fileName);
		} else if(fileName.endsWith(".poc")) {
			addToLibraryO(dirName, fileName);
		} else if(fileName.endsWith(".psc")) {
			addToLibraryS(dirName, fileName);
		}
	}

	private void generateRuntimeTests(String dirName, String fileName, Options options) throws Exception {
		if(fileName.endsWith(".pec")) {
			addToRuntimeE(dirName, fileName, options);
		} else if(fileName.endsWith(".poc")) {
			addToRuntimeO(dirName, fileName, options);
		} else if(fileName.endsWith(".psc")) {
			addToRuntimeS(dirName, fileName, options);
		}
	}
	
	private void generateTranslateTests(String dirName, String fileName, Options options) throws Exception {
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
	protected abstract void addToRuntimeE(String dirName, String fileName, Options options) throws Exception;
	protected abstract void addToRuntimeO(String dirName, String fileName, Options options) throws Exception;
	protected abstract void addToRuntimeS(String dirName, String fileName, Options options) throws Exception;
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




}
