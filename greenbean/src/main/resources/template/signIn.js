(function() {
    $(function() {

        // XXX 可以重构
        const activeClassName = "active";
        const formHideClassName = "formHide";
        const signInTab = $("#signInTab");
        const signUpTab = $("#signUpTab");
        const signInForm = $("#signInForm");
        const signUpForm = $("#signUpForm");

        const signInObject = {
            tab : signInTab,
            form : signInForm
        };
        const signUpObject = {
            tab : signUpTab,
            form : signUpForm
        };
        const signObjectArray = [signInObject, signUpObject];

        const handlerDataObject = {
            activeClassName : activeClassName,
                formHideClassName : formHideClassName,
                signObjectArray : signObjectArray
        };

        const tabClickHandler = function(event) {
            const handlerDataObject = event.data;
            const targetDOMObject = $(event.target);
            const signObjectArray = handlerDataObject.signObjectArray;
            for(let i = 0; i < signObjectArray.length; i++) {
                if(signObjectArray[i].tab.is(targetDOMObject)) {
                    if(!targetDOMObject.hasClass(handlerDataObject.activeClassName)) {
                        targetDOMObject.addClass(handlerDataObject.activeClassName);
                        const targetForm = signObjectArray[i].form;
                        targetForm.removeClass(handlerDataObject.formHideClassName);
                    }
                } else {
                    signObjectArray[i].tab.removeClass(handlerDataObject.activeClassName);
                    signObjectArray[i].form.addClass(handlerDataObject.formHideClassName);
                }
            }
        };

        for(let i = 0; i < signObjectArray.length; i++) {
            signObjectArray[i].tab.on("click", handlerDataObject, tabClickHandler);
        }


        signUpForm.validate({
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

