
var key = "forTrackingCookieKey";
var trackingID = $.cookie(key);

if(!trackingID){
    var randomNumber = Math.random();
    $.cookie(key, randomNumber, { expires: 7 });
    trackingID = randomNumber;
}

$.ajax({
    type: "POST",
    url: "http://localhost:9999/tracking",
    data: "trackingID=" + trackingID,
    success: function(msg){
        alert( "Data Saved: " + msg );
    }
});
