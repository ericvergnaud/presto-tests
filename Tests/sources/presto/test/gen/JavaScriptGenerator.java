package presto.test.gen;
import java.io.File;
import java.io.IOException;


public class JavaScriptGenerator extends Generator {
	
	static final String ROOT = "JavaScript/Core/src/test/";

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
	protected void addToTranslateEPE(String dirName, String fileName) throws IOException {
		if(translateEPE==null) {
			String capDirName = capitalize(dirName);
			String testFilePath = ROOT + "presto/translate/epe/Test" + capDirName + ".js";
			translateEPE = mkfile(testFilePath);
			beginTranslateEPE(capDirName);
		}
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		translateEPE.write("exports.test");
		translateEPE.write(capFileName);
		translateEPE.write(" = function(test) {\n");
		translateEPE.write("\tcompareResourceEPE(test, \"");
		translateEPE.write(dirName);
		translateEPE.write("/");
		translateEPE.write(fileName);
		translateEPE.write("\");\n");
		translateEPE.write("};\n");
		translateEPE.write("\n");
	}

	private void beginTranslateEPE(String dirName) throws IOException {
		translateEPE.write("require(\"../../../../exploded\");\n");
		translateEPE.write("\n");
		translateEPE.write("var compareResourceEPE = require(\"../../parser/BaseParserTest\").compareResourceEPE;\n");
		translateEPE.write("\n");
	}

	private void endTranslateEPE() throws IOException {
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
	protected void addToTranslateOPO(String dirName, String fileName) throws IOException {
		if(translateOPO==null) {
			String capDirName = capitalize(dirName);
			String testFilePath = ROOT + "presto/translate/opo/Test" + capDirName + ".js";
			translateOPO = mkfile(testFilePath);
			beginTranslateOPO(capDirName);
		}
		String capFileName = capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
		capFileName = capFileName.replaceAll("-", "_");
		translateOPO.write("exports.test");
		translateOPO.write(capFileName);
		translateOPO.write(" = function(test) {\n");
		translateOPO.write("\tcompareResourceOPO(test, \"");
		translateOPO.write(dirName);
		translateOPO.write("/");
		translateOPO.write(fileName);
		translateOPO.write("\");\n");
		translateOPO.write("};\n");
		translateOPO.write("\n");
	}

	private void beginTranslateOPO(String dirName) throws IOException {
		translateOPO.write("require(\"../../../../exploded\");\n");
		translateOPO.write("\n");
		translateOPO.write("var compareResourceOPO = require(\"../../parser/BaseParserTest\").compareResourceOPO;\n");
		translateOPO.write("\n");
	}

	private void endTranslateOPO() throws IOException {
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
	protected void addToRuntimeP(String dirName, String fileName) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
