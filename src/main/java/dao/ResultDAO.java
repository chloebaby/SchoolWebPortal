package dao;

import java.util.List;
import java.util.UUID;

import model.Result;

public interface ResultDAO {
	public List<Result> selectAllResults();
	public void insertResult(Result result);
	public void updateResult(Result result);
	public Result selectResultById(UUID resultId);
}
