import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;

import presentation.*;

public class start {
	public static void main(String[] args) throws IllegalAccessException, SecurityException, IllegalArgumentException, InvocationTargetException, IntrospectionException {
		InterfataClient IC=new InterfataClient();
		InterfataProdus IP=new InterfataProdus();
		InterfataComanda ICC=new InterfataComanda();
	}
}