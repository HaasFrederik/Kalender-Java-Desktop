package backend.interactivity;

import java.awt.Component;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public interface Interactible {

	public void doOn(UserAction userAction, Functionality function);
	
	default public MethodHandle resolveFunctionality(Functionality func, Component comp) throws NoSuchMethodException, IllegalAccessException {
		MethodType methodType = MethodType.methodType(void.class, comp.getClass());
		MethodHandles.Lookup finder = MethodHandles.lookup();
		return finder.findStatic(func.CLASS, func.NAME, methodType);
		
	}
	
}
