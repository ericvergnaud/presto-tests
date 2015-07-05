package prompto.test.gen;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;


public class JavaGenerator extends Generator {
	
	static final String ROOT = "prompto-java/Java-Core/src/test/java/";

	@Override
	protected void enterSubdir(File subDir) {
	}

	@Override
	protected void exitSubdir(File subDir) throws IOException {
		if(runtimeE!=null)
			endRuntimeE();
		if(runtimeO!=null)
			endRuntimeO();
		if(runtimeS!=null)
			endRuntimeS();
		if(translateEOE!=null)
			endTranslateEOE();
		if(translateESE!=null)
			endTranslateESE();
		if(translateOEO!=null)
			endTranslateOEO();
		if(translateOSO!=null)
			endTranslateOSO();
		closeAll();
	}

	@Override
	protected void addToTranslateEOE(String dirName, String fileName) throws IOException {
		if(translateEOE==null) {
			String capDirName = capitalize(dirName);
			String testFilePath = ROOT + "prompto/translate/eoe/Test" + capDirName + ".java";
			translateEOE = mkfile(testFilePath);
			beginTranslateEOE(capDirName);
		}
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		translateEOE.write("\t@Test\n");
		translateEOE.write("\tpublic void test");
		translateEOE.write(capFileName);
		translateEOE.write("() throws Exception {\n");
		translateEOE.write("\t\tcompareResourceEOE(\"");
		translateEOE.write(dirName);
		translateEOE.write("/");
		translateEOE.write(fileName);
		translateEOE.write("\");\n");
		translateEOE.write("\t}\n");
		translateEOE.write("\n");
	}

	private void beginTranslateEOE(String dirName) throws IOException {
		translateEOE.write("// generated: " + LocalDateTime.now() + "\n");
		translateEOE.write("package prompto.translate.eoe;\n");
		translateEOE.write("\n");
		translateEOE.write("import org.junit.Test;\n");
		translateEOE.write("\n");
		translateEOE.write("import prompto.parser.e.BaseEParserTest;\n");
		translateEOE.write("\n");
		translateEOE.write("public class Test");
		translateEOE.write(dirName);
		translateEOE.write(" extends BaseEParserTest {\n");
		translateEOE.write("\n");
	}

	private void endTranslateEOE() throws IOException {
		translateEOE.write("}\n");
		translateEOE.write("\n");
	}
	
	@Override
	protected void addToTranslateESE(String dirName, String fileName) throws IOException {
		if(translateESE==null) {
			String capDirName = capitalize(dirName);
			String testFilePath = ROOT + "prompto/translate/ese/Test" + capDirName + ".java";
			translateESE = mkfile(testFilePath);
			beginTranslateESE(capDirName);
		}
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		translateESE.write("\t@Test\n");
		translateESE.write("\tpublic void test");
		translateESE.write(capFileName);
		translateESE.write("() throws Exception {\n");
		translateESE.write("\t\tcompareResourceESE(\"");
		translateESE.write(dirName);
		translateESE.write("/");
		translateESE.write(fileName);
		translateESE.write("\");\n");
		translateESE.write("\t}\n");
		translateESE.write("\n");
	}

	private void beginTranslateESE(String dirName) throws IOException {
		translateESE.write("// generated: " + LocalDateTime.now() + "\n");
		translateESE.write("package prompto.translate.ese;\n");
		translateESE.write("\n");
		translateESE.write("import org.junit.Test;\n");
		translateESE.write("\n");
		translateESE.write("import prompto.parser.e.BaseEParserTest;\n");
		translateESE.write("\n");
		translateESE.write("public class Test");
		translateESE.write(dirName);
		translateESE.write(" extends BaseEParserTest {\n");
		translateESE.write("\n");
	}

	private void endTranslateESE() throws IOException {
		translateESE.write("}\n");
		translateESE.write("\n");
	}

	@Override
	protected void addToTranslateOEO(String dirName, String fileName) throws IOException {
		if(translateOEO==null) {
			String capDirName = capitalize(dirName);
			String testFilePath = ROOT + "prompto/translate/oeo/Test" + capDirName + ".java";
			translateOEO = mkfile(testFilePath);
			beginTranslateOEO(capDirName);
		}
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		translateOEO.write("\t@Test\n");
		translateOEO.write("\tpublic void test");
		translateOEO.write(capFileName);
		translateOEO.write("() throws Exception {\n");
		translateOEO.write("\t\tcompareResourceOEO(\"");
		translateOEO.write(dirName);
		translateOEO.write("/");
		translateOEO.write(fileName);
		translateOEO.write("\");\n");
		translateOEO.write("\t}\n");
		translateOEO.write("\n");
	}

