angular.module('resource.promDoc', ['ngResource'])
	.factory('Documents', function ($resource) {
	return $resource('http://localhost:8080/xws/api/prometni-dokumenti',null, {
        'create': { method:'POST' },
        'update': { method:'PUT' }
    });
})

