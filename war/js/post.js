$(function(){
	$('#postButton').click(function(){
		//初期化
		$('#post-input-file-message').empty();
		$('#post-input-name-message').empty();
		$('#post-input-comment-message').empty();
		$('#post-input-url-message').empty();
		$('#post-tweet-content').empty();
		$('#postModalLoadingZone').empty();
		$('#finenameOnModal').empty();
		$('#categorynameOnModal').empty();
		$('#nameOnModal').empty();
		$('#commentOnModal').empty();
		$('#urlOnModal').empty();
		$('#isTweetOnModal').empty();
		$('#tweetContentOnModal').empty();


		//オブジェクトの取得
	    var file_input = $('#postFileInput');
	    var category_input = $('#postItemcategory option:selected');
	    var name_input = $('#postItemName');
	    var comment_input = $('#postItemComment');
	    var url_input = $('#postItemUrl');
	    var isTweet_input = $('#postWithTwitter');
	    var tweetContent_input = $('#tweetContent');
	    var name_input_hidden = $('#postItemNameHidden');
	    var comment_input_hidden = $('#postItemCommentHidden');
	    var tweetContent_input_hidden = $('#tweetContentHidden');

	    //get value
		var filename = file_input.val();
		var category = category_input.val();
		var categoryname = category_input.text();
		var name = name_input.val();
		var comment = comment_input.val();
		var url = url_input.val();
		var isTweet = isTweet_input.attr('checked');
		var tweetContent = tweetContent_input.val();

		//for validate
		var f = new Validate();

		if(filename == ""){
			$('#post-input-file-message').html("投稿するファイルを選択して下さい。");
			$('#post-input-file-message').addClass('help-inline');
			return;
		}
		if(!filename.match(/\.(jpg|JPG|jpeg|JPEG)$/i)){
			$('#post-input-file-message').html("JPGファイルのみ投稿可能です。");
			$('#post-input-file-message').addClass('help-inline');
			return;
		}
		if(name == ""){
			$('#post-input-name-message').html("アイテム名称を入力して下さい。");
			$('#post-input-name-message').addClass('help-inline');
			return;
		}
		if(name.length > 30){
			$('#post-input-name-message').html("アイテム名称は30文字以内で入力して下さい。");
			$('#post-input-name-message').addClass('help-inline');
			return;
		}
		if(comment == ""){
			$('#post-input-comment-message').html("コメントを入力して下さい。");
			$('#post-input-comment-message').addClass('help-inline');
			return;
		}
		if(!(url == "")){
			if(!f.isURL(url)){
				$('#post-input-url-message').html("URLを正しく入力して下さい。");
				$('#post-input-url-message').addClass('help-inline');
				return;
			}
		}
		if(isTweet==true || isTweet == 'checked'){
			if(tweetContent.length > 140){
				$('#post-tweet-content').html("ツイート内容は140文字以内で入力して下さい。");
				$('#post-tweet-content').addClass('help-inline');
				return;
			}else if(tweetContent.length == 0){
				$('#post-tweet-content').html("ツイート内容を入力して下さい。");
				$('#post-tweet-content').addClass('help-inline');
				return;
			}
		}


		//set
		$('#finenameOnModal').html(filename);
		$('#categorynameOnModal').html(categoryname);
		$('#nameOnModal').html(name);
		if(comment.length > 50){
			$('#commentOnModal').html(comment.slice(0, 50) + "...");
		}else{
			$('#commentOnModal').html(comment);
		}
		if(url == ""){
			$('#urlOnModal').html("URL指定なし");
		}else{
			$('#urlOnModal').html(url);
		}
		if(isTweet==true || isTweet == 'checked'){
			$('#isTweetOnModal').html("Twitterにも投稿する");
			$('#tweetContentOnModal').html(tweetContent);
		}else{
			$('#isTweetOnModal').html("Twitterには投稿しない");
		}
		//show modal
	    $('#postModal').modal({
	        keyboard: true
	    });

		name_input_hidden.val(base64.encode( name, 1 ));
		comment_input_hidden.val(base64.encode( comment, 1 ));
		tweetContent_input_hidden.val(base64.encode( tweetContent, 1 ));

	});
});

$(function(){
	$('#postButtonOnModal').click(function(){
		$.get("/getBlobUrl", function(data){
			$('#postForm').attr('action', data.url);
			$('#postModalLoadingZone').html("<img src='/images/progressbar_green.gif'>");
			setTimeout($('#postForm').submit(),2000);
		}, 'json');
	});
});


function tweetContentChangeByName(){

	var name = $('#postItemName').val();
	var tweetContent = $('#tweetContent').val();

	tweetContent = tweetContent.replace(/「.*／/, "「" + name + "／");

	$('#tweetContent').val(tweetContent);
}

function tweetContentChangeByCategory(){

	var categoryname = $('#postItemcategory option:selected').text();
	var tweetContent = $('#tweetContent').val();

	tweetContent = tweetContent.replace(/／.*」/, "／" + categoryname + "」");

	$('#tweetContent').val(tweetContent);
}