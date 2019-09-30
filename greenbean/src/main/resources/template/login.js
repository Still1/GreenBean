(function() {
    $(document).ready(function() {
        var activeClassName = "active";
        var signInTab = $("#signInTab");
        var signUpTab = $("#signUpTab");
        var tabArray = new Array(signInTab, signUpTab);
        var submitButton = $("#submitButton");

        var handlerDataObject = {
            activeClassName : activeClassName,
            tabArray : tabArray,
            submitButton : submitButton
        };

        var tabClickHandler = function(event) {
            var handlerDataObject = event.data;
            var targetDOMObject = $(event.target);
            var tabArray = handlerDataObject.tabArray;
            for(var i = 0; i < tabArray.length; i++) {
                if(tabArray[i].is(targetDOMObject)) {
                    if(!targetDOMObject.hasClass(handlerDataObject.activeClassName)) {
                        targetDOMObject.addClass(handlerDataObject.activeClassName);
                        handlerDataObject.submitButton.text(targetDOMObject.text());
                    }
                } else {
                    tabArray[i].removeClass(handlerDataObject.activeClassName);
                }
            }
        };

        for(var i = 0; i < tabArray.length; i++) {
            tabArray[i].click(handlerDataObject, tabClickHandler);
        }
    });
})();

