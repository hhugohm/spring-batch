package org.neos.batch.job.processor;

import org.apache.commons.beanutils.BeanUtils;
import org.joda.time.DateTime;
import org.neos.batch.domain.User;
import org.neos.batch.domain.UserMapper;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class UserBatchProcessor  implements ItemProcessor<UserMapper,User> {

	@Override
	public User process(UserMapper userMapper) throws Exception {
		
		System.out.println("EN EL PROCESOR.............................");
		
		User user = new User();
		user.setDateSave(new DateTime());// fecha de alta
		
		BeanUtils.copyProperties(user, userMapper);
	
		return user;
	}

	

}
