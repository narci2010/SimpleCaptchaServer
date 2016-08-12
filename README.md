# SimpleCaptchaServer
简单的嵌入式Http框架验证码服务（包含：语音，图片验证码）

验证码生成服务
-----------------------------概述-----------------------------
基于SimpleCaptcha完善的Java类库（要支持数字，英文，数字英文组合，中文，音频等验证码）。
程序是运用了嵌入式的http框架，所以直接运行本可执行jar程序,无须任何额外的（Tomcat等http服务器容器）
具有一定的并发性
通过测试，普通机器在5秒开启1000个线程，发送10000个请求，平均响应时间为2.5s

-----------------------------接口-----------------------------

/GET http://ip:port/?width=140&height=40&len=6&style=eng_num&output=jpg
{
	"sucess":true,
	"data":{
		"captcha_base64":"",
		"answer":"0zCf",
		"media_type":"png"
	}
}
或者
{
	"success": false,
	"error": {
		"code": 错误状态码,
		"name": "错误信息的文字描述"
	}
}

-----------------------------参数说明-----------------------------
端口号：默认为8181,修改端口号，可以通过配置文件进行相应的修改
width:验证码图片长度。默认为：120
height:验证码图片高度。默认为：40
len: 验证码的长度。此参数和captcha_src同时出现时以captcha_src为准。默认为4。
style: eng/num/eng_num/chs/...，验证码的风格。需要把SimpleCaptcha支持的所有风格都包含进来，比如纯英文、纯数字、英文夹杂数字、纯中文等等。默认为纯英文。
output: gif/png/jpg/mp3/wav，验证码的输出类型。默认为png。
