angular.module('resource.pGodina2', ['ngResource'])
	.factory('pGodina2', function ($resource) {
	return $resource('http://localhost:8080/xws/api/poslovna-godina-new/:invoiceItemId', null, {
        get : {method:'GET'}
    });
})