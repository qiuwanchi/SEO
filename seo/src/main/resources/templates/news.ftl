<!doctype html>
<html lang="zh-CN">

	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title><#if seoProjectDto.title?? && seoProjectDto.title != "">${seoProjectDto.title}</#if></title>
		<meta name="keywords" content="<#if seoProjectDto.keywords?? && seoProjectDto.keywords != "">${seoProjectDto.keywords}</#if>">
		<meta name="description" content="<#if seoProjectDto.description?? && seoProjectDto.description != "">${seoProjectDto.description}</#if>">
		<link rel="stylesheet" href="${baseUrl}/css/style.css">
	</head>

	<body>
		<div class="nav">
			<div class="container clearfix">
				<div class="navimg"> <img  class="logo" src="${logoProject.url}" <#if logoProject.alt?? && logoProject.alt != ""> alt="${logoProject.alt}" </#if> /> </div>
				<div class="navul clearfix">
					<ul>
                          <li><a href="${baseUrl}/index.html">首页</a></li>
                          <li><a href="${baseUrl}/aboutUs.html">关于我们</a></li>
                          <li><a href="${baseUrl}/products.html">公司产品</a></li>
                          <li><a href="${baseUrl}/serviceCase.html">服务案例</a></li>
                          <li><a href="${baseUrl}/solution.html">解决方案</a></li>
                          <li  class="active"><a href="${baseUrl}/news.html">新闻资讯</a></li>
                          <li><a href="${baseUrl}/contactUs.html">联系我们</a></li>
					</ul>
				</div>
				<div class="kftel">
					<h2>400-0000-033</h2>
					<span>客服服务热线</span>
				</div>
			</div>
		</div>
		<div id="news">
			<div class="bannerx">
			<#if bannerDto.clickUrl?? && bannerDto.clickUrl != "">
                <a href="${bannerDto.clickUrl}" target="_blank">
            </#if>
            <img src="<#if bannerDto.url?? && bannerDto.url != ""> ${bannerDto.url} </#if>"  <#if bannerDto.alt?? && bannerDto.alt != ""> alt="${bannerDto.alt}"</#if> >

            <#if bannerDto.clickUrl?? && bannerDto.clickUrl != "">
                </a>
            </#if>
			</div>
			<div class="container main newsx_main">
				<div class="position">当前位置：
					<a href="${baseUrl}/index.html">首页</a>
                    	 <#if firstCategory??>
                    		|<a href="${baseUrl}/news.html">新闻资讯</a>|<span>${newsModule.name}</span>
                    	 <#else>
                    		|<span>新闻资讯</span>
                    	 </#if>

					</div>
				<div class=" clearfix">
					<div class="page_nav_news layui-col-xs12 layui-col-sm4 layui-col-md3">
                      <#list newsModuleList as newsModule>
                        <a href="${baseUrl}/news/${newsModule.code}/">
                        <span <#if newsModule_index == clickCategorySort>class="active"</#if>>${newsModule.name}</span>
                        </a>
                      </#list>

					</div>
					<div class="layui-col-xs12 layui-col-sm8 layui-col-md9">
						<ul class="news_list">
						<#list newsProjectList as newsProject>
							<li class="clearfix">
								<div class="news_list_img">
								<a href="${baseUrl}/news/${newsProject.moduleCode}/${newsProject.number}.html" target="_blank">
								<img src="${newsProject.url}" <#if newsProject.alt?? && newsProject.alt != ""> alt="${newsProject.alt}" </#if>/>
								</a>
								</div>
								<div class="news_list_data"> <strong>${newsProject.monthDay}</strong> <span><a href="${baseUrl}/news/${newsProject.moduleCode}/${newsProject.number}.html" target="_blank">→</a></span> </div>
								<div class="news_list_text">
									<a href="${baseUrl}/news/${newsProject.moduleCode}/${newsProject.number}.html" target="_blank">
										<h1>${newsProject.name}</h1>
										<p>${newsProject.describeMsg}</p>
									</a>
								</div>
							</li>
						</#list>

						</ul>
						<div id="newPage" class="clearfix" style="margin-right: 20px;">
							<ul class="clearfix">
								<span>共${page.total}条</span>
								${pageHtml}
							</ul>
						</div>

					</div>
				</div>
			</div>
		</div>
		<#include "footer.ftl"/>
		<script type="text/javascript">
			$('.page_nav span').click(function() {
				var f = this;
				$('.page_nav span').each(function() {
					this.className = this == f ? 'active' : 'none'
				});
			});
			$('.page_nav_news span').click(function() {
				var f = this;
				$('.page_nav_news span').each(function() {
					this.className = this == f ? 'active' : 'none'
				});
			});

		</script>
	</body>

</html>