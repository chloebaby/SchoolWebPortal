package dao;

import java.util.List;

import model.Result;

public interface ResultDAO {
	public List<Result> selectAllResults();
	public void insertResult(Result result);
	public void updateResult(Result result);
}
