$(document).ready(function(){
	//图片放大效果
    function u(u) {
        function c() {
            function h(e) {
                e.show();
                n.removeClass("loading")
            }
            var t = $(this),
            r = parseInt(n.css("borderLeftWidth")),
            i = s - r * 2,
            u = o - r * 2,
            a = t.width(),
            f = t.height();
            if (a == n.width() && a <= i && f == n.height() && f <= u) {
                h(t);
                return
            }
            if (a > i || f > u) {
                var l = u < f ? u: f,
                c = i < a ? i: a;
                if (l / f <= c / a) {
                    t.width(a * l / f);
                    t.height(l)
                } else {
                    t.width(c);
                    t.height(f * c / a)
                }
            }
            n.animate({
                width: t.width(),
                height: t.height(),
                marginTop: -(t.height() / 2) - r,
                marginLeft: -(t.width() / 2) - r
            },
            200,
            function() {
                h(t)
            })
        }
        if (u) u.preventDefault();
        var a = $(this),
        f = a.attr("href");
        if (!f) return;
        var l = $(new Image).hide();
        $("#zoom .previous, #zoom .next").show();
        if (a.hasClass("zoom")) $("#zoom .previous, #zoom .next").hide();
        if (!r) {
            r = true;
            t.show();
            $("body").addClass("zoomed")
        }
        n.html(l).delay(500).addClass("loading");
        l.load(c).attr("src", f);
        i = a
    }
    function a() {
        var t = i.parent("tr").prev();
        if (t.length == 0) t = $(".gallery tr:first-child");
          t.find("td").children("a.zoomimg").trigger("click",u)
    }
    function f() {
        var t = i.parent("tr").next();
        if (t.length == 0) t = $(".gallery tr:last-child");
        t.find("td").children("a.zoomimg").trigger("click",u)
    }
    function l(s) {
        if (s) s.preventDefault();
        r = false;
        i = null;
        t.hide();
        $("body").removeClass("zoomed");
        n.empty()
    }
    function c() {
        s = $(window).width();
        o = $(window).height()
    }
    $("body").append('<div id="zoom"><a class="close"></a><a href="#previous" class="previous"></a><a href="#next" class="next"></a><div class="content loading"></div></div>');
    var t = $("#zoom").hide(),
    n = $("#zoom .content"),
    r = false,
    i = null,
    s = $(window).width(),
    o = $(window).height(); (function() {
        t.on("click",
        function(t) {
            t.preventDefault();
            if ($(t.target).attr("id") == "zoom") l()
        });
        $("#zoom .close").on("click", l);
        $("#zoom .previous").on("click", a);
        $("#zoom .next").on("click", f);
        $(document).keydown(function(e) {
            if (!i) return;
            if (e.which == 38 || e.which == 40) e.preventDefault();
            if (e.which == 27) l();
            if (e.which == 37 && !i.hasClass("zoom")) a();
            if (e.which == 39 && !i.hasClass("zoom")) f()
        });
        if ($(".gallery tr a.zoomimg").length == 1) $(".gallery tr a.zoomimg")[0].addClass("zoom");
        $(".zoom, .gallery tr a.zoomimg").on("click", u)
    })(); (function() {
        $(window).on("resize", c)
    })(); (function() {
        $(window).on("mousewheel DOMMouseScroll",
        function(e) {
            if (!i) return;
            e.stopPropagation();
            e.preventDefault();
            e.cancelBubble = false
        })
    })()
})