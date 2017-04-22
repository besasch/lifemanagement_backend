<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
  <title>aucobo</title>
  <meta charset="utf-8"></meta>
  <meta http-equiv="X-UA-Compatible" content="IE=edge"></meta>
  <meta http-equiv="Content-Type" content="text/html; charset='UTF-8'"></meta>  
  
  
  <script src="lib/jquery/jquery-2.1.4.min.js"></script>
  <script src="lib/bootstrap/js/bootstrap.min.js"></script>
  <script src="lib/angular/angular.min.js"></script>
  <script src="lib/angular/angular-route.min.js"></script>
  <script src="lib/angular/angular-cookies.js"></script>
  <script src="lib/angular-dropdownMultiselect/angular-dropdownMultiselect.js"></script>
  <script src="lib/bluebird/bluebird.js"></script>
  
  <link href="lib/angular-dropdownMultiselect/angular-dropdownMultiselect.css" rel="stylesheet" media="screen"></link> 
  <link href="lib/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen"></link>
  <link href="css/style.css" rel="stylesheet"></link>
  <link rel="apple-touch-icon" sizes="57x57" href="img/favicon/apple-icon-57x57.png">
  <link rel="apple-touch-icon" sizes="60x60" href="img/favicon/apple-icon-60x60.png">
  <link rel="apple-touch-icon" sizes="72x72" href="img/favicon/apple-icon-72x72.png">
  <link rel="apple-touch-icon" sizes="76x76" href="img/favicon/apple-icon-76x76.png">
  <link rel="apple-touch-icon" sizes="114x114" href="img/favicon/apple-icon-114x114.png">
  <link rel="apple-touch-icon" sizes="120x120" href="img/favicon/apple-icon-120x120.png">
  <link rel="apple-touch-icon" sizes="144x144" href="img/favicon/apple-icon-144x144.png">
  <link rel="apple-touch-icon" sizes="152x152" href="img/favicon/apple-icon-152x152.png">
  <link rel="apple-touch-icon" sizes="180x180" href="img/favicon/apple-icon-180x180.png">
  <link rel="icon" type="image/png" sizes="192x192"  href="img/favicon/android-icon-192x192.png">
  <link rel="icon" type="image/png" sizes="32x32" href="img/favicon/favicon-32x32.png">
  <link rel="icon" type="image/png" sizes="96x96" href="img/favicon/favicon-96x96.png">
  <link rel="icon" type="image/png" sizes="16x16" href="img/favicon/favicon-16x16.png">
  <link rel="manifest" href="img/favicon/manifest.json">
  <meta name="msapplication-TileColor" content="#ffffff">
  <meta name="msapplication-TileImage" content="img/favicon/ms-icon-144x144.png">
  <meta name="theme-color" content="#ffffff">
</head>

<body ng-app="aucobo">

