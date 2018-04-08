(() => {
    angular.module('webProject').config([
        '$stateProvider',
        '$urlRouterProvider',
        '$httpProvider',
        ($stateProvider, $urlRouterProvider, $httpProvider) => {
            $stateProvider.state('home', {
                url: '/',
                templateUrl: '../groups/home.html'
            }).state('form', {
                url: '/groups',
                temmplateUrl: '../groups/form.html' 
            })

            $urlRouterProvider.otherwise('/')
        }
    ])
})()