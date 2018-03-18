package prompto.test.gen;

public class Python2Generator extends PythonGenerator {

	static final String CORE_ROOT = "prompto-python2/Python2-Core/src/test/";
	static final String LIB_ROOT = "prompto-python2/Python2-Runtime/src/test/";

	@Override
	protected String getTarget() {
		return "Python 2";
	}
	
	@Override
	protected String getCoreRoot() {
		return CORE_ROOT;
	}
	
	@Override
	protected String getLibraryRoot() {
		return LIB_ROOT;
	}
}
