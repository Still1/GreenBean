//TODO 验证表单
(function() {
    $(function() {
        const updateSettingButton = $('#updateSettingButton');

        updateSettingButton.on("click", function () {
            const nicknameObject = {
                nickname : $("#nicknameInput").val()
            };

            const headerObject = {};
            headerObject[csrfObject.headerName] = csrfObject.token;

            $.ajax({
                url : "updateUserSettings",
                method : "POST",
                headers : headerObject,
                data : nicknameObject
            }).done(function() {
                //TODO 成功的提示
                $("#userNickname").text(nicknameObject.nickname);
                $('#toast').toast('show');
            }).fail(function() {
            });
        });

        $('.toast').toast({delay : 2000});
    });
})();