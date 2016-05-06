angular.module('relInfra', ['ngComponentRouter','angular-md5'])        
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
        .factory('BearerAuthInterceptor', function ($window, $q) {
    return {
        request: function(config) {
            config.headers = config.headers || {};
            if ($window.localStorage.getItem('token')) {
              // may also use sessionStorage
                config.headers.Authorization = 'Bearer ' + $window.localStorage.getItem('token');
            }
            return config || $q.when(config);
        },
        response: function(response) {
            if (response.status === 401) {
                //  Redirect user to login page / signup Page.
            }
            return response || $q.when(response);
        }
    };
});
angular.module('relInfra')
        .value('urlBase', 'http://localhost:8080/relevamientos-infraestructura/app/')
        .value('$routerRootComponent', 'app')
        .config(function ($httpProvider) {
                $httpProvider.interceptors.push('BearerAuthInterceptor');
        })
        .config(['$locationProvider',function($locationProvider) {
            $locationProvider.html5Mode(true);
        }])
        .component('app', {
            templateUrl:'components/app.html',
            $routeConfig: [
                {path: '/login', name: 'Login', component: 'login', useAsDefault: true},
                {path: '/cuadro1', name: 'CuadroA', component: 'cuadroA'}
            ]
        });


