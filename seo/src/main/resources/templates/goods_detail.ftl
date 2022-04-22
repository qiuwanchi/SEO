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
<div class="c-nav" id="c-nav">
  <div class="container navFlex">
    <div class="flexItem"> <img  class="logo" src="${logoProject.url}" <#if logoProject.alt?? && logoProject.alt != ""> alt="${logoProject.alt}" </#if> /> </div>
    <div class="flexItem clearfix">
      <ul>
		  <li><a href="${baseUrl}/index.html">首页</a></li>
		  <li><a href="${baseUrl}/aboutUs.html">关于我们</a></li>
		  <li class="active"><a href="${baseUrl}/products.html">公司产品</a></li>
		  <li><a href="${baseUrl}/serviceCase.html">服务案例</a></li>
		  <li><a href="${baseUrl}/solution.html">解决方案</a></li>
		  <li><a href="${baseUrl}/news.html">新闻资讯</a></li>
		  <li><a href="${baseUrl}/contactUs.html">联系我们</a></li>
      </ul>
      <div class="kftel">
        <h2>400-0000-033</h2>
        <span>客服服务热线</span>
      </div>
    </div>
  </div>
</div>
<div id="case">
<div class="bannerx"> <img  src="${currentProjectDto.url}" <#if currentProjectDto.alt?? && currentProjectDto.alt != ""> alt="${currentProjectDto.alt}" </#if> /> </div>
<div class="container main goodsx">
<div class="position">当前位置：<a href="${baseUrl}/index.html">首页</a>|<a href="${baseUrl}/products.html">公司产品</a>|<a href="${baseUrl}/products/${currentProjectDto.moduleCode}/">${currentProjectDto.moduleName}</a>|<span>${currentProjectDto.name}</span></div>
<div class="goods_detail clearfix">
<div class="detail1 clearfix">
  <div class="layui-col-xs12 layui-col-md5 detail1_l"><img src="${seoImageDto.url}" <#if seoImageDto.alt?? && seoImageDto.alt != ""> alt="${seoImageDto.alt}" </#if> /></div>
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
    <h2>展厅智能中控系统特点</h2>
    <span>Features of intelligent central control system in exhibition hall</span> </div>
  <ul class="detail2_ul clearfix">
	<#list systemCharacteristicsProjectDtoList as systemCharacteristics>
    <li class="clearfix">
      <#if systemCharacteristics.clickUrl?? && systemCharacteristics.clickUrl !="">
	    <a href="${systemCharacteristics.clickUrl}" target="_blank">
      <#else>
        <a href="javascript:;">
      </#if>

      <img src="${systemCharacteristics.url}" <#if systemCharacteristics.alt?? && systemCharacteristics.alt != ""> alt="${systemCharacteristics.alt}" </#if>> </a>
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
  <div class="main-title title_video"><img src="${baseUrl}/images/title_video.PNG"></div>
  <ul class=" clearfix pic_list">
    <li class="layui-col-xs12  layui-col-sm6 layui-col-md3"> <a href="#">
      <div class="main_img"><img src="images/pic1.jpg"></div>
      <div class="video_padding">
        <h2>展厅智能中控系统又叫中央控制系统</h2>
      </div>
      </a> </li>
    <li class="layui-col-xs12  layui-col-sm6 layui-col-md3"> <a href="#">
      <div class="main_img"><img src="images/pic2.jpg"></div>
      <div class="video_padding">
        <h2>展厅智能中控系统又叫中央控制系统</h2>
      </div>
      </a> </li>
    <li class="layui-col-xs12  layui-col-sm6 layui-col-md3"> <a href="#">
      <div class="main_img"><img src="images/pic3.jpg"></div>
      <div class="video_padding">
        <h2>展厅智能中控系统又叫中央控制系统</h2>
      </div>
      </a> </li>
    <li class="layui-col-xs12  layui-col-sm6 layui-col-md3"> <a href="#">
      <div class="main_img"><img src="images/pic2.jpg"></div>
      <div class="video_padding">
        <h2>展厅智能中控系统又叫中央控制系统</h2>
      </div>
      </a> </li>
    <li class="layui-col-xs12  layui-col-sm6 layui-col-md3"> <a href="#">
      <div class="main_img"><img src="images/pic3.jpg"></div>
      <div class="video_padding">
        <h2>展厅智能中控系统又叫中央控制系统</h2>
      </div>
      </a> </li>
    <li class="layui-col-xs12  layui-col-sm6 layui-col-md3"> <a href="#">
      <div class="main_img"><img src="images/pic2.jpg"></div>
      <div class="video_padding">
        <h2>展厅智能中控系统又叫中央控制系统</h2>
      </div>
      </a> </li>
    <li class="layui-col-xs12  layui-col-sm6 layui-col-md3"> <a href="#">
      <div class="main_img"><img src="images/pic3.jpg"></div>
      <div class="video_padding">
        <h2>展厅智能中控系统又叫中央控制系统</h2>
      </div>
      </a> </li>
    <li class="layui-col-xs12  layui-col-sm6 layui-col-md3"> <a href="#">
      <div class="main_img"><img src="images/pic2.jpg"></div>
      <div class="video_padding">
        <h2>展厅智能中控系统又叫中央控制系统</h2>
      </div>
      </a> </li>
  </ul>
  <div class="more"><a href="#">查看更多 》</a></div>
