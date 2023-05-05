package com.example.demo.Controller;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Component
@Document(collection="doc_details")

public class File_details {
//	public Integer id;
	public String name;
	public String path;
	public Double size;
	public String filetype;
	
	public File_details()
	{
		
	}
	public File_details(String name, String path) {
		this.name = name;
		this.path = path;
		
}
	public File_details(Integer id, String name, String path, Double size) {
		super();
//		id++;
//		this.id = id;
		this.name = name;
		this.path = path;
		this.size = size;
	}
	
	
	public File_details(String name, String path,Double size,String filetype) {
		this.name = name;
		this.path = path;
		this.size = size;
		this.filetype = filetype;
}
	
	 
	
	

	


	

	
	
	
	
	
	
}