	private void beginTranslateOEO(String dirName) throws IOException {
		translateOEO.write("// generated: " + LocalDateTime.now() + "\n");
		translateOEO.write("package prompto.translate.oeo;\n");
		translateOEO.write("\n");
		translateOEO.write("import org.junit.Test;\n");
		translateOEO.write("\n");
		translateOEO.write("import prompto.parser.o.BaseOParserTest;\n");
		translateOEO.write("\n");
		translateOEO.write("public class Test");
		translateOEO.write(dirName);
		translateOEO.write(" extends BaseOParserTest {\n");
		translateOEO.write("\n");
	}

	private void endTranslateOEO() throws IOException {
		translateOEO.write("}\n");
		translateOEO.write("\n");
	}

	@Override
	protected void addToTranslateOSO(String dirName, String fileName) throws IOException {
		if(translateOSO==null) {
			String capDirName = capitalize(dirName);
			String testFilePath = ROOT + "prompto/translate/oso/Test" + capDirName + ".java";
			translateOSO = mkfile(testFilePath);
			beginTranslateOSO(capDirName);
		}
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		translateOSO.write("\t@Test\n");
		translateOSO.write("\tpublic void test");
		translateOSO.write(capFileName);
		translateOSO.write("() throws Exception {\n");
		translateOSO.write("\t\tcompareResourceOSO(\"");
		translateOSO.write(dirName);
		translateOSO.write("/");
		translateOSO.write(fileName);
		translateOSO.write("\");\n");
		translateOSO.write("\t}\n");
		translateOSO.write("\n");
	}

	private void beginTranslateOSO(String dirName) throws IOException {
		translateOSO.write("// generated: " + LocalDateTime.now() + "\n");
		translateOSO.write("package prompto.translate.oso;\n");
		translateOSO.write("\n");
		translateOSO.write("import org.junit.Test;\n");
		translateOSO.write("\n");
		translateOSO.write("import prompto.parser.o.BaseOParserTest;\n");
		translateOSO.write("\n");
		translateOSO.write("public class Test");
		translateOSO.write(dirName);
		translateOSO.write(" extends BaseOParserTest {\n");
		translateOSO.write("\n");
	}

	private void endTranslateOSO() throws IOException {
		translateOSO.write("}\n");
		translateOSO.write("\n");
	}

