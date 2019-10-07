(function() {
    $(document).ready(function() {
        var signOutLink = $('#signOutLink');
        signOutLink.click(function () {
            $('#signOutForm').submit();
        });
    })
})();