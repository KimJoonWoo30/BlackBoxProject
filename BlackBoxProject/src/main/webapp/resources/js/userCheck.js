/**
 * 회원가입에 대한 전반적인 중복확인 작업
 */

// 아이디 중복확인
function chkDupId() {
	var prmId = $('#userId').val();

	// 아이디가 입력이 안되있을 경우
	if ($("#userId").val() == '') {
		alert('아이디를 입력해주세요.');
		return;
	}

	var regexp = /[0-9a-zA-Z]/;
	var idLength = prmId.length;
	for (var i = 0; i < idLength; i++) {
		if (prmId.charAt(i) != " " && regexp.test(prmId.charAt(i)) == false) {
			alert("한글이나 특수문자는 입력 불가능 합니다.");
			$("#userId").val("");
			return;
		}
		if (idLength < 4 || idLength > 20) {
			alert('아이디는 4~20자 까지 회원 가입 가능합니다.');
			$("#userId").val("");
			return;
		}
	}

	$.ajax({
		type : 'POST',
		data : "prmId=" + prmId,
		dataType : 'text',
		url : '/user/checkId',
		success : function(rData, textStatus, xhr) {
			var chkRst = rData;
			if (chkRst == 0) {
				alert("등록 가능 합니다.");
				$("#idChk").val('Y');
			} else {
				alert("중복된 아이디 입니다.");
				$("#idChk").val('N');
			}
		},
		error : function(xhr, status, e) {
			alert(e);
		}
	});
}

function chkDupNick() {

	var prmId = $('#userNick').val();

	if ($("#userNick").val() == '') {
		alert('닉네임을 입력해주세요.');
		return;
	}

	var regexp = /[0-9a-zA-Z]/;
	var idLength = prmId.length;
	for (var i = 0; i < idLength; i++) {
		if (prmId.charAt(i) != " " && regexp.test(prmId.charAt(i)) == false) {
			alert("한글이나 특수문자는 입력 불가능 합니다.");
			$("#userNick").val("");
			return;
		}
		if (idLength < 4 || idLength > 20) {
			alert('닉네임은 4~20자 까지 회원 가입 가능합니다.');
			$("#userNick").val("");
			return;
		}
	}

	$.ajax({
		type : 'POST',
		data : "prmId=" + prmId,
		dataType : 'text',
		url : '/user/checkNick',
		success : function(rData, textStatus, xhr) {
			var chkRst = rData;
			if (chkRst == 0) {
				alert("등록 가능 합니다.");
				$("#nickChk").val('Y');
			} else {
				alert("중복된 아이디 입니다.");
				$("#nickChk").val('N');
			}
		},
		error : function(xhr, status, e) {
			alert(e);
		}
	});
}

// 회원 가입 버튼
function insertChk() {
	// 중복확인 성공 했는지 여부
	if ($("#idChk").val() == 'N') {
		alert('아이디체크를 해주세요.');
		return;
	}

	if ($("#nickChk").val() == 'N') {
		alert('닉네임체크를 해주세요.');
		return;
	}

	// 비밀번호 유효성 검사
	var prmPw = $('#userPw').val();

	var regexpPw = /[0-9a-zA-Z]/;
	var pwLength = prmPw.length;
	for (var i = 0; i < pwLength; i++) {
		if (prmPw.charAt(i) != " " && regexpPw.test(prmPw.charAt(i)) == false) {
			alert("비밀번호는 한글이나 특수문자는 입력 불가능 합니다.");
			$("#userPw").val("");
			return;
		}
		if (pwLength < 4 || pwLength > 20) {
			alert('비밀번호는 4~20자 까지 회원 가입 가능합니다.');
			$("#userPw").val("");
			return;
		}
	}

	// 이름 유효성 검사
	var prmName = $('#userName').val();
	console.log(prmName);
	var regexpName = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
	var nameLength = prmName.length;
	for (var i = 0; i < nameLength; i++) {
		if (prmName.charAt(i) != " "
				&& regexpName.test(prmName.charAt(i)) == false) {
			alert("이름은 한글만 입력가능합니다.");
			$("#userName").val("");
			return;
		}
		if (nameLength < 1 || nameLength > 20) {
			alert('이름은 1~20자 까지 회원 가입 가능합니다.');
			$("#userName").val("");
			return;
		}
	}

	// 전화번호 유효성 검사
	var prmPhone = $('#userPhone').val();

	var regexpPhone = /[0-9]/;
	var phoneLength = prmPhone.length;
	for (var i = 0; i < phoneLength; i++) {
		if (prmPhone.charAt(i) != " "
				&& regexpPhone.test(prmPhone.charAt(i)) == false) {
			alert("전화번호는 숫자만 입력가능합니다.");
			$("#userPhone").val("");
			return;
		}
		if (phoneLength < 8 || phoneLength > 12) {
			alert('전화번호는  8~12자 까지 회원 가입 가능합니다.');
			$("#userPhone").val("");
			return;
		}
	}

	// 이메일 유효성 검사
	var prmEmail = $('#userEmail').val();
	var regExp = /[0-9a-zA-Z][_0-9a-zA-Z-]*@[_0-9a-zA-Z-]+(\.[_0-9a-zA-Z-]+){1,2}$/;

	// 이메일 형식에 맞지않으면

	if (!prmEmail.match(regExp)) {
		alert("이메일에 잘못된 문자열이 입력되었습니다.");
		return;
	}

	// 비밀번호 일치 여부
	if ($("#userPw").val() != $("#userPwCheck").val()) {
		alert('비밀번호가 일치하지 않습니다.');
		return;
	}

	// 빈칸 없는지 확인 여부
	if ($("#userName").val() == '' || $("#userEmail").val() == ''
			|| $("#userPhone").val() == '') {
		alert('빈칸이 있습니다.');
		return;
	}

	/*// 성별 체크됬는지 여부
	if ($(".login-page :checked").size() < 1) {
		alert("성별을 체크해주세요.");
		return;
	}*/

	document.join.submit();
}

