<!doctype html>
<html lang="zh-CN">

	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>${currentSubProject.title}</title>
        <meta name="keywords" content="${currentSubProject.keywords}">
        <meta name="description" content="${currentSubProject.description}">
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
		<div id="news">
			<div class="bannerx">
			<#if banner.clickUrl?? && banner.clickUrl != "">
                 <a href="${banner.clickUrl}" target="_blank">
            </#if>
            <img src="<#if banner.url?? && banner.url != "">${banner.url}</#if>"  <#if banner.alt?? && banner.alt != ""> alt="${banner.alt}" </#if> />
            <#if banner.clickUrl?? && banner.clickUrl != "">
                  </a>
            </#if>

			</div>
			<div class="container">
				<div class="newsx_detail clearfix">
					<div class="layui-col-xs12 layui-col-md9 newsx_detail_l">
						<div class="position">当前位置：
						    <a href="${baseUrl}/index.html">首页</a>
						    |<a href="${baseUrl}/serviceCase.html">服务案例</a>
						    |<a href="${baseUrl}/serviceCase/${module.code}/">${module.name}</a>
						    |<a href="${baseUrl}/serviceCase/${module.code}/${project.code}/">${project.name}</a>
						    |<span>${currentSubProject.name}</span>
						</div>

						<div style="padding: 30px;">
							<h1>${currentSubProject.name}</h1>
							<div class="news_data"><span>发布日期：${currentSubProject.createTime?string("yyyy-MM-dd")}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;作者：${currentSubProject.createBy}</span></div>
							<#list seoVideoDtoList as seoVideoDto>
							<div id="dplayer${seoVideoDto_index}"> </div>
							</#list>
							<#if currentSubProject.content??>
                                ${currentSubProject.content}
                            <#else>
                                <p></p>
                            </#if>
						</div>
						<div class="hot_tag clearfix"> <span>标签：</span>
                            <#list keywordsList as keywords>
                                <strong><a href="${baseUrl}/solutionServiceCase/search?keyword=${keywords}" target="_blank">${keywords}</a></strong>
                            </#list>
						</div>
						<div class="share clearfix"> <span>分享到：</span>
							<div style="float: left;"><img src="${baseUrl}/images/share.jpg"></div>
						</div>
						<div class="article clearfix">

                            <#if preSubProjectDto??>
                                <div class="article_left">上一篇：
                                    <a href="${baseUrl}/serviceCase/${preSubProjectDto.firstCategory}/${preSubProjectDto.secondCategory}/${preSubProjectDto.number}.html" target="_blank">${preSubProjectDto.name}</a>
                                </div>
                            <#else>
                                <div class="article_left">
                                <a> </a>

                                </div>
                            </#if>

                            <#if nextSubProjectDto??>
                                <div class="article_right">下一篇：
                                    <a href="${baseUrl}/serviceCase/${nextSubProjectDto.firstCategory}/${nextSubProjectDto.secondCategory}/${nextSubProjectDto.number}.html" target="_blank">${nextSubProjectDto.name}</a>
                                </div>
							<#else>
							    <div class="article_right">
							    <a> </a>

                                </div>
                            </#if>

						</div>
					</div>
					<div class="layui-col-xs12 layui-col-md3 newsx_detail_r">
						<div class="rightList">
							<h3 class="clearfix"><span></span><strong>相关推荐</strong></h3>
							<ul>
								 <#list recommendSubProjectDtoList as  recommendSubProjectDto>
                                    <li><a href="${baseUrl}/serviceCase/${recommendSubProjectDto.firstCategory}/${recommendSubProjectDto.secondCategory}/${recommendSubProjectDto.number}.html" target="_blank">●&nbsp;${recommendSubProjectDto.name}</a></li>
                                 </#list>
							</ul>
						</div>
						<div class="rightList">
							<h3 class="clearfix"><span></span><strong>热门问答</strong></h3>
							<ul>
								<#list recommendNewsFqaProjectList as recommendNewsFqaProject>
                                    <li><a href="${baseUrl}/news/${recommendNewsFqaProject.moduleCode}/${recommendNewsFqaProject.number}.html" target="_blank">●&nbsp;${recommendNewsFqaProject.name}</a></li>
                                </#list>
							</ul>
						</div>
						<div class="rightList">
							<h3 class="clearfix"><span></span><strong>热门标签</strong></h3>
							<div class="bq">
                                <#list keywordsDtoList as keywordsDto>
                                      <span><a href="${baseUrl}/search?keyword=${keywordsDto.words}" target="_blank">${keywordsDto.words}</a></span>
                                </#list>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<#include "footer.ftl"/>

	<script type="text/javascript" src="${baseUrl}/js/DPlayer.min.js"></script>
	<script type="text/javascript">
			var seoVideoUrlArray = ${seoVideoUrlArray};
			//播放器控制
			for(var i = 0;i < seoVideoUrlArray.length; i++){
				new DPlayer({
					container: document.getElementById('dplayer' + i),
					video: {
						url:  seoVideoUrlArray[i],
					},
					autoplay:true,
				});
			}
	</script>
	</body>

</html>