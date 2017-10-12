

public class fptminer {
	public static void main(String args[]){
		validate(args);
		long startTime = System.currentTimeMillis();
		
		
		
		long endTime   = System.currentTimeMillis();
		double totalTime = endTime - startTime;
		totalTime /= 1000;
		System.out.println("Runtime: " + totalTime + "s");
	}
	
	private static void validate(String args[]){
		if(args.length!=4){
			throw new RuntimeException("Check input format");
		}
		System.exit(1);
	}
}
