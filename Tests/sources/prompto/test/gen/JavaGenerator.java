package prompto.test.gen;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;


public class JavaGenerator extends Generator {
	
	static final String CORE_ROOT = "prompto-java/Core/src/test/java/";
	static final String LIB_ROOT = "prompto-java/Runtime/src/test/java/";

	@Override
	protected Target getTarget() {
		return Target.JAVA;
	}
	
	@Override
	protected void enterSubdir(File subDir) {
	}

	@Override
	protected void exitSubdir(File subDir) throws IOException {
		if(libraryE!=null)
			endLibrary(libraryE);
		if(libraryO!=null)
			endLibrary(libraryO);
		if(libraryM!=null)
			endLibrary(libraryM);
		if(runtimeE!=null)
			endRuntime(runtimeE);
		if(runtimeO!=null)
			endRuntime(runtimeO);
		if(runtimeM!=null)
			endRuntime(runtimeM);
		if(problemsE!=null)
			endProblems(problemsE);
		if(problemsO!=null)
			endProblems(problemsO);
		if(problemsM!=null)
			endProblems(problemsM);
		if(translateEOE!=null)
			endTranslate(translateEOE);
		if(translateEME!=null)
			endTranslate(translateEME);
		if(translateOEO!=null)
			endTranslate(translateOEO);
		if(translateOMO!=null)
			endTranslate(translateOMO);
		closeAll();
	}

	@Override
	protected void addToTranslateEOE(File subDir, String fileName) throws IOException {
		if(translateEOE==null) {
			String capDirName = capitalize(subDir.getName());
			String testFilePath = CORE_ROOT + "prompto/translate/eoe/Test" + capDirName + ".java";
			translateEOE = mkfile(testFilePath);
			beginTranslate(translateEOE, capDirName, "E", "eoe");
		}
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		addToTranslate(translateEOE, capFileName, "EOE", subDir.getName(), fileName);
	}
	
	@Override
	protected void addToTranslateEME(File subDir, String fileName) throws IOException {
		if(translateEME==null) {
			String capDirName = capitalize(subDir.getName());
			String testFilePath = CORE_ROOT + "prompto/translate/eme/Test" + capDirName + ".java";
			translateEME = mkfile(testFilePath);
			beginTranslate(translateEME, capDirName, "E", "eme");
		}
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		addToTranslate(translateEME, capFileName, "EME", subDir.getName(), fileName);
	}

	@Override
	protected void addToTranslateOEO(File subDir, String fileName) throws IOException {
		if(translateOEO==null) {
			String capDirName = capitalize(subDir.getName());
			String testFilePath = CORE_ROOT + "prompto/translate/oeo/Test" + capDirName + ".java";
			translateOEO = mkfile(testFilePath);
			beginTranslate(translateOEO, capDirName, "O", "oeo");
		}
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		addToTranslate(translateOEO, capFileName, "OEO", subDir.getName(), fileName);
	}

	@Override
	protected void addToTranslateOMO(File subDir, String fileName) throws IOException {
		if(translateOMO==null) {
			String capDirName = capitalize(subDir.getName());
			String testFilePath = CORE_ROOT + "prompto/translate/omo/Test" + capDirName + ".java";
			translateOMO = mkfile(testFilePath);
			beginTranslate(translateOMO, capDirName, "O", "omo");
		}
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		addToTranslate(translateOMO, capFileName, "OMO", subDir.getName(), fileName);
	}

