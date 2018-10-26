
/**
 * 扩展 container
 * by cjj
 */

Ext.override(Ext.Container, {  
    getCmpByName : function(name) {  
        var getByName = function(container, name) {  
            var items = container.items;  
            if (items != null) {  
                for (var i = 0; i < items.getCount(); i++) {  
                    var comp = items.get(i);  
                    var cp = getByName(comp, name);  
                    if (cp != null)  
                        return cp;  
                    if (comp.getName && name == comp.getName()) {  
                        return comp;  
                        break;  
                    }  
                }  
            }  
            return null;  
        };  
        return getByName(this, name);  
    }  
});  