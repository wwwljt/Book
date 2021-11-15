<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 温伟伟
  Date: 2021/10/23
  Time: 19:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--分页条的开始--%>
<div id="page_nav">
    <%-- 页数大于首页，  才显示 --%>
    <c:if test="${requestScope.bookPage.pageNo>1}">
        <a href="${requestScope.bookPage.url}&pageNo=1">首页</a>
        <a href="${requestScope.bookPage.url}&pageNo=${requestScope.bookPage.pageNo-1}">上一页</a>
    </c:if>

    <%--页码输出的开始--%>
    <c:choose>
        <%--情况1：如果总页码小于等于5的情况，页码的范围是：1~总页码--%>
        <c:when test="${ requestScope.bookPage.pageTotal <= 5 }">
            <c:set var="begin" value="1"/>
            <c:set var="end" value="${requestScope.bookPage.pageTotal}"/>
        </c:when>
        <%--情况2：总页码大于5的情况--%>
        <c:when test="${requestScope.bookPage.pageTotal > 5}">
            <c:choose>
                <%--小情况1：当前页码为前面3个：1，2，3的情况，页码范围是：1~5.--%>
                <c:when test="${requestScope.bookPage.pageNo <= 3}">
                    <c:set var="begin" value="1"/>
                    <c:set var="end" value="5"/>
                </c:when>
                <%--小情况2：当前页码为最后3个，8，9，10，页码范围是：总页码减4~ 总页码--%>
                <c:when test="${requestScope.bookPage.pageNo > requestScope.bookPage.pageTotal-3}">
                    <c:set var="begin" value="${requestScope.bookPage.pageTotal-4}"/>
                    <c:set var="end" value="${requestScope.bookPage.pageTotal}"/>
                </c:when>
                <%--小情况3：，页码范围是：当前页码减2 - 当前页码加2--%>
                <c:otherwise>
                    <c:set var="begin" value="${requestScope.bookPage.pageNo-2}"/>
                    <c:set var="end" value="${requestScope.bookPage.pageNo+2}"/>
                </c:otherwise>
            </c:choose>
        </c:when>
    </c:choose>

    <c:forEach begin="${begin}" end="${end}" var="i">
        <c:if test="${i == requestScope.bookPage.pageNo}">
            【${i}】
        </c:if>
        <c:if test="${i != requestScope.bookPage.pageNo}">
            <a href="${requestScope.bookPage.url}&pageNo=${i}">${i}</a>
        </c:if>
    </c:forEach>
    <%--页码输出的结束--%>

    <%--如果已经是最后一页，则不显示 末页，下一页--%>
    <c:if test="${requestScope.bookPage.pageNo<requestScope.bookPage.pageTotal}">
        <a href="${requestScope.bookPage.url}&pageNo=${requestScope.bookPage.pageNo+1}">下一页&nbsp;&nbsp;&nbsp;&nbsp;</a>
        <a href="${requestScope.bookPage.url}&pageNo=${requestScope.bookPage.pageTotal}">末页&nbsp;&nbsp;&nbsp;&nbsp;</a>
    </c:if>
    共${requestScope.bookPage.pageTotal}页，${requestScope.bookPage.pageTotalCount}条记录&nbsp;&nbsp;&nbsp;&nbsp;到第
    <input value="${requestScope.bookPage.pageNo}" name="pn" id="pn_input"/>页
    <input id="searchPageBtn" type="button" value="确定">
</div>
<%-- 分页条的结束--%>
