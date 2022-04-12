<!doctype html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>首页</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="c-nav" id="c-nav">
  <div class="container navFlex">
    <div class="flexItem"> <img  class="logo" src="${logo.url}" /> </div>
    <div class="flexItem clearfix">
      <ul>
        <li class="active"><a href="${baseUrl}">首页</a></li>
        <li><a href="${baseUrl}">关于我们</a></li>
        <li><a href="${baseUrl}/products">公司产品</a></li>
        <li><a href="${baseUrl}/products">项目案例</a></li>
        <li><a href="${baseUrl}/products">新闻资讯</a></li>
        <li><a href="${baseUrl}/contactUs">联系我们</a></li>
      </ul>
	  <div class="search">
		  <input type="text" class="search_text" placeholder="关键字搜索" />
		  <input type="button" class="search_btn" value="搜索" />
	  </div>
    </div>
  </div>
</div>
<div class="layui-carousel banner" id="test1" lay-filter="test1">
    <div carousel-item="">
      <#list bannerList as banner>
      	  <div><img src="${banner.url}"></div>
      	  </#list>
    </div>
  </div>
<div class="index_main">
<!--公司产品-->
<div class="layui-row container goods_main">
  <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
	<div class="main-title  title_hot"><img src="images/title_hot.png"></div>
    <ul class="layui-tab-title clearfix">

    <#list productModuleList as productModule>
        <li <#if productModule_index ==0> class="layui-this"  </#if> >${productModule.name}</li>
    </#list>

    </ul>
    <div class="layui-tab-content" style="padding-top: 10px;">
      <div class="layui-tab-item layui-show">
		  <ul class=" clearfix pic_list">

		  <#list productModuleProjectList as project>

			  <li class="layui-col-xs12 layui-col-sm6 layui-col-md3">
				<a href="javascript:;">
				<div class="main_img"><img src="${project.url}"></div>
				<h1>${project.name}</h1>
				</a>
			  </li>
			</#list>

		  </ul>
		  <div class="more"><a href="#">查看更多 》</a></div>
	  </div>
      <div class="layui-tab-item">
		  <ul class=" clearfix pic_list">
			  <li class="layui-col-xs12 layui-col-sm6 layui-col-md3">
				<a href="javascript:;">
				<div class="main_img"><img src="images/pic2.jpg"></div>
				<h1>产品名称</h1>
				</a>
			  </li>
			  <li class="layui-col-xs12 layui-col-sm6 layui-col-md3">
				<a href="javascript:;">
			  	<div class="main_img"><img src="images/pic3.jpg"></div>
			  	<h1>产品名称</h1>
				</a>
			  </li>
			  <li class="layui-col-xs12 layui-col-sm6 layui-col-md3">
				<a href="javascript:;">
			  	<div class="main_img"><img src="images/pic1.jpg"></div>
			  	<h1>产品名称</h1>
				</a>
			  </li>
			  <li class="layui-col-xs12 layui-col-sm6 layui-col-md3">
				<a href="javascript:;">
			  	<div class="main_img"><img src="images/pic4.jpg"></div>
			  	<h1>产品名称</h1>
				</a>
			  </li>
			  <li class="layui-col-xs12 layui-col-sm6 layui-col-md3">
				<a href="javascript:;">
			  	<div class="main_img"><img src="images/pic3.jpg"></div>
			  	<h1>产品名称</h1>
				</a>
			  </li>
			  <li class="layui-col-xs12 layui-col-sm6 layui-col-md3">
				<a href="javascript:;">
			  	<div class="main_img"><img src="images/pic4.jpg"></div>
			  	<h1>产品名称</h1>
				</a>
			  </li>
			  <li class="layui-col-xs12 layui-col-sm6 layui-col-md3">
				<a href="javascript:;">
			  	<div class="main_img"><img src="images/pic3.jpg"></div>
			  	<h1>产品名称</h1>
				</a>
			  </li>
			  <li class="layui-col-xs12 layui-col-sm6 layui-col-md3">
				<a href="javascript:;">
			  	<div class="main_img"><img src="images/pic4.jpg"></div>
			  	<h1>产品名称</h1>
				</a>
			  </li>
		  </ul>
		  <div class="more"><a href="#">查看更多 》</a></div>
	  </div>

    </div>
  </div>
</div>

