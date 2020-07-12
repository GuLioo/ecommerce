"use strict";

// *** General Variables 最新最全最好的Bootstrap模板：http://www.bootstrapmb.com*** //
var $window = $(window),
	$document = $(document),
	$this = $(this),
	$html = $("html"),
	$body = $("body");


// *** On ready *** //
$document.on("ready", function () {
	responsiveClasses();
	imageBG();
	fitVideos();
	lightboxImage();
	lightboxGallery();
	lightboxIframe();
	scrollToAnchor();
	menuCategories();
	sliderFeatured1();
	sliderProducts1();
	sliderProducts2();
	listSelectCategoryText();
	listSelectCategory();
	listOptionsSelect();
	sliderPriceRange();
	listLanguages();
	mobileCartMiniToggle();
	itemClickCounter();
	productSingleTabs();
	listProductsLayoutTabs();
	productEqualHeights();
	optionsSelect2();
	stickyHeaderBar();
	fullCategories();
	sliderImageBG();
	optimizeSliderImageBG();	
});


// *** On load *** //
$window.on("load", function () {
	popupNewsletter();
})

	// *** On resize *** //
	.on("resize", function () {
		responsiveClasses();
		productSingleTabs();
		listProductsLayoutTabs();
	})

	// *** On scroll *** //
	.on("scroll", function () {
		scrollTopIcon();
		stickyHeaderBar();
	});



// *** Responsive Classes *** //
function responsiveClasses() {
	var jRes = jRespond([
		{
			label: "smallest",
			enter: 0,
			exit: 479
		}, {
			label: "handheld",
			enter: 480,
			exit: 767
		}, {
			label: "tablet",
			enter: 768,
			exit: 991
		}, {
			label: "laptop",
			enter: 992,
			exit: 1199
		}, {
			label: "desktop",
			enter: 1200,
			exit: 10000
		}
	]);
	jRes.addFunc([
		{
			breakpoint: "desktop",
			enter: function () { $body.addClass("device-lg"); },
			exit: function () { $body.removeClass("device-lg"); }
		}, {
			breakpoint: "laptop",
			enter: function () { $body.addClass("device-md"); },
			exit: function () { $body.removeClass("device-md"); }
		}, {
			breakpoint: "tablet",
			enter: function () { $body.addClass("device-sm"); },
			exit: function () { $body.removeClass("device-sm"); }
		}, {
			breakpoint: "handheld",
			enter: function () { $body.addClass("device-xs"); },
			exit: function () { $body.removeClass("device-xs"); }
		}, {
			breakpoint: "smallest",
			enter: function () { $body.addClass("device-xxs"); },
			exit: function () { $body.removeClass("device-xxs"); }
		}
	]);
}


// *** RTL Case *** //
var HTMLDir = $("html").css("direction"),
	owlRtl,
	selectRtl;

// If page is RTL
if (HTMLDir == "rtl") {
	// Owl Carousel
	owlRtl = true;
	selectRtl = "rtl";
} else {
	owlRtl = false;
	selectRtl = false;
}


// *** Image Background *** //
function imageBG() {
	$(".img-bg").each(function () {
		var $this = $(this),
			imgSrc = $this.find("img").attr("src");

		if ($this.parent(".section-image").length) {
			$this.css("background-image", "url('" + imgSrc + "')");
		} else {
			$this.prepend("<div class='bg-element'></div>");
			var bgElement = $this.find(".bg-element");
			bgElement.css("background-image", "url('" + imgSrc + "')");
		}
		$this.find("img").css({ "opacity": 0, "visibility": "hidden" });
	});
}


// *** Fit Videos *** //
function fitVideos() {
	$("#full-container").fitVids();
}



// *** Lightbox Iframe *** //
function lightboxIframe() {
	$(".lightbox-iframe").magnificPopup({
		type: 'iframe',
		mainClass: 'mfp-fade',
		removalDelay: 160,
		preloader: false,
		fixedContentPos: false
	});
}


// *** Lightbox Image *** //
function lightboxImage() {
	$(".lightbox-img").magnificPopup({
		type: 'image',
		gallery: {
			enabled: false
		},
		mainClass: 'mfp-fade',
		removalDelay: 160,
		preloader: false,
		fixedContentPos: false
	});
}


// *** Lightbox Gallery *** //
function lightboxGallery() {
	$(".lightbox-gallery").magnificPopup({
		type: 'image',
		gallery: {
			enabled: true
		},
		mainClass: 'mfp-fade',
		removalDelay: 160,
		preloader: false,
		fixedContentPos: false
	});
}


