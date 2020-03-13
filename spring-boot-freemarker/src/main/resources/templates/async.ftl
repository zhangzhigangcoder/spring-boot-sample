<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>servlet async support</title>
	</head>
	<body>
		<script type="text/javascript" src="http://mod.qw.com/CQM/resources/scripts/jquery-1.7.1.min.js"></script>
		<script type="text/javascript">
		       deferred();//页面打开就向后台发送请求
		       
		       function deferred(){
		           $.get('defer',function(data){
		               console.log(data); //在控制台输出服务端推送的数据
		               deferred(); //一次请求完成后再向后台发送请求
		           });
		       }
		</script>
	</body>
</html>
