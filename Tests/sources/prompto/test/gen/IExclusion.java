package prompto.test.gen;

public interface IExclusion {

	boolean isExcluded(String dirName, String fileName, Target target, TestType type);
	
}