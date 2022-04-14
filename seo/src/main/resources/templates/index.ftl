<!doctype html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>首页</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="c-nav" id="c-nav">
  <div class="container navFlex">
    <div class="flexItem"> <img  class="logo" src="${logo.url}" /> </div>
    <div class="flexItem clearfix">
      <ul>
        <li class="active"><a href="${baseUrl}/index.html">首页</a></li>
        <li><a href="${baseUrl}">关于我们</a></li>
        <li><a href="${baseUrl}/products.html">公司产品</a></li>
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
<div class="layui-carousel banner" id="test1" lay-filter="test1">
    <div carousel-item="">
      <#list bannerList as banner>
      	  <div><img src="${banner.url}"></div>
      	  </#list>
    </div>
  </div>
<div class="index_main">
<!--公司产品-->
<div class="layui-row container goods_main">
  <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
	<div class="main-title  title_hot"><img src="images/title_hot.png"></div>
    <ul class="layui-tab-title clearfix">

    <#list productModuleList as productModule>
        <li <#if productModule_index ==0> class="layui-this"  </#if> >${productModule.name}</li>
    </#list>

    </ul>
    <div class="layui-tab-content" style="padding-top: 10px;">

    <#list productModuleList as productModule>
      <div class="layui-tab-item <#if productModule_index ==0>layui-show </#if> ">
		  <ul class=" clearfix pic_list">

		    <#list productModule.projectDtoList as project>
			  <li class="layui-col-xs12 layui-col-sm6 layui-col-md3">
				<a href="javascript:;">
				<div class="main_img"><img src="${project.url}"></div>
				<h1>${project.name}</h1>
				</a>
			  </li>
			</#list>

		  </ul>
		  <div class="more"><a href="#">查看更多 》</a></div>
	  </div>
    </#list>

    </div>
  </div>
</div>

<!--多媒体展厅项目案例-->
<div style="background: #f7f7f7; padding-top: 20px; margin-top: 20px;">
<div class="layui-row container">
  <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
	<div class="main-title"><img src="images/title.PNG"></div>
    <ul class="layui-tab-title">
      <#list showRoomModuleList as showRoomModule>
         <li <#if showRoomModule_index ==0> class="layui-this"  </#if>>${showRoomModule.name}</li>
      </#list>
    </ul>
    <div class="layui-tab-content">
    <#list showRoomModuleList as showRoomModule>
          <div class="layui-tab-item <#if showRoomModule_index ==0>layui-show" </#if>">
    		  <ul class=" clearfix pic_list">
    		  <#list showRoomModule.projectDtoList as project>

    			  <li class="layui-col-xs12 layui-col-sm6 layui-col-md3">
    				<a href="javascript:;">
    				<div class="main_img"><img src="${project.url}"></div>
    				<span>0${project_index + 1}</span>
    				<h5>${project.name}</h5>
    				<p>${project.describeMsg}</p>
    				</a>
    			  </li>
    			  </#list>
    		  </ul>
    		  <div class="more"><a href="#">查看更多 》</a></div>
    	  </div>
    </#list>
  </div>

  </div>
</div>
</div>
<!-- 视频案例-->
<div class="container">
	<div class="main_video">
		<div class="main-title title_video"><img src="images/title_video.PNG"></div>
		<ul class=" clearfix pic_list">
			 <#list videoCaseModuleProjectList as project>
                <li class="layui-col-xs12 layui-col-md4">
                    <a href="#">
                    <div class="main_img"><img src="${project.url}"></div>
                    <div class="video_padding">
                    <h5>${project.name}</h5>
                    <p>${project.describeMsg}</p>
                    </div>
                    </a>
                </li>
            </#list>
		</ul>
		<div class="more"><a href="#">查看更多 》</a></div>
	</div>
