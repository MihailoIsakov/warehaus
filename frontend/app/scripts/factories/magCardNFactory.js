angular.module('resource.magacinskaN', ['ngResource'])
	.factory('KarticaN', function ($resource) {
	return $resource('http://localhost:8080/xws/api/magacinska-karticaN/:idMagacinskaKartica', { idMagacinskaKartica: '@idMagacinskaKartica' }, {
        'update': { method:'PUT' }
    });
})