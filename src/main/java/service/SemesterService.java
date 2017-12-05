package service;

import java.util.List;

import dao.SemesterDAO;
import daoimplementation.DAOSemesterImplementation;
import model.Semester;

public class SemesterService implements SemesterServiceInterface {
	private SemesterDAO daoSemesterImplementation;
	
	public SemesterService() {
		daoSemesterImplementation = new DAOSemesterImplementation();
	}
	
	@Override
	public List<Semester> findAllSemesters(){
		return daoSemesterImplementation.selectAllSemesters();
	}

}
