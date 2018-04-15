describe('GroupFactory teste', () => {

  var GroupFactoryTest

  beforeEach(angular.mock.module('webProject'))

  beforeEach(inject(GroupFactory => {
    GroupFactoryTest = GroupFactory
  }))

  it('Exists GroupFactoryTest', () => {
    expect(GroupFactoryTest).toBeDefined()
  })

  it('Exists function list and results', () => {
    expect(GroupFactoryTest.list).toBeDefined()

    var result = GroupFactoryTest.list()

    console.log(GroupFactoryTest.list())
    if (!result){
      throw 'Invalid Result'
    }
  })  

  // it('Exists function list and results', () => {
  //   expect(GroupFactoryTest.findById).toBeDefined()

  //   var result = GroupFactoryTest.findById(1)

  //   console.log(GroupFactoryTest.findById())
  //   if (!result){
  //     throw 'Invalid Result'
  //   }
  // }) 

})