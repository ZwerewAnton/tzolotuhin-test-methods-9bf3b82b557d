$(document).ready(function(){
    $("#create_customer_id").click(
        function() {
            var fName = $("#first_name_id").val();
            var lName = $("#last_name_id").val();
            var email = $("#email_id").val();
            var password = $("#password_id").val();
            $('input[type="text"],input[type="password"]').css("border","2px solid #00F5FF");
            $('input[type="text"],input[type="password"]').css("box-shadow","0 0 5px #00F5FF");
            // check fields
            if(fName == ''){
                $('input[name="first_name"]').css("border","2px solid red");
                $('input[name="first_name"]').css("box-shadow","0 0 3px red");
                alert("FirstName is empty.");
            }else if(fName.includes(' ')){
                $('input[name="first_name"]').css("border","2px solid red");
                $('input[name="first_name"]').css("box-shadow","0 0 3px red");
                alert("FirstName have space.");
            }else if(fName.length < 5 || fName.length > 13){
                $('input[name="first_name"]').css("border","2px solid red");
                $('input[name="first_name"]').css("box-shadow","0 0 3px red");
                alert("FirstName's length should be more or equal 6 symbols and less or equal 12 symbols.");
            }else if(lName == '') {
                $('input[name="last_name"]').css("border","2px solid red");
                $('input[name="last_name"]').css("box-shadow","0 0 3px red");
                alert("LastName is empty.");
            }else if(lName.includes(' ')){
                $('input[name="last_name"]').css("border","2px solid red");
                $('input[name="last_name"]').css("box-shadow","0 0 3px red");
                alert("LastName have space.");
            }else if(email =='' || password =='') {
                $('input[name="email"],input[name="password"]').css("border","2px solid red");
                $('input[name="email"],input[name="password"]').css("box-shadow","0 0 3px red");
                alert("Email or password is empty.");
            }else if(password.includes(fName) || password.includes(lName)){
                $('input[name="password"]').css("border","2px solid red");
                $('input[name="password"]').css("box-shadow","0 0 3px red");
                alert("Password contains part of the FirstName or LastName.");
            }else {
                $.post({
                    url: 'rest/create_customer',
                    headers: {
                        'Authorization': 'Basic ' + btoa('admin' + ':' + 'setup'),
                        'Content-Type': 'application/json'
                    },
                    data: JSON.stringify({
                                         	"firstName":fName,
                                             "lastName":lName,
                                             "login":email,
                                             "pass":password,
                                             "balance":"0"
                                         })
                }).done(function(data) {
                     $.redirect('/endpoint/customers.html', {'login': 'admin', 'pass': 'setup', 'role': 'ADMIN'}, 'GET');
                });
            }
        }
    );
});