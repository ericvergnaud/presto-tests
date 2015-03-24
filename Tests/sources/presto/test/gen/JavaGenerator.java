package presto.test.gen;
import java.io.File;
import java.io.IOException;


public class JavaGenerator extends Generator {
	
	static final String ROOT = "presto-java/Java-Core/src/test/java/";

	@Override
	protected void enterSubdir(File subDir) {
	}

	@Override
	protected void exitSubdir(File subDir) throws IOException {
		if(runtimeE!=null)
			endRuntimeE();
		if(runtimeO!=null)
			endRuntimeO();
		if(runtimeP!=null)
			endRuntimeP();
		if(translateEOE!=null)
			endTranslateEOE();
		if(translateEPE!=null)
			endTranslateEPE();
		if(translateOEO!=null)
			endTranslateOEO();
		if(translateOPO!=null)
			endTranslateOPO();
		closeAll();
	}

	@Override
	protected void addToTranslateEOE(String dirName, String fileName) throws IOException {
		if(translateEOE==null) {
			String capDirName = capitalize(dirName);
			String testFilePath = ROOT + "presto/translate/eoe/Test" + capDirName + ".java";
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
		translateEOE.write("package presto.translate.eoe;\n");
		translateEOE.write("\n");
		translateEOE.write("import org.junit.Test;\n");
		translateEOE.write("\n");
		translateEOE.write("import presto.parser.e.BaseEParserTest;\n");
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
	protected void addToTranslateEPE(String dirName, String fileName) throws IOException {
		if(translateEPE==null) {
			String capDirName = capitalize(dirName);
			String testFilePath = ROOT + "presto/translate/epe/Test" + capDirName + ".java";
			translateEPE = mkfile(testFilePath);
			beginTranslateEPE(capDirName);
		}
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		translateEPE.write("\t@Test\n");
		translateEPE.write("\tpublic void test");
		translateEPE.write(capFileName);
		translateEPE.write("() throws Exception {\n");
		translateEPE.write("\t\tcompareResourceEPE(\"");
		translateEPE.write(dirName);
		translateEPE.write("/");
		translateEPE.write(fileName);
		translateEPE.write("\");\n");
		translateEPE.write("\t}\n");
		translateEPE.write("\n");
	}

	private void beginTranslateEPE(String dirName) throws IOException {
		translateEPE.write("package presto.translate.epe;\n");
		translateEPE.write("\n");
		translateEPE.write("import org.junit.Test;\n");
		translateEPE.write("\n");
		translateEPE.write("import presto.parser.e.BaseEParserTest;\n");
		translateEPE.write("\n");
		translateEPE.write("public class Test");
		translateEPE.write(dirName);
		translateEPE.write(" extends BaseEParserTest {\n");
		translateEPE.write("\n");
	}

	private void endTranslateEPE() throws IOException {
		translateEPE.write("}\n");
		translateEPE.write("\n");
	}

	@Override
	protected void addToTranslateOEO(String dirName, String fileName) throws IOException {
		if(translateOEO==null) {
			String capDirName = capitalize(dirName);
			String testFilePath = ROOT + "presto/translate/oeo/Test" + capDirName + ".java";
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
		translateOEO.write("package presto.translate.oeo;\n");
		translateOEO.write("\n");
		translateOEO.write("import org.junit.Test;\n");
		translateOEO.write("\n");
		translateOEO.write("import presto.parser.o.BaseOParserTest;\n");
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
	protected void addToTranslateOPO(String dirName, String fileName) throws IOException {
		if(translateOPO==null) {
			String capDirName = capitalize(dirName);
			String testFilePath = ROOT + "presto/translate/opo/Test" + capDirName + ".java";
			translateOPO = mkfile(testFilePath);
			beginTranslateOPO(capDirName);
		}
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		translateOPO.write("\t@Test\n");
		translateOPO.write("\tpublic void test");
		translateOPO.write(capFileName);
		translateOPO.write("() throws Exception {\n");
		translateOPO.write("\t\tcompareResourceOPO(\"");
		translateOPO.write(dirName);
		translateOPO.write("/");
		translateOPO.write(fileName);
		translateOPO.write("\");\n");
		translateOPO.write("\t}\n");
		translateOPO.write("\n");
	}

	private void beginTranslateOPO(String dirName) throws IOException {
		translateOPO.write("package presto.translate.opo;\n");
		translateOPO.write("\n");
		translateOPO.write("import org.junit.Test;\n");
		translateOPO.write("\n");
		translateOPO.write("import presto.parser.o.BaseOParserTest;\n");
		translateOPO.write("\n");
		translateOPO.write("public class Test");
		translateOPO.write(dirName);
		translateOPO.write(" extends BaseOParserTest {\n");
		translateOPO.write("\n");
	}

	private void endTranslateOPO() throws IOException {
		translateOPO.write("}\n");
		translateOPO.write("\n");
	}

	@Override
	protected void addToTranslatePEP(String dirName, String fileName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void addToTranslatePOP(String dirName, String fileName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void addToRuntimeE(String dirName, String fileName) throws Exception {
		if(runtimeE==null) {
			String capDirName = capitalize(dirName);
			String testFilePath = ROOT + "presto/runtime/e/Test" + capDirName + ".java";
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
		runtimeE.write("package presto.runtime.e;\n");
		runtimeE.write("\n");
		runtimeE.write("import org.junit.After;\n");
		runtimeE.write("import org.junit.Before;\n");
		runtimeE.write("import org.junit.Test;\n");
		runtimeE.write("\n");
		runtimeE.write("import presto.parser.e.BaseEParserTest;\n");
		runtimeE.write("import presto.runtime.utils.Out;\n");
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
			String testFilePath = ROOT + "presto/runtime/o/Test" + capDirName + ".java";
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
		runtimeO.write("package presto.runtime.o;\n");
		runtimeO.write("\n");
		runtimeO.write("import org.junit.After;\n");
		runtimeO.write("import org.junit.Before;\n");
		runtimeO.write("import org.junit.Test;\n");
		runtimeO.write("\n");
		runtimeO.write("import presto.parser.o.BaseOParserTest;\n");
		runtimeO.write("import presto.runtime.utils.Out;\n");
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
	protected void addToRuntimeP(String dirName, String fileName) throws IOException {
		if(runtimeP==null) {
			String capDirName = capitalize(dirName);
			String testFilePath = ROOT + "presto/runtime/p/Test" + capDirName + ".java";
			runtimeP = mkfile(testFilePath);
			beginRuntimeP(capDirName);
		}
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		runtimeP.write("\t@Test\n");
		runtimeP.write("\tpublic void test");
		runtimeP.write(capFileName);
		runtimeP.write("() throws Exception {\n");
		runtimeP.write("\t\tcheckOutput(\"");
		runtimeP.write(dirName);
		runtimeP.write("/");
		runtimeP.write(fileName);
		runtimeP.write("\");\n");
		runtimeP.write("\t}\n");
		runtimeP.write("\n");
	}
	
	private void beginRuntimeP(String cdirName) throws IOException {
		runtimeP.write("package presto.runtime.p;\n");
		runtimeP.write("\n");
		runtimeP.write("import org.junit.After;\n");
		runtimeP.write("import org.junit.Before;\n");
		runtimeP.write("import org.junit.Test;\n");
		runtimeP.write("\n");
		runtimeP.write("import presto.parser.p.BasePParserTest;\n");
		runtimeP.write("import presto.runtime.utils.Out;\n");
		runtimeP.write("\n");
		runtimeP.write("public class Test");
		runtimeP.write(cdirName);
		runtimeP.write(" extends BasePParserTest {\n");
		runtimeP.write("\n");
		runtimeP.write("\t@Before\n");
		runtimeP.write("\tpublic void before() {\n");
		runtimeP.write("\t\tOut.init();\n");
		runtimeP.write("\t}\n");
		runtimeP.write("\n");
		runtimeP.write("\t@After\n");
		runtimeP.write("\tpublic void after() {\n");
		runtimeP.write("\t\tOut.restore();\n");
		runtimeP.write("\t}\n");
		runtimeP.write("\n");
	}

	private void endRuntimeP() throws IOException {
		runtimeP.write("}\n");
		runtimeP.write("\n");
	}



	
	
}
