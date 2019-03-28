<%@ page import="com.clinks.portal.web.utils.ApolloUtil" %><%
    String webPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
    String path = request.getContextPath();
    webPath += path;
    String pmsPath = ApolloUtil.getPmsUrl();
    request.setAttribute("webPath", webPath);
    request.setAttribute("pmsPath", ApolloUtil.getPmsUrl());
%>
<script type="text/javascript">
    var _path="<%=webPath%>";
    var pmsUrl = "<%=pmsPath%>";
</script>
<script type="text/javascript" src="${webPath}/js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="${webPath}/js/cookie.base.js"></script>
<script type="text/javascript" src="${webPath}/js/jquery.blockUI.js"></script>