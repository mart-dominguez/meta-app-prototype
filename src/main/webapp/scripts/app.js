angular.module('relInfra', ['ngComponentRouter','angular-md5'])
        .value('urlBase', 'http://localhost:8080/relevamientos-infraestructura/app/')
        .value('$routerRootComponent', 'app')
        .config(['$locationProvider',function($locationProvider) {
            $locationProvider.html5Mode(true);
        }])
        .run(function () {
            var mdlUpgradeDom = false;
            setInterval(function() {
              if (mdlUpgradeDom) {
                componentHandler.upgradeDom();
                mdlUpgradeDom = false;
              }
            }, 200);

            var observer = new MutationObserver(function () {
              mdlUpgradeDom = true;
            });
            observer.observe(document.body, {
                childList: true,
                subtree: true
            });
        });

angular.module('relInfra')
.component('app', {
    templateUrl:'components/app.html',
    $routeConfig: [
        {path: '/login', name: 'Login', component: 'login', useAsDefault: true},
        {path: '/cuadro1', name: 'CuadroA', component: 'cuadroA'}
    ]
});


