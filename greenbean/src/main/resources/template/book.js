(function() {
    $(function() {
        function initRatingStar(ratingClickStar, ratingIntroText, initDialogRatingClickStar) {
            ratingClickStar.on('mouseover', function (event) {
                const targetIndex = $(event.target).data('index');
                makeStarsSolid(ratingClickStar, ratingIntroText, targetIndex);
            });

            ratingClickStar.on('mouseleave', function () {
                const targetIndex = ratingClickStar.attr('data-rating');
                makeStarsSolid(ratingClickStar, ratingIntroText, targetIndex);
            });

            if(initDialogRatingClickStar) {
                ratingClickStar.on('click', function (event) {
                    const targetIndex = $(event.target).data('index');
                    const dialogRatingClickStar = $('#dialogRatingClickStar');
                    dialogRatingClickStar.attr('data-rating', targetIndex);
                    dialogRatingClickStar.triggerHandler('mouseleave');

                    setFormScore(targetIndex);
                });
            }
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

        function setFormScore(star) {
            const score = star * 2;
            $('#addUserRatingForm>input[name=score]').val(score);
        }

        const ratingClickStar = $('#ratingClickStar');
        const ratingIntroText = $('#ratingIntroText');
        initRatingStar(ratingClickStar, ratingIntroText, true);
        ratingClickStar.on('click', function () {
            const typeInput = $('#addUserRatingForm>input[name=type]');
            typeInput.val(2);
        });

        const dialogRatingClickStar = $('#dialogRatingClickStar');
        const dialogRatingIntroText = $('#dialogRatingIntroText');
        initRatingStar(dialogRatingClickStar, dialogRatingIntroText, true);

        const userRatingClickStar = $('#userRatingClickStar');
        const userRatingIntroText = $('#userRatingIntroText');
        initRatingStar(userRatingClickStar, userRatingIntroText, false);
        userRatingClickStar.on('click', function (event) {
            const targetIndex = $(event.target).data('index');
            setFormScore(targetIndex);
            $('#addUserRatingForm').trigger('submit');
        });

        const ratingDialog = $('#ratingDialog');

        ratingDialog.on('show.bs.modal', function(event) {
            const button = $(event.relatedTarget);
            const recipient = button.data('status');
            const modal = $(this);
            modal.find('.modal-title>span').text(recipient);
        });

        ratingDialog.on('hidden.bs.modal', function() {
            const ratingOperation = $('#ratingOperation');
            const originalRating = ratingOperation.data('rating');
            const originalType = ratingOperation.data('type');
            let starCount = 0;
            let score = '';
            if(originalRating !== undefined) {
                starCount = originalRating;
                score = originalRating * 2;
            }
            if(originalType !== undefined) {
                $('#addUserRatingForm input[name=type][value=' + originalType + ']').prop('checked', 'true');
            } else {
                $('#addUserRatingForm>input[name=type][type=hidden]').val('');
            }
            makeStarsSolid(dialogRatingClickStar, dialogRatingIntroText, starCount);
            dialogRatingClickStar.attr('data-rating', starCount);
            $('#addUserRatingForm>input[name=score]').val(score);
        });

        $('.ratingButton>button').on('click', function() {
            const buttonId = $(this).attr('id');
            const typeInput = $('#addUserRatingForm>input[name=type]');
            //XXX 硬编码
            if(buttonId == 'readingButton') {
                typeInput.val(1);
            } else if(buttonId == 'readButton') {
                typeInput.val(2);
            }
        });

        $('#saveButton').on('click', function() {
            $('#addUserRatingForm').trigger('submit');
        });

        $('#removeButton').on('click', function() {
            $('#removeUserRatingForm').trigger('submit');
        });
    });
})();