(function($) {
"use strict";


// Avoid `console` errors in browsers that lack a console.
(function() {
    var method;
    var noop = function () {};
    var methods = [
        'assert', 'clear', 'count', 'debug', 'dir', 'dirxml', 'error',
        'exception', 'group', 'groupCollapsed', 'groupEnd', 'info', 'log',
        'markTimeline', 'profile', 'profileEnd', 'table', 'time', 'timeEnd',
        'timeStamp', 'trace', 'warn'
    ];
    var length = methods.length;
    var console = (window.console = window.console || {});

    while (length--) {
        method = methods[length];

        // Only stub undefined methods.
        if (!console[method]) {
            console[method] = noop;
        }
    }
}());






// ============================================================================
// mobile_menu plugin
// ============================================================================

(function($, window, document, undefined) {

    var pluginName = 'mobile_menu';
    var defaults = {
      easing: 'easeOutBack',
      speed: 'slow'
    };

    function Plugin(element, options) {
        this.element = element;
        this.$element = $(element);
        this.options = $.extend({}, defaults, options);
        this._name = pluginName;
        this.init();
    }

    Plugin.prototype = {
         init: function() {
            var that = this;

            // Bind toggler button
            this.$element.find('.submenu-toggler').click(function(e){
               e.preventDefault();
               that.toggle_sub($(this).siblings('ul'), $(this).closest('li'))
            });

            this.$element.find('.mobile-menu-toggler').click(function(e){
               e.preventDefault();
               that.$element.find('.mobile-menu-body').slideToggle();
            })

         },
         toggle_sub: function($sub_ul, $li){
            // if($icon.hasClass('fa-plus')) $icon.removeClass('fa-plus').addClass('fa-minus');
            // else $icon.removeClass('fa-minus').addClass('fa-plus');
            $li.toggleClass('open');
            
            $sub_ul.slideToggle(this.options.speed, this.options.easing);

           
         }
    } // Plugin.prototype

    $.fn[pluginName] = function(options) {
        var args = [].slice.call(arguments, 1);
        return this.each(function() {
            if (!$.data(this, 'plugin_' + pluginName))
                $.data(this, 'plugin_' + pluginName, new Plugin(this, options));
            else if ($.isFunction(Plugin.prototype[options]))
                $.data(this, 'plugin_' + pluginName)[options].apply($.data(this, 'plugin_' + pluginName), args);
        });
    }
})(jQuery, window, document);



// ============================================================================
// Custom Select plugin
// ============================================================================

(function(a) {
    a.fn.extend({
        customSelect: function(c) {
            if (typeof document.body.style.maxHeight === "undefined") {
                return this
            }
            var e = {
                    customClass: "customSelect",
                    mapClass: true,
                    mapStyle: true
                },
                c = a.extend(e, c),
                d = c.customClass,
                f = function(h, k) {
                    var g = h.find(":selected"),
                        j = k.children(":first"),
                        i = g.html() || "&nbsp;";
                    j.html(i);
                    if (g.attr("disabled")) {
                        k.addClass(b("DisabledOption"))
                    } else {
                        k.removeClass(b("DisabledOption"))
                    }
                    setTimeout(function() {
                        k.removeClass(b("Open"));
                        a(document).off("mouseup.customSelect")
                    }, 60)
                },
                b = function(g) {
                    return d + g
                };
            return this.each(function() {
                var g = a(this),
                    i = a("<span />").addClass(b("Inner")),
                    h = a("<span />");
                g.after(h.append(i));
                h.addClass(d);
                if (c.mapClass) {
                    h.addClass(g.attr("class"))
                }
                if (c.mapStyle) {
                    h.attr("style", g.attr("style"))
                }
                g.addClass("hasCustomSelect").on("render.customSelect", function() {
                    f(g, h);
                    g.css("width", "");
                    var k = parseInt(g.outerWidth(), 10) - (parseInt(h.outerWidth(), 10) - parseInt(h.width(), 10));
                    h.css({
                        display: "inline-block"
                    });
                    var j = h.outerHeight();
                    if (g.attr("disabled")) {
                        h.addClass(b("Disabled"))
                    } else {
                        h.removeClass(b("Disabled"))
                    }
                    i.css({
                        // width: k,
                        width: "100%",
                        display: "inline-block"
                    });
                    g.css({
                        "-webkit-appearance": "menulist-button",
                        //width: h.outerWidth(),
                        position: "absolute",
                        opacity: 0,
                        height: j,
                        fontSize: h.css("font-size")
                    })
                }).on("change.customSelect", function() {
                    h.addClass(b("Changed"));
                    f(g, h)
                }).on("keyup.customSelect", function(j) {
                    if (!h.hasClass(b("Open"))) {
                        g.trigger("blur.customSelect");
                        g.trigger("focus.customSelect")
                    } else {
                        if (j.which == 13 || j.which == 27) {
                            f(g, h)
                        }
                    }
                }).on("mousedown.customSelect", function() {
                    h.removeClass(b("Changed"))
                }).on("mouseup.customSelect", function(j) {
                    if (!h.hasClass(b("Open"))) {
                        if (a("." + b("Open")).not(h).length > 0 && typeof InstallTrigger !== "undefined") {
                            g.trigger("focus.customSelect")
                        } else {
                            h.addClass(b("Open"));
                            j.stopPropagation();
                            a(document).one("mouseup.customSelect", function(k) {
                                if (k.target != g.get(0) && a.inArray(k.target, g.find("*").get()) < 0) {
                                    g.trigger("blur.customSelect")
                                } else {
                                    f(g, h)
                                }
                            })
                        }
                    }
                }).on("focus.customSelect", function() {
                    h.removeClass(b("Changed")).addClass(b("Focus"))
                }).on("blur.customSelect", function() {
                    h.removeClass(b("Focus") + " " + b("Open"))
                }).on("mouseenter.customSelect", function() {
                    h.addClass(b("Hover"))
                }).on("mouseleave.customSelect", function() {
                    h.removeClass(b("Hover"))
                }).trigger("render.customSelect")
            })
        }
    })
})(jQuery);




})(jQuery);



