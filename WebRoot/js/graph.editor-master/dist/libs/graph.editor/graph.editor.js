! 
function(t, e) {
	function n(t) {
		this._refs = {}, t && (this.root = t.root)
	}

	function i(t) {
		if (!(t instanceof Object)) return !t;
		if (Array.isArray(t)) return 0 == t.length;
		for (var e in t) return !1;
		return !0
	}

	function o(t, n) {
		var i = t.split(".");
		n = n || e;
		for (var o = -1; n && ++o < i.length;) {
			var s = i[o];
			n = n[s]
		}
		return n
	}

	function s(t, e, n) {
		if (t._classPath = e, t instanceof Function && (t.prototype._className = t._classPath, t.prototype._class = t), n !== !1)
			for (var i in t)
				if (!("_" == i[0] || "$" == i[0] || "superclass" == i || "constructor" == i || "prototype" == i || i.indexOf(".") >= 0)) {
					var o = t[i];
					o && o instanceof Object && !o._classPath && s(o, e + "." + i)
				}
	}

	function a(t) {
		var e = t._className;
		if (!e) return null;
		var n = p[e];
		if (!n) {
			var i = t._class;
			n = p[e] = new i
		}
		return n
	}

	function r(t, e) {
		return t == e || t && e && t.equals && t.equals(e)
	}

	function l(t, e, n, i) {
		var o = a(i);
		e.forEach(function(e) {
			var s = i[e];
			if (!r(s, o[e])) {
				var a = t.toJSON(s);
				(a || !s) && (n[e] = a)
			}
		}, i)
	}

	function h(t, e) {
		var n;
		for (var i in e) n || (n = {}), n[i] = t.toJSON(e[i]);
		return n
	}

	function u(t) {
		t && (this.withGlobalRefs = t.withGlobalRefs !== !1), this.reset()
	}

	function d(t, e) {
		var n = new u,
			o = {
				version: "1.8 alpha",
				refs: {}
			},
			s = [],
			a = {};
		if (t.currentSubNetwork) {
			var r = n.elementToJSON(t.currentSubNetwork);
			r && (o.currentSubNetwork = {
				_ref: r._refId = t.currentSubNetwork.id
			})
		}
		if (t.forEach(function(t) {
			if (!e || e(t) !== !1) {
				var i = n.elementToJSON(t);
				i && (s.push(i), a[t.id] = i)
			}
		}), n._elementRefs)
			for (var l in n._elementRefs) a[l]._refId = l;
		n._globalRefs && (o.refs = n._globalRefs), n.clearRef(), o.datas = s;
		for (var h in o) i(o[h]) && delete o[h];
		return o
	}

	function c(t) {
		var e = t.indexOf(".");
		return 0 > e ? parseFloat(t) : (t = t.substring(0, e) + "." + t.substring(e).replace(/\./g, ""), parseFloat(t))
	}
	if (!t.Graph.prototype.parseJSON) {
		n.prototype = {
			_refs: null,
			_index: 1,
			root: null,
			reset: function() {
				this._refs = {}, this._index = 1
			},
			getREF: function(t) {
				return this._refs[t]
			},
			clearRef: function() {
				for (var t in this._refs) {
					var e = this._refs[t],
						n = e._value;
					n && (n._refed || delete e._refId, delete n._refed, delete n._refId, delete e._value)
				}
				this.reset()
			},
			toJSON: function(t) {
				if (!(t instanceof Object)) return t;
				if (t instanceof Function && !t._classPath) return null;
				if (void 0 !== t._refId) return t._refed = !0, {
					_ref: t._refId
				};
				var e = this._index++;
				t._refId = e;
				var n = this._toJSON(t);
				return n._refId = e, n._value = t, this._refs[e] = n, n
			},
			_toJSON: function(t) {
				if (t._classPath) return {
					_classPath: t._classPath
				};
				if (!t._className) return t;
				var e = {
					_className: t._className
				};
				return e.json = t.toJSON ? t.toJSON(this) : t, e
			},
			parseJSON: function(t) {
				return t instanceof Object ? void 0 !== t._ref ? this._refs[t._ref] : void 0 !== t._refId ? this._refs[t._refId] = this._parseJSON(t) : this._parseJSON(t) : t
			},
			_parseJSON: function(t) {
				if (t._classPath) return o(t._classPath);
				if (t._className) {
					var e = o(t._className),
						n = new e;
					if (void 0 !== t._refId && (this._refs[t._refId] = n), n && t.json)
						if (t = t.json, n.parseJSON) n.parseJSON(t, this);
						else
							for (var i in t) n[i] = t[i];
					return n
				}
				return t
			}
		};
		var p = {};
		t.HashList.prototype.toJSON = function(t) {
			var e = [];
			return this.forEach(function(n) {
				e.push(t.toJSON(n))
			}), e
		}, t.HashList.prototype.parseJSON = function(t, e) {
			t.forEach(function(t) {
				this.add(e.parseJSON(t))
			}, this)
		};
		var f = {
			"class": !1,
			id: !1,
			fillGradient: !1,
			syncSelectionStyles: !1,
			originalBounds: !1,
			parent: !1,
			font: !1,
			$data: !1,
			$x: !1,
			$y: !1
		};
		t.BaseUI.prototype.toJSON = function(t) {
			var e = {};
			for (var n in this)
				if ("_" != n[0] && ("$" != n[0] || "_" != n[1]) && 0 != n.indexOf("$invalidate") && f[n] !== !1) {
					var i = this[n];
					if (!(i instanceof Function || i == this["class"].prototype[n])) try {
						e[n] = t.toJSON(i)
					} catch (o) {}
				}
			return e
		}, t.BaseUI.prototype.parseJSON = function(t, e) {
			for (var n in t) {
				var i = e.parseJSON(t[n]);
				this[n] = i
			}
		}, t.Element.prototype.toJSON = function(t) {
			var e = {},
				n = ["enableSubNetwork", "zIndex", "tooltipType", "tooltip", "movable", "selectable", "resizable", "uiClass", "name", "parent", "host"];
			if (this.outputProperties && (n = n.concat(this.outputProperties)), l(t, n, e, this), this.styles) {
				var i = h(t, this.styles);
				i && (e.styles = i)
			}
			if (this.properties) {
				var o = h(t, this.properties);
				o && (e.properties = o)
			}
			var s = this.bindingUIs;
			if (s) {
				var a = [];
				s.forEach(function(e) {
					var n = t.toJSON(e.ui);
					a.push({
						ui: n,
						bindingProperties: e.bindingProperties
					})
				}), e.bindingUIs = a
			}
			return e
		}, t.Element.prototype.parseJSON = function(t, e) {
			if (t.styles) {
				var n = {};
				for (var i in t.styles) n[i] = e.parseJSON(t.styles[i]);
				this.putStyles(n, !0)
			}
			if (t.properties) {
				var o = {};
				for (var i in t.properties) o[i] = e.parseJSON(t.properties[i]);
				this.properties = o
			}
			t.bindingUIs && t.bindingUIs.forEach(function(t) {
				var n = e.parseJSON(t.ui);
				n && this.addUI(n, t.bindingProperties)
			}, this);
			for (var i in t)
				if ("styles" != i && "properties" != i && "bindingUIs" != i) {
					var s = e.parseJSON(t[i]);
					this[i] = s
				}
		}, t.Node.prototype.toJSON = function(e) {
			var n = t.doSuper(this, t.Node, "toJSON", arguments);
			return l(e, ["location", "size", "image", "rotate", "anchorPosition"], n, this), n
		}, t.Group.prototype.toJSON = function(e) {
			var n = t.doSuper(this, t.Group, "toJSON", arguments);
			return l(e, ["minSize", "groupType", "padding", "groupImage", "expanded"], n, this), n
		}, t.ShapeNode.prototype.toJSON = function(e) {
			var n = t.doSuper(this, t.Node, "toJSON", arguments);
			return l(e, ["location", "rotate", "anchorPosition", "path"], n, this), n
		}, t.Edge.prototype.toJSON = function(e) {
			var n = t.doSuper(this, t.Edge, "toJSON", arguments);
			return l(e, ["from", "to", "edgeType", "angle", "bundleEnabled", "pathSegments"], n, this), n
		}, u.prototype = {
			_refs: null,
			_refValues: null,
			_index: 1,
			root: null,
			reset: function() {
				this._globalRefs = {}, this._elementRefs = {}, this._refs = {}, this._refValues = {}, this._index = 1
			},
			getREF: function(t) {
				return this._refs[t]
			},
			clearRef: function() {
				for (var t in this._globalRefs) delete this._globalRefs[t]._value;
				for (var t in this._refValues) delete this._refValues[t]._refId;
				this.reset()
			},
			elementToJSON: function(t) {
				return this._toJSON(t)
			},
			_elementRefs: null,
			_globalRefs: null,
			withGlobalRefs: !0,
			toJSON: function(e) {
				if (!(e instanceof Object)) return e;
				if (e instanceof Function && !e._classPath) return null;
				if (!this.withGlobalRefs) return this._toJSON(e);
				if (e instanceof t.Element) return this._elementRefs[e.id] = !0, {
					_ref: e.id
				};
				if (void 0 === e._refId) {
					var n = this._toJSON(e);
					if (!n) return n;
					var i = e._refId = this._index++;
					return this._refValues[i] = e, this._refs[i] = n, n
				}
				var i = e._refId;
				if (!this._globalRefs[i]) {
					var n = this._refs[i];
					if (!n) return n;
					var o = {};
					for (var s in n) o[s] = n[s], delete n[s];
					n.$ref = i, this._globalRefs[i] = o
				}
				return {
					$ref: i
				}
			},
			_toJSON: function(e) {
				if (e._classPath) return {
					_classPath: e._classPath
				};
				if (!e._className) {
					if (t.isArray(e)) {
						var n = [];
						return e.forEach(function(t) {
							n.push(this.toJSON(t))
						}, this), n
					}
					n = {};
					var i;
					e["class"] && (i = e["class"].prototype);
					for (var o in e) {
						var s = e[o];
						s instanceof Function || i && s == i[o] || (n[o] = this.toJSON(e[o]))
					}
					return n
				}
				var a = {
					_className: e._className
				};
				return a.json = e.toJSON ? e.toJSON(this) : e, a
			},
			jsonToElement: function(t) {
				return void 0 !== t._refId && t._refId in this._refs ? this._refs[t._refId] : this._parseJSON(t)
			},
			parseJSON: function(t) {
				if (!(t instanceof Object)) return t;
				if (!this.withGlobalRefs) return this._parseJSON(t);
				if (void 0 !== t.$ref) {
					var e = this._globalRefs[t.$ref];
					if (!e) return;
					return void 0 === e._value && (e._value = this.parseJSON(e)), e._value
				}
				if (void 0 !== t._ref) {
					var n = this._elementRefs[t._ref];
					if (!n) return;
					return this.jsonToElement(n)
				}
				return this._parseJSON(t)
			},
			_parseJSON: function(e) {
				if (!(e instanceof Object)) return e;
				if (e._classPath) return o(e._classPath);
				if (e._className) {
					var n = o(e._className),
						i = new n;
					if (this.withGlobalRefs && void 0 !== e._refId && (this._refs[e._refId] = i), i && e.json)
						if (e = e.json, i.parseJSON) i.parseJSON(e, this);
						else
							for (var s in e) i[s] = e[s];
					return i
				}
				if (t.isArray(e)) {
					var a = [];
					return e.forEach(function(t) {
						a.push(this.parseJSON(t))
					}, this), a
				}
				var a = {};
				for (var r in e) a[r] = this.parseJSON(e[r]);
				return a
			}
		}, t.GraphModel.prototype.toJSON = function(t) {
			return d(this, t)
		}, t.GraphModel.prototype.parseJSON = function(e, i) {
			var o = e.datas;
			if (o && o.length > 0) {
				if (c(e.version) <= 1.7) {
					var s = new n(i),
						o = e.datas;
					return o.forEach(function(e) {
						var n = s.parseJSON(e);
						n instanceof t.Element && this.add(n)
					}, this), void s.reset()
				}
				var s = new u(i, e.g),
					a = {};
				if (o.forEach(function(t) {
					t._refId && (a[t._refId] = t)
				}), s._globalRefs = e.refs || {}, s._elementRefs = a, o.forEach(function(e) {
					var n = s.jsonToElement(e);
					n instanceof t.Element && this.add(n)
				}, this), e.currentSubNetwork) {
					var r = s.getREF(e.currentSubNetwork._ref);
					r && (this.currentSubNetwork = r)
				}
				s.clearRef()
			}
		}, t.Graph.prototype.toJSON = t.Graph.prototype.exportJSON = function(t, e) {
			e = e || {};
			var n = this.graphModel.toJSON(e.filter);
			return t && (n = JSON.stringify(n, e.replacer, e.space || "	")), n
		}, t.Graph.prototype.parseJSON = function(e, n) {
			t.isString(e) && (e = JSON.parse(e)), this.graphModel.parseJSON(e, n)
		}, s(t, "Q"), t.loadClassPath = s, t.exportJSON = function(t, e) {
			try {
				var n = new u({
					withGlobalRefs: !1
				}).toJSON(t);
				return e ? JSON.stringify(n) : n
			} catch (i) {}
		}, t.parseJSON = function(e) {
			try {
				return t.isString(e) && (e = JSON.parse(e)), new u({
					withGlobalRefs: !1
				}).parseJSON(e)
			} catch (n) {}
		}
	}
}(Q, window, document),
function(t, e) {
	function n(e, n) {
		this.onBoundsChange = n, this.parent = e, this.handleSize = t.isTouchSupport ? 20 : 8, this.boundsDiv = this._createDiv(this.parent), this.boundsDiv.type = "border", this.boundsDiv.style.position = "absolute", this.boundsDiv.style.border = "dashed 1px #888";
		var i = "lt,t,rt,l,r,lb,b,rb";
		i = i.split(",");
		for (var o = 0, s = i.length; s > o; o++) {
			var a = i[o],
				r = this._createDiv(this.parent);
			r.type = "handle", r.name = a, r.style.position = "absolute", r.style.backgroundColor = "#FFF", r.style.border = "solid 1px #555", r.style.width = r.style.height = this.handleSize + "px";
			var l;
			l = "lt" == a || "rb" == a ? "nwse-resize" : "rt" == a || "lb" == a ? "nesw-resize" : "t" == a || "b" == a ? "ns-resize" : "ew-resize", r.style.cursor = l, this[i[o]] = r
		}
		this.interaction = new t.DragSupport(this.parent, this)
	}

	function i() {
		var n = e("<div/>").html(s).contents();
		this.html = n = n[0], document.body.appendChild(this.html), n.addEventListener("mousedown", function(t) {
			t.target == n && this.destroy()
		}.bind(this), !1);
		var i = this._getChild(".graph-export-panel__export_scale"),
			o = this._getChild(".graph-export-panel__export_scale_label");
		i.onchange = function() {
			o.textContent = this.scale = i.value, this.updateOutputSize()
		}.bind(this);
		var a = function(e) {
				var n = this.graph;
				if (n) {
					var o = i.value,
						s = this.imageInfo.scale,
						a = new t.Rect(this.clipBounds.x / s, this.clipBounds.y / s, this.clipBounds.width / s, this.clipBounds.height / s);
					a.offset(this.bounds.x, this.bounds.y);
					var r = n.exportImage(o, a);
					if (!r || !r.data) return !1;
					var l = window.open(),
						h = l.document;
					h.title = n.name || "";
					var u = h.createElement("img");
					if (u.src = r.data, h.body.style.textAlign = "center", h.body.style.margin = "0px", h.body.appendChild(u), e === !0) {
						var d = h.createElement("style");
						d.setAttribute("type", "text/css"), d.setAttribute("media", "print");
						var c = "img {max-width: 100%; max-height: 100%;}";
						a.width / a.height > 1.2 && (c += "\n @page { size: landscape; }"), d.appendChild(document.createTextNode(c)), h.head.appendChild(d), u.style.maxWidth = "100%", u.style.maxHeight = "100%", setTimeout(function() {
							l.print(), l.onfocus = function() {
								l.close()
							}
						}, 100)
					}
				}
			},
			r = this._getChild(".graph-export-panel__export_submit");
		r.onclick = a.bind(this);
		var l = this._getChild(".graph-export-panel__print_submit");
		l.onclick = a.bind(this, !0)
	}

	function o(t) {
		a || (a = new i), a.show(t)
	}
	var s = '<div class="graph-export-panel modal fade">  <div class="modal-dialog">  <div class="modal-content">  <div class="modal-body">  <h3 style="text-align: center;">图片导出预览</h3>  <div>  <label>画布大小</label>  <span class ="graph-export-panel__canvas_size"></span>  </div>  <div style="text-align: center;" title="双击选择全画布范围">  <div class ="graph-export-panel__export_canvas" style="position: relative; display: inline-block;">  </div>  </div>  <div>  <label>导出范围</label>  <span class ="graph-export-panel__export_bounds"></span>  </div>  <div>  <label>缩放比例: <input class ="graph-export-panel__export_scale" type="range" value="1" step="0.2" min="0.2" max="3"><span class ="graph-export-panel__export_scale_label">1</span></label>  </div>  <div>  <label>输出大小: </label><span class ="graph-export-panel__export_size"></span>  </div>  <div style="text-align: right">  <button type="submit" class="btn btn-primary graph-export-panel__export_submit">导出</button>  <button type="submit" class="btn btn-primary graph-export-panel__print_submit">打印</button>  </div>  </div>  </div>  </div>  </div>';
	n.prototype = {
		destroy: function() {
			this.interaction.destroy()
		},
		update: function(e, n) {
			this.wholeBounds = new t.Rect(0, 0, e, n), this._setBounds(this.wholeBounds.clone())
		},
		ondblclick: function() {
			return this._bounds.equals(this.wholeBounds) ? (this.oldBounds || (this.oldBounds = this.wholeBounds.clone().grow(-this.wholeBounds.height / 5, -this.wholeBounds.width / 5)), void this._setBounds(this.oldBounds, !0)) : void this._setBounds(this.wholeBounds.clone(), !0)
		},
		startdrag: function(t) {
			t.target.type && (this.dragItem = t.target)
		},
		ondrag: function(e) {
			if (this.dragItem) {
				t.stopEvent(e);
				var n = e.dx,
					i = e.dy;
				if ("border" == this.dragItem.type) this._bounds.offset(n, i), this._setBounds(this._bounds, !0);
				else if ("handle" == this.dragItem.type) {
					var o = this.dragItem.name;
					"l" == o[0] ? (this._bounds.x += n, this._bounds.width -= n) : "r" == o[0] && (this._bounds.width += n), "t" == o[o.length - 1] ? (this._bounds.y += i, this._bounds.height -= i) : "b" == o[o.length - 1] && (this._bounds.height += i), this._setBounds(this._bounds, !0)
				}
			}
		},
		enddrag: function() {
			this.dragItem && (this.dragItem = !1, this._bounds.width < 0 ? (this._bounds.x += this._bounds.width, this._bounds.width = -this._bounds.width) : 0 == this._bounds.width && (this._bounds.width = 1), this._bounds.height < 0 ? (this._bounds.y += this._bounds.height, this._bounds.height = -this._bounds.height) : 0 == this._bounds.height && (this._bounds.height = 1), this._bounds.width > this.wholeBounds.width && (this._bounds.width = this.wholeBounds.width), this._bounds.height > this.wholeBounds.height && (this._bounds.height = this.wholeBounds.height), this._bounds.x < 0 && (this._bounds.x = 0), this._bounds.y < 0 && (this._bounds.y = 0), this._bounds.right > this.wholeBounds.width && (this._bounds.x -= this._bounds.right - this.wholeBounds.width), this._bounds.bottom > this.wholeBounds.height && (this._bounds.y -= this._bounds.bottom - this.wholeBounds.height), this._setBounds(this._bounds, !0))
		},
		_createDiv: function(t) {
			var e = document.createElement("div");
			return t.appendChild(e), e
		},
		_setHandleLocation: function(t, e, n) {
			t.style.left = e - this.handleSize / 2 + "px", t.style.top = n - this.handleSize / 2 + "px"
		},
		_setBounds: function(t) {
			t.equals(this.wholeBounds) || (this.oldBounds = t), this._bounds = t, t = t.clone(), t.width += 1, t.height += 1, this.boundsDiv.style.left = t.x + "px", this.boundsDiv.style.top = t.y + "px", this.boundsDiv.style.width = t.width + "px", this.boundsDiv.style.height = t.height + "px", this._setHandleLocation(this.lt, t.x, t.y), this._setHandleLocation(this.t, t.cx, t.y), this._setHandleLocation(this.rt, t.right, t.y), this._setHandleLocation(this.l, t.x, t.cy), this._setHandleLocation(this.r, t.right, t.cy), this._setHandleLocation(this.lb, t.x, t.bottom), this._setHandleLocation(this.b, t.cx, t.bottom), this._setHandleLocation(this.rb, t.right, t.bottom), this.onBoundsChange && this.onBoundsChange(this._bounds)
		}
	}, Object.defineProperties(n.prototype, {
		bounds: {
			get: function() {
				return this._bounds
			},
			set: function(t) {
				this._setBounds(t)
			}
		}
	}), i.prototype = {
		canvas: null,
		html: null,
		_getChild: function(t) {
			return e(this.html).find(t)[0]
		},
		initCanvas: function() {
			var e = this._getChild(".graph-export-panel__export_canvas");
			e.innerHTML = "";
			var i = t.createCanvas(!0);
			e.appendChild(i), this.canvas = i;
			var o, s = this._getChild(".graph-export-panel__export_bounds"),
				a = this._getChild(".graph-export-panel__export_size"),
				r = function() {
					var t = this.canvas,
						e = t.g,
						n = t.ratio || 1;
					e.save(), e.clearRect(0, 0, t.width, t.height), e.drawImage(this.imageInfo.canvas, 0, 0), e.beginPath(), e.moveTo(0, 0), e.lineTo(t.width, 0), e.lineTo(t.width, t.height), e.lineTo(0, t.height), e.lineTo(0, 0);
					var i = o.x * n,
						s = o.y * n,
						a = o.width * n,
						r = o.height * n;
					e.moveTo(i, s), e.lineTo(i, s + r), e.lineTo(i + a, s + r), e.lineTo(i + a, s), e.closePath(), e.fillStyle = "rgba(0, 0, 0, 0.3)", e.fill(), e.restore()
				},
				l = function(t) {
					o = t, this.clipBounds = o, r.call(this);
					var e = o.width / this.imageInfo.scale | 0,
						n = o.height / this.imageInfo.scale | 0;
					s.textContent = (o.x / this.imageInfo.scale | 0) + ", " + (o.y / this.imageInfo.scale | 0) + ", " + e + ", " + n, this.updateOutputSize()
				};
			this.updateOutputSize = function() {
				var t = this._getChild(".graph-export-panel__export_scale"),
					e = t.value,
					n = o.width / this.imageInfo.scale * e | 0,
					i = o.height / this.imageInfo.scale * e | 0,
					s = n + " X " + i;
				n * i > 12e6 && (s += "<span style='color: #F66;'>图幅太大，导出时可能出现内存不足</span>"), a.innerHTML = s
			};
			var h = new n(i.parentNode, l.bind(this));
			this.update = function() {
				var t = this.canvas.ratio || 1,
					e = this.imageInfo.width / t,
					n = this.imageInfo.height / t;
				this.canvas.setSize(e, n), h.update(e, n)
			}
		},
		destroy: function() {
			this.graph = null, this.imageInfo = null, this.clipBounds = null, this.bounds = null
		},
		show: function(t) {
			e(this.html).modal("show"), this.graph = t;
			var n = t.bounds;
			this.bounds = n;
			var i = this._getChild(".graph-export-panel__canvas_size");
			i.textContent = (0 | n.width) + " X " + (0 | n.height);
			var o, s = Math.min(500, screen.width / 1.3);
			o = n.width > n.height ? Math.min(1, s / n.width) : Math.min(1, s / n.height), this.canvas || this.initCanvas(), this.imageInfo = t.exportImage(o * this.canvas.ratio), this.imageInfo.scale = o, this.update()
		}
	};
	var a;
	t.showExportPanel = o
}(Q, jQuery),
function(t, e) {
	function n(t) {
		t = t || window.event;
		var e = t.dataTransfer,
			n = t.target;
		e.setData("text", n.getAttribute(l))
	}

	function i(t, e, i, o) {
		var s = document.createElement("img");
		return s.src = e, s.setAttribute("draggable", "true"), s.setAttribute("title", i), o = o || {}, o.image || o.type && "Node" != o.type || (o.image = e), o.label = o.label || i, o.title = i, s.setAttribute(l, JSON.stringify(o)), s.ondragstart = n, t.appendChild(s), s
	}

	function o(e, n, i, o, r) {
		for (var l in e) {
			var h = e[l];
			t.isArray(h) ? s(h, n, i, o, r) : n.appendChild(a(h, i))
		}
	}

	function s(t, e, n, i, o) {
		var s = document.createElement("div");
		s.className = i ? "btn-group-vertical" : "btn-group", o !== !1 && s.setAttribute("data-toggle", "buttons");
		for (var r = 0, l = t.length; l > r; r++) t[r].type || o === !1 || (t[r].type = "radio"), s.appendChild(a(t[r], n));
		e.appendChild(s)
	}

	function a(n, i) {
		if ("search" == n.type) {
			var o = document.createElement("div");
			o.style.display = "inline-block", o.style.verticalAlign = "middle", o.innerHTML = '<div class="input-group input-group-sm" style="width: 150px;">            <input type="text" class="form-control" placeholder="' + (n.placeholder || "") + '">                <span class="input-group-btn">                    <div class="btn btn-default" type="button"></div>                </span>            </div>';
			var s = o.getElementsByTagName("input")[0];
			n.id && (s.id = n.id);
			var a = e(o).find(".btn")[0];
			if (n.iconClass) {
				var r = document.createElement("div");
				e(r).addClass(n.iconClass), a.appendChild(r)
			} else n.name && a.appendChild(document.createTextNode(" " + n.name)); if (n.input = s, n.search) {
				var l = function() {
						n.searchInfo = null
					},
					h = function(t) {
						var e = s.value;
						if (!e) return void l();
						if (!n.searchInfo || n.searchInfo.value != e) {
							var i = n.search(e, n);
							if (!i || !i.length) return void l();
							n.searchInfo = {
								value: e,
								result: i
							}
						}
						u(t)
					},
					u = function(t) {
						if (n.select instanceof Function && n.searchInfo && n.searchInfo.result && n.searchInfo.result.length) {
							var e = n.searchInfo,
								i = n.searchInfo.result;
							if (1 == i.length) return void n.select(i[0], 0);
							void 0 === e.index ? e.index = 0 : (e.index += t ? -1 : 1, e.index < 0 && (e.index += i.length), e.index %= i.length), n.select(i[e.index], e.index) === !1 && (n.searchInfo = null, h())
						}
					};
				s.onkeydown = function(e) {
					return 27 == e.keyCode ? (l(), s.value = "", void t.stopEvent(e)) : void(13 == e.keyCode && h(e.shiftKey))
				}, a.onclick = function() {
					h()
				}
			}
			return o
		}
		if ("input" == n.type) {
			var o = document.createElement("div");
			o.style.display = "inline-block", o.style.verticalAlign = "middle", o.innerHTML = '<div class="input-group input-group-sm" style="width: 150px;">            <input type="text" class="form-control">                <span class="input-group-btn">                    <button class="btn btn-default" type="button"></button>                </span>            </div>';
			var s = o.getElementsByTagName("input")[0],
				a = o.getElementsByTagName("button")[0];
			return a.innerHTML = n.name, n.input = s, n.action && (a.onclick = function(t) {
				n.action.call(i || window.graph, t, n)
			}), o
		}
		if ("select" == n.type) {
			var o = document.createElement("select");
			o.className = "form-control";
			var d = n.options;
			return d.forEach(function(t) {
				var e = document.createElement("option");
				e.innerHTML = t, e.value = t, o.appendChild(e)
			}), o.value = n.value, n.action && (o.onValueChange = function(t) {
				n.action.call(i || window.graph, t, n)
			}), o
		}
		if (n.type) {
			var c = document.createElement("label"),
				a = document.createElement("input");
			n.input = a, a.setAttribute("type", n.type), c.appendChild(a), n.selected && (a.setAttribute("checked", "checked"), "radio" == n.type && (c.className += "active"))
		} else var c = document.createElement("div"); if (c.className += "btn btn-default btn-sm", n.icon) {
			var r = document.createElement("img");
			r.src = n.icon, c.appendChild(r)
		} else if (n.iconClass) {
			var r = document.createElement("div");
			e(r).addClass(n.iconClass), c.appendChild(r)
		} else n.name && c.appendChild(document.createTextNode(" " + n.name));
		return n.name && c.setAttribute("title", n.name), n.action && (c.onclick = function(t) {
			n.action.call(i || window.graph, t, n)
		}), c
	}

	function r(e, n, i) {
		function s() {
			return e instanceof t.Graph ? e : e()
		}

		function a(t, e, n) {
			s().interactionMode = e.value, s().interactionProperties = n || e
		}
		var r = {
			interactionModes: [{
				name: "默认模式",
				value: t.Consts.INTERACTION_MODE_DEFAULT,
				selected: !0,
				iconClass: "icon toolbar-default",
				action: a
			}, {
				name: "框选模式",
				value: t.Consts.INTERACTION_MODE_SELECTION,
				iconClass: "icon toolbar-rectangle_selection",
				action: a
			}, {
				name: "浏览模式",
				value: t.Consts.INTERACTION_MODE_VIEW,
				iconClass: "icon toolbar-pan",
				action: a
			}],
			zoom: [{
				name: "放大",
				iconClass: "icon toolbar-zoomin",
				action: function() {
					s().zoomIn()
				}
			}, {
				name: "缩小",
				iconClass: "icon toolbar-zoomout",
				action: function() {
					s().zoomOut()
				}
			}, {
				name: "1:1",
				iconClass: "icon toolbar-zoomreset",
				action: function() {
					s().scale = 1
				}
			}, {
				name: "纵览",
				iconClass: "icon toolbar-overview",
				action: function() {
					s().zoomToOverview()
				}
			}],
			editor: [{
				name: "创建连线",
				value: t.Consts.INTERACTION_MODE_CREATE_EDGE,
				iconClass: "icon toolbar-edge",
				action: a
			}, {
				name: "创建L型连线",
				value: t.Consts.INTERACTION_MODE_CREATE_SIMPLE_EDGE,
				iconClass: "icon toolbar-edge_VH",
				action: a,
				edgeType: t.Consts.EDGE_TYPE_VERTICAL_HORIZONTAL
			}, {
				name: "创建多边形",
				value: t.Consts.INTERACTION_MODE_CREATE_SHAPE,
				iconClass: "icon toolbar-polygon",
				action: a
			}, {
				name: "创建线条",
				value: t.Consts.INTERACTION_MODE_CREATE_LINE,
				iconClass: "icon toolbar-line",
				action: a
			}],
			search: {
				name: "Find",
				placeholder: "Name",
				iconClass: "icon toolbar-search",
				type: "search",
				id: "search_input",
				search: function(t) {
					var e = [],
						n = new RegExp(t, "i");
					return s().forEach(function(t) {
						t.name && n.test(t.name) && e.push(t.id)
					}), e
				},
				select: function(t) {
					if (t = s().graphModel.getById(t), !t) return !1;
					s().setSelection(t), s().sendToTop(t);
					var e = s().getUIBounds(t);
					e && s().centerTo(e.cx, e.cy, Math.max(2, s().scale), !0)
				}
			},
			"export": {
				name: "导出图片",
				iconClass: "icon toolbar-print",
				action: function() {
					t.showExportPanel(s())
				}
			}
		};
		if (i)
			for (var l in i) r[l] = i[l];
		return o(r, n, this, !1, !1), n
	}
	var l = "draginfo";
	t.createDNDImage = i, t.createToolbar = r, t.createButtonGroup = s
}(Q, jQuery),
function(t) {
	function e(e, n, i) {
		var o = document.documentElement,
			s = new t.Rect(window.pageXOffset, window.pageYOffset, o.clientWidth - 2, o.clientHeight - 2),
			a = e.offsetWidth,
			r = e.offsetHeight;
		n + a > s.x + s.width && (n = s.x + s.width - a), i + r > s.y + s.height && (i = s.y + s.height - r), n < s.x && (n = s.x), i < s.y && (i = s.y), e.style.left = n + "px", e.style.top = i + "px"
	}

	function n(t, e) {
		for (var n = e.parentNode; null != n;) {
			if (n == t) return !0;
			n = n.parentNode
		}
		return !1
	}

	function i(t) {
		return t.touches && t.touches.length && (t = t.touches[0]), {
			x: t.pageX,
			y: t.pageY
		}
	}

	function o(e, n) {
		var o = n.popupmenu,
			s = i(e),
			a = s.x,
			r = s.y,
			l = o.getMenuItems(n, n.getElement(e), e);//原始右键菜单
			//l = o.getBpmMenuItems(n, n.getElement(e), e);//自定义右键菜单
		l && (o.items = l, o.showAt(a, r), t.stopEvent(e))
	}
	var s = function(t) {
			this.items = t || []
		},
		a = "dropdown-menu";
	s.Separator = "divider", s.prototype = {
		dom: null,
		_invalidateFlag: !0,
		add: function(t) {
			this.items.push(t), this._invalidateFlag = !0
		},
		addSeparator: function() {
			this.add(s.Separator)
		},
		showAt: function(t, n) {
			return this.items && this.items.length ? (this._invalidateFlag && this.render(), this.dom.style.display = "block", document.body.appendChild(this.dom), void e(this.dom, t, n)) : !1
		},
		hide: function() {
			this.dom && this.dom.parentNode && this.dom.parentNode.removeChild(this.dom)
		},
		render: function() {
			if (this._invalidateFlag = !1, this.dom) this.dom.innerHTML = "";
			else {
				this.dom = document.createElement("ul"), this.dom.setAttribute("role", "menu"), this.dom.className = a;
				var e = t.isTouchSupport ? "touchstart" : "mousedown";
				if (!this.stopEditWhenClickOnWindow) {
					var i = this;
					this.stopEditWhenClickOnWindow = function(t) {
						n(i.html, t.target) && i.hide()
					}
				}
				window.addEventListener("mousedown", this.stopEditWhenClickOnWindow, !0), this.dom.addEventListener(e, function(e) {
					t.stopEvent(e)
				}, !1)
			}
			for (var o = 0, s = this.items.length; s > o; o++) {
				var r = this.renderItem(this.items[o]);
				this.dom.appendChild(r)
			}
		},
		html2Escape: function(t) {
			return t.replace(/[<>&"]/g, function(t) {
				return {
					"<": "&lt;",
					">": "&gt;",
					"&": "&amp;",
					'"': "&quot;"
				}[t]
			})
		},
		renderItem: function(e) {
			var n = document.createElement("li");
			if (n.setAttribute("role", "presentation"), e == s.Separator) return n.className = s.Separator, n.innerHTML = " ", n;
			if (t.isString(e)) return n.innerHTML = '<a role="menuitem" tabindex="-1" href="#">' + this.html2Escape(e) + "</a>", n;
			e.selected && (n.style.backgroundPosition = "3px 5px", n.style.backgroundRepeat = "no-repeat", n.style.backgroundImage = "url('data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAAPklEQVQ4y2P4//8/AyWYYdQA7AYAAZuamlo7ED+H4naQGNEGQDX/R8PtpBjwHIsBz+lqAGVeoDgQR1MiaRgAnxW7Q0QEK0cAAAAASUVORK5CYII=')");
			var i = document.createElement("a");
			if (i.setAttribute("role", "menuitem"), i.setAttribute("tabindex", "-1"), i.setAttribute("href", "javascript:void(0)"), n.appendChild(i), e.html) i.innerHTML = e.html;
			else {
				var o = e.text || e.name;
				o && (i.innerHTML = this.html2Escape(o))
			}
			var a = e.className;
			a && (n.className = a);
			var r = e.action,
				l = this,
				h = function(n) {
					r && r.call(e.scope, n, e), t.isIOS || n.target.focus(), setTimeout(function() {
						l.hide()
					}, 100)
				};
			return t.isTouchSupport ? i.ontouchstart = h : n.onclick = h, n
		},
		getMenuItems: function(e, n) {
			var i = [];
			if("StartEvent"==n.bpmNodeType||"EndEvent"==n.bpmNodeType){
				i.push({
					id:"nodeMsgTemp",
					text:"节点信息模板",
					action: function() {
					
				}}), 
				i.push({
					id:"nodeSql",
					text:"节点Sql设置",
					action: function() {
						
					}
				}),  
				i.push({
					id:"flowEvent",
					text:"事件设置",
					action: function() {
						
					}
				}),  
				i.push({
					id:"triggerNewFlow",
					text:"触发新流程设置",
					action: function() {
						
					}
				}),  i.push(t.PopupMenu.Separator);
			}
			if("exclusiveGateway"==n.bpmNodeType){
					i.push({
						id:"flowCodition",
						text:"设置分支条件",
						action: function() {
						
					}}), 
					 i.push(t.PopupMenu.Separator);
				}
			if("multiUserTask"==n.bpmNodeType){
						i.push({
							id:"nodeMsgTemp",
							text:"节点信息模板",
							action: function() {
							
						}}), 
						i.push({
							id:"nodeSql",
							text:"节点sql设置",
							action: function() {
							
						}}), 
						i.push({
							id:"signExecuteEvent",
							text:"会签执行事件设置",
							action: function() {
							
						}}), 
						i.push({
							id:"flowRule",
							text:"跳转规则设置",
							action: function() {
							
						}}), 
						i.push({
							id:"flowVote",
							text:"会签投票规则设置",
							action: function() {
							
						}}), 
						i.push({
							id:"flowEvent",
							text:"事件设置",
							action: function() {
							
						}}), 
						i.push({
							id:"flowDue",
							text:"任务催办设置",
							action: function() {
							
						}}), 
						i.push({
							id:"flowOrgUser",
							text:"人员选择器设置",
							action: function() {
							
						}}), 
						i.push({
							id:"informType",
							text:"通知方式",
							action: function() {
							
						}}), 
						 i.push(t.PopupMenu.Separator);
					}
				if("webService"==n.bpmNodeType){
							i.push({
								id:"webServiceSet",
								text:"WebService设置",
								action: function() {
								
							}
							}), 
							 i.push(t.PopupMenu.Separator);
						}
				if("email"==n.bpmNodeType){
								i.push({
									id:"flowMessage",
									text:"消息参数",
									action: function() {
									
								}}), 
								 i.push(t.PopupMenu.Separator);
							}
						
				if("script"==n.bpmNodeTyp||"ServiceTask"==n.bpmNodeType){
							i.push({
								id:"flowEvent",
								text:"设置脚本",
								action: function() {
								
								var url=__ctx + "/platform/bpm/bpmNodeScript/edit.ht?type="+"script"+"&actDefId=" + "add:1:10000061600157"+"&nodeId=" +"ScriptTask1" +"&defId="+"10000061600153";
                               alert(url);
								if(!1){
									url += "&parentActDefId="+"";
								}url=url.getNewUrl();
								DialogUtil.open({
									height:600,
									width:800,
									title : '设置流程事件脚本',
									url: url, 
									isResize: true,
								});
								
								}}), 
							i.push({
								    id:"parameterBinding",
									text:"主从参数绑定",
									action: function() {
									
									}}),
								i.push(t.PopupMenu.Separator);
								}	            		
				if("callActivity"==n.bpmNodeType){
							i.push({
								id: "viewSubFlow",
								text:"查看调用子流程示意图",
								action: function() {
									
								}}), 
							i.push({
								id:"flowSet",
								text:"设置子流程",
								action: function() {
										
								}}), 
							 i.push(t.PopupMenu.Separator);
							}
				if("subProcess"==n.bpmNodeType){
							i.push({
								id:"flowEvent",
								text:"事件设置",
								action: function() {
										
									}}), 
								 i.push(t.PopupMenu.Separator);
								}
				if("userTask"==n.bpmNodeType){
					i.push({
						id:"flowKey",
						text:"任务信息设置",
						action: function() {
						
					}}), 
					i.push({
						id:"nodeMsgTemp",
						text:"节点信息模板",
						action: function() {
						
					}}), 
					i.push({
						id:"nodeSql",
						text:"节点sql设置",
						action: function() {
						
					}}), 
					i.push({
						id:"triggerNewFlow",
						text:"触发新流程设置",
						action: function() {
						
					}}), 
					i.push({
						id:"flowRule",
						text:"跳转规则设置",
						action: function() {
						
					}}), 
					i.push({
						id:"flowForkJoin",
						text:"流程分发汇总",
						action: function() {
						
					}}), 
					i.push({
						id:"flowEvent",
						text:"事件设置",
						action: function() {
						
					}}), 
					i.push({
						id:"flowDue",
						text:"任务催办设置",
						action: function() {
						
					}}), 
					i.push({
						id:"flowOrgUser",
						text:"人员选择器设置",
						action: function() {
						
					}}), 
					i.push({
						id:"informType",
						text:"通知方式",
						action: function() {
						
					}}), 
					 i.push(t.PopupMenu.Separator);
				}
				
			if (n) {
				var o = n instanceof t.ShapeNode,
					s = (n instanceof t.Group, !o && n instanceof t.Node, n instanceof t.Edge);
				if (i.push({
					text: "编辑名称",
					action: function() {
						t.prompt("输入图元名称", n.name || "", function(t) {
							null !== t && (n.name = t)
						})
					}
				}), s) {
					var a = n.getStyle(t.Styles.EDGE_LINE_DASH) || t.DefaultStyles[t.Styles.EDGE_LINE_DASH];
					i.push({
						text: a ? "实线样式" : "虚线样式",
						action: function() {
							n.setStyle(t.Styles.EDGE_LINE_DASH, a ? null : [5, 3])
						}
					}), i.push({
						text: "连线线宽",
						action: function() {
							t.prompt("输入连线线宽", n.getStyle(t.Styles.EDGE_WIDTH) || t.DefaultStyles[t.Styles.EDGE_WIDTH], function(e) {
								null !== e && (e = parseFloat(e), n.setStyle(t.Styles.EDGE_WIDTH, e))
							})
						}
					}), i.push({
						text: "连线颜色",
						action: function() {
							t.prompt("输入连线颜色", n.getStyle(t.Styles.EDGE_COLOR) || t.DefaultStyles[t.Styles.EDGE_COLOR], function(e) {
								null !== e && n.setStyle(t.Styles.EDGE_COLOR, e)
							})
						}
					})
				} else n.parent instanceof t.Group && i.push({
					text: "脱离分组",
					action: function() {
						n.parent = null
					}
				});
				i.push(t.PopupMenu.Separator),
				
				i.push({
					text: "置顶显示",
					action: function() {
						n.zIndex = 1, e.sendToTop(n), e.invalidate()
					}
				}), i.push({
					text: "置底显示",
					action: function() {
						n.zIndex = -1, e.sendToBottom(n), e.invalidate()
					}
				}), i.push({
					text: "恢复默认层",
					action: function() {
						n.zIndex = 0, e.invalidate()
					}
				}), i.push(t.PopupMenu.Separator)
			}
			i.push({
				text: "清空画布",
				action: function() {
					e.clear()
				}
			}), i.push(t.PopupMenu.Separator), i.push({
				text: "放大",
				action: function(t) {
					var n = e.globalToLocal(t);
					e.zoomIn(n.x, n.y, !0)
				}
			}), i.push({
				text: "缩小",
				action: function(t) {
					var n = e.globalToLocal(t);
					e.zoomOut(n.x, n.y, !0)
				}
			}), i.push({
				text: "1:1",
				action: function(t) {
					e.globalToLocal(t);
					e.scale = 1
				}
			}), i.push(t.PopupMenu.Separator);
			for (var r = e.interactionMode, l = [{
				text: "平移模式",
				value: t.Consts.INTERACTION_MODE_DEFAULT
			}, {
				text: "框选模式",
				value: t.Consts.INTERACTION_MODE_SELECTION
			}], h = 0, u = l.length; u > h; h++) {
				var d = l[h];
				d.value == r && (d.selected = !0), d.action = function(t, n) {
					e.interactionMode = n.value
				}, i.push(d)
			}
			return i.push(t.PopupMenu.Separator), i.push({
				html: '<a href="http://qunee.com" target="_blank">Qunee - ' + t.version + "</a>"
			}), i
		}
	}, Object.defineProperties(s.prototype, {
		items: {
			get: function() {
				return this._items
			},
			set: function(t) {
				this._items = t, this._invalidateFlag = !0
			}
		}
	});
	var r = {
		onstart: function(t, e) {
			e._popupmenu.hide()
		}
	};
	t.isTouchSupport && (r.onlongpress = function(t, e) {
		o(t, e)
	}), Object.defineProperties(t.Graph.prototype, {
		popupmenu: {
			get: function() {
				return this._popupmenu
			},
			set: function(t) {
				this._popupmenu != t && (this._popupmenu = t, this._contextmenuListener || (this._contextmenuListener = r, this.addCustomInteraction(this._contextmenuListener), this.html.oncontextmenu = function(t) {
					o(t, this)
				}.bind(this)))
			}
		}
	}), t.PopupMenu = s
}(Q, jQuery),
function(t, e) {
	"use strict";

	function n(t) {
		var e = t.dataTransfer,
			n = t.target;
		e.setData("text", n.getAttribute(l))
	}

	function i(t, e, n, i, o) {
		var s = document.createElement(e);
		return (o || i) && s.setAttribute("title", o || i), n && (s.className = n), i && (s.innerHTML = i), t && t.appendChild(s), s
	}

	function o(t) {
		if (!t) return c;
		var e = {};
		for (var n in c) e[n] = c[n];
		for (var n in t) {
			var i = p[n];
			i && (e[i] = t[n])
		}
		return e
	}

	function s(e) {
		return t.isString(e) || e.draw instanceof Function
	}

	function a(e, n) {
		n = e.name || n;
		var a = e.root,
			r = e.images,
			l = i("group"),
			d = i("group__title", l);
		d.onclick = f, i(null, d, "span", n), i("icon group-expand", d, "span");
		var c = i("group__items", l),
			p = document.createElement("div");
		if (p.style.clear = "both", l.appendChild(p), !r) return l;
		var _ = e.imageWidth || this.imageWidth,
			g = e.imageHeight || this.imageHeight;
		return u(r, function(e, n) {
			if ("_classPath" != n && "_className" != n) {
				var r;
				r = s(e) ? e : e.image;
				var l, u;
				if (r) {
					var d;
					t.isString(r) ? (d = r, !t.hasImage(r) && a && (r = a + r)) : d = e.imageName || e.name || n || "drawable-" + this._index++, t.hasImage(d) || t.registerImage(d, r), l = t.createCanvas(_, g, !0), t.drawImage(d, l, o(e.styles)), s(e) ? e = {
						image: d
					} : e.image = d, u = d
				} else {
					if (!e.html) return;
					var l = document.createElement("div");
					l.style.width = _ + "px", l.style.height = g + "px", l.style.lineHeight = g + "px", l.style.overflow = "hidden", l.innerHTML = e.html
				}
				u = e.tooltip || e.label || u || n, l.setAttribute("title", u);
				var p = i("group__item", c);
				h.appendDNDInfo(l, e), p.appendChild(l)
			}
		}, this), l
	}

	function r(t, n) {
		n = n || {}, this.dom = t, e(t).addClass("layout graph-editor"), this.createGraph(n.styles || d), this.createToolbar(), this.createToolbox(), this.createJSONPane(), e(t).borderLayout();
		var i = n.callback || function() {
			this.graph.moveToCenter()
		};
		this.toolbar && this.initToolbar(this.toolbar, this.graph), this.toolbox && this.initToolbox(this.toolbox, this.graph, n.images), this.initContextMenu(this.graph), n.data ? this.loadDatas(this.graph, n.data, i) : i.call(this, this)
	}
	var l = "draginfo",
		h = {
			appendDNDInfo: function(e, i) {
				return e.setAttribute("draggable", "true"), e.setAttribute(l, t.exportJSON(i, !0)), e.ondragstart = n, e
			},
			getFirstChild: function(t, e) {
				var n = t.find(e);
				return n.length ? n[0] : void 0
			}
		};
	window.requestFileSystem = window.requestFileSystem || window.webkitRequestFileSystem;
	null != window.requestFileSystem;
	e.fn.graphEditor = function(t) {
		return this.each(function() {
			var e = this.graphEditor;
			return e || (this.graphEditor = e = new r(this, t)), e
		})
	};
	var i = function(t, n, i, o) {
			var s = document.createElement(i || "div");
			return s.className = t, e(s).html(o), n && n.appendChild(s), s
		},
		u = function(t, e, n) {
			if (Array.isArray(t)) return t.forEach(function(t) {
				e.call(this, t)
			}, n);
			for (var i in t) e.call(n, t[i], i)
		},
		d = {};
	d[t.Styles.SHAPE_FILL_COLOR] = t.toColor(3435973836), d[t.Styles.SELECTION_COLOR] = "#888", d[t.Styles.SELECTION_SHADOW_BLUR] = 5, d[t.Styles.SELECTION_SHADOW_OFFSET_X] = 2, d[t.Styles.SELECTION_SHADOW_OFFSET_Y] = 2;
	var c = {
			fillColor: "#EEE",
			lineWidth: 1,
			strokeStyle: "#2898E0",
			padding: {
				left: 1,
				top: 1,
				right: 5,
				bottom: 5
			},
			shadowColor: "#888",
			shadowOffsetX: 2,
			shadowOffsetY: 2,
			shadowBlur: 3
		},
		p = {};
	p[t.Styles.RENDER_COLOR] = "renderColor", p[t.Styles.RENDER_COLOR_BLEND_MODE] = "renderColorBlendMode", p[t.Styles.SHAPE_FILL_COLOR] = "fillColor", p[t.Styles.SHAPE_STROKE_STYLE] = "strokeStyle", p[t.Styles.SHAPE_LINE_DASH] = "borderLineDash", p[t.Styles.SHAPE_LINE_DASH_OFFSET] = "borderLineDashOffset", p[t.Styles.SHAPE_OUTLINE] = "outline", p[t.Styles.SHAPE_OUTLINE_STYLE] = "outlineStyle", p[t.Styles.LINE_CAP] = "lineGap", p[t.Styles.LINE_JOIN] = "lineJoin", p[t.Styles.BACKGROUND_COLOR] = "backgroundColor", p[t.Styles.BACKGROUND_GRADIENT] = "backgroundGradient", p[t.Styles.BORDER] = "border", p[t.Styles.BORDER_COLOR] = "borderColor", p[t.Styles.BORDER_LINE_DASH] = "borderLineDash", p[t.Styles.BORDER_LINE_DASH_OFFSET] = "borderLineDashOffset";
	var f = function(t) {
			for (var n = t.target.parentNode; n && !e(n).hasClass("group");) n = n.parentNode;
			n && (e(n).hasClass("group--closed") ? e(n).removeClass("group--closed") : e(n).addClass("group--closed"))
		},
		_ = function(t, e) {
			var n = t.find(e);
			return n.length ? n[0] : void 0
		};
	r.prototype = {
		_getFirst: function(t) {
			return _(e(this.dom), "." + t)
		},
		imageWidth: 40,
		imageHeight: 40,
		toolbar: null,
		toolbox: null,
		propertyPane: null,
		graph: null,
		createGraph: function(e) {
			var n = this._getFirst("graph-editor__canvas");
			n || (n = i("graph-editor__canvas ", this.dom), n.setAttribute("data-options", 'region:"center"'));
			var o = this.graph = new t.Graph(n);
			return o.allowEmptyLabel = !0, o.originAtCenter = !1, o.editable = !0, o.styles = e, o.getDropInfo = function(e, n) {
				return n ? t.parseJSON(n) : void 0
			}, o
		},
		createToolbar: function() {
			var t = this._getFirst("graph-editor__toolbar");
			return t ? this.toolbar = t : (this.toolbar = t = i("graph-editor__toolbar", this.dom), t.setAttribute("data-options", 'region:"north", height: 40'), t)
		},
		createToolbox: function() {
			var t = this._getFirst("graph-editor__toolbox");
			return t ? this.toolbox = t : (this.toolbox = t = i("graph-editor__toolbox", this.dom), t.setAttribute("data-options", "region:'west', width:'18%', left:15, min-width:100, max-width:300"), t)
		},
		createPropertyPane: function() {
			var t = this._getFirst("graph-editor__property");
			return t ? this.propertyPane = t : (this.propertyPane = t = i("graph-editor__property", this.dom), t.setAttribute("data-options", "region:'east', width: '20%', right: 15, min-width: 100, max-width: '300'"), t)
		},
		getJSONTextArea: function() {
			return _(e(this.jsonPane), "textarea")
		},
		exportJSON: function(t) {
			if (t && this.jsonPane) {
				var e = this.graph.exportJSON(!0, {
					space: "  "
				});
				return this.getJSONTextArea().value = e
			}
			return this.graph.exportJSON.apply(this.graph, arguments)
		},
		submitJSON: function() {
			var t = this.getJSONTextArea().value;
			this.graph.clear(), this.graph.parseJSON(t)
		},
		createJSONPane: function() {
			var e = this._getFirst("graph-editor__json");
			if (e) return this.jsonPane = e;
			this.jsonPane = e = i("graph-editor__json", this.dom), e.appendChild(document.createElement("textarea"));
			var n = i("graph-editor__json__buttons", e),
				o = [{
					name: "更新",
					action: this.exportJSON.bind(this)
				}, {
					name: "提交",
					action: this.submitJSON.bind(this)
				}];
			return t.createButtonGroup(o, n), e.style.display = "none", e
		},
		_index: 0,
		loadDatas: function(e, n, i) {
			t.loadJSON(n, function(t) {
				e.parseJSON(t), i instanceof Function && i.call(this, this)
			}.bind(this))
		},
		_createToolBoxItems: function(e, n) {
			return t.isArray(e) ? void u(e, function(t, e) {
				n.appendChild(a.call(this, t, e))
			}, this) : void n.appendChild(a.call(this, e))
		},
		initToolbox: function(e, n, i) {
			var o = [{
					label: "Node",
					image: "Q-node"
				}, {
					type: "Text",
					label: "Text",
					html: '<span style="background-color: #2898E0; color:#FFF; padding: 3px 5px;">文本</span>',
					styles: {
						"label.background.color": "#2898E0",
						"label.color": "#FFF",
						"label.padding": new t.Insets(3, 5)
					}
				}, {
					type: "Group",
					label: "Group",
					image: "Q-group"
				}, {
					label: "SubNetwork",
					image: "Q-subnetwork",
					properties: {
						enableSubNetwork: !0
					}
				}],
				s = [{
					name: "基本节点",
					images: o
				}, {
					name: "注册图标",
					images: t.getAllImages()
				}, {
					name: "内置形状",
					images: t.Shapes.getAllShapes(this.imageWidth, this.imageHeight)
				}];
			this._createToolBoxItems(s, e, "Q-"), i && this._createToolBoxItems(i, e)
		},
		initToolbar: function(e, n) {
			t.createToolbar(n, e, {
				save: {
					name: "导出JSON",
					iconClass: "icon toolbar-json",
					action: this.showJSONPanel.bind(this)
				}
			})
		},
		showExportPanel: function() {
			t.showExportPanel(this.graph)
		},
		showJSONPanel: function(t) {
			var n = t.target;
			e(n).hasClass("btn") || (n = n.parentNode);
			var i = e(n).hasClass("active");
			i ? e(n).removeClass("active") : e(n).addClass("active"), i = !i;
			var o = this.jsonPane;
			o.style.display = i ? "" : "none", i && this.exportJSON(!0)
		},
		initContextMenu: function(e) {
			e.popupmenu = new t.PopupMenu
		}
	}
}(Q, jQuery);