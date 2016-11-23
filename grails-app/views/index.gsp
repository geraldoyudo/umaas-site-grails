<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>Welcome to UMAAS</title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <style type="text/css">
        [ng\:cloak], [ng-cloak], [data-ng-cloak], [x-ng-cloak], .ng-cloak, .x-ng-cloak {
            display: none !important;
        }
    </style>

    <asset:stylesheet src="application.css"/>

    <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />

    <script type="text/javascript">
        window.contextPath = "${request.contextPath}";
    </script>
</head>

<body ng-app="umaas.manager.site" ng-controller="IndexController as indexCtrl">

    <div class="navbar navbar-default navbar-static-top" role="navigation">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" ng-click="navExpanded = !navExpanded">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/#">
                    <i class="fa grails-icon">
                        <asset:image src="grails-cupsonly-logo-white.svg"/>
                    </i> UMAAS
                </a>
            </div>
            <div class="navbar-collapse collapse" aria-expanded="false" style="height: 0.8px;" uib-collapse="!navExpanded">
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown" uib-dropdown>
                        <a href="#" class="dropdown-toggle" uib-dropdown-toggle role="button" aria-haspopup="true" aria-expanded="false">Documentation <span class="caret"></span></a>
                        <ul class="dropdown-menu" uib-dropdown-menu>
                            <li><a ng-href="{{indexCtrl.applicationData.docs + 'getting_started/index.html'}}">Getting Started</a></li>
                            <li><a  ng-href="{{indexCtrl.applicationData.docs + 'reference/index.html'}}">Reference</a></li>
                            <li><a  ng-href="{{indexCtrl.applicationData.docs + 'tutorials/index.html'}}">Tutorials</a></li>
                        </ul>
                    </li>
                    <li ng-if="indexCtrl.user" class="dropdown" uib-dropdown>
                        <a href="#" class="dropdown-toggle" uib-dropdown-toggle role="button" aria-haspopup="true" aria-expanded="false">My Account <span class="caret"></span></a>
                        <ul class="dropdown-menu" uib-dropdown-menu>
                            <li><a ui-sref="account.dashboard">Dashboard</a></li>
                            <li><a ui-sref="account.domains">My Domains</a></li>
                            <li><a ui-sref="account.accessCodes">My Access Codes</a></li>
                        </ul>
                    </li>
                    <li class="button">
                        <a ui-sref="community" aria-haspopup="true" aria-expanded="false">Community</span></a>
                    </li>
                    <li class="button">
                        <a ui-sref="about" aria-haspopup="true" aria-expanded="false">About Us</span></a>
                    </li>
                    <li ng-if="!indexCtrl.user" class="button">
                        <a ui-sref="login" aria-haspopup="true" aria-expanded="false">Log In</span></a>
                    </li>
                    <li ng-if="!indexCtrl.user" class="button">
                        <a
                        ng-href="{{indexCtrl.applicationData.umaasRegistration + '?domain=' + indexCtrl.applicationData.domain}}"
                        aria-haspopup="true" aria-expanded="false">Sign Up</span></a>
                    </li>
                    <li ng-if="indexCtrl.user" class="button">
                        <a href="/logout" aria-haspopup="true" aria-expanded="false">Log Out</span></a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <div ui-view></div>

    <div class="footer" role="contentinfo"></div>

    <div id="spinner" class="spinner" style="display:none;">
        <g:message code="spinner.alt" default="Loading&hellip;"/>
    </div>

    <asset:javascript src="/umaas/manager/site/umaas.manager.site.js" />
</body>
</html>