</div>
<div class="detail5">
<h2>获取项目方案/报价</h2>
<span>Get project proposal / quotation</span>
<div class="goods_detail_input">
<form class="layui-form" action="">
<div class="clearfix">
  <div class="layui-input-inline layui-col-xs12 layui-col-md3">
    <input type="text" name="name" lay-verify="title"  placeholder="请输入姓名" class="layui-input">
  </div>
  <div class="layui-input-inline layui-col-xs12 layui-col-md3">
    <input type="text" name="tel" lay-verify="title"  placeholder="请输入电话" class="layui-input">
  </div>
  <div class="layui-input-inline layui-col-xs12 layui-col-md6">
    <input type="text" name="xq" lay-verify="title"  placeholder="请输入您的需求" class="layui-input">
  </div>
</div>
<div style="width: 100%; text-align: center;">
  <button type="submit" class="layui-btn" lay-submit="">立即获取</button>
</div>
<form>
</div>
</div>
<div class="detail6">
  <div class="layui-col-xs12 layui-col-md4">
    <div class="rightList">
      <h3 class="clearfix"><span></span><strong>常见问题</strong></h3>
      <ul>
        <li><a href="javascrpt:;">●&nbsp;近期全国本土疫情多点频发</a></li>
        <li><a href="javascrpt:;">●&nbsp;打造乡村振兴的齐鲁样板</a></li>
        <li><a href="javascrpt:;">●&nbsp;交通运输部召开会议，部署货运物流保通保畅工作</a></li>
        <li><a href="javascrpt:;">●&nbsp;近期全国本土疫情多点频发</a></li>
        <li><a href="javascrpt:;">●&nbsp;共同的梦想，共同的未来</a></li>
        <li><a href="javascrpt:;">●&nbsp;近期全国本土疫情多点频发</a></li>
      </ul>
    </div>
  </div>
  <div class="layui-col-xs12 layui-col-md4">
    <div class="rightList">
      <h3 class="clearfix"><span></span><strong>科普知识</strong></h3>
      <ul>
        <li><a href="javascrpt:;">●&nbsp;近期全国本土疫情多点频发</a></li>
        <li><a href="javascrpt:;">●&nbsp;打造乡村振兴的齐鲁样板</a></li>
        <li><a href="javascrpt:;">●&nbsp;交通运输部召开会议，部署货运物流保通保畅工作</a></li>
        <li><a href="javascrpt:;">●&nbsp;近期全国本土疫情多点频发</a></li>
        <li><a href="javascrpt:;">●&nbsp;共同的梦想，共同的未来</a></li>
        <li><a href="javascrpt:;">●&nbsp;近期全国本土疫情多点频发</a></li>
      </ul>
    </div>
  </div>
  <div class="layui-col-xs12 layui-col-md4">
    <div class="rightList">
      <h3 class="clearfix"><span></span><strong>相关推荐</strong></h3>
      <ul>
        <li><a href="javascrpt:;">●&nbsp;近期全国本土疫情多点频发</a></li>
        <li><a href="javascrpt:;">●&nbsp;打造乡村振兴的齐鲁样板</a></li>
        <li><a href="javascrpt:;">●&nbsp;交通运输部召开会议，部署货运物流保通保畅工作</a></li>
        <li><a href="javascrpt:;">●&nbsp;近期全国本土疫情多点频发</a></li>
        <li><a href="javascrpt:;">●&nbsp;共同的梦想，共同的未来</a></li>
        <li><a href="javascrpt:;">●&nbsp;近期全国本土疫情多点频发</a></li>
      </ul>
    </div>
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

});
</script>
</body>
</html>