(() => {
    angular.module('webProject').config([
        '$stateProvider',
        '$urlRouterProvider',
        '$httpProvider',
        ($stateProvider, $urlRouterProvider, $httpProvider) => {
            $stateProvider.state('init', {
                url: '/init',
                templateUrl: '../init.html'
            })
            $urlRouterProvider.otherwise('/init')
        }
    ])
})()