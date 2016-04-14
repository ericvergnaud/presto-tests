package prompto.test.gen;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;

public abstract class PythonGenerator extends Generator {
	
	protected abstract String getCoreRoot();
	protected abstract String getLibraryRoot();

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
			String testFilePath = getCoreRoot() + "prompto/translate/eoe/Test" + capDirName + ".py";
			translateEOE = mkfile(testFilePath);
			beginTranslate(translateEOE, "E", capDirName);
		}
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		addToTranslate(translateEOE, "EOE", capFileName, dirName, fileName);
	}

	@Override
	protected void addToTranslateESE(String dirName, String fileName) throws IOException {
		if(translateESE==null) {
			String capDirName = capitalize(dirName);
			String testFilePath = getCoreRoot() + "prompto/translate/ese/Test" + capDirName + ".py";
			translateESE = mkfile(testFilePath);
			beginTranslate(translateESE, "E", capDirName);
		}
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		addToTranslate(translateESE, "ESE", capFileName, dirName, fileName);
	}
	
	@Override
	protected void addToTranslateOEO(String dirName, String fileName) throws IOException {
		if(translateOEO==null) {
			String capDirName = capitalize(dirName);
			String testFilePath = getCoreRoot() + "prompto/translate/oeo/Test" + capDirName + ".py";
			translateOEO = mkfile(testFilePath);
			beginTranslate(translateOEO, "O", capDirName);
		}
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		addToTranslate(translateOEO, "OEO", capFileName, dirName, fileName);
	}
	
	@Override
	protected void addToTranslateOSO(String dirName, String fileName) throws IOException {
		if(translateOSO==null) {
			String capDirName = capitalize(dirName);
			String testFilePath = getCoreRoot() + "prompto/translate/oso/Test" + capDirName + ".py";
			translateOSO = mkfile(testFilePath);
			beginTranslate(translateOSO, "O", capDirName);
		}
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		addToTranslate(translateOSO, "OSO", capFileName, dirName, fileName);
	}
	
	@Override
	protected void addToTranslateSES(String dirName, String fileName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void addToTranslateSOS(String dirName, String fileName) {
		// TODO Auto-generated method stub
		
	}

	private void addToTranslate(OutputStreamWriter writer, String suffix, String capFileName, String dirName, String fileName) throws IOException {
		writer.write("    def test");
		writer.write(capFileName);
		writer.write("(self):\n");
		writer.write("        self.compareResource");
		writer.write(suffix);
		writer.write("(\"");
		writer.write(dirName);
		writer.write("/");
		writer.write(fileName);
		writer.write("\")\n");
		writer.write("\n");
	}

	private void beginTranslate(OutputStreamWriter writer, String dialect, String dirName) throws IOException {
		writer.write("from prompto.parser.");
		writer.write(dialect.toLowerCase());
		writer.write(".Base");
		writer.write(dialect.toUpperCase());
		writer.write("ParserTest import Base");
		writer.write(dialect.toUpperCase());
		writer.write("ParserTest\n");
		writer.write("\n");
		writer.write("class Test");
		writer.write(dirName);
		writer.write("(Base");
		writer.write(dialect.toUpperCase());
		writer.write("ParserTest):\n");
		writer.write("    \n");
		writer.write("    def setUp(self):\n");
		writer.write("        super(type(self), self).setUp()\n");
		writer.write("    \n");
	}

	private void endTranslate(OutputStreamWriter writer) throws IOException {
		writer.write("\n");
	}
	

	@Override
	protected void addToRuntimeE(String dirName, String fileName, Options options) throws Exception {
		if(runtimeE==null) {
			String capDirName = capitalize(dirName);
			String testFilePath = getCoreRoot() + "prompto/runtime/e/Test" + capDirName + ".py";
			runtimeE = mkfile(testFilePath);
			beginRuntime(runtimeE, "E", capDirName);
		}
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		addToRuntime(runtimeE, capFileName, dirName, fileName);
	}

	@Override
	protected void addToRuntimeO(String dirName, String fileName, Options options) throws IOException {
		if(runtimeO==null) {
			String capDirName = capitalize(dirName);
			String testFilePath = getCoreRoot() + "prompto/runtime/o/Test" + capDirName + ".py";
			runtimeO = mkfile(testFilePath);
			beginRuntime(runtimeO, "O", capDirName);
		}
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		addToRuntime(runtimeO, capFileName, dirName, fileName);
	}


	@Override
	protected void addToRuntimeS(String dirName, String fileName, Options options) throws IOException {
		if(runtimeS==null) {
			String capDirName = capitalize(dirName);
			String testFilePath = getCoreRoot() + "prompto/runtime/s/Test" + capDirName + ".py";
			runtimeS = mkfile(testFilePath);
			beginRuntime(runtimeS, "S", capDirName);
		}
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		addToRuntime(runtimeS, capFileName, dirName, fileName);
	}
	
	
	private void addToRuntime(OutputStreamWriter writer, String capFileName, String dirName, String fileName) throws IOException {
		writer.write("    def test");
		writer.write(capFileName);
		writer.write("(self):\n");
		writer.write("        self.checkOutput(\"");
		writer.write(dirName);
		writer.write("/");
		writer.write(fileName);
		writer.write("\")\n");
		writer.write("\n");
	}

	private void beginRuntime(OutputStreamWriter writer, String dialect, String dirName) throws IOException {
		writer.write("from prompto.parser.");
		writer.write(dialect.toLowerCase());
		writer.write(".Base");
		writer.write(dialect.toUpperCase());
		writer.write("ParserTest import Base");
		writer.write(dialect.toUpperCase());
		writer.write("ParserTest\n");
		writer.write("from prompto.runtime.utils.Out import Out\n");
		writer.write("\n");
		writer.write("class Test");
		writer.write(dirName);
		writer.write("(Base");
		writer.write(dialect.toUpperCase());
		writer.write("ParserTest):\n");
		writer.write("    \n");
		writer.write("    def setUp(self):\n");
		writer.write("        super(type(self), self).setUp()\n");
		writer.write("        Out.init()\n");
		writer.write("    \n");
		writer.write("    def tearDown(self):\n");
		writer.write("        Out.restore()\n");
		writer.write("\n");
	}

	private void endRuntime(OutputStreamWriter writer) throws IOException {
		writer.write("\n");
	}
	

	@Override
	protected void addToLibraryE(String dirName, String fileName) throws Exception {
		if(libraryE==null) {
			String capDirName = capitalize(dirName);
			String testFilePath = getLibraryRoot() + "e/Test" + capDirName + ".py";
			libraryE = mkfile(testFilePath);
			beginLibrary(libraryE, "E", capDirName);
		}
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		addToLibrary(libraryE, capFileName, dirName, fileName);
	}

	@Override
	protected void addToLibraryO(String dirName, String fileName) throws IOException {
		if(libraryO==null) {
			String capDirName = capitalize(dirName);
			String testFilePath = getLibraryRoot() + "o/Test" + capDirName + ".py";
			libraryO = mkfile(testFilePath);
			beginLibrary(libraryO, "O", capDirName);
		}
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		addToLibrary(libraryO, capFileName, dirName, fileName);
	}


	@Override
	protected void addToLibraryS(String dirName, String fileName) throws IOException {
		if(libraryS==null) {
			String capDirName = capitalize(dirName);
			String testFilePath = getLibraryRoot() + "s/Test" + capDirName + ".py";
			libraryS = mkfile(testFilePath);
			beginLibrary(libraryS, "S", capDirName);
		}
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		addToLibrary(libraryS, capFileName, dirName, fileName);
	}
	
	
	private void beginLibrary(OutputStreamWriter writer, String dialect, String dirName) throws IOException {
		writer.write("from prompto.parser.");
		writer.write(dialect.toLowerCase());
		writer.write(".Base");
		writer.write(dialect.toUpperCase());
		writer.write("ParserTest import Base");
		writer.write(dialect.toUpperCase());
		writer.write("ParserTest\n");
		writer.write("from prompto.runtime.utils.Out import Out\n");
		writer.write("\n");
		writer.write("class Test");
		writer.write(dirName);
		writer.write("(Base");
		writer.write(dialect.toUpperCase());
		writer.write("ParserTest):\n");
		writer.write("    \n");
		writer.write("    def setUp(self):\n");
		writer.write("        super(type(self), self).setUp()\n");
		writer.write("        Out.init()\n");
		if(dependencies!=null) {
			writer.write("        self.coreContext = None\n");
			for(String s : dependencies) {
				writer.write("        self.loadDependency(\"");
				writer.write(s);
				writer.write("\")\n");
			}
		}
		writer.write("    \n");
		writer.write("    def tearDown(self):\n");
		writer.write("        Out.restore()\n");
		writer.write("\n");
	}
	
	private void addToLibrary(OutputStreamWriter writer, String capFileName, String dirName, String fileName) throws IOException {
		writer.write("    def test");
		writer.write(capFileName);
		writer.write("(self):\n");
		writer.write("        self.runTests(\"");
		writer.write(dirName);
		writer.write("/");
		writer.write(fileName);
		writer.write("\")\n");
		writer.write("\n");
	}


	private void endLibrary(OutputStreamWriter writer) throws IOException {
		writer.write("\n");
	}
	

	
}