<nav class="navbar navbar-aucobo navbar-fixed-top">
      <div class="container">
        
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a href="#/landingpage" class="pull-left navbar-brand"><img  style="max-width:60px; margin-top: -7px;" src="img/logo.png" /></a>

        </div>

       
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
          <ul class="nav navbar-nav">
            <li class="dropdown">
              <a href="" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Regeln <span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="/#/newrule"><span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span> Neue Regel</a></li>
                <li><a href="/#/managerules"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> Regeln verwalten</a></li>
                <li role="separator" class="divider"></li>  
                <li><a href=""><span class="glyphicon glyphicon-search" aria-hidden="true"></span> Stöbern</a></li>
                <li><a href=""><span class="glyphicon glyphicon-star" aria-hidden="true"></span> Beliebt</a></li>
              </ul>
              
            </li>
            <li class="dropdown" style="display:none">
              <a href="" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Visualisierungen <span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="/#/newvisualisation"><span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span> Neue Visualisierung</a></li>
                <li><a href="/#/managevisualisations"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> Visualisierungen verwalten</a></li>
                <li role="separator" class="divider"></li>  
                <li><a href="/#/newvisualisation"><span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span> Neue App</a></li>
                <li><a href="/#/managevisualisations"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> App verwalten</a></li>
              </ul>
            </li>
			<li class="dropdown" style="display:none">
              <a href="" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Daten <span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href=""><span class="glyphicon glyphicon-search" aria-hidden="true"></span> Durchsuchen</a></li>
              </ul>
            </li>
            <li class="dropdown" >
              <a href="" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Schnittstellen <span class="caret"></span></a>
              <ul class="dropdown-menu">
    			<li><a href="/#/manageinterfaces"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> Schnittstellen verwalten</a></li>
              </ul>
             </li>
          </ul>
          <form class="navbar-form navbar-left" role="search" style="display:none">
            <div class="form-group">
              <input type="text" style="width:300px" class="form-control" placeholder="Suche" />
            </div>
          </form>
          <ul class="nav navbar-nav navbar-right">
            <li class="dropdown" style="display:none">
              <a href="" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><span class="glyphicon glyphicon-glyphicon glyphicon-shopping-cart" aria-hidden="true"></span></a>
              <ul class="dropdown-menu">
                <li><a href="/#/browsestore">Im Store stöbern</a></li>
              </ul>
            </li>
            <li class="dropdown">
              <a href="" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><span class="glyphicon glyphicon-user" aria-hidden="true"></span></a>
              <ul class="dropdown-menu">
                <li><a href="/#/feedback"><span class="glyphicon glyphicon-comment" aria-hidden="true"></span> Feedback</a></li>
                <li role="separator" class="divider" style="display:none"></li>
                <li style="display:none"><a href="">Logout</a></li>
              </ul>
            </li>
          </ul>
        </div>
      </div>
    </nav>

     
<div class="container">
  <div ng-view="true">
  </div>

</div>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">{{feedback.title}}</h4>
      </div>
      <div class="modal-body">
        <p>{{feedback.message}}</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">OK</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

    <script type="text/javascript">
//    var gatewayHost = "${gatewayHost}";
//    var interfacesHost = "${interfacesHost}";
//    var rulesHost = "${rulesHost}";
//    var triggerHost = "${triggerHost}";
//    var devicesHost = "${devicesHost}";
    var gatewayHost = "${gatewayHost}";
    var interfacesHost = "${interfacesHost}";
    var rulesHost = "${rulesHost}";
    var triggerHost = "${triggerHost}";
    var devicesHost = "${devicesHost}";
    var feedbacksHost = "${feedbacksHost}";
    var ownersHost = "${ownersHost}";

//     console.log("gatewayHost:", "${gatewayHost}");
//     console.log("devicesHost:", ${devicesHost});
//     console.log("interfacesHost:", "${interfacesHost}");
//     console.log("rulesHost:", "${rulesHost}");
//     console.log("workqueueHost:", "${workqueueHost}");

	function hideAddressBar(){
	  if(document.documentElement.scrollHeight<window.outerHeight/window.devicePixelRatio)
	    document.documentElement.style.height=(window.outerHeight/window.devicePixelRatio)+'px';
	  setTimeout(window.scrollTo(1,1),0);
	}
	window.addEventListener("load",function(){hideAddressBar();});
	window.addEventListener("orientationchange",function(){hideAddressBar();});
    </script>

  <script src="app.js"></script>
  <script src="scripts/services/ownerFactory.js"></script>
  <script src="scripts/services/ruleFactory.js"></script>
  <script src="scripts/services/interfaceFactory.js"></script>
  <script src="scripts/services/deviceFactory.js"></script>
  <script src="scripts/services/triggerFactory.js"></script>
  <script src="scripts/services/feedbackFactory.js"></script>
  
  <script src="scripts/services/uiFactory.js"></script>
  <script src="scripts/controllers/rules.js"></script>
  <script src="scripts/controllers/landing.js"></script>
  <script src="scripts/controllers/interfaces.js"></script>
  <script src="scripts/controllers/store.js"></script>
  <script src="scripts/controllers/feedback.js"></script>
 



</body>
</html>
