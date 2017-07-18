'use strict';

$(() => {
    $('#filtro').submit(function (e) {
        var filtro = $('#categoria').val()
        var username = $('#usuario').text()

        $.ajax({
            method: 'get',
            url: '/api/projetos/' + filtro,
            data: {username : username}
        }).done((data) => {
            $('#projetos').empty();
            mostrarProjetos(data)
        }).fail((e) => {
            console.log('ERROR: Selecione uma categoria!')
        })

        e.preventDefault()
    })
})

function mostrarProjetos(projetos) {
    for (var pos in projetos) {
        var pedido = projetos[pos]
        $('#projetos').append(
            '<div class="divider col m12 s12"></div>' +
            '<div class="col m12 s12">' +
                '<h4 class="blue-text left">' + pedido.nome + '</h4>' +
                '<a href="/pedidos/user/' + pedido.id + '" class="btn blue right distance-min">Detalhes</a>' +
            '</div>' +
            '<div class="col m6 s12">' +
                '<p><strong>Categoria: </strong><span>' + pedido.servico.categoria + '</span></p>' +
                '<p><strong>Patrocinado: </strong><span>'  + pedido.usuario.nome + '</span></p>' +
                '<p><strong>Preço estimado: </strong><span>' +  pedido.valorEstimado + '</span></p>' +
            '</div>' +
            '<div class="col m6 s12">' +
                '<p class="truncate">' + pedido.servico.atividade + '</p>' +
            '</div>'
        );
    }
}