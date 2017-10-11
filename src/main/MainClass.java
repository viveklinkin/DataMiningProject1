package main;


public class MainClass {
	public static void main(String args[]){
		validate(args);
		
	}
	
	private static void validate(String args[]){
		if(args.length!=4){
			throw new RuntimeException("Check input format");
		}
		System.exit(1);
	}
}
