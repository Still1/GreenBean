(function() {
    $(function() {
        const nextButton = $('#nextButton');
        nextButton.on("click", function () {
            //TODO 第一步的时候，验证表单
            $('.d-none').removeClass('d-none');
            $('#isbn').attr('readonly', 'readonly');
        });
    });
})();