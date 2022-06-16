
/**
 *   PAGE ACTIONS
 */
// BOOKMARK BTN
$('.bookmark-btn').on('click', function (e) {
    e.preventDefault();

    // must check to see if a user is logged in, or you will get errors in the run log
    let checkAnonU = getBtnValue($(this));
    if(checkAnonU[1] != -1){
        if($(this).children('span').children('i').hasClass('fa-regular')){
            changeIconClass($(this), 'fa-regular', 'fa-solid');
            addToFavorite(getBtnValue($(this)));
        } else {
            changeIconClass($(this), 'fa-solid', 'fa-regular');
            removeFromFavorite(getBtnValue($(this)));
        }
    }
})

// HEART BTN
$('.heart-btn').on('click', function (e) {
    e.preventDefault();

    // must check to see if a user is logged in, or you will get errors in the run log
    let btnDataValue = getBtnValue($(this));
    if(btnDataValue[1] != -1){
        if($(this).children('span').children('i').hasClass('fa-regular')){
            // icon change
            changeIconClass($(this), 'fa-regular', 'fa-solid');

            // targeting the span's value
            let target = $('#rated-recipe-' + btnDataValue[0])

            // change number of ratings to +1
            let num = parseInt(target.text());
            target.html(num + 1);

            // to AJAX
            addUserRating(getBtnValue($(this)));
        } else {
            // icon change
            changeIconClass($(this), 'fa-solid', 'fa-regular');

            // targeting the span's value
            let target = $('#rated-recipe-' + btnDataValue[0])

            // change number of ratings to -1
            let num = parseInt(target.text());
            target.html(num - 1);
            target.html(num - 1);

            // to AJAX
            removeUserRating(getBtnValue($(this)));
        }
    }
})


// SCROLL TO TOP
function scrollToTop() {
    window.scrollTo(0, 0);
}

/**
 * HELPER FUN
 */
function getBtnValue(target){
    let val = target;
    // console.log(val)
    val = val[0].attributes[2].value.split(',');
    // console.log(val);
    return val;
}

function changeIconClass(target, fromClass, toClass){
    target.children('span').children('i').removeClass(fromClass).addClass(toClass);
}


/**
 *    LOCAL DB CRUD
 */
// ADD TO FAVORITE
function addToFavorite(values){
    const data = {
        user_id: values[1],
        recipe_id: values[0]
    }
    const url = '/ajax/add-favorite';
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
            // console.log(data);
        })
        .catch((e) => {
            // console.log('Error adding favorite recipe to user m-t-m: ', e)
        });
}

// REMOVE FROM FAVORITE
function removeFromFavorite(values){
    const data = {
        user_id: values[1],
        recipe_id: values[0]
    }
    const url = '/ajax/remove-favorite';
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
            // console.log(data);
        })
        .catch((e) => {
            // console.log('Error removing favorite recipe to user m-t-m: ', e)
        });
}

// ADD USER RATING
function addUserRating(values){
    const data = {
        user_id: values[1],
        recipe_id: values[0]
    }
    const url = '/ajax/add-rating';
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
            // console.log(data);
        })
        .catch((e) => {
            // console.log('Error adding user rating recipe to user 1-t-m: ', e)
        });
}

// REMOVE USER RATING
function removeUserRating(values){
    const data = {
        user_id: values[1],
        recipe_id: values[0]
    }
    const url = '/ajax/remove-rating';
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
            // console.log(data);
        })
        .catch((e) => {
            // console.log('Error removing user rating recipe to user 1-t-m: ', e)
        });
}

