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
	protected void addToTranslateESE(String dirName, String fileName) throws IOException {
		if(translateESE==null) {
			String capDirName = capitalize(dirName);
			String testFilePath = getRoot() + "presto/translate/ese/Test" + capDirName + ".py";
			translateESE = mkfile(testFilePath);
			beginTranslateESE(capDirName);
		}
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		translateESE.write("    def test");
		translateESE.write(capFileName);
		translateESE.write("(self):\n");
		translateESE.write("        self.compareResourceESE(\"");
		translateESE.write(dirName);
		translateESE.write("/");
		translateESE.write(fileName);
		translateESE.write("\")\n");
		translateESE.write("\n");
	}

	private void beginTranslateESE(String dirName) throws IOException {
		translateESE.write("from presto.parser.e.BaseEParserTest import BaseEParserTest\n");
		translateESE.write("\n");
		translateESE.write("class Test");
		translateESE.write(dirName);
		translateESE.write("(BaseEParserTest):\n");
		translateESE.write("    \n");
		translateESE.write("    def setUp(self):\n");
		translateESE.write("        super(type(self), self).setUp()\n");
		translateESE.write("    \n");
	}

	private void endTranslateESE() throws IOException {
		translateESE.write("\n");
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
	protected void addToTranslateOSO(String dirName, String fileName) throws IOException {
		if(translateOSO==null) {
			String capDirName = capitalize(dirName);
			String testFilePath = getRoot() + "presto/translate/oso/Test" + capDirName + ".py";
			translateOSO = mkfile(testFilePath);
			beginTranslateOSO(capDirName);
		}
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		translateOSO.write("    def test");
		translateOSO.write(capFileName);
		translateOSO.write("(self):\n");
		translateOSO.write("        self.compareResourceOSO(\"");
		translateOSO.write(dirName);
		translateOSO.write("/");
		translateOSO.write(fileName);
		translateOSO.write("\")\n");
		translateOSO.write("\n");
	}

	private void beginTranslateOSO(String dirName) throws IOException {
		translateOSO.write("from presto.parser.o.BaseOParserTest import BaseOParserTest\n");
		translateOSO.write("\n");
		translateOSO.write("class Test");
		translateOSO.write(dirName);
		translateOSO.write("(BaseOParserTest):\n");
		translateOSO.write("    \n");
		translateOSO.write("    def setUp(self):\n");
		translateOSO.write("        super(type(self), self).setUp()\n");
		translateOSO.write("    \n");
	}

	private void endTranslateOSO() throws IOException {
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
	protected void addToRuntimeS(String dirName, String fileName) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}

