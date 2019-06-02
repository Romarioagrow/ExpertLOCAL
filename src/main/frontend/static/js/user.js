/*$(document).ready(function(){
    $('button[type="submit"][id="submitRegistration"]').on('click', collectUserDetails);
});*/

/*function collectUserDetails(e) {
    e.preventDefault();

    let fullName = $('#registerName').val() + ' ' + $('#registerLastName').val();
    console.log(fullName);

    var userDetails = {
        email: $('#registerEmail').val(),
        fullName: fullName,
        mobile: $('#registerMobile').val(),
        password :$('#registerPassword').val(),
        passwordConfirm : $('#registerPasswordConfirm').val(),
    };

    userDetails = JSON.stringify(userDetails);
    console.log(userDetails);

    $.ajax({
        url: 'http://localhost:8080/user/registration',
        type: 'POST',
        data: userDetails,
        dataType: 'json',
        processData: false,
        headers: {'Content-Type': 'application/json'},
        complete: function (response) {
            var result = response.responseText;
            console.log(result);

            if (result.includes('success')) window.location.href = 'http://localhost:8080/user/login';
            else $('#registration-error').empty().append(
                '<h3>Пользователь с электронным адресом '+$('#registerEmail').val()+' уже существует, либо пароли не совпадают!</h3>'
            );
        }
    });
}*/
