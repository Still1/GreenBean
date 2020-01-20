(function() {
    $(function () {
        initPublicationDay();

        function initPublicationDay() {
            const publicationMonth = $('#publicationMonth');
            publicationMonth.triggerHandler('change');

            const publicationDay = $('#publicationDay');
            const originalValue = publicationDay.data("originalvalue");
            const targetOption = publicationDay.find('[value=' + originalValue + ']');
            targetOption.prop('selected', 'true');
        }
    });
})();