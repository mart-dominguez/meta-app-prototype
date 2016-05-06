angular.module('relInfra')
        .factory('genericRest', ['$http','$q','urlBase',function ($http,$q,urlBase) {
            var _element ={};
            var _list = [];
            var _add = function(unObjeto,api){
                console.log(unObjeto);
                var deffered = $q.defer();
		$http.post(urlBase+api+"/",unObjeto).success(function(data, status, headers, config) {
                    _element= data;
                    deffered.resolve();
		}).error(function(data, status, headers, config) {
                    console.log(data);
                });
		return deffered.promise;
            }
            var _upd = function(unObjeto,api){
                console.log(unObjeto);
                var deffered = $q.defer();
		$http.put(urlBase+api+"/",unObjeto).success(function(data, status, headers, config) {
                    _element= data;
                    deffered.resolve();
		}).error(function(data, status, headers, config) {
                    console.log(data);
                });
		return deffered.promise;
            }
            var _del = function(unObjeto,api){
                console.log(unObjeto);
                var deffered = $q.defer();
		$http.delete(urlBase+api+"/"+unObjeto.id).success(function(data, status, headers, config) {
                    //_element= data;
                    deffered.resolve();
		}).error(function(data, status, headers, config) {
                    console.log(data);
                });
		return deffered.promise;
            }
            var _query = function(api){
                var deffered = $q.defer();
			$http.get(urlBase+api+"/").
			success(function(data, status, headers, config) {
				_list= data;
				deffered.resolve();
			}).
			error(function(data, status, headers, config) {
				console.log(data);
			});
			return deffered.promise;
            }
            return{
                add: _add,
                update: _upd,
                remove: _del,
                search : _query,
                getList : function(){
                    return _list;
                },
                getElement : function(){
                    return _element;
                },
            }
        }])
            .factory('loginService', ['$http','$q','urlBase',function ($http,$q,urlBase) {
                var _element ={};
                var _auth = function(user,pass){
                    var deffered = $q.defer();
                    $http.post(urlBase+"auth",{userName:user,claveMd5:pass}).
			         success(function(data, status, headers, config) {
				        console.log(data) ;
                                _element = data;
				    deffered.resolve();
                        }).
                        error(function(data, status, headers, config) {
                            console.log(data);
                        });
                    return deffered.promise;
                }
                var _validar = function(){
                    console.log(_element);
                    var deffered = $q.defer();
                    $http.post(urlBase+"auth/validar",{token:_element}).
                        success(function(data, status, headers, config) {
				            console.log(data) ;
				            deffered.resolve();
                        }).
                        error(function(data, status, headers, config) {
                            console.log(data);
                            console.log(status);
                            console.log(headers);
                        });
                    return deffered.promise;
                }
            return{
                autenticar: _auth,
                digesto:function(){
                    return _element;
                },
                validar: _validar
            }        
}]);
