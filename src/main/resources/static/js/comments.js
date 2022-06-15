
/**
 * PAGE ACTIONS
 */
$('.comment-btn').keypress(function(e) {

    // Enter Key pressed
    if(e.keyCode == 13) {

        // Take value
        let comment = $(this).val();
        // Reset input field
        $(this).val('');
        // Get user and recipe id
        let val = $(this).prop('id');
        val = val.split(',');

        // targeting the span's value
        let target = $('#commented-recipe-' + val[0])
        console.log(target.text())

        // change number of comments +1
        let num = parseInt(target.text());
        target.html(num + 1);

        // Logic to keep anon user entering comments
        if(val[1] != -1){
            // addComment(comment, val);
        } else {
            alert('Please log in before commenting on recipe.')
        }
    }
})


/**
 * LOCAL DB CRUD
 */
// ADD COMMENT
function addComment(comment, values){
    const data = {
        user_id: values[1],
        recipe_id: values[0],
        comment: comment
    }
    const url = 'http://localhost:8080/ajax/add-comment';
    const readOption = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
    };

    fetch(url, readOption)
        .then((res) => res.json())
        .then((data) => {
            console.log(data);
        })
        .catch((e) => {
            console.log('Error adding comment to recipe m-t-m: ', e)
        });
}