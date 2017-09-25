package com.blackboxproject.service;

import java.util.List;

import com.blackboxproject.domain.CourseVO;

public interface CourseService {

	public void registAllCourese(List<CourseVO> list) throws Exception;

	public CourseVO getCourseInfo(int courseId) throws Exception;

	// 준우, 교과목 코드랑 분반가지고 교과목 번호 가져오는 단계
	public int getCourseId(String courseCode, String courseClass) throws Exception;

	// 준우, jnu_user_course테이블에 아이디와 교과목 번호 삽입하는 과정
	public void userToCourse(String userId, int courseId) throws Exception;

	// 준우, jnu_user_course테이블에서 사용자에 해당하는 교과목 번호를 가져오는 과정
	public List<CourseVO> getUserCourseId(String userId) throws Exception;

	public CourseVO getCourseInfoByCodeAndDate(String string, Integer integer, String semester);

}
