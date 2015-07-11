angular.module('resource.poslGodinaDoc', ['ngResource'])
	.factory('PoslGodinaDoc', function ($resource) {
	return $resource('http://localhost:8080/xws/api/poslovna-godina-doc/:invoiceItemId',null, {
        'create': { method:'POST' },
        get : {method:'GET', isArray:true},
        'update': { method:'GET', isArray:true}
      
    });
})

