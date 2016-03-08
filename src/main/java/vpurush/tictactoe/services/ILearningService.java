package vpurush.tictactoe.services;

import java.util.List;

public interface ILearningService {

	void InitNetwork();

	void Add(List<Integer> input, List<Integer> output);
	
	void Add(double[] input, double[] output);

	double[] ConvertToDoubleArray(List<Integer> input);

	DataModel GetDataByInput(double[] input);

	void Train();

	double[] Run(List<Integer> input);

}