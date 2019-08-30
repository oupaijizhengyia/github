package com.tangu.tanguStudy.configurationProperties;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tangu.tanguStudy.configurationProperties.StudyPeoperties;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AnotationReadPeopertiesTests {
	
	@Value("${com.tangu.study.str}")
	private String str;
	
	@Value("${com.tangu.study.integer}")
	private int i1;
	
	@Value("${com.tangu.study.integer}")
	private Integer i2;
	
	@Autowired
	private StudyPeoperties studyPeoperties;
	
	@Test
	public void valueTest() {
		System.out.println(str);
		assert str != null;
		System.out.println(i1);
		assert i1 != 0;
		System.out.println(i2);
		assert i2 != null;
	}
	
	@Test
	public void studyPeopertiesTest() {
		System.out.println(studyPeoperties.getStr());
		assert studyPeoperties.getStr() != null;
		System.out.println(studyPeoperties.getInteger());
		assert studyPeoperties.getInteger() != 0;
	}
}
