package com.harsha.buildlinkbetweenadeptandcompanies.Models

class Post {
    var postUrl:String=""
    var caption:String=""
    constructor()
    constructor(postUrl: String, caption: String) {
        this.postUrl = postUrl
        this.caption = caption
    }
}