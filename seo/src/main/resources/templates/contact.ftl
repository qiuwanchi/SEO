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
		<link rel="stylesheet" href="${baseUrl}/css/font-awesome.min.css">
		<!--引用百度地图API-->
        <style type="text/css">
            html,body{margin:0;padding:0;}
            .iw_poi_title {color:#CC5522;font-size:14px;font-weight:bold;overflow:hidden;padding-right:13px;white-space:nowrap}
            .iw_poi_content {font:12px arial,sans-serif;overflow:visible;padding-top:4px;white-space:-moz-pre-wrap;word-wrap:break-word}
        </style>
        <script type="text/javascript" src="http://api.map.baidu.com/api?key=&v=1.1&services=true"></script>
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
                          <li><a href="${baseUrl}/solution.html">解决方案</a></li>
                          <li><a href="${baseUrl}/news.html">新闻资讯</a></li>
                          <li class="active"><a href="${baseUrl}/contactUs.html">联系我们</a></li>
					</ul>
				</div>
				<div class="kftel">
					<h2>400-0000-033</h2>
					<span>客服服务热线</span>
				</div>
			</div>
		</div>
		<div id="contact">
			<div class="bannerx">

			<#if bannerDto.clickUrl?? && bannerDto.clickUrl != "">
                  <a href="${bannerDto.clickUrl}" target="_blank">
             </#if>

            <img src="<#if bannerDto.url?? && bannerDto.url != ""> ${bannerDto.url} </#if>"  <#if bannerDto.alt?? && bannerDto.alt != ""> alt="${bannerDto.alt}"</#if> >

            <#if bannerDto.clickUrl?? && bannerDto.clickUrl != "">
                </a>
            </#if>

			</div>
			<div class="container main">
				<div class="position">当前位置：
					<a href="${baseUrl}/index.html">首页</a>|<span>联系我们</span></div>
				<div class="contact_top clearfix">
					<ul class="layui-col-xs12 layui-col-md5 map_l">
						<li class="clearfix"><i class="fa fa-map-marker" aria-hidden="true"></i>
							<p>地址：佛山市顺德区昇平路碧江民乐公园南侧约100米</p>
						</li>
						<li class="clearfix"><i class="fa fa-phone" aria-hidden="true"></i>
							<p>电话：400-8888-8888</p>
						</li>
						<li class="clearfix"><i class="fa fa-qq" aria-hidden="true"></i>
							<p>QQ：888888888</p>
						</li>
						<li class="clearfix"><i class="fa fa-envelope" aria-hidden="true"></i>
							<p>邮箱：youxiang@sina.com</p>
						</li>
					</ul>
					<div class="layui-col-xs12 layui-col-md7 map_img clearfix">
					   <div style=" width:900px; height:400px;float: right;" id="dituContent"></div>
					</div>
				</div>
				<div class="contact_main clearfix">
					<div class="layui-col-xs12 layui-col-md7 contactx_l">
						<p>告诉我们您的想法，以便我们为您提供更优质的服务！</p>
							<input type="text" name="name" id="name" placeholder="请输入姓名" autocomplete="off" class="layui-input">
							<input type="text" name="telephone" id="telephone" placeholder="请输入电话" autocomplete="off" class="layui-input">
							<textarea name="message" id="message" placeholder="请输入留言" class="layui-textarea"></textarea>
							<button class="layui-btn contact-btn"  id="leavingMessage">提交留言</button>
					</div>
					<div class="layui-col-xs12 layui-col-md5 footer_r contactx_r">
						<h2>扫码关注我们</h2>
						<ul class="clearfix">
							<#list scanCodeProjectList as scanCodeProject>
							<li>
								<img src="${scanCodeProject.url}" <#if scanCodeProject.alt?? && scanCodeProject.alt != ""> alt="${scanCodeProject.alt}" </#if>>
							<p>${scanCodeProject.name}</p>
							</li>
							</#list>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<#include "footer.ftl"/>
		<script type="text/javascript">
			$(document).ready(function(){
        	  $("#leavingMessage").click(function(){
        		$.post("${baseUrl}/leavingMessage",
        		{
        		  name:$("#name").val(),
        		  telephone:$("#telephone").val(),
        		  message:$("#message").val()
        		},
        		function(data,status){
        			if(status == 'success'){
        				$("#name").val('');
        				$("#telephone").val('');
        				$("#message").val('');
        				alert("提交成功!");
        			}

        		});
        	  });
        	});


        	//创建和初始化地图函数：
                function initMap(){
                    createMap();//创建地图
                    setMapEvent();//设置地图事件
                    addMapControl();//向地图添加控件
                }

                //创建地图函数：
                function createMap(){
                    var map = new BMap.Map("dituContent");//在百度地图容器中创建一个地图
                    var point = new BMap.Point(116.395645,39.929986);//定义一个中心点坐标
                    map.centerAndZoom(point,12);//设定地图的中心点和坐标并将地图显示在地图容器中
                    window.map = map;//将map变量存储在全局
                }

                //地图事件设置函数：
                function setMapEvent(){
                    map.enableDragging();//启用地图拖拽事件，默认启用(可不写)
                    map.enableScrollWheelZoom();//启用地图滚轮放大缩小
                    map.enableDoubleClickZoom();//启用鼠标双击放大，默认启用(可不写)
                    map.enableKeyboard();//启用键盘上下左右键移动地图
                }

                //地图控件添加函数：
                function addMapControl(){
                    //向地图中添加缩放控件
            	var ctrl_nav = new BMap.NavigationControl({anchor:BMAP_ANCHOR_TOP_LEFT,type:BMAP_NAVIGATION_CONTROL_LARGE});
            	map.addControl(ctrl_nav);
                    //向地图中添加缩略图控件
            	var ctrl_ove = new BMap.OverviewMapControl({anchor:BMAP_ANCHOR_BOTTOM_RIGHT,isOpen:1});
            	map.addControl(ctrl_ove);
                    //向地图中添加比例尺控件
            	var ctrl_sca = new BMap.ScaleControl({anchor:BMAP_ANCHOR_BOTTOM_LEFT});
            	map.addControl(ctrl_sca);
                }


                initMap();//创建和初始化地图
		</script>
	</body>

</html>