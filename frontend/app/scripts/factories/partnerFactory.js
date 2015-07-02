angular.module('resource.partner', ['ngResource'])
	.factory('Partner', function ($resource) {
	return $resource('http://localhost:8080/xws/api/poslovni-partner/:partnerId',null, {
        'update': { method:'PUT'},
		'create': {method:'POST'}
    });
})