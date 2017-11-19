package dao;

public interface HibernateDAO {
	public void openSession();
	public void openTransaction();
	public void closeSession();
	public void closeTransaction();
	public void close();
}
