package backend.interactivity;

public enum Functionality {

	temp(Functions.class, "temp");
	
	public final Class<?> CLASS;
	public final String NAME;
	private Functionality(final Class<?> cls, final String name) {
		this.CLASS = cls;
		this.NAME = name;
	}
	
}
