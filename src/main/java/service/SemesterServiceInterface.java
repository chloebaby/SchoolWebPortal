package service;

import java.util.Set;
import java.util.UUID;

import model.Semester;

public interface SemesterServiceInterface {
	public Set<Semester> findAllSemesters();
	public UUID findUUIDBySemester(String semester);
	public Semester findSemesterByUUID(UUID semesterId);
	public void updateSemester(Semester semester);
	public void saveSemester(Semester semester);
}