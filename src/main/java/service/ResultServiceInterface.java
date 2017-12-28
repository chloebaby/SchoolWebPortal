package service;

import java.util.List;

import model.Result;

public interface ResultServiceInterface {
	public List<Result> findAllResults();
	public void saveResult(Result result);
	public void updateResult(Result result);
}
