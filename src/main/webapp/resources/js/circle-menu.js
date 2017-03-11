/**
 * Created by jeremy on 08/02/2017.
 */

$(document).ready(function(){
    $('#circleMenu').click(function () {
        if( $(this).hasClass('active'))
        {
            $(this).removeClass('active');
            $('#overlay').removeClass('active');

            $('.menu-list .menu').each(function(){
                $(this).removeClass('active');
            });
        }
        else
        {
            $(this).addClass('active');
            $('#overlay').addClass('active');

            $('.menu-list .menu').each(function(){
                $(this).addClass('active');
            });
        }
    });

    $('#menu-toggle').click(function(){
        $(this).toggleClass('open');
    });

    $('.menu').each(function () {
        $(this).mouseenter(function () {
            if ($(this).hasClass("active")) {
                var value = $(this).attr("menu-label");
                $('.menu-label').text(value);
                $('.menu-label').addClass("appear");
            }
        });
        $(this).mouseleave(function () {
            $('.menu-label').removeClass("appear");
        });
    })
});