// *** Scroll Top Icon *** //
function scrollTopIcon() {
	var windowScroll = $(window).scrollTop();
	if (windowScroll > 800) {
		$(".scroll-top-icon").addClass("show");
	} else {
		$(".scroll-top-icon").removeClass("show");
	}
}

$(".scroll-top").on("click", function (e) {
	e.preventDefault();
	$("html, body").animate({
		scrollTop: 0
	}, 1200); //1200 easeInOutExpo
});


// *** Scroll To Anchor *** //
function scrollToAnchor() {
	var stickyBar = $(".header-bar.sticky"),
		stickyBarHeight = stickyBar.height(),
		offsetDifference = (!stickyBar) ? 0 : stickyBarHeight;

	$(".scroll-to").on("click", function (e) {
		e.preventDefault();
		var $anchor = $(this);

		// scroll to specific anchor
		$("html, body").stop().animate({
			scrollTop: $($anchor.attr("href")).offset().top - offsetDifference
		}, 1200, "easeInOutExpo");
	});
}


// *** Menu Categories *** //
function menuCategories() {
	// Firing Superfish plugin
	$(".menu-categories").superfish({
		popUpSelector: "ul",
		cssArrows: true,
		delay: 0,
		speed: 200,
		speedOut: 200,
		animation: { opacity: "show", marginRight: 0 }, //  , height : "show"
		animationOut: { opacity: "hide", marginRight: -20 }
	});
}


// *** Slider Image BG *** //
function sliderImageBG() {
	$(".slider-img-bg .owl-item > li").each(function () {
		var $this = $(this),
			imgSrc = $this.find(".slide").children("img").attr("src");
		$this.prepend("<div class='bg-element'></div>");
		var bgElement = $this.find("> .bg-element");
		bgElement.css("background-image", "url('" + imgSrc + "')");
	});
}


// *** Optimize Slider Image BG *** //
function optimizeSliderImageBG() {
	$(".slider-img-bg").each(function () {
		var imgHeight = $(this).closest("div").height();

		if ($(".banner-parallax").children(".banner-slider").length > 0) {
			// $( ".banner-parallax, .banner-parallax .row > [class*='col-']" ).height( $( ".banner-slider" ).height() );
		}

		$(this).find(".owl-item > li .slide").children("img").css({
			"display": "none",
			"height": imgHeight,
			"opacity": 0
		});
	});
}


// *** Slider Boxes Features *** //
function sliderFeatured1() {
	var sliderFeatured1 = $(".slider-featured-1 > .owl-carousel");

	sliderFeatured1.owlCarousel({
		// items: 3,
		rtl: owlRtl,
		autoplay: false,
		autoplaySpeed: 500, // Sliding autoplay speed
		autoplayTimeout: 5000, // Autoplay interval timeout.
		dragEndSpeed: 350, // Sliding speed by mouse drag
		autoplayHoverPause: true, // Stop autoplay on mouse hover
		loop: false,
		slideBy: 1, // Number of item are slided for each one transition
		margin: 10, // space between each item. Very useful!
		stagePadding: 0, // This used to see part of left an right items as preview style
		nav: true, // show prev and next buttons
		dots: true, // Show dots navigation
		navText: ["<i class=\"fa fa-chevron-up\"></i>", "<i class=\"fa fa-chevron-down\"></i>"], // prev and next buttons content
		responsive: {
			0: {
				items: 1
			},
			480: {
				items: 1
			},
			// 600 : {
			//     items : 2
			// },
			768: {
				items: 1
			},
			1024: {
				items: 1
			}
		},
		autoHeight: true,
		autoWidth: false,
		// animateOut: 'goDownOut',
		// animateIn: 'goDownIn',
		navRewind: true,
		center: false, // It's used to make the carousel start from the centered item
		dotsEach: 1, // Number of items per dot
		dotData: false,
		lazyLoad: false, // img tag needs class="owl-lazy" and data-src="url-to-img" or/and data-src-retina="url-to-highres-img"
		smartSpeed: 600, // Sliding speed when hover next or prev nav
		fluidSpeed: 5000,
		navSpeed: 400,
		// fallbackEasing: "ease-in-out",
		dotsSpeed: 400 // Sliding speed by using dots
	});
}


