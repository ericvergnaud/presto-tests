package prompto.test.gen;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.LocalDateTime;


public class JavaGenerator extends Generator {
	
	static final String CORE_ROOT = "prompto-java/Java-Core/src/test/java/";
	static final String LIB_ROOT = "prompto-java/Java-Libraries/src/test/java/";

	@Override
	protected void enterSubdir(File subDir) {
	}

	@Override
	protected void exitSubdir(File subDir) throws IOException {
		if(libraryE!=null)
			endLibrary(libraryE);
		if(libraryO!=null)
			endLibrary(libraryO);
		if(libraryS!=null)
			endLibrary(libraryS);
		if(runtimeE!=null)
			endRuntime(runtimeE);
		if(runtimeO!=null)
			endRuntime(runtimeO);
		if(runtimeS!=null)
			endRuntime(runtimeS);
		if(translateEOE!=null)
			endTranslate(translateEOE);
		if(translateESE!=null)
			endTranslate(translateESE);
		if(translateOEO!=null)
			endTranslate(translateOEO);
		if(translateOSO!=null)
			endTranslate(translateOSO);
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
	protected void addToTranslateESE(String dirName, String fileName) throws IOException {
		if(translateESE==null) {
			String capDirName = capitalize(dirName);
			String testFilePath = CORE_ROOT + "prompto/translate/ese/Test" + capDirName + ".java";
			translateESE = mkfile(testFilePath);
			beginTranslate(translateESE, capDirName, "E", "ese");
		}
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		addToTranslate(translateESE, capFileName, "ESE", dirName, fileName);
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
	protected void addToTranslateOSO(String dirName, String fileName) throws IOException {
		if(translateOSO==null) {
			String capDirName = capitalize(dirName);
			String testFilePath = CORE_ROOT + "prompto/translate/oso/Test" + capDirName + ".java";
			translateOSO = mkfile(testFilePath);
			beginTranslate(translateOSO, capDirName, "O", "oso");
		}
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		addToTranslate(translateOSO, capFileName, "OSO", dirName, fileName);
	}

	@Override
	protected void addToTranslateSES(String dirName, String fileName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void addToTranslateSOS(String dirName, String fileName) {
		// TODO Auto-generated method stub
		
	}

	private void beginTranslate(OutputStreamWriter writer, String dirName, String dialect, String packageName) throws IOException {
		writer.write("// generated: " + LocalDateTime.now() + "\n");
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
		writer.write("EOE(\"");
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
	protected void addToRuntimeE(String dirName, String fileName) throws Exception {
		if(runtimeE==null) {
			String capDirName = capitalize(dirName);
			String testFilePath = CORE_ROOT + "prompto/runtime/e/Test" + capDirName + ".java";
			runtimeE = mkfile(testFilePath);
			beginRuntime(runtimeE, capDirName, "E");
		}
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		addToRuntime(runtimeE, capFileName, dirName, fileName);
	}

	@Override
	protected void addToRuntimeO(String dirName, String fileName) throws IOException {
		if(runtimeO==null) {
			String capDirName = capitalize(dirName);
			String testFilePath = CORE_ROOT + "prompto/runtime/o/Test" + capDirName + ".java";
			runtimeO = mkfile(testFilePath);
			beginRuntime(runtimeO, capDirName, "O");
		}
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		addToRuntime(runtimeO, capFileName, dirName, fileName);
	}

	@Override
	protected void addToRuntimeS(String dirName, String fileName) throws IOException {
		if(runtimeS==null) {
			String capDirName = capitalize(dirName);
			String testFilePath = CORE_ROOT + "prompto/runtime/s/Test" + capDirName + ".java";
			runtimeS = mkfile(testFilePath);
			beginRuntime(runtimeS, capDirName, "S");
		}
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		addToRuntime(runtimeS, capFileName, dirName, fileName);
	}

	private void beginRuntime(OutputStreamWriter writer, String dirName, String dialect) throws IOException {
		writer.write("// generated: " + LocalDateTime.now() + "\n");
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

	private void addToRuntime(OutputStreamWriter writer, String capFileName, String dirName, String fileName) throws IOException {
		writer.write("\t@Test\n");
		writer.write("\tpublic void test");
		writer.write(capFileName);
		writer.write("() throws Exception {\n");
		writer.write("\t\tcheckOutput(\"");
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
	protected void addToLibraryS(String dirName, String fileName) throws Exception {
		if(libraryS==null) {
			String capDirName = capitalize(dirName);
			String testFilePath = LIB_ROOT + "prompto/library/s/Test" + capDirName + ".java";
			libraryS = mkfile(testFilePath);
			beginLibrary(libraryS, capDirName, "S");
		}
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		addToLibrary(libraryS, capFileName, dirName, fileName);
	}

	private void beginLibrary(OutputStreamWriter writer, String dirName, String dialect) throws IOException {
		writer.write("// generated: " + LocalDateTime.now() + "\n");
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
