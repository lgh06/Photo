<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta charset="UTF-8" />
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>我是摄影师，我要注册</title>
<link rel="stylesheet" type="text/css" href="./res/css/reset.css" />
<link rel="stylesheet" type="text/css" href="./res/css/main.css" />
<link rel="stylesheet" type="text/css" href="./res/css/bootstrap.css" />
<!-- <link rel="stylesheet" type="text/css" href="./res/css/bootstrap-theme.css" /> -->
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
  <script src="http://cdn.bootcss.com/html5shiv/3.7.0/html5shiv.js"></script>
  <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
  <![endif]-->
<script src="./res/js/jquery-1.10.2.js"></script>
<script src="./res/js/bootstrap.js"></script>
<script>
  $(document).ready(function(){
    $('.reg-model-title').click(function(){
    	$(this).addClass('active').removeClass('btn-default').addClass('btn-primary');
    	$('.reg-grapher-title').removeClass('active').removeClass('btn-primary').addClass('btn-default');
        $('.reg-grapher-content').hide(500);
        setTimeout(function(){$('.reg-model-content').show(1000);},501);
    });

      $('.reg-grapher-title').click(function(){
    	  $(this).addClass('active').removeClass('btn-default').addClass('btn-primary');
      	  $('.reg-model-title').removeClass('active').removeClass('btn-primary').addClass('btn-default');
      	  $('.reg-model-title').removeClass('bgcred');
          $('.reg-model-content').hide(500);
          setTimeout(function(){$('.reg-grapher-content').show(1000);},501);
          
      });
    });
  </script>

</head>
<body>


	<div class="reg jumbotron">
		<button type="button" class="btn btn-primary btn-lg reg-model-title">我是模特</button>
		<button type="button" class="btn btn-default btn-lg reg-grapher-title">我是摄影师</button>
		
		<div class="reg-model-content">
		<form class="form-horizontal clear" role="form" name="regModel" action="./user/regModel" method="post" >
			<div class="input-group uname">
				<span class="input-group-addon logintxt">用户名</span> 
				<input type="text" class="form-control" name="user.name">
			</div>
			<div class="input-group uname">
				<span class="input-group-addon logintxt">真实姓名</span>
				<input type="text" class="form-control" name="user.realname">
			</div>
			<div class="input-group uname">
				<span class="input-group-addon logintxt">请设置密码</span>
				<input type="password" class="form-control" name="user.password">
			</div>
			
				<input type="hidden" class="hide" name="user.role" value="1">
			<div class="submit">
				<input type="submit" value="提交" />
			</div>

		</form>
		</div>
		
		<div class="reg-grapher-content">
		<form class="form-horizontal clear" role="form" name="regGrapher" action="./user/regGrapher" method="post" >
			<div class="input-group uname">
				<span class="input-group-addon logintxt">用户名</span> 
				<input type="text" class="form-control" name="user.name">
			</div>
			<div class="input-group uname">
				<span class="input-group-addon logintxt">真实姓名</span>
				<input type="text" class="form-control" name="user.realname">
			</div>
			<div class="input-group uname">
				<span class="input-group-addon logintxt">请设置密码</span>
				<input type="password" class="form-control" name="user.password">
			</div>
			
				<input type="hidden" class="hide" name="user.role" value="2">
			<div class="submit">
				<input type="submit" value="提交" />
			</div>


		</form>
		</div>



	</div>

	<script src="./res/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
