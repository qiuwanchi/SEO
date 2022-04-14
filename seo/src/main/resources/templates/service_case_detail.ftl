<!doctype html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>服务案例</title>
	<link rel="stylesheet" href="${baseUrl}/css/style.css">
</head>
<body>
<div class="c-nav" id="c-nav">
	<div class="container navFlex">
		<div class="flexItem"> <img  class="logo" src="${baseUrl}/images/logo.png" /> </div>
		<div class="flexItem clearfix">
			<ul>
				<li><a href="${baseUrl}/index.html">首页</a></li>
				<li><a href="${baseUrl}">关于我们</a></li>
				<li><a href="${baseUrl}/products.html">公司产品</a></li>
				<li class="active"><a href="${baseUrl}/serviceCase.html">服务案例</a></li>
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
<div id="news">
	<div class="bannerx">
		<img src="${baseUrl}/images/case_banner.jpg">
	</div>
	<div class="container">
		<div class="newsx_detail clearfix">
			<div class="layui-col-xs12 layui-col-md9 newsx_detail_l">
				<div class="position">当前位置：<a href="${baseUrl}/index.html">首页</a>|<a href="${baseUrl}/serviceCase.html">服务案例</a>|<span>${currentProject.name}</span></div>
				<div style="padding: 30px;">
					<h1>${currentProject.name}</h1>
					<div class="news_data"><span>发布日期：${currentProject.createTime?string("yyyy-MM-dd")}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;作者：${currentProject.createBy}</span></div>
					<p>近期全国本土疫情多点频发，疫情防控形势严峻、任务繁重。部分地区对货运车辆采取层层加码、一刀切的通行管控措施，部分高速公路服务区关停，部分公路防疫检测站拥堵，造成货运受阻、物流不畅，部分地区生产生活物资供应受到影响，社会各方高度关注。</p>
					<p>近期全国本土疫情多点频发，疫情防控形势严峻、任务繁重。部分地区对货运车辆采取层层加码、一刀切的通行管控措施，部分高速公路服务区关停，部分公路防疫检测站拥堵，造成货运受阻、物流不畅，部分地区生产生活物资供应受到影响，社会各方高度关注。</p>
					<img src="${baseUrl}/images/news_pic.jpg" />
					<p>党中央、国务院高度重视统筹做好疫情防控和经济社会发展工作，要求促进国际国内物流畅通，全力保障货运物流特别是重要生产生活物资运输畅通，维护产业链供应链稳定。4月7日，交通运输部召开物流保障协调工作机制会议，国家发展改革委、工信部、公安部、商务部、卫健委等机制成员单位参加会议。会议研究部署了做好货运物流保通保畅工作。</p>
					<p>会议指出，针对目前出现的部分地区货运物流不通不畅问题，交通运输部、公安部等部门加强统筹调度，督促指导各地科学规范设置公路防疫检查点、分类精准实施通行管控、完善货车司机服务保障，全力做好医疗、民生、能源、农资等重点物资运输保障工作，取得初步成效。但部分地区政策落实不到位、层层加码的问题仍然存在，长三角等部分涉疫地区物流运行不畅问题较为突出，必须采取更加切实有效措施，着力打通堵点卡点，保证全国物流运行顺畅，加强货车司机服务保障。</p>
					<img src="${baseUrl}/images/banner2.jpg" />
					<p>会议强调，要针对当前货运物流存在的突出问题，立即部署落实务实有效举措，确保“一断三不断”（“一断”是指坚决阻断病毒传播渠道；“三不断”是公路交通网络不断、应急运输绿色通道不断、必要的群众生产生活物资运输通道不断），切实维护人民群众正常生产生活秩序。一是严禁在高速公路主线和服务区设置防疫检测点，严禁擅自关停高速公路服务区，确保全国交通运输干线畅通。二是要建立统一格式、全国互认、办理便捷的通行证制度，确保通行证全国通行。三是要依法依规制定通行管控措施，不得层层加码、一刀切，确保通行管控政策统一。四是要及时为货车司机提供餐饮、如厕等基本生活服务，加密设置核酸检测点，确保货车司机服务措施到位。五是要加强区域协同、信息互通，做到统一汇总，及时发布，确保管控信息互联共享。</p>
				</div>
				<div class="hot_tag clearfix">
					<span>热门标签：</span>
					<strong><a href="javascript:;">确保货车司机服务措施到位</a></strong>
					<strong><a href="javascript:;">加密设置核酸检测点</a></strong>
					<strong><a href="javascript:;">要针对当前货运物流存在的突出问题</a></strong>
				</div>
				<div class="share clearfix">
					<span>分享到：</span>
					<div style="float: left;"><img src="${baseUrl}/images/share.jpg" ></div>
				</div>
				<div class="article clearfix">
					<div class="article_left">上一篇：
						<#if preProject??>
							<a href="${baseUrl}/serviceCase/${preProject.id}.html" target="_blank">${preProject.name}</a>
						<#else>
							<a href="javascript:;">无</a>
						</#if>
					</div>
					<div class="article_right">下一篇：
						<#if nextProject??>
							<a href="${baseUrl}/serviceCase/${nextProject.id}.html" target="_blank">${nextProject.name}</a>
						<#else>
							<a href="javascript:;">无</a>
						</#if>
					</div>
				</div>
			</div>
			<div class="layui-col-xs12 layui-col-md3 newsx_detail_r">
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
				<div class="rightList">
					<h3 class="clearfix"><span></span><strong>热门问答</strong></h3>
					<ul>
						<li><a href="javascrpt:;">●&nbsp;近期全国本土疫情多点频发</a></li>
						<li><a href="javascrpt:;">●&nbsp;打造乡村振兴的齐鲁样板</a></li>
						<li><a href="javascrpt:;">●&nbsp;交通运输部召开会议，部署货运物流保通保畅工作</a></li>
						<li><a href="javascrpt:;">●&nbsp;近期全国本土疫情多点频发</a></li>
						<li><a href="javascrpt:;">●&nbsp;共同的梦想，共同的未来</a></li>
						<li><a href="javascrpt:;">●&nbsp;近期全国本土疫情多点频发</a></li>
					</ul>
				</div>
				<!--<div class="rightList">
                    <h3 class="clearfix"><span></span><strong>热门标签</strong></h3>
                    <div class="bq">
                        <span><a href="javascript:;">全国</a></span>
                        <span><a href="javascript:;">本土疫情</a></span>
                        <span><a href="javascript:;">疫情多点频发</a></span>
                        <span><a href="javascript:;">交通运输</a></span>
                        <span><a href="javascript:;">部署货运</a></span>
                        <span><a href="javascript:;">全国</a></span>
                        <span><a href="javascript:;">本土疫情</a></span>
                        <span><a href="javascript:;">频发</a></span>
                        <span><a href="javascript:;">交通运输</a></span>
                        <span><a href="javascript:;">未来</a></span>
                    </div>
                </div>-->
			</div>
		</div>

	</div>
</div>
<#include "footer.ftl"/>
<script type="text/javascript">

</script>
</body>
</html>