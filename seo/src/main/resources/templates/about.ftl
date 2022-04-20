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
<div class="c-nav" id="c-nav">
  <div class="container navFlex">
    <div class="flexItem"> <img  class="logo" src="${logoProject.url}" <#if logoProject.alt?? && logoProject.alt != ""> alt="${logoProject.alt}" </#if> /> </div>
    <div class="flexItem clearfix">
      <ul>
		<li><a href="${baseUrl}/index.html">首页</a></li>
        <li class="active"><a href="${baseUrl}/aboutUs.html">关于我们</a></li>
        <li><a href="${baseUrl}/products.html">公司产品</a></li>
        <li><a href="${baseUrl}/serviceCase.html">服务案例</a></li>
		<li><a href="solution.html">解决方案</a></li>
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
<div id="about">
  <div class="bannerx"> <img  src="${companyIntroductionProjectDto.url}" <#if companyIntroductionProjectDto.alt?? && companyIntroductionProjectDto.alt != ""> alt="${companyIntroductionProjectDto.alt}" </#if> /> </div>
  <div class="container main about">
    <div class="position">当前位置：<a href="index.html">首页</a>|<span>关于我们</span></div>
    <div class="goods_detail about_detail  clearfix">
      <div class="detail1 about1 clearfix">
        <div class="about_l"><img src="images/about.jpg"></div>
        <p>展厅智能中控系统又叫中央控制系统、智能多媒体中控系统等，由无线控制面板（一般是IPAD）、控制主机以及从主机到各种设备的连线，当用户触碰IPAD上相应设备的按钮操作时，触摸屏会向中控主机传送触发指令，中控主机受到指令，向对应的受控设备发送控制信号，受控设备就会响应该操作。</p>
        <p>展厅智能中控系统又叫中央控制系统、智能多媒体中控系统等，由无线控制面板（一般是IPAD）、控制主机以及从主机到各种设备的连线，当用户触碰IPAD上相应设备的按钮操作时，触摸屏会向中控主机传送触发指令，中控主机受到指令，向对应的受控设备发送控制信号，受控设备就会响应该操作。</p>
        <p>展厅智能中控系统又叫中央控制系统、智能多媒体中控系统等，由无线控制面板（一般是IPAD）、控制主机以及从主机到各种设备的连线，当用户触碰IPAD上相应设备的按钮操作时，触摸屏会向中控主机传送触发指令，中控主机受到指令，向对应的受控设备发送控制信号，受控设备就会响应该操作。</p>
        <p>展厅智能中控系统又叫中央控制系统、智能多媒体中控系统等，由无线控制面板（一般是IPAD）、控制主机以及从主机到各种设备的连线，当用户触碰IPAD上相应设备的按钮操作时，触摸屏会向中控主机传送触发指令，中控主机受到指令，向对应的受控设备发送控制信号，受控设备就会响应该操作。</p>
      </div>
      <div class="main_wh about2">
        <div class="main-title title_hot"><img src="images/title_one.PNG"></div>
        <ul class="wh_list clearfix">
		<#list oneStopServiceProjectDtoList as oneStopServiceProject>
          <li class="layui-col-sm4 layui-col-md2">
            <div class="wh_tnt"> <a href="javascript:;">
              <div><img src="${oneStopServiceProject.url}">
                <h3>${oneStopServiceProject.name}</h3>
              </div>
              </a> </div>
          </li>
		</#list>
        </ul>
      </div>
      <div class="layui-row detail1_3 about3 clearfix">
        <div class="main-title title_hot"><img src="images/title_yw.PNG"></div>
        <div class="layui-tab layui-tab-brief clearfix " lay-filter="docDemoTabBrief">
          <ul class="layui-tab-title layui-col-xs12 layui-col-md2  clearfix">
            <#list businessScopeProjectDtoList as businessScopeProject>
              <li <#if businessScopeProject_index ==0> class="layui-this" </#if>>${businessScopeProject.name}</li>
            </#list>
          </ul>
          <div class="layui-tab-content layui-col-xs12 layui-col-md10">
		  <#list businessScopeProjectDtoList as businessScopeProject>
            <div class="layui-tab-item <#if businessScopeProject_index ==0>layui-show</#if> clearfix">
              <div class="layui-col-xs12 layui-col-md7 detail1_3_l"><img src="${businessScopeProject.url}"></div>
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
          <div class="main-title title_hot"><img src="images/title_ys.PNG"></div>
          <p class="ys_p">苏州好奇数字科技有限公司自创立以来，坚持科技创新，是一家快速成长、锐意进取的互动多媒体公司。公司专注于更好的视觉效果、人机互动体验开发。为客户提供方案规划、硬件系统集成施工、交互软件开发调试、影视制作、售后维护、技术支持等服务。</p>
          <ul class="clearfix ys_list">
		  <#list companyAdvantageProjectDtoList as companyAdvantageProject>
            <li>
              <div class="ys_tnt">
                <div class="ys_img"><img src="${companyAdvantageProject.url}" <#if companyAdvantageProject.alt?? && companyAdvantageProject.alt != ""> alt="${companyAdvantageProject.alt}" </#if>></div>
                <h3>${companyAdvantageProject.name}</h3>
                <span>${companyAdvantageProject.describeMsg}</span> </div>
            </li>
		  </#list>
			
          </ul>
        </div>
      </div>
      <div class="about4">
        <div class="main-title title_video"><img src="images/title_ry.PNG"></div>
        <div id="container">
          <ul id="content">
		  <#list honoraryQualificationMap?keys as k>
                <li>
				<#list honoraryQualificationMap[k] as honoraryQualificationProject>
				<a href="javasript:;" target="_blank">
				<img src="${honoraryQualificationProject.url}" alt="" />
				<h3>${honoraryQualificationProject.name}</h3>
				</a>
				</#list>

				</#list>
            </ul>
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

 /* window.onload比 $(function(){}) 加载的更晚一些，这样那些宽度的计算在Chrome中就可以准确计算了*/
    window.onload = function () {

        /*计算一个segment的宽度*/

        var segmentWidth = 0;
        $("#container #content li").each(function () {
            segmentWidth += $(this).outerWidth(true);
        });

        $("#container #content li").clone().appendTo($("#container #content"));

        run(20000);

        function run(interval) {
            $("#container #content").animate({ "left": -segmentWidth }, interval, "linear", function () {
                $("#container #content").css("left", 0);
                run(20000);
            });
        }

        $("#container").mouseenter(function () {
            $("#container #content").stop();
        }).mouseleave(function () {
            var passedCourse = -parseInt($("#container #content").css("left"));
            var time = 20000 * (1 - passedCourse / segmentWidth);
            run(time);
        });
    };

</script>

</body>
</html>