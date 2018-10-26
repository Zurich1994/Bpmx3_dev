var isAndroid = (/android/gi).test(navigator.appVersion);   // 是否是android手机
// 判断IOS版本号是否大于7，大于7在顶部加20

function add20ToHeader() {
    if (!isAndroid) {
        if (getStorage('IOS7Plus')) {
            try {
                if (getStorage('IOS7Plus')) {
                    if ($$('header').style.paddingTop) {
                        $$('header').style.paddingTop = (parseInt($$('header').style.paddingTop) + 20) + 'px';
                    } else {
                        $$('header').style.paddingTop = '20px';
                    }
                }
            } catch (e) { }
        } else {
            uexDevice.cbGetInfo = function (opId, dataType, data) {
                if (data) {
                    var device = JSON.parse(data);
                    var os = parseInt(device.os);
                    logs('ios os==' + os);
                    if (os >= 7) {
                        setStorage('IOS7Plus', 'yes');
                        try {
                            if ($$('header').style.paddingTop) {
                                $$('header').style.paddingTop = (parseInt($$('header').style.paddingTop) + 20) + 'px';
                            } else {
                               $$('header').style.paddingTop = '20px';
                            }
                        } catch (e) { }
                    }
                }
            };
            uexDevice.getInfo('1');
        }
    }
}

var lcstor = window.localStorage;
function setStorage(key,value){
	if(lcstor)
		lcstor[key] = value;
	else
		alert('localStorage error');
}

function getStorage(key){
	if(lcstor){
		for(i in lcstor){
			if(i==key)
				return lcstor[i];
		}
	}else
		alert('localStorage error');
}

function clearStorage(key){
	if(lcstor){
		if(key)
			lcstor.removeItem(key);
		else
			lcstor.clear();
	}
	else
		alert('localStorage error');
}


