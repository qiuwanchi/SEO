<div id="footer">
  <div class="container">
    <div class="footer clearfix">
      <div class="footer_l layui-col-sm12 layui-col-md6">
        <h2>网站导航</h2>
        <ul class="clearfix">
        <#list websiteNavigationModuleList as websiteNavigation>
          <li>
                <a href="${websiteNavigation.clickUrl}" target="_blank" class="this">${websiteNavigation.name}</a>
                <#list websiteNavigation.projectDtoList as project>
                    <a href="${project.clickUrl}" target="_blank">${project.name}</a>
                </#list>
          </li>
        </#list>
        </ul>
      </div>
      <div class="footer_m layui-col-sm12 layui-col-md3">
        <h2>联系我们</h2>
        <div class="middle">
          <div class="contact_l">
            <h5>400-0000-033</h5>
            <p>周一至周日 8:00~18:00</p>
          </div>
          <div class="contact_r">
            <h5>QQ在线咨询</h5>
            <p>24小时在线为您服务</p>
          </div>
        </div>
      </div>
      <div class="footer_r layui-col-sm12 layui-col-md3">
        <h2>关注我们</h2>
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
  <div class="footer_link">
  	<p class="container">友情链接</p>
  	<div class="container">
  	<#list friendlyLinksProjectDtoList as friendlyLinksProjectDto>

  	<#if friendlyLinksProjectDto.clickUrl?? && friendlyLinksProjectDto.clickUrl != "">
  	    <a href="${friendlyLinksProjectDto.clickUrl}" target="_blank">${friendlyLinksProjectDto.name}</a>
    <#else>
        <a href="javascript:;">${friendlyLinksProjectDto.name}</a>
  	</#if>

  	</#list>

  	</div>
  </div>
<div class="footer_bot">©Copyright 2022-2022 版权所有 苏州好奇数字科技有限公司</div>
  <div style="margin:0 auto; padding:20px 0; text-align: center;">
    <a target="_blank" href="http://beian.miit.gov.cn" >苏ICP备2022019946号</a>&nbsp;&nbsp;<a target="_blank" href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=32050702011117" style="display:inline-block;text-decoration:none;height:20px;line-height:20px;"><img src="${baseUrl}/images/batb.png" style="float:left;"/>苏公网安备 32050702011117号</a>
  </div>
</div>
<div class="kf">
  <ul>
    <li class="this"><a href="javascript:;"><i class="layui-icon layui-icon-chat" ></i>
      <p>在线咨询</p>
      </a></li>
    <li><a href="javascript:;"><img src="${baseUrl}/images/icon1.png">
      <p>电话咨询</p>
      </a></li>
    <li><a href="javascript:;"><i class="layui-icon layui-icon-survey" ></i>
      <p>在线留言</p>
      </a></li>
    <li class="top"><a href="javascript:;"><img src="${baseUrl}/images/icon4.png"></i></a></li>
  </ul>
</div>
<script type="text/javascript" src="${baseUrl}/layui/layui.js"></script>
<script src="${baseUrl}/js/jquery-1.11.3.min.js" ></script>
<script type="text/javascript">

//回到顶部
$('.top').click(function(){
     $(window).scrollTop(0);
});
</script>