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
		void generate(File subDir, String fileName, Options options) throws Exception;
	}
	
	private void generate(Options options) throws Exception {
		generate(readResourcesPath(), "runtime", this::generateRuntimeTests, new Options().withExclusions( new Exclusions()
				.withExcludedDirs(Arrays.asList("resourceError", "issues", "debug", "comment", "annotations", "manual", "problems"))
				.withExcludedFiles(Arrays.asList("unexpected", "return", "dateTimeTZOffset", "dateTimeTZName", "global", "empty", "widget2"))
				.withExclusion((dir, file, target, type) -> 
					"add".equals(dir.getName()) && "addCss.pec".equals(file) && type == TestType.COMPILED && target == Target.JAVA)
				.withExclusion((dir, file, target, type) -> 
					"add".equals(dir.getName()) && "addCss.poc".equals(file) && type == TestType.COMPILED && target == Target.JAVA)
				.withExclusion((dir, file, target, type) -> 
					"native".equals(dir.getName()) && "attribute.pec".equals(file) && type == TestType.TRANSPILED && target == Target.JAVA)
				.withExclusion((dir, file, target, type) -> 
					"native".equals(dir.getName()) && file.startsWith("printer.") && type == TestType.TRANSPILED && target == Target.JAVA)
				.withExclusion((dir, file, target, type) -> 
					"widget".equals(dir.getName()) && type == TestType.COMPILED && target == Target.JAVA)
				.withExclusion((dir, file, target, type) -> !hasOutputFile(dir, file))));
		generate(readResourcesPath(), "problems", this::generateProblemTests, new Options().withExclusions(new Exclusions()
				.withExclusion((dir, file, target, type) -> !hasProblemsFile(dir, file))));
		generate(readResourcesPath(), "suggestions/o", this::generateSuggestionsTests, new Options().withExclusions(new Exclusions()
				.withExclusion((dir, file, target, type) -> !hasSuggestionsFile(dir, file))));
		generate(readResourcesPath(), "suggestions/e", this::generateSuggestionsTests, new Options().withExclusions(new Exclusions()
				.withExclusion((dir, file, target, type) -> !hasSuggestionsFile(dir, file))));
		generate(readResourcesPath(), "suggestions/m", this::generateSuggestionsTests, new Options().withExclusions(new Exclusions()
				.withExclusion((dir, file, target, type) -> !hasSuggestionsFile(dir, file))));
		generate(readResourcesPath(), "translate", this::generateTranslateTests, new Options().withExclusions(new Exclusions()
				.withExcludedDirs(Arrays.asList("problems", "suggestions"))
				.withExcludedFiles(Collections.singletonList("widget2"))));
		generate(readLibrariesPath(), "library", this::generateLibraryTests, new Options().withExclusions(new Exclusions()
				.withExcludedDirs(Arrays.asList("web", "react-bootstrap-3"))
				.withExcludedFiles(Collections.singletonList("concat"))
				.withExclusion((dir, file, target, type) -> 
					"attribute.pec".equals(file) && type == TestType.TRANSPILED)));
	}
	
	private boolean hasOutputFile(File dir, String file) {
		return hasFileWithExtension(dir, file, ".txt");
	}

	private boolean hasProblemsFile(File dir, String file) {
		return hasFileWithExtension(dir, file, ".problems.yml");
	}

	private boolean hasSuggestionsFile(File dir, String file) {
		String extension = "." + file.substring(file.length()-2, file.length()-1) + ".suggestions.yml";
		return hasFileWithExtension(dir, file, extension);
	}

	private boolean hasFileWithExtension(File dir, String file, String extension) {
		int idx = file.lastIndexOf(".");
		if(idx >= 0)
			file = file.substring(0, idx);
		File checkedFile = new File(dir, file + extension);
		// System.out.println(checkedFile.getAbsolutePath() + (checkedFile.exists() ? " exists" : " is missing"));
		return checkedFile.exists();
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
				if(options.exclusions.isExcludedFile(subDir, plainName, getTarget()))
					continue;
				generator.generate(subDir, fileName, options);
			}
			exitSubdir(subDir);
		}
	}

	protected abstract Target getTarget();

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

	private void generateLibraryTests(File subDir, String fileName, Options options) throws Exception {
		if(fileName.endsWith(".pec")) {
			addToLibraryE(subDir, fileName, options);
		} else if(fileName.endsWith(".poc")) {
			addToLibraryO(subDir, fileName, options);
		} else if(fileName.endsWith(".pmc")) {
			addToLibraryM(subDir, fileName, options);
		}
	}

	private void generateRuntimeTests(File subDir, String fileName, Options options) throws Exception {
		if(fileName.endsWith(".pec")) {
			addToRuntimeE(subDir, fileName, options);
		} else if(fileName.endsWith(".poc")) {
			addToRuntimeO(subDir, fileName, options);
		} else if(fileName.endsWith(".pmc")) {
			addToRuntimeM(subDir, fileName, options);
		}
	}
	
	private void generateProblemTests(File subDir, String fileName, Options options) throws Exception {
		if(fileName.endsWith(".pec")) {
			addToProblemsE(subDir, fileName, options);
		} else if(fileName.endsWith(".poc")) {
			addToProblemsO(subDir, fileName, options);
		} else if(fileName.endsWith(".pmc")) {
			addToProblemsM(subDir, fileName, options);
		}
	}

	private void generateSuggestionsTests(File subDir, String fileName, Options options) throws Exception {
		if(fileName.endsWith(".pec")) {
			addToSuggestionsE(subDir, fileName, options);
		} else if(fileName.endsWith(".poc")) {
			addToSuggestionsO(subDir, fileName, options);
		} else if(fileName.endsWith(".pmc")) {
			addToSuggestionsM(subDir, fileName, options);
		}
	}

	private void generateTranslateTests(File subDir, String fileName, Options options) throws Exception {
		if(fileName.endsWith(".pec")) {
			addToTranslateEOE(subDir, fileName);
			addToTranslateEME(subDir, fileName);
		} else if(fileName.endsWith(".poc")) {
			addToTranslateOEO(subDir, fileName);
			addToTranslateOMO(subDir, fileName);
		} else if(fileName.endsWith(".pmc")) {
			addToTranslateMEM(subDir, fileName);
			addToTranslateMOM(subDir, fileName);
		}
	}
	
	protected abstract void enterSubdir(File subDir) throws Exception;
	protected abstract void exitSubdir(File subDir) throws Exception;
	protected abstract void addToTranslateEOE(File subDir, String fileName) throws Exception;
	protected abstract void addToTranslateEME(File subDir, String fileName) throws Exception;
	protected abstract void addToTranslateOEO(File subDir, String fileName) throws Exception;
	protected abstract void addToTranslateOMO(File subDir, String fileName) throws Exception;
	protected abstract void addToTranslateMEM(File subDir, String fileName) throws Exception;
	protected abstract void addToTranslateMOM(File subDir, String fileName) throws Exception;
	protected abstract void addToRuntimeE(File subDir, String fileName, Options options) throws Exception;
	protected abstract void addToRuntimeO(File subDir, String fileName, Options options) throws Exception;
	protected abstract void addToRuntimeM(File subDir, String fileName, Options options) throws Exception;
	protected void addToProblemsE(File subDir, String fileName, Options options) throws Exception {}
	protected void addToProblemsO(File subDir, String fileName, Options options) throws Exception {}
	protected void addToProblemsM(File subDir, String fileName, Options options) throws Exception {}
	protected void addToSuggestionsE(File subDir, String fileName, Options options) throws Exception {}
	protected void addToSuggestionsO(File subDir, String fileName, Options options) throws Exception {}
	protected void addToSuggestionsM(File subDir, String fileName, Options options) throws Exception {}
	protected abstract void addToLibraryE(File subDir, String fileName, Options options) throws Exception;
	protected abstract void addToLibraryO(File subDir, String fileName, Options options) throws Exception;
	protected abstract void addToLibraryM(File subDir, String fileName, Options options) throws Exception;

	OutputStreamWriter translateEOE;
	OutputStreamWriter translateEME;
	OutputStreamWriter translateOEO;
	OutputStreamWriter translateOMO;
	OutputStreamWriter translateMEM;
	OutputStreamWriter translateMOM;
	OutputStreamWriter runtimeE;
	OutputStreamWriter runtimeO;
	OutputStreamWriter runtimeM;
	OutputStreamWriter problemsE;
	OutputStreamWriter problemsO;
	OutputStreamWriter problemsM;
	OutputStreamWriter suggestionsE;
	OutputStreamWriter suggestionsO;
	OutputStreamWriter suggestionsM;
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
		if(problemsE!=null) {
			problemsE.close();
			problemsE = null;
		}
		if(problemsO!=null) {
			problemsO.close();
			problemsO = null;
		}
		if(problemsM!=null) {
			problemsM.close();
			problemsM = null;
		}
		if(suggestionsE!=null) {
			suggestionsE.close();
			suggestionsE = null;
		}
		if(suggestionsO!=null) {
			suggestionsO.close();
			suggestionsO = null;
		}
		if(suggestionsM!=null) {
			suggestionsM.close();
			suggestionsM = null;
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
