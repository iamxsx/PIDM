<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<footer class="footer">
    <div class="col-md-12" style="position: relative">
        <p>&copy; 2016 广东省价格和产业发展协会&nbsp;版权所有&nbsp;不得转载 <a href="##">联系我们</a></p>
        <p>粤ICP备16102668号-1 </p>
        <p><a href="#">广州亦云信息技术股份有限公司&nbsp;提供技术支持</a></p>
        <span class="foot-tip">微信二维码即将登陆，敬请期待</span>
        <img class="footcode" src="${ctx}/qrcode?text=202.104.231.132:8080">
    </div>
</footer>