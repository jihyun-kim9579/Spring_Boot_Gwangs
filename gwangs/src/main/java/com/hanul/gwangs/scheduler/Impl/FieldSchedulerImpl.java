package com.hanul.gwangs.scheduler.Impl;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hanul.gwangs.dto.FieldDTO;
import com.hanul.gwangs.scheduler.IFieldScheduler;
import com.hanul.gwangs.service.IFieldService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
@RequiredArgsConstructor
public class FieldSchedulerImpl implements IFieldScheduler{
	
	private final IFieldService fieldService;
	
	
	
	@Override
	@Transactional
	@Scheduled(cron = "0 0 0 * * ?")
	public void register30NextDays() {
		LocalDate Next30Day = LocalDate.now().plusDays(30);
		
		List<Integer> fieldNumbers = Arrays.asList(1 , 2 , 3 , 4);
		List<String> fieldTimes = Arrays.asList("19시 ~ 21시" , "21시 ~ 23시" , "23시 ~ 01시");
		
		for (Integer filedNumber : fieldNumbers) {
			for (String fieldTime : fieldTimes) {
				FieldDTO fieldDTO = FieldDTO.builder()
					.fieldName("상무 챔피언스 필드")
					.fieldNumber(filedNumber)
					.fieldDate(Next30Day)
					.fieldTime(fieldTime)
					.fieldAddr("서구 유덕로 148")
					.fieldCost("120,000")
					.build();
				fieldService.fieldRegister(fieldDTO);
			}
			
		}
		
//  다른 구장 필요시 위에까지 복사해서 붙여넣기 
		
		for (Integer filedNumber : fieldNumbers) {
			for (String fieldTime : fieldTimes) {
				FieldDTO fieldDTO = FieldDTO.builder()
					.fieldName("신화 풋살장")
					.fieldNumber(filedNumber)
					.fieldDate(Next30Day)
					.fieldTime(fieldTime)
					.fieldAddr("광산구 왕버들로 33")
					.fieldCost("120,000")
					.build();
				fieldService.fieldRegister(fieldDTO);
			}
			
		}
		
	}
	
}
