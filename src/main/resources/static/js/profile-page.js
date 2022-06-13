/**
 * PAGE FUNCTIONS
 */
// DELETE BTN
$('.recipe-delete-btn').on('click', function (e) {
    e.preventDefault();

    let id = $(this)[0].attributes[3].nodeValue;

    if(confirm("Confirm, You Want To DELETE this Recipe?")){
        window.location.href = "/recipes/delete/" + id;
    }
});

// EDIT BTN
$('.recipe-edit-btn').on('click', function (e) {
    e.preventDefault();

        console.log($(this));
    let id = $(this)[0].attributes[2].nodeValue;

    if(confirm("Confirm, You Want To EDIT this Recipe?")){
        window.location.href = "/recipes/edit/" + id;
    }
});


