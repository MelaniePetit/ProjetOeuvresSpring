/**
 * Created by jeremy on 15/02/2017.
 */

$(document).ready(function(){
    var menu = $('#MenuContainer');
    $('.open-close-menu').click(function () {
        if(menu.hasClass("slide")){
            menu.removeClass("slide");
        }
        else{
            menu.addClass("slide");
        }
    })
});