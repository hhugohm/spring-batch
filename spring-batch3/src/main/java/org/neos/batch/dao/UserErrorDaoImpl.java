package org.neos.batch.dao;

import org.springframework.stereotype.Repository;

@Repository
public class UserErrorDaoImpl implements UserErrorDao {

	@Override
	public void saveErrorUser(int numRecord) {
	
		System.out.println("@@@@@@@FALLO EL REGISTRO@@@@@@@@: "+ numRecord);

	}

}
