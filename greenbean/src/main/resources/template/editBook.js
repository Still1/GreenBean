(function() {
    $(function () {
        initPublicationDay();
        bindCancelButtonClickEvent();

        function initPublicationDay() {
            const publicationMonth = $('#publicationMonth');
            publicationMonth.triggerHandler('change');

            const publicationDay = $('#publicationDay');
            const originalValue = publicationDay.data("originalvalue");
            const targetOption = publicationDay.find('[value=' + originalValue + ']');
            targetOption.prop('selected', 'true');
        }

        function bindCancelButtonClickEvent() {
            const cancelButton = $('#cancelButton');
            cancelButton.on('click', function() {
                window.history.back();
            });
        }
    });
})();