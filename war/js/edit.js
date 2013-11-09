$(function(){

	$('#editButton').click(function(){
		//初期化
		$('#edit-input-name-message').empty();
		$('#edit-input-comment-message').empty();
		$('#edit-input-url-message').empty();
		$('#editModalLoadingZone').empty();

		$('#categorynameOnModal').empty();
		$('#nameOnModal').empty();
		$('#commentOnModal').empty();
		$('#urlOnModal').empty();;


		name_input_hidden = document.getElementById('editItemNameHidden');
		comment_input_hidden = document.getElementById('editItemCommentHidden');

		var category = $('#editItemcategory option:selected').val();
		var categoryname = $('#editItemcategory option:selected').text();
		var name = $('#editItemName').val();
		var comment = $('#editItemComment').val();
		var url = $('#editItemUrl').val();

		var f = new Validate();

		if(name == ""){
			$('#edit-input-name-message').html("アイテム名称を入力して下さい。");
			$('#edit-input-name-message').addClass('help-inline');
			return;
		}
		if(name.length > 30){
			$('#edit-input-name-message').html("アイテム名称は30文字以内で入力して下さい。");
			$('#edit-input-name-message').addClass('help-inline');
			return;
		}
		if(comment == ""){
			$('#edit-input-comment-message').html("コメントを入力して下さい。");
			$('#edit-input-comment-message').addClass('help-inline');
			return;
		}
		if(!(url == "")){
			if(!f.isURL(url)){
				$('#edit-input-url-message').html("URLを正しく入力して下さい。");
				$('#edit-input-url-message').addClass('help-inline');
				return;
			}
		}

		//set
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

		$('#editModal').modal({
			keyboard: true
		});

		$('#editItemNameHidden').val(base64.encode( name, 1 ));
		$('#editItemCommentHidden').val(base64.encode( comment, 1 ));
	});

	$('#editButtononModal').click(function(){
		$('#editModalLoadingZone').html("<img src='/images/progressbar_green.gif'>");
	});

});