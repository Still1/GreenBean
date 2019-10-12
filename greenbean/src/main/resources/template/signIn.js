(function() {
    $(document).ready(function() {

        // XXX 可以重构
        var activeClassName = "active";
        var formHideClassName = "formHide";
        var signInTab = $("#signInTab");
        var signUpTab = $("#signUpTab");
        var signInForm = $("#signInForm");
        var signUpForm = $("#signUpForm");

        var signInObject = {
            tab : signInTab,
            form : signInForm
        };
        var signUpObject = {
            tab : signUpTab,
            form : signUpForm
        };
        var signObjectArray = new Array(signInObject, signUpObject);

        var handlerDataObject = {
            activeClassName : activeClassName,
            formHideClassName : formHideClassName,
            signObjectArray : signObjectArray
        };

        var tabClickHandler = function(event) {
            var handlerDataObject = event.data;
            var targetDOMObject = $(event.target);
            var signObjectArray = handlerDataObject.signObjectArray;
            for(var i = 0; i < signObjectArray.length; i++) {
                if(signObjectArray[i].tab.is(targetDOMObject)) {
                    if(!targetDOMObject.hasClass(handlerDataObject.activeClassName)) {
                        targetDOMObject.addClass(handlerDataObject.activeClassName);
                        var targetForm = signObjectArray[i].form;
                        targetForm.removeClass(handlerDataObject.formHideClassName);
                    }
                } else {
                    signObjectArray[i].tab.removeClass(handlerDataObject.activeClassName);
                    signObjectArray[i].form.addClass(handlerDataObject.formHideClassName);
                }
            }
        };

        for(var i = 0; i < signObjectArray.length; i++) {
            signObjectArray[i].tab.click(handlerDataObject, tabClickHandler);
        }

        signUpForm.submit(function() {
            var validator = signUpForm.validate({
                rules : {
                    username : {
                        required : true
                    },
                    password : {
                        required : true
                    },
                    confirmPassword : {
                        required : true,
                        equalTo : "#passwordSignUp"
                    }
                },
                validClass : "is-valid",
                errorClass : "is-invalid",
                errorElement : "div",
                showErrors : function() {
                    this.defaultShowErrors();
                    $("div.is-invalid").addClass("invalid-feedback");
                }
            });
            var result = signUpForm.valid();
            return result;
        });

        var usernameSignUpInput = $("#usernameSignUp");
        usernameSignUpInput.blur(function() {
            console.log("blur");
        });
    });
})();

