package dao;

import java.util.List;

import model.Semester;

public interface SemesterDAO {
	public List<Semester> selectAllSemesters();
}
