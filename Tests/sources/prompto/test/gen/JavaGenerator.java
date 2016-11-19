package prompto.test.gen;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;


public class JavaGenerator extends Generator {
	
	static final String CORE_ROOT = "prompto-java/Java-Core/src/test/java/";
	static final String LIB_ROOT = "prompto-java/Java-Runtime/src/test/java/";

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
			String testFilePath = CORE_ROOT + "prompto/translate/ese/Test" + capDirName + ".java";
			translateEME = mkfile(testFilePath);
			beginTranslate(translateEME, capDirName, "E", "EME");
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
			String testFilePath = CORE_ROOT + "prompto/translate/oso/Test" + capDirName + ".java";
			translateOMO = mkfile(testFilePath);
			beginTranslate(translateOMO, capDirName, "O", "OMO");
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
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		if(options.interpreted)
			addToRuntime(runtimeE, capFileName, dirName, fileName, false);
		if(options.compiled)
			addToRuntime(runtimeE, capFileName, dirName, fileName, true);
	}

	@Override
	protected void addToRuntimeO(String dirName, String fileName, Options options) throws IOException {
		if(runtimeO==null) {
			String capDirName = capitalize(dirName);
			String testFilePath = CORE_ROOT + "prompto/runtime/o/Test" + capDirName + ".java";
			runtimeO = mkfile(testFilePath);
			beginRuntime(runtimeO, capDirName, "O");
		}
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		if(options.interpreted)
			addToRuntime(runtimeO, capFileName, dirName, fileName, false);
		if(options.compiled)
			addToRuntime(runtimeO, capFileName, dirName, fileName, true);
	}

	@Override
	protected void addToRuntimeM(String dirName, String fileName, Options options) throws IOException {
		if(runtimeM==null) {
			String capDirName = capitalize(dirName);
			String testFilePath = CORE_ROOT + "prompto/runtime/s/Test" + capDirName + ".java";
			runtimeM = mkfile(testFilePath);
			beginRuntime(runtimeM, capDirName, "M");
		}
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		if(options.interpreted)
			addToRuntime(runtimeM, capFileName, dirName, fileName, false);
		if(options.compiled)
			addToRuntime(runtimeM, capFileName, dirName, fileName, true);
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

	private void addToRuntime(OutputStreamWriter writer, String capFileName, String dirName, String fileName, boolean compiled) throws IOException {
		writer.write("\t@Test\n");
		writer.write("\tpublic void test");
		writer.write(compiled ? "Compiled" : "Interpreted"); 
		writer.write(capFileName);
		writer.write("() throws Exception {\n");
		writer.write("\t\tcheck");
		writer.write(compiled ? "Compiled" : "Interpreted"); 
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
	protected void addToLibraryE(String dirName, String fileName) throws Exception {
		if(libraryE==null) {
			String capDirName = capitalize(dirName);
			String testFilePath = LIB_ROOT + "prompto/library/e/Test" + capDirName + ".java";
			libraryE = mkfile(testFilePath);
			beginLibrary(libraryE, capDirName, "E");
		}
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		addToLibrary(libraryE, capFileName, dirName, fileName);
	}

	@Override
	protected void addToLibraryO(String dirName, String fileName) throws Exception {
		if(libraryO==null) {
			String capDirName = capitalize(dirName);
			String testFilePath = LIB_ROOT + "prompto/library/o/Test" + capDirName + ".java";
			libraryO = mkfile(testFilePath);
			beginLibrary(libraryO, capDirName, "O");
		}
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		addToLibrary(libraryO, capFileName, dirName, fileName);
	}

	@Override
	protected void addToLibraryM(String dirName, String fileName) throws Exception {
		if(libraryM==null) {
			String capDirName = capitalize(dirName);
			String testFilePath = LIB_ROOT + "prompto/library/s/Test" + capDirName + ".java";
			libraryM = mkfile(testFilePath);
			beginLibrary(libraryM, capDirName, "M");
		}
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		addToLibrary(libraryM, capFileName, dirName, fileName);
	}

	private void beginLibrary(OutputStreamWriter writer, String dirName, String dialect) throws IOException {
		writer.write("package prompto.library.");
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
	
	private void addToLibrary(OutputStreamWriter writer, String capFileName, String dirName, String fileName) throws IOException {
		writer.write("\t@Test\n");
		writer.write("\tpublic void test");
		writer.write(capFileName);
		writer.write("() throws Exception {\n");
		writer.write("\t\trunTests(\"");
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
