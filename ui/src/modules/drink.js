import angular from 'angular';

angular.module('drink', [])
  .controller('drinkController', ['$scope', '$http', '$state', 'localStorageService','$compile', function($scope, $http, $state, localStorageService,$compile) {
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
      var template = '<div class="col-sm-3"><div class="input-group"><div class="input-group-addon"><i style="font-style: inherit;"> &ensp; 饮酒种类</i></div><input class="form-control" ng-model="drinkTypeObj['+count+']"></div></div><div class="col-sm-3"><div class="input-group"><div class="input-group-addon"><i style="font-style: inherit;"> &ensp; 饮酒数量（毫升/天）</i></div><input class="form-control" ng-model="drinkQuantityObj['+count+']"></div></div><div class="col-sm-3"><div class="input-group"><div class="input-group-addon"><i style="font-style: inherit;"> &ensp; 饮酒年限（年）</i></div><input class="form-control" ng-model="drinkLifeObj['+count+']"></div></div><div class="col-sm-3"><div class="input-group"><div class="input-group-addon"><i style="font-style: inherit;"> &ensp; 酒精度数（度）</i></div><input class="form-control" ng-model="proofObj['+count+']"></div></div>';
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
        count = data.medicineLiverDrinkDetailDTOS.length - 1;
        $scope.drinkHistory = data.drinkHistory;
        $scope.complete = data.complete;

        if (data.medicineLiverDrinkDetailDTOS.length != 0) {
          $scope.drinkType = data.medicineLiverDrinkDetailDTOS[0].drinkType;
          $scope.drinkQuantity = data.medicineLiverDrinkDetailDTOS[0].drinkQuantity;
          $scope.detailId = data.medicineLiverDrinkDetailDTOS[0].id;
        }

        for (var i = 1; i < data.medicineLiverDrinkDetailDTOS.length; i++) {
          var template = '<div class="col-sm-3"><div class="input-group"><div class="input-group-addon"><i style="font-style: inherit;"> &ensp; 饮酒种类</i></div><input class="form-control" ng-model="drinkTypeObj['+i+']"></div></div><div class="col-sm-3"><div class="input-group"><div class="input-group-addon"><i style="font-style: inherit;"> &ensp; 饮酒数量（毫升/天）</i></div><input class="form-control" ng-model="drinkQuantityObj['+i+']"></div></div><div class="col-sm-3"><div class="input-group"><div class="input-group-addon"><i style="font-style: inherit;"> &ensp; 饮酒年限（年）</i></div><input class="form-control" ng-model="drinkLifeObj['+i+']"></div></div><div class="col-sm-3"><div class="input-group"><div class="input-group-addon"><i style="font-style: inherit;"> &ensp; 酒精度数（度）</i></div><input class="form-control" ng-model="proofObj['+i+']"></div></div>';
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
      drink.otherHistory = $scope.otherHistory;
      drink.pastDisease = $scope.pastDisease;
      drink.patientId = sessionStorage.getItem('mlPatientId');
      drink.complete = true;

      drink.medicineLiverDrinkDetailDTOS.push({
        diseaseName: $scope.diseaseName,
        existence: $scope.existence,
        id: $scope.detailId,
        diagnosisDate: new Date($scope.diagnosisDate),
        crueDate: new Date($scope.crueDate)
      });
      drink.projectId = 2;
      for (var i = 0; i < count; i++) {
        drink.medicineLiverDrinkDetailDTOS.push({
          id: ($scope.detailIdObj[i + 1]),
          diseaseName: ($scope.diseaseNameObj[i + 1]),
          existence: ($scope.existenceObj[i + 1]),
          diagnosisDate: new Date($scope.diagnosisDateObj[i + 1]),
          crueDate: new Date($scope.crueDateObj[i + 1])
        });
      }
      $http({
        method: 'POST',
        url: '/api/mldrink',
        data: drink
      }).then(function success() {
        $('#myModal').modal();
      });
    };
  }]);