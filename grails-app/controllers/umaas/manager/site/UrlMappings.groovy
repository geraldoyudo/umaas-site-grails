package umaas.manager.site

class UrlMappings {

    static mappings = {
        delete "/$controller/$id(.$format)?"(action:"delete")
        get "/$controller(.$format)?"(action:"index")
        get "/$controller/$id(.$format)?"(action:"show")
        post "/$controller(.$format)?"(action:"save")
        put "/$controller/$id(.$format)?"(action:"update")
        patch "/$controller/$id(.$format)?"(action:"patch")
        "/login"(controller: "login")
        "/login/auth"(controller: "login", action: "auth")
        "/login/authAjax"(controller: "login", action: "authAjax")
        "/login/authfail"(controller: "login", action: "authfail")
        "/login/denied"(controller: "login", action: "denied")
        "/login/full"(controller: "login", action: "full")
        "/login/ajaxSuccess"(controller: "login", action: "ajaxSuccess")
        "/login/ajaxDenied"(controller: "login", action: "ajaxDenied")
        "/logout"(controller:"logout")
        "/"(view: '/index')
        "500"(view: '/error')
        "404"(view: '/notFound')
        get "/$controller/listByUser"(action:"listByUser")
    }
}
