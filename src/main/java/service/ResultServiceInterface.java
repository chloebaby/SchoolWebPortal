package service;

import java.util.List;
import java.util.UUID;

import model.Result;

public interface ResultServiceInterface {
	public List<Result> findAllResults();
	public void saveResult(Result result);
	public void updateResult(Result result);
	public Result findResultById(UUID resultId);
}