// *** Slider Products 1 *** //
function sliderProducts1() {
	var sliderProducts1 = $(".slider-products-1 > .owl-carousel");

	$(sliderProducts1).each(function () {
		sliderProducts1.owlCarousel({
			// items: 3,
			rtl: owlRtl,
			autoplay: false,
			autoplaySpeed: 500, // Sliding autoplay speed
			autoplayTimeout: 5000, // Autoplay interval timeout.
			dragEndSpeed: 350, // Sliding speed by mouse drag
			autoplayHoverPause: true, // Stop autoplay on mouse hover
			loop: false,
			slideBy: 1, // Number of item are slided for each one transition
			margin: -1, // space between each item. Very useful!
			stagePadding: 0, // This used to see part of left an right items as preview style
			nav: true, // show prev and next buttons
			dots: false, // Show dots navigation
			navText: ["<i class=\"fa fa-chevron-up\"></i>", "<i class=\"fa fa-chevron-down\"></i>"], // prev and next buttons content
			responsive: {
				0: {
					items: 1
				},
				480: {
					items: 1
				},
				600: {
					items: 2
				},
				768: {
					items: 2
				},
				1024: {
					items: 3
				},
				1200: {
					items: 4
				}
			},
			autoHeight: true,
			autoWidth: false,
			// animateOut: 'goDownOut',
			// animateIn: 'goDownIn',
			navRewind: true,
			center: false, // It's used to make the carousel start from the centered item
			dotsEach: 1, // Number of items per dot
			dotData: false,
			lazyLoad: false, // img tag needs class="owl-lazy" and data-src="url-to-img" or/and data-src-retina="url-to-highres-img"
			smartSpeed: 600, // Sliding speed when hover next or prev nav
			fluidSpeed: 5000,
			navSpeed: 400,
			// fallbackEasing: "ease-in-out",
			dotsSpeed: 400 // Sliding speed by using dots
		});
	});
}


function productEqualHeights() {
	$(".slider-products-1").each(function () {
		var $this = $(this),
			boxProduct = $this.find(".box-product");

		var maxHeight = 0;
		boxProduct.each(function () {
			var thisH = $(this).outerHeight();
			if (thisH > maxHeight) { maxHeight = thisH; }
		});
		boxProduct.css("min-height", maxHeight);
	});
}


// *** List Select Category Text *** //
function listSelectCategoryText() {
	var listLink = $(".menu-select-category li a");

	listLink.on("click", function () {
		var $this = $(this);
		$this.each(function () {
			var listLinkText = $this.text();
			$this.closest(".list-select-category").find(".btn-select-category").text(listLinkText);
		});
	});
}


// *** List Select Category *** //
var btnSelectCategory = $(".btn-select-category"),
	selectCategory = $(".list-select-category");
function listSelectCategory() {
	btnSelectCategory.on("click", function (e) {
		if ($window.width() < 768) {
			e.preventDefault();
			selectCategory.toggleClass("is-opened");
		}
	});
}

$window.on("resize", function () {
	if ($window.width() > 768) {
		selectCategory.removeClass("is-opened");
	}
});


// *** List Options Select *** //
function listOptionsSelect() {
	$(".list-options-select li").each(function () {
		var $this = $(this);
		$this.on("click", function () {
			$this.closest(".list-options-select").find("li").removeClass("is-active");
			$this.addClass("is-active");
		});
	});
}


// *** Slider Price Range *** //
function sliderPriceRange() {
	if (jQuery('#price_range').length > 0) {
		$('#price_range').slider();
	}
}


// *** List Languages *** //
var dropdownLangs = $(".dropdown-languages");
function listLanguages() {
	dropdownLangs.on("click", function (e) {
		if ($window.width() < 768) {
			e.preventDefault();
			$(this).toggleClass("is-opened");
		}
	});
}

$window.on("resize", function () {
	if ($window.width() > 768) {
		dropdownLangs.removeClass("is-opened");
	}
});


// *** Mobile Cart Mini Toggle *** //
var mobileBtnCart = $(".mobile-btn-cart"),
	mobileCartBox = mobileBtnCart.next(".cart-box");
function mobileCartMiniToggle() {
	mobileBtnCart.on("click", function (e) {
		if ($window.width() < 768) {
			e.preventDefault();
			mobileCartBox.toggleClass("is-opened");
		}
	});
}

$window.on("resize", function () {
	if ($window.width() > 768) {
		mobileCartBox.removeClass("is-opened");
	}
});


// *** Cart Notification *** //
function cartNotification() {
	var btnAddToCart = $(".btn-add-to-cart"),
		boxNotification = $(".box-notification"),
		closeMessage = boxNotification.find(".close");

	btnAddToCart.on("click", function (e) {
		e.preventDefault();
		boxNotification.addClass("is-opened");

		setTimeout(function () {
			$(boxNotification.removeClass("is-opened"));
		}, 6000);
	});

	closeMessage.on("click", function (e) {
		e.preventDefault();
		$(this).closest(boxNotification).removeClass("is-opened");
	});
}

