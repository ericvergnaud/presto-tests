package prompto.test.gen;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;


public class JavaGenerator extends Generator {
	
	static final String CORE_ROOT = "prompto-java/Core/src/test/java/";
	static final String LIB_ROOT = "prompto-java/Runtime/src/test/java/";

	@Override
	protected String getTarget() {
		return "Java";
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
	protected void addToTranslateEOE(String dirName, String fileName) throws IOException {
		if(translateEOE==null) {
			String capDirName = capitalize(dirName);
			String testFilePath = CORE_ROOT + "prompto/translate/eoe/Test" + capDirName + ".java";
			translateEOE = mkfile(testFilePath);
			beginTranslate(translateEOE, capDirName, "E", "eoe");
		}
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		addToTranslate(translateEOE, capFileName, "EOE", dirName, fileName);
	}
	
	@Override
	protected void addToTranslateEME(String dirName, String fileName) throws IOException {
		if(translateEME==null) {
			String capDirName = capitalize(dirName);
			String testFilePath = CORE_ROOT + "prompto/translate/eme/Test" + capDirName + ".java";
			translateEME = mkfile(testFilePath);
			beginTranslate(translateEME, capDirName, "E", "eme");
		}
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		addToTranslate(translateEME, capFileName, "EME", dirName, fileName);
	}

	@Override
	protected void addToTranslateOEO(String dirName, String fileName) throws IOException {
		if(translateOEO==null) {
			String capDirName = capitalize(dirName);
			String testFilePath = CORE_ROOT + "prompto/translate/oeo/Test" + capDirName + ".java";
			translateOEO = mkfile(testFilePath);
			beginTranslate(translateOEO, capDirName, "O", "oeo");
		}
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		addToTranslate(translateOEO, capFileName, "OEO", dirName, fileName);
	}

	@Override
	protected void addToTranslateOMO(String dirName, String fileName) throws IOException {
		if(translateOMO==null) {
			String capDirName = capitalize(dirName);
			String testFilePath = CORE_ROOT + "prompto/translate/omo/Test" + capDirName + ".java";
			translateOMO = mkfile(testFilePath);
			beginTranslate(translateOMO, capDirName, "O", "omo");
		}
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		addToTranslate(translateOMO, capFileName, "OMO", dirName, fileName);
	}

	@Override
	protected void addToTranslateMEM(String dirName, String fileName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void addToTranslateMOM(String dirName, String fileName) {
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
	protected void addToRuntimeE(String dirName, String fileName, Options options) throws Exception {
		if(runtimeE==null) {
			String capDirName = capitalize(dirName);
			String testFilePath = CORE_ROOT + "prompto/runtime/e/Test" + capDirName + ".java";
			runtimeE = mkfile(testFilePath);
			beginRuntime(runtimeE, capDirName, "E");
		}
		addToRuntime(dirName, fileName, options, runtimeE);
	}

	@Override
	protected void addToRuntimeO(String dirName, String fileName, Options options) throws Exception {
		if(runtimeO==null) {
			String capDirName = capitalize(dirName);
			String testFilePath = CORE_ROOT + "prompto/runtime/o/Test" + capDirName + ".java";
			runtimeO = mkfile(testFilePath);
			beginRuntime(runtimeO, capDirName, "O");
		}
		addToRuntime(dirName, fileName, options, runtimeO);
	}

	@Override
	protected void addToRuntimeM(String dirName, String fileName, Options options) throws Exception {
		if(runtimeM==null) {
			String capDirName = capitalize(dirName);
			String testFilePath = CORE_ROOT + "prompto/runtime/m/Test" + capDirName + ".java";
			runtimeM = mkfile(testFilePath);
			beginRuntime(runtimeM, capDirName, "M");
		}
		addToRuntime(dirName, fileName, options, runtimeM);
	}

	private void addToRuntime(String dirName, String fileName, Options options, OutputStreamWriter runtime) throws Exception {
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		if(options.interpreted)
			addToRuntime(runtime, capFileName, dirName, fileName, Type.INTERPRETED);
		if(options.compiled && !isExcludedFromCompiled(dirName, fileName))
			addToRuntime(runtime, capFileName, dirName, fileName, Type.COMPILED);
		if(options.transpiled && !isExcludedFromTranspiled(dirName, fileName))
			addToRuntime(runtime, capFileName, dirName, fileName, Type.TRANSPILED);
	}
	
	private boolean isExcludedFromCompiled(String dirName, String fileName) {
		return "widget".equals(dirName);
	}

	private boolean isExcludedFromTranspiled(String dirName, String fileName) {
		return "native".equals(dirName) && fileName.startsWith("printer.");
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

	private void addToRuntime(OutputStreamWriter writer, String capFileName, String dirName, String fileName, Type type) throws IOException {
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
	protected void addToLibraryE(String dirName, String fileName, Options options) throws Exception {
		if(libraryE==null)
			libraryE = beginLibrary(dirName, 'E');
		addToLibrary(dirName, fileName, options, libraryE);
	}
	
	void addToLibrary(String dirName, String fileName, Options options, OutputStreamWriter library) throws IOException {
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		if(options.interpreted)
			addToLibrary(library, capFileName, dirName, fileName, Type.INTERPRETED);
		if(options.compiled)
			addToLibrary(library, capFileName, dirName, fileName, Type.COMPILED);
		if(options.transpiled)
			addToLibrary(library, capFileName, dirName, fileName, Type.TRANSPILED);
	}

	private OutputStreamWriter beginLibrary(String dirName, Character dialect) throws IOException {
		String capDirName = capitalize(dirName);
		String testFilePath = LIB_ROOT + "prompto/library/" + dialect.toString().toLowerCase() + "/Test" + capDirName + ".java";
		OutputStreamWriter library = mkfile(testFilePath);
		beginLibrary(library, capDirName, dialect);
		return library;
	}

	@Override
	protected void addToLibraryO(String dirName, String fileName, Options options) throws Exception {
		if(libraryO==null)
			libraryO = beginLibrary(dirName, 'O');
		addToLibrary(dirName, fileName, options, libraryO);
	}

	@Override
	protected void addToLibraryM(String dirName, String fileName, Options options) throws Exception {
		if(libraryM==null)
			libraryM = beginLibrary(dirName, 'M');
		addToLibrary(dirName, fileName, options, libraryO);
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
	
	private void addToLibrary(OutputStreamWriter writer, String capFileName, String dirName, String fileName, Type type) throws IOException {
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
