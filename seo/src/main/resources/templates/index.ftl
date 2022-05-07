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
<link rel="stylesheet" type="text/css" href="${baseUrl}/css/zzsc.css" />
</head>
<body>
<div class="nav">
  <div class="container clearfix">
    <div class="navimg">
        <#if logoProject.clickUrl?? && logoProject.clickUrl != "">
        <a href="${logoProject.clickUrl}" target="_blank">
        </#if>
        <img  class="logo" src="${logoProject.url}" <#if logoProject.alt?? && logoProject.alt != ""> alt="${logoProject.alt}" </#if> />
          <#if logoProject.clickUrl?? && logoProject.clickUrl != "">
      </a>
    </#if>

    </div>
    <div class="navul clearfix">
      <ul>
          <li class="active"><a href="${baseUrl}/index.html">首页</a></li>
          <li><a href="${baseUrl}/aboutUs.html">关于我们</a></li>
          <li><a href="${baseUrl}/products.html">公司产品</a></li>
          <li><a href="${baseUrl}/serviceCase.html">服务案例</a></li>
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
<div class="layui-carousel banner" id="test10">
  	<div class="pro-switch">
		<div class="slider">
			<div class="flexslider">
				<ul class="slides">
				    <#list bannerProjectDtoList as bannerProject>
					<li>
						<div class="img">
						<#if bannerProject.clickUrl?? && bannerProject.clickUrl != "">
                            <a href="${bannerProject.clickUrl}" target="_blank">
                        </#if>
						<img src="${bannerProject.url}" <#if bannerProject.alt?? && bannerProject.alt != ""> alt="${bannerProject.alt}" </#if>/>

						<#if bannerProject.clickUrl?? && bannerProject.clickUrl != "">
                            </a>
                        </#if>
						</div>
					</li>
                    </#list>
				</ul>
			</div>
		</div>
	</div>