<!--多媒体展厅项目案例-->
<div style="background: #f7f7f7; padding-top: 20px; margin-top: 20px;">
<div class="layui-row container">
  <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
	<div class="main-title"><img src="images/title.PNG"></div>
    <ul class="layui-tab-title">
      <#list showRoomModuleList as showRoomModule>
         <li <#if showRoomModule_index ==0> class="layui-this"  </#if>>${showRoomModule.name}</li>
      </#list>
    </ul>
    <div class="layui-tab-content" style="margin-top: 20px;">
      <div class="layui-tab-item layui-show">
		  <ul class=" clearfix pic_list">
			<#list showRoomModuleProjectList as project>
                <li class="layui-col-xs12 layui-col-sm6 layui-col-md3">
                  <a href="javascript:;">
                  <div class="main_img"><img src="${project.url}"></div>
                  <span>01</span>
                  <h5>${project.name}</h5>
                  <p>${project.describeMsg}</p>
                  </a>
                </li>
           </#list>
		  </ul>
		  <div class="more"><a href="#">查看更多 》</a></div>
	  </div>
      <div class="layui-tab-item">
      		  <ul class=" clearfix pic_list">
      			  <li class="layui-col-xs12 layui-col-sm6 layui-col-md3">
      				<a href="javascript:;">
      				<div class="main_img"><img src="images/pic1.jpg"></div>
      				<span>01</span>
      				<h1>展厅项目1</h1>
      				<p>这个是案例的一个标题发丝都会发生的核辐射的了开发商垫付士大夫看见你说的发生...</p>
      				</a>
      			  </li>
      			  <li class="layui-col-xs12 layui-col-sm6 layui-col-md3">
      				<a href="javascript:;">
      			  	<div class="main_img"><img src="images/pic2.jpg"></div>
      			  	<span>02</span>
      			  	<h1>展厅项目2</h1>
      			  	<p>这个是案例的一个标题发丝都会发生的核辐射的了开发商垫付士大夫看见你说的发生...</p>
      				</a>
      			  </li>
      			  <li class="layui-col-xs12 layui-col-sm6 layui-col-md3">
      				<a href="javascript:;">
      			  	<div class="main_img"><img src="images/pic3.jpg"></div>
      			  	<span>03</span>
      			  	<h1>展厅项目3</h1>
      			  	<p>这个是案例的一个标题发丝都会发生的核辐射的了开发商垫付士大夫看见你说的发生...</p>
      				</a>
      			  </li>
      			  <li class="layui-col-xs12 layui-col-sm6 layui-col-md3">
      				<a href="javascript:;">
      			  	<div class="main_img"><img src="images/pic4.jpg"></div>
      			  	<span>04</span>
      			  	<h1>展厅项目4</h1>
      			  	<p>这个是案例的一个标题发丝都会发生的核辐射的了开发商垫付士大夫看见你说的发生...</p>
      				</a>
      			  </li>
      		  </ul>
      		  <div class="more"><a href="#">查看更多 》</a></div>
      </div>
      <div class="layui-tab-item">
      		  <ul class=" clearfix pic_list">
      			  <li class="layui-col-xs12 layui-col-sm6 layui-col-md3">
      				<a href="javascript:;">
      				<div class="main_img"><img src="images/pic1.jpg"></div>
      				<span>01</span>
      				<h1>展厅项目1</h1>
      				<p>这个是案例的一个标题发丝都会发生的核辐射的了开发商垫付士大夫看见你说的发生...</p>
      				</a>
      			  </li>
      			  <li class="layui-col-xs12 layui-col-sm6 layui-col-md3">
      				<a href="javascript:;">
      			  	<div class="main_img"><img src="images/pic2.jpg"></div>
      			  	<span>02</span>
      			  	<h1>展厅项目2</h1>
      			  	<p>这个是案例的一个标题发丝都会发生的核辐射的了开发商垫付士大夫看见你说的发生...</p>
      				</a>
      			  </li>
      			  <li class="layui-col-xs12 layui-col-sm6 layui-col-md3">
      				<a href="javascript:;">
      			  	<div class="main_img"><img src="images/pic3.jpg"></div>
      			  	<span>03</span>
      			  	<h1>展厅项目3</h1>
      			  	<p>这个是案例的一个标题发丝都会发生的核辐射的了开发商垫付士大夫看见你说的发生...</p>
      				</a>
      			  </li>
      			  <li class="layui-col-xs12 layui-col-sm6 layui-col-md3">
      				<a href="javascript:;">
      			  	<div class="main_img"><img src="images/pic4.jpg"></div>
      			  	<span>04</span>
      			  	<h1>展厅项目4</h1>
      			  	<p>这个是案例的一个标题发丝都会发生的核辐射的了开发商垫付士大夫看见你说的发生...</p>
      				</a>
      			  </li>
      		  </ul>
      		  <div class="more"><a href="#">查看更多 》</a></div>
      </div>
      <div class="layui-tab-item">
      		  <ul class=" clearfix pic_list">
      			  <li class="layui-col-xs12 layui-col-sm6 layui-col-md3">
      				<a href="javascript:;">
      				<div class="main_img"><img src="images/pic1.jpg"></div>
      				<span>01</span>
      				<h1>展厅项目1</h1>
      				<p>这个是案例的一个标题发丝都会发生的核辐射的了开发商垫付士大夫看见你说的发生...</p>
      				</a>
      			  </li>
      			  <li class="layui-col-xs12 layui-col-sm6 layui-col-md3">
      				<a href="javascript:;">
      			  	<div class="main_img"><img src="images/pic2.jpg"></div>
      			  	<span>02</span>
      			  	<h1>展厅项目2</h1>
      			  	<p>这个是案例的一个标题发丝都会发生的核辐射的了开发商垫付士大夫看见你说的发生...</p>
      				</a>
      			  </li>
      			  <li class="layui-col-xs12 layui-col-sm6 layui-col-md3">
      				<a href="javascript:;">
      			  	<div class="main_img"><img src="images/pic3.jpg"></div>
      			  	<span>03</span>
      			  	<h1>展厅项目3</h1>
      			  	<p>这个是案例的一个标题发丝都会发生的核辐射的了开发商垫付士大夫看见你说的发生...</p>
      				</a>
      			  </li>
      			  <li class="layui-col-xs12 layui-col-sm6 layui-col-md3">
      				<a href="javascript:;">
      			  	<div class="main_img"><img src="images/pic4.jpg"></div>
      			  	<span>04</span>
      			  	<h1>展厅项目4</h1>
      			  	<p>这个是案例的一个标题发丝都会发生的核辐射的了开发商垫付士大夫看见你说的发生...</p>
      				</a>
      			  </li>
      		  </ul>
      		  <div class="more"><a href="#">查看更多 》</a></div>
      </div>
      <div class="layui-tab-item">
      		  <ul class=" clearfix pic_list">
      			  <li class="layui-col-xs12 layui-col-sm6 layui-col-md3">
      				<a href="javascript:;">
      				<div class="main_img"><img src="images/pic1.jpg"></div>
      				<span>01</span>
      				<h1>展厅项目1</h1>
      				<p>这个是案例的一个标题发丝都会发生的核辐射的了开发商垫付士大夫看见你说的发生...</p>
      				</a>
      			  </li>
      			  <li class="layui-col-xs12 layui-col-sm6 layui-col-md3">
      				<a href="javascript:;">
      			  	<div class="main_img"><img src="images/pic2.jpg"></div>
      			  	<span>02</span>
      			  	<h1>展厅项目2</h1>
      			  	<p>这个是案例的一个标题发丝都会发生的核辐射的了开发商垫付士大夫看见你说的发生...</p>
      				</a>
      			  </li>
      			  <li class="layui-col-xs12 layui-col-sm6 layui-col-md3">
      				<a href="javascript:;">
      			  	<div class="main_img"><img src="images/pic3.jpg"></div>
      			  	<span>03</span>
      			  	<h1>展厅项目3</h1>
      			  	<p>这个是案例的一个标题发丝都会发生的核辐射的了开发商垫付士大夫看见你说的发生...</p>
      				</a>
      			  </li>
      			  <li class="layui-col-xs12 layui-col-sm6 layui-col-md3">
      				<a href="javascript:;">
      			  	<div class="main_img"><img src="images/pic4.jpg"></div>
      			  	<span>04</span>
      			  	<h1>展厅项目4</h1>
      			  	<p>这个是案例的一个标题发丝都会发生的核辐射的了开发商垫付士大夫看见你说的发生...</p>
      				</a>
      			  </li>
      		  </ul>
      		  <div class="more"><a href="#">查看更多 》</a></div>
      </div>
     </div>
  </div>
