$(function(){
	$('#resignButton').click(function(){
	    $('#resignModal').modal({
	        keyboard: true
	    });
	});
});

$(function(){
	$('#resignButtonOnModal').click(function(){
		//initialize
		$('#resignModalLoadingZone').empty();

	    if(loginID_this_page != loginID_in_session){
	    	window.alert("退会できません。再度お試し下さい。");
	    	location.href="/user/" + loginID_in_session;
	    }

		$('#resignModalLoadingZone').html("<img src='/images/progressbar_green.gif'>");

	    //ajax通信用のコントロールデータ作成
	    var controll_data = {};
	    controll_data.loginID  = loginID_this_page;
	    controll_data.loginIDinSession = loginID_in_session;

	    //start ajax
		$.ajax({
	        type : 'POST',
	        url : '/resign',
	        data : controll_data,
	        cache : false,
	        dataType : 'json',

	        success : function(json) {
	        	if(json.errorCode == "1"){
	            	window.alert("退会できません。再度お試し下さい。");
	            	$('#resignModalLoadingZone').empty();
	            	location.href="/user/" + loginID_in_session;
	        	}else{
	        		window.alert("退会処理が完了しました。");
	        		$('#resignModalLoadingZone').empty();
	        		location.href="/";
	        	}
	        },
	        complete : function() {
	          //通信終了
	        }
	    });
	});
});