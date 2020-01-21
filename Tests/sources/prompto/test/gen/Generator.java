package prompto.test.gen;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
			if("compiledOnly".equals(arg)) {
				o.interpreted = false;
				o.transpiled = false;
			} else if("interpretedOnly".equals(arg)) {
				o.compiled = false;
				o.transpiled = false;
			} else if("transpiledOnly".equals(arg)) {
				o.compiled = false;
				o.interpreted = false;
			}
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
		String fullPath = checkPathForSubmodule(promptoPath, filePath);
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

	@FunctionalInterface
	static interface FileGenerator {
		void generate(String dirName, String fileName, Options options) throws Exception;
	}
	
	private void generate(Options options) throws Exception {
		options.exclusions = new Exclusions()
				.withExcludedDirs(Arrays.asList("resourceError", "issues", "debug", "comment", "annotations"))
				.withExcludedFiles(Arrays.asList("unexpected", "return", "dateTimeTZOffset", "dateTimeTZName", "global", "empty", "widget2"))
				.withExclusion((dir, file, target, type) -> 
					"native".equals(dir) && "attribute.pec".equals(file) && type == TestType.TRANSPILED)
				.withExclusion((dir, file, target, type) -> 
					"native".equals(dir) && file.startsWith("printer.") && type == TestType.TRANSPILED)
				.withExclusion((dir, file, target, type) -> 
					"widget".equals(dir) && type == TestType.COMPILED);
		generate(readResourcesPath(), "runtime", this::generateRuntimeTests, options);
		options.exclusions = new Exclusions().withExcludedFiles(Collections.singletonList("widget2"));
		generate(readResourcesPath(), "translate", this::generateTranslateTests, options);
		options.exclusions = new Exclusions().withExcludedFiles(Collections.singletonList("concat"))
				.withExclusion((dir, file, target, type) -> 
					"attribute.pec".equals(file) && type == TestType.TRANSPILED);
		generate(readLibrariesPath(), "library", this::generateLibraryTests, options);
	}

	private void generate(String path, String nature, FileGenerator generator, Options options) throws Exception {
		System.out.println("Generating " + nature + " " + getTarget() + " tests at " + readPromptoPath());
		File rootDir = new File(path);
		String[] dirNames = rootDir.list();
		for(String dirName : dirNames) {
			if(options.exclusions.isExcludedDir(dirName))
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
							|| name.endsWith(".pmc");
				}
			});
			Arrays.sort(fileNames);
			for(String fileName : fileNames) {
				String plainName = fileName.substring(0, fileName.indexOf('.'));
				if(options.exclusions.isExcludedFile(plainName))
					continue;
				generator.generate(dirName, fileName, options);
			}
			exitSubdir(subDir);
		}
	}

	protected abstract String getTarget();

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
			addToLibraryE(dirName, fileName, options);
		} else if(fileName.endsWith(".poc")) {
			addToLibraryO(dirName, fileName, options);
		} else if(fileName.endsWith(".pmc")) {
			addToLibraryM(dirName, fileName, options);
		}
	}

	private void generateRuntimeTests(String dirName, String fileName, Options options) throws Exception {
		if(fileName.endsWith(".pec")) {
			addToRuntimeE(dirName, fileName, options);
		} else if(fileName.endsWith(".poc")) {
			addToRuntimeO(dirName, fileName, options);
		} else if(fileName.endsWith(".pmc")) {
			addToRuntimeM(dirName, fileName, options);
		}
	}
	
	private void generateTranslateTests(String dirName, String fileName, Options options) throws Exception {
		if(fileName.endsWith(".pec")) {
			addToTranslateEOE(dirName, fileName);
			addToTranslateEME(dirName, fileName);
		} else if(fileName.endsWith(".poc")) {
			addToTranslateOEO(dirName, fileName);
			addToTranslateOMO(dirName, fileName);
		} else if(fileName.endsWith(".pmc")) {
			addToTranslateMEM(dirName, fileName);
			addToTranslateMOM(dirName, fileName);
		}
	}
	
	protected abstract void enterSubdir(File subDir) throws Exception;
	protected abstract void exitSubdir(File subDir) throws Exception;
	protected abstract void addToTranslateEOE(String dirName, String fileName) throws Exception;
	protected abstract void addToTranslateEME(String dirName, String fileName) throws Exception;
	protected abstract void addToTranslateOEO(String dirName, String fileName) throws Exception;
	protected abstract void addToTranslateOMO(String dirName, String fileName) throws Exception;
	protected abstract void addToTranslateMEM(String dirName, String fileName) throws Exception;
	protected abstract void addToTranslateMOM(String dirName, String fileName) throws Exception;
	protected abstract void addToRuntimeE(String dirName, String fileName, Options options) throws Exception;
	protected abstract void addToRuntimeO(String dirName, String fileName, Options options) throws Exception;
	protected abstract void addToRuntimeM(String dirName, String fileName, Options options) throws Exception;
	protected abstract void addToLibraryE(String dirName, String fileName, Options options) throws Exception;
	protected abstract void addToLibraryO(String dirName, String fileName, Options options) throws Exception;
	protected abstract void addToLibraryM(String dirName, String fileName, Options options) throws Exception;

	OutputStreamWriter translateEOE;
	OutputStreamWriter translateEME;
	OutputStreamWriter translateOEO;
	OutputStreamWriter translateOMO;
	OutputStreamWriter translateMEM;
	OutputStreamWriter translateMOM;
	OutputStreamWriter runtimeE;
	OutputStreamWriter runtimeO;
	OutputStreamWriter runtimeM;
	OutputStreamWriter libraryE;
	OutputStreamWriter libraryO;
	OutputStreamWriter libraryM;
	List<String> dependencies; // of library projects
	
	protected void closeAll() throws IOException {
		if(translateEOE!=null) {
			translateEOE.close();
			translateEOE = null;
		}
		if(translateEME!=null) {
			translateEME.close();
			translateEME = null;
		}
		if(translateOEO!=null) {
			translateOEO.close();
			translateOEO = null;
		}
		if(translateOMO!=null) {
			translateOMO.close();
			translateOMO = null;
		}
		if(translateMEM!=null) {
			translateMEM.close();
			translateMEM = null;
		}
		if(translateMOM!=null) {
			translateMOM.close();
			translateMOM = null;
		}
		if(runtimeE!=null) {
			runtimeE.close();
			runtimeE = null;
		}
		if(runtimeO!=null) {
			runtimeO.close();
			runtimeO = null;
		}
		if(runtimeM!=null) {
			runtimeM.close();
			runtimeM = null;
		}
		if(libraryE!=null) {
			libraryE.close();
			libraryE = null;
		}
		if(libraryO!=null) {
			libraryO.close();
			libraryO = null;
		}
		if(libraryM!=null) {
			libraryM.close();
			libraryM = null;
		}
	}




}
