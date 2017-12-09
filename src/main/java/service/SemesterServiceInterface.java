package service;

import java.util.List;
import java.util.UUID;

import model.Semester;

public interface SemesterServiceInterface {
	public List<Semester> findAllSemesters();
	public UUID findUUIDBySemester(String semester);
}