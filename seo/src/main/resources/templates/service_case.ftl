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
                        <li><a href="${baseUrl}/products.html">公司产品</a></li>
                        <li class="active"><a href="${baseUrl}/serviceCase.html">服务案例</a></li>
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
			 <#if bannerDto.clickUrl?? && bannerDto.clickUrl != "">
                  <a href="${bannerDto.clickUrl}" target="_blank">
             </#if>

			<img src="<#if bannerDto.url?? && bannerDto.url != ""> ${bannerDto.url} </#if>"  <#if bannerDto.alt?? && bannerDto.alt != ""> alt="${bannerDto.alt}"</#if> >

            <#if bannerDto.clickUrl?? && bannerDto.clickUrl != "">
                </a>
            </#if>

			</div>
			<div class="position">
				<div class="container">当前位置：
					<#if firstCategoryModuleDto?? && secondCategoryProjectDto??>
                        |<a href="${baseUrl}/serviceCase.html">服务案例</a>
                        |<a href="${baseUrl}/serviceCase/${firstCategoryModuleDto.code}/">${firstCategoryModuleDto.name}</a>
                        |<span>${secondCategoryProjectDto.name}</span>
                    <#else>
                        <#if firstCategoryModuleDto??>
                            |<a href="${baseUrl}/serviceCase.html">服务案例</a>
                            |<span>${firstCategoryModuleDto.name}</span>
                        </#if>
                    </#if>
				</div>
			</div>
			<div class="container main">
				<div class="case_main">
					<div class="nav_float_list clearfix">
					 <#list serviceCaseModuleList as serviceCaseModule>
						<div class="nav_float">
							<div class="case_nav <#if serviceCaseModule_index == firstCategoryIndex> active </#if>">
								<a href="${baseUrl}/serviceCase/${serviceCaseModule.code}/">${serviceCaseModule.name}</a><i class="layui-icon layui-icon-down layui-font-12"></i> </div>
							<ul class="case_nav_list">

							<#list serviceCaseModule.projectDtoList as projectDto>
								<li>
									<a href="${baseUrl}/serviceCase/${serviceCaseModule.code}/${projectDto.code}/" target="_blank">${projectDto.name}</a>
								</li>
							</#list>
							</ul>
						</div>

					</#list>

					</div>
					<ul class="case_list clearfix">

					<#list subProjectList as subProject>

						<li class="layui-col-xs6 layui-col-sm4 layui-col-md3">
							<a href="${baseUrl}/serviceCase/${subProject.firstCategory}/${subProject.secondCategory}/${subProject.number}.html" target="_blank">
								<div><img src="<#if subProject.url?? && subProject.url != "">${subProject.url}</#if>" <#if subProject.alt?? && subProject.alt != ""> alt="${subProject.alt}" </#if> /></div>
								<h2>${subProject.name}</h2>
							</a>
						</li>
					</#list>


					</ul>
					<div id="newPage" class="clearfix">
						<ul class="clearfix">
							<span>共${page.total}条</span>
							${pageHtml}
						</ul>
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


			$(".layui-icon-down").on("click", function(e) {
				$(this).parent().siblings('.case_nav_list').slideDown(100);
				//$(".case_nav_list").slideDown(100);
				$(this).parents().siblings().children('.case_nav_list').slideUp(100);
				$(document).one("click", function() {
					$(".case_nav_list").slideUp(100);
				});
				e.stopPropagation();

			});
		</script>
	</body>

</html>