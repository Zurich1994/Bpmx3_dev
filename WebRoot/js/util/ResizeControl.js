/**
 * 修改表格大小
 * 如此表格
 * <table>
 * <tr>
 * <td> </td>
 * <td id="tdId"> </td>
 * <td> </td>
 * </tr>
 * </table>
 * 指定中间那个表格的ID，就可以拖动修改表格的列宽。
 * 调用方法：
 * var resize=new  ResizeControl("tdId");
 * resize.init();
 * @param tdResizeId
 * @return
 */
function ResizeControl(tdResizeId)
{
	var oldOffset = null;
	var tdResize = null;
	var isMove = false;
	var tdLeft = null;
	var tdRight = null;
	var lwidth;
	var rwidth;
	this.tdResizeId = tdResizeId;
	var self = this;
	this.init = function()
	{
		$("body").bind("mousemove", function(event)
		{
			if(tdResize == null || oldOffset == null)
				return;
			if(isMove == false)
				return;
			var left = oldOffset.left;
			var offsetX = event.clientX - left;
			tdLeft.width(lwidth + offsetX);
			tdRight.width(rwidth - offsetX);
		});
		$("#" + self.tdResizeId).bind("mousemove", function(event)
		{
			tdResize = $(this);
			tdResize.css(
			{
				'cursor':'w-resize'
			});
		});
		$("#" + self.tdResizeId).bind("mousedown", function(event)
		{
			tdResize = $(this);
			if(tdResize.prevAll().length < 1 | tdResize.nextAll().length < 1)
			{
				return;
			}
			oldOffset = tdResize.offset();
			if(event.clientX - oldOffset.left < 4 || (tdResize.width() - (event.clientX - oldOffset.left)) < 4)
			{
				isMove = true;
				tdResize.css(
				{
					'cursor':'w-resize'
				});
				tdLeft = tdResize.prev();
				tdRight = tdResize.next();
				lwidth = tdLeft.width();
				rwidth = tdRight.width();
			}
		});
		$("body").bind("mouseup", function(event)
		{
			if(tdResize != null)
			{
				tdResize.css(
				{
					'cursor':'default'
				});
			}
			tdResize = null;
			oldOffset = null;
			isMove = false;
		});
	};
}
