package org.neos.batch.domain;

import org.joda.time.DateTime;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class ResultFieldSetMapper implements FieldSetMapper<UserMapper>{

	@Override
	public UserMapper mapFieldSet(FieldSet fieldSet) throws BindException  {
		UserMapper result = new UserMapper();
		try{
			if(fieldSet.getFieldCount()==6){
			
				result.setIdUser(fieldSet.readString(0));
				result.setName(fieldSet.readString(1));
				result.setLastName(fieldSet.readString(2));
				result.setOld(fieldSet.readInt(3));
				result.setEmail(fieldSet.readString(4));
				result.setDateBorn(new DateTime(fieldSet.readDate(5,"dd/MM/yyyy")));
			}else{
				return new UserMapper("ERROR");
			}
			return result;
		}catch(Exception e){
			return new UserMapper("ERROR");
		}
	}

}
