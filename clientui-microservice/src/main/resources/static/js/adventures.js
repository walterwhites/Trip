$(document).ready(function(){

    $(".filter-button").click(function(){
        var value = $(this).attr('data-filter');

        if(value == "all")
        {
            $(".filter").fadeIn("slow");
        }
        else
        {
            $(".filter").not('.'+value).fadeOut("slow", function() {
                $(".filter").not('.'+value).hide();
                $(".filter").filter('.'+value).fadeIn("slow");
            });
        }
        $(".filter-button").not("button[data-filter=" + value + "]").removeClass('btn-primary');
        $(".filter-button").filter("button[data-filter=" + value + "]").addClass('btn-primary');
    });
});