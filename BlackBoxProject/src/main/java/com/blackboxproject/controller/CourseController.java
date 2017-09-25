package com.blackboxproject.controller;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.blackboxproject.domain.CourseVO;
import com.blackboxproject.domain.FileVO;
import com.blackboxproject.domain.PostVO;
import com.blackboxproject.service.CourseService;
import com.blackboxproject.service.PostService;

@Controller
public class CourseController {
	
	@Inject
	CourseService cs;
	
	@Inject
	PostService ps;
	
	@Resource(name="AutoUploadPath")
	private String uploadPath;
	
	// 나의 강의목록 페이지
//	@RequestMapping("/courses")
//	public void listCourses(){
//		
//		// 1. 권한인증하기
//		
//		// 2. 
//		
//	}
	
	
	
	// 강의페이지
	@RequestMapping(value="/courses/{courseId}", method=RequestMethod.GET)
	public String coursePage(@PathVariable("courseId")int courseId, Model model) throws Exception{
		
		// 1. 권한 인증
		
		// 2. 강의 정보 가져오기
		CourseVO vo = cs.getCourseInfo(courseId);
		model.addAttribute("course", vo);
		System.out.println("coursePage : " + vo);
		
		
		// 3. 강의회차 정보 가져와서 전달
		List<PostVO> lectures = ps.getLectureList(courseId);
		model.addAttribute("lectures", lectures);
		
		return "/coursePage";
	}
	
	// 강의 시청 페이지
	@RequestMapping(value="/courses/{courseId}/lectures/{postId}", method=RequestMethod.GET)
	public String lecturePage(@PathVariable("courseId")int courseId,@PathVariable("postId")int postId, Model model) throws Exception{
		
		// 1. 권한 인증
		
		// 2. 강좌 시청 화면, 강의 이미지가 있는 경로
		FileVO fvo = ps.getFilePathForLecture(postId);
		String dirPath = fvo.getFileName();
		
		// 3. 이미지 가져오기
		File fullPath = new File(uploadPath + dirPath);
		String filenames[] = fullPath.list();
		
		List<String> fileList = new LinkedList<>();
		List<String> audioList = new LinkedList<>();
		List<String> thumbnailList = new LinkedList<>();
		for(String name : filenames){
			String formatName = name.substring(name.lastIndexOf(".")+1);
			if("wav".equals(formatName))
				audioList.add("/displayAudio/?fileName=" + dirPath + "/" + name);
			else if(name.startsWith("s_"))
				thumbnailList.add("/displayImage/?fileName=" + dirPath + "/" + name);
			else
				fileList.add("/displayImage/?fileName=" + dirPath + "/" + name);
		}
		model.addAttribute("images", fileList);
		model.addAttribute("audios", audioList);
		model.addAttribute("thumbnails", thumbnailList);
		
		// 4. 음성 가져오기
		model.addAttribute("postId", postId);
		
		return "/lecturePage";
	}
}