	@Override
	protected void addToTranslateMEM(File subDir, String fileName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void addToTranslateMOM(File subDir, String fileName) {
		// TODO Auto-generated method stub
		
	}

	private void beginTranslate(OutputStreamWriter writer, String dirName, String dialect, String packageName) throws IOException {
		writer.write("package prompto.translate.");
		writer.write(packageName);
		writer.write(";\n");
		writer.write("\n");
		writer.write("import org.junit.Test;\n");
		writer.write("\n");
		writer.write("import prompto.parser.");
		writer.write(dialect.toLowerCase());
		writer.write(".Base");
		writer.write(dialect.toUpperCase());
		writer.write("ParserTest;\n");
		writer.write("\n");
		writer.write("public class Test");
		writer.write(dirName);
		writer.write(" extends Base");
		writer.write(dialect.toUpperCase());
		writer.write("ParserTest {\n");
		writer.write("\n");
	}

	private void addToTranslate(OutputStreamWriter writer, String capFileName, String methodSuffix, String dirName, String fileName) throws IOException {
		writer.write("\t@Test\n");
		writer.write("\tpublic void test");
		writer.write(capFileName);
		writer.write("() throws Exception {\n");
		writer.write("\t\tcompareResource");
		writer.write(methodSuffix);
		writer.write("(\"");
		writer.write(dirName);
		writer.write("/");
		writer.write(fileName);
		writer.write("\");\n");
		writer.write("\t}\n");
		writer.write("\n");
	}


	private void endTranslate(OutputStreamWriter writer) throws IOException {
		writer.write("}\n");
		writer.write("\n");
	}
	
	@Override
	protected void addToRuntimeE(File subDir, String fileName, Options options) throws Exception {
		if(runtimeE==null) {
			String capDirName = capitalize(subDir.getName());
			String testFilePath = CORE_ROOT + "prompto/runtime/e/Test" + capDirName + ".java";
			runtimeE = mkfile(testFilePath);
			beginRuntime(runtimeE, capDirName, "E");
		}
		addToRuntime(subDir, fileName, options, runtimeE);
	}

	@Override
	protected void addToRuntimeO(File subDir, String fileName, Options options) throws Exception {
		if(runtimeO==null) {
			String capDirName = capitalize(subDir.getName());
			String testFilePath = CORE_ROOT + "prompto/runtime/o/Test" + capDirName + ".java";
			runtimeO = mkfile(testFilePath);
			beginRuntime(runtimeO, capDirName, "O");
		}
		addToRuntime(subDir, fileName, options, runtimeO);
	}

	@Override
	protected void addToRuntimeM(File subDir, String fileName, Options options) throws Exception {
		if(runtimeM==null) {
			String capDirName = capitalize(subDir.getName());
			String testFilePath = CORE_ROOT + "prompto/runtime/m/Test" + capDirName + ".java";
			runtimeM = mkfile(testFilePath);
			beginRuntime(runtimeM, capDirName, "M");
		}
		addToRuntime(subDir, fileName, options, runtimeM);
	}

	private void addToRuntime(File subDir, String fileName, Options options, OutputStreamWriter runtime) throws Exception {
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		if(options.interpreted && !options.exclusions.isExcludedTest(subDir, fileName, Target.JAVA, TestType.INTERPRETED))
			addToRuntime(runtime, capFileName, subDir.getName(), fileName, TestType.INTERPRETED);
		if(options.compiled && !options.exclusions.isExcludedTest(subDir, fileName, Target.JAVA, TestType.COMPILED))
			addToRuntime(runtime, capFileName, subDir.getName(), fileName, TestType.COMPILED);
		if(options.transpiled && !options.exclusions.isExcludedTest(subDir, fileName, Target.JAVA, TestType.TRANSPILED))
			addToRuntime(runtime, capFileName, subDir.getName(), fileName, TestType.TRANSPILED);
	}
	
	private void beginRuntime(OutputStreamWriter writer, String dirName, String dialect) throws IOException {
		writer.write("package prompto.runtime.");
		writer.write(dialect.toLowerCase());
		writer.write(";\n");
		writer.write("\n");
		writer.write("import org.junit.After;\n");
		writer.write("import org.junit.Before;\n");
		writer.write("import org.junit.Test;\n");
		writer.write("\n");
		writer.write("import prompto.parser.");
		writer.write(dialect.toLowerCase());
		writer.write(".Base");
		writer.write(dialect.toUpperCase());
		writer.write("ParserTest;\n");
		writer.write("import prompto.runtime.utils.Out;\n");
		writer.write("\n");
		writer.write("public class Test");
		writer.write(dirName);
		writer.write(" extends Base");
		writer.write(dialect.toUpperCase());
		writer.write("ParserTest {\n");
		writer.write("\n");
		writer.write("\t@Before\n");
		writer.write("\tpublic void before() {\n");
		writer.write("\t\tOut.init();\n");
		writer.write("\t}\n");
		writer.write("\n");
		writer.write("\t@After\n");
		writer.write("\tpublic void after() {\n");
		writer.write("\t\tOut.restore();\n");
		writer.write("\t}\n");
		writer.write("\n");
	}

	private void addToRuntime(OutputStreamWriter writer, String capFileName, String dirName, String fileName, TestType type) throws IOException {
		writer.write("\t@Test\n");
		writer.write("\tpublic void test");
		writer.write(type.toString()); 
		writer.write(capFileName);
		writer.write("() throws Exception {\n");
		writer.write("\t\tcheck");
		writer.write(type.toString()); 
		writer.write("Output(\"");
		writer.write(dirName);
		writer.write("/");
		writer.write(fileName);
		writer.write("\");\n");
		writer.write("\t}\n");
		writer.write("\n");
	}

	private void endRuntime(OutputStreamWriter writer) throws IOException {
		writer.write("}\n");
		writer.write("\n");
	}
	
	
	@Override
	protected void addToProblemsE(File subDir, String fileName, Options options) throws Exception {
		if(problemsE==null) {
			String capDirName = capitalize(subDir.getName());
			String testFilePath = CORE_ROOT + "prompto/problems/e/Test" + capDirName + ".java";
			problemsE = mkfile(testFilePath);
			beginProblems(problemsE, capDirName, "E");
		}
		addToProblems(subDir.getName(), fileName, options, problemsE);
	}


	@Override
	protected void addToProblemsO(File subDir, String fileName, Options options) throws Exception {
		if(problemsO==null) {
			String capDirName = capitalize(subDir.getName());
			String testFilePath = CORE_ROOT + "prompto/problems/o/Test" + capDirName + ".java";
			problemsO = mkfile(testFilePath);
			beginProblems(problemsO, capDirName, "O");
		}
		addToProblems(subDir.getName(), fileName, options, problemsO);
	}

	
	private void beginProblems(OutputStreamWriter writer, String dirName, String dialect) throws IOException {
		writer.write("package prompto.problems.");
		writer.write(dialect.toLowerCase());
		writer.write(";\n");
		writer.write("\n");
		writer.write("import org.junit.Test;\n");
		writer.write("\n");
		writer.write("import prompto.parser.");
		writer.write(dialect.toLowerCase());
		writer.write(".Base");
		writer.write(dialect.toUpperCase());
		writer.write("ParserTest;\n");
		writer.write("\n");
		writer.write("public class Test");
		writer.write(dirName);
		writer.write(" extends Base");
		writer.write(dialect.toUpperCase());
		writer.write("ParserTest {\n");
		writer.write("\n");
	}

	private void addToProblems(String dirName, String fileName, Options options, OutputStreamWriter writer) throws IOException {
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		writer.write("\t@Test\n");
		writer.write("\tpublic void test");
		writer.write(capFileName);
		writer.write("() throws Exception {\n");
		writer.write("\t\tcheck");
		writer.write("Problems(\"");
		writer.write(dirName);
		writer.write("/");
		writer.write(fileName);
		writer.write("\");\n");
		writer.write("\t}\n");
		writer.write("\n");
	}

	private void endProblems(OutputStreamWriter writer) throws IOException {
		writer.write("}\n");
		writer.write("\n");
	}

	@Override
	protected void addToLibraryE(File subDir, String fileName, Options options) throws Exception {
		if(libraryE==null)
			libraryE = beginLibrary(subDir.getName(), 'E');
		addToLibrary(subDir, fileName, options, libraryE);
	}
	
	@Override
	protected void addToLibraryO(File subDir, String fileName, Options options) throws Exception {
		if(libraryO==null)
			libraryO = beginLibrary(subDir.getName(), 'O');
		addToLibrary(subDir, fileName, options, libraryO);
	}

	@Override
	protected void addToLibraryM(File subDir, String fileName, Options options) throws Exception {
		if(libraryM==null)
			libraryM = beginLibrary(subDir.getName(), 'M');
		addToLibrary(subDir, fileName, options, libraryO);
	}

	void addToLibrary(File subDir, String fileName, Options options, OutputStreamWriter library) throws IOException {
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		if(options.interpreted && !options.exclusions.isExcludedTest(subDir, fileName, Target.JAVA, TestType.INTERPRETED))
			addToLibrary(library, capFileName, subDir.getName(), fileName, TestType.INTERPRETED);
		if(options.compiled  && !options.exclusions.isExcludedTest(subDir, fileName, Target.JAVA, TestType.COMPILED))
			addToLibrary(library, capFileName, subDir.getName(), fileName, TestType.COMPILED);
		if(options.transpiled  && !options.exclusions.isExcludedTest(subDir, fileName, Target.JAVA, TestType.TRANSPILED))
			addToLibrary(library, capFileName, subDir.getName(), fileName, TestType.TRANSPILED);
	}

	private OutputStreamWriter beginLibrary(String dirName, Character dialect) throws IOException {
		String capDirName = capitalize(dirName);
		String testFilePath = LIB_ROOT + "prompto/library/" + dialect.toString().toLowerCase() + "/Test" + capDirName + ".java";
		OutputStreamWriter library = mkfile(testFilePath);
		beginLibrary(library, capDirName, dialect);
		return library;
	}

	private void beginLibrary(OutputStreamWriter writer, String dirName, Character dialect) throws IOException {
		String dir = dialect.toString().toLowerCase();
		writer.write("package prompto.library.");
		writer.write(dir);
		writer.write(";\n");
		writer.write("\n");
		writer.write("import org.junit.After;\n");
		writer.write("import org.junit.Before;\n");
		writer.write("import org.junit.Test;\n");
		writer.write("\n");
		writer.write("import prompto.parser.");
		writer.write(dir);
		writer.write(".Base");
		writer.write(dialect.toString());
		writer.write("ParserTest;\n");
		writer.write("import prompto.runtime.utils.Out;\n");
		writer.write("\n");
		writer.write("public class Test");
		writer.write(dirName);
		writer.write(" extends Base");
		writer.write(dialect.toString());
		writer.write("ParserTest {\n");
		writer.write("\n");
		writer.write("\t@Before\n");
		writer.write("\tpublic void before() throws Exception {\n");
		writer.write("\t\tOut.init();\n");
		if(dependencies!=null) {
			for(String s : dependencies) {
				writer.write("\t\tloadDependency(\"");
				writer.write(s);
				writer.write("\");\n");
			}
		}
		writer.write("\t}\n");
		writer.write("\n");
		writer.write("\t@After\n");
		writer.write("\tpublic void after() {\n");
		writer.write("\t\tOut.restore();\n");
		writer.write("\t}\n");
		writer.write("\n");
	}
	
	private void addToLibrary(OutputStreamWriter writer, String capFileName, String dirName, String fileName, TestType type) throws IOException {
		writer.write("\t@Test\n");
		writer.write("\tpublic void test");
		writer.write(type.toString());
		writer.write(capFileName);
		writer.write("() throws Exception {\n");
		writer.write("\t\trun");
		writer.write(type.toString());
		writer.write("Tests(\"");
		writer.write(dirName);
		writer.write("/");
		writer.write(fileName);
		writer.write("\");\n");
		writer.write("\t}\n");
		writer.write("\n");
	}

	private void endLibrary(OutputStreamWriter writer) throws IOException {
		writer.write("}\n");
		writer.write("\n");
	}

	
}
