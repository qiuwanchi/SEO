<!doctype html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>公司产品</title>
	<link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="c-nav" id="c-nav">
	<div class="container navFlex">
		<div class="flexItem"> <img  class="logo" src="${logoProject.url}" <#if logoProject.alt?? && logoProject.alt != ""> alt="${logoProject.alt}" </#if> /> </div>
	<div class="flexItem clearfix">
		<ul>
			<li><a href="${baseUrl}/index.html">首页</a></li>
			<li><a href="${baseUrl}">关于我们</a></li>
			<li class="active"><a href="${baseUrl}/products.html">公司产品</a></li>
			<li><a href="${baseUrl}/serviceCase.html">服务案例</a></li>
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
		<img src="images/goods_banner.jpg">
	</div>
	<div class="position"><div class="container">当前位置：<a href="index.html">首页</a>|<span>公司产品</span></div></div>
	<div class="container main">
		<div class="newsx_main goodsx_main clearfix">
			<div class="page_nav">
				<span class="active"><a href="#case">全部</a></span>

				<#list productModuleList as productModule>
				<span><a href="#goods_${productModule_index}">${productModule.name}</a></span>
				</#list>
			</div>
		<#list productModuleList as productModule>
			<div id="goods_${productModule_index}" class="goodsx_num clearfix">
				<div class="goods_tit"><h3>${productModule.name}<strong>Exhibition hall multimedia</strong></h3></div>
				<ul class="case_list clearfix">

					<#list productModule.projectDtoList as project>
					<li class="layui-col-xs6 layui-col-sm4 layui-col-md4">
						<a href="goods_detail.html">
							<div><img src="${project.url}"></div>
							<h2>${project.name}</h2>
						</a>
					</li>
					</#list>
				</ul>
			</div>
		</#list>

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

</script>
</body>
		</html>