$(function(){
	$('#myCarousel').carousel();
	$('#registButton').click(function(){
		//初期化
		$('#regist-input-loginID-message').empty();
		$('#regist-input-password-message').empty();
		$('#registModalLoadingZone').empty();
		//open modal
		$('#registModal').modal({keyboard: true});
	});
});

$(function(){
	$('#registButtonOnModal').click(function(){
		//初期化
		$('#regist-input-loginID-message').empty();
		$('#regist-input-password-message').empty();
		$('#registModalLoadingZone').empty();

		//validating
		if($('#loginID-regist').val() == ""){
			$('#regist-input-loginID-message').text("ログインＩＤが未入力です。");
			$('#regist-input-loginID-message').addClass('help-inline');
			return;
		}

		if($('#loginID-regist').val().match( /[^A-Za-z0-9_-]+/ ) ) {
			$('#regist-input-loginID-message').text("入力不可能な文字が入力されています。");
			$('#regist-input-loginID-message').addClass('help-inline');
			return;
		}

		if($('#password-regist').val() == ""){
			$('#regist-input-password-message').text("パスワードが未入力です。");
			$('#regist-input-password-message').addClass('help-inline');
			return;
		}

		//set loading image
		$('#registModalLoadingZone').html("<img src='/images/progressbar_green.gif'>");

	    //ajax通信用のコントロールデータ作成
	    var controll_data = {};
	    controll_data.loginID  = $('#loginID-regist').val();
	    controll_data.password = sha256.hex($('#password-regist').val());

	    //start ajax
		$.ajax({
	        type : 'POST',
	        url : '/userRegist',
	        data : controll_data,
	        cache : false,
	        dataType : 'json',

	        success : function(json) {
	        	if(json.errorCode == "1"){
	        		$('#regist-input-loginID-message').text("既に使用されているログインＩＤです。");
	        		$('#registModalLoadingZone').empty();
	        	}else{
	        		$('#regist-input-loginID-message').empty();
	        		$('#regist-input-password-message').empty();
	        		$('#registModalLoadingZone').empty();
	        		window.alert("ユーザー登録が完了しました。");
	        		location.href="/";
	        	}
	        },
	        complete : function() {
	          //通信終了
	        }
	    });
	});
});