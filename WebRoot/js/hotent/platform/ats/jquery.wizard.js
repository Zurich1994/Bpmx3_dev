(function($ , undefined) {
	$.fn.ht_wizard = function(options) {

		this.each(function() {
			var $this = $(this);
			$this.wizard();

			var buttons = $this.siblings('.wizard-actions').eq(0);
			var $wizard = $this.data('wizard');
			$wizard.$prevBtn.remove();
			$wizard.$nextBtn.remove();
			
			$wizard.$prevBtn = buttons.find('.btn-prev').eq(0).on('click',  function(){
				$wizard.previous();
			}).attr('disabled', 'disabled');
			
			$wizard.$nextBtn = buttons.find('.btn-next').eq(0).on('click',  function(){
				$wizard.next();
			}).removeAttr('disabled');
			
			$wizard.nextText = $wizard.$nextBtn.text();
			
			var step = options && ((options.selectedItem && options.selectedItem.step) || options.step);
			if(step) {
				$wizard.currentStep = step;
				$wizard.setState();
			}
		});

		return this;
	}

})(window.jQuery);