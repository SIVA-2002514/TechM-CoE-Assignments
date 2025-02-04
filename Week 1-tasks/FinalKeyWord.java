public class DisplayingValues 
{                                                                                                    
public static final String FINAL_METHOD_MESSAGE = "THIS IS FINAL METHOD";                              
}   

final class FinalClass {  //Final class cannot be inherited

	// Final variable cannot be changed once initialized
	public final int finalVariable = 100;

	// Final method cannot be overridden
	public final void finalMethod() {
		System.out.println(DisplayingValues.FINAL_METHOD_MESSAGE);
	}
}
