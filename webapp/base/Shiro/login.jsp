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
<div id="test">
  <form>
    登录:<input v-model="user.loginName" /> <br /> 密码:<input
          v-model="user.password" /> <br />
  </form>
  <button v-on:click="test(user)">login</button>
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
                             test : function(user) {
                                 console.log("点击了登录");
                                 console.log(user);
                                 console.log(JSON.stringify(user));
                                 $.ajax({
                                            type : "post",
                                            url : "/base/Shiro/doShiroLogin",
                                            data : JSON.stringify(user),
                                            datatype : 'json',
                                            contentType:"application/json",
                                            success : function(data) {

                                                console.log(data);
//                                                vm.parent = data;
//                                                vm.isTest = data.success
                                                //console.log(vm.parent.success);
                                                //console.log(vm.isTest);
                                            },
                                            error : function() {
                                                alert("请求失败");
                                            }
                                        });
                             }
                         }
                     });

</script>
</html>
