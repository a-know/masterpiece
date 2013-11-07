<%@page pageEncoding="UTF-8" isELIgnored="false" session="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" charset="utf-8">
    <title><c:out value="${loginID_this_page}" />'s Masterpiece - Masterpiece(β) あなたの愛用品、教えてください。</title>
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
    <link href="../../../css/bootstrap-responsive.css" rel="stylesheet">
    <link href="../../../css/global.css" rel="stylesheet">

    <!-- Le fav and touch icons -->
    <link rel="shortcut icon" href="../../../images/favicon.gif" type="image/gif" />
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

      <c:if test="${user.loginID == ''}" var="noUser" />
      <c:if test="${noUser}" >
      <h3>そのようなユーザーは存在しません。</h3>
      </c:if>
      <c:if test="${!noUser}" >

        <div class="span3">

          <div class="section">
            <div class="heading">
              <h3><i class="icon-user icon-white" ></i> About <c:out value="${loginID_this_page}" /></h3>
            </div>
            <div class="content">
                <div class="icon"><img src="<c:out value="${user.imageUrl}" />"></div>
                <a href="/user/<c:out value="${loginID_this_page}" />">id:<c:out value="${loginID_this_page}" /></a>さん<br>
                <c:out value="${user.postCount}" /> Masterpieces<br>
                <span style='color:#ffcc00;'>★</span>×<c:out value="${user.starCount}" /><br>
                <c:if test="${unread != 0}" >

                <c:if test="${loginID_this_page == sessionScope.loginID}" var="loginUser" />
                <c:if test="${loginUser}" >
                <div align="center" style="clear:both;">
                  <a class="btn btn-large btn-danger"  href="/user/<c:out value="${user.loginID}" />/message">未読メッセージ：<strong><c:out value="${unread}" /></strong> 件</a>
                </div>
                </c:if>
                </c:if>
            </div>
          </div>

          <div class="well sidebar-nav">
            <ul class="nav nav-list">
              <li class="nav-header"><c:out value="${loginID_this_page}" />のMasterpiece</li>
              <li class="active"><a href="/user/<c:out value="${loginID_this_page}" />"><i class="icon-tag icon-white" ></i><c:out value="${loginID_this_page}" />さんのMasterpiece</a></li>
              <li><a href="/user/<c:out value="${loginID_this_page}" />/list"><i class="icon-tags" ></i>Masterpiece リスト</a></li>
              <c:if test="${loginID_this_page == sessionScope.loginID}" var="loginUser" />
              <c:if test="${loginUser}" >
              <li><a href="/user/<c:out value="${loginID_this_page}" />/post"><i class="icon-edit" ></i>新規投稿</a></li>
              <li class="nav-header">設定</li>
              <li><a href="/user/<c:out value="${loginID_this_page}" />/settings"><i class="icon-cog" ></i>設定</a></li>
              <li><a href="/user/<c:out value="${loginID_this_page}" />/resign"><i class="icon-off" ></i>退会</a></li>
              <li class="nav-header">コミュニケーション</li>
              <li><a href="/user/<c:out value="${loginID_this_page}" />/message"><i class="icon-envelope" ></i>メッセージを確認する</a></li>
              </c:if>
              <c:if test="${!loginUser}" >
              <li class="nav-header">コミュニケーション</li>
              <li><a href="/user/<c:out value="${loginID_this_page}" />/sendMessage"><i class="icon-envelope" ></i><c:out value="${loginID_this_page}" />さんにメッセージを送る</a></li>
              </c:if>
            </ul>
          </div><!--/.well -->

          <div class="section">
            <div class="heading">
              <h3><i class="icon-th-list icon-white" ></i> Recent Activity</h3>
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
                <span class="label label-success">NEW</span><br>
                ユーザー登録（<fmt:formatDate value="${activity.activityDate}" pattern="yyyy/MM/dd HH:mm" />）<br>
                <br>
                <hr>
              </c:if>
              <c:if test="${activity.activityCode == '1'}">
                <div class="icon"><img src="<c:out value="${activityUserList[status.index].imageUrl}" />"></div>
                <span class="label label-warning">UPDATE</span><br>
                Masterpiece登録（<fmt:formatDate value="${activity.activityDate}" pattern="yyyy/MM/dd HH:mm" />）<br>
                <a href="/user/<c:out value="${activity.loginID}" />/item/<c:out value="${activity.activityInfo['itemKey']}" />" target="_blank"><c:out value="${activity.activityInfo['name']}"/>／<c:out value="${activity.activityInfo['category']}"/></a>
                <hr>
              </c:if>
              <c:if test="${activity.activityCode == '2'}">
                <div class="icon"><img src="<c:out value="${activityUserList[status.index].imageUrl}" />"></div>
                <span class="label label-info">INFO</span> <fmt:formatDate value="${activity.activityDate}" pattern="yyyy/MM/dd HH:mm" /><br>
                「<a href="/user/<c:out value="${activity.loginID}" />/item/<c:out value="${activity.activityInfo['itemKey']}" />" target="_blank"><c:out value="${activity.activityInfo['name']}"/>／<c:out value="${activity.activityInfo['category']}"/></a>」が、<span style='color:red;'><c:out value="${activity.activityInfo['starCount']}"/></span>個目の<span style='color:#ffcc00;'>★</span>を獲得！<br>
                <hr>
              </c:if>
              <c:if test="${activity.activityCode == '3'}">
                <div class="icon"><img src="<c:out value="${activityUserList[status.index].imageUrl}" />"></div>
                <span class="label label-info">INFO</span> <fmt:formatDate value="${activity.activityDate}" pattern="yyyy/MM/dd HH:mm" /><br>
                累計<span style='color:red;'><c:out value="${activity.activityInfo['userStarCount']}"/></span>個目の<span style='color:#ffcc00;'>★</span>を獲得！<br>
                <hr>
              </c:if>
            </c:forEach>
            </c:if>

            </div>
          </div>

        </div><!--/span-->

        <div class="span9">

          <div class="row-fluid">

          <div class="pagination pagination-centered">
            <ul>
              <c:forEach begin="1" end="${pageCount}" step="1" varStatus="status">
              <c:if test="${status.index == currentPage}" var="currentPageFlg" />
              <c:if test="${currentPageFlg}" >
              <li class="active"><a href="/user/<c:out value="${loginID_this_page}" />/page/<c:out value="${status.index}" />"><c:out value="${status.index}" /></a></li>
              </c:if>
              <c:if test="${!currentPageFlg}" >
              <li><a href="/user/<c:out value="${loginID_this_page}" />/page/<c:out value="${status.index}" />"><c:out value="${status.index}" /></a></li>
              </c:if>
              </c:forEach>
            </ul>
          </div>


          <c:if test="${currentPage > pageCount}">
          <h3>指定されたページは存在しません。</h3>
          </c:if>
          <c:if test="${itemCount == 0}" var="noItem" />
          <c:if test="${noItem}" >
          <h3>まだMasterpieceが投稿されていません。</h3>
          </c:if>
          <c:if test="${!noItem}" >
          <c:forEach var="item" items="${itemList_top}" varStatus="status">
            <div class="section" style="clear:both;">
              <div class="heading" id="posthead">
                <c:if test="${item.url == ''}"  var="noUrl" />
                <c:if test="${noUrl}" >
                <h3><a href="/user/<c:out value="${loginID_this_page}"/>/item/<c:out value="${itemKeyList[status.index]}" />" style="text-decoration: underline; color: white;" target="_blank">No.<c:out value="${itemCount_top - status.index}" />／<c:out value="${item.name}" />（<c:out value="${categoryNameList[status.index]}" />）</a></h3>
                </c:if>
                <c:if test="${!noUrl}" >
                <h3><a href="/user/<c:out value="${loginID_this_page}"/>/item/<c:out value="${itemKeyList[status.index]}" />" style="text-decoration: underline; color: white;" target="_blank">No.<c:out value="${itemCount_top - status.index}" />／<c:out value="${item.name}" />（<c:out value="${categoryNameList[status.index]}" />）</a><a href="<c:out value="${item.url}" />" target="_blank">【参考リンク】</a></h3>
                </c:if>
              </div>
              <div class="content">

                <div style="padding:15px 15px 0 15px;">
                <img src="<c:out value="${imageUrlList[status.index]}" />" class="class_box_shadow" id="image_pop_<c:out value="${status.index}" />" rel="popover" title="<c:out value='${item.name}' />（<c:out value="${categoryNameList[status.index]}" />）" data-content="<c:out value='${item.comment}' /><br>（<fmt:formatDate value="${item.postDate}" pattern="yyyy/MM/dd HH:mm" /> 投稿）">
                </div>
                <p style="padding:15px 15px 0 15px;">
                  <input type="hidden" id="addStarTargetKey<c:out value="${status.index}" />" value="${itemKeyList[status.index]}">
                  <a class="btn btn-success" id="addStarButton<c:out value="${status.index}" />">★＋1</a><span class="star-box" style="padding:3px 6px 3px 6px; margin:17px 0px 15px 8px;" id="starString<c:out value="${status.index}" />"><c:out value='${item.starString}' />（★×<c:out value='${item.starCount}' />）</span>
                </p>

                <c:if test="${loginID_this_page == sessionScope.loginID}" var="loginUser" />
                <c:if test="${loginUser}" >
                <p style="text-align:right; padding:0 15px 0 15px;">
                  <a class="btn btn-info"  href="/user/<c:out value="${loginID_this_page}"/>/edit/<c:out value="${itemKeyList[status.index]}" />"><i class="icon-pencil icon-white"></i> 編集する</a>
                  <input type="hidden" id="deleteItemTargetKey<c:out value="${status.index}" />" value="${itemKeyList[status.index]}">
                  <a class="btn btn-danger"  id="deleteItemButton<c:out value="${status.index}" />"><i class="icon-trash icon-white"></i> 削除する</a>
                </p>
                  <div id="deleteItemModal<c:out value="${status.index}" />" class="modal hide fade">
                    <div class="modal-header">
                      <a class="close" data-dismiss="modal" >&times;</a>
                      <h3>Masterpieceの削除</h3>
                    </div>
                    <div class="modal-body">
                      このMasterpieceを削除します。<br>
                      <br>
                      <div class="controls" style="padding: 0 0 0 30px;">
                        <li>このMasterpieceを削除することで、あなたの登録Masterpiece数・総スター数も減算されます。</li>
                        <li>一度削除処理を行うと、元の状態に戻すことはできません。</li>
                      </div>
                      <br>
                      <br>
                      本当によろしいですか？
                    </div>
                    <div class="modal-footer">
                      <a href="#" class="btn btn-danger" id="deleteItemModalButton<c:out value="${status.index}" />">削除する</a>
                      <a href="#" class="btn" data-dismiss="modal" >キャンセル</a>
                    </div>
                  </div>
                </c:if>

                <p style="padding:0 0 0 15px;">
                <a href="http://b.hatena.ne.jp/entry/http://master-piece.appspot.com/user/<c:out value="${loginID_this_page}" />/item/<c:out value="${itemKeyList[status.index]}" />" class="hatena-bookmark-button" data-hatena-bookmark-title="<c:out value="${item.name}" />／<c:out value="${categoryNameList[status.index]}" /> - <c:out value="${loginID_this_page}" />'s Masterpiece" data-hatena-bookmark-layout="standard" title="このエントリーをはてなブックマークに追加"><img src="http://b.st-hatena.com/images/entry-button/button-only.gif" alt="このエントリーをはてなブックマークに追加" width="20" height="20" style="border: none;" /></a>
                <a href="https://twitter.com/share" class="twitter-share-button" data-url="http://master-piece.appspot.com/user/<c:out value="${loginID_this_page}" />/item/<c:out value="${itemKeyList[status.index]}" />" data-text="<c:out value="${item.name}" />／<c:out value="${categoryNameList[status.index]}" /> - <c:out value="${loginID_this_page}" />'s Masterpiece" data-lang="ja">ツイート</a>
                </p>
                <div style="padding:0 0 0 15px;" class="fb-like" data-href="http://master-piece.appspot.com/user/<c:out value="${loginID_this_page}" />/item/<c:out value="${itemKeyList[status.index]}" />" data-send="false" data-layout="button_count" data-width="350" data-show-faces="false"></div>



              </div>
            </div>
          </c:forEach>
          </c:if>



          </div>

          <div class="pagination pagination-centered">
            <ul>
              <c:forEach begin="1" end="${pageCount}" step="1" varStatus="status">
              <c:if test="${status.index == currentPage}" var="currentPageFlg" />
              <c:if test="${currentPageFlg}" >
              <li class="active"><a href="/user/<c:out value="${loginID_this_page}" />/page/<c:out value="${status.index}" />"><c:out value="${status.index}" /></a></li>
              </c:if>
              <c:if test="${!currentPageFlg}" >
              <li><a href="/user/<c:out value="${loginID_this_page}" />/page/<c:out value="${status.index}" />"><c:out value="${status.index}" /></a></li>
              </c:if>
              </c:forEach>
            </ul>
          </div>


        </div><!--/span-->
      </div><!--/row-->
      </c:if>


      <hr>

      <footer>
        <p>&copy; a-know 2012 (twitter:<a href="https://twitter.com/#!/a_know" target="_blank">@a_know</a>)</p>
        <p>Icons from <a href="http://glyphicons.com">Glyphicons Free</a>, licensed under <a href="http://creativecommons.org/licenses/by/3.0/">CC BY 3.0</a>.</p>
      </footer>

    </div><!--/.fluid-container-->







    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script type="text/javascript" src="http://b.st-hatena.com/js/bookmark_button_wo_al.js" charset="utf-8" async="async"></script>
    <script type="text/javascript" src="http://platform.twitter.com/widgets.js"></script>
    <script type="text/JavaScript" src="../../../js/jQuery.min.js"></script>
    <script type="text/JavaScript" src="../../../js/bootstrap.js"></script>
    <script type="text/javascript" src="../../../js/sha256.js"></script>
    <script type="text/javascript" src="../../../js/login.js"></script>
    <script type="text/javascript" src="../../../js/user.js"></script>
    <script type="text/javascript" src="../../../js/addStar.js"></script>
    <script type="text/javascript" src="../../../js/deleteItem.js"></script>

  </body>
</html>

