package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;




@Component
public class File_DB_Repository {

	
	@Autowired
	File_db_mongo file_db;

	
	
	
	  public List<File_db> getAllDocuments() {
	        return file_db.findAll();
	    }




	public void delete(String id) {
		
		
		file_db.deleteById(id);
		
	}




}
