package service;

import java.util.List;
import java.util.UUID;

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
	
	@Override
	public UUID findUUIDBySemester(String semester) {
		return daoSemesterImplementation.selectUUIDBySemesterName(semester);
	}
	
	@Override
	public Semester findSemesterByUUID(UUID semesterId) {
		return daoSemesterImplementation.selectSemesterByUUID(semesterId);
	}
	
	@Override
	public void updateSemester(Semester semester) {
		daoSemesterImplementation.updateSemester(semester);
	}

}