cartNotification();


// Hide search product input placeholder
var spInput = $("#form-search-products").find("input[type=text]"),
	spInputPlaceholder = spInput.attr("placeholder"),
	spInputAltPlaceholder = spInput.data("alt-placeholder"),
	searchProducts = $(".search-products"),
	iconsMeta = $(".icons-meta");
function searchProductsInput() {

	spInput.on("focus", function () {
		if ($window.width() < 600) {
			spInput.attr("placeholder", spInputPlaceholder);
			searchProducts.addClass("input-focused");
			iconsMeta.addClass("input-focused");
		}
	});

	spInput.on("blur", function () {
		if ($window.width() < 600) {
			spInput.attr("placeholder", spInputAltPlaceholder);
			searchProducts.removeClass("input-focused");
			iconsMeta.removeClass("input-focused");
		}
	});

	if ($window.width() < 480) {
		spInput.attr("placeholder", spInputAltPlaceholder);
	} else {
		spInput.attr("placeholder", spInputPlaceholder);
		searchProducts.removeClass("input-focused");
		iconsMeta.removeClass("input-focused");

	}
}

searchProductsInput();

$window.on("resize", function () {
	searchProductsInput();
});



// Disabling some links default behaviour
$(".btn-select-category, .menu-select-category > li > a, .btn-dropdown-categories").on("click", function (e) {
	e.preventDefault();
});


// *** Mobile Menu Categories *** //
function mobileMenuCategories() {
	$("body").append("<div class='popup-preview-overlay'>");

	$(".menu-categories").children().clone().appendTo(".mobile-menu-categories");

	$(".popup-preview-overlay").on("click", function (e) {
		e.preventDefault();
		$(".popup-preview-overlay").toggleClass("viewed");
		$(".side-full-categories").removeClass("viewed");
		$("html").toggleClass("scroll-lock");
	});

	$(".mobile-menu-categories a").each(function (e) {
		if ($(this).next(".sub-menu").length) {
			// $( this ).addClass( "ddddddd" );
			$(this).closest("li").addClass("has-ul");
		}
	});

	$(".mobile-menu-categories a").on("click", function (e) {
		var $this = $(this);
		if ($this.next(".sub-menu").length) {
			e.preventDefault();
			if ($this.next().hasClass("viewed")) {
				$this.next().removeClass("viewed");
				$this.parent().find(".active").removeClass("active");
				$this.next().slideUp(250);
			} else {
				$this.parent().parent().find(".active").removeClass("active");
				$this.parent("ul").find(".active").removeClass("active");
				$this.parent().parent().find("li .sub-menu").removeClass("viewed");
				$this.parent().parent().find("li .sub-menu").slideUp(250);
				$this.toggleClass("active");
				$this.next().toggleClass("viewed");
				$this.next().slideToggle(250);
			}
		}
	});
}

mobileMenuCategories();

// *** List Full Categories *** //
function fullCategories() {
	var btnDropdownCat = $(".btn-dropdown-categories"),
		sideFullCat = $(".side-full-categories");
	btnDropdownCat.on("click", function (e) {
		e.preventDefault();
		if ($window.width() < 992) {
			sideFullCat.addClass("viewed");
			$(".popup-preview-overlay").addClass("viewed");
			$html.addClass("scroll-lock");
		} else {
			$html.removeClass("scroll-lock");
		}
	});
}


// *** Sticky Nav *** //
function stickyHeaderBar() {
	var windowScroll = $(window).scrollTop(),
		headerBar = $(".header-bar");

	headerBar.each(function () {
		var $this = $(this);

		if ($this.hasClass("sticky")) {
			if (windowScroll > $this.offset().top) {
				$this.addClass("is-sticky");
				// logo.attr( "src" , logoSrc );
			} else {
				$this.removeClass("is-sticky");
			}
		}
	});
}



