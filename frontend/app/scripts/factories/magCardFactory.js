angular.module('resource.magacinska', ['ngResource'])
	.factory('Kartica', function ($resource) {
	return $resource('http://localhost:8080/xws/api/magacinska-karticaK/:idMagacinskaKartica', { idMagacinskaKartica: '@idMagacinskaKartica' }, {
        'update': { method:'PUT' }
    });
})

