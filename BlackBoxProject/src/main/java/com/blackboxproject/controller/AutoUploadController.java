package com.blackboxproject.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.blackboxproject.domain.CourseVO;
import com.blackboxproject.domain.FileVO;
import com.blackboxproject.domain.PostVO;
import com.blackboxproject.service.AutoUploadService;
import com.blackboxproject.service.CourseService;
import com.blackboxproject.service.PostService;

import util.MediaUtils;
import util.MultipartFileSender;
import util.UploadUtils;

@Controller
public class AutoUploadController {

   private static final Logger logger = LoggerFactory.getLogger(AutoUploadController.class);

   @Inject
   private AutoUploadService service;

   @Inject
   private PostService ps;

   @Inject
   private CourseService cs;

   @Resource(name="AutoUploadPath")
   private String uploadPath;




   @RequestMapping(value = "/find", method = RequestMethod.GET)
   public void findCoureseCode() throws Exception {
      logger.info("find");
   }

   @ResponseBody
   @RequestMapping(value = "/find", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
   public ResponseEntity<String> findCoureseCode(String fileName) throws Exception {

      System.out.println(fileName);
      // fileName = "2017_09_21_10.30.12_공7_218";
      // 
      int year = Integer.parseInt(fileName.substring(0, 4)); // 년
      int month = Integer.parseInt(fileName.substring(5, 7)); // 월
      int day = Integer.parseInt(fileName.substring(8, 10)); // 일
      String days[] = { "토", "일", "월", "화", "수", "목", "금" };

      // 요일 확인하기, 젤러의 공식 사용
      String courseDay;
      if (month == 1) {
         month = 13;
         year = year - 1;
      } else if (month == 2) {
         month = 14;
         year = year - 1;
      }

      int j = year / 100;
      int k = year % 100;

      int h = (day + ((26 * (month + 1)) / 10) + k + (k / 4) + j / 4 + (5 * j)) % 7;

      courseDay = days[h];

      // 몇 교시 인지 확인하기
      int hour = Integer.parseInt(fileName.substring(11, 13));
      int minute = Integer.parseInt(fileName.substring(14, 16));
      int time = 0;
      int allMinute = hour * 60 + minute - 60 * 9; // 현재 시간을 분으로 환산 후, 9시를 기준으로 시간 시작
      int period; // 몇 교시

      if (h % 2 == 1) {
         time = 90; // 화목 수업의 경우에는 1시간30분 잡기(원래 수업시간은 1시간 15분이나 대부분 끝나고 누르므로 쉬는시간 포함)
      } else if (h % 2 == 0) {
         time = 60; // 월수 수업의 경우에는 1시간
      }

      period = (allMinute / time) + 1;

      String courseTime = courseDay + period; // 수3

      // 건물 위치 및 강의실 확인
      // String building = fileName.substring(20, fileName.lastIndexOf("_"));
      String building = "공7";
      String roomNumber = fileName.substring(fileName.lastIndexOf("_") + 1);
      // 공7-218, 인문대303
      System.out.println(courseTime + " " + building + "-" + roomNumber);
      CourseVO vo;
      String courseClassroom;

      // 1차로 하이픈 붙이고 검색
      courseClassroom = building + "-" + roomNumber;
      vo = service.getFileName(courseTime, courseClassroom);

      // 없을 시에 하이픈 제거하고 검색하기
      if (vo == null) {
         
         
         courseClassroom = building + roomNumber;
         vo = service.getFileName(courseTime, courseClassroom);
      }

      String result = vo.getCourseCode() + "-" + vo.getCourseClass();
      System.out.println(result); // 예를 들어, CLT0573-1 

      return new ResponseEntity<String>(result, HttpStatus.CREATED);
   }

   @ResponseBody
   @RequestMapping("/displayImage")
   public ResponseEntity<byte[]>  displayFile(String fileName)throws Exception{

      InputStream in = null; 
      ResponseEntity<byte[]> entity = null;

      logger.info("FILE NAME: " + fileName);

      try{

         String formatName = fileName.substring(fileName.lastIndexOf(".")+1);

         MediaType mType = MediaUtils.getMediaType(formatName);

         HttpHeaders headers = new HttpHeaders();
         in = new FileInputStream(uploadPath+fileName);
         
         if(in == null)
            System.out.println(uploadPath+fileName);

         if(mType != null){
            headers.setContentType(mType);
         }else{

            fileName = fileName.substring(fileName.indexOf("_")+1);       
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.add("Content-Disposition", "attachment; filename=\""+ 
                  new String(fileName.getBytes("UTF-8"), "ISO-8859-1")+"\"");
         }
         
         entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), 
               headers, 
               HttpStatus.CREATED);
      }catch(Exception e){
         e.printStackTrace();
         entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
      }finally{
         in.close();
      }
      return entity;    
   }

   
   @ResponseBody
   @RequestMapping("/displayAudio")
   public void  displayAudio(String fileName,
         HttpServletRequest req, HttpServletResponse res)throws Exception{
      
      File getFile = new File(uploadPath + fileName);
      
      try {
         MultipartFileSender.fromFile(getFile)
            .with(req)
            .with(res)
            .serveResource();
      } catch (Exception e) {
         if (!e.getClass().getName().equals("org.apache.catalina.connector.ClientAbortException")) e.printStackTrace();
      }
      
      
   }

   @ResponseBody
   @RequestMapping(value="/autoUpload", method=RequestMethod.POST,
   produces = "text/plain;charset=UTF-8")
   public ResponseEntity<String> autoUpload(MultipartFile file) throws Exception{

      logger.info("originalFileName : " + file.getOriginalFilename());
      return new ResponseEntity<String>(UploadUtils.uploadCourseFile(uploadPath, file.getOriginalFilename(), file.getBytes())
            , HttpStatus.CREATED);
   }

   @ResponseBody
   @RequestMapping(value="/finishUpload", method=RequestMethod.POST)
   public ResponseEntity<String> finishUpload(String end) throws Exception{

      System.out.println(end);

      String courseName = end.substring(1);

      String[] course = courseName.split("-");

      Calendar cal = Calendar.getInstance();
      String year = cal.get(Calendar.YEAR) + "";
      String semester = "";
      int month = cal.get(Calendar.MONTH) + 1;
      if(month >= 3 && month <= 6)
         semester = "1";
      else if (month>=9 && month <= 12)
         semester = "2";
      else if (month == 7 || month == 8)
         semester = "하";
      else 
         semester = "동";

      CourseVO cvo = cs.getCourseInfoByCodeAndDate(course[0],
            new Integer(course[1]), semester);

      // 게시글 작성
      PostVO pvo = new PostVO();
      pvo.setCourseId(cvo.getCourseId());
      pvo.setBoardId(2);
      pvo.setPostTitle(cvo.getCourseName());
      pvo.setPostContent(cvo.getCourseCode()+cvo.getCourseClass());
      pvo.setUserNick("admin");


      String dirPath = UploadUtils.calcPath(uploadPath);
      // file 경로 정보 DB에 추가
      List<FileVO> list = new LinkedList<>();
      FileVO f = new FileVO();
      String filePath = dirPath + File.separator + courseName;
      filePath = filePath.replace(File.separator, "/");

      f.setFileName(filePath);
      f.setFileOriginname(filePath);
      f.setUserNick("admin");
      list.add(f);
      pvo.setFiles(list);

      ps.createPost(pvo);
      return new ResponseEntity<String>("SUCCESS",HttpStatus.OK);


   }
}