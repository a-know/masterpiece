<%@page pageEncoding="UTF-8" isELIgnored="false" session="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" charset="utf-8">
    <title>Matserpieceについて - Masterpiece(β)</title>
    <meta name="description" content="Masterpiece(β) - 「ものが大事に扱われている」ということは、例えそれが他人のものであっても、とても心地の良いものです。そんな、あなたの大事なものを「Masterpiece（逸品、愛用品）」として、登録・紹介できるサイトです。">
    <meta name="author" content="a-know(@a_know)">

    <!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

      <script type="text/javascript">
        var loginID_this_page = "${loginID_this_page}";
        var loginID_in_session;
        if('true' == "${sessionScope.logon}"){
        	loginID_in_session = "${sessionScope.loginID}";
        }else{
        	loginID_in_session = "";
        }
      </script>

    <!-- Le styles -->
    <link href="../../../css/bootstrap.css" rel="stylesheet">
    <link href="../../../css/style.css" rel="stylesheet">
    <link href="../../../css/demo.css" rel="stylesheet">
    <style type="text/css">
    </style>
    <link href="../../../css/bootstrap-responsive.css" rel="stylesheet">
    <link href="../../../css/global.css" rel="stylesheet">

    <!-- Le fav and touch icons -->
    <link rel="shortcut icon" href="../../../images/favicon.gif" type="image/gif" />
  </head>

  <body>

    <div class="navbar navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container-fluid">
          <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </a>
          <a class="brand" href="/">Masterpiece(β)</a>
          <div class="nav-collapse">
            <ul class="nav">
              <li class="active"><a href="/about">Masterpieceについて</a></li>
              <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">カテゴリ検索 <b class="caret"></b></a>
                <ul class="dropdown-menu">
                  <li><a href="/search/category/01">コンピュータ(<c:out value="${countByCategoryMap['01']}" />)</a></li>
                  <li><a href="/search/category/02">デジタルガジェット(<c:out value="${countByCategoryMap['02']}" />)</a></li>
                  <li><a href="/search/category/03">携帯電話(<c:out value="${countByCategoryMap['03']}" />)</a></li>
                  <li><a href="/search/category/04">電化製品(<c:out value="${countByCategoryMap['04']}" />)</a></li>
                  <li><a href="/search/category/05">オーディオ機器(<c:out value="${countByCategoryMap['05']}" />)</a></li>
                  <li><a href="/search/category/06">カメラ(<c:out value="${countByCategoryMap['06']}" />)</a></li>
                  <li><a href="/search/category/07">生活雑貨(<c:out value="${countByCategoryMap['07']}" />)</a></li>
                  <li><a href="/search/category/08">文房具(<c:out value="${countByCategoryMap['08']}" />)</a></li>
                  <li><a href="/search/category/09">雑貨(<c:out value="${countByCategoryMap['09']}" />)</a></li>
                  <li><a href="/search/category/10">CD/DVD類(<c:out value="${countByCategoryMap['10']}" />)</a></li>
                  <li><a href="/search/category/11">書籍(<c:out value="${countByCategoryMap['11']}" />)</a></li>
                  <li><a href="/search/category/12">おもちゃ/ホビー(<c:out value="${countByCategoryMap['12']}" />)</a></li>
                  <li><a href="/search/category/13">模型/プラモデル(<c:out value="${countByCategoryMap['13']}" />)</a></li>
                  <li><a href="/search/category/14">楽器(<c:out value="${countByCategoryMap['14']}" />)</a></li>
                  <li><a href="/search/category/15">アート(<c:out value="${countByCategoryMap['15']}" />)</a></li>
                  <li><a href="/search/category/16">アンティーク/コレクション(<c:out value="${countByCategoryMap['16']}" />)</a></li>
                  <li><a href="/search/category/17">ファッション(<c:out value="${countByCategoryMap['17']}" />)</a></li>
                  <li><a href="/search/category/18">アクセサリー(<c:out value="${countByCategoryMap['18']}" />)</a></li>
                  <li><a href="/search/category/19">服飾雑貨/小物(<c:out value="${countByCategoryMap['19']}" />)</a></li>
                  <li><a href="/search/category/20">家具/インテリア(<c:out value="${countByCategoryMap['20']}" />)</a></li>
                  <li><a href="/search/category/21">時計(<c:out value="${countByCategoryMap['21']}" />)</a></li>
                  <li><a href="/search/category/22">スポーツ用品(<c:out value="${countByCategoryMap['22']}" />)</a></li>
                  <li><a href="/search/category/23">自動車/バイク(<c:out value="${countByCategoryMap['23']}" />)</a></li>
                  <li><a href="/search/category/24">食品/飲料(<c:out value="${countByCategoryMap['24']}" />)</a></li>
                  <li><a href="/search/category/25">ペット/生き物(<c:out value="${countByCategoryMap['25']}" />)</a></li>
                  <li><a href="/search/category/26">植物(<c:out value="${countByCategoryMap['26']}" />)</a></li>
                  <li><a href="/search/category/99">その他(<c:out value="${countByCategoryMap['99']}" />)</a></li>
                </ul>
              </li>
            </ul>
          <form class="navbar-search pull-left" action="/searchUser">
            <input type="text" class="search-query span2" placeholder="ユーザーIDで検索" id="loginID" name="loginID"  style="margin:0 0 10px 0;" required>
            <button class="btn btn-primary" type="submit" style="margin:0 0 10px 0;">
              検索
            </button>
          </form>

            <c:if test="${sessionScope.logon}">
            <form class="navbar-search pull-right" name="logoutForm" action="/logout" method="post">
              <button class="btn pull-right" type="submit" style="padding: 5px 10px 5px 10px; margin: 0 0 2px 10px">ログアウト</button>
              <p class="navbar-text pull-right">ようこそ、<a href="/user/<c:out value="${sessionScope.loginID}" />"><c:out value="${sessionScope.loginID}" /></a>さん</p>
            </form>
            </c:if>
            <c:if test="${!sessionScope.logon}">
            <form class="navbar-search pull-right" id="loginForm" name="loginForm" action="/login" method="post" >
              <input type="text" class="input-small" placeholder="login ID" id="loginID-loginForm" name="loginID-loginForm" value="<c:out value="${sessionScope.loginID}" />">
              <input type="password" class="input-small" placeholder="Password" id="password-loginForm" name="password-loginForm">
              <button class="btn btn-large btn-primary" type="submit" style="padding: 4px 10px 2px 10px; margin:0 0 10px 0;">ログイン</button>
            </form>
            </c:if>
          </div><!--/.nav-collapse -->
        </div>
      </div>

    </div>
    <c:if test="${sessionScope.loginError == '1'}" var="loginError" />
    <c:if test="${loginError}">
    <div class="container-fluid pull-right"><div class="span4 offset4"><div class="alert alert-error"><a class="close" data-dismiss="alert" href="#">&times;</a>ログインＩＤまたはパスワードが違います。</div></div></div>
    </c:if>
    <div id="topMessageArea"></div>
    <div class="container-fluid">
      <div class="row-fluid">

      <c:if test="${user.loginID == ''}" var="noUser" />
      <c:if test="${noUser}" >
      <h3>そのようなユーザーは存在しません。</h3>
      </c:if>
      <c:if test="${!noUser}" >

        <div class="span3">

          <c:if test="${sessionScope.logon}">
          <div class="section">
            <div class="heading">
              <h3><i class="icon-flag icon-white" ></i> Welcome, <c:out value="${user.loginID}" /></h3>
            </div>
            <div class="content">
                <div class="icon"><img src="<c:out value="${user.imageUrl}" />"></div>
                <a href="/user/<c:out value="${user.loginID}" />">id:<c:out value="${user.loginID}" /></a>さん<br>
                <c:out value="${user.postCount}" /> Masterpieces<br>
                <span style='color:#ffcc00;'>★</span>×<c:out value="${user.starCount}" /><br>
                <c:if test="${unread != 0}" >
                <div align="center" style="clear:both;">
                  <a class="btn btn-large btn-danger"  href="/user/<c:out value="${user.loginID}" />/message" style="margin:0 0 5px 0;">未読メッセージ：<strong><c:out value="${unread}" /></strong> 件</a><br>
                  <a class="btn btn-large btn-success"  href="/user/<c:out value="${user.loginID}" />/post">Masterpieceを登録する</a>
                </div>
                </c:if>
                <c:if test="${unread == 0}" >
                  <div align="center" style="clear:both;">
                    <a class="btn btn-large btn-info"  href="/user/<c:out value="${user.loginID}" />" style="margin:0 0 5px 0;">マイページを表示</a><br>
                    <a class="btn btn-large btn-success"  href="/user/<c:out value="${user.loginID}" />/post">Masterpieceを登録する</a>
                  </div>
                </c:if>
            </div>
          </div>
          </c:if>


          <c:if test="${!sessionScope.logon}">
          <div class="section">
            <div class="heading">
              <h3><i class="icon-ok icon-white" ></i> About this site "Masterpiece"</h3>
            </div>
            <div class="content">
            「ものが大事に扱われている」ということは、<br>
            例えそれが他人のものであっても、<br>
            とても心地の良いものです。<br>
            あなたの大事な「Masterpiece（逸品、愛用品）」。<br>
            教えてください。<br>
            </div>
          </div>
          </c:if>

          <div id="registModal" class="modal hide fade">
            <div class="modal-header">
              <a class="close" data-dismiss="modal" >&times;</a>
              <h3>ユーザー登録 - ようこそMasterpieceへ！</h3>
            </div>
            <div class="modal-body">
              <p>Masterpieceへようこそ！</p>
              <p>ユーザー登録を行います。ログインＩＤとパスワードを入力して下さい。</p>
              <br>
              <label class="control-label" for="input01">ログインＩＤ（半角英数字、"-"（ハイフン）、"_"（アンダースコア）のみ使用可能）</label>
              <div class="controls">
                <input type="text" class="input-xlarge" id="loginID-regist" name="loginID-regist">
                <span class="help-inline" id="regist-input-loginID-message" style="color:red;"></span>
              </div>
              <label class="control-label" for="input01">パスワード</label>
              <div class="controls">
                <input type="password" class="input-xlarge" id="password-regist" name="password-regist">
                <span class="help-inline" id="regist-input-password-message" style="color:red;"></span>
              </div>

              <div id="registModalLoadingZone" align="center"></div>
            </div>
            <div class="modal-footer">
              <a href="#" class="btn btn-danger" onclick="registModalButtonClick();">ユーザー登録</a>
              <a href="#" class="btn" data-dismiss="modal" >キャンセル</a>
            </div>
          </div>
          <c:if test="${!sessionScope.logon}">
          <div align="center">
            <a class="btn btn-large btn-success" onclick="registButtonClick();">いますぐユーザー登録</a>
          </div>
          </c:if>

          <div class="section">
            <div class="heading">
              <h3><i class="icon-th-list icon-white" ></i> Recent Activity (ALL)</h3>
            </div>
            <div class="content">

            <c:if test="${fn:length(activityList) == 0}" var="noActivity" />
            <c:if test="${noActivity}" >
            まだActivityがありません。
            </c:if>
            <c:if test="${!noActivity}" >
            <c:forEach var="activity" items="${activityList}" varStatus="status">
              <c:if test="${activity.activityCode == '0'}">
                <div class="icon"><img src="<c:out value="${activityUserList[status.index].imageUrl}" />"></div>
                <span class="label label-success">NEW</span> <fmt:formatDate value="${activity.activityDate}" pattern="yyyy/MM/dd HH:mm" /><br>
                <a href="/user/<c:out value="${activity.loginID}" />" target="_blank">id:<c:out value="${activity.loginID}" /></a>さんが<br>
                ユーザー登録しました！<br>
                <hr>
              </c:if>
              <c:if test="${activity.activityCode == '1'}">
                <div class="icon"><img src="<c:out value="${activityUserList[status.index].imageUrl}" />"></div>
                <span class="label label-warning">UPDATE</span> <fmt:formatDate value="${activity.activityDate}" pattern="yyyy/MM/dd HH:mm" /><br>
                <a href="/user/<c:out value="${activity.loginID}" />" target="_blank">id:<c:out value="${activity.loginID}" /></a>さんが、
                Masterpiece「<a href="/user/<c:out value="${activity.loginID}" />/item/<c:out value="${activity.activityInfo['itemKey']}" />" target="_blank"><c:out value="${activity.activityInfo['name']}"/>／<c:out value="${activity.activityInfo['category']}"/></a>」を追加登録しました！<br>
                <hr>
              </c:if>
              <c:if test="${activity.activityCode == '2'}">
                <div class="icon"><img src="<c:out value="${activityUserList[status.index].imageUrl}" />"></div>
                <span class="label label-info">INFO</span> <fmt:formatDate value="${activity.activityDate}" pattern="yyyy/MM/dd HH:mm" /><br>
                <a href="/user/<c:out value="${activity.loginID}" />" target="_blank">id:<c:out value="${activity.loginID}" /></a>さんの
                Masterpiece「<a href="/user/<c:out value="${activity.loginID}" />/item/<c:out value="${activity.activityInfo['itemKey']}" />" target="_blank"><c:out value="${activity.activityInfo['name']}"/>／<c:out value="${activity.activityInfo['category']}"/></a>」が、<span style='color:red;'><c:out value="${activity.activityInfo['starCount']}"/></span>個目の<span style='color:#ffcc00;'>★</span>を獲得しました！<br>
                <hr>
              </c:if>
              <c:if test="${activity.activityCode == '3'}">
                <div class="icon"><img src="<c:out value="${activityUserList[status.index].imageUrl}" />"></div>
                <span class="label label-info">INFO</span> <fmt:formatDate value="${activity.activityDate}" pattern="yyyy/MM/dd HH:mm" /><br>
                <a href="/user/<c:out value="${activity.loginID}" />" target="_blank">id:<c:out value="${activity.loginID}" /></a>さんが、<br>
                累計<span style='color:red;'><c:out value="${activity.activityInfo['userStarCount']}"/></span>個目の<span style='color:#ffcc00;'>★</span>を獲得しました！<br>
                <hr>
              </c:if>
            </c:forEach>
            <div align="center"><span style="color:gray; font-size:80%;">全体のアクティビティ最新10件を取得</span></div>
            </c:if>

            </div>
          </div>

        </div><!--/span-->

        <div class="span9">

          <div class="row-fluid">

            <div class="section" style="clear:both;">
              <div class="heading" id="posthead">
              <h3><i class="icon-question-sign icon-white" ></i> Masterpieceについて</h3>
              </div>
              <div class="content">
                「ものが大事に扱われている」ということは、例えそれが他人のものであっても、とても心地の良いものです。<br>
                そんな、あなたの大事なものを「Masterpiece（逸品、愛用品）」として、登録・紹介できるサイトです。<br>
                <br>
                <div align="center"><img src="/images/about01.jpg" class="class_box_shadow"></div>
                <br>
                「Masterpiece」は、ユーザーのみなさんに「愛用品」を登録し紹介して頂くことを通じて、<br>
                「ものを大事にすること」の素晴らしさ、大切さを今一度、実感して頂ければという思いを込めて<br>
                つくったWebサービスです。<br>
                <br>
                このサービスを使って頂くことで、<br>
                <br>
                <ul>
                  <li>毎日の生活のなかで、実はお世話になっていた「愛用品」を再発見する</li>
                  <li>これからの「もの」との出会いに、「次の“愛用品”となれるように・・・」という気持ちを持ってもらう</li>
                </ul>
                ということにつながれば、これ以上の喜びはありません。<br>
              </div>
            </div>

            <div class="section" style="clear:both;">
              <div class="heading" id="posthead">
              <h3><i class="icon-question-sign icon-white" ></i> 「Masterpiece」の使い方</h3>
              </div>
              <div class="content">
                <h4>１．ユーザー登録をしよう</h4>
                <br>
                <div><img src="/images/about02.png" class="class_box_shadow"></div>
                <br>
                ユーザー登録は、簡単です。<br>
                サイドバーの「いますぐユーザー登録」のボタンから、すぐに行うことができます。<br>
                <br>
                <h4>２．愛用品を登録してみよう</h4>
                <br>
                <div>
                  <img src="/images/about03.png" class="class_box_shadow">
                  <img src="/images/about04.png" class="class_box_shadow">
                  <img src="/images/about05.png" class="class_box_shadow">
                </div>
                <br>
                愛用品の、とびきりの写真を一枚、撮影したら、Masterpieceに投稿してみましょう。<br>
                「マイページ」の「新規投稿」メニューから、簡単に行うことができます。<br>
                （フィーチャーフォン、スマートフォンからの投稿には対応しておりません。申し訳ありません。）<br>
                もしあなたがtwitterを使っていたら、あらかじめ設定画面でtwitter連携設定をしておくことで、<br>
                投稿と同時にtwitterにもつぶやくことができます。<br>
                <br>
                <div>
                  <img src="/images/about06.png" class="class_box_shadow">
                  <img src="/images/about07.png" class="class_box_shadow">
                </div>
                <br>
                <h4>３．他のユーザーの愛用品を見てみよう</h4>
                <br>
                <div>
                  <img src="/images/about08.png" class="class_box_shadow">
                  <img src="/images/about09.png" class="class_box_shadow">
                </div>
                <br>
                気になるカテゴリには他にどんな愛用品があるか、確認してみましょう。<br>
                もし「いいなあ」という愛用品があれば、その気持ちを「<span style='color:#ffcc00;'>★</span>」ボタンを押すことで<br>
                表現してみましょう。その愛用品に、「<span style='color:#ffcc00;'>★</span>」をつけることができます。<br>
                もしあなたがfacebookに登録していれば、「いいね！」ボタンでも<br>
                その気持ちを表現することができます。<br>
                <br>
              </div>
            </div>

            <div class="section" style="clear:both;">
              <div class="heading" id="posthead">
              <h3><i class="icon-question-sign icon-white" ></i> 「Masterpiece」ができたわけ</h3>
              </div>
              <div class="content">
                きっかけは、このサービスの製作者・<a href="https://twitter.com/#!/a_know" target="_blank">a-know</a>の敬愛する糸井重里さんが書かれた、<br>
                a-knowの大好きな本「<strong>ほぼ日刊イトイ新聞の本</strong>」の、「子どもの偽ウォークマン」という小題の一節でした。<br>
                引用するにはちょっと長いのですが、本当に素晴らしい文章なので、敬意を込めて、ここで抜粋・引用させていただきます。<br>
                <br>
                <br>
                <blockquote>
                話はいきなり1990年代のはじめにさかのぼる。<br>
                ぼくはいつものようにパチンコをした。<br>
                そのころのぼくのパチンコというのは、絶対に換金しない遊びだった。いくら玉が出てもお金には換えない。いまのように、何連チャンとかしなかったから、よっぽど出たときでもせいぜい五千発くらいだった。<br>
                一個四円の玉を五千発だと、約二万円くらいの景品と交換できる。<br>
                ぬいぐるみももらった。傘も獲った。スナック菓子も電池もいっぱい獲った。ライターも、電気カミソリも、ＣＤもビデオテープも、タバコも、イヤってくらいもらったら、だんだん欲しい景品がなくなってくる。<br>
                パンツも、お茶漬け海苔も、天体望遠鏡ももらってあって、もうほんとに景品交換所に欲しいものがなくなったときに、ぼくは、Ｓ社製でない（といって、Ｎ社でもＡ社でもない）たぶん香港メイドの「ウォークマン」（と呼んではいけないんだ、ほんとは）を、二千五百発程度の数のパチンコ玉と交換した。<br>
                ぼくは、ほんもののウォークマンをすでにいくつも買っていたし、使ってはいないけれど、どこかにしまってあるはずだったから、そんな偽物はいらないわけだ。<br>
                当時小学生だった娘におもちゃがわりにやればいいかと、雑に考えていたのだと思う。<br>
                娘にそれを渡したことさえ忘れて、半年か一年くらい経った。<br>
                ぼくは、娘が小さいころから、よく親子で旅行に出ていた。そんなある旅行中、寝る前に彼女が言った。<br>
                「こんど、パパ、イヤホンのあたらしいの買ってくれる？」<br>
                イヤホンぐらい、いくつでも買ってやらぁな。そうか、壊れたのかい。見れば、当時でも珍しいくらいの不細工なイヤホンだった。<br>
                自分の小さなころの理科実験セットなんかにあったものと、変わらない。<br>
                さらに気がついたのは、そのイヤホンが付いている偽ウォークマンの、とんでもないほどのデカさと武骨さだった。<br>
                「いいよ、イヤホンな。明日、どっかの電器屋で買おう」<br>
                「ありがと。おやすみなさい」<br>
                彼女は、偽ウォークマンに、だめになりかかっているイヤホンのコードをぐるぐると巻き付けて、そいつを大事そうにベッドサイドに置いて、かけぶとんを頭からかぶった。<br>
                自分が、ゴミのようにあつかっていたパチンコの景品が、家族とはいえ別の人間の手に渡って、こんなに大切にされている。<br>
                これは、ちょっとショックだった。<br>
                なんでも買えばある。なくしても、買えばいい。<br>
                古くなったら新しいのを買う。<br>
                高いものは簡単には買えないけれど、値段の安いものなら、いくつでも買える。<br>
                知らず知らずのうちに、自分にそう考えるくせがついていたらしい。<br>
                「大衆消費社会」の構造がそうなているからだとか、ものを大切にするべきだとか、べつに理論や倫理で考えたわけではない。<br>
                「偽物の不細工なウォークマン」で、好きなテープを聴き、寝る前にいかにも古臭いイヤホンをぐるぐる巻き付けてそいつをしまう、その姿のほうが、かっこよく思えたのだった。<br>
                うらやましい気持ちになったのだ。<br>
                その、うらやましがられた本人さえも忘れているだろう「小さすぎる事件」が、どこに行ったときだったのかすら憶えていないが、<br>
                「こいつのほうが、かっこいい」<br>
                と思ったことは、いつまでも忘れないようにしようと、そのときのぼくは決めていた。<br>
                だから、ずっと憶えているのだ。<br>
                人が、他の人やものを大事にしているのを見るのは、気持ちがいい。<br>
                人やものを、粗末にあつかうのを見るのは、見苦しい。<br>
                年寄りの説教のようだが、それは、倫理というよりも、精神的な快感と不快感に置き換えられるもののようだ。<br>
                <br>
                <small>糸井重里・著「ほぼ日刊イトイ新聞の本」（講談社文庫）P.19-22　より抜粋</small>
                </blockquote>
                <br>
                <br>
                これと似たような経験がa-knowにもあったこともあり、この一節はすごく心に響きました。<br>
                <br>
                そして同時に、「大事にされているもの」を集められる場があれば・・・、それをみんなで「みせあいっこ」できれば・・・。<br>
                言葉や数字でうまく言い表せないけれど、きっと、何らかの「いい」影響を、この世の中に加えることができるんじゃないかなぁ、と、<br>
                そう思って、このサービスを作りました。<br>
                <br>
                <br>
                ぜひ使ってみて、「愛用品」「大事にしているもの」を、教えてください。<br>
                <br>
              </div>
            </div>



          </div>


        </div><!--/span-->
      </div><!--/row-->
      </c:if>


      <hr>

      <footer>
        <p>&copy; a-know 2012 (twitter:<a href="https://twitter.com/#!/a_know" target="_blank">@a_know</a>)</p>
        <p><img src="https://developers.google.com/appengine/images/appengine-noborder-120x30.gif" alt="Powered by Google App Engine" />Icons from <a href="http://glyphicons.com">Glyphicons Free</a>, licensed under <a href="http://creativecommons.org/licenses/by/3.0/">CC BY 3.0</a>.</p>
      </footer>

    </div><!--/.fluid-container-->







    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script type="text/javascript" src="http://platform.twitter.com/widgets.js"></script>
    <script type="text/JavaScript" src="../../../js/jQuery.min.js"></script>
    <script type="text/JavaScript" src="../../../js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../../../js/sha256.js"></script>
    <script type="text/javascript" src="../../../js/login.js"></script>
    <script type="text/javascript" src="../../../js/index.js"></script>

  </body>
</html>

