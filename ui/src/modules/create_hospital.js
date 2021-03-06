import angular from 'angular';

angular.module('createHospital', [])
  .controller('createHospitalController', ['$scope', '$http', '$state', '$rootScope', 'Upload', 'localStorageService', function($scope, $http, $state, $rootScope, Upload, localStorageService) {
    loginStatus();
    $scope.image = 'src/lib/images/hospital1.jpg';

    var hospital = {};
    $scope.submitReview = function() {
      dealGraph();
    };


    function loginStatus() {
      if (!localStorageService.get('user')) {
        window.location.href = '/login.html';
      }
    }
    /**
     *医院图片处理
     */
    function dealGraph() {
      if ((typeof $scope.image) == (typeof '')) {
        $scope.imageName = 'src/lib/images/hospital1.jpg';
      } else {
        uploadPic($scope.image);
      }
    }


    /**
     *医院图片上传
     */
    function uploadPic(picFile) {
      picFile.upload = Upload.upload({
        url: '/api/hospitals/upload',
        data: {
          file: picFile
        }
      }).then(function success(response) {
        $scope.imageName = response.data;
        hospital.hospitalName = $scope.hospital_name;
        hospital.address = $scope.hospital_address;
        hospital.addressDetail = $scope.hospital_addressDetail;
        hospital.telephone = $scope.hospital_telphone;
        hospital.specialMajor = $scope.hospital_specialMajor;
        hospital.manageRange = $scope.hospital_manageRange;
        hospital.introduction = $scope.hospital_troducution;
        hospital.image_url = '/hospital/image/' + $scope.imageName + '.jpg';

        $http({
          method: 'POST',
          url: '/api/hospitals',
          data: hospital
        }).then(function success() {
          $scope.alertMessage = '创建医院成功';
          $('#messageModal').modal('show');
        }, function failed() {
          $scope.alertMessage = '创建医院失败,稍后再试';
          $('#messageModal').modal('show');
        });
      });
    }

  }]);