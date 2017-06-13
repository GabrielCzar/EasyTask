/**
 * Created by gabriel on 07/06/17.
 */
$(() => {
    $('#cadastrar').click((e) => {
        $('#fform_entrar').hide('slow');
        $('#fform_cadastrar').show('slow');
        e.preventDefault();
    });
    $('#entrar').click((e) => {
        $('#fform_entrar').show('slow');
        $('#fform_cadastrar').hide('slow');
        e.preventDefault();
    });
    $('.modal').modal();

});
