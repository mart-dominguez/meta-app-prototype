/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 angular.module('relInfra')
.component('cuadroA', {
    templateUrl:'components/cuadro-a.html',
    bindings: {
            cuadro: '@',
            $router: '<' 
    },
    controller: ['genericRest',function (genericRest) {
        var ctrl = this;

        ctrl.$routerOnActivate = function(next) {
          console.log("ON ACTIVATE ROUTER");
          // Get the hero identified by the route parameter
          var id = next.params.cuadro;
          genericRest.findOne("planilla",id).then(
                function(){
                    ctrl.cuadro  = genericRest.getElement();
                    ctrl.cuadro.datos =  JSON.parse(ctrl.cuadro.datos);
                }
            );
        };
        ctrl.$onInit = function() {
          console.log("ON INIT CONTROLLER ROUTER");
          ctrl.cuadro={
              id:1,
              titulo:"Servicios Basicos",
              datos:[
                  {texto:"Alumbrado Publico",
                      enZona:false,
                      enPredio:false,
                      distancia:0
                  },
                  {texto:"Electricidad de red",
                      enZona:false,
                      enPredio:false,
                      distancia:0
                  }
              ]
          }
        };
        ctrl.guardar = function(){
            console.log(ctrl.cuadro);
            genericRest.add(ctrl.cuadro,"planilla");
        };
    }],
    controllerAs: '$ctrlCuadroA'
  });  