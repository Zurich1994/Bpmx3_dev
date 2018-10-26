angular.module('commonListService', [])
.service('CommonListService', [function() {
    var service = {
        	yesOrNoList:yesOrNoList=[
				{
					key:'是',
					value:true
				},
				{
					key:"否",
					value:false
				}
	      	],
	      	yesOrNoList2:yesOrNoList=[
   				{
   					key:'是',
   					value:1
   				},
   				{
   					key:"否",
   					value:0
   				}
   	      	]
    }
    return service;
}]);