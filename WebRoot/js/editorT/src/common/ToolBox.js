!function (Q, $) {
    var createElement = function (className, parent, tag, html) {
        var element = document.createElement(tag || 'div');
        element.className = className;
        $(element).html(html);
        if (parent) {
            parent.appendChild(element);
        }
        return element;
    }

    var forEach = function (object, call, scope) {
        if (Array.isArray(object)) {
            return object.forEach(function (v) {
                call.call(this, v);
            }, scope);
        }
        for (var name in object) {
            call.call(scope, object[name], name);
        }
    }
    var defaultImageStyles = {
        fillColor: '#EEE',
        lineWidth: 1,
        strokeStyle: '#2898E0',
        padding: {left: 1, top: 1, right: 5, bottom: 5},
        shadowColor: '#888',
        shadowOffsetX: 2,
        shadowOffsetY: 2,
        shadowBlur: 3
    }
    var nodeImageStyles = {};
    nodeImageStyles[Q.Styles.RENDER_COLOR] = 'renderColor';
    nodeImageStyles[Q.Styles.RENDER_COLOR_BLEND_MODE] = 'renderColorBlendMode';
    nodeImageStyles[Q.Styles.SHAPE_FILL_COLOR] = 'fillColor';
    nodeImageStyles[Q.Styles.SHAPE_STROKE_STYLE] = 'strokeStyle';
    nodeImageStyles[Q.Styles.SHAPE_LINE_DASH] = 'borderLineDash';
    nodeImageStyles[Q.Styles.SHAPE_LINE_DASH_OFFSET] = 'borderLineDashOffset';
    //nodeImageStyles[Q.Styles.SHAPE_FILL_GRADIENT] = 'fillGradient';
    nodeImageStyles[Q.Styles.SHAPE_OUTLINE] = 'outline';
    nodeImageStyles[Q.Styles.SHAPE_OUTLINE_STYLE] = 'outlineStyle';
    nodeImageStyles[Q.Styles.LINE_CAP] = 'lineGap';
    nodeImageStyles[Q.Styles.LINE_JOIN] = 'lineJoin';
    nodeImageStyles[Q.Styles.BACKGROUND_COLOR] = 'backgroundColor';
    nodeImageStyles[Q.Styles.BACKGROUND_GRADIENT] = 'backgroundGradient';
    nodeImageStyles[Q.Styles.BORDER] = 'border';
    nodeImageStyles[Q.Styles.BORDER_COLOR] = 'borderColor';
    nodeImageStyles[Q.Styles.BORDER_LINE_DASH] = 'borderLineDash';
    nodeImageStyles[Q.Styles.BORDER_LINE_DASH_OFFSET] = 'borderLineDashOffset';
    //Styles.IMAGE_BACKGROUND_COLOR = "image.background.color";
    //Styles.IMAGE_BACKGROUND_GRADIENT = "image.background.gradient";
    //Styles.IMAGE_BORDER = "image.border.width";
    //Styles.IMAGE_BORDER_STYLE = Styles.IMAGE_BORDER_COLOR = "image.border.style";
    //Styles.IMAGE_BORDER_LINE_DASH = "image.border.line.dash";
    //Styles.IMAGE_BORDER_LINE_DASH_OFFSET = "image.border.line.dash.offset";
    //Styles.IMAGE_RADIUS = Styles.IMAGE_BORDER_RADIUS = "image.radius";
    //Styles.IMAGE_PADDING = "image.padding";

    //var imageUI = new Q.ImageUI();
    //var imageProperties = {};
    //for(var name in imageUI){
    //    if(name[0] == '_' || name.indexOf('$invalidate') == 0 || imageUI[name] instanceof Function){
    //        continue;
    //    }
    //    if(name[0] == '$'){
    //        name = name.substring(1);
    //    }
    //    imageProperties[name] = imageUI[name];
    //}
    //Q.log(JSON.stringify(imageProperties, null, '\t'));

    function mixStyles(styles) {
        if (!styles) {
            return defaultImageStyles;
        }
        var result = {};
        for (var name in defaultImageStyles) {
            result[name] = defaultImageStyles[name];
        }
        for (var name in styles) {
            var propertyName = nodeImageStyles[name];
            if (propertyName) {
                result[propertyName] = styles[name];
            }
        }
        return result;
    }

    var onGroupTitleClick = function (evt) {
        var parent = evt.target.parentNode;
        while (parent && !$(parent).hasClass('group')) {
            parent = parent.parentNode;
        }
        if (!parent) {
            return;
        }
        if ($(parent).hasClass('group--closed')) {
            $(parent).removeClass('group--closed');
        } else {
            $(parent).addClass('group--closed');
        }
    }

    function isImage(image) {
        return Q.isString(image) || image.draw instanceof Function;
    }

    var ToolBox = function (graph, html, groups) {
        this.graph = graph;
        this.html = html;
        this.init(groups);
    }
    ToolBox.prototype = {
        loadButton: null,
        imageWidth: 40,
        imageHeight: 40,
        loadImageBoxes: function (groups) {
            if (Q.isArray(groups)) {
                forEach(groups, function (group) {
                    this.loadImageBox(group);
                }, this);
                return;
            }
            this.loadImageBox(groups);
        },
        loadImageBox: function (json, insert) {
            if (Q.isString(json)) {
                json = JSON.parse(json);
            }
            if (insert) {
                var firstGroup = this.html.getElementsByClassName('group').item(0);
                if (firstGroup) {
                    this.html.insertBefore(this._createGroup(json, json.prefix), firstGroup);
                    return;
                }
            }
            this.html.appendChild(this._createGroup(json, json.prefix));
        },
        //加载图元库文件
        loadImageBoxFile: function (files) {
            if (!files[0]) {
                return;
            }
            Q.readerSingleFile(files[0], 'json', function (json) {
                if (json) {
                    this.loadImageBox(json, true);
                }
            }.bind(this));
        },
//初始化拖拽节点列表
        init: function (groups) {
            var toolbox = this.html, graph = this.graph;

            Q.appendClass(toolbox, 'graph-editor__toolbox');
            var buttonBar = createElement('graph-editor__toolbox-buttonBar', toolbox);
            var button = this.loadButton = Q.createButton({
                type: 'file',
                name: getI18NString('Load Images...'),
                iconClass: 'q-icon toolbar-add',
                action: this.loadImageBoxFile.bind(this)
            }, this);
            buttonBar.appendChild(button);

            var basicNodes = [{
                label: 'Node',
                image: 'Q-node'
            }, {
                type: 'Text',
                label: 'Text',
                html: '<span style="background-color: #2898E0; color:#FFF; padding: 3px 5px;">' + getI18NString('Text') + '</span>',
                styles: {
                    'label.background.color': '#2898E0',
                    'label.color': '#FFF',
                    'label.padding': new Q.Insets(3, 5)
                }
            }, {
                type: 'Group',
                label: 'Group',
                image: 'Q-group'
            }, {
                label: 'SubNetwork',
                image: 'Q-subnetwork',
                properties: {enableSubNetwork: true}
            }];

            var innerGroups = [{prefix: 'Q-', name: 'basic.nodes', displayName: getI18NString('Basic Nodes'), images: basicNodes},
                 /*
                  * 
                  *   {
                prefix: 'Q-',
                name: 'register.images',
                displayName: getI18NString('Register Images'),
                images: Q.getAllImages()
                     },
                  */            
            /*{
                name: 'default.shapes',
                displayName: getI18NString('Default Shapes'),
                prefix: 'Q-',
                images: Q.Shapes.getAllShapes(this.imageWidth, this.imageHeight)
            }*/];
            this.loadImageBoxes(innerGroups);
            if (groups) {
                this.loadImageBoxes(groups);
            }
            Q.Shapes.getShape(Q.Consts.SHAPE_CIRCLE, 100, 100)
        },
        _index: 0,
        _getGroup: function(name){
            return this._groups[name];
            //return $(this.html).find('#' + name);
        },
        hideDefaultGroups: function(){
            this.hideGroup('basic.nodes');
          //  this.hideGroup('register.images');
            this.hideGroup('default.shapes');
           
        },
        hideGroup: function(name){
            var group = this._getGroup(name);
            if(group){
                group.style.display = 'none';
            }
        },
        showGroup: function(name){
            var group = this._getGroup(name);
            if(group){
                group.style.display = '';
            }
        },
        _groups: {},
        _createGroup: function (groupInfo) {
            var name = groupInfo.name;
            var root = groupInfo.root;
            var images = groupInfo.images;
            var displayName = groupInfo.displayName || name;

            var group = createElement('group');
            group.id = name;
            this._groups[name] = group;

            var title = createElement('group__title', group);
            title.onclick = onGroupTitleClick;
            createElement(null, title, 'span', displayName);
            createElement('q-icon group-expand toolbar-expand', title, 'span');
            var items = createElement('group__items', group);
            var clearDiv = document.createElement('div');
            clearDiv.style.clear = 'both';
            group.appendChild(clearDiv);

            if (!images) {
                return group;
            }

            //var images = [{
            //    type: '图元类型',
            //    label: '图元文本',
            //    image: '图元图片',
            //    imageName: '图片名称',
            //    styles: '图元样式',
            //    properties: '图元属性',
            //    clientProperties: '图元client属性',
            //    html: '拖拽html内容'
            //}, 'a.png', {draw: function(g){}}];
            //var group = {
            //    name: '分组名称',
            //    root: '根目录',
            //    imageWidth: '',
            //    imageHeight: '',
            //    size: 'q-icon size',
            //    images: images//'拖拽图片信息'
            //}

            var imageWidth = groupInfo.imageWidth || this.imageWidth;
            var imageHeight = groupInfo.imageHeight || this.imageHeight;

            forEach(images, function (imageInfo, name) {
                if (name == '_classPath' || name == '_className') {
                    return;
                }
                var image;
                if (isImage(imageInfo)) {
                    image = imageInfo;
                } else {
                    image = imageInfo.image;
                }
                var imageDiv, tooltip;
                if (image) {
                    var imageName;
                    if (Q.isString(image)) {
                        imageName = image;
                        if (!Q.hasImage(image) && root) {
                            imageName = image = root + image;
                        }
                    } else {
                        imageName = imageInfo.imageName || imageInfo.name || name || 'drawable-' + this._index++;
                    }
                    if (!Q.hasImage(imageName)) {
                        Q.registerImage(imageName, image);
                    }
                    imageDiv = Q.createCanvas(imageWidth, imageHeight, true);
                    Q.drawImage(imageName, imageDiv, mixStyles(imageInfo.styles));
                    if (isImage(imageInfo)) {
                        imageInfo = {image: imageName};
                    } else {
                        imageInfo.image = imageName;
                    }
                    if(groupInfo.size){
                        if(!imageInfo.properties){
                            imageInfo.properties = {}
                        }
                        if(!imageInfo.properties.size){
                            imageInfo.properties.size = groupInfo.size;
                        }
                    }

                    tooltip = imageName;
                } else if (imageInfo.html) {
                    var imageDiv = document.createElement('div');
                    imageDiv.style.width = imageWidth + 'px';
                    imageDiv.style.height = imageHeight + 'px';
                    imageDiv.style.lineHeight = imageHeight + 'px';
                    imageDiv.style.overflow = 'hidden';
                    imageDiv.innerHTML = imageInfo.html;
                } else {
                    return;
                }
                tooltip = imageInfo.tooltip || imageInfo.label || tooltip || name;
                imageDiv.setAttribute('title', tooltip);
                var item = createElement('group__item', items);
                Q.appendDNDInfo(imageDiv, imageInfo);
                item.appendChild(imageDiv);
            }, this)
            return group;
        }
    }

    Q.ToolBox = ToolBox;

}(Q, jQuery)