package org.chetan.dao;

import java.util.List;
import org.chetan.model.User;
import org.chetan.model.Wall;

public interface WallDao 
{
	void create(Wall wall);
	void update(Wall wall);
	int delete(long wallId);
	Wall find(long wallId);	
	List<Wall> getAllWalls(User wallToUser);
}
