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
    controller: ['loginService','md5',function (loginService,md5) {
      console.log(loginService);
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
                  console.log(loginService.digesto());
                  loginService.validar();
                  //this.$router.navigate(['CuadroA', {pass: txtMd5}]);
                  $ctrl.$router.navigate(['CuadroA']);                  
              }
            );
            
            //this.$router.navigate(['CuadroA', {pass: txtMd5}]);
        };
    }],
    controllerAs: '$loginCtrl'
  });  