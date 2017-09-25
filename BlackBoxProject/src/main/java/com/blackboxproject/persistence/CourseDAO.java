package com.blackboxproject.persistence;

import java.util.List;

import com.blackboxproject.domain.CourseVO;

public interface CourseDAO {

	public void registCourse(CourseVO vo) throws Exception;

	public CourseVO getCourseInfo(int courseId) throws Exception;

	public int getCourseId(String courseCode, String courseClass) throws Exception;

	public void userCourse(String userId, int courseId) throws Exception;

	public List<CourseVO> getUserCourseId(String userId) throws Exception;

	public CourseVO getCourseInfoByCodeAndDate(String code, Integer classNum, String semester);
}
