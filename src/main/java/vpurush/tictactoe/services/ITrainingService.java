package vpurush.tictactoe.services;

import java.util.List;

import org.la4j.Matrix;
import org.la4j.matrix.DenseMatrix;

public interface ITrainingService {

	void Init();

	List<DenseMatrix> GenerateMasterData(DenseMatrix input, int i, int j);

	void TrainMasterData();
}