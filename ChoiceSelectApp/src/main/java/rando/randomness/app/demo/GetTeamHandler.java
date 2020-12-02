package rando.randomness.app.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import choice.select.app.http.GetTeamRequest;
import choice.select.app.http.GetTeamResponse;
import rando.randomness.app.demo.db.ChoiceDAO;
import rando.randomness.app.demo.model.Team;

public class GetTeamHandler implements RequestHandler<GetTeamRequest, GetTeamResponse> {

	LambdaLogger logger;

	@Override
	public GetTeamResponse handleRequest(GetTeamRequest req, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler of GetChoiceHandler");
		logger.log(req.toString());

		ChoiceDAO dao =  new ChoiceDAO();
		GetTeamResponse response;

		try {
			Team t = dao.retrieveTeam(req.getTid());
			response = new GetTeamResponse(t);
		} catch (Exception e) {
			e.printStackTrace();
			response = new GetTeamResponse(400, "unexpected error retrieving team");
		}

		return response;
	}

}
