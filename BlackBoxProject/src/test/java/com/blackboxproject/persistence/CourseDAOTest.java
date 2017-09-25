package com.blackboxproject.persistence;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.blackboxproject.domain.CourseVO;
import com.blackboxproject.domain.FileVO;
import com.blackboxproject.service.CourseService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"file:src/main/webapp/WEB-INF/spring/**/*.xml"
})
public class CourseDAOTest {
	
	@Inject
	CourseDAO courseDao;
	
	@Inject
	PostDAO pd;

	
	@Inject
	CourseService courseService;
	
	@Resource(name="AutoUploadPath")
	private String uploadPath;
	
	@Test
	public void test() throws Exception {
		List<CourseVO> list = new LinkedList<>();
		
		for (int i = 0; i < 3; i++) {
			
			CourseVO vo = new CourseVO();
			vo.setCourseCode("abc" + i);
			vo.setCourseClass(1);
			vo.setCourseYear("2000" + i);
			vo.setCourseSemester("��" +i);
			vo.setCourseName("�Ϲ�ȭ��");
			vo.setCourseTime("��1��1");
			vo.setCourseDiv("aaaaa" + i);
			vo.setCourseDepartment("bbbbbbb" );
			vo.setCoursePhone("5135555");
			vo.setCourseCampus("ccccccc");
			vo.setCourseGrade("1~4");
			vo.setCourseProf("ddddddd");
			vo.setCourseClassroom("eeeeeeee");
			vo.setCourseCredit(3);
			
			list.add(vo);
		}
		
		courseService.registAllCourese(list);
	}
	
	
	@Test
	public void testName() throws Exception {
		CourseVO cvo = courseDao.getCourseInfoByCodeAndDate("CLT0590", 51, "2");
		System.out.println(cvo.toString());
	}
	
	@Test
	public void getFilePathTest() throws Exception {
		FileVO fvo = pd.getFilePathForLecture(4);
		System.out.println(fvo.toString());
		
	}
	@Test
	public void fileIOTest() throws Exception {
		File f = new File(uploadPath + "/2017/09/24/CLT0672-1");
		String[] files = f.list();
		for(String file : files ){
			System.out.println(file);
		}
				
	}

}