// *** Slider Products 2 *** //
function sliderProducts2() {
	var sliderProducts2 = $(".slider-products-2 > .owl-carousel");

	$(sliderProducts2).each(function () {
		sliderProducts2.owlCarousel({
			// items: 3,
			rtl: owlRtl,
			autoplay: false,
			autoplaySpeed: 500, // Sliding autoplay speed
			autoplayTimeout: 5000, // Autoplay interval timeout.
			dragEndSpeed: 350, // Sliding speed by mouse drag
			autoplayHoverPause: true, // Stop autoplay on mouse hover
			loop: false,
			slideBy: 1, // Number of item are slided for each one transition
			margin: 30, // space between each item. Very useful!
			stagePadding: 0, // This used to see part of left an right items as preview style
			nav: true, // show prev and next buttons
			dots: false, // Show dots navigation
			navText: ["<i class=\"fa fa-chevron-up\"></i>", "<i class=\"fa fa-chevron-down\"></i>"], // prev and next buttons content
			responsive: {
				0: {
					items: 1
				},
				480: {
					items: 1
				},
				// 600 : {
				//     items : 2
				// },
				768: {
					items: 1
				},
				1024: {
					items: 1
				}
			},
			autoHeight: true,
			autoWidth: false,
			// animateOut: 'goDownOut',
			// animateIn: 'goDownIn',
			navRewind: true,
			center: false, // It's used to make the carousel start from the centered item
			dotsEach: 1, // Number of items per dot
			dotData: false,
			lazyLoad: false, // img tag needs class="owl-lazy" and data-src="url-to-img" or/and data-src-retina="url-to-highres-img"
			smartSpeed: 600, // Sliding speed when hover next or prev nav
			fluidSpeed: 5000,
			navSpeed: 400,
			// fallbackEasing: "ease-in-out",
			dotsSpeed: 400 // Sliding speed by using dots
		});
	});
}


// *** Item Click Increase & Decrease Counter *** //
function itemClickCounter() {
	jQuery.fn.allowDigitsOnly = function () {
		return this.each(function () {
			$(this).keydown(function (e) {
				var key = e.charCode || e.keyCode || 0;
				return (
					key == 8 ||
					key == 9 ||
					key == 46 ||
					key == 110 ||
					key == 190 ||
					(key >= 35 && key <= 40) ||
					(key >= 48 && key <= 57) ||
					(key >= 96 && key <= 105));
			});
		});
	};

	var inputField = $(".counter-add-item input");
	inputField.allowDigitsOnly();

	$(".increase-btn").on("click", function (e) {
		e.preventDefault();
		var inputField = $(this).prev("input");
		var currentInputValue = parseInt(inputField.val());
		inputField.val(currentInputValue + 1);
	});

	$(".decrease-btn").on("click", function (e) {
		e.preventDefault();
		var inputField = $(this).next("input");
		var currentInputValue = parseInt(inputField.val());
		inputField.val(currentInputValue - 1);

		if (currentInputValue < 1) { inputField.val("0"); }
	});
}


// *** Slider Product Single *** //
var sync1 = $(".slider-product-single > .owl-carousel"),
	sync2 = $(".slider-product-single-thumbs > .owl-carousel");

// Initialize Plugin
var owlCarousel1 = sync1.owlCarousel({
	items: 1,
	rtl: owlRtl,
	autoplay: false,
	autoplaySpeed: 500, // Sliding autoplay speed
	autoplayTimeout: 3000, // Autoplay interval timeout.
	dragEndSpeed: 400, // Sliding speed by mouse drag
	autoplayHoverPause: true, // Stop autoplay on mouse hover
	loop: false,
	slideBy: 1, // Number of item are slided for each one transition
	margin: 30, // space between each item. Very useful!
	stagePadding: 0, // This used to see part of left an right items as preview style
	nav: true, // show prev and next buttons
	dots: true, // Show dots navigation
	navText: ["<i class=\"fa fa-angle-up\"></i>", "<i class=\"fa fa-angle-down\"></i>"], // prev and next buttons content
	responsive: {
		0: {
			items: 1
		},
		480: {
			items: 1
		},
		768: {
			items: 1
		},
		1200: {
			items: 1
		}
	},
	autoHeight: false,
	autoWidth: false,
	// animateOut: 'goDownOut',
	// animateIn: 'goDownIn',
	navRewind: true,
	center: false, // It's used to make the carousel start from the centered item
	dotsEach: 1, // Number of items per dot
	dotData: false,
	lazyLoad: false, // img tag needs class="owl-lazy" and data-src="url-to-img" or/and data-src-retina="url-to-highres-img"
	smartSpeed: 600, // Sliding speed when hover next or prev nav
	fluidSpeed: 5000,
	navSpeed: 400,
	// fallbackEasing: "ease-in-out",
	dotsSpeed: 400 // Sliding speed by using dots
});

