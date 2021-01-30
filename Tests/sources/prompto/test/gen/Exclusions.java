package prompto.test.gen;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Exclusions {
	
	Set<String> excludedDirs = Collections.emptySet();
	Set<String> excludedFiles = Collections.emptySet();
	List<IExclusion> exclusions = new ArrayList<>();
	
	Exclusions withExcludedDirs(List<String> dirs) {
		excludedDirs = new HashSet<>(dirs);
		return this; 
	}
	
	Exclusions withExcludedFiles(List<String> files) {
		excludedFiles = new HashSet<>(files);
		return this; 
	}

	public Exclusions withExclusion(IExclusion exclusion) {
		exclusions.add(exclusion);
		return this;
	}

	public boolean isExcludedDir(String dirName) {
		return excludedDirs.contains(dirName);
	}

	public boolean isExcludedFile(File subDir, String fileName, Target target) {
		return excludedFiles.contains(fileName) || isExcludedTest(subDir, fileName, target, TestType.INTERPRETED);
	}

	public boolean isExcludedTest(File subDir, String fileName, Target target, TestType type) {
		return exclusions.stream().anyMatch(e->e.isExcluded(subDir, fileName, target, type));
	}

	
}