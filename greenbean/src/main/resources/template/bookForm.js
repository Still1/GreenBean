(function() {
    $(function() {
        bindAddButtonClickEvent();
        //XXX 绝对路径 硬编码
        initAutocomplete($('input[name="author"]'), "/greenbean/getAuthorSuggestion");
        initAutocomplete($('input[name="translator"]'), "/greenbean/getTranslatorSuggestion");
        bindDateChangeEvent();
        setAddButtonTop($('.addButton'));
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

    function bindAddButtonClickEvent() {
        const addButton = $('.addButton');
        addButton.on('click', function(event) {
            const addButton = $(event.currentTarget);
            const row = addButton.prev().children(':last-child');
            const cloneRow = row.clone();
            const cloneRowInput = cloneRow.find('input');
            cloneRowInput.val("");
            const cloneRowInputName = cloneRowInput.attr("name");
            //XXX 绝对路径 硬编码
            let url = '/greenbean/get' + cloneRowInputName.substr(0, 1).toUpperCase() + cloneRowInputName.substr(1) + 'Suggestion';
            initAutocomplete(cloneRowInput, url);

            row.after(cloneRow);
            const dataNumber = parseInt(addButton.attr('data-number'));
            cloneRow.find('.prefixNumber>span:first').text(dataNumber + 1);
            addButton.attr("data-number", dataNumber + 1);

            setAddButtonTop(addButton);
        });
    }

    function setAddButtonTop(addButton) {
        //XXX 考虑更好的写法
        addButton.each(function() {
            const dataNumber = parseInt($(this).attr('data-number'));
            $(this).css("top", (dataNumber - 1) * 38 + "px");
        });
    }

    // TODO 验证表单 限制表单的输入 例如ISBN只能输入数字
})();