package vpurush.tictactoe.services;

import java.util.ArrayList;
import java.util.List;

import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.BackPropagation;
import org.neuroph.util.TransferFunctionType;
import org.springframework.stereotype.Service;

@Service
public class LearningService implements ILearningService {
	private MultiLayerPerceptron nnet;
	private List<DataModel> data;
	
	public LearningService(){
		this.data = new ArrayList<DataModel>();
	}
	
	/* (non-Javadoc)
	 * @see vpurush.tictactoe.services.ILearningService#InitNetwork()
	 */
	public void InitNetwork(){
		this.nnet = new MultiLayerPerceptron(TransferFunctionType.TANH, 9, 9, 9, 9);
	}
	
	/* (non-Javadoc)
	 * @see vpurush.tictactoe.services.ILearningService#Add(java.util.List, java.util.List)
	 */
	public void Add(List<Integer> input, List<Integer> output){
		double[] dInput = this.ConvertToDoubleArray(input);
		double[] dOutput = this.ConvertToDoubleArray(output);
		
		this.Add(dInput, dOutput);
	}
	
	public void Add(double[] input, double[] output){
		
		DataModel d = this.GetDataByInput(input);
		if(d != null){
			for (int i = 0; i < output.length; i++) {
				if(output[i] == 1){
					d.output[i] = 1;
				}
			}
		}else{		
			this.data.add(new DataModel(input, output));
		}
		
	}
	
	/* (non-Javadoc)
	 * @see vpurush.tictactoe.services.ILearningService#ConvertToDoubleArray(java.util.List)
	 */
	public double[] ConvertToDoubleArray(List<Integer> input){
		double[] output = new double[input.size()];
		
		for(int i=0; i<input.size(); i++){
			output[i] = (double) input.get(i);
		}
		
		return output;
	}
	
	/* (non-Javadoc)
	 * @see vpurush.tictactoe.services.ILearningService#GetDataByInput(double[])
	 */
	public DataModel GetDataByInput(double[] input){
		String str = DataModel.getInputStr(input);
		DataModel output = null;
		for(DataModel d:this.data){
			if(d.inputStr == str){
				output = d;
			}
		}
		return output;
	}
	
	/* (non-Javadoc)
	 * @see vpurush.tictactoe.services.ILearningService#Train()
	 */
	public void Train(){
		this.InitNetwork();
		DataSet ds = new DataSet(9,9);
		
		for(DataModel d: this.data){
			System.out.println("d.input.length" + d.input.length + "d.output.length" + d.output.length);
			ds.addRow(new DataSetRow(d.input, d.output));
		}
		this.nnet.learn(ds);
	}
	
	/* (non-Javadoc)
	 * @see vpurush.tictactoe.services.ILearningService#Run(java.util.List)
	 */
	public double[] Run(List<Integer> input){
		double dInput[] = this.ConvertToDoubleArray(input);
		this.nnet.setInput(dInput);
		this.nnet.calculate();
		return this.nnet.getOutput();
	}

}