var owlCarousel2 = sync2.owlCarousel({
	// items: 1,
	rtl: owlRtl,
	autoplay: false,
	autoplaySpeed: 500, // Sliding autoplay speed
	autoplayTimeout: 3000, // Autoplay interval timeout.
	dragEndSpeed: 400, // Sliding speed by mouse drag
	autoplayHoverPause: true, // Stop autoplay on mouse hover
	loop: false,
	slideBy: 1, // Number of item are slided for each one transition
	margin: 15, // space between each item. Very useful!
	stagePadding: 0, // This used to see part of left an right items as preview style
	nav: false, // show prev and next buttons
	dots: false, // Show dots navigation
	navText: ["<i class=\"fa fa-angle-left\"></i>", "<i class=\"fa fa-angle-right\"></i>"], // prev and next buttons content
	responsive: {
		0: {
			items: 1
		},
		480: {
			items: 2
		},
		768: {
			items: 3
		},
		1200: {
			items: 4
		}
	},
	autoHeight: false,
	autoWidth: false,
	// animateOut: 'goDownOut',
	// animateIn: 'goDownIn',
	navRewind: true,
	center: false, // It's used to make the carousel start from the centered item
	dotsEach: 1, // Number of items per dot
	dotData: false,
	lazyLoad: false, // img tag needs class="owl-lazy" and data-src="url-to-img" or/and data-src-retina="url-to-highres-img"
	smartSpeed: 600, // Sliding speed when hover next or prev nav
	fluidSpeed: 5000,
	navSpeed: 400,
	// fallbackEasing: "ease-in-out",
	dotsSpeed: 400 // Sliding speed by using dots
});

// Sync Carousel & add current class
carouselSyncCurrentClass();

// In carousel change: Sync carousel & add current class
owlCarousel1.on("changed.owl.carousel", function () {
	carouselSyncCurrentClass();
});
owlCarousel2.on("changed.owl.carousel", function (event) {
	carouselSyncCurrentClass();
});

// Thumbs switch click event
owlCarousel2.find(".slide").on("click", function () {
	var itemIndex = $(this).closest(".owl-item").index();
	owlCarousel1.trigger("to.owl.carousel", itemIndex);
	carouselSyncCurrentClass();
});

function carouselSyncCurrentClass() {
	setTimeout(function () {
		var carousel1ActiveIndex = sync1.find(".owl-item.active").index();
		sync2.find(".owl-item").removeClass("current");
		var currentItem = sync2.find(".owl-item:nth-child(" + (carousel1ActiveIndex + 1) + ")");
		currentItem.addClass("current");
		if (!currentItem.hasClass("active")) {
			if (currentItem.prevAll(".active").length > 0) {
				owlCarousel2.trigger("next.owl.carousel");
			}
			if (currentItem.nextAll(".active").length) {
				owlCarousel2.trigger("prev.owl.carousel");
			}
		}
	}, 0);
}


// *** Scroll To *** //
$(".scroll-to").on("click", function (e) {
	e.preventDefault();
	var $anchor = $(this);

	// scroll to specific anchor
	$("html, body").stop().animate({
		scrollTop: $($anchor.attr("href")).offset().top
	}, 1200);
});


// *** Countdown Timer *** //
(function ($) {
	$.fn.countdown = function (options, callback) {

		//custom 'this' selector
		var thisEl = $(this);

		//array of custom settings
		var settings = {
			'date': null,
			'format': null
		};

		//append the settings array to options
		if (options) {
			$.extend(settings, options);
		}

		//main countdown function
		function countdown_proc() {

			var eventDate = Date.parse(settings['date']) / 1000;
			var currentDate = Math.floor($.now() / 1000);

			if (eventDate <= currentDate) {
				callback.call(this);
				clearInterval(interval);
			}

			var seconds = eventDate - currentDate;

			var days = Math.floor(seconds / (60 * 60 * 24)); //calculate the number of days
			seconds -= days * 60 * 60 * 24; //update the seconds variable with no. of days removed

			var hours = Math.floor(seconds / (60 * 60));
			seconds -= hours * 60 * 60; //update the seconds variable with no. of hours removed

			var minutes = Math.floor(seconds / 60);
			seconds -= minutes * 60; //update the seconds variable with no. of minutes removed

			// If page is RTL
			if (HTMLDir == "rtl") {
				//conditional Ss
				if (days == 1) {
					thisEl.find(".timeRefDays").text("يوم");
				} else {
					thisEl.find(".timeRefDays").text("أيام");
				}
				if (hours == 1) {
					thisEl.find(".timeRefHours").text("ساعة");
				} else {
					thisEl.find(".timeRefHours").text("ساعات");
				}
				if (minutes == 1) {
					thisEl.find(".timeRefMinutes").text("دقيقة");
				} else {
					thisEl.find(".timeRefMinutes").text("دقائق");
				}
				if (seconds == 1) {
					thisEl.find(".timeRefSeconds").text("ثانية");
				} else {
					thisEl.find(".timeRefSeconds").text("ثواني");
				}
			} else {
				//conditional Ss
				if (days == 1) {
					thisEl.find(".timeRefDays").text("day");
				} else {
					thisEl.find(".timeRefDays").text("days");
				}
				if (hours == 1) {
					thisEl.find(".timeRefHours").text("hour");
				} else {
					thisEl.find(".timeRefHours").text("hours");
				}
				if (minutes == 1) {
					thisEl.find(".timeRefMinutes").text("minute");
				} else {
					thisEl.find(".timeRefMinutes").text("minutes");
				}
				if (seconds == 1) {
					thisEl.find(".timeRefSeconds").text("second");
				} else {
					thisEl.find(".timeRefSeconds").text("seconds");
				}
			}

			//logic for the two_digits ON setting
			if (settings['format'] == "on") {
				days = (String(days).length >= 2) ? days : "0" + days;
				hours = (String(hours).length >= 2) ? hours : "0" + hours;
				minutes = (String(minutes).length >= 2) ? minutes : "0" + minutes;
				seconds = (String(seconds).length >= 2) ? seconds : "0" + seconds;
			}

			//update the countdown's html values.
			if (!isNaN(eventDate)) {
				thisEl.find(".days").text(days);
				thisEl.find(".hours").text(hours);
				thisEl.find(".minutes").text(minutes);
				thisEl.find(".seconds").text(seconds);
			} else {
				alert("Invalid date. Here's an example: 12 Tuesday 2016 17:30:00");
				clearInterval(interval);
			}
		}

		//run the function
		countdown_proc();

		//loop the function
		var interval = setInterval(countdown_proc, 1000);

	}
})(jQuery);

