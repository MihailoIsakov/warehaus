angular.module('resource.magCardpga', ['ngResource'])
	.factory('Pga', function ($resource) {
	return $resource('http://localhost:8080/xws/api/magacinska-karticaPGA/:idArtikal', { idArtikal: '@idArtikal', idMagacin:'@idMagacin', idPG:'@idPG' }, {
		'findGG': { method:'GET' }
    });
})