</div>
</div>
<!-- 视频案例-->
<div class="container">
	<div class="main_video">
		<div class="main-title title_video"><img src="images/title_video.PNG"></div>
		<ul class=" clearfix pic_list">
			 <#list videoCaseModuleProjectList as project>
                <li class="layui-col-xs12 layui-col-md4">
                    <a href="#">
                    <div class="main_img"><img src="${project.url}"></div>
                    <div class="video_padding">
                    <h5>${project.name}</h5>
                    <p>${project.describeMsg}</p>
                    </div>
                    </a>
                </li>
            </#list>
		</ul>
		<div class="more"><a href="#">查看更多 》</a></div>
	</div>
</div>
<!-- 我们的优势-->
<div class="main_ys">
	<div class="container">
		<div class="main-title title_hot"><img src="images/title_ys.PNG"></div>
		<p class="ys_p">苏州好奇数字科技有限公司自创立以来，坚持科技创新，是一家快速成长、锐意进取的互动多媒体公司。公司专注于更好的视觉效果、人机互动体验开发。为客户提供方案规划、硬件系统集成施工、交互软件开发调试、影视制作、售后维护、技术支持等服务。</p>
	    <ul class="clearfix ys_list">
			<#list companyAdvantageModuleProjectList as project>
                <li>
                  <div class="ys_tnt">
                    <div class="ys_img"><img src="${project.url}"></div>
                    <h3>${project.name}</h3>
                    <span>${project.describeMsg}</span>
                  </div>
                </li>
             </#list>
		</ul>
		<div class="more"><a href="#">查看更多 》</a></div>
	</div>
