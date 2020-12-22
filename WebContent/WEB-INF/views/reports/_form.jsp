<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" />
            <br />
        </c:forEach>
    </div>
</c:if>
<label for="report_date">日付</label>
<br />
<input type="date" name="report_date"
    value="<fmt:formatDate value="${report.report_date}" pattern="yyyy-MM-dd"/>" />
<br />
<br />

<label for="name">氏名</label>
<br />
<c:out value="${sessionScope.login_employee.name}" />
<br />
<br />
<div class="form_container">
    <div class="form_item">
        <label for="attendance_at">出勤時間</label> <br /> <input type="time"
            name="attendance_at" value="${report.attendance_at}" /><br />
    </div>
    <div class="form_item" id="check_attendance_at">
        <p class="check">出勤時間を入力してください</p>
    </div>
</div>
<br />
<div class="form_container">
    <div class="form_item">
        <label for="leave_work_at">退勤時間</label> <br /> <input type="time"
            name="leave_work_at" value="${report.leave_work_at}" />
    </div>
    <div class="form_item" id="check_leave_work_at">
        <p class="check">退勤時間を入力してください</p>
    </div>
</div>
<br />
<br />
<div class="form_container">
    <div class="form_item">
        <label for="title">タイトル</label> <br /> <input type="text"
            name="title" value="${report.title}" />
    </div>
    <div class="form_item" id="check_title">
        <p class="check">タイトルを入力してください</p>
    </div>
</div>
<br />
<br />
<div class="form_container">
    <div class="form_item">
        <label for="content">内容</label>
        <textarea name="content" rows="10" cols="50">${report.content}</textarea>
    </div>
    <div class="form_item" id="check_content">
        <p class="check">内容を入力してください</p>
    </div>
</div>
<br />
<br />
<input type="hidden" name="_token" value="${_token}" />
<button type="submit" onclick="return check();">投稿</button>
<script>
    function check() {
        let flag = 0;
        if (form.attendance_at.value == "") {  //出勤時間の確認
            flag = 1;
            document.getElementById("check_attendance_at").style.display = "block";
        } else {
            document.getElementById("check_attendance_at").style.display = "none";
        }
        if (form.leave_work_at.value == "") {  //退勤時間の確認
            flag = 1;
            document.getElementById("check_leave_work_at").style.display = "block";
        } else {
            document.getElementById("check_leave_work_at").style.display = "none";
        }

        if (form.title.value == "") {  //タイトルの確認
            flag = 1;
            document.getElementById("check_title").style.display = "block";
        } else {
            document.getElementById("check_title").style.display = "none";
        }

        if (form.content.value == "") {  //内容の確認
            flag = 1;
            document.getElementById("check_content").style.display = "block";
        } else {
            document.getElementById("check_content").style.display = "none";
        }

        if (flag == 1) {
           // alert("入力内容を確認してください");
            return false;
        } else {
            if (confirm("この内容で投稿しますか？")) {
                document.form[0].submit();
            } else {
                return false;
            }
        }
    }
</script>