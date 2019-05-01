package prompto.test.gen;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;


public class JavaScriptGenerator extends Generator {
	
	static final String CORE_ROOT = "prompto-javascript/JavaScript-Core/src/test/";
	static final String LIB_ROOT = "prompto-javascript/JavaScript-Runtime/src/test/";

	@Override
	protected String getTarget() {
		return "JavaScript";
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
			String testFilePath = CORE_ROOT + "prompto/translate/eoe/Test" + capDirName + ".test.js";
			translateEOE = mkfile(testFilePath);
			beginTranslate(translateEOE, "EOE");
		}
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		addToTranslate(translateEOE, "EOE", dirName, capFileName, fileName);
	}
	
	@Override
	protected void addToTranslateEME(String dirName, String fileName) throws IOException {
		if(translateEME==null) {
			String capDirName = capitalize(dirName);
			String testFilePath = CORE_ROOT + "prompto/translate/eme/Test" + capDirName + ".test.js";
			translateEME = mkfile(testFilePath);
			beginTranslate(translateEME, "EME");
		}
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		addToTranslate(translateEME, "EME", dirName, capFileName, fileName);
	}
	
	@Override
	protected void addToTranslateOEO(String dirName, String fileName) throws IOException {
		if(translateOEO==null) {
			String capDirName = capitalize(dirName);
			String testFilePath = CORE_ROOT + "prompto/translate/oeo/Test" + capDirName + ".test.js";
			translateOEO = mkfile(testFilePath);
			beginTranslate(translateOEO, "OEO");
		}
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		addToTranslate(translateOEO, "OEO", dirName, capFileName, fileName);
	}
	
	@Override
	protected void addToTranslateOMO(String dirName, String fileName) throws IOException {
		if(translateOMO==null) {
			String capDirName = capitalize(dirName);
			String testFilePath = CORE_ROOT + "prompto/translate/omo/Test" + capDirName + ".test.js";
			translateOMO = mkfile(testFilePath);
			beginTranslate(translateOMO, "OMO");
		}
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		addToTranslate(translateOMO, "OMO", dirName, capFileName, fileName);
	}

	@Override
	protected void addToTranslateMEM(String dirName, String fileName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void addToTranslateMOM(String dirName, String fileName) {
		// TODO Auto-generated method stub
		
	}


	private void beginTranslate(OutputStreamWriter writer, String suffix) throws IOException {
		writer.write("var compareResource");
		writer.write(suffix);
		writer.write(" = require(\"../../parser/BaseParserTest\").compareResource");
		writer.write(suffix);
		writer.write(";\n");
		writer.write("\n");
	}


	protected void addToTranslate(OutputStreamWriter writer, String suffix, String dirName, String capFileName, String fileName) throws IOException {
		writer.write("test('");
		writer.write(capFileName);
		writer.write("', () => {\n");
		writer.write("\tcompareResource");
		writer.write(suffix);
		writer.write("('");
		writer.write(dirName);
		writer.write("/");
		writer.write(fileName);
		writer.write("');\n");
		writer.write("});\n");
		writer.write("\n");
	}

	protected void endTranslate(OutputStreamWriter writer) {
		
	}

	@Override
	protected void addToRuntimeE(String dirName, String fileName, Options options) throws IOException {
		if(runtimeE==null)
			runtimeE = beginRuntime(dirName, 'E');
		addToRuntime(dirName, fileName, options, runtimeE);
	}

	@Override
	protected void addToRuntimeO(String dirName, String fileName, Options options) throws IOException {
		if(runtimeO==null)
			runtimeO = beginRuntime(dirName, 'O');
		addToRuntime(dirName, fileName, options, runtimeO);
	}

	@Override
	protected void addToRuntimeM(String dirName, String fileName, Options options) throws IOException {
		if(runtimeM==null)
			runtimeM = beginRuntime(dirName, 'M');
		addToRuntime(dirName, fileName, options, runtimeM);
	}

	private OutputStreamWriter beginRuntime(String dirName, Character dialect) throws IOException {
		String capDirName = capitalize(dirName);
		String testFilePath = CORE_ROOT + "prompto/runtime/" + dialect.toString().toLowerCase() + "/Test" + capDirName + ".test.js";
		OutputStreamWriter runtime = mkfile(testFilePath);
		beginRuntime(runtime, dialect.toString(), capDirName);
		return runtime;
	}
	
	protected void addToRuntime(String dirName, String fileName, Options options, OutputStreamWriter runtime) throws IOException {
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		if(options.interpreted)
			addToRuntime(runtime, capFileName, dirName, fileName, Type.INTERPRETED);
		if(options.transpiled)
			addToRuntime(runtime, capFileName, dirName, fileName, Type.TRANSPILED);
	}


	
	private void addToRuntime(OutputStreamWriter writer, String capFileName, String dirName, String fileName, Type type) throws IOException {
		writer.write("test('");
		writer.write(type.toString()); 
		writer.write(" ");
		writer.write(capFileName);
		writer.write("', () => {\n");
		writer.write("\tcheck");
		writer.write(type.toString()); 
		writer.write("Output('");
		writer.write(dirName);
		writer.write("/");
		writer.write(fileName);
		writer.write("');\n");
		writer.write("});\n");
		writer.write("\n");
	}

	private void beginRuntime(OutputStreamWriter writer, String dialect, String dirName) throws IOException {
		writer.write("var Out = require(\"../utils/Out\").Out;\n");
		writer.write("var checkInterpretedOutput = require(\"../../parser/Base");
		writer.write(dialect.toUpperCase());
		writer.write("ParserTest\").checkInterpretedOutput;\n");
		writer.write("var checkTranspiledOutput = require(\"../../parser/Base");
		writer.write(dialect.toUpperCase());
		writer.write("ParserTest\").checkTranspiledOutput;\n");
		writer.write("\n");
		writer.write("beforeEach( () => {\n");
		writer.write("\tOut.init();\n");
		writer.write("});\n");
		writer.write("\n");
		writer.write("afterEach( () => {\n");
		writer.write("\tOut.restore();\n");
		writer.write("});\n");
		writer.write("\n");
	}

	private void endRuntime(OutputStreamWriter writer) {
		
	}
	
	@Override
	protected void addToLibraryE(String dirName, String fileName, Options options) throws IOException {
		if(libraryE==null)
			libraryE = beginLibrary(dirName, 'E');
		addToLibrary(dirName, fileName, options, libraryE);
	}
	
	void addToLibrary(String dirName, String fileName, Options options, OutputStreamWriter writer) throws IOException {
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		if(options.interpreted)
			addToLibrary(writer, capFileName, dirName, fileName, Type.INTERPRETED);
		if(options.transpiled)
			addToLibrary(writer, capFileName, dirName, fileName, Type.TRANSPILED);
	}

	private OutputStreamWriter beginLibrary(String dirName, Character dialect) throws IOException {
		String capDirName = capitalize(dirName);
		String testFilePath = LIB_ROOT + "library/" + dialect.toString().toLowerCase() + "/Test" + capDirName + ".test.js";
		OutputStreamWriter library =  mkfile(testFilePath);
		beginLibrary(library, dialect.toString(), capDirName);
		return library;	
	}

	@Override
	protected void addToLibraryO(String dirName, String fileName, Options options) throws IOException {
		if(libraryO==null)
			libraryO = beginLibrary(dirName, 'O');
		addToLibrary(dirName, fileName, options, libraryO);
	}

	@Override
	protected void addToLibraryM(String dirName, String fileName, Options options) throws IOException {
		if(libraryM==null)
			libraryM = beginLibrary(dirName, 'M');
		addToLibrary(dirName, fileName, options, libraryM);
	}
	
	private void beginLibrary(OutputStreamWriter writer, String dialect, String dirName) throws IOException {
		writer.write("var prompto = require(\"../../../../../JavaScript-Core/src/main/prompto/index.js\");\n");
		writer.write("var Out = require(\"../../../../../JavaScript-Core/src/test/prompto/runtime/utils/Out\").Out;\n");
		writer.write("var BaseParserTest = require(\"../../../../../JavaScript-Core/src/test/prompto/parser/BaseParserTest\");\n");
		writer.write("var loadDependency = require(\"../../../../../JavaScript-Core/src/test/prompto/parser/BaseEParserTest\").loadDependency;\n");
		writer.write("var runInterpretedTests = require(\"../../../../../JavaScript-Core/src/test/prompto/parser/Base");
		writer.write(dialect.toUpperCase());
		writer.write("ParserTest\").runInterpretedTests;\n");
		writer.write("var runTranspiledTests = require(\"../../../../../JavaScript-Core/src/test/prompto/parser/Base");
		writer.write(dialect.toUpperCase());
		writer.write("ParserTest\").runTranspiledTests;\n");
		writer.write("\n");
		writer.write("beforeEach( () => {\n");
		writer.write("\tOut.init();\n");
		if(dependencies!=null) {
			writer.write("\tBaseParserTest.coreContext = null;\n");
			for(String s : dependencies) {
				writer.write("\tloadDependency(\"");
				writer.write(s);
				writer.write("\");\n");
			}
		}
		writer.write("});\n");
		writer.write("\n");
		writer.write("afterEach( () => {\n");
		writer.write("\tOut.restore();\n");
		writer.write("});\n");
		writer.write("\n");
	}
	
	private void addToLibrary(OutputStreamWriter writer, String capFileName, String dirName, String fileName, Type type) throws IOException {
		writer.write("test('");
		writer.write(type.toString()); 
		writer.write(" ");
		writer.write(capFileName);
		writer.write("', () => {\n");
		writer.write("\trun");
		writer.write(type.toString()); 
		writer.write("Tests('");
		writer.write(dirName);
		writer.write("/");
		writer.write(fileName);
		writer.write("');\n");
		writer.write("});\n");
		writer.write("\n");
	}

	private void endLibrary(OutputStreamWriter writer) {
		
	}
	

	
}
