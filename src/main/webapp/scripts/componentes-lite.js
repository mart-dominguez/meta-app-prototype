angular.module('relInfra')
  .component('contenedor', {
    transclude: true,
    templateUrl:'components/contenedor.html'
  })
  .component('encabezado', {
        bindings: {
            titulo: '@'
    	},
    	controller: function () {
        this.$onInit = function() {
          console.log('encabezado component initialized'+this.titulo);
        };
    	},
      controllerAs: '$ctrl',      
    	templateUrl:'components/encabezado.html'
  })
  .component('menu', {
    transclude: true,
    templateUrl:'components/menuizquierdo.html',
    controller: function () {
        this.$onInit = function() {
          console.log('menu component initialized');
        };
    },
    controllerAs: '$menuCtrl'
  }).component('item', {
    templateUrl:'components/itemmenu.html'
  }).component('contenido', {
    transclude: true,
    templateUrl:'components/contenido.html'
  })