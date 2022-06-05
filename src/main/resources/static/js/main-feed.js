
// CAT FILTER BUTTONS
// $('#all-recipe-filter').on('click', function() {
//     console.log('click')
//     $('#feed').html(changeFeed('recipes'))
// })
//
// $('#breakfast-recipe-filter').on('click', function() {
//     console.log('click')
//     $('#feed').html(changeFeed('breakfastRecipesFinal'))
// })

$('#breakfast-recipe-filter').on('click', () => {
    console.log('test click');
    $('#feed').html(changeFeed('breakfastRecipesFinal'));
})




// CONSTRUCT FEED
function changeFeed(newFeed){
    return '' +
        '                        <div th:each="recipe: ${' + newFeed + '}">' +
        '                            <div class="card mt-3">' +
        '                                <!--CARD HEADER-->' +
        '                                <div class="card-header">' +
        '                                    <div class="row">' +
        '                                        <!--AVATAR-->' +
        '                                        <div class="col-2">' +
        '                                            <img src="https://source.unsplash.com/random/50x50/?raccoon" alt="px" class="img-fluid rounded-circle d-none d-md-block about-img px-1">' +
        '                                        </div>' +
        '                                        <!--USERNAME-->' +
        '                                        <div class="col-10 d-flex align-items-center">' +
        '                                            <div>' +
        '                                                <div>' +
        '                                                    <strong th:text="${recipe.user != null}? ${recipe.user.username} : \'Feed.\'" >username</strong>' +
        '                                                </div>' +
        '                                                <div th:each="cat: ${recipe.recipeCategories}" class="d-inline-block">' +
        '                                                    <div class="small text-muted mx-1">' +
        '                                                        <span th:text="${cat.type}">category</span>' +
        '                                                    </div>' +
        '                                                </div>' +
        '                                            </div>' +
        '                                        </div>' +
        '                                    </div>' +
        '                                </div>' +
        '                                <!--BODY-->' +
        '                                <div class="class-body">' +
        '                                    <!--IMG-->' +
        '                                    <div>' +
        '                                        <a th:href="@{/recipes/details/{id}(id=${recipe.id})}">' +
        '                                            <img th:src="${recipe.imgUrl}" alt="${img.getImageTitle()}" class="img-thumbnail" style="width: 100%">' +
        '                                        </a>' +
        '                                    </div>' +
        '                                    <!--ICON LINKS-->' +
        '                                    <div class="d-flex d-inline-block p-1">' +
        '                                        <div>' +
        '                                            <a href="#"><i class="fa-regular fa-heart fa-2x p-2"></i></a>' +
        '                                        </div>' +
        '                                        <div>' +
        '                                            <a th:href="@{/recipes/details/{id}(id=${recipe.id})}"><i class="fa-solid fa-list-check fa-2x p-2"></i></a>' +
        '                                        </div>' +
        '                                        <div>' +
        '                                            <a href="#"><i class="fa-regular fa-paper-plane fa-2x p-2"></i></a>' +
        '                                        </div>' +
        '                                        <div style="margin-left: auto">' +
        '                                            <a href="#"><i class="fa-regular fa-bookmark fa-2x p-2"></i></a>' +
        '                                        </div>' +
        '                                    </div>' +
        '                                    <!--LIKES and TITLE-->' +
        '                                    <div class="p-3">' +
        '                                        <div class="small text-muted">' +
        '                                            # of likes' +
        '                                        </div>' +
        '                                        <div>' +
        '                                            <span th:text="${recipe.title}">Title of Recipe</span>' +
        '                                        </div>' +
        '                                        <div class="small text-muted">' +
        '                                            # of Comments' +
        '                                        </div>' +
        '                                    </div>' +
        '                                </div>' +
        '                                <!--FOOTER-->' +
        '                                <div class="card-footer">' +
        '                                    <div class="d-flex d-inline-block">' +
        '                                        <div>' +
        '                                            <i class="fa-regular fa-face-smile fa-2x pt-1 pr-1"></i>' +
        '                                        </div>' +
        '                                        <div class="input-group w-100 pl-2">' +
        '                                            <div class="form-outline w-100 "> ' +
        '                                                <input id="a-comment" type="Add a comment" class="form-control border-0" placeholder="Add a comment..." />' +
        '                                            </div>' +
        '                                        </div>' +
        '                                    </div>' +
        '                                </div>' +
        '                            </div>' +
        '                        </div>'


}
