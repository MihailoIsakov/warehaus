angular.module('resource.artikale', ['ngResource'])
	.factory('Artikale', function ($resource) {
	return $resource('http://localhost:8080/xws/api/artikal/:id',null, {
        'findById': { method:'GET' }
    });
})