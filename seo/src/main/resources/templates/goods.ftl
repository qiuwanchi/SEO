<!doctype html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>公司产品</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="c-nav" id="c-nav">
  <div class="container navFlex">
    <div class="flexItem"> <img  class="logo" src="${logoProject.url}" <#if logoProject.alt?? && logoProject.alt != ""> alt="${logoProject.alt}" </#if> /> </div>
    <div class="flexItem clearfix">
      <ul>
		  <li><a href="${baseUrl}/index.html">首页</a></li>
		  <li><a href="${baseUrl}">关于我们</a></li>
		  <li class="active"><a href="${baseUrl}/products.html">公司产品</a></li>
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
<div id="g">
	<div class="bannerx">
		<div class="container">
		<h3>公司产品</h3>
		<p>Company products</p>
		</div>
	</div>
	<div class="container main">
		<div class="position">当前位置：<a href="index.html">首页</a>|<span>公司产品</span></div>
		<div class="newsx_main clearfix">
			<div class="page_nav"><span class="active">全部</span><span>展厅多媒体</span><span>互动投影</span><span>全息投影</span><span>数字影院</span><span>特色屏幕</span><span>影视动画</span><span>ar/vr 互动</span><span>全息投影</span><span>数字影院</span><span>特色屏幕</span><span>影视动画</span></div>
			<ul class="case_list">
				<li class="layui-col-xs6 layui-col-sm4 layui-col-md3"> 
					<a href="goods_detail.html">
					<div><img src="images/pic1.jpg"></div>
					<h2>疫情之下如何保春耕？种子化肥等物资如何到位？</h2>
					</a>
				</li>
				<li class="layui-col-xs6 layui-col-sm4 layui-col-md3"> 
					<a href="goods_detail.html">
					<div><img src="images/pic2.jpg"></div>
					<h2>中国人民出色地搭建了安全的奥运舞台</h2>
					</a>
				</li>
				<li class="layui-col-xs6 layui-col-sm4 layui-col-md3"> 
					<a href="goods_detail.html">
					<div><img src="images/pic3.jpg"></div>
					<h2>带动三亿人参与冰雪运动成果展</h2>
					</a>
				</li>
				<li class="layui-col-xs6 layui-col-sm4 layui-col-md3"> 
					<a href="goods_detail.html">
					<div><img src="images/pic4.jpg"></div>
					<h2>国务院安委会制定部署安全生产15条措施</h2>
					</a>
				</li>
				<li class="layui-col-xs6 layui-col-sm4 layui-col-md3"> 
					<a href="goods_detail.html">
					<div><img src="images/pic4.jpg"></div>
					<h2>疫情之下如何保春耕？种子化肥等物资如何到位？</h2>
					</a>
				</li>
				<li class="layui-col-xs6 layui-col-sm4 layui-col-md3"> 
					<a href="goods_detail.html">
					<div><img src="images/pic1.jpg"></div>
					<h2>中国人民出色地搭建了安全的奥运舞台</h2>
					</a>
				</li>
				<li class="layui-col-xs6 layui-col-sm4 layui-col-md3"> 
					<a href="goods_detail.html">
					<div><img src="images/pic3.jpg"></div>
					<h2>带动三亿人参与冰雪运动成果展</h2>
					</a>
				</li>
				<li class="layui-col-xs6 layui-col-sm4 layui-col-md3"> 
					<a href="goods_detail.html">
					<div><img src="images/pic2.jpg"></div>
					<h2>国务院安委会制定部署安全生产15条措施</h2>
					</a>
				</li>
				<li class="layui-col-xs6 layui-col-sm4 layui-col-md3"> 
					<a href="goods_detail.html">
					<div><img src="images/pic4.jpg"></div>
					<h2>疫情之下如何保春耕？种子化肥等物资如何到位？</h2>
					</a>
				</li>
				<li class="layui-col-xs6 layui-col-sm4 layui-col-md3"> 
					<a href="goods_detail.html">
					<div><img src="images/pic3.jpg"></div>
					<h2>中国人民出色地搭建了安全的奥运舞台</h2>
					</a>
				</li>
				<li class="layui-col-xs6 layui-col-sm4 layui-col-md3"> 
					<a href="goods_detail.html">
					<div><img src="images/pic1.jpg"></div>
					<h2>带动三亿人参与冰雪运动成果展</h2>
					</a>
				</li>
				<li class="layui-col-xs6 layui-col-sm4 layui-col-md3"> 
					<a href="goods_detail.html">
					<div><img src="images/pic2.jpg"></div>
					<h2>国务院安委会制定部署安全生产15条措施</h2>
					</a>
				</li>
			</ul>
			<div id="paging"></div>
		</div>
	  
	</div>
</div>
<#include "footer.ftl"/>
<script type="text/javascript">
	$('.page_nav span').click(function () {
	    var f = this;
	    $('.page_nav span').each(function () {
	      this.className = this == f ? 'active' : 'none'
	    });
	  });
//参考layui官方文档
layui.use(['laypage', 'layer'], function(){
  var laypage = layui.laypage
  ,layer = layui.layer;
  laypage.render({
    elem: 'paging'
    ,count: 100
    ,theme: '#297ec2'
  });
});
</script>
</body>
</html>