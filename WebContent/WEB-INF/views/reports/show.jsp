<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${report != null}">
                <h2>日報 詳細ページ</h2>
                <table>
                    <tbody>
                        <tr>
                            <th>氏名</th>
                            <td><c:out value="${report.employee.name}" /></td>
                        </tr>
                        <tr>
                            <th>日付</th>
                            <td><fmt:formatDate value="${report.report_date}"
                                    pattern="yyyy-MM-dd" /></td>
                        </tr>
                        <tr>
                            <th>内容</th>
                            <td><pre>
                                    <c:out value="${report.content}" />
                                </pre></td>
                        </tr>
                        <tr>
                            <th>登録日時</th>
                            <td><fmt:formatDate value="${report.created_at}"
                                    pattern="yyyy-MM-dd HH:mm:ss" /></td>
                        </tr>
                        <tr>
                            <th>出勤時間</th>
                            <td><c:out value="${report.attendance_at}" /></td>
                        </tr>
                        <tr>
                            <th>退勤時間</th>
                            <td><c:out value="${report.leave_work_at}" /></td>
                        </tr>
                        <tr>
                            <th>更新日時</th>
                            <td><fmt:formatDate value="${report.updated_at}"
                                    pattern="yyyy-MM-dd HH:mm:ss" /></td>
                        </tr>
                    </tbody>
                </table>
                <c:choose>
                    <c:when
                        test="${sessionScope.login_employee.id == report.employee.id}">
                        <p>
                            <a href="<c:url value="/reports/edit?id=${report.id}"/>">この日報を編集する</a>
                        </p>
                        <p>いいね：${like_count}</p>
                    </c:when>
                    <c:otherwise>
                        <c:choose>
                            <c:when test="${ like_or == 0 }">
                                <form name="like" method="POST"
                                    action="<c:url value='/like_add'/>">
                                    <input type="button" class="add_like" value="いいね"
                                        onclick="return mySubmit('like', '<c:url value='/like_add'/>', 'POST');" />
                                    <input type="hidden" name="_token" value="${_token}" /> <input
                                        type="hidden" name="id" value="${report.id}" />
                                </form>
                                <script type="text/javascript">
                            function mySubmit(formName, url, method) {
                                // サブミットするフォームを取得
                                var f = document.forms[formName];

                                f.method = method; // method(GET or POST)を設定する
                                f.action = url; // action(遷移先URL)を設定する
                                f.submit(); // submit する
                                return true;
                            }
                        </script>
                            </c:when>
                            <c:otherwise>
                                <form name="like" method="POST"
                                    action="<c:url value='/like_add'/>">
                                    <input type="button" class="off_like" value="いいね"
                                        onclick="return mySubmit('like', '<c:url value='/like_add'/>', 'POST');" />
                                    <input type="hidden" name="_token" value="${_token}" /> <input
                                        type="hidden" name="id" value="${report.id}" />
                                </form>
                                <script type="text/javascript">
                            function mySubmit(formName, url, method) {
                                // サブミットするフォームを取得
                                var f = document.forms[formName];

                                f.method = method; // method(GET or POST)を設定する
                                f.action = url; // action(遷移先URL)を設定する
                                f.submit(); // submit する
                                return true;
                            }
                        </script>
                            </c:otherwise>
                        </c:choose>
                    </c:otherwise>
                </c:choose>
            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>
        <p>
            <a href="<c:url value="/reports/index"/>">一覧に戻る</a>
        </p>
    </c:param>
</c:import>