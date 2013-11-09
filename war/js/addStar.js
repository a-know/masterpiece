$(function(){
	var addStarFunc = function (data){
	    var controll_data = {};
	    controll_data.key = $(data.key).val();

		$.ajax({
	        type : 'POST',
	        url : '/addStar',
	        data : controll_data,
	        cache : false,
	        dataType : 'json',

	        success : function(json) {
	        	$(data.starString).text(json.starString + "（★×" + json.starCount + "）");
	        },
	        complete : function() {
	          //通信終了
	        }
	    });
	};

	$('#addStarButton0').click(function(){addStarFunc({key:'#addStarTargetKey0', starString:'#starString0'});});
	$('#addStarButton1').click(function(){addStarFunc({key:'#addStarTargetKey1', starString:'#starString1'});});
	$('#addStarButton2').click(function(){addStarFunc({key:'#addStarTargetKey2', starString:'#starString2'});});
	$('#addStarButton3').click(function(){addStarFunc({key:'#addStarTargetKey3', starString:'#starString3'});});
	$('#addStarButton4').click(function(){addStarFunc({key:'#addStarTargetKey4', starString:'#starString4'});});
});