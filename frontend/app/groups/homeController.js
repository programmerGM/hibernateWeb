(() => {
  'use stricts'

  angular.module('webProject').controller('HomeController', [
    'msgs',
    'GroupService',
    HomeController
  ])

  function HomeController(msgs, GroupService) {
    const vm = this

    vm.list = () => {
      console.log('List antes')
      GroupService.list().then((groups) => {
        vm.groups = groups
      })
      console.log('List depois')
    }

    vm.list()
  }
})()