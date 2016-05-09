/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

 angular.module('relInfra')
.component('resumen', {
    templateUrl:'components/resumen.html',
    bindings: {
            cuadros: '@',
            titulo: '<',
			$router: '<' 
    },
    controller: ['genericRest',function (genericRest) {    	
	    var ctrl = this;

       	ctrl.titulo = "TITULO  0";
        ctrl.$onInit = function() {
        	console.log(ctrl.titulo);
        	ctrl.titulo = "TITULO  1";
            genericRest.search("planilla").then(
                function(){
					console.log("123:"+ctrl.titulo);
                    //console.log(genericRest.getList());
                    ctrl.cuadros = genericRest.getList();
                    console.log(ctrl.cuadros);
                    ctrl.titulo = "TITULO  3";
                }
            );
			console.log("987:"+ctrl.titulo);            
            ctrl.titulo = "TITULO  2";
        };
        ctrl.elegirCuadro = function(id){
			this.$router.navigate(['CuadroA', {cuadro: id}]);
        }
    }]
  });  