package presto.test.gen;
import java.io.File;
import java.io.IOException;

public abstract class PythonGenerator extends Generator {
	
	protected abstract String getRoot();

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
			String testFilePath = getRoot() + "presto/translate/eoe/Test" + capDirName + ".py";
			translateEOE = mkfile(testFilePath);
			beginTranslateEOE(capDirName);
		}
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		translateEOE.write("    def test");
		translateEOE.write(capFileName);
		translateEOE.write("(self):\n");
		translateEOE.write("        self.compareResourceEOE(\"");
		translateEOE.write(dirName);
		translateEOE.write("/");
		translateEOE.write(fileName);
		translateEOE.write("\")\n");
		translateEOE.write("\n");
	}

	private void beginTranslateEOE(String dirName) throws IOException {
		translateEOE.write("from presto.parser.e.BaseEParserTest import BaseEParserTest\n");
		translateEOE.write("\n");
		translateEOE.write("class Test");
		translateEOE.write(dirName);
		translateEOE.write("(BaseEParserTest):\n");
		translateEOE.write("    \n");
		translateEOE.write("    def setUp(self):\n");
		translateEOE.write("        super(type(self), self).setUp()\n");
		translateEOE.write("    \n");
	}

	private void endTranslateEOE() throws IOException {
		translateEOE.write("\n");
	}
	
	@Override
	protected void addToTranslateEPE(String dirName, String fileName) throws IOException {
		if(translateEPE==null) {
			String capDirName = capitalize(dirName);
			String testFilePath = getRoot() + "presto/translate/epe/Test" + capDirName + ".py";
			translateEPE = mkfile(testFilePath);
			beginTranslateEPE(capDirName);
		}
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		translateEPE.write("    def test");
		translateEPE.write(capFileName);
		translateEPE.write("(self):\n");
		translateEPE.write("        self.compareResourceEPE(\"");
		translateEPE.write(dirName);
		translateEPE.write("/");
		translateEPE.write(fileName);
		translateEPE.write("\")\n");
		translateEPE.write("\n");
	}

	private void beginTranslateEPE(String dirName) throws IOException {
		translateEPE.write("from presto.parser.e.BaseEParserTest import BaseEParserTest\n");
		translateEPE.write("\n");
		translateEPE.write("class Test");
		translateEPE.write(dirName);
		translateEPE.write("(BaseEParserTest):\n");
		translateEPE.write("    \n");
		translateEPE.write("    def setUp(self):\n");
		translateEPE.write("        super(type(self), self).setUp()\n");
		translateEPE.write("    \n");
	}

	private void endTranslateEPE() throws IOException {
		translateEPE.write("\n");
	}

	@Override
	protected void addToTranslateOEO(String dirName, String fileName) throws IOException {
		if(translateOEO==null) {
			String capDirName = capitalize(dirName);
			String testFilePath = getRoot() + "presto/translate/oeo/Test" + capDirName + ".py";
			translateOEO = mkfile(testFilePath);
			beginTranslateOEO(capDirName);
		}
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		translateOEO.write("    def test");
		translateOEO.write(capFileName);
		translateOEO.write("(self):\n");
		translateOEO.write("        self.compareResourceOEO(\"");
		translateOEO.write(dirName);
		translateOEO.write("/");
		translateOEO.write(fileName);
		translateOEO.write("\")\n");
		translateOEO.write("\n");
	}

	private void beginTranslateOEO(String dirName) throws IOException {
		translateOEO.write("from presto.parser.o.BaseOParserTest import BaseOParserTest\n");
		translateOEO.write("\n");
		translateOEO.write("class Test");
		translateOEO.write(dirName);
		translateOEO.write("(BaseOParserTest):\n");
		translateOEO.write("    \n");
		translateOEO.write("    def setUp(self):\n");
		translateOEO.write("        super(type(self), self).setUp()\n");
		translateOEO.write("    \n");
	}

	private void endTranslateOEO() throws IOException {
		translateOEO.write("\n");
	}

	@Override
	protected void addToTranslateOPO(String dirName, String fileName) throws IOException {
		if(translateOPO==null) {
			String capDirName = capitalize(dirName);
			String testFilePath = getRoot() + "presto/translate/opo/Test" + capDirName + ".py";
			translateOPO = mkfile(testFilePath);
			beginTranslateOPO(capDirName);
		}
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		translateOPO.write("    def test");
		translateOPO.write(capFileName);
		translateOPO.write("(self):\n");
		translateOPO.write("        self.compareResourceOPO(\"");
		translateOPO.write(dirName);
		translateOPO.write("/");
		translateOPO.write(fileName);
		translateOPO.write("\")\n");
		translateOPO.write("\n");
	}

	private void beginTranslateOPO(String dirName) throws IOException {
		translateOPO.write("from presto.parser.o.BaseOParserTest import BaseOParserTest\n");
		translateOPO.write("\n");
		translateOPO.write("class Test");
		translateOPO.write(dirName);
		translateOPO.write("(BaseOParserTest):\n");
		translateOPO.write("    \n");
		translateOPO.write("    def setUp(self):\n");
		translateOPO.write("        super(type(self), self).setUp()\n");
		translateOPO.write("    \n");
	}

	private void endTranslateOPO() throws IOException {
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
			String testFilePath = getRoot() + "presto/runtime/e/Test" + capDirName + ".py";
			runtimeE = mkfile(testFilePath);
			beginRuntimeE(capDirName);
		}
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		runtimeE.write("    def test");
		runtimeE.write(capFileName);
		runtimeE.write("(self):\n");
		runtimeE.write("        self.checkOutput(\"");
		runtimeE.write(dirName);
		runtimeE.write("/");
		runtimeE.write(fileName);
		runtimeE.write("\")\n");
		runtimeE.write("\n");
	}

	private void beginRuntimeE(String dirName) throws IOException {
		runtimeE.write("from presto.parser.e.BaseEParserTest import BaseEParserTest\n");
		runtimeE.write("from presto.runtime.utils.Out import Out\n");
		runtimeE.write("\n");
		runtimeE.write("class Test");
		runtimeE.write(dirName);
		runtimeE.write("(BaseEParserTest):\n");
		runtimeE.write("    \n");
		runtimeE.write("    def setUp(self):\n");
		runtimeE.write("        super(type(self), self).setUp()\n");
		runtimeE.write("        Out.init()\n");
		runtimeE.write("    \n");
		runtimeE.write("    def tearDown(self):\n");
		runtimeE.write("        Out.restore()\n");
		runtimeE.write("\n");
	}

	private void endRuntimeE() throws IOException {
		runtimeE.write("\n");
	}
	
	@Override
	protected void addToRuntimeO(String dirName, String fileName) throws IOException {
		if(runtimeO==null) {
			String capDirName = capitalize(dirName);
			String testFilePath = getRoot() + "presto/runtime/o/Test" + capDirName + ".py";
			runtimeO = mkfile(testFilePath);
			beginRuntimeO(capDirName);
		}
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		runtimeO.write("    def test");
		runtimeO.write(capFileName);
		runtimeO.write("(self):\n");
		runtimeO.write("        self.checkOutput(\"");
		runtimeO.write(dirName);
		runtimeO.write("/");
		runtimeO.write(fileName);
		runtimeO.write("\")\n");
		runtimeO.write("\n");
	}

	private void beginRuntimeO(String dirName) throws IOException {
		runtimeO.write("from presto.parser.o.BaseOParserTest import BaseOParserTest\n");
		runtimeO.write("from presto.runtime.utils.Out import Out\n");
		runtimeO.write("\n");
		runtimeO.write("class Test");
		runtimeO.write(dirName);
		runtimeO.write("(BaseOParserTest):\n");
		runtimeO.write("    \n");
		runtimeO.write("    def setUp(self):\n");
		runtimeO.write("        super(type(self), self).setUp()\n");
		runtimeO.write("        Out.init()\n");
		runtimeO.write("    \n");
		runtimeO.write("    def tearDown(self):\n");
		runtimeO.write("        Out.restore()\n");
		runtimeO.write("\n");
	}

	private void endRuntimeO() throws IOException {
		runtimeO.write("\n");
	}


	@Override
	protected void addToRuntimeP(String dirName, String fileName) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}

