package dao;

import java.util.Set;
import java.util.UUID;

import model.Semester;

public interface SemesterDAO {
	public Set<Semester> selectAllSemesters();
	public UUID selectUUIDBySemesterName(String semester);
	public Semester selectSemesterByUUID(UUID semesterId);
	public void updateSemester(Semester semester);
	public void saveSemester(Semester semester);
}
