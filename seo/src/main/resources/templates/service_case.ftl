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
    <div class="flexItem"> <img  class="logo" src="${logoProject.url}" <#if logoProject.alt?? && logoProject.alt != ""> alt="${logoProject.alt}" </#if> /> </div>
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
  <div class="bannerx"> <img src="${bannerImageDto.url}" <#if bannerImageDto.alt?? && bannerImageDto.alt != ""> alt="${bannerImageDto.alt}" </#if> /> </div>
  <div class="position">
    <div class="container">当前位置：<a href="${baseUrl}/index.html">首页</a>|<span>服务案例</span></div>
  </div>
  <div class="container main">
    <div class="newsx_main case_main">
      <div class="nav_float_list clearfix">

        <#list serviceCaseModuleList as serviceCaseModule>
        <div class="nav_float">
        <div class="case_nav"> ${serviceCaseModule.name}<i class="layui-icon layui-icon-down layui-font-12"></i> </div>
        <ul class="case_nav_list">
            <#list serviceCaseModule.projectDtoList as serviceCaseProject>
                <li><a href="${baseUrl}/serviceCase/${serviceCaseProject.moduleCode}/${serviceCaseProject.code}">${serviceCaseProject.name}</a></li>
            </#list>
        </ul>
        </div>
        </#list>

      </div>
      <ul class="case_list clearfix">
       <#list subProjectList as subProject>

        <li class="layui-col-xs6 layui-col-sm4 layui-col-md3">
          <a href="case_detail.html">
              <div><img src="${subProject.url}"></div>
              <h2>${subProject.name}</h2>
          </a>
         </li>
        </#list>

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
    ,count: ${page.total}
    ,first: '首页'
    ,last: '尾页'
    ,prev: '<em>上一页</em>'
    ,next: '<em>下一页</em>'
    ,theme: '#297ec2'
    ,limit: ${page.size}
    ,curr: ${page.current}
    ,jump: function(obj, first){
        if(!first){

            var firstCategory = '${firstCategory}';
            var secondCategory = '${secondCategory}';
            var url = '${baseUrl}/serviceCase/';
            if(firstCategory.length != 0){
                url = url + firstCategory + '/';
            }

            if(secondCategory.length != 0){
                url = url + secondCategory + '/';
            }

            window.location.href = url + 'index_' + obj.curr + '.html';
        }
    }
  });

});


//回到顶部
$('.top').click(function(){
     $(window).scrollTop(0);
});



$(".case_nav").on("click", function(e){
    $(this).siblings('.case_nav_list').slideDown(100);
    //$(".case_nav_list").slideDown(100);
    $(this).parent().siblings().children('.case_nav_list').slideUp(100);
    $(document).one("click", function(){
        $(".case_nav_list").slideUp(100);
    });
    e.stopPropagation();

});


</script>
</body>
</html>