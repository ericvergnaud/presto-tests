package presto.test.gen;
import java.io.File;
import java.io.IOException;


public class JavaScriptGenerator extends Generator {
	
	static final String ROOT = "presto-javascript/JavaScript-Core/src/test/";

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
			String testFilePath = ROOT + "presto/translate/eoe/Test" + capDirName + ".js";
			translateEOE = mkfile(testFilePath);
			beginTranslateEOE(capDirName);
		}
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		translateEOE.write("exports.test");
		translateEOE.write(capFileName);
		translateEOE.write(" = function(test) {\n");
		translateEOE.write("\tcompareResourceEOE(test, \"");
		translateEOE.write(dirName);
		translateEOE.write("/");
		translateEOE.write(fileName);
		translateEOE.write("\");\n");
		translateEOE.write("};\n");
		translateEOE.write("\n");
	}

	private void beginTranslateEOE(String dirName) throws IOException {
		translateEOE.write("require(\"../../../../exploded\");\n");
		translateEOE.write("\n");
		translateEOE.write("var compareResourceEOE = require(\"../../parser/BaseParserTest\").compareResourceEOE;\n");
		translateEOE.write("\n");
	}

	private void endTranslateEOE() throws IOException {
	}
	
	@Override
	protected void addToTranslateESE(String dirName, String fileName) throws IOException {
		if(translateESE==null) {
			String capDirName = capitalize(dirName);
			String testFilePath = ROOT + "presto/translate/ese/Test" + capDirName + ".js";
			translateESE = mkfile(testFilePath);
			beginTranslateESE(capDirName);
		}
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		translateESE.write("exports.test");
		translateESE.write(capFileName);
		translateESE.write(" = function(test) {\n");
		translateESE.write("\tcompareResourceESE(test, \"");
		translateESE.write(dirName);
		translateESE.write("/");
		translateESE.write(fileName);
		translateESE.write("\");\n");
		translateESE.write("};\n");
		translateESE.write("\n");
	}

	private void beginTranslateESE(String dirName) throws IOException {
		translateESE.write("require(\"../../../../exploded\");\n");
		translateESE.write("\n");
		translateESE.write("var compareResourceESE = require(\"../../parser/BaseParserTest\").compareResourceEPE;\n");
		translateESE.write("\n");
	}

	private void endTranslateESE() throws IOException {
	}

	@Override
	protected void addToTranslateOEO(String dirName, String fileName) throws IOException {
		if(translateOEO==null) {
			String capDirName = capitalize(dirName);
			String testFilePath = ROOT + "presto/translate/oeo/Test" + capDirName + ".js";
			translateOEO = mkfile(testFilePath);
			beginTranslateOEO(capDirName);
		}
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		translateOEO.write("exports.test");
		translateOEO.write(capFileName);
		translateOEO.write(" = function(test) {\n");
		translateOEO.write("\tcompareResourceOEO(test, \"");
		translateOEO.write(dirName);
		translateOEO.write("/");
		translateOEO.write(fileName);
		translateOEO.write("\");\n");
		translateOEO.write("};\n");
		translateOEO.write("\n");
	}

	private void beginTranslateOEO(String dirName) throws IOException {
		translateOEO.write("require(\"../../../../exploded\");\n");
		translateOEO.write("\n");
		translateOEO.write("var compareResourceOEO = require(\"../../parser/BaseParserTest\").compareResourceOEO;\n");
		translateOEO.write("\n");
	}

	private void endTranslateOEO() throws IOException {
	}

	@Override
	protected void addToTranslateOSO(String dirName, String fileName) throws IOException {
		if(translateOSO==null) {
			String capDirName = capitalize(dirName);
			String testFilePath = ROOT + "presto/translate/oso/Test" + capDirName + ".js";
			translateOSO = mkfile(testFilePath);
			beginTranslateOSO(capDirName);
		}
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		translateOSO.write("exports.test");
		translateOSO.write(capFileName);
		translateOSO.write(" = function(test) {\n");
		translateOSO.write("\tcompareResourceOSO(test, \"");
		translateOSO.write(dirName);
		translateOSO.write("/");
		translateOSO.write(fileName);
		translateOSO.write("\");\n");
		translateOSO.write("};\n");
		translateOSO.write("\n");
	}

	private void beginTranslateOSO(String dirName) throws IOException {
		translateOSO.write("require(\"../../../../exploded\");\n");
		translateOSO.write("\n");
		translateOSO.write("var compareResourceOSO = require(\"../../parser/BaseParserTest\").compareResourceOSO;\n");
		translateOSO.write("\n");
	}

	private void endTranslateOSO() throws IOException {
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
			String testFilePath = ROOT + "presto/runtime/e/Test" + capDirName + ".js";
			runtimeE = mkfile(testFilePath);
			beginRuntimeE(capDirName);
		}
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		runtimeE.write("exports.test");
		runtimeE.write(capFileName);
		runtimeE.write(" = function(test) {\n");
		runtimeE.write("\tcheckOutput(test, \"");
		runtimeE.write(dirName);
		runtimeE.write("/");
		runtimeE.write(fileName);
		runtimeE.write("\");\n");
		runtimeE.write("};\n");
		runtimeE.write("\n");
	}

	private void beginRuntimeE(String dirName) throws IOException {
		runtimeE.write("require(\"../../../../exploded\");\n");
		runtimeE.write("\n");
		runtimeE.write("var Out = require(\"../utils/Out\").Out;\n");
		runtimeE.write("var checkOutput = require(\"../../parser/BaseEParserTest\").checkOutput;\n");
		runtimeE.write("\n");
		runtimeE.write("exports.setUp = function(done) {\n");
		runtimeE.write("\tOut.init();\n");
		runtimeE.write("\tdone();\n");
		runtimeE.write("};\n");
		runtimeE.write("\n");
		runtimeE.write("exports.tearDown = function(done) {\n");
		runtimeE.write("\tOut.restore();\n");
		runtimeE.write("\tdone();\n");
		runtimeE.write("};\n");
		runtimeE.write("\n");
	}

	private void endRuntimeE() throws IOException {
	}
	
	@Override
	protected void addToRuntimeO(String dirName, String fileName) throws IOException {
		if(runtimeO==null) {
			String capDirName = capitalize(dirName);
			String testFilePath = ROOT + "presto/runtime/o/Test" + capDirName + ".js";
			runtimeO = mkfile(testFilePath);
			beginRuntimeO(capDirName);
		}
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		runtimeO.write("exports.test");
		runtimeO.write(capFileName);
		runtimeO.write(" = function(test) {\n");
		runtimeO.write("\tcheckOutput(test, \"");
		runtimeO.write(dirName);
		runtimeO.write("/");
		runtimeO.write(fileName);
		runtimeO.write("\");\n");
		runtimeO.write("};\n");
		runtimeO.write("\n");
	}

	private void beginRuntimeO(String cdirName) throws IOException {
		runtimeO.write("require(\"../../../../exploded\");\n");
		runtimeO.write("\n");
		runtimeO.write("var Out = require(\"../utils/Out\").Out;\n");
		runtimeO.write("var checkOutput = require(\"../../parser/BaseOParserTest\").checkOutput;\n");
		runtimeO.write("\n");
		runtimeO.write("exports.setUp = function(done) {\n");
		runtimeO.write("\tOut.init();\n");
		runtimeO.write("\tdone();\n");
		runtimeO.write("};\n");
		runtimeO.write("\n");
		runtimeO.write("exports.tearDown = function(done) {\n");
		runtimeO.write("\tOut.restore();\n");
		runtimeO.write("\tdone();\n");
		runtimeO.write("};\n");
		runtimeO.write("\n");
	}

	private void endRuntimeO() throws IOException {
	}


	@Override
	protected void addToRuntimeS(String dirName, String fileName) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