</div>
<!-- 解决方案案例-->
<div class="container">
  <div class="main_project">
	<div class="main-title title_hot"><img src="images/title_project.PNG"></div>
	<h4>为客户提供方案规划、硬件系统集成施工、交互软件开发调试、影视制作、售后维护、技术支持等服务。</h4>
	<ul class=" clearfix pic_list">
	 <#list solutionCaseModuleProjectList as project>
          <li class="layui-col-xs12 layui-col-sm6 layui-col-md3">
            <a href="javascript:;">
            <div class="main_img"><img src="${project.url}"></div>
            <h5>${project.name}</h5>
            <p>${project.describeMsg}</p>
            </a>
          </li>
      </#list>
	</ul>
	<div class="more"><a href="javascript:;">查看更多 》</a></div>
  </div>
</div>
<!-- 维护服务-->
<div class="main_wh">
	<div class="container">
		<div class="main-title title_hot"><img src="images/title_wh.PNG"></div>
		<h2>更快、更好、更贴心</h2>
		<ul class="wh_list clearfix">
			<#list maintenanceServicesModuleProjectList as project>
                <li class="layui-col-sm6 layui-col-md3">
                    <div class="wh_tnt">
                        <a href="javascript:;">
                            <div><img src="${project.url}"><h3>${project.name}</h3></div>
                        </a>
                    </div>
                </li>
            </#list>
		</ul>
	</div>
