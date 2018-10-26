if (typeof FormData == 'undefined') {
	FormData = {};
}

/**
 * 统计函数集
 */
FormData.FunctionType = [ {
	name : 'sum',
	des : '求和',
	handle : function(self, target,callback) {
		var sum = 0;
		for ( var i = 0, c; c = target[i++];) {
			var val = $(c).val();
			if (val == "")
				continue;
			var num = Number(val);
			if (num || num == 0) {
				sum += num;
			}
		}
		$(self).val(sum);
		callback(self,sum);
	}
}, {
	name : 'average',
	des : '求平均值',
	handle : function(self, target,callback) {
		var sum = 0,
			count = 0;
		for ( var i = 0, c; c = target[i++];) {
			var val = $(c).val();
			if (val == "")
				continue;
			var num = Number(val);
			if (num || num == 0) {
				sum += num;
				count++;
			}
		}
		$(self).val(sum / count);
		callback(self,sum / count);
	}
}, {
	name : 'max',
	des : '求最大值',
	handle : function(self, target,callback) {
		var max = null;
		for ( var i = 0, c; c = target[i++];) {
			var val = $(c).val();
			if (val == "")
				continue;
			var num = Number(val);
			if (num || num == 0) {
				if (max == null) {
					max = num;
				}
				if (num > max) {
					max = num;
				}
			}
		}
		$(self).val(max);
		callback(self,max);
	}
}, {
	name : 'min',
	des : '求最小值',
	handle : function(self, target,callback) {
		var min = null;
		for ( var i = 0, c; c = target[i++];) {
			var val = $(c).val();
			if (val == "")
				continue;
			var num = Number(val);
			if (num || num == 0) { 
				if (min == null) {
					min = num;
				}
				if (num < min) {
					min = num;
				}
			}
		}
		$(self).val(min);
		callback(self,min);
	}
} ];