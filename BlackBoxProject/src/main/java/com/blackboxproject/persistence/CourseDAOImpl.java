package com.blackboxproject.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.blackboxproject.domain.CourseVO;

@Repository
public class CourseDAOImpl implements CourseDAO {

	@Inject
	private SqlSession session;

	private static final String namespace = "com.blackboxproject.mapper.CourseMapper";

	@Override
	public void registCourse(CourseVO vo) throws Exception {
		session.insert(namespace + ".registAllCourse", vo);
	}

	@Override
	public CourseVO getCourseInfo(int courseId) throws Exception {
		CourseVO vo = session.selectOne(namespace + ".getCourseInfo", courseId);
		return vo;
	}

	@Override
	public int getCourseId(String courseCode, String courseClass) {
		Map<String, String> paramMap = new HashMap<String, String>();

		paramMap.put("courseCode", courseCode);
		paramMap.put("courseClass", courseClass);

		return session.selectOne(namespace + ".getCourseId", paramMap);
	}

	@Override
	public void userCourse(String userId, int courseId) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();

		paramMap.put("userId", userId);
		paramMap.put("courseId", courseId);

		session.insert(namespace + ".userToCourse", paramMap);
	}

	@Override
	public List<CourseVO> getUserCourseId(String userId) throws Exception {
		// TODO Auto-generated method stub
		List<CourseVO> vo = session.selectList(namespace + ".getUserCourseId", userId);
		return vo;
	}

	@Override
	public CourseVO getCourseInfoByCodeAndDate(String code, Integer classNum, String semester) {
		Map<String, Object> paramMap = new HashMap<String, Object>();

		paramMap.put("courseCode", code);
		paramMap.put("courseClass", classNum);
		paramMap.put("courseSemester", semester);
		
		return session.selectOne(namespace+".getCourseInfoByCodeAndDate",
				paramMap);
	}
}
