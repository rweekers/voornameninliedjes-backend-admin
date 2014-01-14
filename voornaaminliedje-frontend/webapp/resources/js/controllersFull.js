'use strict';

/* Controllers */

var songControllers = angular.module('songControllers', []);

var songOTD;

songControllers.controller('SongListCtrl', ['$scope', 'Song', 'FindSongs',
    function($scope, Song, FindSongs) {

        $scope.songs = Song.query({
            offset: 0,
            max: 15
        });
        $scope.orderProp = 'artist';

        $scope.disabled = 'enabled';

        logVisit();

        $scope.offset = 0;

        $scope.next = function($event) {
            $scope.offset = calculateNextOffset($scope.offset, 770, 15);
            $scope.songs = Song.query({
                offset: $scope.offset,
                max: 15
            });
        }

        $scope.previous = function($event) {
            $scope.offset = calculatePrevOffset($scope.offset, 770, 15);
            $scope.songs = Song.query({
                offset: $scope.offset,
                max: 15
            });
        }

        $scope.search = function($event) {
            logSearchInstruction($scope.searchInstruction);

            $scope.songs = FindSongs.query({
                firstname: $scope.searchInstruction
            });
        }
    }


]);

songControllers.controller('SongOfTheDayCtrl', ['$scope', 'SongOfTheDay',
    function($scope, SongOfTheDay) {

        $scope.songOfTheDay = SongOfTheDay.query();
        songOTD = $scope.songOfTheDay;
    }
]);

songControllers.controller('VisitCtrl', ['$scope', 'Song',
    function($scope, Song) {

        console.log("Blabla");
    }
]);

songControllers.controller('MyCtrl', ['$scope', '$http',
    function($scope, $http) {
        $scope.filterOptions = {
            filterText: "",
            useExternalFilter: true
        };
        $scope.totalServerItems = 0;
        $scope.pagingOptions = {
            pageSizes: [5, 10, 20],
            pageSize: 5,
            currentPage: 1
        };
        $scope.setPagingData = function(data, page, pageSize) {
            var pagedData = data.slice((page - 1) * pageSize, page * pageSize);
            $scope.myData = pagedData;
            $scope.totalServerItems = data.length;
            if (!$scope.$$phase) {
                $scope.$apply();
            }
        };
        $scope.getPagedDataAsync = function(pageSize, page, searchText) {
            setTimeout(function() {
                var data;
                if (searchText) {
                    var ft = searchText.toLowerCase();
                    $http.get('../../resources/js/largeLoad.json').success(function(largeLoad) {
                        data = largeLoad.filter(function(item) {
                            return JSON.stringify(item).toLowerCase().indexOf(ft) != -1;
                        });
                        $scope.setPagingData(data, page, pageSize);
                    });
                } else {
                    $http.get('../../resources/js/largeLoad.json').success(function(largeLoad) {
                        $scope.setPagingData(largeLoad, page, pageSize);
                    });
                }
            }, 100);
        };

        $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);

        $scope.$watch('pagingOptions', function(newVal, oldVal) {
            if (newVal !== oldVal && newVal.currentPage !== oldVal.currentPage) {
                $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, $scope.filterOptions.filterText);
            }
        }, true);
        $scope.$watch('filterOptions', function(newVal, oldVal) {
            if (newVal !== oldVal) {
                $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, $scope.filterOptions.filterText);
            }
        }, true);

        $scope.gridOptions = {
            data: 'myData',
            enablePaging: true,
            showFooter: true,
            totalServerItems: 'totalServerItems',
            pagingOptions: $scope.pagingOptions,
            filterOptions: $scope.filterOptions
        };
    }
]);

songControllers.controller('MyCtrl2', ['$scope', '$http',
    function($scope, $http) {
        $scope.filterOptions = {
            filterText: "",
            useExternalFilter: true
        };
        $scope.totalServerItems = 0;
        $scope.pagingOptions = {
            pageSizes: [5, 10, 20],
            pageSize: 7,
            currentPage: 1
        };
        $scope.setPagingData = function(data, page, pageSize) {
            var pagedData = data.slice((page - 1) * pageSize, page * pageSize);
            $scope.myData = pagedData;
            $scope.totalServerItems = data.length;
            if (!$scope.$$phase) {
                $scope.$apply();
            }
        };
        $scope.getPagedDataAsync = function(pageSize, page, searchText) {
            setTimeout(function() {
                var data;
                if (searchText) {
                    var ft = searchText.toLowerCase();
                    $http.get('http://127.0.0.1:8080/voornaaminliedje/api/song/all').success(function(largeLoad) {
                        data = largeLoad.filter(function(item) {
                            return JSON.stringify(item).toLowerCase().indexOf(ft) != -1;
                        });
                        $scope.setPagingData(data, page, pageSize);
                    });
                } else {
                    $http.get('http://127.0.0.1:8080/voornaaminliedje/api/song/all').success(function(largeLoad) {
                        $scope.setPagingData(largeLoad, page, pageSize);
                    });
                }
            }, 100);
        };

        $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);

        $scope.$watch('pagingOptions', function(newVal, oldVal) {
            if (newVal !== oldVal && newVal.currentPage !== oldVal.currentPage) {
                $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, $scope.filterOptions.filterText);
            }
        }, true);
        $scope.$watch('filterOptions', function(newVal, oldVal) {
            if (newVal !== oldVal) {
                $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, $scope.filterOptions.filterText);
            }
        }, true);

        $scope.gridOptions = {
            data: 'myData',
            enablePaging: true,
            showFooter: true,
            totalServerItems: 'totalServerItems',
            pagingOptions: $scope.pagingOptions,
            filterOptions: $scope.filterOptions,
            columnDefs: [{
                field: 'artist',
                displayName: 'Artiest',
                width: "30%"
            }, {
                field: 'title',
                displayName: 'Titel',
                width: "65%"
            }]
        };
    }
]);


function calculatePrevOffset(oldOffset, count, max) {
    var offset;

    if (oldOffset > max) {
        offset = oldOffset - max;
    } else {
        offset = 0;
    }

    return offset;
}

function calculateNextOffset(oldOffset, count, max) {
    var offset;

    if (oldOffset + 2 * max < count) {
        offset = oldOffset + max;
    } else {
        offset = count - max;
    }

    return offset;
}
