/*$(function() {
 var viewModel = {};
 viewModel.songs = ko.observableArray();
 ko.applyBindings(viewModel);
 var url = 'http://localhost:8080/voornaaminliedje/api/songs/some';
 var parameters = '?offset=99&max=10';
 
 
 $.getJSON(url + parameters).then(function(songs) {
 $.each(songs, function() {
 viewModel.songs.push({
 artist: ko.observable(this.artist),
 title: ko.observable(this.title)
 });
 });
 });
 });*/

var url = 'http://localhost:8080/voornaaminliedje/api/songs/all';

var initialData2;

$(function() {
    initialData2 = $.getJSON(url);
});

// var initialData2 = [
// {name: "Baa", sales: 10, price: 10.01}];

var initialData = [
    {name: "Well-Travelled Kitten", sales: 352, price: 75.95},
    {name: "Speedy Coyote", sales: 89, price: 190.00},
    {name: "Furious Lizard", sales: 152, price: 25.00},
    {name: "Indifferent Monkey", sales: 1, price: 99.95},
    {name: "Brooding Dragon", sales: 0, price: 6350},
    {name: "Ingenious Tadpole", sales: 39450, price: 0.35},
    {name: "Optimistic Snail", sales: 420, price: 1.50}
];

var PagedGridModel = function(items) {
    this.items = ko.observableArray(items);

    this.addItem = function() {
        this.items.push({name: "New item", sales: 0, price: 100});
    };

    this.sortByName = function() {
        this.items.sort(function(a, b) {
            return a.name < b.name ? -1 : 1;
        });
    };

    this.jumpToFirstPage = function() {
        this.gridViewModel.currentPageIndex(0);
    };

    this.gridViewModel = new ko.simpleGrid.viewModel({
        data: this.items,
        columns: [
            {headerText: "Item Name", rowText: "name"},
            {headerText: "Sales Count", rowText: "sales"},
            {headerText: "Price", rowText: function(item) {
                    return "$" + item.price.toFixed(2)
                }}
        ],
        pageSize: 4
    });
};

ko.applyBindings(new PagedGridModel(initialData2));