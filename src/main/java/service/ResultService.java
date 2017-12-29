package service;

import java.util.List;
import java.util.UUID;

import dao.ResultDAO;
import daoimplementation.DAOResultImplementation;
import model.Result;

public class ResultService implements ResultServiceInterface {
	private ResultDAO daoResultImplementation;
	
	
	public ResultService() {
		daoResultImplementation = new DAOResultImplementation();
	}
	
	@Override
	public List<Result> findAllResults(){
		return daoResultImplementation.selectAllResults();
	}
	
	@Override
	public void saveResult(Result result) {
		daoResultImplementation.insertResult(result);
	}
	
	@Override
	public void updateResult(Result result) {
		daoResultImplementation.updateResult(result);
	}

	@Override
	public Result findResultById(UUID resultId) {
		return daoResultImplementation.selectResultById(resultId);
	}
}
