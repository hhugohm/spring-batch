package org.neos.batch.job.processor;

import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;
import org.neos.batch.domain.User;
import org.neos.batch.domain.UserMapper;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class UserBatchProcessor  implements ItemProcessor<UserMapper,User> {

	@Override
	public User process(UserMapper userMapper) throws Exception {
		
		User user = new User();
		user.setDateSave(new Date());// fecha de alta
		
		BeanUtils.copyProperties(user, userMapper);
	
		return user;
	}

	

}
