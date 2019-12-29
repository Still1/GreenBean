(function() {
    $(function() {
        bindNextButtonClickEvent();
        bindCancelButtonClickEvent();
        bindAddButtonClickEvent();
        initAutocomplete($('input[name="author"]'), "getAuthorSuggestion");
        initAutocomplete($('input[name="translator"]'), "getTranslatorSuggestion");
        bindDateChangeEvent();
    });

    function bindDateChangeEvent() {
        const dateSelectElement = $('#publicationYear,#publicationMonth');
        dateSelectElement.on('change', function() {
            const publicationDay = $('#publicationDay');
            //XXX 切换年月时，原来的日如果是合法则保留
            publicationDay.children(':not(:first-child)').remove();
            const publicationMonthValue = $('#publicationMonth').val();
            if(publicationMonthValue != 0) {
                const publicationYearValue = $('#publicationYear').val();
                const dayNumber = new Date(publicationYearValue,publicationMonthValue,0).getDate();
                for(let i = 0; i < dayNumber; i++) {
                    const optionElement = $('<option></option>');
                    optionElement.val(i + 1);
                    optionElement.text(i + 1);
                    publicationDay.append(optionElement);
                }
            }
        });
    }

    //XXX 考虑更好写法
    function initAutocomplete(selector, url) {
        selector.autocomplete({
            source: function(request, response) {
                $.ajax({
                    url : url,
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
            minLength: 1
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
            const cloneRowInput = cloneRow.find('input');
            cloneRowInput.val("");
            const cloneRowInputName = cloneRowInput.attr("name");
            let url = 'get' + cloneRowInputName.substr(0, 1).toUpperCase() + cloneRowInputName.substr(1) + 'Suggestion';
            initAutocomplete(cloneRowInput, url);

            row.after(cloneRow);
            cloneRow.find('.prefixNumber>span:first').text(dataNumber + 1);
            addButton.attr("data-number", dataNumber + 1);

            //XXX 考虑更好的写法
            addButton.css("top", dataNumber * 38 + "px");
        });
    }

    // TODO 验证表单 限制表单的输入 例如ISBN只能输入数字
})();