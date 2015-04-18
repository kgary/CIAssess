var w = 500,
    h = 500;

var colorscale = d3.scale.category10();

//Legend titles
var LegendOptions = ['Smartphone', 'Tablet'];

//Data
var d = [
    [{
        axis: "Email",
        value: 0.59
    }, {
        axis: "Social Networks",
        value: 0.56
    }, {
        axis: "Internet Banking",
        value: 0.42
    }, {
        axis: "News Sportsites",
        value: 0.34
    }, {
        axis: "Search Engine",
        value: 0.48
    }, {
        axis: "View Shopping sites",
        value: 0.14
    }, {
        axis: "Paying Online",
        value: 0.11
    }, {
        axis: "Buy Online",
        value: 0.05
    }, {
        axis: "Stream Music",
        value: 0.07
    }, {
        axis: "Online Gaming",
        value: 0.12
    }, {
        axis: "Navigation",
        value: 0.27
    }, {
        axis: "App connected to TV program",
        value: 0.03
    }, {
        axis: "Offline Gaming",
        value: 0.12
    }, {
        axis: "Photo Video",
        value: 0.4
    }, {
        axis: "Reading",
        value: 0.03
    }, {
        axis: "Listen Music",
        value: 0.22
    }, {
        axis: "Watch TV",
        value: 0.03
    }, {
        axis: "TV Movies Streaming",
        value: 0.03
    }, {
        axis: "Listen Radio",
        value: 0.07
    }, {
        axis: "Sending Money",
        value: 0.18
    }, {
        axis: "Other",
        value: 0.07
    }, {
        axis: "Use less Once week",
        value: 0.08
    }],
    [{
        axis: "Email",
        value: 0.48
    }, {
        axis: "Social Networks",
        value: 0.41
    }, {
        axis: "Internet Banking",
        value: 0.27
    }, {
        axis: "News Sportsites",
        value: 0.28
    }, {
        axis: "Search Engine",
        value: 0.46
    }, {
        axis: "View Shopping sites",
        value: 0.29
    }, {
        axis: "Paying Online",
        value: 0.11
    }, {
        axis: "Buy Online",
        value: 0.14
    }, {
        axis: "Stream Music",
        value: 0.05
    }, {
        axis: "Online Gaming",
        value: 0.19
    }, {
        axis: "Navigation",
        value: 0.14
    }, {
        axis: "App connected to TV program",
        value: 0.06
    }, {
        axis: "Offline Gaming",
        value: 0.24
    }, {
        axis: "Photo Video",
        value: 0.17
    }, {
        axis: "Reading",
        value: 0.15
    }, {
        axis: "Listen Music",
        value: 0.12
    }, {
        axis: "Watch TV",
        value: 0.1
    }, {
        axis: "TV Movies Streaming",
        value: 0.14
    }, {
        axis: "Listen Radio",
        value: 0.06
    }, {
        axis: "Sending Money",
        value: 0.16
    }, {
        axis: "Other",
        value: 0.07
    }, {
        axis: "Use less Once week",
        value: 0.17
    }]
];