</div>
<!-- 企业资讯-->
<div class="container">
	<div class="main_news">
		<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
			<div class="main-title title_hot"><img src="images/title_news.PNG"></div>
		  <ul class="layui-tab-title">
		    <li class="layui-this">公司新闻</li>
		    <li>多媒体知识</li>
		    <li>行业新闻</li>
		    <li>其他</li>
		    <li>主题馆等</li>
		  </ul>
		  <div class="layui-tab-content ">
		    <div class="layui-tab-item layui-show ">
			   <div class="main_news_img layui-col-xs12 layui-col-sm6 layui-col-md6"><img src="images/news_pic.jpg"></div>
			   <div class="news_list layui-col-xs12 layui-col-sm6 layui-col-md6">
				   <div class="news_list_li clearfix">
					   <div class="rl"><span>18</span><p>2022-03</p></div>
					   <div class="text">
						   <a href="javascript:;">
						   <h3>资讯1</h3>
						   <h4>为客户提供方案规划、硬件系统集成施工、交互软件开发调试、影视制作、售后维护、技术支持等服务。，逐步形成了以强化地板为主，实木地板、实木复合地板等一体化的木地板研发、生产、销售与服务平台。</h4>
					       </a>
					   </div>
				   </div>
				   <div class="news_list_li clearfix">
				   	  <div class="rl"><span>18</span><p>2022-03</p></div>
				   	  <div class="text">
						<a href="javascript:;">
				   		<h3>资讯1</h3>
				   		<h4>为客户提供方案规划、硬件系统集成施工、交互软件开发调试、影视制作、售后维护、技术支持等服务。，逐步形成了以强化地板为主，实木地板、实木复合地板等一体化的木地板研发、生产、销售与服务平台。</h4>
				   	    </a>
					  </div>
				   </div>
				   <div class="news_list_li clearfix">
				   	  <div class="rl"><span>18</span><p>2022-03</p></div>
				   	  <div class="text">
						<a href="javascript:;">
				   		<h3>资讯1</h3>
				   		<h4>为客户提供方案规划、硬件系统集成施工、交互软件开发调试、影视制作、售后维护、技术支持等服务。，逐步形成了以强化地板为主，实木地板、实木复合地板等一体化的木地板研发、生产、销售与服务平台。</h4>
				   	    </a>
					</div>
				   </div>
			   </div>
			   <div class="clearfix"></div>
			   <div class="more"><a href="javascript:;">查看更多 》</a></div>
			</div>
		    <div class="layui-tab-item ">
		       <div class="main_news_img layui-col-xs12 layui-col-sm6 layui-col-md6"><img src="images/news_pic.jpg"></div>
		       <div class="news_list layui-col-xs12 layui-col-sm6 layui-col-md6">
		    	   <div class="news_list_li clearfix">
		    		   <div class="rl"><span>18</span><p>2022-03</p></div>
		    		   <div class="text">
		    			   <a href="javascript:;">
		    			   <h3>资讯1</h3>
		    			   <h4>为客户提供方案规划、硬件系统集成施工、交互软件开发调试、影视制作、售后维护、技术支持等服务。，逐步形成了以强化地板为主，实木地板、实木复合地板等一体化的木地板研发、生产、销售与服务平台。</h4>
		    		       </a>
		    		   </div>
		    	   </div>
		    	   <div class="news_list_li clearfix">
		    	   	  <div class="rl"><span>18</span><p>2022-03</p></div>
		    	   	  <div class="text">
		    			<a href="javascript:;">
		    	   		<h3>资讯1</h3>
		    	   		<h4>为客户提供方案规划、硬件系统集成施工、交互软件开发调试、影视制作、售后维护、技术支持等服务。，逐步形成了以强化地板为主，实木地板、实木复合地板等一体化的木地板研发、生产、销售与服务平台。</h4>
		    	   	    </a>
		    		  </div>
		    	   </div>
		    	   <div class="news_list_li clearfix">
		    	   	  <div class="rl"><span>18</span><p>2022-03</p></div>
		    	   	  <div class="text">
		    			<a href="javascript:;">
		    	   		<h3>资讯1</h3>
		    	   		<h4>为客户提供方案规划、硬件系统集成施工、交互软件开发调试、影视制作、售后维护、技术支持等服务。，逐步形成了以强化地板为主，实木地板、实木复合地板等一体化的木地板研发、生产、销售与服务平台。</h4>
		    	   	    </a>
		    		</div>
		    	   </div>
		       </div>
		       <div class="clearfix"></div>
		       <div class="more"><a href="javascript:;">查看更多 》</a></div>
		    </div>
	      <div class="layui-tab-item ">
	           <div class="main_news_img layui-col-xs12 layui-col-sm6 layui-col-md6"><img src="images/news_pic.jpg"></div>
	           <div class="news_list layui-col-xs12 layui-col-sm6 layui-col-md6">
	        	   <div class="news_list_li clearfix">
	        		   <div class="rl"><span>18</span><p>2022-03</p></div>
	        		   <div class="text">
	        			   <a href="javascript:;">
	        			   <h3>资讯1</h3>
	        			   <h4>为客户提供方案规划、硬件系统集成施工、交互软件开发调试、影视制作、售后维护、技术支持等服务。，逐步形成了以强化地板为主，实木地板、实木复合地板等一体化的木地板研发、生产、销售与服务平台。</h4>
	        		       </a>
	        		   </div>
	        	   </div>
	        	   <div class="news_list_li clearfix">
	        	   	  <div class="rl"><span>18</span><p>2022-03</p></div>
	        	   	  <div class="text">
	        			<a href="javascript:;">
	        	   		<h3>资讯1</h3>
	        	   		<h4>为客户提供方案规划、硬件系统集成施工、交互软件开发调试、影视制作、售后维护、技术支持等服务。，逐步形成了以强化地板为主，实木地板、实木复合地板等一体化的木地板研发、生产、销售与服务平台。</h4>
	        	   	    </a>
	        		  </div>
	        	   </div>
	        	   <div class="news_list_li clearfix">
	        	   	  <div class="rl"><span>18</span><p>2022-03</p></div>
	        	   	  <div class="text">
	        			<a href="javascript:;">
	        	   		<h3>资讯1</h3>
	        	   		<h4>为客户提供方案规划、硬件系统集成施工、交互软件开发调试、影视制作、售后维护、技术支持等服务。，逐步形成了以强化地板为主，实木地板、实木复合地板等一体化的木地板研发、生产、销售与服务平台。</h4>
	        	   	    </a>
	        		</div>
	        	   </div>
	           </div>
	           <div class="clearfix"></div>
	           <div class="more"><a href="javascript:;">查看更多 》</a></div>
	        </div>
	      <div class="layui-tab-item ">
	          <div class="main_news_img layui-col-xs12 layui-col-sm6 layui-col-md6"><img src="images/news_pic.jpg"></div>
	           <div class="news_list layui-col-xs12 layui-col-sm6 layui-col-md6">
	        	   <div class="news_list_li clearfix">
	        		   <div class="rl"><span>18</span><p>2022-03</p></div>
	        		   <div class="text">
	        			   <a href="javascript:;">
	        			   <h3>资讯1</h3>
	        			   <h4>为客户提供方案规划、硬件系统集成施工、交互软件开发调试、影视制作、售后维护、技术支持等服务。，逐步形成了以强化地板为主，实木地板、实木复合地板等一体化的木地板研发、生产、销售与服务平台。</h4>
	        		       </a>
	        		   </div>
	        	   </div>
	        	   <div class="news_list_li clearfix">
	        	   	  <div class="rl"><span>18</span><p>2022-03</p></div>
	        	   	  <div class="text">
	        			<a href="javascript:;">
	        	   		<h3>资讯1</h3>
	        	   		<h4>为客户提供方案规划、硬件系统集成施工、交互软件开发调试、影视制作、售后维护、技术支持等服务。，逐步形成了以强化地板为主，实木地板、实木复合地板等一体化的木地板研发、生产、销售与服务平台。</h4>
	        	   	    </a>
	        		  </div>
	        	   </div>
	        	   <div class="news_list_li clearfix">
	        	   	  <div class="rl"><span>18</span><p>2022-03</p></div>
	        	   	  <div class="text">
	        			<a href="javascript:;">
	        	   		<h3>资讯1</h3>
	        	   		<h4>为客户提供方案规划、硬件系统集成施工、交互软件开发调试、影视制作、售后维护、技术支持等服务。，逐步形成了以强化地板为主，实木地板、实木复合地板等一体化的木地板研发、生产、销售与服务平台。</h4>
	        	   	    </a>
	        		</div>
	        	   </div>
	           </div>
	           <div class="clearfix"></div>
	           <div class="more"><a href="javascript:;">查看更多 》</a></div>
	        </div>
	      <div class="layui-tab-item ">
	           <div class="main_news_img layui-col-xs12 layui-col-sm6 layui-col-md6"><img src="images/news_pic.jpg"></div>
	           <div class="news_list layui-col-xs12 layui-col-sm6 layui-col-md6">
	        	   <div class="news_list_li clearfix">
	        		   <div class="rl"><span>18</span><p>2022-03</p></div>
	        		   <div class="text">
	        			   <a href="javascript:;">
	        			   <h3>资讯1</h3>
	        			   <h4>为客户提供方案规划、硬件系统集成施工、交互软件开发调试、影视制作、售后维护、技术支持等服务。，逐步形成了以强化地板为主，实木地板、实木复合地板等一体化的木地板研发、生产、销售与服务平台。</h4>
	        		       </a>
	        		   </div>
	        	   </div>
	        	   <div class="news_list_li clearfix">
	        	   	  <div class="rl"><span>18</span><p>2022-03</p></div>
	        	   	  <div class="text">
	        			<a href="javascript:;">
	        	   		<h3>资讯1</h3>
	        	   		<h4>为客户提供方案规划、硬件系统集成施工、交互软件开发调试、影视制作、售后维护、技术支持等服务。，逐步形成了以强化地板为主，实木地板、实木复合地板等一体化的木地板研发、生产、销售与服务平台。</h4>
	        	   	    </a>
	        		  </div>
	        	   </div>
	        	   <div class="news_list_li clearfix">
	        	   	  <div class="rl"><span>18</span><p>2022-03</p></div>
	        	   	  <div class="text">
	        			<a href="javascript:;">
	        	   		<h3>资讯1</h3>
	        	   		<h4>为客户提供方案规划、硬件系统集成施工、交互软件开发调试、影视制作、售后维护、技术支持等服务。，逐步形成了以强化地板为主，实木地板、实木复合地板等一体化的木地板研发、生产、销售与服务平台。</h4>
	        	   	    </a>
	        		</div>
	        	   </div>
	           </div>
	           <div class="clearfix"></div>
	           <div class="more"><a href="javascript:;">查看更多 》</a></div>
	        </div>
      </div>
		</div>
	</div>