var get_date = $('.countdown').data('event-date');

if (get_date) {
	$(".countdown").each( function() {

		$(this).countdown({
			date: get_date,
			/*Change date and time in HTML data-event-date attribute */
			format: "on"
		}, function () {
			console.log('event ended');
		});
	});
}


// *** Product Single Tabs *** //
function productSingleTabs() {
	$(".ps-tabs > li").addClass("ps-tabs-item");

	// Variables
	var clickedTab = $(".ps-tabs > .active");
	var tabWrapper = $(".ps-tabs-content");
	var activeTab = tabWrapper.find(".active");
	var activeTabHeight = activeTab.outerHeight();

	// Show tab on page load
	activeTab.show();

	// Set height of wrapper on page load
	tabWrapper.height(activeTabHeight);

	$(".ps-tabs .ps-tabs-item").on("click", function () {

		if (!$(this).hasClass("active")) {
			// Remove class from active tab
			$(".ps-tabs .ps-tabs-item").removeClass("active");

			// Add class active to clicked tab
			$(this).addClass("active");

			// Update clickedTab variable
			clickedTab = $(".ps-tabs .active");

			// fade out active tab
			activeTab.animate({ top: 15 }, { duration: 200, queue: false }).fadeOut(200, function () {

				// Remove active class all tabs
				$(".ps-tabs-content > li").removeClass("active");

				// Get index of clicked tab
				var clickedTabIndex = clickedTab.index();

				// Add class active to corresponding tab
				$(".ps-tabs-content > li").eq(clickedTabIndex).addClass("active");

				// update new active tab
				activeTab = $(".ps-tabs-content > .active");

				// Update variable
				activeTabHeight = activeTab.outerHeight();

				// Animate height of wrapper to new tab height
				tabWrapper.stop().delay(0).animate({
					height: activeTabHeight
				}, 200, function () {

					// Fade in active tab
					activeTab.delay(0).css("top", 15)
						.animate({ top: 0 }, { duration: 150, queue: false }).fadeIn(100);

				});
			});
		}
	});
}


// *** List Products Layout Tabs *** //
function listProductsLayoutTabs() {
	$(".lp-layout-tabs > li").addClass("tabs-item");

	// Variables
	var clickedTab = $(".lp-layout-tabs > .active");
	var tabWrapper = $(".lp-layout-tabs-content");
	var activeTab = tabWrapper.find(".active");
	var activeTabHeight = activeTab.outerHeight();

	// Show tab on page load
	activeTab.show();

	// Set height of wrapper on page load
	tabWrapper.height(activeTabHeight);

	$(".lp-layout-tabs .tabs-item").on("click", function () {

		if (!$(this).hasClass("active")) {
			// Remove class from active tab
			$(".lp-layout-tabs .tabs-item").removeClass("active");

			// Add class active to clicked tab
			$(this).addClass("active");

			// Update clickedTab variable
			clickedTab = $(".lp-layout-tabs .active");

			// fade out active tab
			activeTab.animate({ top: 15 }, { duration: 200, queue: false }).fadeOut(200, function () {

				// Remove active class all tabs
				$(".lp-layout-tabs-content > li").removeClass("active");

				// Get index of clicked tab
				var clickedTabIndex = clickedTab.index();

				// Add class active to corresponding tab
				$(".lp-layout-tabs-content > li").eq(clickedTabIndex).addClass("active");

				// update new active tab
				activeTab = $(".lp-layout-tabs-content > .active");

				// Update variable
				activeTabHeight = activeTab.outerHeight();

				// Animate height of wrapper to new tab height
				tabWrapper.stop().delay(0).animate({
					height: activeTabHeight
				}, 200, function () {

					// Fade in active tab
					activeTab.delay(0).css("top", 15)
						.animate({ top: 0 }, { duration: 150, queue: false }).fadeIn(100);

				});
			});
		}
	});
}


