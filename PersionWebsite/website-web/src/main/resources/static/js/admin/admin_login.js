function adminLogin() {
    var username = $("#username").val();
    var password = $("#password").val();
    var loginDate = {"loginName": username, loginPassword : password};
    $.post(baseUrl + "/persion-website/user/admin/login", loginDate, function(result){
        console.log(result);
        var code = result.code;
        if (code == "1") {
            var data = result.resp.data;
            sessionStorage.setItem("adminUser", data);
            $.cookie('loginToken', data.loginToken);
            window.location.href = baseUrl + "/persion-website/admin/index";
        } else {
            alert("登录验证失败");
            return;
        }
    });
}

$(function(){
    $("#login_btn").on('click', function(e){adminLogin()});
    sessionStorage.setItem("baseUrl", baseUrl);
});