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
    <div layout="row" layout-xs="column" layout-sm="column">
      <div layout="row" flex>
      <a class="app-title" href="#">
        <md-button class="md-primary md-raised">UMAAS</md-button>
      </a>
      <span flex></span>
      <md-button ng-click="indexCtrl.showNav = !indexCtrl.showNav" hide-gt-sm class="md-primary">
       <md-icon md-font-set="material-icons">menu</md-icon></md-button>
      </div>
        <md-menu style="margin-right: 16px" ng-show="indexCtrl.showNav || indexCtrl.$mdMedia('gt-sm')">
          <md-button aria-label="Documentation Menu" class="nav-menu md-primary md-raised" ng-click="$mdOpenMenu($event)">
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
        <md-menu style="margin-right: 16px" ng-if="indexCtrl.user" ng-show="indexCtrl.showNav || indexCtrl.$mdMedia('gt-sm')">
          <md-button aria-label="Accounts Menu" class="md-primary md-raised nav-menu" ng-click="$mdOpenMenu($event)">
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
      <md-button aria-label="Community" class="md-primary md-raised" ng-href="/#!/community" ng-show="indexCtrl.showNav || indexCtrl.$mdMedia('gt-sm')">
            <span>Community</span>
      </md-button>
       <md-button aria-label="About" class="md-primary md-raised" ui-sref="about" ng-show="indexCtrl.showNav || indexCtrl.$mdMedia('gt-sm')">
            <span>About</span>
      </md-button>
      <md-button aria-label="Log In" class="md-primary md-raised" ui-sref="login" ng-if="!indexCtrl.user" ng-show="indexCtrl.showNav || indexCtrl.$mdMedia('gt-sm')" >
            <span>Log In</span>
      </md-button>
      <md-button aria-label="Sign Up" class="md-primary md-raised" 
      ng-href="{{indexCtrl.applicationData.umaasRegistration + '?domain=' + indexCtrl.applicationData.domain}}"
       ng-if="!indexCtrl.user" ng-show="indexCtrl.showNav || indexCtrl.$mdMedia('gt-sm')" >
            <span>Sign Up</span>
      </md-button>
        <md-button aria-label="Log Out" class="md-primary md-raised" ng-if="indexCtrl.user"  ng-href="/logout" ng-show="indexCtrl.showNav || indexCtrl.$mdMedia('gt-sm')">
            <span>Log Out</span>
      </md-button>

    </div>
    <div class="wrapper">
      <md-progress-circular class="loading" ng-show="indexCtrl.running" md-mode="indeterminate"></md-progress-circular>
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
