package prompto.test.gen;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;


public class JavaScriptGenerator extends Generator {
	
	static final String CORE_ROOT = "prompto-javascript/JavaScript-Core/src/test/";
	static final String LIB_ROOT = "prompto-javascript/JavaScript-Runtime/src/test/";

	@Override
	protected Target getTarget() {
		return Target.JAVASCRIPT;
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
		if(problemsE!=null)
			endProblems(problemsE);
		if(problemsO!=null)
			endProblems(problemsO);
		if(problemsM!=null)
			endProblems(problemsM);
		if(suggestionsE!=null)
			endSuggestions(suggestionsE);
		if(suggestionsO!=null)
			endSuggestions(suggestionsO);
		if(suggestionsM!=null)
			endSuggestions(suggestionsM);
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
	protected void addToTranslateEOE(File subDir, String fileName) throws IOException {
		if(translateEOE==null) {
			String capDirName = capitalize(subDir.getName());
			String testFilePath = CORE_ROOT + "prompto/translate/eoe/Test" + capDirName + ".test.js";
			translateEOE = mkfile(testFilePath);
			beginTranslate(translateEOE, "EOE");
		}
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		addToTranslate(translateEOE, "EOE", subDir.getName(), capFileName, fileName);
	}
	
	@Override
	protected void addToTranslateEME(File subDir, String fileName) throws IOException {
		if(translateEME==null) {
			String capDirName = capitalize(subDir.getName());
			String testFilePath = CORE_ROOT + "prompto/translate/eme/Test" + capDirName + ".test.js";
			translateEME = mkfile(testFilePath);
			beginTranslate(translateEME, "EME");
		}
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		addToTranslate(translateEME, "EME", subDir.getName(), capFileName, fileName);
	}
	
	@Override
	protected void addToTranslateOEO(File subDir, String fileName) throws IOException {
		if(translateOEO==null) {
			String capDirName = capitalize(subDir.getName());
			String testFilePath = CORE_ROOT + "prompto/translate/oeo/Test" + capDirName + ".test.js";
			translateOEO = mkfile(testFilePath);
			beginTranslate(translateOEO, "OEO");
		}
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		addToTranslate(translateOEO, "OEO", subDir.getName(), capFileName, fileName);
	}
	
	@Override
	protected void addToTranslateOMO(File subDir, String fileName) throws IOException {
		if(translateOMO==null) {
			String capDirName = capitalize(subDir.getName());
			String testFilePath = CORE_ROOT + "prompto/translate/omo/Test" + capDirName + ".test.js";
			translateOMO = mkfile(testFilePath);
			beginTranslate(translateOMO, "OMO");
		}
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		addToTranslate(translateOMO, "OMO", subDir.getName(), capFileName, fileName);
	}

	@Override
	protected void addToTranslateMEM(File subDir, String fileName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void addToTranslateMOM(File subDir, String fileName) {
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
	protected void addToRuntimeE(File subDir, String fileName, Options options) throws IOException {
		if(runtimeE==null)
			runtimeE = beginRuntime(subDir.getName(), 'E');
		addToRuntime(subDir.getName(), fileName, options, runtimeE);
	}

	@Override
	protected void addToRuntimeO(File subDir, String fileName, Options options) throws IOException {
		if(runtimeO==null)
			runtimeO = beginRuntime(subDir.getName(), 'O');
		addToRuntime(subDir.getName(), fileName, options, runtimeO);
	}

	@Override
	protected void addToRuntimeM(File subDir, String fileName, Options options) throws IOException {
		if(runtimeM==null)
			runtimeM = beginRuntime(subDir.getName(), 'M');
		addToRuntime(subDir.getName(), fileName, options, runtimeM);
	}

	private OutputStreamWriter beginRuntime(String dirName, Character dialect) throws IOException {
		String capDirName = capitalize(dirName);
		String testFilePath = CORE_ROOT + "prompto/runtime/" + dialect.toString().toLowerCase() + "/Test" + capDirName + ".test.js";
		OutputStreamWriter writer = mkfile(testFilePath);
		beginRuntime(writer, dialect.toString(), capDirName);
		return writer;
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

	protected void addToRuntime(String dirName, String fileName, Options options, OutputStreamWriter writer) throws IOException {
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		if(options.interpreted)
			addToRuntime(writer, capFileName, dirName, fileName, TestType.INTERPRETED);
		if(options.transpiled)
			addToRuntime(writer, capFileName, dirName, fileName, TestType.TRANSPILED);
	}


	
	private void addToRuntime(OutputStreamWriter writer, String capFileName, String dirName, String fileName, TestType type) throws IOException {
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

	private void endRuntime(OutputStreamWriter writer) {
		
	}
	
	@Override
	protected void addToProblemsE(File subDir, String fileName, Options options) throws IOException {
		if(problemsE==null)
			runtimeE = beginProblems(subDir.getName(), 'E');
		addToProblems(subDir.getName(), fileName, options, problemsE);
	}

	@Override
	protected void addToProblemsO(File subDir, String fileName, Options options) throws IOException {
		if(problemsO==null)
			problemsO = beginProblems(subDir.getName(), 'O');
		addToProblems(subDir.getName(), fileName, options, problemsO);
	}

	@Override
	protected void addToProblemsM(File subDir, String fileName, Options options) throws IOException {
		if(problemsM==null)
			problemsM = beginProblems(subDir.getName(), 'M');
		addToProblems(subDir.getName(), fileName, options, problemsM);
	}

	private OutputStreamWriter beginProblems(String dirName, Character dialect) throws IOException {
		String capDirName = capitalize(dirName);
		String testFilePath = CORE_ROOT + "prompto/problems/" + dialect.toString().toLowerCase() + "/Test" + capDirName + ".test.js";
		OutputStreamWriter writer = mkfile(testFilePath);
		beginProblems(writer, dialect.toString(), capDirName);
		return writer;
	}
	
	private void beginProblems(OutputStreamWriter writer, String dialect, String dirName) throws IOException {
		writer.write("var checkProblems = require(\"../../parser/Base");
		writer.write(dialect.toUpperCase());
		writer.write("ParserTest\").checkProblems;\n");
		writer.write("\n");
	}

	protected void addToProblems(String dirName, String fileName, Options options, OutputStreamWriter writer) throws IOException {
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		if(options.interpreted)
			addToProblems(writer, capFileName, dirName, fileName);
	}


	private void addToProblems(OutputStreamWriter writer, String capFileName, String dirName, String fileName) throws IOException {
		writer.write("test('");
		writer.write(capFileName);
		writer.write(" problems', () => {\n");
		writer.write("\tcheckProblems('");
		writer.write(dirName);
		writer.write("/");
		writer.write(fileName);
		writer.write("');\n");
		writer.write("});\n");
		writer.write("\n");
	}

	private void endProblems(OutputStreamWriter writer) {
		
	}
	

	@Override
	protected void addToSuggestionsO(File subDir, String fileName, Options options) throws IOException {
		if(suggestionsO==null)
			suggestionsO = beginSuggestions(subDir.getName(), 'O');
		addToSuggestions(subDir.getName(), fileName, options, suggestionsO);
		
	}
	
	@Override
	protected void addToSuggestionsM(File subDir, String fileName, Options options) throws IOException {
		if(suggestionsM==null)
			suggestionsM = beginSuggestions(subDir.getName(), 'M');
		addToSuggestions(subDir.getName(), fileName, options, suggestionsM);
		
	}
	
	@Override
	protected void addToSuggestionsE(File subDir, String fileName, Options options) throws IOException {
		if(suggestionsE==null)
			suggestionsE = beginSuggestions(subDir.getName(), 'E');
		addToSuggestions(subDir.getName(), fileName, options, suggestionsE);
		
	}

	private OutputStreamWriter beginSuggestions(String dirName, Character dialect) throws IOException {
		String capDirName = capitalize(dirName);
		String testFilePath = CORE_ROOT + "prompto/suggestions/" + dialect.toString().toLowerCase() + "/Test" + capDirName + ".test.js";
		OutputStreamWriter writer = mkfile(testFilePath);
		beginSuggestions(writer, dialect.toString(), capDirName);
		return writer;
	}
	
	private void beginSuggestions(OutputStreamWriter writer, String dialect, String dirName) throws IOException {
		writer.write("var checkSuggestions = require(\"../../parser/Base");
		writer.write(dialect.toUpperCase());
		writer.write("ParserTest\").checkSuggestions;\n");
		writer.write("\n");
	}

	protected void addToSuggestions(String dirName, String fileName, Options options, OutputStreamWriter writer) throws IOException {
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		if(options.interpreted)
			addToSuggestions(writer, capFileName, dirName, fileName);
	}

	private void addToSuggestions(OutputStreamWriter writer, String capFileName, String dirName, String fileName) throws IOException {
		writer.write("test('");
		writer.write(capFileName);
		writer.write(" suggestions', () => {\n");
		writer.write("\tcheckSuggestions('");
		writer.write(dirName);
		writer.write("/");
		writer.write(fileName);
		writer.write("');\n");
		writer.write("});\n");
		writer.write("\n");
	}

	private void endSuggestions(OutputStreamWriter writer) {
		
	}
	
	@Override
	protected void addToLibraryE(File subDir, String fileName, Options options) throws IOException {
		if(libraryE==null)
			libraryE = beginLibrary(subDir.getName(), 'E');
		addToLibrary(subDir, fileName, options, libraryE);
	}
	
	@Override
	protected void addToLibraryO(File subDir, String fileName, Options options) throws IOException {
		if(libraryO==null)
			libraryO = beginLibrary(subDir.getName(), 'O');
		addToLibrary(subDir, fileName, options, libraryO);
	}

	@Override
	protected void addToLibraryM(File subDir, String fileName, Options options) throws IOException {
		if(libraryM==null)
			libraryM = beginLibrary(subDir.getName(), 'M');
		addToLibrary(subDir, fileName, options, libraryM);
	}
	
	void addToLibrary(File subDir, String fileName, Options options, OutputStreamWriter writer) throws IOException {
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		if(options.interpreted && !options.exclusions.isExcludedTest(subDir, fileName, Target.JAVASCRIPT, TestType.INTERPRETED))
			addToLibrary(writer, capFileName, subDir.getName(), fileName, TestType.INTERPRETED);
		if(options.transpiled && !options.exclusions.isExcludedTest(subDir, fileName, Target.JAVASCRIPT, TestType.TRANSPILED))
			addToLibrary(writer, capFileName, subDir.getName(), fileName, TestType.TRANSPILED);
	}

	private OutputStreamWriter beginLibrary(String dirName, Character dialect) throws IOException {
		String capDirName = capitalize(dirName);
		String testFilePath = LIB_ROOT + "library/" + dialect.toString().toLowerCase() + "/Test" + capDirName + ".test.js";
		OutputStreamWriter library =  mkfile(testFilePath);
		beginLibrary(library, dialect.toString(), capDirName);
		return library;	
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
	
	private void addToLibrary(OutputStreamWriter writer, String capFileName, String dirName, String fileName, TestType type) throws IOException {
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