// *** Popup Preview Newsletter *** //
function popupNewsletter() {
	if ($(".popup-preview-newsletter").length) {
		$("body").append("<div class='newsletter-popup-preview-overlay'>");
		var popupNewsletterDelay = 2000;
		setTimeout(function() {
			$(".popup-preview").toggleClass("viewed");
			$(".newsletter-popup-preview-overlay").toggleClass("viewed");
			$("html").toggleClass("scroll-lock");
		}, popupNewsletterDelay );
	}
	
	$(".popup-btn, .popup-bg, .popup-close").on("click", function (e) {
		e.preventDefault();
		$(".popup-preview").toggleClass("viewed");
		$(".newsletter-popup-preview-overlay").toggleClass("viewed");
		$("html").toggleClass("scroll-lock");
	});
}



// *** Checkout Progress Bar Width *** //
$( ".checkout-progress-bar" ).find( ".progress-bar" ).each( function () {
	var $this = $( this ),
		customWidth = $this.data( "pb-width" );

	$this.width( customWidth );
} );


// *** Options Select2 *** //
function optionsSelect2() {
	$(".options-select2").select2({
		dir: selectRtl
	});
}

// *** List Brands *** //
function listBrands() {
	$(".list-brands a").each(function (e) {
		if ($(this).next(".sub-menu").length) {
			// $( this ).addClass( "ddddddd" );
			$(this).closest("li").addClass("has-ul");
		}
	});

	$(".list-brands a").on("click", function (e) {
		var $this = $(this);
		if ($this.next(".sub-menu").length) {
			e.preventDefault();
			if ($this.next().hasClass("viewed")) {
				$this.next().removeClass("viewed");
				$this.parent().find(".active").removeClass("active");
				$this.next().slideUp(200);
			} else {
				$this.parent().parent().find(".active").removeClass("active");
				$this.parent("ul").find(".active").removeClass("active");
				$this.parent().parent().find("li .sub-menu").removeClass("viewed");
				$this.parent().parent().find("li .sub-menu").slideUp(200);
				$this.toggleClass("active");
				$this.next().toggleClass("viewed");
				$this.next().slideToggle(200);
			}
		}
	});
}

listBrands();


// *** Dashboard User Upload Image *** //
function duUPloadImg() {
	var duUPloadImg = $(".du-upload-img");
	$( "#du-btn-upload" ).on( "click" , function () {
		duUPloadImg.find( "input[type='file']" ).click();
	} );
	duUPloadImg.find('input[type="file"]').change(function (e) {
		var fileName = e.target.files[0].name;
		duUPloadImg.find( ".file-name" ).text( fileName );
		// alert('The file "' + fileName + '" has been selected.');
	});

}
duUPloadImg();


// *** Dashboard User Upload Image *** //
function formLabelStyle1() {
	$( "form.with-label-style-1" ).each( function() {
		var $this = $( this ),
			onClass = "on",
			showClass = "show";
	
		$this.find("input, textarea").on("checkval", function () {
			var label = $(this).prev("label");
			if (this.value !== "") {
				label.addClass(showClass);
			} else {
				label.removeClass(showClass);
			}
		}).on("keyup", function () {
			$(this).trigger("checkval");
		}).on("focus", function () {
			$(this).prev("label").addClass(onClass);
		}).on("blur", function () {
			$(this).prev("label").removeClass(onClass);
		}).trigger("checkval");
	} );
}
formLabelStyle1();


function listChooseColor() {
	$( ".list-choose-color li" ).each( function () {
		var $this = $( this ),
			colorValue = $this.find( "input" ).attr( "value" );

		$this.find( ".checkmark" ).css( "background-color", colorValue );

	} );
}

listChooseColor();