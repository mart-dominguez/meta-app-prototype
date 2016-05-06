/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 angular.module('relInfra')
.component('login', {
    templateUrl:'components/login.html',
    bindings: {
            usuario: '@',
            $router: '<' 
    },
      controller: ['$window','loginService','md5',function ($window,loginService,md5) {
      var $ctrl = this;

      this.$routerOnActivate = function(next) {
          console.log('encabezado component initialized'+this.titulo);
          this.usuario={
              userName:undefined,
              password:undefined
          }
        };
        this.guardar = function(){
            console.log(this);
            var txtMd5 =md5.createHash(this.usuario.password || ''); 
            console.log(txtMd5);
            loginService.autenticar(this.usuario.userName,txtMd5).then(
                function(){
                  console.log("LISTO");
                  $window.localStorage.setItem('token',loginService.digesto());
                  //this.$router.navigate(['CuadroA', {pass: txtMd5}]);
                  $ctrl.$router.navigate(['CuadroA']);
              }
            );
            
        };
    }],
    controllerAs: '$loginCtrl'
  });  