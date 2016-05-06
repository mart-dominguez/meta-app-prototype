/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 angular.module('relInfra')
.component('cuadroA', {
    templateUrl:'components/cuadro-a.html',
    bindings: {
            cuadro: '@'
    },
    controller: ['genericRest',function (genericRest) {
        console.log(genericRest);
        this.$onInit = function() {
          console.log('encabezado component initialized'+this.titulo);
          this.cuadro={
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
        this.guardar = function(){
            console.log(this.cuadro);
            genericRest.add(this.cuadro,"planilla/2");
        };
    }],
    controllerAs: '$ctrlCuadroA'
  });  