package prompto.test.gen;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;


public class JavaScriptGenerator extends Generator {
	
	static final String CORE_ROOT = "prompto-javascript/JavaScript-Core/src/test/";
	static final String LIB_ROOT = "prompto-javascript/JavaScript-Runtime/src/test/";

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
			String testFilePath = CORE_ROOT + "prompto/translate/eoe/Test" + capDirName + ".js";
			translateEOE = mkfile(testFilePath);
			beginTranslate(translateEOE, "EOE");
		}
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		addToTranslate(translateEOE, "EOE", dirName, capFileName, fileName);
	}
	
	@Override
	protected void addToTranslateESE(String dirName, String fileName) throws IOException {
		if(translateESE==null) {
			String capDirName = capitalize(dirName);
			String testFilePath = CORE_ROOT + "prompto/translate/ese/Test" + capDirName + ".js";
			translateESE = mkfile(testFilePath);
			beginTranslate(translateESE, "ESE");
		}
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		addToTranslate(translateESE, "ESE", dirName, capFileName, fileName);
	}
	
	@Override
	protected void addToTranslateOEO(String dirName, String fileName) throws IOException {
		if(translateOEO==null) {
			String capDirName = capitalize(dirName);
			String testFilePath = CORE_ROOT + "prompto/translate/oeo/Test" + capDirName + ".js";
			translateOEO = mkfile(testFilePath);
			beginTranslate(translateOEO, "OEO");
		}
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		addToTranslate(translateOEO, "OEO", dirName, capFileName, fileName);
	}
	
	@Override
	protected void addToTranslateOSO(String dirName, String fileName) throws IOException {
		if(translateOSO==null) {
			String capDirName = capitalize(dirName);
			String testFilePath = CORE_ROOT + "prompto/translate/oso/Test" + capDirName + ".js";
			translateOSO = mkfile(testFilePath);
			beginTranslate(translateOSO, "OSO");
		}
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		addToTranslate(translateOSO, "OSO", dirName, capFileName, fileName);
	}

	@Override
	protected void addToTranslateSES(String dirName, String fileName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void addToTranslateSOS(String dirName, String fileName) {
		// TODO Auto-generated method stub
		
	}


	private void beginTranslate(OutputStreamWriter writer, String suffix) throws IOException {
		writer.write("require(\"../../../../exploded\");\n");
		writer.write("\n");
		writer.write("var compareResource");
		writer.write(suffix);
		writer.write(" = require(\"../../parser/BaseParserTest\").compareResource");
		writer.write(suffix);
		writer.write(";\n");
		writer.write("\n");
	}


	protected void addToTranslate(OutputStreamWriter writer, String suffix, String dirName, String capFileName, String fileName) throws IOException {
		writer.write("exports.test");
		writer.write(capFileName);
		writer.write(" = function(test) {\n");
		writer.write("\tcompareResource");
		writer.write(suffix);
		writer.write("(test, \"");
		writer.write(dirName);
		writer.write("/");
		writer.write(fileName);
		writer.write("\");\n");
		writer.write("};\n");
		writer.write("\n");
	}

	protected void endTranslate(OutputStreamWriter writer) {
		
	}

	@Override
	protected void addToRuntimeE(String dirName, String fileName, Options options) throws Exception {
		if(runtimeE==null) {
			String capDirName = capitalize(dirName);
			String testFilePath = CORE_ROOT + "prompto/runtime/e/Test" + capDirName + ".js";
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
			String testFilePath = CORE_ROOT + "prompto/runtime/o/Test" + capDirName + ".js";
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
			String testFilePath = CORE_ROOT + "prompto/runtime/s/Test" + capDirName + ".js";
			runtimeS = mkfile(testFilePath);
			beginRuntime(runtimeS, "S", capDirName);
		}
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		addToRuntime(runtimeS, capFileName, dirName, fileName);
	}
	
	private void addToRuntime(OutputStreamWriter writer, String capFileName, String dirName, String fileName) throws IOException {
		writer.write("exports.test");
		writer.write(capFileName);
		writer.write(" = function(test) {\n");
		writer.write("\tcheckOutput(test, \"");
		writer.write(dirName);
		writer.write("/");
		writer.write(fileName);
		writer.write("\");\n");
		writer.write("};\n");
		writer.write("\n");
	}

	private void beginRuntime(OutputStreamWriter writer, String dialect, String dirName) throws IOException {
		writer.write("require(\"../../../../exploded\");\n");
		writer.write("\n");
		writer.write("var Out = require(\"../utils/Out\").Out;\n");
		writer.write("var checkOutput = require(\"../../parser/Base");
		writer.write(dialect.toUpperCase());
		writer.write("ParserTest\").checkOutput;\n");
		writer.write("\n");
		writer.write("exports.setUp = function(done) {\n");
		writer.write("\tOut.init();\n");
		writer.write("\tdone();\n");
		writer.write("};\n");
		writer.write("\n");
		writer.write("exports.tearDown = function(done) {\n");
		writer.write("\tOut.restore();\n");
		writer.write("\tdone();\n");
		writer.write("};\n");
		writer.write("\n");
	}

	private void endRuntime(OutputStreamWriter writer) {
		
	}
	
	@Override
	protected void addToLibraryE(String dirName, String fileName) throws Exception {
		if(libraryE==null) {
			String capDirName = capitalize(dirName);
			String testFilePath = LIB_ROOT + "library/e/Test" + capDirName + ".js";
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
			String testFilePath = LIB_ROOT + "library/o/Test" + capDirName + ".js";
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
			String testFilePath = LIB_ROOT + "library/s/Test" + capDirName + ".js";
			libraryS = mkfile(testFilePath);
			beginLibrary(libraryS, "S", capDirName);
		}
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		addToLibrary(libraryS, capFileName, dirName, fileName);
	}
	
	private void beginLibrary(OutputStreamWriter writer, String dialect, String dirName) throws IOException {
		writer.write("require(\"../../../../../JavaScript-Core/src/exploded.js\");\n");
		writer.write("var prompto = require(\"../../../../../JavaScript-Core/src/main/prompto/index.js\");\n");
		writer.write("var Out = require(\"../../../../../JavaScript-Core/src/test/prompto/runtime/utils/Out\").Out;\n");
		writer.write("var BaseParserTest = require(\"../../../../../JavaScript-Core/src/test/prompto/parser/BaseParserTest\");\n");
		writer.write("var loadDependency = require(\"../../../../../JavaScript-Core/src/test/prompto/parser/BaseEParserTest\").loadDependency;\n");
		writer.write("var runTests = require(\"../../../../../JavaScript-Core/src/test/prompto/parser/Base");
		writer.write(dialect.toUpperCase());
		writer.write("ParserTest\").runTests;\n");
		writer.write("\n");
		writer.write("exports.setUp = function(done) {\n");
		writer.write("\tOut.init();\n");
		if(dependencies!=null) {
			writer.write("\tBaseParserTest.coreContext = null;\n");
			for(String s : dependencies) {
				writer.write("\tloadDependency(\"");
				writer.write(s);
				writer.write("\");\n");
			}
		}
		writer.write("\tdone();\n");
		writer.write("};\n");
		writer.write("\n");
		writer.write("exports.tearDown = function(done) {\n");
		writer.write("\tOut.restore();\n");
		writer.write("\tdone();\n");
		writer.write("};\n");
		writer.write("\n");
	}
	
	private void addToLibrary(OutputStreamWriter writer, String capFileName, String dirName, String fileName) throws IOException {
		writer.write("exports.test");
		writer.write(capFileName);
		writer.write(" = function(test) {\n");
		writer.write("\trunTests(test, \"");
		writer.write(dirName);
		writer.write("/");
		writer.write(fileName);
		writer.write("\");\n");
		writer.write("};\n");
		writer.write("\n");
	}

	private void endLibrary(OutputStreamWriter writer) {
		
	}
	

	
}
