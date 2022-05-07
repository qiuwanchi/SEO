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
				<div class="navimg">

				<#if logoProject.clickUrl?? && logoProject.clickUrl != "">
				<a href="${logoProject.clickUrl}" target="_blank">
				</#if>
				<img  class="logo" src="${logoProject.url}" <#if logoProject.alt?? && logoProject.alt != ""> alt="${logoProject.alt}" </#if> />
				<#if logoProject.clickUrl?? && logoProject.clickUrl != "">
				</a>
				</#if>

				</div>
				<div class="navul clearfix">
					<ul>
						  <li><a href="${baseUrl}/index.html">首页</a></li>
                          <li><a href="${baseUrl}/aboutUs.html">关于我们</a></li>
                          <li class="active"><a href="${baseUrl}/products.html">公司产品</a></li>
                          <li><a href="${baseUrl}/serviceCase.html">服务案例</a></li>
                          <li><a href="${baseUrl}/solution.html">解决方案</a></li>
                          <li><a href="${baseUrl}/news.html">新闻资讯</a></li>
                          <li><a href="${baseUrl}/contactUs.html">联系我们</a></li>
					</ul>
				</div>
				<div class="kftel">
					<h2>400-0000-033</h2>
					<span>客服服务热线</span>
				</div>
			</div>
		</div>
		<div id="case">
			<div class="bannerx">
			 <#if banner.clickUrl?? && banner.clickUrl != "">
                 <a href="${banner.clickUrl}" target="_blank">
             </#if>
			 <img src="<#if banner.url?? && banner.url != "">${banner.url}</#if>"  <#if banner.alt?? && banner.alt != ""> alt="${banner.alt}" </#if>>
			 <#if banner.clickUrl?? && banner.clickUrl != "">
                  </a>
             </#if>

			 </div>
			<div class="position">
				 <div class="container">当前位置：
				 <a href="${baseUrl}/index.html">首页</a>|
                     <#if moduleDto??>
                        <a href="${baseUrl}/products.html">公司产品</a>|
                        <span>${moduleDto.name}</span>
                     <#else>
                        <span>公司产品</span>
                     </#if>
				 </div>
			</div>
			<div class="container main">
				<div class="newsx_main goodsx_main clearfix">
					<div class="page_nav clearfix">
					<#list productModuleList as productModule>
                        <span <#if productModule_index == categoryIndex> class="active" </#if> ><a href="${baseUrl}/products/${productModule.code}/">${productModule.name}</a></span>
					</#list>

					</div>
					<ul class="case_list clearfix">
					<#list projectDtoList as productProject>
						<li class="layui-col-xs6 layui-col-sm4 layui-col-md3">
							<a href="${baseUrl}/products/${productProject.moduleCode}/${productProject.number}.html" target="_blank">
								<div><img src="${productProject.url}" <#if productProject.alt?? && productProject.alt != ""> alt="${productProject.alt}" </#if>></div>
								<h2>${productProject.name}</h2>
							</a>
						</li>
					</#list>
					</ul>
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
		</script>
	</body>

</html>