package vpurush.tictactoe.services;

public class DataModel {
	public double[] input;
	public double[] output;
	public String inputStr;
	
	
	public DataModel(double[] input, double[] output){
		this.input = input;
		this.output = output;
		
		this.inputStr = DataModel.getInputStr(this.input);
	}
	
	public static String getInputStr(double[] input){
		String str = "";

		for(double d: input){
			str += d;
		}
		return str;
	}
	
}
