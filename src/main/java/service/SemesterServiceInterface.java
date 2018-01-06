package service;

import java.util.List;
import java.util.UUID;

import model.Semester;

public interface SemesterServiceInterface {
	public List<Semester> findAllSemesters();
	public UUID findUUIDBySemester(String semester);
	public Semester findSemesterByUUID(UUID semesterId);
	public void updateSemester(Semester semester);
	public void saveSemester(Semester semester);
}