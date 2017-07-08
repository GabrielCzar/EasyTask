
$(() => {
    'use strict';

    $('#telefone').formatter({
        'pattern': '({{99}}) {{9}}.{{9999}}-{{9999}}',
        'persistent': false
    });

    $('#cpf').formatter({
        'pattern': '{{999}}.{{999}}.{{999}}-{{99}}',
        'persistent': false
    });

});