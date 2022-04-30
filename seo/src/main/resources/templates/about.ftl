<!doctype html>
<html lang="zh-CN">

	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>关于我们</title>
		<link rel="stylesheet" href="${baseUrl}/css/style.css">
	</head>

	<body>
		<div class="nav">
			<div class="container clearfix">
				<div class="navimg"> <img  class="logo" src="${logoProject.url}" <#if logoProject.alt?? && logoProject.alt != ""> alt="${logoProject.alt}" </#if> /> </div>
				<div class="navul clearfix">
					<ul>
						  <li><a href="${baseUrl}/index.html">首页</a></li>
                          <li class="active"><a href="${baseUrl}/aboutUs.html">关于我们</a></li>
                          <li><a href="${baseUrl}/products.html">公司产品</a></li>
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
		<div id="about">
			<div class="bannerx">
			<#if aboutUsBannerProjectDto.clickUrl?? && aboutUsBannerProjectDto.clickUrl != "">
                <a href="${aboutUsBannerProjectDto.clickUrl}" target="_blank">
            </#if>
			<img  src="${aboutUsBannerProjectDto.url}" <#if aboutUsBannerProjectDto.alt?? && aboutUsBannerProjectDto.alt != ""> alt="${aboutUsBannerProjectDto.alt}" </#if> />
			<#if aboutUsBannerProjectDto.clickUrl?? && aboutUsBannerProjectDto.clickUrl != "">
                </a>
            </#if>
			</div>
			<div class="container main about">
				<div class="position">当前位置：
					<a href="${baseUrl}/index.html">首页</a>|<span>关于我们</span></div>
				<div class="goods_detail about_detail  clearfix">
					<div class="detail1 about1 clearfix">
						<div class="about_l"><img src="${companyIntroductionProjectDto.url}" <#if companyIntroductionProjectDto.alt?? && companyIntroductionProjectDto.alt != ""> alt="${companyIntroductionProjectDto.alt}" </#if>></div>
						${companyIntroductionProjectDto.content}
					</div>
					<div class="main_wh about2">
						<div class="main-title title_hot"><img src="${baseUrl}/images/title_one.PNG" alt="一站式服务"></div>
						<ul class="wh_list clearfix">

						<#list oneStopServiceProjectDtoList as oneStopServiceProject>
							<li class="layui-col-sm4 layui-col-md2">
								<div class="wh_tnt">

								<#if oneStopServiceProject.clickUrl?? && oneStopServiceProject.clickUrl != "">
                                    <a href="${oneStopServiceProject.clickUrl}" target="_blank">
                                <#else>
                                    <a href="javascript:;">
                                </#if>
										<div><img src="${oneStopServiceProject.url}" <#if oneStopServiceProject.alt?? && oneStopServiceProject.alt != ""> alt="${oneStopServiceProject.alt}" </#if> />
											<h3>${oneStopServiceProject.name}</h3>
										</div>
									</a>
								</div>
							</li>
						</#list>

						</ul>
					</div>
					<div class="layui-row detail1_3 about3 clearfix">
						<div class="main-title title_hot"><img src="${baseUrl}/images/title_yw.PNG" alt="业务范围"></div>
						<div class="layui-tab layui-tab-brief clearfix " lay-filter="docDemoTabBrief">
							<ul class="layui-tab-title layui-col-xs12 layui-col-md2  clearfix">
							<#list businessScopeProjectDtoList as businessScopeProject>
								<li <#if businessScopeProject_index ==0> class="layui-this" </#if>>${businessScopeProject.name}</li>
                            </#list>
							</ul>
							<div class="layui-tab-content layui-col-xs12 layui-col-md10">
							<#list businessScopeProjectDtoList as businessScopeProject>
								<div class="layui-tab-item <#if businessScopeProject_index ==0>layui-show</#if> clearfix">
									<div class="layui-col-xs12 layui-col-md7 detail1_3_l"><img src="${businessScopeProject.url}" <#if businessScopeProject.alt?? && businessScopeProject.alt != ""> alt="${businessScopeProject.alt}" </#if> /></div>
									<div class="layui-col-xs12 layui-col-md5 detail1_3_r">
										${businessScopeProject.content}
									</div>
								</div>
							</#list>

							</div>
						</div>
					</div>
					<div class="main_ys about4">
						<div class="container">
							<div class="main-title title_hot"><img src="${baseUrl}/images/title_ys.PNG" alt="我们的优势"></div>
							<p class="ys_p">苏州好奇数字科技有限公司自创立以来，坚持科技创新，是一家快速成长、锐意进取的互动多媒体公司。公司专注于更好的视觉效果、人机互动体验开发。为客户提供方案规划、硬件系统集成施工、交互软件开发调试、影视制作、售后维护、技术支持等服务。</p>
							<ul class="clearfix ys_list">
								<#list companyAdvantageProjectDtoList as companyAdvantageProject>
								<li>
									<div class="ys_tnt">
                                        <#if companyAdvantageProject.clickUrl?? && companyAdvantageProject.clickUrl != "">
                                            <a href="${companyAdvantageProject.clickUrl}" target="_blank">
                                        </#if>
										<div class="ys_img"><img src="${companyAdvantageProject.url}" <#if companyAdvantageProject.alt?? && companyAdvantageProject.alt != ""> alt="${companyAdvantageProject.alt}" </#if>></div>
										<h3>${companyAdvantageProject.name}</h3>
										<span>${companyAdvantageProject.describeMsg}</span>

										<#if companyAdvantageProject.clickUrl?? && companyAdvantageProject.clickUrl != "">
                                            <a href="${companyAdvantageProject.clickUrl}" target="_blank">
                                        </#if>

									</div>
								</li>
								</#list>

							</ul>
						</div>
					</div>
					<div class="about4">
						<div class="main-title title_video"><img src="${baseUrl}/images/title_ry.PNG" alt="荣誉资质"></div>
						<div id="container">
							<ul id="content">
                                <#list honoraryQualificationProjectDtoList as honoraryQualificationProject>

								<li>
									<#if honoraryQualificationProject.clickUrl?? && honoraryQualificationProject.clickUrl != "">
										<a href="${honoraryQualificationProject.clickUrl}"  target="_blank">
									<#else>
										<a href="javascript:;" >
									</#if>
									<img src="${honoraryQualificationProject.url}" <#if honoraryQualificationProject.alt?? && honoraryQualificationProject.alt != ""> alt="${honoraryQualificationProject.alt}" </#if> />
										<h3>${honoraryQualificationProject.name}</h3>
									</a>
                                </#list>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
		<#include "footer.ftl"/>
		<script type="text/javascript">
			layui.use(['carousel', 'form', 'element'], function() {
				var carousel = layui.carousel,
					form = layui.form,
					element = layui.element

			});

			/* window.onload比 $(function(){}) 加载的更晚一些，这样那些宽度的计算在Chrome中就可以准确计算了*/
			window.onload = function() {

				/*计算一个segment的宽度*/

				var segmentWidth = 0;
				$("#container #content li").each(function() {
					segmentWidth += $(this).outerWidth(true);
				});

				$("#container #content li").clone().appendTo($("#container #content"));

				run(30000);

				function run(interval) {
					$("#container #content").animate({
						"left": -segmentWidth
					}, interval, "linear", function() {
						$("#container #content").css("left", 0);
						run(30000);
					});
				}

				$("#container").mouseenter(function() {
					$("#container #content").stop();
				}).mouseleave(function() {
					var passedCourse = -parseInt($("#container #content").css("left"));
					var time = 30000 * (1 - passedCourse / segmentWidth);
					run(time);
				});
			};
		</script>

	</body>

</html>