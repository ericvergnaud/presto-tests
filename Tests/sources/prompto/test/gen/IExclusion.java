package prompto.test.gen;

import java.io.File;

@FunctionalInterface
public interface IExclusion {

	boolean isExcluded(File subDir, String fileName, Target target, TestType type);
	
}