<!doctype html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>服务案例</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="c-nav" id="c-nav">
  <div class="container navFlex">
    <div class="flexItem"> <img  class="logo" src="images/logo.png" /> </div>
    <div class="flexItem clearfix">
      <ul>
		  <li><a href="${baseUrl}/index.html">首页</a></li>
		  <li><a href="${baseUrl}">关于我们</a></li>
		  <li><a href="${baseUrl}/products.html">公司产品</a></li>
		  <li class="active"><a href="${baseUrl}/serviceCase.html">服务案例</a></li>
		  <li><a href="${baseUrl}/news.html">新闻资讯</a></li>
		  <li><a href="${baseUrl}/contactUs.html">联系我们</a></li>
      </ul>
	  <div class="search">
		  <input type="text" class="search_text" placeholder="关键字搜索" />
		  <input type="button" class="search_btn" value="搜索" />
	  </div>
    </div>
  </div>
</div>
<div id="case">
	<div class="bannerx">
		<img src="${serviceCaseModule.url}">
	</div>
	<div class="position"><div class="container">当前位置：<a href="index.html">首页</a>|<span>服务案例</span></div></div>
	<div class="container main">
		<div class="newsx_main clearfix">
			<div class="page_nav">
				<#list serviceCaseModuleList as serviceCaseModule>
				<span <#if serviceCaseModule_index ==0> class="active"</#if> >${serviceCaseModule.name}</span>
				</#list>
			</div>
			<ul class="case_list clearfix">
				<#list serviceCaseModule.projectDtoList as project>
				<li class="layui-col-xs6 layui-col-sm4 layui-col-md4">
					<a href="${baseUrl}/serviceCase/${project.id}.html" target="_blank">
					<div><img src="${project.url}"></div>
					<h2>${project.describeMsg}</h2>
					</a>
				</li>
				</#list>
			</ul>
			<div id="paging"></div>
		</div>
	  
	</div>
</div>
<div id="footer">
  <div class="container">
	  <div class="footer clearfix">
		  <div class="footer_l layui-col-sm12 layui-col-md6">
			  <h2>网站导航</h2>
			  <ul class="clearfix">
				<li><a href="javascript:;" class="this">好奇首页</a></li>
				<li>
					<a href="javascript:;" class="this">关于我们</a>
					<a href="javascript:;">公司简介</a>
                    <a href="javascript:;">企业文化</a>
                    <a href="javascript:;">荣誉资质</a>
                    <a href="javascript:;">发展历程</a>
                    <a href="javascript:;">组织架构</a>
				</li>
				<li>
					<a href="javascript:;" class="this">公司产品</a>
					<a href="javascript:;">文字文字</a>
				    <a href="javascript:;">文字文字</a>
				    <a href="javascript:;">文字文字</a>
				    <a href="javascript:;">文字文字</a>
				    <a href="javascript:;">文字文字</a>
				</li>
				<li>
					<a href="javascript:;" class="this">项目案例</a>
					<a href="javascript:;">文字文字</a>
				    <a href="javascript:;">文字文字</a>
				    <a href="javascript:;">文字文字</a>
				    <a href="javascript:;">文字文字</a>
				    <a href="javascript:;">文字文字</a>
				</li>
				<li>
					<a href="javascript:;" class="this">新闻资讯</a>
					<a href="javascript:;">文字文字</a>
				    <a href="javascript:;">文字文字</a>
				    <a href="javascript:;">文字文字</a>
				    <a href="javascript:;">文字文字</a>
				    <a href="javascript:;">文字文字</a>
				</li>
				<li>
					<a href="javascript:;" class="this">联系我们</a>
					<a href="javascript:;">文字文字</a>
				    <a href="javascript:;">文字文字</a>
				    <a href="javascript:;">文字文字</a>
				</li>
			  </ul>
		  </div>
		  <div class="footer_m layui-col-sm12 layui-col-md3">
			  <h2>联系我们</h2>
			  <div class="middle">
			  <div class="contact_l"> 
					<h5>400-0000-033</h5>
					<p>周一至周日 8:00~18:00</p>
			  </div>
			   <div class="contact_r">
				   	 <h5>QQ在线咨询</h5>
				   	 <p>24小时在线为您服务</p>
			   </div>
			   </div>
		  </div>
		  <div class="footer_r layui-col-sm12 layui-col-md3">
			  <h2>关注我们</h2>
			  <ul class="clearfix">
				<li><img src="images/ewm.jpg"><p>微信二维码</p></li> 
				<li><img src="images/ewm.jpg"><p>抖音二维码</p></li> 
			  </ul>
		  </div>
	  </div>
	  
  </div>
  <div class="footer_bot">©Copyright 2022-2022 版权所有 苏州好奇数字科技有限公司</div>
</div>
<div class="kf">
	<ul>
	  <li class="this"><a href="javascript:;"><img src="images/icon1.png"><p>电话咨询</p></a></li>
	  <li><a href="javascript:;"><img src="images/icon2.png"><p>QQ咨询</p></a></li>
	  <li><a href="javascript:;"><img src="images/icon3.png"><p>在线留言</p></a></li>
	  <li class="top"><a href="#c-nav"><img src="images/icon4.png"></a></li>
	</ul>
</div>
<script type="text/javascript" src="layui/layui.js"></script> 
<script src="js/jquery-1.11.3.min.js" ></script> 
<script type="text/javascript">
	$('.page_nav span').click(function () {
	    var f = this;
	    $('.page_nav span').each(function () {
	      this.className = this == f ? 'active' : 'none'
	    });
	  });
//参考layui官方文档
layui.use(['laypage', 'layer'], function(){
  var laypage = layui.laypage
  ,layer = layui.layer;
  laypage.render({
    elem: 'paging'
    ,count: 100
    ,first: '首页'
    ,last: '尾页'
    ,prev: '<em>上一页</em>'
    ,next: '<em>下一页</em>'
    ,theme: '#297ec2'
  });
});
</script>
</body>
</html>