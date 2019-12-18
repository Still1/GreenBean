(function() {
    //TODO 抽取公共部分
    $(function() {
        const signOutLink = $('#signOutLink');
        signOutLink.on("click", function () {
            $('#signOutForm').trigger("submit");
        });
    });
})();