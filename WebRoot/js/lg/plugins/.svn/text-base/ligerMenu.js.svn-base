/**
* jQuery ligerUI 1.1.9
* 
* http://ligerui.com
*  
* Author daomi 2012 [ gd_star@163.com ] 
* 
*/
(function ($)
{
    $.ligerMenu = function (options)
    {
        return $.ligerui.run.call(null, "ligerMenu", arguments);
    };

    $.ligerDefaults.Menu = {
        width: 120,
        top: 0,
        left: 0,
        items: null,
        shadow: true
    };

    $.ligerMethos.Menu = {};

    $.ligerui.controls.Menu = function (options)
    {
        $.ligerui.controls.Menu.base.constructor.call(this, null, options);
    };
    $.ligerui.controls.Menu.ligerExtend($.ligerui.core.UIComponent, {
        __getType: function ()
        {
            return 'Menu';
        },
        __idPrev: function ()
        {
            return 'Menu';
        },
        _extendMethods: function ()
        {
            return $.ligerMethos.Menu;
        },
        _render: function ()
        {
            var g = this, p = this.options;
            g.menuItemCount = 0;
            //全部菜单
            g.menus = {};
            //顶级菜单
            g.menu = g.createMenu();
            g.element = g.menu[0];
            g.menu.css({ top: p.top, left: p.left, width: p.width });

            p.items && $(p.items).each(function (i, item)
            {
                g.addItem(item);
            });

            g.createIframe(g.menu);
            $(document).bind('click.menu', function ()
            {
          
                for (var menuid in g.menus)
                {
                    var menu = g.menus[menuid];
                    if (!menu) return;
                    menu.hide();
                    if (menu.shadow) menu.shadow.hide();
                    g.hideIframe(menu);
                }
            });
            g.set(p);
        },
        show: function (options, menu)
        {
            var g = this, p = this.options;
            if (menu == undefined) menu = g.menu;
            if (options && options.left != undefined)
            {
                menu.css({ left: options.left });
            }
            if (options && options.top != undefined)
            {
                menu.css({ top: options.top });
            }
            menu.show();
            g.updateShadow(menu);
            g.showIframe(menu);
        },
        updateShadow: function (menu)
        {
            var g = this, p = this.options;
            if (!p.shadow) return;
            menu.shadow.css({
                left: menu.css('left'),
                top: menu.css('top'),
                width: menu.outerWidth(),
                height: menu.outerHeight()
            });
            if (menu.is(":visible"))
                menu.shadow.show();
            else
                menu.shadow.hide();
        },
        hide: function (menu)
        {
            var g = this, p = this.options;
            if (menu == undefined) menu = g.menu;
            g.hideAllSubMenu(menu);
            menu.hide();
            g.updateShadow(menu);
          
            g.hideIframe(menu);
        },
        hideIframe:function(menu){
        	var memuId=menu.attr("memuId");
        	$("iframe.l_menu_frame[iframeId='"+memuId+"']").hide();
        },
        showIframe:function(menu){
        	var memuId=menu.attr("memuId");
        	var obj=$("iframe.l_menu_frame[iframeId='"+memuId+"']");
       
        	obj.show();
        	obj.css({ top: menu.css("top"), left: menu.css("left") , width: menu.width()+2,height:menu.height()+5 });  
        	
        },
        toggle: function ()
        {
            var g = this, p = this.options;
            g.menu.toggle();
            g.updateShadow(g.menu);
        },
        removeItem: function (itemid)
        {
            var g = this, p = this.options;
            $("> .l-menu-item[menuitemid=" + itemid + "]", g.menu.items).remove();
        },
        setEnabled: function (itemid)
        {
            var g = this, p = this.options;
            $("> .l-menu-item[menuitemid=" + itemid + "]", g.menu.items).removeClass("l-menu-item-disable");
        },
        setDisabled: function (itemid)
        {
            var g = this, p = this.options;
            $("> .l-menu-item[menuitemid=" + itemid + "]", g.menu.items).addClass("l-menu-item-disable");
        },
        isEnable: function (itemid)
        {
            var g = this, p = this.options;
            return !$("> .l-menu-item[menuitemid=" + itemid + "]", g.menu.items).hasClass("l-menu-item-disable");
        },
        getItemCount: function ()
        {
            var g = this, p = this.options;
            return $("> .l-menu-item", g.menu.items).length;
        },
        addItem: function (item, menu)
        {
            var g = this, p = this.options;
            if (!item) return;
            if (menu == undefined) menu = g.menu;

            if (item.line)
            {
                menu.items.append('<div class="l-menu-item-line"></div>');
                return;
            }
            var ditem = $('<div class="l-menu-item"><div class="l-menu-item-text"></div> </div>');
            var itemcount = $("> .l-menu-item", menu.items).length;
            menu.items.append(ditem);
            ditem.attr("ligeruimenutemid", ++g.menuItemCount);
            item.id && ditem.attr("menuitemid", item.id);
            item.text && $(">.l-menu-item-text:first", ditem).html(item.text);
            item.icon && ditem.prepend('<div class="l-menu-item-icon "><img src="'+ item.icon+'" style="width:16px;height:16px;margin-top: 4px;margin-left: 4px;"/></div>');
            if (item.disable || item.disabled)
                ditem.addClass("l-menu-item-disable");
            if (item.children)
            {
                ditem.append('<div class="l-menu-item-arrow"></div>');
                var newmenu = g.createMenu(ditem.attr("ligeruimenutemid"));
                g.menus[ditem.attr("ligeruimenutemid")] = newmenu;
                newmenu.width(p.width);
                newmenu.hover(null, function ()
                {
                    if (!newmenu.showedSubMenu)
                        g.hide(newmenu);
                });
                $(item.children).each(function ()
                {
                    g.addItem(this, newmenu);
                });
                g.createIframe(newmenu);
            }
            item.click && ditem.click(function ()
            {
                if ($(this).hasClass("l-menu-item-disable")) return;
                item.click(item, itemcount);
            });
            item.dblclick && ditem.dblclick(function ()
            {
                if ($(this).hasClass("l-menu-item-disable")) return;
                item.dblclick(item, itemcount);
            });

            var menuover = $("> .l-menu-over:first", menu);
            ditem.hover(function (event)
            {
            	var x=event.clientX; 
    			var y=event.clientY;  
    			var documentHeight=document.body.clientHeight;
                if ($(this).hasClass("l-menu-item-disable")) return;
                var itemtop = $(this).offset().top;
                var top = itemtop - menu.offset().top;
                menuover.css({ top: top });
                if(y>documentHeight-40){
                	var allItems=$("> .l-menu-item", menu.items);
                	for(var i=0;i<itemcount;i++){
                		var nextItem=$(allItems[i]);
                		if(nextItem.is(":hidden")){
                			continue;
                		}else{
                			nextItem.hide();
                			break;
                		}
                	}
                	
                }else if(y<menu.offset().top+30){
                	var allItems=$("> .l-menu-item:hidden", menu.items);
                	for(var i=itemcount;i>=0;i--){
                		var prevItem=$(allItems[i]);
                		if(prevItem.is(":hidden")){               			
                			prevItem.show();
                			break;
                		}else{
                			continue;
                		}
                	}
                }
                g.hideAllSubMenu(menu);
                if (item.children)
                {
                    var ligeruimenutemid = $(this).attr("ligeruimenutemid");
                    if (!ligeruimenutemid) return;
                    if (g.menus[ligeruimenutemid])
                    {
                        g.show({ top: itemtop, left: $(this).offset().left + $(this).width() - 5 }, g.menus[ligeruimenutemid]);
                        menu.showedSubMenu = true;
                    }
                }
            }, function ()
            {
                if ($(this).hasClass("l-menu-item-disable")) return;
                var ligeruimenutemid = $(this).attr("ligeruimenutemid");
                if (item.children)
                {
                    var ligeruimenutemid = $(this).attr("ligeruimenutemid");
                    if (!ligeruimenutemid) return;
                };
            });
        },
        hideAllSubMenu: function (menu)
        {
            var g = this, p = this.options;
            if (menu == undefined) menu = g.menu;
            $("> .l-menu-item", menu.items).each(function ()
            {
                if ($("> .l-menu-item-arrow", this).length > 0)
                {
                    var ligeruimenutemid = $(this).attr("ligeruimenutemid");
                    if (!ligeruimenutemid) return;
                    g.menus[ligeruimenutemid] && g.hide(g.menus[ligeruimenutemid]);
                    //隐藏iframe
                    g.hideIframe(g.menus[ligeruimenutemid]);
                }
            });
            menu.showedSubMenu = false;
        },
        createIframe:function(menu){
        	var iframeObj=$('<iframe  frameborder="0" class="l_menu_frame" style="position:absolute;z-index:1;display:none;"></iframe>');
        	var memuId=menu.attr("memuId");
        	
        	iframeObj.css({left:menu.left,top:menu.top,width:menu.width()+2,height:menu.height()+5})
        	iframeObj.attr("iframeId",memuId);
        	iframeObj.appendTo('body');
        },
        createMenu: function (parentMenuItemID)
        {
            var g = this, p = this.options;
            var menu = $('<div class="l-menu" style="display:none"><div class="l-menu-yline"></div><div class="l-menu-over"><div class="l-menu-over-l"></div> <div class="l-menu-over-r"></div></div><div class="l-menu-inner"></div></div>');
            parentMenuItemID && menu.attr("ligeruiparentmenuitemid", parentMenuItemID);
            menu.items = $("> .l-menu-inner:first", menu);
            menu.appendTo('body');
            if (p.shadow)
            {
                menu.shadow = $('<div class="l-menu-shadow"></div>').insertAfter(menu);
                g.updateShadow(menu);
            }
            menu.hover(null, function ()
            {
                if (!menu.showedSubMenu)
                    $("> .l-menu-over:first", menu).css({ top: -24 });
            });
            if (parentMenuItemID)
                g.menus[parentMenuItemID] = menu;
            else
                g.menus[0] = menu;
            
            if (menu.attr("memuId") == undefined) menu.attr("memuId", new Date().getTime());
            return menu;
        }
    });
    //旧写法保留
    $.ligerui.controls.Menu.prototype.setEnable = $.ligerui.controls.Menu.prototype.setEnabled;
    $.ligerui.controls.Menu.prototype.setDisable = $.ligerui.controls.Menu.prototype.setDisabled;



})(jQuery);