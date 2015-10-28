package org.neos.batch.writer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.neos.batch.domain.User;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemWriter;
import org.springframework.core.io.Resource;

public class WriterFile implements ItemWriter<User>,ItemStream {
	
	private Resource resource;
	private int totalRecords;

	
	@Override
	public void write(List<? extends User> items) throws Exception {
		
		System.out.println("NUMERO DE ELEMENTOS A PROCESAR...:" + items.size());
		for(int i=0; i<items.size();i++){
		//if(items.size()!=0){
			User user = (User) items.get(i);
			writeFile(user);
			System.out.println(user+" ....WRITER");
			totalRecords=totalRecords+1;
			
		}
		
	}
	
	public void writeFile(User user) {
	        BufferedWriter output = null;
	        try {
	        	FileWriter fileWritter = new FileWriter(resource.getFile(),true);
	            output = new BufferedWriter(fileWritter);
	            output.append(user.toString());
	            output.append("\n");
	            output.flush();
	            //output.write(user.toString());
	        } catch ( IOException e ) {
	            e.printStackTrace();
	        } finally {
	            if ( output != null ){
	            	try {
						output.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
	            }
	        }
	}
	public void setResource(Resource resource) {
		this.resource = resource;
	}
	
	@Override
	public void open(ExecutionContext executionContext) throws ItemStreamException {
		System.out.println("OPENNNNNN");
		if (executionContext.containsKey("total.records")) {
			totalRecords = (Integer) executionContext.get("total.records");
	    }
		
	}
	@Override
	public void update(ExecutionContext executionContext) throws ItemStreamException {
		System.out.println("UPDATE");
		 executionContext.put("total.records", totalRecords);
	}
	@Override
	public void close() throws ItemStreamException {
		
		System.out.println("CLOSE");
		
		 BufferedWriter output = null;
	        try {
	        	FileWriter fileWritter = new FileWriter(resource.getFile(),true);
	            output = new BufferedWriter(fileWritter);
	            output.append("NUMERO DE REGISTROS PROCESADOS: " + totalRecords);
	            output.append("\n");
	            output.flush();
	            //output.write(user.toString());
	        } catch ( IOException e ) {
	            e.printStackTrace();
	        } finally {
	            if ( output != null ){
	            	try {
						output.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
	            }
	        }
		
	}

	
	
	

}
