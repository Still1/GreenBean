(function() {
    $(function() {
        function initRatingStar(ratingClickStar, ratingIntroText) {
            ratingClickStar.on('mouseover', function (event) {
                const targetIndex = $(event.target).data('index');
                makeStarsSolid(ratingClickStar, ratingIntroText, targetIndex);
            });

            ratingClickStar.on('mouseleave', function () {
                const targetIndex = ratingClickStar.attr('data-rating');
                makeStarsSolid(ratingClickStar, ratingIntroText, targetIndex);
            });

            ratingClickStar.on('click', function (event) {
                const targetIndex = $(event.target).data('index');
                const dialogRatingClickStar = $('#dialogRatingClickStar');
                dialogRatingClickStar.attr('data-rating', targetIndex);
                dialogRatingClickStar.triggerHandler('mouseleave');
                const ratingClickStarId = ratingClickStar.attr('id');
                if(ratingClickStarId == 'ratingClickStar') {
                    const typeInput = $('#addUserRatingForm>input[name=type]');
                    typeInput.val(2);
                }
                const score = targetIndex * 2;
                $('#addUserRatingForm>input[name=score]').val(score);
            });

            ratingClickStar.triggerHandler('mouseleave');
        }

        const ratingIntroTextArray = ['', 'Very bad', 'Bad', 'Average', 'Good', 'Very good'];

        function makeStarsSolid(ratingClickStar, ratingIntroText, targetIndex) {
            ratingClickStar.children().each(function(index, element) {
                const img = $(element.firstElementChild);
                if(index < targetIndex) {
                    img.attr('src', ratingClickStar.attr('data-solid'));
                } else {
                    img.attr('src', ratingClickStar.attr('data-hollow'));
                }
            });
            ratingIntroText.text(ratingIntroTextArray[targetIndex]);
        }

        const ratingClickStar = $('#ratingClickStar');
        const ratingIntroText = $('#ratingIntroText');
        initRatingStar(ratingClickStar, ratingIntroText);

        const dialogRatingClickStar = $('#dialogRatingClickStar');
        const dialogRatingIntroText = $('#dialogRatingIntroText');
        initRatingStar(dialogRatingClickStar, dialogRatingIntroText);

        const ratingDialog = $('#ratingDialog');

        ratingDialog.on('show.bs.modal', function(event) {
            const button = $(event.relatedTarget);
            const recipient = button.data('status');
            const modal = $(this);
            modal.find('.modal-title>span').text(recipient);
        });

        ratingDialog.on('hidden.bs.modal', function() {
            makeStarsSolid(dialogRatingClickStar, dialogRatingIntroText, 0);
            dialogRatingClickStar.attr('data-rating', 0);
            $('#addUserRatingForm>input[name=type]').val('');
            $('#addUserRatingForm>input[name=score]').val('');
        });

        $('#ratingButton>button').on('click', function() {
            const buttonId = $(this).attr('id');
            const typeInput = $('#addUserRatingForm>input[name=type]');
            //XXX 硬编码
            if(buttonId == 'readingButton') {
                typeInput.val(1);
            } else if(buttonId == 'readButton') {
                typeInput.val(2);
            }
        });
    });
})();