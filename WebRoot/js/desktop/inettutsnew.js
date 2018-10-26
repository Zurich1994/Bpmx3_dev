var iNettuts = {
    jQuery : $,
    settings : {
        columns : '.column',
        widgetSelector: '.widget',
        handleSelector: '.widget-head',
        contentSelector: '.widget-content',
        widgetDefault : {
            movable: true,
            removable: true,
            collapsible: true,
            colorClasses : ['color-yellow', 'color-red', 'color-blue', 'color-white', 'color-orange', 'color-green']
        },
        widgetIndividual : {
            intro : {
                movable: false,
                removable: false,
                collapsible: false
            }
        }
    },

    initnew : function () {
        this.addWidgetControlsnew();
        this.makeSortable();
    },
    
    getWidgetSettings : function (id) {
        var $ = this.jQuery,
            settings = this.settings;
        return (id&&settings.widgetIndividual[id]) ? $.extend({},settings.widgetDefault,settings.widgetIndividual[id]) : settings.widgetDefault;
    },
    
    addWidgetControlsnew : function () {
        var iNettuts = this,
            $ = this.jQuery,
            settings = this.settings;
            
        $(settings.widgetSelector, $(settings.columns)).each(function (index,value) {
        	$("#columnColse"+index).remove();
        	$("#collapse"+index).remove();
            var thisWidgetSettings = iNettuts.getWidgetSettings(this.id);
            if (thisWidgetSettings.removable) {
                $('<a href="javascript:;" id="columnColse'+index+'" class="remove">CLOSE</a>').mousedown(function (e) {
                    e.stopPropagation();    
                }).click(function () {
                        $(this).parents(settings.widgetSelector).animate({
                            opacity: 0    
                        },function () {
                            $(this).wrap('<div/>').parent().slideUp(function () {
                                $(this).remove();
                            });
                        });
                    return false;
                }).appendTo($(settings.handleSelector, this));
            }
            
            if (thisWidgetSettings.editable) {
                $('<a href="javascript:;" class="edit">更多</a>').mousedown(function (e) {
                    e.stopPropagation();    
                }).click(function () {
                   window.location.href=columnUrl;
                    return false;
                }).appendTo($(settings.handleSelector, this));
            }
            
            
            if (thisWidgetSettings.collapsible) {
                $('<a href="javascript:;" id="collapse'+index+'"  class="collapse">COLLAPSE</a>').mousedown(function (e) {
                    e.stopPropagation();    
                }).toggle(function () {
                    $(this).css({backgroundPosition: '-38px 0'})
                        .parents(settings.widgetSelector)
                            .find(settings.contentSelector).hide();
                    return false;
                },function () {
                    $(this).css({backgroundPosition: ''})
                        .parents(settings.widgetSelector)
                            .find(settings.contentSelector).show();
                    return false;
                }).prependTo($(settings.handleSelector,this));
            }
        });
    },
    
    attachStylesheet : function (href) {
        var $ = this.jQuery;
        return $('<link href="' + href + '" rel="stylesheet" type="text/css" />').appendTo('head');
    },
    
    makeSortable : function () {
    	
        var iNettuts = this,
            $ = this.jQuery,
            settings = this.settings,
            $sortableItems = (function () {
                var notSortable = '';
                $(settings.widgetSelector,$(settings.columns)).each(function (i) {
                    if (!iNettuts.getWidgetSettings(this.id).movable) {
                        if(!this.id) {
                            this.id = 'widget-no-id-' + i;
                        }
                        notSortable += '#' + this.id + ',';
                    }
                });
                return $('> li:not(' + notSortable + ')', settings.columns);
            })();
        
        $sortableItems.find(settings.handleSelector).css({
            cursor: 'move'
        }).mousedown(function (e) {
            $sortableItems.css({width:''});
            $(this).parent().css({
                width: $(this).parent().width() + 'px'
            });
        }).mouseup(function () {
            if(!$(this).parent().hasClass('dragging')) {
                $(this).parent().css({width:''});
            } else {
                $(settings.columns).sortable('disable');
            }
        });
    }
  
};
iNettuts.initnew();
