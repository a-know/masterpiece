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
    <link href="/css/bootstrap.css" rel="stylesheet">
    <link href="/css/style.css" rel="stylesheet">
    <link href="/css/demo.css" rel="stylesheet">
    <link href="/css/bootstrap-responsive.css" rel="stylesheet">
    <link href="/css/global.css" rel="stylesheet">

    <!-- Le fav and touch icons -->
    <link rel="shortcut icon" href="/images/favicon.gif" type="image/gif" />
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
              <li><a href="/user/<c:out value="${loginID_this_page}" />"><i class="icon-tag" ></i><c:out value="${loginID_this_page}" />さんのMasterpiece</a></li>
              <li><a href="/user/<c:out value="${loginID_this_page}" />/list"><i class="icon-tags" ></i>Masterpiece リスト</a></li>
              <c:if test="${loginID_this_page == sessionScope.loginID}" var="loginUser" />
              <c:if test="${loginUser}" >
              <li class="active"><a href="/user/<c:out value="${loginID_this_page}" />/post"><i class="icon-edit icon-white" ></i>新規投稿</a></li>
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
            <h3>投稿が完了しました。</h3>
            新規アイテム「<a href="/user/<c:out value="${loginID_this_page}" />/item/<c:out value="${itemKey}" />" target="_blank"><c:out value="${itemName}" />／<c:out value="${categoryName}" /></a>」の投稿が完了しました。
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
    <script type="text/JavaScript" src="/js/jQuery.min.js"></script>
    <script type="text/JavaScript" src="/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/sha256.js"></script>
    <script type="text/javascript" src="/js/login.js"></script>

  </body>
</html>

