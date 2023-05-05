package com.example.demo.Controller;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface file_mongo extends MongoRepository<File_details,Integer> {

}