</div>
<div class="index_main">
  <!--热门产品-->
  <div class="layui-row container goods_main">
    <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
      <div class="main-title  title_hot"><img src="${baseUrl}/images/title_hot.png" alt="热门产品"></div>
      <div class="words">热门产品</div>
      <ul class="layui-tab-title clearfix">
      <#list productModuleDtoList as productModule>
          <li <#if productModule_index ==0> class="layui-this" </#if> >${productModule.name}</li>
      </#list>

          <#if productMoreProjectDto??>
             <!--热门产品-更多按钮-->
              <li>
                  <#if productMoreProjectDto.clickUrl?? && productMoreProjectDto.clickUrl != "">
                      <a href="${productMoreProjectDto.clickUrl}" target="_blank">${productMoreProjectDto.name}</a>
                  <#else>
                      <a href="javascript:;">${productMoreProjectDto.name}</a>
                  </#if>
              </li>
           </#if>

      </ul>
      <div class="layui-tab-content" style="padding-top: 10px;">
        <#list productModuleDtoList as productModule>
        <div class="layui-tab-item <#if productModule_index ==0> layui-show </#if>">
          <ul class=" clearfix pic_list">
            <#list productModule.projectDtoList as project>
            <li class="layui-col-xs6 layui-col-sm6 layui-col-md3"> <a href="${baseUrl}/products/${productModule.code}/${project.number}.html" target="_blank">
              <div class="main_img"><img src="${project.url}" <#if project.alt?? && project.alt != ""> alt="${project.alt}" </#if>></div>
              <h1>${project.name}</h1>
              </a> </li>
            </#list>

          </ul>
          <div class="more"><a href="${baseUrl}/products/${productModule.code}/" target="_blank">查看更多 》</a></div>
        </div>
        </#list>

      </div>
    </div>
  </div>

   <!--服务案例-->
  <div style="background: #f7f7f7; padding-top: 20px; margin-top: 20px;">
    <div class="layui-row container">
      <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
        <div class="main-title  title_hot"><img src="${baseUrl}/images/title.png" alt="服务案例"></div>
        <div class="words">服务案例</div>
        <ul class="layui-tab-title  clearfix">

        <#list serviceCaseModuleDtoList as serviceCaseModuleDto>
          <li <#if serviceCaseModuleDto_index ==0> class="layui-this"</#if>>
          	 <div class="index_nav"><a href="javascript:;" >${serviceCaseModuleDto.name}</a><i class="layui-icon layui-icon-down layui-font-12"></i></div>
             <div class="case_nav_list">

             <#list serviceCaseModuleDto.projectDtoList as projectDto>

              <a href="${baseUrl}/serviceCase/${serviceCaseModuleDto.code}/${projectDto.code}/" target="_blank" >${projectDto.name}</a>
              </#list>

            </div>
          </li>
        </#list>
            <#if serviceCaseMoreProjectDto??>
                    <!--服务案例-更多按钮-->
            <li>
            <#if serviceCaseMoreProjectDto.clickUrl?? && serviceCaseMoreProjectDto.clickUrl != "">
            <a href="${serviceCaseMoreProjectDto.clickUrl}" target="_blank">${serviceCaseMoreProjectDto.name}</a>
            <#else>
            <a href="javascript:;">${serviceCaseMoreProjectDto.name}</a>
            </#if>
            </li>
            </#if>

        </ul>
        <div class="layui-tab-content" style="margin-top: 20px;">

          <#list serviceCaseModuleDtoList as serviceCaseModuleDto>
          <div class="layui-tab-item <#if serviceCaseModuleDto_index ==0> layui-show </#if>">
            <ul class=" clearfix pic_list">

              <#list serviceCaseModuleDto.subProjectDtoList as subProjectDto>

              <li class="layui-col-xs6 layui-col-sm6 layui-col-md3"> <a href="${baseUrl}/serviceCase/${serviceCaseModuleDto.code}/${subProjectDto.secondCategory}/${subProjectDto.number}.html" target="_blank">
                <div class="main_img"><img src="${subProjectDto.url}" <#if subProjectDto.alt?? && subProjectDto.alt != ""> alt="${subProjectDto.alt}" </#if>></div>
                <span>0${subProjectDto_index + 1}</span>
                <h1>${subProjectDto.name}</h1>
                <p>${subProjectDto.describeMsg}</p>
                </a>
              </li>
              </#list>

            </ul>
            <div class="more"><a href="${baseUrl}/serviceCase/${serviceCaseModuleDto.code}/" target="_blank" >查看更多 》</a></div>
          </div>
          </#list>

        </div>
      </div>
    </div>
  </div>

  <!-- 我们的优势-->
  <div class="main_ys">
    <div class="container">
      <div class="main-title title_hot"><img src="${baseUrl}/images/title_ys.png" alt="我们的优势"></div>
        <div class="words">我们的优势</div>
      <p class="ys_p">苏州好奇数字科技有限公司自创立以来，坚持科技创新，是一家快速成长、锐意进取的互动多媒体公司。公司专注于更好的视觉效果、人机互动体验开发。为客户提供方案规划、硬件系统集成施工、交互软件开发调试、影视制作、售后维护、技术支持等服务。</p>
      <ul class="clearfix ys_list">
      <#list companyAdvantageProjectDtoList as companyAdvantageProject>
        <li>
          <div class="ys_tnt">
            <div class="ys_img">
             <#if companyAdvantageProject.clickUrl?? && companyAdvantageProject.clickUrl != "">
                <a href="${companyAdvantageProject.clickUrl}" target="_blank">
             </#if>
            <img src="${companyAdvantageProject.url}" <#if companyAdvantageProject.alt?? && companyAdvantageProject.alt != ""> alt="${companyAdvantageProject.alt}" </#if>/>
            <#if companyAdvantageProject.clickUrl?? && companyAdvantageProject.clickUrl != "">
                </a>
            </#if>
            </div>

            <#if companyAdvantageProject.clickUrl?? && companyAdvantageProject.clickUrl != "">
                <a href="${companyAdvantageProject.clickUrl}" target="_blank">
            </#if>
            <h3>${companyAdvantageProject.name}</h3>
            <span>${companyAdvantageProject.describeMsg}</span>
            <#if companyAdvantageProject.clickUrl?? && companyAdvantageProject.clickUrl != "">
                </a>
            </#if>
          </div>

        </li>
      </#list>
      </ul>
      <div class="more"><a href="${baseUrl}/aboutUs.html" target="_blank">查看更多 》</a></div>
    </div>
  </div>

  <!-- 解决方案-->
  <div class="container">
    <div class="main_project">
      <div class="main-title title_hot"><img src="${baseUrl}/images/title_project.png" alt="解决方案"></div>
        <div class="words">解决方案</div>
      <h4>为客户提供方案规划、硬件系统集成施工、交互软件开发调试、影视制作、售后维护、技术支持等服务。</h4>
      <ul class=" clearfix pic_list">
      <#list solutionCaseProjectDtoList as solutionCaseProject>
        <li class="layui-col-xs12 layui-col-sm6 layui-col-md3"> <a href="${baseUrl}/solution/${solutionCaseProject.number}.html" target="_blank">
          <div class="main_img"><img src="${solutionCaseProject.url}" <#if solutionCaseProject.alt?? && solutionCaseProject.alt != ""> alt="${solutionCaseProject.alt}" </#if>></div>
          <h1>${solutionCaseProject.name}</h1>
          <p>${solutionCaseProject.describeMsg}</p>
          </a> </li>
        </#list>

      </ul>
      <div class="more"><a href="${baseUrl}/solution.html" target="_blank">查看更多 》</a></div>
    </div>
  </div>
  <!-- 维护服务-->
  <div class="main_wh">
    <div class="container">
      <div class="main-title title_hot"><img src="${baseUrl}/images/title_wh.png" alt="维护服务"></div>
        <div class="words">维护服务</div>
      <h2>更快、更好、更贴心</h2>
      <ul class="wh_list clearfix">
      <#list maintenanceServicesProjectDtoList as maintenanceServicesProject>
        <li class="layui-col-sm6 layui-col-md3">
          <div class="wh_tnt">
              <#if maintenanceServicesProject.clickUrl?? && maintenanceServicesProject.clickUrl != "">
                  <a href="${maintenanceServicesProject.clickUrl}" target="_blank">
              </#if>
            <div>
                <img src="${maintenanceServicesProject.url}" <#if maintenanceServicesProject.alt?? && maintenanceServicesProject.alt != ""> alt="${maintenanceServicesProject.alt}" </#if> />
              <h3>${maintenanceServicesProject.name}</h3>
            </div>
            <#if maintenanceServicesProject.clickUrl?? && maintenanceServicesProject.clickUrl != "">
                </a>
            </#if>
            </div>
        </li>
      </#list>
      </ul>
    </div>
  </div>

  <!-- 新闻资讯-->
  <div class="container">
    <div class="main_news">
      <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
        <div class="main-title title_hot"><img src="${baseUrl}/images/title_news.png" alt="新闻资讯"></div>
          <div class="words">新闻资讯</div>
        <ul class="layui-tab-title clearfix">
          <#list newsModuleDtoList as newsModule>
            <li <#if newsModule_index ==0>class="layui-this" </#if>>${newsModule.name}</li>
          </#list>
        </ul>
        <div class="layui-tab-content ">
        <#list newsModuleDtoList as newsModule>
          <div class="layui-tab-item <#if newsModule_index ==0>layui-show</#if> ">
              <div class="clearfix">
            <div class="main_news_img layui-col-xs12 layui-col-sm6 layui-col-md6">
            <#if newsModule.clickUrl?? && newsModule.clickUrl != "">
                <a href="${newsModule.clickUrl}" target="_blank">
            </#if>
            <img src="<#if newsModule.url??> ${newsModule.url}</#if>" <#if newsModule.alt?? && newsModule.alt != ""> alt="${newsModule.alt}" </#if> />
            <#if newsModule.clickUrl?? && newsModule.clickUrl != "">
                </a>
            </#if>
            </div>
            <div class="news_list layui-col-xs12 layui-col-sm6 layui-col-md6">
            <#list newsModule.projectDtoList as project>
              <div class="news_list_li clearfix">
                <div class="rl"><span>${project.day}</span>
                  <p>${project.years}</p>
                </div>
                <div class="text"> <a href="${baseUrl}/news/${newsModule.code}/${project.number}.html" target="_blank">
                  <h3>${project.name}</h3>
                  <h4>${project.describeMsg}</h4>
                  </a> </div>
              </div>
             </#list>

            </div>
            </div>
            <div class="more"><a href="${baseUrl}/news/${newsModule.code}/" target="_blank">查看更多 》</a></div>
          </div>
        </#list>


        </div>
      </div>
    </div>
  </div>
</div>
<#include "footer.ftl"/>
<script defer src="js/slider.js"></script>
<script type="text/javascript">

layui.use(['carousel', 'form','element'], function(){
  var carousel = layui.carousel
  ,form = layui.form
  ,element = layui.element

});
//回到顶部
$('.top').click(function(){
     $(window).scrollTop(0);
});
</script>
<script type="text/javascript">
    $(function(){
      $('.flexslider').flexslider({
        animation: "slide",
        start: function(slider){
          $('body').removeClass('loading');
        }
      });
    });

    $(".layui-icon-down").on("click", function(e){
    $(this).parent().siblings('.case_nav_list').slideDown(100);
    $(this).parents().siblings().children('.case_nav_list').slideUp(100);
    $(document).one("click", function(){
        $(".case_nav_list").slideUp(100);
    });
    e.stopPropagation();

});
  </script>
</body>
</html>