package vpurush.tictactoe.services;

import java.util.ArrayList;
import java.util.List;

import org.la4j.matrix.DenseMatrix;
import org.la4j.matrix.dense.Basic1DMatrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainingService implements ITrainingService {
	
	@Autowired
	ILearningService learningService;

	
	DenseMatrix board;
	
	/* (non-Javadoc)
	 * @see vpurush.tictactoe.services.ITrainingService#Init()
	 */
	public void Init(){
		board = new Basic1DMatrix().blankOfShape(3, 3).toDenseMatrix();
	}
	
	/* (non-Javadoc)
	 * @see vpurush.tictactoe.services.ITrainingService#GenerateMasterData(org.la4j.Matrix, int, int)
	 */
	public List<DenseMatrix> GenerateMasterData(DenseMatrix input, int i, int j){
		List<DenseMatrix> output = new ArrayList<DenseMatrix>();
		
		if(input == null){
			input = new Basic1DMatrix().blankOfShape(3, 3).toDenseMatrix();
			i = 0;
			j = 0;
		}else{
			if(j < 2){
				j += 1;
			}else if(i < 2){
				j = 0;
				i += 1;
			}
		}

		DenseMatrix mat1 = input.copy().toDenseMatrix();
		DenseMatrix mat2 = input.copy().toDenseMatrix();
		DenseMatrix mat3 = input.copy().toDenseMatrix();			
		mat1.set(i, j, -1);
		mat2.set(i, j, 0);
		mat3.set(i, j, 1);
		if(i== 2 && j == 2){
			output.add(mat1);
			output.add(mat2);
			output.add(mat3);
		}else{
			output.addAll(this.GenerateMasterData(mat1, i, j));
			output.addAll(this.GenerateMasterData(mat2, i, j));
			output.addAll(this.GenerateMasterData(mat3, i, j));
		}
		
		
		return output;
	}
	
	public void TrainMasterData(){
		List<DenseMatrix> matrixList = this.GenerateMasterData(null, 0, 0);
		
		for(DenseMatrix m: matrixList){
			double[][] inputArr = m.toArray();
			double[] input = new double[] {inputArr[0][0], 
										inputArr[0][1],
										inputArr[0][2],
										inputArr[1][0],
										inputArr[1][1],
										inputArr[1][2],
										inputArr[2][0],
										inputArr[2][1],
										inputArr[2][2]
								};
			
			double[] output = new double[] {0, 0, 0, 0, 0, 0, 0, 0, 0};
			
			learningService.Add(input, output);
			
		}
		
		learningService.Train();
	}
}
