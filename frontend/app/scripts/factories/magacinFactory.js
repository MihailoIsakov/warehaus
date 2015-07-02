angular.module('resource.magacin', ['ngResource'])
	.factory('Magacin', function ($resource) {
	return $resource('http://localhost:8080/xws/api/magacin/:idMagacin', { idMagacin: '@idMagacin' }, {
        'update': { method:'PUT' },
		'delete': { method:'DELETE' },
		'create': { method:'POST' },
		'findById': { method:'GET' }
    });
})