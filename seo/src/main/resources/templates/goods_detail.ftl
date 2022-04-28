<!doctype html>
<html lang="zh-CN">

	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>公司产品</title>
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
			<div class="container main goodsx">
				<div class="position">当前位置：
                    <a href="${baseUrl}/index.html">首页</a>
                    |<a href="${baseUrl}/products.html">公司产品</a>
                    |<a href="${baseUrl}/products/${currentProjectDto.moduleCode}/">${currentProjectDto.moduleName}</a>
                    |<span>${currentProjectDto.name}</span>
				</div>

				<div class="goods_detail clearfix">
					<div class="detail1 clearfix">
						<div class="layui-col-xs12 layui-col-md5 detail1_l"><img  src="${currentProjectDto.url}" <#if currentProjectDto.alt?? && currentProjectDto.alt != ""> alt="${currentProjectDto.alt}" </#if> /></div>
						<div class="layui-col-xs12 layui-col-md7 detail1_r">
							${currentProjectDto.content}
							<div class="getPrice">
								  <#if currentProjectDto.clickUrl?? && currentProjectDto.clickUrl !="">
                                    <a href="${currentProjectDto.clickUrl}" target="_blank">获取详细报价</a>
                                  <#else>
                                    <a href="javascript:;">获取详细报价</a>
                                  </#if>
							</div>
						</div>
					</div>
					<div class="detail2 clearfix">
						<div class="detail2_text">
							<h2>${currentProjectDto.name}的特点</h2>
							<span>
							<#if currentProjectDto.describeMsg?? && currentProjectDto.describeMsg !="">
							    ${currentProjectDto.describeMsg}
							</#if>
							</span>
						</div>
						<ul class="detail2_ul clearfix">
						<#list systemCharacteristicsProjectDtoList as systemCharacteristics>
							<li class="clearfix">
								  <#if systemCharacteristics.clickUrl?? && systemCharacteristics.clickUrl !="">
                                    <a href="${systemCharacteristics.clickUrl}" target="_blank">
                                  <#else>
                                    <a href="javascript:;">
                                  </#if>
                                    <img src="${systemCharacteristics.url}" <#if systemCharacteristics.alt?? && systemCharacteristics.alt != ""> alt="${systemCharacteristics.alt}" </#if>>
                                    </a>

						            <#if systemCharacteristics_index+1 != systemCharacteristicsProjectDtoList?size >
                                        <span><img src="${baseUrl}/images/arrow.png"></span>
                                    </#if>

							</li>
							</#list>

						</ul>
					</div>
					<div class="layui-row detail1_3 clearfix">
						<div class="layui-tab layui-tab-brief clearfix " lay-filter="docDemoTabBrief">
							<ul class="layui-tab-title layui-col-xs12 layui-col-md2  clearfix">
								<#list applicationScenarioSubProjectDtoList as applicationScenario>
                                    <li <#if applicationScenario_index == 0> class="layui-this" </#if>>${applicationScenario.name}</li>
                                </#list>
							</ul>
							<div class="layui-tab-content layui-col-xs12 layui-col-md10">
							<#list applicationScenarioSubProjectDtoList as applicationScenario>
								<div class="layui-tab-item <#if applicationScenario_index == 0>layui-show</#if> clearfix">
									<div class="layui-col-xs12 layui-col-md7 detail1_3_l"><img src="${applicationScenario.url}" <#if applicationScenario.alt?? && applicationScenario.alt != ""> alt="${applicationScenario.alt}" </#if>></div>
									<div class="layui-col-xs12 layui-col-md5 detail1_3_r">
										  <#if applicationScenario.content?? && applicationScenario.content !="">
                                            ${applicationScenario.content}
                                          <#else>
                                            <p></p>
                                          </#if>

										<div class="more">
                                            <#if applicationScenario.clickUrl?? && applicationScenario.clickUrl !="">
                                                <a href="${applicationScenario.clickUrl}" target="_blank">查看更多 》</a>
                                            <#else>
                                                <a href="javascript:;">查看更多 》</a>
                                            </#if>
										</div>

									</div>
								</div>
							</#list>

							</div>
						</div>
					</div>

					<div class="main_video detail4">
						<div class="main-title title_video"><img src="${baseUrl}/images/title_video.PNG" alt="视频案例"></div>
						<ul class=" clearfix pic_list video">

						<#list recommendServiceCaseVideoList as recommendServiceCaseVideo>
							<li class="layui-col-xs12  layui-col-sm6 layui-col-md3">
								<a target="_blank" href="${baseUrl}/serviceCase/${recommendServiceCaseVideo.firstCategory}/${recommendServiceCaseVideo.secondCategory}/${recommendServiceCaseVideo.number}.html">
									<div class="main_img"><i class="layui-icon layui-icon-play"></i><img src="${recommendServiceCaseVideo.url}" <#if recommendServiceCaseVideo.alt?? && recommendServiceCaseVideo.alt != ""> alt="${recommendServiceCaseVideo.alt}" </#if>></div>
									<div class="video_padding">
										<h2>${recommendServiceCaseVideo.name}</h2>
									</div>
								</a>
							</li>

						</#list>

						</ul>
						<div class="more">
							<a href="${baseUrl}/serviceCase.html" target="_blank">查看更多 》</a>
						</div>
					</div>
					<div class="detail5">
						<h2>获取项目方案/报价</h2>
						<span>Get project proposal / quotation</span>
						<div class="goods_detail_input">
							<form class="layui-form" action="">
								<div class="clearfix">
									<div class="layui-input-inline layui-col-xs12 layui-col-md3">
										<input type="text" name="name" lay-verify="title" placeholder="请输入姓名" class="layui-input">
									</div>
									<div class="layui-input-inline layui-col-xs12 layui-col-md3">
										<input type="text" name="tel" lay-verify="title" placeholder="请输入电话" class="layui-input">
									</div>
									<div class="layui-input-inline layui-col-xs12 layui-col-md6">
										<input type="text" name="xq" lay-verify="title" placeholder="请输入您的需求" class="layui-input">
									</div>
								</div>
								<div style="width: 100%; text-align: center;">
									<button type="submit" class="layui-btn" lay-submit="">立即获取</button>
								</div>
								<form>
						</div>
					</div>
					<div class="detail6 clearfix">
						<div class="layui-col-xs12 layui-col-md4">
							<div class="rightList">
								<h3 class="clearfix"><span></span><strong>常见问题</strong></h3>
								<ul>

								<#list recommendNewsFqaProjectList as recommendNewsFqaProject>
									<li>
										<a href="${baseUrl}/news/${recommendNewsFqaProject.moduleCode}/${recommendNewsFqaProject.number}.html" target="_blank">●&nbsp;${recommendNewsFqaProject.name}</a>
									</li>
								</#list>
								</ul>
							</div>
						</div>
						<div class="layui-col-xs12 layui-col-md4">
							<div class="rightList">
								<h3 class="clearfix"><span></span><strong>科普知识</strong></h3>
								<ul>
									<#list recommendNewsPopularScienceKnowledgeProjectList as recommendNewsPopularScienceKnowledgeProject>
                                        <li>
                                            <a href="${baseUrl}/news/${recommendNewsPopularScienceKnowledgeProject.moduleCode}/${recommendNewsPopularScienceKnowledgeProject.number}.html" target="_blank">●&nbsp;${recommendNewsPopularScienceKnowledgeProject.name}</a>
                                        </li>
                                    </#list>
								</ul>
							</div>
						</div>
						<div class="layui-col-xs12 layui-col-md4">
							<div class="rightList">
								<h3 class="clearfix"><span></span><strong>最近更新</strong></h3>
								<ul>
									<#list recentUpdatesNewsProjectList as recentUpdatesNewsProject>
                                        <li>
                                            <a href="${baseUrl}/news/${recentUpdatesNewsProject.moduleCode}/${recentUpdatesNewsProject.number}.html" target="_blank">●&nbsp;${recentUpdatesNewsProject.name}</a>
                                        </li>
                                    </#list>
								</ul>
							</div>
						</div>
					</div>
					<div class="detail4 detail7">
						<div class="main-title title_video"><img src="${baseUrl}/images/xgtj.PNG" alt="相关推荐"></div>
						<ul class=" clearfix pic_list">
						<#list recommendProductsProjectDtoList as recommendProductsProjectDto>
							<li class="layui-col-xs12  layui-col-sm6 layui-col-md3">
								<a href="${baseUrl}/products/${recommendProductsProjectDto.moduleCode}/${recommendProductsProjectDto.number}.html" target="_blank">
									<div class="main_img"><img src="${recommendProductsProjectDto.url}" <#if recommendProductsProjectDto.alt?? && recommendProductsProjectDto.alt != ""> alt="${recommendProductsProjectDto.alt}" </#if>></div>
									<div class="video_padding">
										<h2>${recommendProductsProjectDto.name}</h2>
									</div>
								</a>
							</li>
						</#list>

						</ul>

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
		</script>
	</body>

</html>