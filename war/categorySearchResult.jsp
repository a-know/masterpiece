<%@page pageEncoding="UTF-8" isELIgnored="false" session="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" charset="utf-8">
    <title>カテゴリ「<c:out value="${categoryName}" />」の検索結果 - Masterpiece(β)</title>
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


          <c:if test="${itemCount == 0}" var="noItem" />
          <c:if test="${noItem}" >
          <h3>カテゴリ「<c:out value="${categoryName}" />」の検索結果はありません。</h3>
          </c:if>
          <c:if test="${!noItem}" >
          <c:if test="${currentPage > pageCount}">
          <h3>指定されたページは存在しません。</h3>
          </c:if>
          <div class="section" style="clear:both;">
            <div class="heading" id="posthead">
            <h3><i class="icon-tags icon-white" ></i> カテゴリ「<c:out value="${categoryName}" />」の検索結果（<c:out value="${itemCount}" /> 件）</h3>
            </div>
            <div class="content">
              <table class="table table-striped">
                <thead>
                  <tr>
                    <th>No</th>
                    <th>名称</th>
                    <th>投稿者</th>
                    <th>★</th>
                    <th>投稿日時</th>
                    <th>参考URL</th>
                  </tr>
                </thead>
                <tbody>

                <c:forEach var="item" items="${itemList_list}" varStatus="status">
                <tr>
                  <td><c:out value="${itemCount_top - status.index}" /></td>
                  <td><a href="/user/<c:out value="${item.loginID}"/>/item/<c:out value="${itemKeyList[status.index]}" />" style="text-decoration: underline;" target="_blank"><c:out value="${item.name}" /></a></td>
                  <td>id:<a href="/user/<c:out value="${item.loginID}" />" target="_blank"><c:out value="${item.loginID}" /></a>さん</td>
                  <td><span style='color:#ffcc00;'>★</span>×<c:out value='${item.starCount}' /></td>
                  <td><fmt:formatDate value="${item.postDate}" pattern="yyyy/MM/dd HH:mm" /></td>
                  <c:if test="${item.url == ''}"  var="noUrl" />
                  <c:if test="${noUrl}" >
                  <td>－</td>
                  </c:if>
                  <c:if test="${!noUrl}" >
                  <td><a href="<c:out value="${item.url}" />" target="_blank">Click</a></td>
                  </c:if>
                </tr>
                </c:forEach>

                </tbody>
                </table>

              </div>
            </div>
          </c:if>



          </div>

          <div class="pagination pagination-centered">
            <ul>
              <c:forEach begin="1" end="${pageCount}" step="1" varStatus="status">
              <c:if test="${status.index == currentPage}" var="currentPageFlg" />
              <c:if test="${currentPageFlg}" >
              <li class="active"><a href="/search/category/<c:out value="${categoryCode}" />/<c:out value="${status.index}" />"><c:out value="${status.index}" /></a></li>
              </c:if>
              <c:if test="${!currentPageFlg}" >
              <li><a href="/search/category/<c:out value="${categoryCode}" />/<c:out value="${status.index}" />"><c:out value="${status.index}" /></a></li>
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
        <p><img src="https://developers.google.com/appengine/images/appengine-noborder-120x30.gif" alt="Powered by Google App Engine" />Icons from <a href="http://glyphicons.com">Glyphicons Free</a>, licensed under <a href="http://creativecommons.org/licenses/by/3.0/">CC BY 3.0</a>.</p>
      </footer>

    </div><!--/.fluid-container-->







    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script type="text/javascript" src="http://platform.twitter.com/widgets.js"></script>
    <script type="text/JavaScript" src="../../../js/jQuery.min.js"></script>
    <script type="text/JavaScript" src="../../../js/bootstrap.js"></script>
    <script type="text/javascript" src="../../../js/sha256.js"></script>
    <script type="text/javascript" src="../../../js/login.js"></script>
    <script type="text/javascript" src="../../../js/index.js"></script>

  </body>
</html>

