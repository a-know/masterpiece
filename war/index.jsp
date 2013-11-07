<%@page pageEncoding="UTF-8" isELIgnored="false" session="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" charset="utf-8">
    <title>Masterpiece(β) - あなたの愛用品、教えてください。</title>
    <meta name="description" content="Masterpiece(β) - 「ものが大事に扱われている」ということは、例えそれが他人のものであっても、とても心地の良いものです。そんな、あなたの大事なものを「Masterpiece（逸品、愛用品）」として、登録・紹介できるサイトです。">
    <meta name="author" content="a-know(@a_know)">
    <meta property="fb:admins" content="100001786055969" />

    <!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- Le styles -->
    <link href="../css/bootstrap.css" rel="stylesheet">
    <link href="../css/bootstrap-responsive.css" rel="stylesheet">
    <link href="../css/global.css" rel="stylesheet">

    <!-- Le fav and touch icons -->
    <link rel="shortcut icon" href="../images/favicon.gif" type="image/gif" />
  </head>

  <body>

  <div id="fb-root"></div>
  <script>
  (function(d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) return;
    js = d.createElement(s); js.id = id;
    js.src = "//connect.facebook.net/ja_JP/all.js#xfbml=1";
    fjs.parentNode.insertBefore(js, fjs);
  }(document, 'script', 'facebook-jssdk'));
  </script>

  <script>
    !function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0];if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src="//platform.twitter.com/widgets.js";fjs.parentNode.insertBefore(js,fjs);}}(document,"script","twitter-wjs");
  </script>

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
              <li><a href="/about">Masterpieceについて</a></li>
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
                  <a class="btn btn-large btn-danger"  href="/user/<c:out value="${user.loginID}" />/message" style="margin:0 0 5px 0;">未読メッセージ：<strong><c:out value="${unread}" /></strong> 件</a>
                  <a class="btn btn-large btn-success"  href="/user/<c:out value="${user.loginID}" />/post">Masterpieceを登録する</a>
                </div>
                </c:if>
                <c:if test="${unread == 0}" >
                  <div align="center" style="clear:both;">
                    <a class="btn btn-large btn-info"  href="/user/<c:out value="${user.loginID}" />" style="margin:0 0 5px 0;">マイページを表示</a>
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
                <span class="" id="regist-input-loginID-message" style="color:red;"></span>
              </div>
              <label class="control-label" for="input01">パスワード</label>
              <div class="controls">
                <input type="password" class="input-xlarge" id="password-regist" name="password-regist">
                <span class="" id="regist-input-password-message" style="color:red;"></span>
              </div>

              <div id="registModalLoadingZone" align="center"></div>
            </div>
            <div class="modal-footer">
              <a href="#" class="btn btn-danger" id="registButtonOnModal">ユーザー登録</a>
              <a href="#" class="btn" data-dismiss="modal" >キャンセル</a>
            </div>
          </div>
          <c:if test="${!sessionScope.logon}">
          <div align="center">
            <a class="btn btn-large btn-success" id="registButton">いますぐユーザー登録</a>
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
          <div id="myCarousel" class="carousel slide">
            <div class="carousel-inner">
              <c:if test="${fn:length(slideList) == 0}" var="noItem" />
              <c:if test="${noItem}" >
              <div class="item active">
                <img src="../images/masterpiece_sample01.jpg" alt="">
                <div class="carousel-caption">
                  <h4>これはデモです</h4>
                  <p>これはデモです。</p>
                </div>
              </div>
              </c:if>
              <c:if test="${!noItem}" >
              <c:forEach var="item" items="${slideList}" varStatus="status">
                <c:if test="${status.index == 0}" var="firstItem" />
                <c:if test="${firstItem}" >
                  <div class="item active">
                </c:if>
                <c:if test="${!firstItem}" >
                  <div class="item">
                </c:if>

                  <img src="<c:out value="${item['imageUrl']}" />" alt="">
                  <div class="carousel-caption">
                    <h4><a href="../user/<c:out value="${item['loginID']}" />/item/<c:out value="${item['itemKey']}" />" style="text-decoration: underline; color: white;" target="_blank"><c:out value="${item['name']}" />／<c:out value="${item['categoryName']}" /></a> <span style="color:#ffcc00;"><strong><c:out value="${item['starString']}" />（★×<c:out value="${item['starCount']}" />）</strong></span></h4>
                    <p><c:out value="${item['comment']}" />（by <a href="../user/<c:out value="${item['loginID']}" />" style="text-decoration: underline; color: white;" target="_blank">id:<c:out value="${item['loginID']}" /></a>）</p>

                  </div>
                </div>

              </c:forEach>
              </c:if>
            </div>

            <a class="left carousel-control" href="#myCarousel" data-slide="prev">&lsaquo;</a>
            <a class="right carousel-control" href="#myCarousel" data-slide="next">&rsaquo;</a>
          </div>
          <div class='alert alert-danger' style="font: 12px 'Meiryo','メイリオ','Hiragino Kaku Gothic Pro','ヒラギノ角ゴ Pro W3','MS P Gothic','ＭＳ Ｐゴシック',Osaka,Verdana,Arial, Helvetica, sans-serif;"><a class='close' data-dismiss='alert' href='#'>&times;</a><strong>【2012/03/16 11:24】Twitter連携処理に失敗する事象を確認しています。</strong>再試行することで正常に処理することができる場合があります、お手数ですが、お試し下さい。</div>

          <div class="row-fluid">
            <c:if test="${fn:length(latestList) == 0}" var="noLatest" />
            <c:if test="${noLatest}" >
            <div class="span4">
              <div class="thumbnail">
                <img src="http://placehold.it/260x180" alt="">
                <div class="caption">
                  <h5>－</h5>
                  <p>これはデモです。</p>
                  <p style="padding:15px 15px 0 15px;">
                    <input type="hidden" id="addStarTargetKey0">
                    <a class="btn btn-success" id="addStarButton0">★＋1</a><span class="star-box" style="padding:3px 6px 3px 6px; margin:17px 0px 15px 8px;" id="starString0">（★×<c:out value='${item.starCount}' />）</span>
                  </p>
                </div>
              </div>
            </div><!--/span-->
            </c:if>
            <c:if test="${!noLatest}" >
              <c:forEach var="item" items="${latestList}" varStatus="status">
              <div class="span4">
                <div class="thumbnail">
                  <img src="<c:out value="${item['imageUrl']}" />" style="height:180px; width:260px;">
                  <div class="caption">
                    <h5><a href="../user/<c:out value="${item['loginID']}" />/item/<c:out value="${item['itemKey']}" />" target="_blank"><c:out value="${item['name']}" />／<c:out value="${item['categoryName']}" /></a></h5>
                    <c:out value="${item['comment']}" /><span style="font-size:80%;">（<fmt:formatDate value="${item['postDate']}" pattern="yy/MM/dd HH:mm" /> posted）</span>

                    <div align="right" style="margin:0 0 10px 0;">by <a href="../user/<c:out value="${item['loginID']}" />" target="_blank">id:<c:out value="${item['loginID']}" /></a></div>
                    <input type="hidden" id="addStarTargetKey<c:out value="${status.index}" />" value="${item['itemKey']}">
                    <div align="right">
                      <a class="btn btn-success" id="addStarButton<c:out value="${status.index}"/>" style="margin:0 0 0 20px;">★＋1</a><span class="star-box" style="padding:3px 5px 3px 5px; margin:17px 0px 15px 6px;" id="starString<c:out value="${status.index}" />">★×<c:out value="${item['starCount']}" /></span>
                    </div>

                  </div>
                </div>
              </div>
              <c:if test="${status.index == 2}" >
                </div><!--/row-->
                <hr>
                <div class="row-fluid">
              </c:if>
              <c:if test="${status.index == 5}" >
                </div><!--/row-->
              </c:if>
              </c:forEach>
            </c:if>


        </div><!--/span-->
      </div><!--/row-->


      <hr>

      <footer>
        <p>&copy; a-know 2012 (twitter:<a href="https://twitter.com/#!/a_know" target="_blank">@a_know</a>)</p>
        <a href="http://b.hatena.ne.jp/entry/http://master-piece.appspot.com/" class="hatena-bookmark-button" data-hatena-bookmark-title="Masterpiece(β) - あなたの愛用品、教えてください。" data-hatena-bookmark-layout="standard" title="このエントリーをはてなブックマークに追加"><img src="http://b.st-hatena.com/images/entry-button/button-only.gif" alt="このエントリーをはてなブックマークに追加" width="20" height="20" style="border: none;" /></a>
        <a href="https://twitter.com/share" class="twitter-share-button" data-url="http://master-piece.appspot.com" data-text="Masterpiece(β) - あなたの愛用品、教えてください。" data-lang="ja">ツイート</a><br>
        <div class="fb-like" data-href="http://master-piece.appspot.com/" data-send="false" data-layout="button_count" data-width="350" data-show-faces="false"></div>
        <p>Icons from <a href="http://glyphicons.com">Glyphicons Free</a>, licensed under <a href="http://creativecommons.org/licenses/by/3.0/">CC BY 3.0</a>.</p>
        <script type="text/javascript"><!--
          hatena_counter_name = "a-know";
          hatena_counter_id = "13";
          hatena_counter_ref = document.referrer+"";
          hatena_counter_screen = screen.width + "x" + screen.height+","+screen.colorDepth;
//--></script>
<script type="text/javascript" src="http://counter.hatena.ne.jp/js/counter.js"></script>
<noscript><img src="http://counter.hatena.ne.jp/a-know/13" border="0" alt="counter"></noscript>

      </footer>

    </div><!--/.fluid-container-->

    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script type="text/javascript" src="http://b.st-hatena.com/js/bookmark_button_wo_al.js" charset="utf-8" async="async"></script>
    <script type="text/javascript" src="http://platform.twitter.com/widgets.js"></script>
    <script type="text/JavaScript" src="../js/jQuery.min.js"></script>
    <script type="text/JavaScript" src="../js/bootstrap.min.js"></script>
    <script type="text/JavaScript" src="../js/index.js"></script>
    <script type="text/javascript" src="../js/sha256.js"></script>
    <script type="text/javascript" src="../js/login.js"></script>
    <script type="text/javascript" src="../js/addStarForTop.js"></script>


  </body>
</html>

