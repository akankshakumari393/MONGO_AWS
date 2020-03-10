package com.gslab.mongo5.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gslab.mongo5.model.Holiday;
import com.gslab.mongo5.repository.HolidayRepository;

@Service
public class HolidayService {

	@Autowired
	HolidayRepository holidayRepository;

	public HolidayService(HolidayRepository holidayRepository) {
		this.holidayRepository = holidayRepository;
	}

	public List<Holiday> getAllHoliday() {
		return (List<Holiday>) holidayRepository.findAll();
	}

	public Holiday addHoliday(Holiday holiday) {
		return holidayRepository.save(holiday);
	}

	public List<Holiday> getHolidaysByYear(String year) {
		// TODO Auto-generated method stub
		List<Holiday> holiday = holidayRepository.findByDateLike("*-" + year);
		return holiday;
	}

	public List<Holiday> getHolidaysByYearMonth(String year, String mM) {
		// TODO Auto-generated method stub
		List<Holiday> holiday = holidayRepository.findByDateLike("*-" + mM + "-" + year);
		return holiday;
	}

	public void deleteHoliday(String date) {
		// TODO Auto-generated method stub
		holidayRepository.deleteByDate(date);
	}

}
