package prompto.test.gen;

public class Options {
	
	boolean interpreted = true;
	boolean compiled = true;
	boolean transpiled = true;
	boolean java = true;
	boolean csharp = true;
	boolean python2 = true;
	boolean python3 = true;
	boolean javascript = true;
	Exclusions exclusions = null;
	
	public Options withExclusions(Exclusions exclusions) {
		this.exclusions = exclusions;
		return this;
	}
	
}
