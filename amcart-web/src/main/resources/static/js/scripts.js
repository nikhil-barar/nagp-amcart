jQuery(function($) {
"use strict";


 $(document).ready(function() {
/* disallow click in dropdown content */
$('.dd-wrapper, .dropdown-head').on('click', function(e) {
   e.stopPropagation();
});

/* custom select */
    $('.s-styled').customSelect();


/* bx slider for vertical 1 */
$('.vertical-bx-1').each(function(){
   $(this).data('bxslider', $(this).bxSlider({
      minSlides: 3,
      slideMargin:0,
      nextText: '<i class="arrow_carrot-right"></i>',
      prevText: '<i class="arrow_carrot-left"></i>',
      pager: false,
   }));
});
  


// Init mobile menu

   $('.mobile-menu').mobile_menu();


// Init Validation Engine

   if($.fn.validationEngine){
      $(".validation-engine").validationEngine({
         autoPositionUpdate: true
      });
   }


// Init ResponsiveSlides Slider for home-1


   function set_slides_count(current){
      var total = $(".home1-slider .rslides > li").length;
      $('.home1-slider .rslides-number').text(current+'/'+total);
   }

   if($.fn.responsiveSlides){
      $(".home1-slider .rslides").responsiveSlides({
        auto: true,             // Boolean: Animate automatically, true or false
        speed: 2000,            // Integer: Speed of the transition, in milliseconds
        timeout: 1000000,          // Integer: Time between slide transitions, in milliseconds
        pager: false,           // Boolean: Show pager, true or false
        nav: true,             // Boolean: Show navigation, true or false
        random: false,          // Boolean: Randomize the order of the slides, true or false
        pause: false,           // Boolean: Pause on hover, true or false
        pauseControls: true,    // Boolean: Pause when hovering controls, true or false
        prevText: '<i class="arrow_carrot-left"></i>',   // String: Text for the "previous" button
        nextText: '<i class="arrow_carrot-right"></i>',       // String: Text for the "next" button
        maxwidth: "",           // Integer: Max-width of the slideshow, in pixels
        navContainer: ".rslides_nav_block",       // Selector: Where controls should be appended to, default is after the 'ul'
        manualControls: "",     // Selector: Declare custom pager navigation
        namespace: "rslides",   // String: Change the default namespace used
        before: function(i){
           //set_slides_count(i);
        },   // Function: Before callback
        after: function(i){
           //var $currentSlide = $("." + this.namespace + "1_on"); 
           set_slides_count(i+1);
        }     // Function: After callback
      });
      
      
      set_slides_count(1);

      $('.rslides-container img').one('load', function() {
         $(this).closest('.rslides-container').removeClass('loading');
      }).each(function(){if(this.complete) $(this).load();});
   }




// Bootstrap Tooltip

  $('[data-toggle="tooltip"], [data-tooltip]').tooltip({ container: 'body' });






// ============================================================================
// Startup animations
// ============================================================================


   // Add animate class where data-animate attribute is set
   var startTopOfWindow = $(window).scrollTop();

   $('[data-animate]').each(function(){
      if($(this).offset().top + $(this).height() > startTopOfWindow)
         $(this).addClass('animated');
   });

   // Remove style for progress bars on start
   $('.progress-bar.animate').each(function(){
      var $this = $(this);
      // Remove progress-bar class and return it on timeout fo cancel transition animation
      $this.removeClass('progress-bar').css('width','');
      setTimeout(function(){$this.addClass('progress-bar')},10);
   });

   // Animate on page start
   setTimeout(function(){animate_on_scroll()}, 50);

   // Anitame elements on scroll
   $(window).on('bind', 'scroll.vmanimate', function() {
      animate_on_scroll();
   });

   // Remove classes after animate is complete
   $('.animated').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function(){
      $(this).removeClass('animated').removeClass($(this).data('animate'));
   });

   function animate_on_scroll()
   {
      var topOfWindow = $(window).scrollTop();
      var bottomOfWindow = topOfWindow + $(window).height();
      var $elms = $('.animated, .animate').each(function(){
         var $this = $(this);
         if ($this.offset().top + 100 < bottomOfWindow) 
         {
            if($this.hasClass('knob')) 
               animate_knob($this);
            else if($this.hasClass('progress-bar'))
               animate_progress($this);
            else if($this.hasClass('animate-number'))
               animate_number($this);
            else
               $this.addClass($this.data('animate'));
         }
      });
      if($elms.length == 0) $(window).unbind('scroll.vmanimate');
   }

   function animate_knob($knob)
   {
      // Animate once
      $knob.removeClass('animate');
      //console.log($knob)
      var myVal = parseInt($knob.val(),10);

      $({value: 0}).animate({value: myVal}, {
         duration: 1500,
         easing:'swing',
         step: function() {
            $knob.val(Math.ceil(this.value)).trigger('change');
         }
      });
   }

   function animate_progress($progress)
   {
      // Animate once
      $progress.removeClass('animate');

      //console.log($progress)
      var valStart = parseInt($progress.attr('aria-valuemin'),10);
      var myVal = parseInt($progress.attr('aria-valuenow'),10);
      var $value = $progress.parent().siblings('.value');

      // Transitions animation
      $progress.css({width: myVal+'%'});

      $({value: valStart}).animate({value: myVal}, {
         duration: 1500,
         easing:'swing',
         step: function() {
            $value.text(this.value.toFixed()+'%');
         }
      })
   }

   function animate_number($block)
   {
      // Animate once
      $block.removeClass('animate');

      //console.log($block)
      var valStart = 0;
      var myVal = parseInt($block.text().replace(' ',''),10);

      $({value: valStart}).animate({value: myVal}, {
         duration: 1500,
         easing:'swing',
         step: function() {
            $block.text(this.value.toFixed().replace(/\B(?=(\d{3})+(?!\d))/g, " "));
         }
      })
   }

// ============================================================================
// Megamenu
// ============================================================================


   $(".main-menu").accessibleMegaMenu({
           /* prefix for generated unique id attributes, which are required 
              to indicate aria-owns, aria-controls and aria-labelledby */
           uuidPrefix: "accessible-megamenu",
           /* css class used to define the megamenu styling */
           menuClass: "nav-menu",
           /* css class for a top-level navigation item in the megamenu */
           topNavItemClass: "nav-item",
           /* css class for a megamenu panel */
           panelClass: "sub-nav",
           /* css class for a group of items within a megamenu panel */
           panelGroupClass: "sub-nav-group",
           /* css class for the hover state */
           hoverClass: "hover",
           /* css class for the focus state */
           focusClass: "focus",
           /* css class for the open state */
           openClass: "open"
       });



// ============================================================================
// Bootsptap Dropdown Effect
// ============================================================================


   // Add slideup & fadein animation to dropdown
   $('.dropdown').on('show.bs.dropdown', function(e){
      var $dropdown = $(this).find('.dropdown-menu');
      var orig_margin_top = parseInt($dropdown.css('margin-top'),10);
      $dropdown.css({'margin-top': (orig_margin_top + 10) + 'px', opacity: 0}).animate({'margin-top': orig_margin_top + 'px', opacity: 1}, 300, function(){
         $(this).css({'margin-top':''});
      });
   });
   // Add slidedown & fadeout animation to dropdown
   $('.dropdown').on('hide.bs.dropdown', function(e){
      var $dropdown = $(this).find('.dropdown-menu');
      var orig_margin_top = parseInt($dropdown.css('margin-top'),10);
      $dropdown.css({'margin-top': orig_margin_top + 'px', opacity: 1, display: 'block'}).animate({'margin-top': (orig_margin_top + 10) + 'px', opacity: 0}, 300, function(){
         $(this).css({'margin-top':'', display:''});
      });
   });




// ============================================================================
// Dropdown products list handling
// ============================================================================


   // Remove all button
   $('.clear-all-btn a').on('click', function(e){
      e.preventDefault();
      var $products_list = $(this).closest('.dropdown-product-list');
      remove_all($products_list);
   });

   // Remove one product
   $('.ddr').on('click', function(e){
      e.preventDefault();
      var $product = $(this).closest('.dd-product-group');
      var $self = $(this);
      remove_product($product,$self);
   });

   function remove_product($product,$self)
   {
      var $products_list = $product.closest('.dropdown-product-list');
      var products_count = $products_list.find('.dd-product-group').length-1; // Todo Ajax answer

      // If last product remove all
      if(products_count == 0){
         remove_all($products_list);
         return;
      }

      console.log($products_list.attr('id')); // List ID
      // TODO Ajax remove one product

      update_products_count_amount($product, products_count, products_count ? '$700.00' : '$0.00');

      if(!$product.closest('table').hasClass('compare-table'))
      {
        $product.fadeOut(300, function(){
           $(this).remove();
        });
      }else
      {
        var index = $self.closest('td').index();
        if($self.closest('tr').find('td').length-2)
        {
          $self.closest('.dropdown-product-list').find('.dd-product-group').each(function(){//debugger;
            $(this).find('td').eq(index).fadeOut(300, function(){
              $(this).remove();
            });
          });
        }
        else
        {
          remove_all($products_list);
          return;
        }
      }
   }

   function remove_all($products_list)
   {
      console.log($products_list.attr('id')); // List ID
      // TODO Ajax remove all products

      $products_list.fadeOut(300, function() { $(this).empty(); }).siblings('.dd-list-empty').addClass('visible').fadeIn(300);
      update_products_count_amount($products_list, 0, '$0.00');
   }

   function update_products_count_amount($product, count, amount)
   {
      var $dropdown = $product.closest('.dropdown');
      $dropdown.find('.dd-products-count').text(count);
      $dropdown.find('.dd-products-price').text(amount );
   }





// ============================================================================
// Mobile Collapse
// ============================================================================

   var mobile_theshold = 992;
   
   $('.mobile-collapse-header').on('click', function(e){
      if(window.innerWidth < mobile_theshold){
         e.preventDefault();
         var $mobile_collapse = $(this).closest('.mobile-collapse');
         $mobile_collapse.toggleClass('opened');
         $mobile_collapse.find('.mobile-collapse-body').slideToggle(function(){
            var bxslider = $(this).find('.vertical-bx-1').data('bxslider');
            if(bxslider) bxslider.reloadSlider();
         });
      }         
   });


// ============================================================================
// Product list carousel
// ============================================================================

   
   $('.pl-ctl-left').on('click', function(e){
      e.preventDefault();
      change_page.call(this, -1);
   });
   $('.pl-ctl-right').on('click', function(e){
      e.preventDefault();
      change_page.call(this, 1);
   });

   function change_page(dir)
   {
      var $carousel = $(this).closest('.pl-carousel');
      var $pages_container = $carousel.find('.pl-pages');
      var $pages = $pages_container.find('>li');
      var $cur_page = $pages_container.find('>li.active');
      var next_index = $cur_page.index()+dir;
      // In loop
      if(next_index > $pages.length-1) next_index = 0;
      if(next_index < 0) next_index = $pages.length-1;
      var $next_page = $pages_container.find('>li').eq(next_index);

      //console.log('next_index:'+next_index)

      $cur_page.removeClass('active').addClass('animation')
         .one("webkitTransitionEnd otransitionend oTransitionEnd msTransitionEnd transitionend", function() {
            $(this).removeClass('animation');

            $next_page.addClass('animation');

            setTimeout(function() {
               $next_page.addClass('active')
                  .one("webkitTransitionEnd otransitionend oTransitionEnd msTransitionEnd transitionend", function() {
                     $(this).removeClass('animation');
               });
            }, 10);
            
      });
   }



// ============================================================================
// News list load more
// ============================================================================


   $('.news-loadmore').on('click', function(e){
      e.preventDefault();
      var $this = $(this);
      $this.find('i').addClass('spin-cc');
      setTimeout(function() {
         var $news_container = $this.closest('.news-list').find('.news-container');
         var $new_items = $news_container.find('.news-item').clone().slice(0,3).appendTo($news_container);
         $new_items.addClass('animate');
         setTimeout(function() {
            $new_items.addClass('scale');
         }, 10);
         $this.find('i').removeClass('spin-cc');
      }, 500);
   });



// ============================================================================
// Product list load more
// ============================================================================

   $('.products-loadmore').on('click', function(e){
      e.preventDefault();
      var $this = $(this);
      $this.find('i').addClass('spin-cc');
      setTimeout(function() {
         var $products_container = $this.closest('.products-list').find('li.active .row');
         var $new_items = $products_container.find('div.pl-item').clone().slice(0,4).appendTo($products_container);
         $new_items.addClass('animate');
         setTimeout(function() {
            $new_items.addClass('scale');
         }, 10);
         $this.find('i').removeClass('spin-cc');
      }, 500);
   });

// ============================================================================
// new Product list load more
// ============================================================================

   $('.best-product .news-loadmore.new-products').on('click', function(e){
      e.preventDefault();
      var $this = $(this);
      $this.find('i').addClass('spin-cc');
      setTimeout(function() {
         var $products_container = $this.closest('.products-list.new-products').find('.pl-pages');
         var $new_items = $products_container.find('li').clone().slice(0,1).appendTo($products_container);
         $new_items.addClass('animate');
         setTimeout(function() {
            $new_items.addClass('scale');
         }, 10);
         $this.find('i').removeClass('spin-cc');
      }, 500);
   });


// ============================================================================
// sale Product list load more
// ============================================================================

   $('.news-loadmore.sales-products').on('click', function(e){
      e.preventDefault();
      var $this = $(this);
      $this.find('i').addClass('spin-cc');
      setTimeout(function() {
         var $products_container = $this.closest('.products-list.sales-products').find('li.active .row');
         var $new_items = $products_container.find('div.pl-item').clone().slice(0,6).appendTo($products_container);
         $new_items.addClass('animate');
         setTimeout(function() {
            $new_items.addClass('scale');
         }, 10);
         $this.find('i').removeClass('spin-cc');
      }, 500);
   });


// ============================================================================
// shop by flower carousel
// ============================================================================

// thumbnails.carousel.js jQuery plugin
(function(window, $, undefined) {

  var conf = {
    center: true,
    backgroundControl: false
  };

  var cache = {
    $carouselContainer: $('.thumbnails-carousel').parent(),
    $thumbnailsLi: $('.thumbnails-carousel li'),
    $controls: $('.thumbnails-carousel').parent().find('.carousel-control')
  };

  function init() {
    cache.$carouselContainer.find('ol.carousel-indicators').addClass('indicators-fix');
    cache.$thumbnailsLi.first().addClass('active-thumbnail');

    if(!conf.backgroundControl) {
      cache.$carouselContainer.find('.carousel-control').addClass('controls-background-reset');
    }
    else {
      cache.$controls.height(cache.$carouselContainer.find('.carousel-inner').height());
    }

    if(conf.center) {
      //cache.$thumbnailsLi.wrapAll("<div class='center clearfix'></div>");
      cache.$thumbnailsLi.wrapAll("<div class='clearfix'></div>");
    }
  }

  function refreshOpacities(domEl) {
    cache.$thumbnailsLi.removeClass('active-thumbnail');
    cache.$thumbnailsLi.eq($(domEl).index()).addClass('active-thumbnail');
  } 

  function bindUiActions() {
    cache.$carouselContainer.on('slide.bs.carousel', function(e) {
        refreshOpacities(e.relatedTarget);
    });

    cache.$thumbnailsLi.on('click', function(){
      cache.$carouselContainer.carousel($(this).index());
    });
  }

  $.fn.thumbnailsCarousel = function(options) {
    conf = $.extend(conf, options);

    init();
    bindUiActions();

    return this;
  }

})(window, jQuery);

$('.thumbnails-carousel').thumbnailsCarousel();




// ============================================================================
// btn-plus and btn-minus in "#order-detail-content" table
// ============================================================================

  $('.btn-plus').on('click', function () {
    var $count = $(this).parent().find('.count');
    var val = parseInt($count.val(),10);
    $count.val(val+1);
    return false;
  });

  $('.btn-minus').on('click', function () {
    var $count = $(this).parent().find('.count');
    var val = parseInt($count.val(),10);
    if(val > 0) $count.val(val-1);
    return false;
  });


// ============================================================================
// Price range filters init
// ============================================================================

$(function() {
   if(!$.fn.slider) return;

   $( ".price-range-selector" ).each(function(){
      var $price_label = $(this).siblings('.wgpf-label').find('.price-range-label');
      var cur_sign = $price_label.data('currency-sign');
      var cursign_before = $price_label.data('cursign-before');
      $(this).slider({
         range: true,
         min: $(this).data('min'),
         max: $(this).data('max'),
         values: [ 0, $(this).data('max') ],
         slide: function( event, ui ) {
            set_range_label(ui.values[ 0 ], ui.values[ 1 ]);
         }
      });

      function set_range_label(value1, value2){
         if(cursign_before)
            $price_label.text( cur_sign + value1 + " - " + cur_sign + value2 );
         else
            $price_label.text( value1 + cur_sign + " - " + value2 + cur_sign);
      }

      set_range_label($(this).data('min'), $(this).data('max'));
   });   
});


// ============================================================================
// customized audio player
// ============================================================================

$(function() {
   if($.fn.mediaelementplayer == undefined) return;
   $('audio.custom-audio-player').mediaelementplayer();
});

// ============================================================================
// bxSlider для зума
// ============================================================================
	
	$(window).load(function(){
		
	   if($('.moving-hover-line').length == 0) return;
	   $('.moving-hover-line >li:not(.hover-line)').hover(function(){
		  go_to_item($(this));
	   },function(){
		  go_to_item($(this).siblings('.active'));
	   });
	   go_to_item($('.moving-hover-line .active')); // Init line for active item

	   function go_to_item($item)
	   {
		  var offset = $item.offset().left - $item.parent().offset().left;
		  $item.siblings('.hover-line').css({'left' : offset, 'width': $item.width()});
	   }
		
	});


	
	 //Галерея для странички product-page.html
	  $('#thumblist').bxSlider({
		minSlides: 1,
		maxSlides: 4,
		slideWidth: 120,
		infiniteLoop: false,
		hideControlOnEnd: true,
		slideMargin: 10,
		pager: false,
		nextText: "",
		prevText: "",
		moveSlides: 1
	  });
	
	
	// ============================================================================
// elevateZoom
// ============================================================================


  $(function() {

  if($.fn.elevateZoom == undefined) return;


  //Инициализация зума при клике на кнопку pl-qview сразу после открытия модального окна
   $('.pl-qview').on('click', function(){
      var target_id = $(this).data('target');
      window.inetrvalID = window.setInterval(function(){
        var bolean = !!$(target_id).has('.zoomWrapper').length;
        var visible = $(target_id+" .qview_zoom").is(":visible");
        console.info("Прошло 200 мсек");
        console.info('visible = ',visible);
        if(!bolean && visible ){
            $(target_id+" .qview_zoom").elevateZoom({
              zoomType: "lens", 
              inWrapper: true,
              imageCrossfade: true,
              responsive: true,
              gallery: target_id.slice(1)+'-thumblist', //id-галереи зума начинается с id модального окна
              cursor: 'pointer', 
              galleryActiveClass: "zoomThumbActive"
            });
            qview_slider[target_id.slice(1)].goToPrevSlide();
        }
        else if (bolean)
        {
            clearInterval(window.inetrvalID);
            return false;
        }
      },200);
   });
  

  //Инициализация галерей для странички product-page.html
  

    if(window_width >= 1200){
      window.elevate_zoom_big = true;
    //initiate the plugin and pass the id of the div containing gallery images 
      zoom_03 = $("#zoom_03").elevateZoom({
        zoomType: "window",
        zoomWindowHeight: 673,
        zoomWindowWidth: 530,
        zoomWindowPosition: "zoom-window-container",
        zoomWindowOffetx: -30,
        zoomWindowOffety: 0,
        borderSize: 6,
        lensBorder: 0,
        lensColour: "black",
        lensOpacity: 0.6,
        borderColour: "#e1e3e6",
        containLensZoom: true, 
        imageCrossfade: true,
        responsive: true,
        gallery:'thumblist', 
        cursor: 'pointer', 
        galleryActiveClass: "zoomThumbActive"
      });

      
    }else
    {
      window.elevate_zoom_big = false;
        zoom_03 = $("#zoom_03").elevateZoom({
        zoomType: "lens", 
        inWrapper: true,
        responsive: true,
        imageCrossfade: true,
        gallery:'thumblist', 
        cursor: 'pointer', 
        galleryActiveClass: "zoomThumbActive"
      });
    }
    console.info("zoom = ",$('#zoom_04').html(),$('#zoom_03').html());
    console.info("elevate_zoom_big = ", elevate_zoom_big);

    //перезагрузка страницы при изменении размера окна для смены режима зума.
    if( $('#zoom_03').html() != undefined || $('#zoom_04').html() != undefined)
    {
      $(window).resize(function(){//debugger;
        console.info("elevate_zoom_big = ", elevate_zoom_big);

        if($(window).width() <= 1200 && elevate_zoom_big)
        {
          window.elevate_zoom_big = false;
          window.setTimeout(function(){location.reload()},0);
        }
        else if($(window).width() > 1200 && !elevate_zoom_big)
        {
          window.elevate_zoom_big = true;
          window.setTimeout(function(){location.reload()},0);
        }
      });
    }


  });
	
	var zoom_03,zoom_04;

  $('#product-pupGallery-button').on('click', function(){

      var target_id = $(this).data('target');
      window.inetrvalID2 = window.setInterval(function(){
      var bolean = !!$(target_id).has('.zoomWrapper').length;
        console.info("Прошло 200 мсек");
        if(!bolean && $(target_id+" #zoom_04").is(":visible")){
            zoom_04 = $(target_id+" #zoom_04").elevateZoom({
              zoomType: "lens", 
              inWrapper: true,
              imageCrossfade: true,
              responsive: true,
              gallery: 'thumblist2',
              cursor: 'pointer', 
              galleryActiveClass: "zoomThumbActive"
            });
        }
        else if (bolean)
        {
            clearInterval(window.inetrvalID2);
            return false;
        }
      },200);
   });
 

  var window_width = $(window).width();
  
  //Вертикальные галереи для модального окна quick view на страничке 404.html
  if(window_width > 768)
  {
    window.qview_big = true;
    var qview_slider = [];
    var collection_qview_slider = $('.qview-thumblist').each(function(index){
      var id = $(this).closest('.qview').attr('id');
      var obj = $(this).bxSlider({
          minSlides: 5,
          maxSlides: 5,
          slideWidth: 80,
          infiniteLoop: false,
          hideControlOnEnd: true,
          startSlide: 1,
          slideMargin: 10,
          pager: false,
          nextText: "",
          prevText: "",
          moveSlides: 1,
          mode: "vertical",
          adaptiveHeight: false,
          onSliderLoad: function(currentIndex){
          }
        });
      console.info("id = ",id);
      console.info("obj = ",obj);
      qview_slider[id] = obj;
    });
  } else //Или горизонтальня алерея для модального окна quick view на страничке 404.html для маленьких разрешений
  {

      window.qview_big = false;
      var qview_slider = [];
      var collection_qview_slider = $('.qview-thumblist').each(function(index){
        var id = $(this).closest('.qview').attr('id');
        var obj = $(this).bxSlider({
            minSlides: 1,
            maxSlides: 5,
            slideWidth: 80,
            infiniteLoop: false,
            hideControlOnEnd: true,
            startSlide: 1,
            slideMargin: 10,
            pager: false,
            nextText: "",
            prevText: "",
            moveSlides: 1,
            mode: "horizontal",
            adaptiveHeight: false,
            onSliderLoad: function(currentIndex){ // your code here 
            }
          });
      qview_slider[id] = obj;
      });
  }

  //Принудительно смещение к началу списка при инициализации галереи.
  //$('#qview-thumblist .bx-next').css({"transform":"translate3d(0px, 0px, 0px)"});

  //Проверка на существование галереи для быстрого просмотра и если существует 
  //то смена горизантльного режима на вертикаьный и наоборот при размере окна 768px
  if($(".qview-thumblist").html() != undefined)
  {
    $(window).resize(function(){
      console.info("qview_big = ", qview_big);

      if($(window).width() <= 768 && qview_big)
      {
        window.qview_big = false;
        for (var key in qview_slider)
        {
          qview_slider[key].reloadSlider({
              minSlides: 1,
              maxSlides: 5,
              slideWidth: 80,
              infiniteLoop: false,
              hideControlOnEnd: true,
              slideMargin: 10,
              pager: false,
              nextText: "",
              prevText: "",
              moveSlides: 1,
              mode: "horizontal",
              adaptiveHeight: false,
              onSliderLoad: function(currentIndex){ // your code here 
              console.info("this.mode",this.mode);
              }
            });
        }
      }
      else if($(window).width() > 768 && !qview_big)
      {
        window.qview_big = true;
        for (var key in qview_slider)
        {
          qview_slider[key].reloadSlider({
            minSlides: 5,
            maxSlides: 5,
            slideWidth: 80,
            infiniteLoop: false,
            hideControlOnEnd: true,
            slideMargin: 10,
            pager: false,
            nextText: "",
            prevText: "",
            moveSlides: 1,
            mode: "vertical",
            adaptiveHeight: false,
            onSliderLoad: function(currentIndex){ // your code here 
            console.info("this.mode",this.mode);
            }
          });
        }
      }
    });
  }




   // ============================================================================
   // Клик по ссылке "Add review" : плавная прокуртка и открытие вкладки
   // ============================================================================

  $('a[href=#review-btn]').on('click', function(){
      var target = $(this).attr('href');
      $('html, body').animate({scrollTop: $(target).offset().top - 85}, 500);
      $('#review-btn').click();
      return false; 
  }); 


// ============================================================================
// Подсчет столбцов на странице сравнения
// ============================================================================

var table_width = $('.compare-table').width();
var product_count = 0;
$('.compare-table tr:first-child td').each(function(){product_count++;});
product_count -= 2;
console.info("product_count = ", product_count);
var td_width = 100/product_count;
console.info("td_width = ", td_width);
$('.compare-table td').css({width:''+td_width+'%'});




// ============================================================================
// FastClick init
// ============================================================================
$(function() {
   if(typeof(FastClick) == "undefined") return;  // Check whether fastlick was loaded
   FastClick.attach(document.body);
});


// ============================================================================
// home v2 slide image toggle
// ============================================================================

jQuery('.rslides .slider-container .slide-product > button').on('click', function(){
  jQuery(this).parent().find('.slide-product-img').toggle();
});

});
});

// ============================================================================
// home v5 style images
// ============================================================================

jQuery('.shop-style-list .style-list .list-item').removeAttr('href');
jQuery('.shop-style-list .style-list .list-item').on('click', function(e){
  e.preventDefault();

  var imageURI = jQuery(this).attr('data-id');

  $( '.shop-range #'+imageURI+' .carousel-inner .item' ).each(function( index ) {
      var bigImgAtt =  $(this).find('img').attr('src');

      jQuery('.shop-range #shop-by-flower .carousel-inner .item:nth-child('+(index+1)+')').find('img').attr('src',bigImgAtt);

    });


    $( ".shop-range #"+imageURI+" ul.clearfix div li" ).each(function( index ) {
      var imgAtt =  $(this).find('img').attr('src');

      jQuery('.shop-range #shop-by-flower .thumbnails-carousel div li:nth-child('+(index+1)+')').find('img').attr('src',imgAtt);

    });
  });


// Testimonials
  //Set the carousel options
  $('#quote-carousel').carousel({
    pause: true,
    interval: 4000,
  });