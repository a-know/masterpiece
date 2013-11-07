$(function(){

	$('#sendMessageButton').click(function(){
		var messageSender = $('#messageSender').val();
		var sendMessageContent = $('#sendMessageContent').val();

		$('#messageSenderError').empty();
		$('#sendMessageContentError').empty();
		$('#messageSending').empty();

		if('' == messageSender){//送信者名が未入力の場合は匿名送信であることがわかるようにする
			messageSender = '（送信者名なし）';
		}
		if(messageSender.length > 200){
			$('#messageSenderError').html("送信者名は200文字以内で入力して下さい。");
			return;
		}else if('' == sendMessageContent){
			$('#sendMessageContentError').html("メッセージの本文は必須入力です。");
			return;
		}
		$('#messageSending').html("<img src='/images/progressbar_green.gif'>");

		//start ajax
	    var controll_data = {};
	    controll_data.to      = loginID_this_page;
	    controll_data.sender  = messageSender;
	    controll_data.content = sendMessageContent;

		$.ajax({
	        type : 'POST',
	        url : '/send',
	        data : controll_data,
	        cache : false,
	        dataType : 'json',

	        success : function(json) {
	        	$('#messageSending').html("<div class='alert alert-success'><a class='close' data-dismiss='alert' href='#'>&times;</a>メッセージの送信が完了しました。</div>");
	        },
	        complete : function() {
	          //通信終了
	        }
	    });
	});
});



$(function(){

	$('#messageIconHref').popover();

	$('#messageSelect').change(function(){
		$('#messageSenderNameArea').empty();

	    //メッセージのキーを取得
	    var objectValue = $('#messageSelect').val();

	    if(objectValue == ""){
	    	return;
	    }

	    //タイトルを取得
	    var title = $('#messageSelect option:selected').text();


	    //ajax通信用のコントロール情報作成
	    var controll_data = {};
	    controll_data.key = objectValue;


	    $.ajax({
	        type : 'POST',
	        url : '/loadMessage',
	        data : controll_data,
	        cache : false,
	        dataType : 'json',

	        success : function(json) {
	        	$('#messageIconHref').attr('title', title);
	        	$('#messageIconHref').attr('data-content', json.content);
	        	$('#messageSenderNameArea').html(json.sender + "さん");
	        },
	        complete : function() {
	          //通信終了
	        }
	    });
	});

	$('#deleteMessageButton').click(function(){
	    $('#messageModal').modal({
	        keyboard: true
	    });
	});

	$('#deleteMessageButtonOnModal').click(function(){
	    //メッセージのキーを取得
	    var objectValue = $('#messageSelect').val();

	    if(objectValue == ""){
	    	window.alert("削除したいメッセージを選択して下さい。");
	    	return;
	    }

	    //ajax通信用のコントロール情報作成
	    var controll_data = {};
	    controll_data.key = objectValue;

	    $.ajax({
	        type : 'POST',
	        url : '/deleteMessage',
	        data : controll_data,
	        cache : false,
	        dataType : 'json',

	        success : function(json) {
	        	$('#messageSenderNameArea').empty();
	        	window.alert("削除が完了しました。");
	        	location.href="/user/" + json.loginID + "/message";
	        },
	        complete : function() {
	          //通信終了
	        }
	    });
	});
});