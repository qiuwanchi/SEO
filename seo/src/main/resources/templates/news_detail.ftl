<!doctype html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>${newsProject.title}</title>
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
		<img src="images/news_banner.png">
	</div>
	<div class="container">
		<div class="newsx_detail clearfix">
      <div class="layui-col-xs12 layui-col-md9 newsx_detail_l">
      	  <div class="position">当前位置：<a href="${baseUrl}/index.html">首页</a>|<a href="${baseUrl}/news.html">新闻资讯</a>|<a href="${baseUrl}/news.html">${newsProject.moduleName}</a>|<span>${newsProject.name}</span></div>
      	  <div style="padding: 30px;">
      	  <h1>${newsProject.name}</h1>
      	  <div class="news_data"><span>发布日期：${newsProject.years}-${newsProject.day}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;作者：${newsProject.createBy}</span></div>
			  ${newsProject.content}
      	 </div>
      	  <div class="hot_tag clearfix">
      	  	<span>标签：</span>
			<#list keywordsList as keywords>
      	  		<strong><a href="javascript:;">${keywords}</a></strong>
		    </#list>
      	  </div>
      	  <div class="share clearfix">
      	  	<span>分享到：</span>
      	  	<div style="float: left;"><img src="images/share.jpg" ></div>
      	  </div>
          <div class="article clearfix">


          	<div class="article_left">上一篇：
			<#if preProject??>
				<a href="${baseUrl}/news/${preProject.id}.html" target="_blank">${preProject.name}</a>
			<#else>
				<a href="javascript:;">无</a>
			</#if>
			</div>
          	<div class="article_right">下一篇：
				<#if nextProject??>
					<a href="${baseUrl}/news/${nextProject.id}.html" target="_blank">${nextProject.name}</a>
				<#else>
					<a href="javascript:;">无</a>
			</#if>
			</div>
          </div>
      </div>
      <div class="layui-col-xs12 layui-col-md3 newsx_detail_r">
      	<div class="rightList">
      		<h3 class="clearfix"><span></span><strong>相关推荐</strong></h3>
      		<ul>
				<#list recommendProjectDtoList as recommendProject>
					<li><a href="${baseUrl}/news/${recommendProject.id}.html" target="_blank">●&nbsp;${recommendProject.name}</a></li>
				</#list>
      		</ul>
      	</div>
      	<div class="rightList">
      		<h3 class="clearfix"><span></span><strong>热门问答</strong></h3>
      		<ul>
      			<li><a href="javascrpt:;">●&nbsp;近期全国本土疫情多点频发</a></li>
      			<li><a href="javascrpt:;">●&nbsp;打造乡村振兴的齐鲁样板</a></li>
      			<li><a href="javascrpt:;">●&nbsp;交通运输部召开会议，部署货运物流保通保畅工作</a></li>
      			<li><a href="javascrpt:;">●&nbsp;近期全国本土疫情多点频发</a></li>
      			<li><a href="javascrpt:;">●&nbsp;共同的梦想，共同的未来</a></li>
      			<li><a href="javascrpt:;">●&nbsp;近期全国本土疫情多点频发</a></li>
      		</ul>
      	</div>
      	<div class="rightList">
      		<h3 class="clearfix"><span></span><strong>热门标签</strong></h3>
      		<div class="bq">
      			<span><a href="javascript:;">全国</a></span>
      			<span><a href="javascript:;">本土疫情</a></span>
      			<span><a href="javascript:;">疫情多点频发</a></span>
      			<span><a href="javascript:;">交通运输</a></span>
      			<span><a href="javascript:;">部署货运</a></span>
      			<span><a href="javascript:;">全国</a></span>
      			<span><a href="javascript:;">本土疫情</a></span>
      			<span><a href="javascript:;">频发</a></span>
      			<span><a href="javascript:;">交通运输</a></span>
      			<span><a href="javascript:;">未来</a></span>
      		</div>
      	</div>
      </div>
		</div>
	  
	</div>
</div>
<#include "footer.ftl"/>
<script type="text/javascript">

</script>
</body>
</html>