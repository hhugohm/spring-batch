package org.neos.batch.job.processor;

import org.apache.commons.beanutils.BeanUtils;
import org.joda.time.DateTime;
import org.neos.batch.dao.UserErrorDao;
import org.neos.batch.domain.User;
import org.neos.batch.domain.UserMapper;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;

public class UserProcessor implements ItemProcessor<UserMapper,User> {
	
	private Validator validator;  
	
	@Autowired
	private UserErrorDao userDao;
	
	@Override
	public User process(UserMapper userMapper) throws Exception {
		
		User user = new User();
		user.setDateSave(new DateTime());// fecha de alta
		
		if(userMapper.getIdUser().equals("")||userMapper.getIdUser().equals("ERROR")){
			userDao.saveErrorUser(userMapper.getRecord());
			return null;
		}else{
			BindingResult results = BindAndValidate(userMapper);
			  if (results.hasErrors())  {
				  buildValidationException(results);  
				  userDao.saveErrorUser(userMapper.getRecord());
				  return null;
			  }
			  else{
				  BeanUtils.copyProperties(user, userMapper);
					System.out.println(user.toString()+"PrOCESSOR");
					
					return user;
			  }
			
		}
		
	}
	
	private BindingResult BindAndValidate(UserMapper item) {  
        DataBinder binder = new DataBinder(item);  
        System.out.println("VALIDANDO EL OBJETO......");
        binder.setValidator(validator);  

        binder.validate();  

        return binder.getBindingResult();  

   }  
    private void buildValidationException(BindingResult results) {  
        StringBuilder msg = new StringBuilder();  
        for (ObjectError error : results.getAllErrors()) {  
        	System.out.println("ERROR:"+ error.getDefaultMessage());
             msg.append("-*-*-*- \n" + error.toString() + "-*-*-*- \n");  
        }  
       
   }  
	

	public void setValidator(Validator validator) {
		this.validator = validator;
	}
	
	
	
}