</div>
<!-- 我们的优势-->
<div class="main_ys">
	<div class="container">
		<div class="main-title title_hot"><img src="images/title_ys.PNG"></div>
		<p class="ys_p">苏州好奇数字科技有限公司自创立以来，坚持科技创新，是一家快速成长、锐意进取的互动多媒体公司。公司专注于更好的视觉效果、人机互动体验开发。为客户提供方案规划、硬件系统集成施工、交互软件开发调试、影视制作、售后维护、技术支持等服务。</p>
	    <ul class="clearfix ys_list">
			<#list companyAdvantageModuleProjectList as project>
                <li>
                  <div class="ys_tnt">
                    <div class="ys_img"><img src="${project.url}"></div>
                    <h3>${project.name}</h3>
                    <span>${project.describeMsg}</span>
                  </div>
                </li>
             </#list>
		</ul>
		<div class="more"><a href="#">查看更多 》</a></div>
	</div>
</div>
<!-- 解决方案案例-->
<div class="container">
  <div class="main_project">
	<div class="main-title title_hot"><img src="images/title_project.PNG"></div>
	<h4>为客户提供方案规划、硬件系统集成施工、交互软件开发调试、影视制作、售后维护、技术支持等服务。</h4>
	<ul class=" clearfix pic_list">
	 <#list solutionCaseModuleProjectList as project>
          <li class="layui-col-xs12 layui-col-sm6 layui-col-md3">
            <a href="javascript:;">
            <div class="main_img"><img src="${project.url}"></div>
            <h5>${project.name}</h5>
            <p>${project.describeMsg}</p>
            </a>
          </li>
      </#list>
	</ul>
	<div class="more"><a href="javascript:;">查看更多 》</a></div>
  </div>
</div>
<!-- 维护服务-->
<div class="main_wh">
	<div class="container">
		<div class="main-title title_hot"><img src="images/title_wh.PNG"></div>
		<h2>更快、更好、更贴心</h2>
		<ul class="wh_list clearfix">
			<#list maintenanceServicesModuleProjectList as project>
                <li class="layui-col-sm6 layui-col-md3">
                    <div class="wh_tnt">
                        <a href="javascript:;">
                            <div><img src="${project.url}"><h3>${project.name}</h3></div>
                        </a>
                    </div>
                </li>
            </#list>
		</ul>
	</div>
</div>
<!-- 企业资讯-->
<div class="container">
	<div class="main_news">
		<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
			<div class="main-title title_hot"><img src="images/title_news.PNG"></div>
		  <ul class="layui-tab-title">
		   <#list newsModuleList as newsModule>
		        <li <#if newsModule_index ==0> class="layui-this"  </#if>>${newsModule.name}</li>
		   </#list>

		  </ul>
		  <div class="layui-tab-content ">

		  <#list newsModuleList as newsModule>

		    <div class="layui-tab-item <#if newsModule_index ==0>layui-show</#if> ">
			   <div class="main_news_img layui-col-xs12 layui-col-sm6 layui-col-md6"><img src="${newsModule.url}"></div>
			   <div class="news_list layui-col-xs12 layui-col-sm6 layui-col-md6">
                   <#list newsModule.projectDtoList as project>
				   <div class="news_list_li clearfix">
					   <div class="rl"><span>${project.day}</span><p>${project.years}</p></div>
					   <div class="text">
						   <a href="javascript:;">
						   <h3>${project.name}</h3>
						   <h4>${project.describeMsg}</h4>
					       </a>
					   </div>
				   </div>
				   </#list>

			   </div>
			   <div class="clearfix"></div>
			   <div class="more"><a href="javascript:;">查看更多 》</a></div>
			</div>
			</#list>



      </div>
		</div>
	</div>
</div>
</div>
<#include "footer.ftl"/>
<script type="text/javascript">

layui.use(['carousel', 'form','element'], function(){
  var carousel = layui.carousel
  ,form = layui.form
  ,element = layui.element

  //常规轮播
  carousel.render({
    elem: '#test1'
    ,arrow: 'always'
  });




});
</script>
</body>
</html>