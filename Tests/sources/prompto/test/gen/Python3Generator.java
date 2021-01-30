package prompto.test.gen;

public class Python3Generator extends PythonGenerator {

	static final String CORE_ROOT = "prompto-python3/Python3-Core/src/test/";
	static final String LIB_ROOT = "prompto-python3/Python3-Runtime/src/test/";

	@Override
	protected Target getTarget() {
		return Target.PYTHON3;
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
