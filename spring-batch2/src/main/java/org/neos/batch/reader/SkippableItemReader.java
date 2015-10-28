package org.neos.batch.reader;

import java.io.BufferedReader;

import java.io.FileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;

public class SkippableItemReader<UserMapper>  extends DelimitedLineTokenizer implements  ItemStreamReader<UserMapper> {

	private Resource resource;
	private String cadenaFinal;
	private String sCurrentLine;
	private boolean noInput = true;
	private BufferedReader br;
	private int numeroLinea;
	private String delimiter = new String("|");
	public static final char DEFAULT_QUOTE_CHARACTER = '"';
	//private FieldSetMapper<T> fieldSetMapper;
	private LineMapper<UserMapper> lineMapper;
	
	public void setResource(Resource resource) {
		this.resource = resource;
	}
	@Override
	public void open(ExecutionContext executionContext) throws ItemStreamException {
		System.out.println("EN EL OPEN.....");
		 int lines= getNumberOfLines();
		 System.out.println("TOTAL LINES: " + lines);
		
		try{
			
			br = new BufferedReader(new FileReader(resource.getFile()));
			numeroLinea=0;
			while ((sCurrentLine = br.readLine()) != null) {
				numeroLinea++;
				cadenaFinal = cadenaFinal+ " "+ sCurrentLine;
				noInput = false;
				read();
			}
			
		}catch(Exception e){
			e.printStackTrace();
			
		}finally{
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		noInput = true;
	}
	
	@Override
	public UserMapper read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		System.out.println("ENTRAAAAAAAAAAAAAAAAA:::::");
		if (noInput) {
			return null;
		}else{
			if(numeroLinea==1){
				System.out.println("......VALIDACION de las cifras control.....");
				return null;
			}else{
				
				//List<String> tokens = doTokenize(sCurrentLine);
				//System.out.println("LISTA VALUES : " + tokens.size());
				//if(tokens.size()==1){
					System.out.println("@@DO-READ@@"+ sCurrentLine);
					System.out.println("CONTADOR: " + numeroLinea);
					//String[] values = tokens.toArray(new String[tokens.size()]);
					UserMapper user = null;
					//UserMapper user =lineMapper.mapLine(sCurrentLine, numeroLinea);
					return user;
				//}
			}
			
			 
		}
		
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
	
	public void setLineMapper(LineMapper<UserMapper> lineMapper) {
		this.lineMapper = lineMapper;
	}
	/*
	public List<String> doTokenize(String line) {

		List<String> tokens = new ArrayList<String>();

		// line is never null in current implementation
		// line is checked in parent: AbstractLineTokenizer.tokenize()
		char[] chars = line.toCharArray();
		boolean inQuoted = false;
		int lastCut = 0;
		int length = chars.length;
		int fieldCount = 0;
		int endIndexLastDelimiter = -1;

		for (int i = 0; i < length; i++) {
			char currentChar = chars[i];
			boolean isEnd = (i == (length - 1));

			boolean isDelimiter = isDelimiter(chars, i, delimiter, endIndexLastDelimiter);

			if ((isDelimiter && !inQuoted) || isEnd) {
				endIndexLastDelimiter = i;
				int endPosition = (isEnd ? (length - lastCut) : (i - lastCut));

				if (isEnd && isDelimiter) {
					endPosition = endPosition - delimiter.length();
				}
				else if (!isEnd){
					endPosition = (endPosition - delimiter.length()) + 1;
				}

				if (includedFields == null || includedFields.contains(fieldCount)) {
					String value = maybeStripQuotes(new String(chars, lastCut, endPosition));
					tokens.add(value);
				}

				fieldCount++;

				if (isEnd && (isDelimiter)) {
					if (includedFields == null || includedFields.contains(fieldCount)) {
						tokens.add("");
					}
					fieldCount++;
				}

				lastCut = i + 1;
			}
			else if (isQuoteCharacter(currentChar)) {
				inQuoted = !inQuoted;
			}

		}

		return tokens;
	}
	private boolean isDelimiter(char[] chars, int i, String token, int endIndexLastDelimiter) {
		boolean result = false;

		if(i-endIndexLastDelimiter >= delimiter.length()) {
			if(i >= token.length() - 1) {
				String end = new String(chars, (i-token.length()) + 1, token.length());
				if(token.equals(end)) {
					result = true;
				}
			}
		}

		return result;
	}
	
	private String maybeStripQuotes(String string) {
		String value = string.trim();
		if (isQuoted(value)) {
			value = StringUtils.replace(value, "'", "'");
			int endLength = value.length() - 1;
			// used to deal with empty quoted values
			if (endLength == 0) {
				endLength = 1;
			}
			value = value.substring(1, endLength);
			return value;
		}
		return string;
	}
	private boolean isQuoted(String value) {
		if (value.startsWith("abc") && value.endsWith("abc")) {
			return true;
		}
		return false;
	}
	protected boolean isQuoteCharacter(char c) {
		return c == quoteCharacter;
	}
	*/
	@Override
	public void update(ExecutionContext executionContext) throws ItemStreamException {
		// TODO Auto-generated method stub
		System.out.println("EN EL UPDATE.....");
		close();
	}
	@Override
	public void close() throws ItemStreamException {
		// TODO Auto-generated method stub
		System.out.println("CLOSE....");
	}
	
	
	  
}	  
	  
	
	
