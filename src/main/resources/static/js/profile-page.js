/**
 * PAGE FUNCTIONS
 */
// DELETE BTN
$('.recipe-delete-btn').on('click', function (e) {
    e.preventDefault();

    let id = $(this);
    id = id[0].attributes[3].nodeValue;

    if(confirm("Confirm, You Want To DELETE this Recipe?")){
        window.location.href = "/recipes/delete/" + id;
    }


})