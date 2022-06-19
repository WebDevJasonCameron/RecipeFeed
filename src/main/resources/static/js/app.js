// VARS, ARRAYS AND LISTS
let recipeListReturns;
let recipeDetails;
let startingOffset = 0;

let keyWord = "";
let userId = "";

const apiKey = SPOON_KEY_ONE;
// const apiKey = SPOON_KEY_TWO;
// const apiKey = SPOON_KEY_THREE;


/**
 *   PAGE ACTIONS
 */
// SEARCH UNDER PROFILE (LARGE)
$('#search-input-02').keypress(function(e) {

    // Enter Key pressed
    if (e.keyCode == 13) {

        // SETTING THE VARS
        // Take comment value
        let searchValue = $(this).val();

        // get the user id
        userId = $('#user-id').val();

        // the search
        // startingOffset += 50;
        getSpoonRecipeListByKeyWord(searchValue);

    }
});

// SEARCH DURING SMALL TO MED SCREENS
$('#search-input-03').keypress(function(e) {

    // Enter Key pressed
    if (e.keyCode == 13) {

        // SETTING THE VARS
        // Take comment value
        let searchValue = $(this).val();

        // get the user id
        userId = $('#user-id').val();

        // the search
        // startingOffset += 50;
        getSpoonRecipeListByKeyWord(searchValue);

    }
});


// FILTER BTN ACTION
$('#find-more-from-api-btn').on('click', (e) => {
    e.preventDefault();

    // get the user id
    userId = $('#user-id').val();

    // the search
    startingOffset += 50;
    getSpoonRecipeListByKeyWord($('#search-input-01').val());
    scrollToTop();
})

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

// ACCESS MODAL
function seeRecipeDetails(id){
    getSpoonRecipeDetailsByID(id);
}

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

function loader(){
    return `
        <div class="d-flex justify-content-center">
            <img src="/img/loader.gif" alt="loading" style="width: 75%;">
        </div>
    `;
}

function removeTags(s) {
    if ((s === null) || (s === '')) {
        return false;
    }
    else {
        s = s.toString();
        return ( /(<([^>]+)>)/ig, '');
    }
}


/**
 *    LOCAL DB CRUD
 */
// GET R LIST
function getSpoonRecipeListByKeyWord(kw){

    keyWord = kw;
    console.log('kw was ' + kw)

    const spoonURL = 'https://api.spoonacular.com/recipes/complexSearch?apiKey=' + apiKey + '&query=' + kw + '&offset=' + startingOffset + '&number=50';
    const readOption = {
        method: 'GET',
    };

    fetch(spoonURL, readOption)
        .then((res) => res.json())
        .then((data) => {
            // console.log(data);
            recipeListReturns = data;
        }).then(() => {
        $('#feed').html(combineCards(recipeListReturns));
    })
}

// GET R DETAILS
function getSpoonRecipeDetailsByID(cid){

    const spoonURL = 'https://api.spoonacular.com/recipes/' + cid + '/information?apiKey=' + apiKey;
    const readOption = {
        method: 'GET',
    };

    fetch(spoonURL, readOption)
        .then((res) => res.json())
        .then((data) => {
            // display loader
            $('#recipe-details-modal-content').html(loader());
            // console.log(data);
            recipeDetails = data;
        })
        .then(() => {
            // Loader here
            $('#recipe-details-modal-content').html(makeModalBody(recipeDetails));
        });
}

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


/**
 * BUILD BODY
 */
// CREATE CARD
function makeCard(r){
    return  `
        <div class="card border-0 mt-4">
            <div class="row no-gutters">
                <div class="col-sm-7">
                    <img 
                        src="${r.image}" 
                        alt="image of ${r.title}" 
                        class="card-img-top">
                </div>
                <div class="col-sm-5">
                    <div class="card-body border-0">
                        <div class="card-title h4">
                            ${r.title}
                        </div>
                    </div>
                    <div class="card-footer bg-white border-0">
                        <button 
                            onclick="seeRecipeDetails(${r.id})"
                            class="btn btn-secondary w-100" 
                            type="button" 
                            data-toggle="modal" 
                            data-target="#recipe-details-modal"
                            >
                                See Details
                        </button>
                    </div>
                </div>
            </div>
        </div>
    `
}

// COMBINE CARDS
function combineCards(rL){
    let output = '';
    for (let i = 0; i < rL.results.length; i++) {
        output += makeCard(rL.results[i]);
    }
    return output;
}

