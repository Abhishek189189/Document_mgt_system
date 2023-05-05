package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties.Tomcat.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.io.*;
import com.example.demo.Model.MyRequest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

//@RestController
//@RequestMapping("user")
//@CrossOrigin

@RestController
@RequestMapping("file")
@CrossOrigin("*")
public class MyController {

	@Autowired
	FileRepository file_db;

	@Autowired
	FileRepository filerepository;

	@Autowired
	File_DB_Repository file_db_repository;

	@GetMapping("list")
	public List<File_db> list() {
		return file_db_repository.getAllDocuments();
	}

	@PostMapping("add")
	public File_details add(@RequestBody File_details file) {
		filerepository.add(file);

		return file;
	}

	@PostMapping("upload")
	public File_details handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
		// Get the file name
		String fileName = file.getOriginalFilename();

		// Save the file
		File newFile = new File("C:\\Users\\AbhishekKadiya\\Desktop\\File_upload\\" + fileName);
		file.transferTo(newFile);

		String path = newFile.getAbsolutePath();

		Double size = (double) (file.getSize());
		

		String type = file.getContentType();

		File_details response = new File_details(fileName, newFile.getAbsolutePath(), size, type);
		filerepository.add(response);
		System.out.println("File Sucessfully uploaded:"+fileName);

		// Return a success message
		return response;
	}



//	@GetMapping("delete")
//	public String delete(@RequestParam String id) {
//
//	    String filePath = "C:\\Users\\AbhishekKadiya\\Desktop\\File_upload\\" + id;
//	    
//	  
//	   
//	
//	    System.out.println(id);
//	   
//	
//		file_db_repository.delete(id);
//		filerepository.list();
//
//		return "Deleted Sucessfully";
////		return file_db_repository.delete();
//	}
	@GetMapping("delete")
	public ResponseEntity<String> delete(@RequestParam String id) {
	    String filePath = "C:\\Users\\AbhishekKadiya\\Desktop\\File_upload\\" + id;
	    System.out.println("Document Deleted:"+id);
	    System.out.println(id);

	    file_db_repository.delete(id);
	    filerepository.list();

	    return ResponseEntity.ok("Deleted Successfully");
	}

	
//	@GetMapping("deletefile")
//	public String handleFileDownload(@RequestParam String name) throws IOException {
//	    // Get the file name
//	    
//	    String filePath = "C:\\Users\\AbhishekKadiya\\Desktop\\File_upload\\"+name;
//	    File file = new File(filePath);
//	    
//	    
//	    System.out.println(name);
//
//	    if (file.delete()) {
//	        System.out.println("File deleted successfully");
//	    } else {
//	        System.out.println("Failed to delete the file");
//	    }
//	    
//	   
//
//
//	    return "File Deleted Sucessfully from path";
//	}
	
	@GetMapping("deletefile")
	public ResponseEntity<String> handleFileDownload(@RequestParam String name) throws IOException {
	    String filePath = "C:\\Users\\AbhishekKadiya\\Desktop\\File_upload\\"+name;
	    File file = new File(filePath);
		filerepository.list();

	    if (file.delete()) {
	    	System.out.println("File Sucessfully Deleted:"+name);
	        return ResponseEntity.ok("File deleted successfully");
	    } else {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
	    }
	}

	
	@GetMapping("download/{id}")
	public ResponseEntity<ByteArrayResource> downloadFileById(@PathVariable("id") String id) throws IOException {
	    // retrieve file information from database or other data source
	    // ...

	    // construct file path
	    String filePath = "C:\\Users\\AbhishekKadiya\\Desktop\\File_upload\\" + id;
	    
	    // check if file exists
	    
	    
	    
	    File file = new File(filePath);
	    if (!file.exists()) {
	        throw new FileNotFoundException("File not found: " + id);
	    }
	    System.out.println("File is being downloaded...");
	    // read file contents into byte array
	    Path path = Paths.get(file.getAbsolutePath());
	    ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

	    // set response headers
	    HttpHeaders headers = new HttpHeaders();
	    headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName());

	    // create response entity with file contents as body
	    return ResponseEntity.ok()
	            .headers(headers)
	            .contentLength(file.length())
	            .contentType(MediaType.APPLICATION_OCTET_STREAM)
	            .body(resource);
	}



	

}
