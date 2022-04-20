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
        <li><a href="${baseUrl}/aboutUs.html">关于我们</a></li>
        <li><a href="${baseUrl}/products.html">公司产品</a></li>
        <li class="active"><a href="${baseUrl}/serviceCase.html">服务案例</a></li>
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
  <div class="bannerx"> <img src="${bannerImageDto.url}" <#if bannerImageDto.alt?? && bannerImageDto.alt != ""> alt="${bannerImageDto.alt}" </#if> /> </div>
  <div class="position">
    <div class="container">当前位置：
	<a href="${baseUrl}/index.html">首页</a>
	<#if firstCategoryModuleDto??>
		|<a href="${baseUrl}/serviceCase.html">服务案例</a>|<span>${firstCategoryModuleDto.name}</span>
    <#else>
		|<span>服务案例</span>
    </#if>
	</div>
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
		<a href="${baseUrl}/serviceCase/${subProject.firstCategory}/${subProject.secondCategory}/${subProject.number}.html">
          <div><img src="${subProject.url}"></div>
          <h2>${subProject.name}</h2>
          </a> 
		</li>
	  </#list>	
	  
      </ul>
      
	  <div id="newPage" class="clearfix">
        <ul class="clearfix">
          <span>共${page.total}条</span>
          ${pageHtml}
        </ul>
      </div>
	  
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

<style type="text/css">
    #newPage{
      padding-right: 0;
      margin-top: 0px;
    }
    #newPage ul{
      float:right
    }
    #newPage span{
        float:left;
      display:block;
      line-height:28px;
      padding-right:6px;
      color:#666
    }
    #newPage li{
        float:left;

      }
    #newPage li a{
        display:block;
        border: 1px solid #d1dbe5;
        padding: 0 10px;
        height: 28px;
        line-height: 28px;
        background-color: #fff;
        color: #333;
        font-size: 14px;
      cursor:pointer;
      margin-left:6px
    }
    #newPage .page_active a{
        border:1px solid #297ec2;
        background-color: #297ec2;
        color: #fff;
        cursor: default;}
    #newPage .noclick a{
        border-color: #eee!important;
        background-color: #FBFBFB!important;
        color: #d2d2d2!important;
        cursor: not-allowed!important;
        opacity: 1;
    }
</style>
</body>
</html>