// 회원 수정 버튼
function modifyChk() {

	// 비밀번호 일치 여부
	if ($("#userOldPw").val() != $("#userOldPwCheck").val()) {
		alert('현재 비밀번호가 일치하지 않습니다.');
		return;
	}

	// 비밀번호 일치 여부
	if ($("#userPw").val() != $("#userPwCheck").val()) {
		alert('새로운 비밀번호가 일치하지 않습니다.');
		return;
	}

	// 비밀번호 유효성 검사
	var prmPw = $('#userPw').val();

	var regexpPw = /[0-9a-zA-Z]/;
	var pwLength = prmPw.length;
	for (var i = 0; i < pwLength; i++) {
		if (prmPw.charAt(i) != " " && regexpPw.test(prmPw.charAt(i)) == false) {
			alert("비밀번호는 한글이나 특수문자는 입력 불가능 합니다.");
			$("#userPw").val("");
			return;
		}
		if (pwLength < 4 || pwLength > 20) {
			alert('비밀번호는 4~20자 까지 회원 가입 가능합니다.');
			$("#userPw").val("");
			return;
		}
	}

	// 이름 유효성 검사
	var prmName = $('#userName').val();

	var regexpName = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
	var nameLength = prmName.length;
	for (var i = 0; i < nameLength; i++) {
		if (prmName.charAt(i) != " "
				&& regexpName.test(prmName.charAt(i)) == false) {
			alert("이름은 한글만 입력가능합니다.");
			$("#userName").val("");
			return;
		}
		if (nameLength < 1 || nameLength > 20) {
			alert('이름은 1~20자 까지 회원 가입 가능합니다.');
			$("#userName").val("");
			return;
		}
	}

	// 전화번호 유효성 검사
	var prmPhone = $('#userPhone').val();

	var regexpPhone = /[0-9]/;
	var phoneLength = prmPhone.length;
	for (var i = 0; i < phoneLength; i++) {
		if (prmPhone.charAt(i) != " "
				&& regexpPhone.test(prmPhone.charAt(i)) == false) {
			alert("전화번호는 숫자만 입력가능합니다.");
			$("#userPhone").val("");
			return;
		}
		if (phoneLength < 8 || phoneLength > 12) {
			alert('전화번호는  8~12자 까지 회원 가입 가능합니다.');
			$("#userPhone").val("");
			return;
		}
	}

	// 이메일 유효성 검사
	var prmEmail = $('#userEmail').val();
	var regExp = /[0-9a-zA-Z][_0-9a-zA-Z-]*@[_0-9a-zA-Z-]+(\.[_0-9a-zA-Z-]+){1,2}$/;
	// 이메일 형식에 맞지않으면

	if (!prmEmail.match(regExp)) {
		alert("이메일에 잘못된 문자열이 입력되었습니다.");
		return;
	}

	// 빈칸 없는지 확인 여부
	if ($("#userName").val() == '' || $("#userEmail").val() == ''
			|| $("#userPhone").val() == '' || $("#userPw").val() == ''
			|| $("#userPwCheck").val() == '') {
		alert('빈칸이 있습니다.');
		return;
	}

	document.modify.submit();
}

// 회원 삭제 버튼
function deleteChk() {

	var chk = document.delUser.userDelete.checked;
	if (!chk) {
		alert("동의를 하신 경우에만 계정이 삭제됩니다.");
		return;
	}
	document.delUser.submit();
}