</div>
</div>
<div id="footer">
  <div class="container">
	  <div class="footer clearfix">
		  <div class="footer_l layui-col-sm12 layui-col-md6">
			  <h2>网站导航</h2>
			  <ul class="clearfix">
				<li><a href="javascript:;" class="this">好奇首页</a></li>
				<li>
					<a href="javascript:;" class="this">关于我们</a>
					<a href="javascript:;">公司简介</a>
                    <a href="javascript:;">企业文化</a>
                    <a href="javascript:;">荣誉资质</a>
                    <a href="javascript:;">发展历程</a>
                    <a href="javascript:;">组织架构</a>
				</li>
				<li>
					<a href="javascript:;" class="this">公司产品</a>
					<a href="javascript:;">文字文字</a>
				    <a href="javascript:;">文字文字</a>
				    <a href="javascript:;">文字文字</a>
				    <a href="javascript:;">文字文字</a>
				    <a href="javascript:;">文字文字</a>
				</li>
				<li>
					<a href="javascript:;" class="this">项目案例</a>
					<a href="javascript:;">文字文字</a>
				    <a href="javascript:;">文字文字</a>
				    <a href="javascript:;">文字文字</a>
				    <a href="javascript:;">文字文字</a>
				    <a href="javascript:;">文字文字</a>
				</li>
				<li>
					<a href="javascript:;" class="this">新闻资讯</a>
					<a href="javascript:;">文字文字</a>
				    <a href="javascript:;">文字文字</a>
				    <a href="javascript:;">文字文字</a>
				    <a href="javascript:;">文字文字</a>
				    <a href="javascript:;">文字文字</a>
				</li>
				<li>
					<a href="javascript:;" class="this">联系我们</a>
					<a href="javascript:;">文字文字</a>
				    <a href="javascript:;">文字文字</a>
				    <a href="javascript:;">文字文字</a>
				</li>
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
				<li><img src="images/ewm.jpg"><p>微信二维码</p></li>
				<li><img src="images/ewm.jpg"><p>抖音二维码</p></li>
			  </ul>
		  </div>
	  </div>

  </div>
  <div class="footer_bot">©Copyright 2022-2022 版权所有 苏州好奇数字科技有限公司</div>
</div>
<div class="kf">
	<ul>
	  <li class="this"><a href="javascript:;"><img src="images/icon1.png"><p>电话咨询</p></a></li>
	  <li><a href="javascript:;"><img src="images/icon2.png"><p>QQ咨询</p></a></li>
	  <li><a href="javascript:;"><img src="images/icon3.png"><p>在线留言</p></a></li>
	  <li class="top"><a href="#c-nav"><img src="images/icon4.png"></a></li>
	</ul>
</div>
<script type="text/javascript" src="layui/layui.js"></script>
<script src="js/jquery-1.11.3.min.js" ></script>
<script type="text/javascript">

layui.use(['carousel', 'form','element'], function(){
  var carousel = layui.carousel
  ,form = layui.form
  ,element = layui.element

  //常规轮播
  carousel.render({
    elem: '#test1'
    ,arrow: 'always'
  });




});
</script>
</body>
</html>