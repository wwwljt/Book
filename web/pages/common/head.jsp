<%--
  Created by IntelliJ IDEA.
  User: 温伟伟
  Date: 2021/10/21
  Time: 22:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme()
            + "://"
            + request.getServerName()
            + ":"
            + request.getServerPort()
            + request.getContextPath()
            + "/";
    pageContext.setAttribute("basePath",basePath);
%>

<!-- 写 base 标签 永远固定相对路径跳转的结果 -->
<base href="<%=basePath%>">
<link type="text/css" rel="stylesheet" href="static/css/style.css">
<link href="plugins/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet">
<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>
<script type="text/javascript">
    $(function () {
        //给 删除的 a 标签绑定单击事件，用于删除的确认操作
        $("a.deleteClass").click(function () {
            return confirm("你确定要删除[" + $(this).parent().parent().find("td:first").text() + "]?");
        })

        //给 修改的 a 标签绑定单击事件，用于删除的确认操作
        $("a.updateClass").click(function () {
            return confirm("你确定要修改[" + $(this).parent().parent().find("td:first").text() + "]?");
        })

        //跳转到指定的页码
        $("#searchPageBtn").click(function () {
                let pageNo = $("#pn_input").val();
                //javaScript语言提供了一个 localhost 地址栏对象
                //他有一个属性叫做 href.  它可以获取浏览器地址中的地址(href 可读可写)

                location.href = "${pageScope.basePath}${requestScope.bookPage.url}&pageNo=" + pageNo;

            }
        )


    })
</script>
<style>
    a {
        text-decoration: none;
    }
</style>
