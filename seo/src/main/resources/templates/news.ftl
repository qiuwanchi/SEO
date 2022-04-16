<!doctype html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>新闻资讯</title>
<link rel="stylesheet" href="${baseUrl}/css/style.css">
</head>
<body>
<div class="c-nav" id="c-nav">
  <div class="container navFlex">
    <div class="flexItem"> <img  class="logo" src="${logoProject.url}" <#if logoProject.alt?? && logoProject.alt != ""> alt="${logoProject.alt}" </#if> /> </div>
    <div class="flexItem clearfix">
      <ul>
		  <li><a href="${baseUrl}/index.html">首页</a></li>
		  <li><a href="about.html">关于我们</a></li>
		  <li><a href="${baseUrl}/products.html">公司产品</a></li>
		  <li><a href="${baseUrl}/serviceCase.html">服务案例</a></li>
		  <li class="active"><a href="${baseUrl}/news.html">新闻资讯</a></li>
		  <li><a href="${baseUrl}/contactUs.html">联系我们</a></li>
      </ul>
	  <div class="search">
		  <input type="text" class="search_text" placeholder="关键字搜索" />
		  <input type="button" class="search_btn" value="搜索" />
	  </div>
    </div>
  </div>
</div>
<div id="news">
	<div class="bannerx">
		<img src="${newsModule.url}">
	</div>
	<div class="container main">
		<div class="position">当前位置：<a href="${baseUrl}/index.html">首页</a>|<span>新闻资讯</span></div>
		<div class="newsx_main clearfix">
			<div class="page_nav_news layui-col-xs12 layui-col-sm4 layui-col-md3">
				<#list newsModuleList as newsModule>
					<span <#if newsModule_index ==0>class="active"</#if>>${newsModule.name}</span>
				</#list>
			</div>
			<div class="layui-col-xs12 layui-col-sm8 layui-col-md9">
			<ul>

			<#list newsProjectList as newsProject>
			<li class="clearfix">
				<div class="news_list_img">
					<a href="${baseUrl}/news/${newsProject.id}.html" target="_blank">
					<img src="${newsProject.url}"/>
					</a>
				</div>
				<div class="news_list_data">
					 <strong>${newsProject.monthDay}</strong>
					 <span><a href="${baseUrl}/news/${newsProject.id}.html" target="_blank">→</a></span>
				</div>
				<div class="news_list_text">
					<a href="${baseUrl}/news/${newsProject.id}.html" target="_blank">
					<h1>${newsProject.name}</h1>
					<p>${newsProject.describeMsg}</p>
					</a>
				</div>
			</li>
			</#list>

			</ul>
		  </div>
			</div>
	  
	</div>
</div>
<#include "footer.ftl"/>
<script type="text/javascript">
	$('.page_nav span').click(function () {
	    var f = this;
	    $('.page_nav span').each(function () {
	      this.className = this == f ? 'active' : 'none'
	    });
	  });
	$('.page_nav_news span').click(function () {
	    var f = this;
	    $('.page_nav_news span').each(function () {
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
    ,theme: '#297ec2'
  });
});
</script>
</body>
</html>