import angular from 'angular';

angular.module('drink', [])
  .controller('drinkController', ['$scope', '$http', '$state', 'localStorageService', '$compile', function($scope, $http, $state, localStorageService, $compile) {
    getMlPatient();

    var drink = {};
    $scope.drinkTypeObj = {};
    $scope.drinkQuantityObj = {};
    $scope.drinkLifeObj = {};
    $scope.proofObj = {};
    $scope.detailIdObj = {};
    $scope.medicineLiverDrinkDetailDTOS = [];

    var count = 0;

    $scope.addDrink = function() {
      count += 1;
      var template = '<div class="col-md-12">&nbsp</div><div class="col-sm-3"><div class="input-group"><div class="input-group-addon"><i style="font-style: inherit;"> &ensp; 饮酒种类</i></div><select ng-model="drinkTypeObj[' + count + ']" class="form-control"><option value="1">白酒</option><option value="2">黄酒</option><option value="3">啤酒</option><option value="4">葡萄酒</option><option value="5">其他</option><option value="6">不详</option></select></div></div><div class="col-sm-3"><div class="input-group"><div class="input-group-addon"><i style="font-style: inherit;"> &ensp; 饮酒数量（毫升/天）</i></div><input class="form-control" ng-model="drinkQuantityObj[' + count + ']"></div></div><div class="col-sm-3"><div class="input-group"><div class="input-group-addon"><i style="font-style: inherit;"> &ensp; 饮酒年限（年）</i></div><input class="form-control" ng-model="drinkLifeObj[' + count + ']"></div></div><div class="col-sm-3"><div class="input-group"><div class="input-group-addon"><i style="font-style: inherit;"> &ensp; 酒精度数（度）</i></div><input class="form-control" ng-model="proofObj[' + count + ']"></div></div>';
      var compileFn = $compile(template);
      var dom = compileFn($scope);
      dom.appendTo('#drink');
    };

    if (sessionStorage.getItem('mlPatientId')) {
      $http({
        method: 'GET',
        url: '/api/mlDrink/' + sessionStorage.getItem('mlPatientId')
      }).then(function success(response) {
        var data = response.data;
        $scope.drinkHistory = data.drinkHistory;
        $scope.complete = data.complete;

        if (data.medicineLiverDrinkDetailDTOS.length != 0) {
          if (data.medicineLiverDrinkDetailDTOS.length == 1) {
            count = 0;
          } else {
            count = data.medicineLiverDrinkDetailDTOS.length - 1;
          }
          $scope.drinkType = data.medicineLiverDrinkDetailDTOS[0].drinkType;
          $scope.drinkQuantity = data.medicineLiverDrinkDetailDTOS[0].drinkQuantity;
          $scope.drinkLife = data.medicineLiverDrinkDetailDTOS[0].drinkLife;
          $scope.proof = data.medicineLiverDrinkDetailDTOS[0].proof;
          // $scope.detailId = data.medicineLiverDrinkDetailDTOS[0].id;
        } else {
          count = 0;
        }

        for (var i = 1; i < data.medicineLiverDrinkDetailDTOS.length; i++) {
          var template = '<div class="col-md-12">&nbsp</div><div class="col-sm-3"><div class="input-group"><div class="input-group-addon"><i style="font-style: inherit;"> &ensp; 饮酒种类</i></div><select ng-model="drinkTypeObj[' + i + ']" class="form-control"><option value="1">白酒</option><option value="2">黄酒</option><option value="3">啤酒</option><option value="4">葡萄酒</option><option value="5">其他</option><option value="6">不详</option></select></div></div><div class="col-sm-3"><div class="input-group"><div class="input-group-addon"><i style="font-style: inherit;"> &ensp; 饮酒数量（毫升/天）</i></div><input class="form-control" ng-model="drinkQuantityObj[' + i + ']"></div></div><div class="col-sm-3"><div class="input-group"><div class="input-group-addon"><i style="font-style: inherit;"> &ensp; 饮酒年限（年）</i></div><input class="form-control" ng-model="drinkLifeObj[' + i + ']"></div></div><div class="col-sm-3"><div class="input-group"><div class="input-group-addon"><i style="font-style: inherit;"> &ensp; 酒精度数（度）</i></div><input class="form-control" ng-model="proofObj[' + i + ']"></div></div>';
          var compileFn = $compile(template);
          var dom = compileFn($scope);
          dom.appendTo('#drink');
          $scope.drinkLifeObj[i] = ((data.medicineLiverDrinkDetailDTOS)[i]).drinkLife;
          $scope.proofObj[i] = ((data.medicineLiverDrinkDetailDTOS)[i]).proof;
          $scope.drinkTypeObj[i] = ((data.medicineLiverDrinkDetailDTOS)[i]).drinkType;
          $scope.drinkQuantityObj[i] = ((data.medicineLiverDrinkDetailDTOS)[i]).drinkQuantity;
          $scope.detailIdObj[i] = ((data.medicineLiverDrinkDetailDTOS[i])).id;
        }
      });
    }

    $scope.save = function() {
      drink.medicineLiverDrinkDetailDTOS = [];
      drink.drinkHistory = $scope.drinkHistory;
      drink.patientId = sessionStorage.getItem('mlPatientId');
      drink.complete = true;

      if ($scope.drinkType != undefined) {
        drink.medicineLiverDrinkDetailDTOS.push({
          drinkType: $scope.drinkType,
          drinkQuantity: $scope.drinkQuantity,
          drinkLife: $scope.drinkLife,
          proof: $scope.proof
            // id: $scope.detailId,
        });
      }
      for (var i = 1; i <= count; i++) {
        drink.medicineLiverDrinkDetailDTOS.push({
          // id: ($scope.detailIdObj[i + 1]),
          drinkType: ($scope.drinkTypeObj[i]),
          drinkQuantity: ($scope.drinkQuantityObj[i]),
          drinkLife: ($scope.drinkLifeObj[i]),
          proof: ($scope.proofObj[i])
        });
      }
      $http({
        method: 'POST',
        url: '/api/mlDrink',
        data: drink
      }).then(function success() {
        $('#myModal').modal();
      });
    };

    $scope.layout = function() {
      $state.go('mlHome');
    };

    function getMlPatient() {
      $http({
        method: 'GET',
        url: '/api/mlPatient/' + sessionStorage.getItem('mlPatientId')
      }).then(function success(response) {
        var data2 = response.data;
        $scope.patientName = data2.name;
        $scope.patientNumber = data2.identifier;
      });
    }
  }]);