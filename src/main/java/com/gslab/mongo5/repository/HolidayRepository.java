package com.gslab.mongo5.repository;

import java.util.List;

//import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gslab.mongo5.model.Holiday;

@Repository
public interface HolidayRepository extends MongoRepository<Holiday, String> {

	//public Optional<Holiday> findFirstByYear(String year);

	public void deleteByDate(String date);
	
	public List<Holiday> findByDateLike(String regex);
   
	
}
