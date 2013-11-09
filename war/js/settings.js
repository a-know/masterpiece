$(function(){
	$('#saveSettingsButton').click(function(){
		//初期化
		$('#settings-input-icon-message').empty();
		$('#settings-input-introduction-message').empty();
		$('#settings-input-url-message').empty();
		$('#settingLoadingZone').html("<img src='/images/progressbar_green.gif'>");

		//オブジェクトの取得
	    url_input = $('#inputUrl');
	    introduction_input = $('#inputIntroduction');
	    introduction_input_hidden = $('#inputIntroductionHidden');
	    iconfile_input = $('#iconFileInput');
	    iconfile_hidden = $('#iconFileExists');

	    //get value
		var url = url_input.val();
		var introduction = introduction_input.val();
		var filename = iconfile_input.val();

		//validating
		var f = new Validate();

		if(!(filename == "")){
			if(!filename.match(/\.(jpg|JPG|gif|GIF|png|PNG)$/i)){
				$('#settings-input-icon-message').html("JPG・GIF・PNGファイルのみ設定可能です。");
				$('#settings-input-icon-message').addClass('help-inline');
				$('#settingLoadingZone').empty();
				return;
			}
			iconfile_hidden.val("exists");
		}
		if(!(introduction == "")){
			if(introduction.length > 200){
				$('#settings-input-introduction-message').html("自己紹介は200文字以内で入力して下さい。");
				$('#settings-input-introduction-message').addClass('help-inline');
				$('#settingLoadingZone').empty();
				return;
			}
		}
		if(!(url == "")){
			if(!f.isURL(url)){
				$('#settings-input-url-message').html("URLを正しく入力して下さい。");
				$('#settings-input-url-message').addClass('help-inline');
				$('#settingLoadingZone').empty();
				return;
			}
		}

		//base64 encoding
		introduction_input_hidden.val(base64.encode( introduction, 1 ));

		$.get("/getBlobUrlForSetting", function(data){
			$('#settingsForm').attr('action', data.url);
			setTimeout($('#settingsForm').submit(),2000);
		}, 'json');
	});
});