$(function(){
	     let widthUl = $('.ys_list li')
	     let imgWidth =$('.ys_list li .ys_img' ) 
	     let whwidthUl =$('.wh_list li')
	     let whimgWidth =$('.wh_tnt div')
	     let wha =$('.wh_tnt a')
	     let whh =$('.wh_tnt h3')
				if($(window).width()>= 1000){
					if(widthUl.length == 5){
					widthUl.css({"width":"20%"})
				}else if(widthUl.length == 6){
					widthUl.css({"width":"16.66%"})
					imgWidth.css({"width":"140px", "height":"140px","line-height": "140px"})
					
				}else if(widthUl.length == 4){
					widthUl.css({"width":"25%"})
					imgWidth.css({"width":"200px", "height":"200px","line-height": "200px"})
				}
				
				if(whwidthUl.length == 5){
					whwidthUl.css({"width":"20%"})
					whimgWidth.addClass('imgWidth2');
					wha.addClass('imgWidth2');
					whh.addClass('imgWidth2');
				}else if(whwidthUl.length == 6){
					whwidthUl.css({"width":"16.66%"})
					whimgWidth.addClass('imgWidth1');
					wha.addClass('imgWidth1');
					whh.addClass('imgWidth1');
					wha.css({"padding":"10px"})
				}else if(whwidthUl.length == 4){
					whwidthUl.css({"width":"25%"})
					whimgWidth.addClass('imgWidth');
					wha.addClass('imgWidth');
					whh.addClass('imgWidth');
				}
					
				}else if($(window).width()<= 1000 && $(window).width()>=800){
					widthUl.css({"width":"33.333%"})
					whwidthUl.css({"width":"33.333%"})
					imgWidth.css({"width":"200px", "height":"200px","line-height": "200px"})
					whimgWidth.addClass('imgWidth');
					wha.addClass('imgWidth');
					whh.addClass('imgWidth');
				}else if($(window).width()< 800 && $(window).width()>=500){
					widthUl.css({"width":"50%"})
					imgWidth.css({"width":"180px", "height":"180px","line-height": "180px"})
					whimgWidth.addClass('imgWidth');
					wha.addClass('imgWidth');
					whh.addClass('imgWidth');
				}else{
					widthUl.css({"width":"50%"})
					imgWidth.css({"width":"120px", "height":"120px","line-height": "120px"})
					whimgWidth.addClass('imgWidth3');
					wha.addClass('imgWidth3');
					whh.addClass('imgWidth3');
				}
})

