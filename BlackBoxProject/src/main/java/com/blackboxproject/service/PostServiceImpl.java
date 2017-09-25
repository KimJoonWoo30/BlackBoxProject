package com.blackboxproject.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blackboxproject.domain.FileVO;
import com.blackboxproject.domain.PostVO;
import com.blackboxproject.persistence.PostDAO;

@Service
public class PostServiceImpl implements PostService {

	@Inject
	PostDAO postDao;
	
	@Override
	public List<PostVO> getPosts(int courseId, int boardId) throws Exception{
		return postDao.getPosts(courseId, boardId);
	}

	@Override
	@Transactional
	public void createPost(PostVO vo) throws Exception{
		// 1. 글작성
		postDao.createPost(vo);
		
		// 2. 파일첨부, createPost에서 postId값을 받아온다. mapper 참조
		List<FileVO> files = vo.getFiles();
		for(FileVO fvo : files){
			fvo.setPostId(vo.getPostId());
			postDao.addFile(fvo);
			
		}
		
		// 3. 트랜잭션 처리/  가 안되는 것같다.
	}

	@Override
	@Transactional
	public PostVO getPostByPostId(int postId) throws Exception {

		
		// 조회수 올리기
		postDao.updatePostView(postId);
		//트랜잭션 처리
		
		return postDao.getPostByPostId(postId);
	}

	@Override
	public List<FileVO> getFilesByPostId(int postId) throws Exception {
		return postDao.getFilesByPostId(postId);
	}

	@Override
	@Transactional
	public void modifyPost(PostVO pvo) throws Exception {
		
		// 1. 컨텐트, 타이틀 업데이트
		postDao.modifyPost(pvo);
		
		// 2. 파일첨부, createPost에서 postId값을 받아온다. mapper 참조
		List<FileVO> files = pvo.getFiles();
		for(FileVO fvo : files){
			fvo.setPostId(pvo.getPostId());
			postDao.addFile(fvo);
		}
	}

	@Override
	public PostVO getLecturePostById(int postId) throws Exception {
		
		// 1. 강의정보 가져오기
		PostVO pvo = postDao.getPostByPostId(postId);
		
		// 2. 이미지 정보 가져오기
		FileVO fvo = postDao.getImageDirectory(postId);
		
		// 3. 음성정보 가져오기
		
		return null;
	}

	@Override
	public List<PostVO> getLectureList(int courseId) {
		return postDao.getLectureList(courseId);
	}

	@Override
	public FileVO getFilePathForLecture(int postId) throws Exception {
		return postDao.getFilePathForLecture(postId);
	}

}
