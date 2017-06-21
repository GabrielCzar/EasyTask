/**
 * Created by gabriel on 07/06/17.
 */
$(() => {
    /* Inicializacao do materialize */
    $('.modal').modal();
    $('ul.tabs').tabs();
    $('.tooltipped').tooltip({delay: 40});
    $('.carousel.carousel-slider').carousel({fullWidth: true, padding:300}, setTimeout(autoplay, 4500));
    $('.dropdown-button').dropdown({
            inDuration: 300,
            outDuration: 225,
            constrainWidth: false, // Does not change width of dropdown to that of the activator
            hover: true, // Activate on hover
            gutter: 0, // Spacing from edge
            belowOrigin: false, // Displays dropdown below the button
            alignment: 'left', // Displays dropdown with edge aligned to the left of button
            stopPropagation: false // Stops event propagation
        }
    );
    $('.button-collapse').sideNav({
            menuWidth: 300, // Default is 300
            edge: 'right', // Choose the horizontal origin
            closeOnClick: true, // Closes side-nav on <a> clicks, useful for Angular/Meteor
            draggable: true // Choose whether you can drag to open on touch screens
        }
    );

    /* Funcoes alternativas */
    function autoplay() {
        $('.carousel').carousel('next');
        setTimeout(autoplay, 4500);
    };
});
