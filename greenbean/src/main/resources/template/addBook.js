(function() {
    $(function() {
        bindNextButtonClickEvent();
        bindCancelButtonClickEvent();
        bindAddButtonClickEvent();
        initAutocomplete();
    });

    function initAutocomplete() {
        $('input[name="author"]').autocomplete({
            source: function(request, response) {
                $.ajax({
                    url : "getAuthorSuggestion",
                    method : "GET",
                    data : {
                        keyword : request.term
                    },
                    dataType : "json"
                }).done(function(data) {
                    response(data);
                }).fail(function() {
                });
            },
            minLength: 1,
            select: function(event, ui) {
                // log( "Selected: " + ui.item.value + " aka " + ui.item.id );
            }
        });
    }

    function bindAuthorInputChangeEvent() {
        const authorInput = $('input[name="author"]');
        authorInput.on('keydown', function(event) {
            //TODO 关键词为空，不发请求
            //TODO 点击选择后，不发请求
        });
    }

    function bindNextButtonClickEvent() {
        const nextButton = $('#nextButton');
        nextButton.on("click", function () {
            //TODO 第一步的时候，验证表单
            $('.d-none').removeClass('d-none');
            $('#isbn').attr('readonly', 'readonly');
            nextButton.hide();
            $('#submitButton').show();
        });
    }

    function bindCancelButtonClickEvent() {
        const cancelButton = $('#cancelButton');
        cancelButton.on('click', function() {
            window.location.href = "home";
        });
    }

    function bindAddButtonClickEvent() {
        const addButton = $('.addButton');
        addButton.on('click', function(event) {
            const addButton = $(event.currentTarget);
            const dataNumber = parseInt(addButton.attr('data-number'));
            const row = addButton.prev().children(':last-child');
            const cloneRow = row.clone();
            row.after(cloneRow);
            cloneRow.find('.prefixNumber>span:first').text(dataNumber + 1);
            addButton.attr("data-number", dataNumber + 1);
            //XXX 考虑更好的写法
            addButton.css("top", dataNumber * 38 + "px");
        });
    }

    // TODO 验证表单 限制表单的输入 例如ISBN只能输入数字
})();