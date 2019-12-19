//TODO 验证表单
(function() {
    $(function() {
        const updateSettingButton = $('#updateSettingButton');

        updateSettingButton.on("click", function () {
            const nickname = $("#nicknameInput").val();
            const avatar = $("#avatarInput")[0].files[0];

            const formData = new FormData();
            formData.append("nickname", nickname);
            formData.append("avatar", avatar);

            const headerObject = {};
            headerObject[csrfObject.headerName] = csrfObject.token;

            $.ajax({
                url : "updateUserSettings",
                method : "POST",
                contentType : false,
                headers : headerObject,
                data : formData,
                processData : false
            }).done(function(data) {
                $("#userNickname").text(nickname);
                if(data !== undefined && data !== "") {
                    //XXX 处理URL
                    $(".avatar").attr("src", "/greenbean/static/picture/avatars/" + data)
                }
                //XXX 抽取方法
                $("#toastBody").text("Update successfully.");
                $("#toastRect").attr("fill", "#54FF9F");
                $('#toast').toast('show');
            }).fail(function() {
                $("#toastBody").text("Update unsuccessfully.");
                $("#toastRect").attr("fill", "#FF3030");
                $('#toast').toast('show');
            });
        });
        $('.toast').toast({delay : 5000});
    });
})();