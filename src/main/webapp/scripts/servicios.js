angular.module('relInfra').factory('reqService',
 ['$http', '$q',function ($http, $q) {
    var url = 'http://localhost:8080/ms-requerimientos-1.0-SNAPSHOT/app/planilla';
    var lista = [];
    var _guardar = function (tipoPlanilla,planilla) {
      deffered = $q.defer();
      $http.post(url+"/"+planilla).success(
        function (data, status, headers, config) {
          console.log(data);
          deffered.resolve(data);
      }).error(function (data, status, headers, config) {      
        console.log(data);
      });
      return deffered.promise;
    };
    return{
        guardar : function(){
            this._guardar().then()
          return "OKA";
        }
    }
    //
});