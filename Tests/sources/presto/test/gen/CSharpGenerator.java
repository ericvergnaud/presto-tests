package presto.test.gen;
import java.io.File;
import java.io.IOException;


public class CSharpGenerator extends Generator {
	
	static final String ROOT = "C#/Core-Test/src/";

	@Override
	protected void enterSubdir(File subDir) {
		
		
	}

	@Override
	protected void exitSubdir(File subDir) throws IOException {
		if(runtimeE!=null)
			endRuntimeE();
		if(runtimeO!=null)
			endRuntimeO();
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
			String testFilePath = ROOT + "presto/translate/eoe/Test" + capDirName + ".cs";
			translateEOE = mkfile(testFilePath);
			beginTranslateEOE(capDirName);
		}
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		translateEOE.write("\t\t[Test]\n");
		translateEOE.write("\t\tpublic void test");
		translateEOE.write(capFileName);
		translateEOE.write("()\n");
		translateEOE.write("\t\t{\n");
		translateEOE.write("\t\t\tcompareResourceEOE(\"");
		translateEOE.write(dirName);
		translateEOE.write("/");
		translateEOE.write(fileName);
		translateEOE.write("\");\n");
		translateEOE.write("\t\t}\n");
		translateEOE.write("\n");
	}

	private void beginTranslateEOE(String dirName) throws IOException {
		translateEOE.write("using NUnit.Framework;\n");
		translateEOE.write("using presto.parser;\n");
		translateEOE.write("\n");
		translateEOE.write("namespace presto.translate.eoe\n");
		translateEOE.write("{\n");
		translateEOE.write("\n");
		translateEOE.write("\t[TestFixture]\n");
		translateEOE.write("\tpublic class Test");
		translateEOE.write(dirName);
		translateEOE.write(" : BaseEParserTest\n");
		translateEOE.write("\t{\n");
		translateEOE.write("\n");
	}

	private void endTranslateEOE() throws IOException {
		translateEOE.write("\t}\n");
		translateEOE.write("}\n");
		translateEOE.write("\n");
	}
	
	@Override
	protected void addToTranslateEPE(String dirName, String fileName) throws IOException {
		if(translateEPE==null) {
			String capDirName = capitalize(dirName);
			String testFilePath = ROOT + "presto/translate/epe/Test" + capDirName + ".cs";
			translateEPE = mkfile(testFilePath);
			beginTranslateEPE(capDirName);
		}
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		translateEPE.write("\t\t[Test]\n");
		translateEPE.write("\t\tpublic void test");
		translateEPE.write(capFileName);
		translateEPE.write("()\n");
		translateEPE.write("\t\t{\n");
		translateEPE.write("\t\t\tcompareResourceEPE(\"");
		translateEPE.write(dirName);
		translateEPE.write("/");
		translateEPE.write(fileName);
		translateEPE.write("\");\n");
		translateEPE.write("\t\t}\n");
		translateEPE.write("\n");
	}

	private void beginTranslateEPE(String dirName) throws IOException {
		translateEPE.write("using NUnit.Framework;\n");
		translateEPE.write("using presto.parser;\n");
		translateEPE.write("\n");
		translateEPE.write("namespace presto.translate.epe\n");
		translateEPE.write("{\n");
		translateEPE.write("\n");
		translateEPE.write("\t[TestFixture]\n");
		translateEPE.write("\tpublic class Test");
		translateEPE.write(dirName);
		translateEPE.write(" : BaseEParserTest\n");
		translateEPE.write("\t{\n");
		translateEPE.write("\n");
	}

	private void endTranslateEPE() throws IOException {
		translateEPE.write("\t}\n");
		translateEPE.write("}\n");
		translateEPE.write("\n");
	}

	@Override
	protected void addToTranslateOEO(String dirName, String fileName) throws IOException {
		if(translateOEO==null) {
			String capDirName = capitalize(dirName);
			String testFilePath = ROOT + "presto/translate/oeo/Test" + capDirName + ".cs";
			translateOEO = mkfile(testFilePath);
			beginTranslateOEO(capDirName);
		}
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		translateOEO.write("\t\t[Test]\n");
		translateOEO.write("\t\tpublic void test");
		translateOEO.write(capFileName);
		translateOEO.write("()\n");
		translateOEO.write("\t\t{\n");
		translateOEO.write("\t\t\tcompareResourceOEO(\"");
		translateOEO.write(dirName);
		translateOEO.write("/");
		translateOEO.write(fileName);
		translateOEO.write("\");\n");
		translateOEO.write("\t\t}\n");
		translateOEO.write("\n");
	}

	private void beginTranslateOEO(String dirName) throws IOException {
		translateOEO.write("using NUnit.Framework;\n");
		translateOEO.write("using presto.parser;\n");
		translateOEO.write("\n");
		translateOEO.write("namespace presto.translate.oeo\n");
		translateOEO.write("{\n");
		translateOEO.write("\n");
		translateOEO.write("\t[TestFixture]\n");
		translateOEO.write("\tpublic class Test");
		translateOEO.write(dirName);
		translateOEO.write(" : BaseOParserTest\n");
		translateOEO.write("\t{\n");
		translateOEO.write("\n");
	}

	private void endTranslateOEO() throws IOException {
		translateOEO.write("\t}\n");
		translateOEO.write("}\n");
		translateOEO.write("\n");
	}

	@Override
	protected void addToTranslateOPO(String dirName, String fileName) throws IOException {
		if(translateOPO==null) {
			String capDirName = capitalize(dirName);
			String testFilePath = ROOT + "presto/translate/opo/Test" + capDirName + ".cs";
			translateOPO = mkfile(testFilePath);
			beginTranslateOPO(capDirName);
		}
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		translateOPO.write("\t\t[Test]\n");
		translateOPO.write("\t\tpublic void test");
		translateOPO.write(capFileName);
		translateOPO.write("()\n");
		translateOPO.write("\t\t{\n");
		translateOPO.write("\t\t\tcompareResourceOPO(\"");
		translateOPO.write(dirName);
		translateOPO.write("/");
		translateOPO.write(fileName);
		translateOPO.write("\");\n");
		translateOPO.write("\t\t}\n");
		translateOPO.write("\n");
	}

	private void beginTranslateOPO(String dirName) throws IOException {
		translateOPO.write("using NUnit.Framework;\n");
		translateOPO.write("using presto.parser;\n");
		translateOPO.write("\n");
		translateOPO.write("namespace presto.translate.opo\n");
		translateOPO.write("{\n");
		translateOPO.write("\n");
		translateOPO.write("\t[TestFixture]\n");
		translateOPO.write("\tpublic class Test");
		translateOPO.write(dirName);
		translateOPO.write(" : BaseOParserTest\n");
		translateOPO.write("\t{\n");
		translateOPO.write("\n");
	}

	private void endTranslateOPO() throws IOException {
		translateOPO.write("\t}\n");
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
			String testFilePath = ROOT + "presto/runtime/e/Test" + capDirName + ".cs";
			runtimeE = mkfile(testFilePath);
			beginRuntimeE(capDirName);
		}
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		runtimeE.write("\t\t[Test]\n");
		runtimeE.write("\t\tpublic void test");
		runtimeE.write(capFileName);
		runtimeE.write("()\n");
		runtimeE.write("\t\t{\n");
		runtimeE.write("\t\t\tCheckOutput(\"");
		runtimeE.write(dirName);
		runtimeE.write("/");
		runtimeE.write(fileName);
		runtimeE.write("\");\n");
		runtimeE.write("\t\t}\n");
		runtimeE.write("\n");
	}

	private void beginRuntimeE(String dirName) throws IOException {
		runtimeE.write("using NUnit.Framework;\n");
		runtimeE.write("using presto.parser;\n");
		runtimeE.write("using presto.utils;\n");
		runtimeE.write("\n");
		runtimeE.write("namespace presto.runtime.e\n");
		runtimeE.write("{\n");
		runtimeE.write("\n");
		runtimeE.write("\t[TestFixture]\n");
		runtimeE.write("\tpublic class Test");
		runtimeE.write(dirName);
		runtimeE.write(" : BaseEParserTest\n");
		runtimeE.write("\t{\n");
		runtimeE.write("\n");
		runtimeE.write("\t\t[SetUp]\n");
		runtimeE.write("\t\tpublic void before()\n");
		runtimeE.write("\t\t{\n");
		runtimeE.write("\t\t\tOut.init();\n");
		runtimeE.write("\t\t}\n");
		runtimeE.write("\n");
		runtimeE.write("\t\t[TearDown]\n");
		runtimeE.write("\t\tpublic void after()\n");
		runtimeE.write("\t\t{\n");
		runtimeE.write("\t\t\tOut.restore();\n");
		runtimeE.write("\t\t}\n");
		runtimeE.write("\n");
	}

	private void endRuntimeE() throws IOException {
		runtimeE.write("\t}\n");
		runtimeE.write("}\n");
		runtimeE.write("\n");
	}
	
	@Override
	protected void addToRuntimeO(String dirName, String fileName) throws IOException {
		if(runtimeO==null) {
			String capDirName = capitalize(dirName);
			String testFilePath = ROOT + "presto/runtime/o/Test" + capDirName + ".cs";
			runtimeO = mkfile(testFilePath);
			beginRuntimeO(capDirName);
		}
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		runtimeO.write("\t\t[Test]\n");
		runtimeO.write("\t\tpublic void test");
		runtimeO.write(capFileName);
		runtimeO.write("()\n");
		runtimeO.write("\t\t{\n");
		runtimeO.write("\t\t\tCheckOutput(\"");
		runtimeO.write(dirName);
		runtimeO.write("/");
		runtimeO.write(fileName);
		runtimeO.write("\");\n");
		runtimeO.write("\t\t}\n");
		runtimeO.write("\n");
	}

	private void beginRuntimeO(String dirName) throws IOException {
		runtimeO.write("using NUnit.Framework;\n");
		runtimeO.write("using presto.parser;\n");
		runtimeO.write("using presto.utils;\n");
		runtimeO.write("\n");
		runtimeO.write("namespace presto.runtime.o\n");
		runtimeO.write("{\n");
		runtimeO.write("\n");
		runtimeO.write("\t[TestFixture]\n");
		runtimeO.write("\tpublic class Test");
		runtimeO.write(dirName);
		runtimeO.write(" : BaseOParserTest\n");
		runtimeO.write("\t{\n");
		runtimeO.write("\n");
		runtimeO.write("\t\t[SetUp]\n");
		runtimeO.write("\t\tpublic void before()\n");
		runtimeO.write("\t\t{\n");
		runtimeO.write("\t\t\tOut.init();\n");
		runtimeO.write("\t\t}\n");
		runtimeO.write("\n");
		runtimeO.write("\t\t[TearDown]\n");
		runtimeO.write("\t\tpublic void after()\n");
		runtimeO.write("\t\t{\n");
		runtimeO.write("\t\t\tOut.restore();\n");
		runtimeO.write("\t\t}\n");
		runtimeO.write("\n");
	}

	private void endRuntimeO() throws IOException {
		runtimeO.write("\t}\n");
		runtimeO.write("}\n");
		runtimeO.write("\n");
	}


	@Override
	protected void addToRuntimeP(String dirName, String fileName) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
