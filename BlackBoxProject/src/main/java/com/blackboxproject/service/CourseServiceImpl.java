package com.blackboxproject.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.blackboxproject.domain.CourseVO;
import com.blackboxproject.persistence.CourseDAO;

@Service
public class CourseServiceImpl implements CourseService {

	@Inject
	CourseDAO coursedao;

	@Override
	public void registAllCourese(List<CourseVO> list) throws Exception {

		for (CourseVO vo : list) {
			coursedao.registCourse(vo);
		}
	}

	@Override
	public CourseVO getCourseInfo(int courseId) throws Exception {
		CourseVO vo = coursedao.getCourseInfo(courseId);
		return vo;
	}

	public int getCourseId(String courseCode, String courseClass) throws Exception {
		return coursedao.getCourseId(courseCode, courseClass);
	}

	@Override
	public void userToCourse(String userId, int courseId) throws Exception {
		coursedao.userCourse(userId, courseId);
	}

	@Override
	public List<CourseVO> getUserCourseId(String userId) throws Exception {
		// TODO Auto-generated method stub
		return coursedao.getUserCourseId(userId);
	}

	@Override
	public CourseVO getCourseInfoByCodeAndDate(String code, Integer classNum, String semester) {
		return coursedao.getCourseInfoByCodeAndDate(code, classNum, semester);
	}

}
