package com.javacodegeeks.example.util;

import java.io.FileWriter;
import java.util.Random;

public class GenerateData {
	private final int SIZE = 1500;
	private FileWriter writer = null;
	private String delimiter = ",";

	public String[] generateRandomData(String prefix, int size){
		String[] randomData = new String[size];
		Random randomNumber = new Random();
		for(int i=0;i< size; i++){
			randomData[i] = prefix + Integer.toString(randomNumber.nextInt(size+1));
		}
		return randomData;
	}
	
	public void getSampleData(String fileName){
		System.out.println("Generating sample data");
		String[] firstName = generateRandomData("Fname: ", SIZE);
		String[] lastName = generateRandomData("Lname: ", SIZE);
		String[] city = generateRandomData("City: ", SIZE);
		String[] id = generateRandomData("ID: ", SIZE);		
		
		try{
			writer = new FileWriter(fileName);
			writer.append("FirstName");writer.append(delimiter);
			writer.append("LastName");writer.append(delimiter);
			writer.append("CITY");writer.append(delimiter);
			writer.append("ID");
			writer.append("\n");
			
			for(int i=0; i< SIZE; i++){
				writer.append(firstName[i]);
				writer.append(delimiter);
				writer.append(lastName[i]);
				writer.append(delimiter);
				writer.append(city[i]);
				writer.append(delimiter);
				writer.append(id[i]);
				writer.append("\n");
		}
		}catch(Exception e){
			System.out.println("Exception while appending to the file.");
			e.printStackTrace();
		} finally{
			try{
				writer.flush();
				writer.close();	
				System.out.println("Input data is ready");
			}catch(Exception ex){
				System.out.println("Exception while closing the writer.");
				ex.printStackTrace();
			}
		}
	}
}
