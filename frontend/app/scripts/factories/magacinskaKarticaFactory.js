angular.module('resource.magacinskaKartica', ['ngResource'])
	.factory('MagacinskaKartica', function ($resource) {
	return $resource('http://localhost:8080/xws/api/magacinska-kartica/:idMagacinskaKartica', { idMagacinskaKartica: '@idMagacinskaKartica' }, {
        'update': { method:'PUT' },
		'delete': { method:'DELETE' },
		'create': { method:'POST' },
		'findById': { method:'GET' }
    });
})