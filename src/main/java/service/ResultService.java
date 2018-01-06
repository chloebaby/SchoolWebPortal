package service;

import java.util.List;
import java.util.UUID;

import org.springframework.transaction.annotation.Transactional;

import dao.ResultDAO;
import daoimplementation.DAOResultImplementation;
import model.Result;

public class ResultService implements ResultServiceInterface {
	private ResultDAO daoResultImplementation;
	
	public ResultService() {}
	
	public ResultService(ResultDAO daoResultImplementation) {
		this.daoResultImplementation = daoResultImplementation;
	}
	
	@Override
	@Transactional
	public List<Result> findAllResults(){
		return daoResultImplementation.selectAllResults();
	}
	
	@Override
	@Transactional
	public void saveResult(Result result) {
		daoResultImplementation.insertResult(result);
	}
	
	@Override
	@Transactional
	public void updateResult(Result result) {
		daoResultImplementation.updateResult(result);
	}

	@Override
	@Transactional
	public Result findResultById(UUID resultId) {
		return daoResultImplementation.selectResultById(resultId);
	}

	public ResultDAO getDaoResultImplementation() {
		return daoResultImplementation;
	}

	public void setDaoResultImplementation(ResultDAO daoResultImplementation) {
		this.daoResultImplementation = daoResultImplementation;
	}
}
