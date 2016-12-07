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

    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <asset:stylesheet src="application.css"/>
    <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />

    <script type="text/javascript">
        window.contextPath = "${request.contextPath}";
    </script>
</head>

<body ng-app="umaas.manager.site" ng-controller="IndexController as indexCtrl" ng-cloak="">
    <div layout="row">

      <a flex class="app-title" href="#">
        <md-button class="md-primary md-raised">UMAAS</md-button>
      </a>
        <md-menu>
          <md-button aria-label="Documentation Menu" class="md-primary md-raised" ng-click="$mdOpenMenu($event)">
            <span>Documentation</span>
            <div md-menu-origin></div>
          </md-button>
          <md-menu-content width="6">
            <md-menu-item >
              <md-button ui-sref="documentation.gettingStarted">
                Getting Started
              </md-button>
            </md-menu-item>
            <md-menu-item >
              <md-button ui-sref="documentation.tutorials">
                Tutorials
              </md-button>
            </md-menu-item>
            <md-menu-item >
              <md-button ui-sref="documentation.references">
                Reference
              </md-button>
            </md-menu-item>
          </md-menu-content>
        </md-menu>
        <md-menu ng-if="indexCtrl.user">
          <md-button aria-label="Accounts Menu" class="md-primary md-raised" ng-click="$mdOpenMenu($event)">
            <span>My Account</span>
            <div md-menu-origin></div>
          </md-button>
          <md-menu-content width="6">
            <md-menu-item >
              <md-button ui-sref="account.dashboard">
                Dashboard
              </md-button>
            </md-menu-item>
            <md-menu-item >
              <md-button ui-sref="account.domains">
                Domains
              </md-button>
            </md-menu-item>
            <md-menu-item >
              <md-button ui-sref="account.accessCodes">
                Access Codes
              </md-button>
            </md-menu-item>
          </md-menu-content>
        </md-menu>
      <div>
        <md-nav-bar  md-selected-nav-item="currentNavItem" nav-bar-aria-label="navigation links">
             <md-nav-item md-nav-href="/#!/community" name="community">Community</md-nav-item>
             <md-nav-item md-nav-sref="about" name="about">About</md-nav-item>
             <md-nav-item ng-if="!indexCtrl.user"  md-nav-sref="login" name="login">Log In</md-nav-item>
             <md-nav-item ng-if="!indexCtrl.user"
             md-nav-href="{{indexCtrl.applicationData.umaasRegistration + '?domain=' + indexCtrl.applicationData.domain}}"
             name="signup">Sign Up</md-nav-item>
             <md-nav-item ng-if="indexCtrl.user"  md-nav-href="/logout" name="logout">Log Out</md-nav-item>
        </md-nav-bar>
      </div>

    </div>
    <div class="wrapper">
      <div  layout-fill>
          <div ui-view layout-fill></div>
          <div class="push"></div>
      </div>
    </div>

    <div class="footer" md-colors="{background: 'primary'}" layout="column">
          <div layout="row" layout-padding>
            <a href="#">Home</href>
            <a href="#/about">About</href>
            <a href="#/contact-us">Contact Us</href>
            <a ng-href="{{indexCtrl.applicationData.blog}}">Blog</href>
          </div>
          <div layout="row" layout-padding>
            <span> Copyright (c) ISSLNG 2016</span>
            <a href="#">Terms of Service</href>
            <a href="#/about">Privacy Policy</href>
          </div>

    </div>

    <asset:javascript src="/umaas/manager/site/umaas.manager.site.js" />
</body>
</html>
