package service;

import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.SemesterDAO;
import daoimplementation.DAOSemesterImplementation;
import model.Semester;

@Service
public class SemesterService implements SemesterServiceInterface {
	private SemesterDAO daoSemesterImplementation;
	
	public SemesterService() {}
	
	public SemesterService(SemesterDAO daoSemesterImplementation) {
		this.daoSemesterImplementation = daoSemesterImplementation;
	}
	
	@Override
	@Transactional
	public Set<Semester> findAllSemesters(){
		return daoSemesterImplementation.selectAllSemesters();
	}
	
	@Override
	@Transactional
	public UUID findUUIDBySemester(String semester) {
		return daoSemesterImplementation.selectUUIDBySemesterName(semester);
	}
	
	@Override
	@Transactional
	public Semester findSemesterByUUID(UUID semesterId) {
		return daoSemesterImplementation.selectSemesterByUUID(semesterId);
	}
	
	@Override
	@Transactional
	public void updateSemester(Semester semester) {
		daoSemesterImplementation.updateSemester(semester);
	}
	
	@Override
	@Transactional
	public void saveSemester(Semester semester) {
		daoSemesterImplementation.saveSemester(semester);
	}

	public SemesterDAO getDaoSemesterImplementation() {
		return daoSemesterImplementation;
	}

	public void setDaoSemesterImplementation(SemesterDAO daoSemesterImplementation) {
		this.daoSemesterImplementation = daoSemesterImplementation;
	}

}
