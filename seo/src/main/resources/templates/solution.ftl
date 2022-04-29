<!doctype html>
<html lang="zh-CN">

	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>解决方案</title>
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
                        <li class="active"><a href="${baseUrl}/solution.html">解决方案</a></li>
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

			<#if bannerProjectDto.clickUrl?? && bannerProjectDto.clickUrl != "">
                  <a href="${bannerProjectDto.clickUrl}" target="_blank">
             </#if>

            <img src="<#if bannerProjectDto.url?? && bannerProjectDto.url != ""> ${bannerProjectDto.url} </#if>"  <#if bannerProjectDto.alt?? && bannerProjectDto.alt != ""> alt="${bannerProjectDto.alt}"</#if> >

            <#if bannerProjectDto.clickUrl?? && bannerProjectDto.clickUrl != "">
                </a>
            </#if>

			</div>
			<div class="position">
				<div class="container">当前位置：
					<a href="index.html">首页</a>|<span>解决方案</span></div>
			</div>
			<div class="container main">
				<div class="newsx_main case_main">
					<div class="main-title title_hot title_jj"><img src="${baseUrl}/images/title_jj.PNG" alt="解决方案"></div>
					<ul class="case_list clearfix">
                        <#list solutionCaseProjectDtoList as solutionCaseProjectDto>
                            <li class="layui-col-xs6 layui-col-sm4 layui-col-md3">
                                <a href="${baseUrl}/solution/${solutionCaseProjectDto.number}.html" target="_blank">
                                    <div><img src="${solutionCaseProjectDto.url}" alt="${solutionCaseProjectDto.alt}"></div>
                                    <h2>${solutionCaseProjectDto.name}</h2>
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

			$(".case_nav").on("click", function(e) {
				$(this).siblings('.case_nav_list').slideDown(100);
				//$(".case_nav_list").slideDown(100);
				$(this).parent().siblings().children('.case_nav_list').slideUp(100);
				$(document).one("click", function() {
					$(".case_nav_list").slideUp(100);
				});
				e.stopPropagation();

			});
		</script>
	</body>

</html>