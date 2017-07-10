/**
 * Created by gabriel on 10/07/17.
 */
$(() => {
    "use strict";

    $('#btn_oferta').click(function (event) {
        const data = $('#oferta').serialize();

        $.ajax({
            url: '/oferta/nova',
            type: 'get',
            data: data,
            success: function(response){
                $('#message').text(response);
                $('#message').addClass('center flow-text green-text');
            },
            error: function (response) {
                $('#message').text('Não foi possivel fazer a oferta!');
            }
        });

        event.preventDefault();
    });

})