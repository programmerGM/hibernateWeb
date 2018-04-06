(() => {
    angular.module('webProject').constant('consts', {
        appName: 'Web Project',
        version: '1.0',
        owner: 'Maurício Generoso',
        apiUrl: 'http://localhost:8080/'
    }).run([
        '$rootScope',
        'consts',
        function($rootScope, consts) {
            $rootScope.consts = consts
        }
    ])
})()