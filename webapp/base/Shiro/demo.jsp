<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html xmlns:v-on="http://www.w3.org/1999/xhtml" xmlns:v-bind="http://www.w3.org/1999/xhtml">

<head>
  <title>vueJs.html</title>
  <meta charset="UTF-8">
  <!--<link rel="stylesheet" type="text/css" href="./styles.css">-->
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%--<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />--%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<title>${appName}</title>
<script type="text/javascript" src="/js/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/js/vue/vue.js"></script>

</head>
<body>
<%--<shiro:hasPermission name={"create","query"}>--%>
<%--create permssion,query permssion  <br>--%>
<%--</shiro:hasPermission>--%>
<div id="test">



  <shiro:hasRole name="role.admin">
    admin role  <button v-on:click="adminRole()">adminRoleTest</button><br>
  </shiro:hasRole>

  <shiro:hasRole name="role.user">
    user role <button v-on:click="userRole()">userRoleTest</button><br><br>
  </shiro:hasRole>

  <shiro:hasPermission name="create">
    create permssion  <button v-on:click="createPermssion()">createPermssionTest</button><br><br>
  </shiro:hasPermission>

  <shiro:hasPermission name="query">
    query permssion <button v-on:click="queryPermssion()">queryPermssionTest</button><br><br>
  </shiro:hasPermission>

  <shiro:hasPermission name="ok">
    ok permssion <button v-on:click="okPermssion()">okPermssionTest</button><br><br>
  </shiro:hasPermission>

  <button v-on:click="okPermssion()">okPermssionTest</button><br><br>
</div>

</body>

<script type="text/javascript">

    Vue.config.debug = true;//开启vue的debug模式
    var vm = new Vue({
                         el:"#test",
                         data : {
                             user : {}



                         },
                         methods : {
                             adminRole : function() {
                                 $.ajax({
                                            type : "get",
                                            url : "/base/Shiro/ShiroDemoRole",
//                                            data : JSON.stringify(),
                                            contentType:"application/json",
                                            success : function(data) {
                                            },
                                            error : function() {
                                                alert("请求失败");
                                            }
                                        });
                             },
                             userRole : function() {
                                 $.ajax({
                                            type : "get",
                                            url : "/base/Shiro/ShiroDemoRole",
                                            contentType:"application/json",
                                            success : function(data) {
                                            },
                                            error : function() {
                                                alert("请求失败");
                                            }
                                        });
                             },createPermssion : function() {
                                 $.ajax({
                                            type : "get",
                                            url : "/base/Shiro/ShiroDemoPermission",
                                            contentType:"application/json",
                                            success : function(data) {
                                            },
                                            error : function() {
                                                alert("请求失败");
                                            }
                                        });
                             },queryPermssion : function() {
                                 $.ajax({
                                            type : "get",
                                            url : "/base/Shiro/ShiroDemoPermission",
                                            contentType:"application/json",
                                            success : function(data) {
                                            },
                                            error : function() {
                                                alert("请求失败");
                                            }
                                        });
                             },okPermssion : function() {
                                 $.ajax({
                                            type : "get",
                                            url : "/base/Shiro/ShiroDemoPermission2",
                                            contentType:"application/json",
                                            success : function(data) {
                                                alert(data.msg);
                                            },
                                            error : function(data) {
                                                alert(data);
                                                alert("请求失败");
                                            }
                                        });
                             }
                         }
                     });

</script>


</html>