	@Override
	protected void addToTranslateSES(String dirName, String fileName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void addToTranslateSOS(String dirName, String fileName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void addToRuntimeE(String dirName, String fileName) throws Exception {
		if(runtimeE==null) {
			String capDirName = capitalize(dirName);
			String testFilePath = ROOT + "prompto/runtime/e/Test" + capDirName + ".java";
			runtimeE = mkfile(testFilePath);
			beginRuntimeE(capDirName);
		}
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		runtimeE.write("\t@Test\n");
		runtimeE.write("\tpublic void test");
		runtimeE.write(capFileName);
		runtimeE.write("() throws Exception {\n");
		runtimeE.write("\t\tcheckOutput(\"");
		runtimeE.write(dirName);
		runtimeE.write("/");
		runtimeE.write(fileName);
		runtimeE.write("\");\n");
		runtimeE.write("\t}\n");
		runtimeE.write("\n");
	}

	private void beginRuntimeE(String dirName) throws IOException {
		runtimeE.write("// generated: " + LocalDateTime.now() + "\n");
		runtimeE.write("package prompto.runtime.e;\n");
		runtimeE.write("\n");
		runtimeE.write("import org.junit.After;\n");
		runtimeE.write("import org.junit.Before;\n");
		runtimeE.write("import org.junit.Test;\n");
		runtimeE.write("\n");
		runtimeE.write("import prompto.parser.e.BaseEParserTest;\n");
		runtimeE.write("import prompto.runtime.utils.Out;\n");
		runtimeE.write("\n");
		runtimeE.write("public class Test");
		runtimeE.write(dirName);
		runtimeE.write(" extends BaseEParserTest {\n");
		runtimeE.write("\n");
		runtimeE.write("\t@Before\n");
		runtimeE.write("\tpublic void before() {\n");
		runtimeE.write("\t\tOut.init();\n");
		runtimeE.write("\t}\n");
		runtimeE.write("\n");
		runtimeE.write("\t@After\n");
		runtimeE.write("\tpublic void after() {\n");
		runtimeE.write("\t\tOut.restore();\n");
		runtimeE.write("\t}\n");
		runtimeE.write("\n");
	}

	private void endRuntimeE() throws IOException {
		runtimeE.write("}\n");
		runtimeE.write("\n");
	}
	
	@Override
	protected void addToRuntimeO(String dirName, String fileName) throws IOException {
		if(runtimeO==null) {
			String capDirName = capitalize(dirName);
			String testFilePath = ROOT + "prompto/runtime/o/Test" + capDirName + ".java";
			runtimeO = mkfile(testFilePath);
			beginRuntimeO(capDirName);
		}
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		runtimeO.write("\t@Test\n");
		runtimeO.write("\tpublic void test");
		runtimeO.write(capFileName);
		runtimeO.write("() throws Exception {\n");
		runtimeO.write("\t\tcheckOutput(\"");
		runtimeO.write(dirName);
		runtimeO.write("/");
		runtimeO.write(fileName);
		runtimeO.write("\");\n");
		runtimeO.write("\t}\n");
		runtimeO.write("\n");
	}

	private void beginRuntimeO(String cdirName) throws IOException {
		runtimeO.write("// generated: " + LocalDateTime.now() + "\n");
		runtimeO.write("package prompto.runtime.o;\n");
		runtimeO.write("\n");
		runtimeO.write("import org.junit.After;\n");
		runtimeO.write("import org.junit.Before;\n");
		runtimeO.write("import org.junit.Test;\n");
		runtimeO.write("\n");
		runtimeO.write("import prompto.parser.o.BaseOParserTest;\n");
		runtimeO.write("import prompto.runtime.utils.Out;\n");
		runtimeO.write("\n");
		runtimeO.write("public class Test");
		runtimeO.write(cdirName);
		runtimeO.write(" extends BaseOParserTest {\n");
		runtimeO.write("\n");
		runtimeO.write("\t@Before\n");
		runtimeO.write("\tpublic void before() {\n");
		runtimeO.write("\t\tOut.init();\n");
		runtimeO.write("\t}\n");
		runtimeO.write("\n");
		runtimeO.write("\t@After\n");
		runtimeO.write("\tpublic void after() {\n");
		runtimeO.write("\t\tOut.restore();\n");
		runtimeO.write("\t}\n");
		runtimeO.write("\n");
	}

	private void endRuntimeO() throws IOException {
		runtimeO.write("}\n");
		runtimeO.write("\n");
	}


	@Override
	protected void addToRuntimeS(String dirName, String fileName) throws IOException {
		if(runtimeS==null) {
			String capDirName = capitalize(dirName);
			String testFilePath = ROOT + "prompto/runtime/s/Test" + capDirName + ".java";
			runtimeS = mkfile(testFilePath);
			beginRuntimeS(capDirName);
		}
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		runtimeS.write("\t@Test\n");
		runtimeS.write("\tpublic void test");
		runtimeS.write(capFileName);
		runtimeS.write("() throws Exception {\n");
		runtimeS.write("\t\tcheckOutput(\"");
		runtimeS.write(dirName);
		runtimeS.write("/");
		runtimeS.write(fileName);
		runtimeS.write("\");\n");
		runtimeS.write("\t}\n");
		runtimeS.write("\n");
	}
	
	private void beginRuntimeS(String cdirName) throws IOException {
		runtimeS.write("// generated: " + LocalDateTime.now() + "\n");
		runtimeS.write("package prompto.runtime.s;\n");
		runtimeS.write("\n");
		runtimeS.write("import org.junit.After;\n");
		runtimeS.write("import org.junit.Before;\n");
		runtimeS.write("import org.junit.Test;\n");
		runtimeS.write("\n");
		runtimeS.write("import prompto.parser.s.BaseSParserTest;\n");
		runtimeS.write("import prompto.runtime.utils.Out;\n");
		runtimeS.write("\n");
		runtimeS.write("public class Test");
		runtimeS.write(cdirName);
		runtimeS.write(" extends BaseSParserTest {\n");
		runtimeS.write("\n");
		runtimeS.write("\t@Before\n");
		runtimeS.write("\tpublic void before() {\n");
		runtimeS.write("\t\tOut.init();\n");
		runtimeS.write("\t}\n");
		runtimeS.write("\n");
		runtimeS.write("\t@After\n");
		runtimeS.write("\tpublic void after() {\n");
		runtimeS.write("\t\tOut.restore();\n");
		runtimeS.write("\t}\n");
		runtimeS.write("\n");
	}

	private void endRuntimeS() throws IOException {
		runtimeS.write("}\n");
		runtimeS.write("\n");
	}



	
	
}
