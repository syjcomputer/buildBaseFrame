<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Title</title>
	<script src="http://res.wx.qq.com/connect/zh_CN/htmledition/js/wxLogin.js">
	</script>
</head>
<body>
<div id="weixin"></div>
<script>
	var obj = new WxLogin({
		self_redirect:true,
		id:"weixin", // 要与你在那里生成二维码的div id 一致
		appid: "wx1292a69b2cd3c5b7", // 你申请的appid
		redirect_uri: encodeURIComponent("http://2462bd30.r6.cpolar.top/buildBaseFrame/api/v1/user/wechat/login/wx"),// 回调url，ip地址一定是内网穿透过的，然后你扫码后，微信官方返回一些参数（code，state）
		scope: "snsapi_login,snsapi_userinfo",// 你的权限
		state: "STATE",// 微信官方接口为了防止跨域攻击要加的参数 可以默认就这个
	});
</script>
</body>
</html>
