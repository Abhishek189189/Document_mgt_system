package com.example.demo.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class FileRepository {
	
	@Autowired
	file_mongo file_db;
	
	
	
	@Autowired
	File_details file_detail;
	
	private List<File_details> fileList = null;

	public FileRepository() {
		super();
		this.fileList = new ArrayList<>();
	}
	
	public List<File_details> list(){
		return fileList; 
	}
	
	public void add(File_details file) {
		fileList.add(file);
		file_db.save(file);
		

	}
	
	
	public void db_add(File_details file)
	{
		file_db.save(file);
	}
	
	


}
