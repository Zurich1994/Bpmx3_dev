(function ($)
{
    $.fn.htButtons = function ()
    {
        return this.each(function ()
        {
        	
            if (this.manager) return;
          
            var g = {};
            this.manager = true;
            g.button = $(this);
            
            if (!g.button.hasClass("ht-btn")) g.button.addClass("ht-btn");
            
            g.button.append('<div class="ht-btn-l"></div><div class="ht-btn-r"></div>');  
            
            g.button.hover(function ()
            {
                $(this).addClass("ht-btn-over");
            }, function ()
            {
                $(this).removeClass("ht-btn-over");
            });
        });
    };

})(jQuery);