package prompto.test.gen;

public enum TestType {
	INTERPRETED,
	COMPILED,
	TRANSPILED;
	
	@Override
	public String toString() {
		return this.name().substring(0, 1) + this.name().substring(1).toLowerCase();
	}
}