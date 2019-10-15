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


        var validator = signUpForm.validate({
            //TODO 验证username password的长度
            rules : {
                username : {
                    required : true,
                    remote : {
                        url : "signUp/validateUsername",
                        method : "GET",
                        data : {
                            username : function() {
                                return $("#usernameSignUp").val();
                            }
                        }
                    }
                },
                password : {
                    required : true
                },
                confirmPassword : {
                    required : true,
                    equalTo : "#passwordSignUp"
                }
            },
            messages : {
                username : {
                    remote : "Username exists."
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
    });
})();

