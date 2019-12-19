(function() {
    $(function() {
        const signOutLink = $('#signOutLink');
        signOutLink.on("click", function () {
            $('#signOutForm').trigger("submit");
        });
    });
})();