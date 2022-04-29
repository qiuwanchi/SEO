<!doctype html>
<html lang="zh-CN">

	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>${searchWords}</title>
		<link rel="stylesheet" href="${baseUrl}/css/style.css">
	</head>

	<body style="background: #f9f9f9;">
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
		<div id="news" class="clearfix">
			<div class="container main newsx_main tag">
				<div class="position">当前位置：
					<a href="${baseUrl}/index.html">首页</a>|<span>${searchWords}</span></div>
				<div>
					<ul class="news_list">
					<#list newsSearchProjectDtoList as newsSearchProjectDto>
						<li class="clearfix">
							<div class="news_list_img"><img src="${newsSearchProjectDto.url}" <#if newsSearchProjectDto.alt?? && newsSearchProjectDto.alt != ""> alt="${newsSearchProjectDto.alt}" </#if>></div>
							<div class="news_list_text">
								<a href="${baseUrl}/news/${newsSearchProjectDto.moduleCode}/${newsSearchProjectDto.number}.html" target="_blank">
									<h1>${newsSearchProjectDto.name}</h1>
									<p>${newsSearchProjectDto.describeMsg}</p>
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