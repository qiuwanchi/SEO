<!doctype html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>服务案例</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="c-nav" id="c-nav">
  <div class="container navFlex">
    <div class="flexItem"> <img  class="logo" src="images/logo.png" /> </div>
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
<div id="case">
	<div class="bannerx">
		<img src="${serviceCaseModule.url}">
	</div>
	<div class="position"><div class="container">当前位置：<a href="index.html">首页</a>|<span>服务案例</span></div></div>
	<div class="container main">
		<div class="newsx_main clearfix">
			<div class="page_nav">
				<#list serviceCaseModuleList as serviceCaseModule>
				<span <#if serviceCaseModule_index ==0> class="active"</#if>  onclick="changeModule('${serviceCaseModule.id}')">${serviceCaseModule.name}</span>
				</#list>
			</div>
			<ul class="case_list clearfix" id="aaa">
				<#list serviceCaseModule.projectDtoList as project>
				<li class="layui-col-xs6 layui-col-sm4 layui-col-md4">
					<a href="${baseUrl}/serviceCase/${project.id}.html" target="_blank">
					<div><img src="${project.url}"></div>
					<h2>${project.describeMsg}</h2>
					</a>
				</li>
				</#list>
			</ul>
			<input name="moduleId" id="moduleId" type="hidden" value="${serviceCaseModule.id}"/>
			<input name="baseUrl" id="baseUrl" type="hidden" value="${baseUrl}"/>

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


    function changeModule(moduleId){
        $("#moduleId").val(moduleId);
        $("#aaa").html("");
        ttt();

    }

    ttt();

    function ttt(){
    var moduleId = $("#moduleId").val();
                    $.ajax({
                        type: 'get',
                        url: "${baseUrl}/serviceCase/count?moduleId=" + moduleId,
                        contentType: 'application/json',
                        async: false,
                        success: function(data1) {
                                  var countA = data1;

                                    layui.use(['laypage', 'layer'], function(){
                                      var laypage = layui.laypage
                                      ,layer = layui.layer;
                                      laypage.render({
                                        elem: 'paging'
                                        ,count: countA
                                        ,first: '首页'
                                        ,last: '尾页'
                                        ,prev: '<em>上一页</em>'
                                        ,next: '<em>下一页</em>'
                                        ,theme: '#297ec2'
                                        ,limit: 2
                                        ,jump: function(obj, first){
                                              var baseUrl = $("#baseUrl").val();
                                              $.ajax({
                                                    type: 'get',
                                                    url: "${baseUrl}/serviceCase-page?moduleId=" + moduleId + "&currentPage=" + obj.curr,
                                                    contentType: 'application/json',
                                                    async: false,
                                                    success: function(result) {
                                                              obj.count = result.total;
                                                              var text = '';
                                                              $.each(result.records, function(i, obj){
                                                               text = text +
                                                                    ' <li class="layui-col-xs6 layui-col-sm4 layui-col-md4">' +
                                                                    '<a href="' + baseUrl + '/serviceCase/' + obj.id + '.html" target="_blank">' +
                                                                    '<div><img src="' + obj.url + '"></div>' +
                                                                    '<h2>' + obj.describeMsg + '</h2>' +
                                                                    '</a>' +
                                                                '</li>';
                                                              });

                                                              $("#aaa").html(text);
                                                    }
                                                });

                                              }
                                      });
                                    });

                        }
                      });
    }
</script>
</body>
</html>