package main;

import java.io.IOException;

import controller.Controller;

public class MVC {

	private static Controller controller; 
	public static void main(String[] args) throws IOException 
	{
		controller = new Controller();
		controller.run();
		
	}

}
