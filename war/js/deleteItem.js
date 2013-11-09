$(function(){
	$('#deleteItemButton0').click(function(){
		$('#deleteItemModal0').modal({keyboard: true});
	});
	$('#deleteItemButton1').click(function(){
		$('#deleteItemModal1').modal({keyboard: true});
	});
	$('#deleteItemButton2').click(function(){
		$('#deleteItemModal2').modal({keyboard: true});
	});
	$('#deleteItemButton3').click(function(){
		$('#deleteItemModal3').modal({keyboard: true});
	});
	$('#deleteItemButton4').click(function(){
		$('#deleteItemModal4').modal({keyboard: true});
	});

	var deleteFunc = function(data){
	    var controll_data = {};
	    controll_data.key = $(data.key).val();

		$.ajax({
	        type : 'POST',
	        url : '/deleteItem',
	        data : controll_data,
	        cache : false,
	        dataType : 'json',

	        success : function(json) {
	        	window.alert("削除が完了しました。");
	        	location.href="/user/" + json.loginID;
	        },
	        complete : function() {
	          //通信終了
	        }
	    });
	};

	$('#deleteItemModalButton0').click(function(){deleteFunc({key:'#deleteItemTargetKey0'});});
	$('#deleteItemModalButton1').click(function(){deleteFunc({key:'#deleteItemTargetKey1'});});
	$('#deleteItemModalButton2').click(function(){deleteFunc({key:'#deleteItemTargetKey2'});});
	$('#deleteItemModalButton3').click(function(){deleteFunc({key:'#deleteItemTargetKey3'});});
	$('#deleteItemModalButton4').click(function(){deleteFunc({key:'#deleteItemTargetKey4'});});
});
