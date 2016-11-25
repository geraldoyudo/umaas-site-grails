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

<body ng-app="umaas.manager.site" ng-controller="IndexController as indexCtrl" ng-cloak="">
    <div layout="row">

      <div flex class="app-title">
        <img src="/assets/umaas.png" alt="Grails" class="logo" style="width:40px; height:40px"/>
        <md-button style="margin-top:auto" href="#">UMAAS</md-button>
      </div>
        <md-menu>
          <md-button aria-label="Documentation Menu" class="md-primary md-raised" ng-click="$mdOpenMenu($event)">
            <span>Documentation</span>
            <div md-menu-origin></div>
          </md-button>
          <md-menu-content width="6">
            <md-menu-item >
              <md-button ng-href="{{indexCtrl.applicationData.docs + 'getting_started/index.html'}}">
                Getting Started
              </md-button>
            </md-menu-item>
            <md-menu-item >
              <md-button ng-href="{{indexCtrl.applicationData.docs + 'tutorials/index.html'}}">
                Tutorials
              </md-button>
            </md-menu-item>
            <md-menu-item >
              <md-button ng-href="{{indexCtrl.applicationData.docs + 'reference/index.html'}}">
                Reference
              </md-button>
            </md-menu-item>
          </md-menu-content>
        </md-menu>
        <md-menu ng-if="indexCtrl.user">
          <md-button aria-label="Accounts Menu" class="md-primary md-raised" ng-click="$mdOpenMenu($event)">
            <span>My Accounts</span>
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
             <md-nav-item md-nav-sref="community" name="community">Community</md-nav-item>
             <md-nav-item md-nav-sref="about" name="about">About</md-nav-item>
             <md-nav-item ng-if="!indexCtrl.user"  md-nav-sref="login" name="login">Log In</md-nav-item>
             <md-nav-item ng-if="!indexCtrl.user"
             md-nav-href="{{indexCtrl.applicationData.umaasRegistration + '?domain=' + indexCtrl.applicationData.domain}}"
             name="signup">Sign Up</md-nav-item>
             <md-nav-item ng-if="indexCtrl.user"  md-nav-href="/logout" name="logout">Log Out</md-nav-item>
        </md-nav-bar>
      </div>

    </div>
    <div  layout-fill>
        <div ui-view></div>
    </div>

    <asset:javascript src="/umaas/manager/site/umaas.manager.site.js" />
</body>
</html>
