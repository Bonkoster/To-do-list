package DAO;

import java.util.List;

import Entityes.MemoryPart;

public interface MemoryPartDAO {
		
	public List<MemoryPart> getAll();
	
	public MemoryPart getOne(int id);
	
	public void addOne(MemoryPart mem);
	
	public void updateOne(MemoryPart mem);
	
	public void deleteOne(int id);
}