// CREATE MODAL BODY
function makeModalBody(r){
    let output = `
                <div id="recipe-details-modal-content" >
                    <!--header-->
                    <div class="modal-header">
                        <img src="${r.image}" alt="Image data here" class="img-fluid">
                    </div>
                    <!--body-->
                    <div class="modal-body">
                        <h4>${r.title}</h4>
                            <div>
                                <div class="h4">
                                    Summary:
                                </div>
                                ${r.summary}
                            </div>
                            <div>
                                <div class="h4 mt-2">
                                    Instructions:
                                </div>
                                ${r.instructions}
                            </div>
                            <div><strong>Ready in</strong> ${r.readyInMinutes} minutes</div> 
                        
                        <h4 class="mt-2">Ingredients</h4>
                        <ol> 
                        
                        
                                ` +  ingredientList(r) + `                   
                        
                        
                        </ol>
                        <h4>Diet Notes:</h4>
                        <ul>
                            <li>vegetarian: ${r.vegetarian}</li>
                            <li>vegan: ${r.vegan}</li>
                            <li>gluten free: ${r.glutenFree}</li>
                            <li>dairy free: ${r.dairyFree}</li>
                            <li>dish type: ${r.dishTypes}</li>
                        </ul>

                    </div>
                    <!--footer-->
                    <div class="modal-footer d-flex justify-content-between">
                        <div>
                            <small>Source:
                                <a href="${r.sourceUrl}" alt="source link">${r.sourceName}</a>
                            </small>
                        </div>
                        <form action="/recipes" method="post">
                            <div id="recipe-data">
                                <input type="hidden" name="cid" value="${r.id}">
                                <input type="hidden" name="title" value="${r.title}">
                                <input type="hidden" name="image-url" value="${r.image}">
                                <input type="hidden" name="summary" value="${r.summary}">
                                <input type="hidden" name="instructions" value="${r.instructions.replace("\"", "")}">
                                <input type="hidden" name="ready-in-minutes" value="${r.readyInMinutes}">
                                <input type="hidden" name="servings" value="${r.servings}">
                                <input type="hidden" name="source-name" value="${r.sourceName}">
                                <input type="hidden" name="source-url" value="${r.sourceUrl}">
                                <input type="hidden" name="vegetarian" value="${r.vegetarian}">
                                <input type="hidden" name="vegan" value="${r.vegan}">
                                <input type="hidden" name="gluten-free" value="${r.glutenFree}">
                                <input type="hidden" name="dairy-free" value="${r.dairyFree}">
                                <input type="hidden" name="user-id" value="${userId}">
                                
                                <div id="categories">
                                
                                ` + hiddenCategoryInputList(r) + `
                                
                                
                                </div>
                                
                            </div>
                            <div id="ingredients">      <!--for each-->
    
    
                            ` + hiddenIngredientInputList(r) + `
    
    
                            </div>
                            <div>
                                <button 
                                    class="btn btn-secondary" 
                                    type="button" 
                                    data-dismiss="modal">
                                        Close
                                </button>                                
                                <button 
                                    class="btn btn-primary" 
                                    type="submit">
                                        Add to Your Feed
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
    `;
    userId = "";

    return output;

}

// CREATE INGREDIENT LIST ITEM
function ingredientListItem(rI){
    return `
        <li>${rI.name}, Amount: ${rI.original}</li>
    `
}

// COMBINE INGREDIENTS INTO A LIST
function ingredientList(r){
    let output = '';
    for (let i = 0; i < r.extendedIngredients.length; i++) {
        output += ingredientListItem(r.extendedIngredients[i]);
    }
    return output;
}

// CREATE HIDDEN INGREDIENT INPUT FOR FORM
function hiddenIngredientInputs(rI){
    return `
                <input type="hidden" name="ingredient-name" value="${rI.name.replace("\"", "")}">
                <input type="hidden" name="ingredient-original" value="${rI.original.replace("\"", "")}">
    `
}

// COMBINE HIDDEN INGREDIENT LIST FOR FORM
function hiddenIngredientInputList(r){
    let output = '';
    for (let i = 0; i < r.extendedIngredients.length; i++) {
        output += hiddenIngredientInputs(r.extendedIngredients[i]);
    }
    return output;
}

// CREATE HIDDEN CAT INPUT FOR FORM
function hiddenCategoryInputs(rC){
    return `
                <input type="hidden" name="category-type" value="${rC}">
    `
}

// COMBINE HIDDEN CAT LIST FOR NORM
function hiddenCategoryInputList(r) {
    let output = `<input type="hidden" name="category-type" value="${keyWord}">`;
    for (let i = 0; i < r.dishTypes.length; i++) {
        output += hiddenCategoryInputs(r.dishTypes[i]);
    }

    keyWord = "";

    return output;
}