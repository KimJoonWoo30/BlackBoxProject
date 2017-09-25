package com.blackboxproject.persistence;

import java.util.List;

import com.blackboxproject.domain.FileVO;
import com.blackboxproject.domain.PostVO;


public interface PostDAO {
	
	public List<PostVO> getPosts(int courseId, int boardId) throws Exception;

	public void createPost(PostVO vo) throws Exception;
	
	public void addFile(FileVO fvo) throws Exception;

	public PostVO getPostByPostId(int postId) throws Exception;

	public List<FileVO> getFilesByPostId(int postId) throws Exception;

	public void updatePostView(int postId);

	public void modifyPost(PostVO pvo);


	public FileVO getImageDirectory(int postId);

	public List<PostVO> getLectureList(int courseId);

	public FileVO getFilePathForLecture(int postId) throws Exception;
	
	public void updateReplyCnt(Integer postId, int amount) throws Exception;

}
