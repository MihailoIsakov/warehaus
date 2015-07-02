angular.module('resource.jedinicaMere', ['ngResource'])
	.factory('JedinicaMere', function ($resource) {
	return $resource('http://localhost:8080/xws/api/jedinica-mere/:idJedinicaMere', { idJedinicaMere: '@idJedinicaMere' }, {
        'update': { method:'PUT' },
		'delete': { method:'DELETE' },
		'create': { method:'POST' },
		'findById': { method:'GET' }
    });
})