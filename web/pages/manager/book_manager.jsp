<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>图书管理</title>
    <%--  静态包含 base 标签  ，css 样式  ，jQuery文件--%>
    <%@include file="/pages/common/head.jsp" %>


</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">图书管理系统</span>
    <%--静态代码包含 manager管理模块的菜单 --%>
    <%@include file="/pages/common/mamager_menu.jsp" %>
</div>

<div id="main">
    <table>
        <tr>
            <td>名称</td>
            <td>价格</td>
            <td>作者</td>
            <td>销量</td>
            <td>库存</td>
            <td colspan="2">操作</td>
        </tr>
        <c:forEach items="${requestScope.bookPage.items}" var="book">
            <tr>
                <td>${book.name}</td>
                <td>${book.price}</td>
                <td>${book.author}</td>
                <td>${book.sales}</td>
                <td>${book.stock}</td>
                <td><a class="updateClass fa fa-edit"
                       href="manager/bookServlet?action=queryBookById&id=${book.id}&pageNo=${requestScope.bookPage.pageNo}"></a>
                </td>
                <td><a class="deleteClass fa fa-remove"
                       href="manager/bookServlet?action=delete&id=${book.id}&pageNo=${requestScope.bookPage.pageNo}"></a>
                </td>
            </tr>
        </c:forEach>


        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td><a href="pages/manager/book_edit.jsp?pageNo=${requestScope.bookPage.pageTotal}">添加图书</a></td>
        </tr>

    </table>


</div>


<%-- 静态包含页条 --%>
<%@include file="/pages/common/page_nav.jsp" %>

<%-- 静态包含 页尾 --%>
<%@include file="/pages/common/footer.jsp" %>
</body>
</html>