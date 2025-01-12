package common;

import stepDefinitions.Hooks;

public class Validations {


	public boolean verifyActualEqualsExpected(String actual, String expected) {
		
		Hooks.scenario.log("Expected Text:"+expected+"\nActual Text:"+actual);
		if(actual.equalsIgnoreCase(expected))
			return true;
		else
			return false;
	}
	
	public boolean verifyActualContainsExpected(String actual, String expected) {
		Hooks.scenario.log("Expected Text:"+expected+"\nActual Text:"+actual);
		if(actual.contains(expected))
			return true;
		else
			return false;
	}

}
