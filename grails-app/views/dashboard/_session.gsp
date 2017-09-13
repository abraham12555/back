<!--
  To change this license header, choose License Headers in Project Properties.
  To change this template file, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <g:urlContextAware value="/login/keepSessionAlive" var="keepSessionAlive"/>
        <script type="text/javascript">
            $.maxInactiveInterval = "${session.maxInactiveInterval}";
            $.keepSessionAlive = "${keepSessionAlive}";
            $.logout = "${urlLogout}"
        </script>
        <g:external dir="js" file="/dashboard/session.min.js" />
    </head>
    <body>
    </body>
</html>
