$(document).ready(function(){

    $(".filter-button").click(function(){
        var value = $(this).attr('data-filter');

        if(value == "all")
        {
            $('.filter').show('1000');
        }
        else
        {
            $(".filter").not('.'+value).hide('3000');
            $('.filter').filter('.'+value).show('3000');
        }
        $(".filter-button").not("button[data-filter=" + value + "]").removeClass('btn-primary');
        $(".filter-button").filter("button[data-filter=" + value + "]").addClass('btn-primary');
    });
});