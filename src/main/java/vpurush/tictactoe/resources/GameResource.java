package vpurush.tictactoe.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.la4j.matrix.DenseMatrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vpurush.tictactoe.services.ILearningService;
import vpurush.tictactoe.services.ITrainingService;

@Component
@Path("/game")
public class GameResource {
	
	@Autowired
	ITrainingService trainingService;
	
	@Autowired
	ILearningService learningService;


	@Path("/play")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String Play(){
		return "output";
	}

	@Path("/train")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String Train(){
		List<DenseMatrix> output = trainingService.GenerateMasterData(null, 0, 0);
		trainingService.TrainMasterData();
		return "output" + output.size();
	}

	@Path("/save")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String Save(){
		return "output";
	}
	
}
