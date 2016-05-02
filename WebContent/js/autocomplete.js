$(function() {
 
    $("#cat_title").autocomplete({
        source: "/services/open/searchCategory",
        select: function(event, ui) {
            var catId = ui.item.id;
            $("#cat_id").val(catId);
        }
    });
 
});