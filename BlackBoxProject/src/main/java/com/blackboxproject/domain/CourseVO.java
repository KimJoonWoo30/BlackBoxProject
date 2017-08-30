package com.blackboxproject.domain;
import java.sql.Timestamp;

// �������б� 
public class CourseVO {

    // ������ ��ȣ 
    private Integer courseId;

    // ������ �ڵ� 
    private String courseCode;


	// �й� 
    private Integer courseClass;

    // ���ǳ⵵  x
    private String courseYear;

    // �����б� x 
    private String courseSemester;

    // ������ �̸� 
    private String courseName;

    // ������ �ð� 
    private String courseTime;

    // �������� 
    private String courseDiv;

    // �ְ��а� 
    private String courseDepartment;

    // �а��繫�ǹ�ȣ 
    private String coursePhone;

    // ������� 
    private String courseCampus;

    // �г� 
    private String courseGrade;

    // ��米�� 
    private String courseProf;

    // ���ǽ� 
    private String courseClassroom;

    // ���� 
    private Integer courseCredit;

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public Integer getCourseClass() {
        return courseClass;
    }

    public void setCourseClass(Integer courseClass) {
        this.courseClass = courseClass;
    }

    public String getCourseSemester() {
        return courseSemester;
    }

    public void setCourseSemester(String courseSemester) {
        this.courseSemester = courseSemester;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(String courseTime) {
        this.courseTime = courseTime;
    }

    public String getCourseDiv() {
        return courseDiv;
    }

    public void setCourseDiv(String courseDiv) {
        this.courseDiv = courseDiv;
    }

    public String getCourseDepartment() {
        return courseDepartment;
    }

    public void setCourseDepartment(String courseDepartment) {
        this.courseDepartment = courseDepartment;
    }

    public String getCoursePhone() {
        return coursePhone;
    }

    public void setCoursePhone(String coursePhone) {
        this.coursePhone = coursePhone;
    }

    public String getCourseCampus() {
        return courseCampus;
    }

    public void setCourseCampus(String courseCampus) {
        this.courseCampus = courseCampus;
    }


    public String getCourseProf() {
        return courseProf;
    }

    public void setCourseProf(String courseProf) {
        this.courseProf = courseProf;
    }

    public String getCourseClassroom() {
        return courseClassroom;
    }

    public void setCourseClassroom(String courseClassroom) {
        this.courseClassroom = courseClassroom;
    }

    public Integer getCourseCredit() {
        return courseCredit;
    }

    public void setCourseCredit(Integer courseCredit) {
        this.courseCredit = courseCredit;
    }

	public String getCourseGrade() {
		return courseGrade;
	}

	public void setCourseGrade(String courseGrade) {
		this.courseGrade = courseGrade;
	}
    
    // JnuCourse �� ����
    public void CopyData(CourseVO param)
    {
        this.courseId = param.getCourseId();
        this.courseCode = param.getCourseCode();
        this.courseClass = param.getCourseClass();
        this.courseYear = param.getCourseYear();
        this.courseSemester = param.getCourseSemester();
        this.courseName = param.getCourseName();
        this.courseTime = param.getCourseTime();
        this.courseDiv = param.getCourseDiv();
        this.courseDepartment = param.getCourseDepartment();
        this.coursePhone = param.getCoursePhone();
        this.courseCampus = param.getCourseCampus();
        this.courseGrade = param.getCourseGrade();
        this.courseProf = param.getCourseProf();
        this.courseClassroom = param.getCourseClassroom();
        this.courseCredit = param.getCourseCredit();
    }

	
    public String getCourseYear() {
		return courseYear;
	}

	public void setCourseYear(String courseYear) {
		this.courseYear = courseYear;
	}

	@Override
	public String toString() {
		return "CourseVO [courseId=" + courseId + ", courseCode=" + courseCode + ", courseClass=" + courseClass
				+ ", courseYear=" + courseYear + ", courseSemester=" + courseSemester + ", courseName=" + courseName
				+ ", courseTime=" + courseTime + ", courseDiv=" + courseDiv + ", courseDepartment=" + courseDepartment
				+ ", coursePhone=" + coursePhone + ", courseCampus=" + courseCampus + ", courseGrade=" + courseGrade
				+ ", courseProf=" + courseProf + ", courseClassroom=" + courseClassroom + ", courseCredit="
				+ courseCredit + "]";
	}

}