package org.neos.batch.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import org.neos.batch.domain.UserMapper;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.core.io.Resource;

public class ReaderFile implements ItemReader<UserMapper> {
	private int itemFile;
	private int numeroLinea;
	private int contador;
	private String currentLine;
	private List<String> listaUser;
	private String[] values;
	private BufferedReader br;
	private Resource resource;
	private LineMapper<UserMapper> lineMapper;
	
	@Override
	public UserMapper read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

		if (contador == 0) {
			try {
				br = new BufferedReader(new FileReader(resource.getFile()));
				numeroLinea = 0;
				while ((currentLine = br.readLine()) != null) {
					numeroLinea++;
					if (numeroLinea == 1) {
						values= getTokenizerHeadFile("|",currentLine);
					} else {
						if (!currentLine.trim().equals("")) {
							listaUser.add(currentLine);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				
			}finally{
				if (br != null)
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
			}
		 System.out.println("LISTA: " + listaUser.size());
		 contador++;
		
		 
		 }
		 
		if( Integer.valueOf(values[2])==listaUser.size()){
			if (itemFile < listaUser.size()) {
				UserMapper user =lineMapper.mapLine(listaUser.get(itemFile), itemFile);
				user.setRecord(itemFile+1);
				itemFile++;
				return user;
			} else {
			return null;
			}
		}else{
			System.out.println("NO CORRESPONDE EL NUMERO DE REGISTROS A PROCESAR.......");
			return null;
		}
		
	}
	
	
	public String[] getTokenizerHeadFile(String tokenizer,String line){
		StringTokenizer st = new StringTokenizer(line,tokenizer);
		String[] values = new String[3];
		int count =0;
		while(st.hasMoreTokens()) {
			values[count]= st.nextToken();
			count++;
		}
		return values;
	}
	
	public int getNumberOfLines(){
		
		int lines = 0;
		try {
			br = new BufferedReader(new FileReader(resource.getFile()));
			while (br.readLine() != null) lines++;
			br.close();
		} catch (IOException e ) {
			e.printStackTrace();
			lines=0;
		}
		
		return lines-1;//se le resta la cabecera
	}
	
	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public void setLineMapper(LineMapper<UserMapper> lineMapper) {
		this.lineMapper = lineMapper;
	}
	public void setListaUser(List<String> listaUser) {
		this.listaUser = listaUser;
	}	
	
}
