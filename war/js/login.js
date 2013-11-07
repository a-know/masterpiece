$(function (){

	$('#loginForm').submit(function(e){
		//初期化
		$('#topMessageArea').empty();

		//get value
		var loginID = $('#loginID-loginForm').val();
		var password = $('#password-loginForm').val();

		if(loginID == ""){
			$('#topMessageArea').html('<div class="container-fluid pull-right"><div class="span4 offset4"><div class="alert alert-error"><a class="close" data-dismiss="alert" href="#">&times;</a>ログインＩＤを入力して下さい。</div></div></div>');
			e.preventDefault();
			return false;
		}

		if(password == ""){
			$('#topMessageArea').html('<div class="container-fluid pull-right"><div class="span4 offset4"><div class="alert alert-error"><a class="close" data-dismiss="alert" href="#">&times;</a>パスワードを入力して下さい。</div></div></div>');
			e.preventDefault();
			return false;
		}

		if( loginID.match( /[^A-Za-z0-9_-]+/ ) ) {
			$('#topMessageArea').html('<div class="container-fluid pull-right"><div class="span4 offset4"><div class="alert alert-error"><a class="close" data-dismiss="alert" href="#">&times;</a>入力不可能な文字が入力されています。</div></div></div>');
			e.preventDefault();
			return false;
		}

	    //ajax通信用のコントロールデータ作成
	    var controll_data = {};
	    controll_data.loginID  = loginID;
	    $('#password-loginForm').val(sha256.hex(password));

	});

});