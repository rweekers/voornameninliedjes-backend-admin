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

/*
$scope.search = function($event) {
    // logSearchInstruction($scope.zoekOpdracht);
    console.log("blabla.");
}*/
